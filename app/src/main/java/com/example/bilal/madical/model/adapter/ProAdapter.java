package com.example.bilal.madical.model.adapter;

/**
 * Created by Bilal on 1/14/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bilal.madical.R;
import com.example.bilal.madical.model.pojo.Product;

import java.util.List;


public class ProAdapter extends ArrayAdapter<Product> {

    private Context context;
    private List<Product> proList1;

    public ProAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.proList1 = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_file, parent, false);
        Product product = proList1.get(position);
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(product.getName());
        return view;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_file, parent, false);
        Product product = proList1.get(position);
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(product.getName());
        return view;
    }
}

