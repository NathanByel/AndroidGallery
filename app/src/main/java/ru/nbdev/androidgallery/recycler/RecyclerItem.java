package ru.nbdev.androidgallery.recycler;

public class RecyclerItem {
    private int drawableID;
    private String text;

    public RecyclerItem(int drawableID, String text) {
        this.drawableID = drawableID;
        this.text = text;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public String getText() {
        return text;
    }
}
