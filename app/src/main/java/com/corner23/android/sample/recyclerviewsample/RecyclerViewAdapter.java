package com.corner23.android.sample.recyclerviewsample;

import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.PaletteItem;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int ALPHA_VAL = 144;
    private List<PhotoCard> mPhotoCards;

    public RecyclerViewAdapter(List<PhotoCard> cards) {
        this.mPhotoCards = cards;
    }

    private int getAlphaColor(int color, int alpha) {
        return (alpha << 24) | (color & 0x00ffffff);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int pos) {
        final PhotoCard card = mPhotoCards.get(pos);

        viewHolder.imageView.setImageResource(card.getImageId());
        viewHolder.textView.setText(card.getTitle());
        viewHolder.textView.setTag(card);
        viewHolder.itemView.setTag(card);

        Palette.generateAsync(BitmapFactory.decodeResource(viewHolder.itemView.getResources(), card.getImageId()),
                new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        PaletteItem textPalette = palette.getLightMutedColor();
                        if (textPalette != null) {
                            int color = textPalette.getRgb();
                            viewHolder.textView.setTextColor(color);
                        }

                        PaletteItem backgroundPalette = palette.getDarkVibrantColor();
                        if (backgroundPalette != null) {
                            int color = backgroundPalette.getRgb();
                            viewHolder.decoView.setBackgroundColor(getAlphaColor(color, ALPHA_VAL));
                        }
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return mPhotoCards.size();
    }

    public void remove(PhotoCard card) {
        int position = getItemPosition(card);
        if (position != -1) {
            mPhotoCards.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(PhotoCard card, int position) {
        mPhotoCards.add(position, card);
        notifyItemInserted(position);
    }

    public int getItemPosition(PhotoCard card) {
        return mPhotoCards.indexOf(card);
    }
}
