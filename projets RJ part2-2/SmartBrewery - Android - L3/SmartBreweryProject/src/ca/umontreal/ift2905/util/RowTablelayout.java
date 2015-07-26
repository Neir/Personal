package ca.umontreal.ift2905.util;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RowTablelayout {
	private String	beerName;
	private int qty;
	private Button plus = null;
	private Button moins = null;
	private TextView qtyView = null;
	
	public RowTablelayout(String beerName, Button plus, Button moins, TextView textView){
		this.beerName=beerName;
		this.plus = plus;
		this.moins = moins;
		this.qtyView = textView;
		qty = 0;
	    this.plus.setOnClickListener(plusListener);
	    this.moins.setOnClickListener(moinsListener);
	}
	
	public int getQty(){
		return qty;
	}
	
	public String getBeerName(){
		return beerName;
	}
	
	public void clear(){
		qty = 0;
		qtyView.setText("0x");
	}

	// Listener du bouton d'ajout
	private OnClickListener plusListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			qty++;
			qtyView.setText(qty+"x");
		}
	};

	// Listener du bouton de suppression
	private OnClickListener moinsListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(qty>0){
				qty--;
				qtyView.setText(qty+"x");
			}
		}
	};
}