package com.corner23.android.sample.recyclerviewsample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    View decoView;

    public ViewHolder(View v) {
        super(v);
        this.imageView = (ImageView) v.findViewById(R.id.photo);
        this.textView = (TextView) v.findViewById(R.id.title);
        this.decoView = v.findViewById(R.id.deco);
    }
}
