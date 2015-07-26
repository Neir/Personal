package ca.umontreal.ift2905.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedList;

import ca.umontreal.ift2905.api.Beer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ImageUtil {
	
	public static class ImageLoaderQueue {
		private class ImageAndLoader
		{
			public final ImageView iv;
			public final ProgressBar pb;
			
			public ImageAndLoader(ImageView iv, ProgressBar pb)
			{
				this.iv = iv;
				this.pb = pb;
			}
		}
		
		private LinkedList<ImageAndLoader> tasks;
		private Thread thread;
		private boolean running;
		private Runnable internalRunnable;
		private Drawable defaultBM;

		private HashMap<String, Drawable> cache;

		private final String ME = "TaskQueue";

		private class InternalRunnable implements Runnable {
			public void run()  {
						internalRun();
			}
		}

		public ImageLoaderQueue() {
			this(null);
		}

		public ImageLoaderQueue(Drawable defaultBM) {
			tasks = new LinkedList<ImageAndLoader>();
			internalRunnable = new InternalRunnable();
			cache = new HashMap<String, Drawable>();
			this.defaultBM = defaultBM;
		}

		public void start() {
			if (!running) {
				thread = new Thread(internalRunnable);
				thread.setDaemon(true);
				running = true;
				thread.start();
			}
		}

		public void stop() {
			running = false;
		}

		public void addTask(ImageView iv) {
			addTask(iv, null);
		}
		
		public void addTask(ImageView iv, ProgressBar pb) {
			if (iv == null)
				return;
			iv.setVisibility(View.INVISIBLE);
			// check cache
			String url = (String) iv.getTag();
			if (url == null)
				return;
			Drawable b;
			synchronized (cache) {
				b = cache.get(url);
			}
			if (b != null) {
				iv.setImageDrawable(b);
				iv.setVisibility(View.VISIBLE);
				if(pb != null)
					pb.setVisibility(View.GONE);
				return;
			}
			
			if(pb != null)
				pb.setVisibility(View.VISIBLE);
			
			// must load the image...
			if (!running)
				start();
			synchronized (tasks) {
				tasks.addLast(new ImageAndLoader(iv, pb));
				tasks.notify(); // notify any waiting threads
			}
		}

		// retourne le prochain imageview a traiter pour le thread interne
		private ImageAndLoader getNextTask() {
			int s;
			synchronized (tasks) {
				s = tasks.size();
			}
			int cs;
			synchronized (cache) {
				cs = cache.size();
			}
			// Log.d(ME, "getNextTask " + s + " todo, cache size is " + cs);
			synchronized (tasks) {
				if (tasks.isEmpty()) {
					try {
						tasks.wait();
					} catch (InterruptedException e) {
						Log.e(ME, "Task interrupted", e);
						stop();
					}
				}
				return tasks.removeFirst();
			}
		}

		// boucle principale du thread interne
		private void internalRun() {
			while (running) {
				final ImageAndLoader ivpb = getNextTask();
				final String url = (String) ivpb.iv.getTag();
				if (url == null)
					continue;
				// check cache again

				final Drawable tmp;
				synchronized (cache) {
					tmp = cache.get(url);
				}
				if (tmp != null) {
					// update the view
					ivpb.iv.post(new Runnable() {
						public void run() {
							if (((String) ivpb.iv.getTag()).equals(url))
								ivpb.iv.setImageDrawable(tmp);
							ivpb.iv.setVisibility(View.VISIBLE);
							if(ivpb.pb != null)
								ivpb.pb.setVisibility(View.GONE);
						};
					});
					continue;
				}
				
				Drawable b = null;
				try {
					b = Beer.loadHttpImage(url);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					b = null;
				} catch (IOException e) {
					e.printStackTrace();
					b = null;
				}
				
				// update cache
				if(b != null)
				{
					synchronized (cache) {
						cache.put(url, b);
					}
				}
				
				final Drawable bf = b == null ? defaultBM : b;
				
				// update UI thread... only if tag did not change
				ivpb.iv.post(new Runnable() {
					public void run() {
						if (((String) ivpb.iv.getTag()).equals(url))
						{
							if(bf != null)
								ivpb.iv.setImageDrawable(bf);
							else
								ivpb.iv.setImageResource(android.R.drawable.stat_notify_error);
						}
						ivpb.iv.setVisibility(View.VISIBLE);
						if(ivpb.pb != null)
							ivpb.pb.setVisibility(View.GONE);
					};
				});
			}
		}
	}
}
