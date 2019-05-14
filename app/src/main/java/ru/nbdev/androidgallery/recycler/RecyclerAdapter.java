package ru.nbdev.androidgallery.recycler;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.nbdev.androidgallery.R;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private static final int THUMB_WIDTH = 640;
    private Resources resources;
    private List<RecyclerItem> items;

    public RecyclerAdapter(Resources resources, List<RecyclerItem> items) {
        this.resources = resources;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new CardViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //((CardViewHolder) holder).textView.setCompoundDrawablesWithIntrinsicBounds(0, items.get(position), 0, 0);
        RecyclerItem item = items.get(position);

        Bitmap original = BitmapFactory.decodeResource(resources, item.getDrawableID());
        int scaledHeight = (int) ((double) THUMB_WIDTH * original.getHeight() / original.getWidth());

        Bitmap thumb = Bitmap.createScaledBitmap(original, THUMB_WIDTH, scaledHeight, true);
        original.recycle();

        Drawable drawable = new BitmapDrawable(resources, thumb);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        ((CardViewHolder) holder).textView.setCompoundDrawables(null, drawable, null, null);
        ((CardViewHolder) holder).textView.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        //public ImageView imageView;
        public TextView textView;

        public CardViewHolder(View view) {
            super(view);
            //imageView = view.findViewById(R.id.recycler_item_image);
            textView = view.findViewById(R.id.recycler_item_text);
        }
    }

    public void swapData(List<RecyclerItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
