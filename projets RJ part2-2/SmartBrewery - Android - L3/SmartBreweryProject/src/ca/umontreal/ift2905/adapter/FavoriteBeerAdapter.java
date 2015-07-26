package ca.umontreal.ift2905.adapter;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import ca.umontreal.ift2905.activities.R;
import ca.umontreal.ift2905.api.Beer;
import ca.umontreal.ift2905.util.ImageUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FavoriteBeerAdapter extends BaseAdapter {
    private Context context;
    private List<String> beerNames;
    private List<String> iconUrls;
    private ImageUtil.ImageLoaderQueue imageUtil;
    
    public FavoriteBeerAdapter(Context c, List<String> names, List<String> urls, ImageUtil.ImageLoaderQueue imageQ) {
        context = c;
        beerNames = names;
        iconUrls = urls;
        imageUtil = imageQ;
    }
 
    @Override
    public int getCount() {
        return beerNames.size();
    }
 
    @Override
    public Object getItem(int position) {
        return beerNames.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = beerNames.get(position);
        String url = iconUrls.get(position);
        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.favorite_beers_info, null);
        }
 
        ImageView beerIcon = (ImageView)v.findViewById(R.id.favoriteBeerIcon);
        System.out.println(beerIcon);
        ProgressBar iconLoad = (ProgressBar)v.findViewById(R.id.favoriteBeerIconLoad);
        System.out.println(iconLoad);
        TextView beerName = (TextView)v.findViewById(R.id.favoriteBeerName);
        beerIcon.setTag(url);
        imageUtil.addTask(beerIcon, iconLoad);
        
//        Drawable image = null;
//        
//        try {
//			image = Beer.loadHttpImage(url);
//		} catch (ClientProtocolException e) {
//		} catch (IOException e) {
//		}
//        beerIcon.setImageDrawable(image);
        beerName.setText(name);
 
        // return view
        return v;
    }

}
