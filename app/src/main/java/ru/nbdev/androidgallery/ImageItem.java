package ru.nbdev.androidgallery;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageItem {
    private int drawableID;
    private String text;

    public ImageItem( int drawableID, String text) {
        this.drawableID = drawableID;
        this.text = text;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public String getText() {
        return text;
    }

    public Drawable getPreviewThumb(Resources resources, int width, int height) {
        Bitmap thumb = ScaledBitmapLoader.decodeSampledBitmapFromResource(resources, drawableID, width, height);
        Drawable previewThumb = new BitmapDrawable(resources, thumb);
        previewThumb.setBounds(0, 0, previewThumb.getMinimumWidth(), previewThumb.getMinimumHeight());
        return previewThumb;
    }
}
