package com.kl.montaha.findproducts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by user on 10/30/2016.
 */
public class MyAdapterProduct extends ArrayAdapter<Products> {
    public MyAdapterProduct(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).
                inflate(R.layout.item_my_Product, parent, false);

       EditText etName=(EditText)convertView.findViewById(R.id.etName);
        EditText etPlace = (EditText) convertView.findViewById(R.id.etPlace);
        EditText etCode = (EditText) convertView.findViewById(R.id.etCode);



        final Products Products = getItem(position);

              etName.setText(Products.getName());
        etCode.setText(Products.getCode());
        etPlace.setText(Products.getPlace());







        return convertView;
    }
}








