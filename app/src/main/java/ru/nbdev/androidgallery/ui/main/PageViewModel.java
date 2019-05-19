package ru.nbdev.androidgallery.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import ru.nbdev.androidgallery.ImageItem;
import ru.nbdev.androidgallery.R;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<ImageItem> imageItem = Transformations.map(mIndex, new Function<Integer, ImageItem>() {
        @Override
        public ImageItem apply(Integer input) {
            switch (input) {
                case 1:
                    return new ImageItem(R.drawable.nature1, "text1");
                case 2:
                    return new ImageItem(R.drawable.nature2, "text2");
                case 3:
                    return new ImageItem(R.drawable.nature3, "text3");
            }
            return new ImageItem(R.mipmap.ic_launcher, "none");
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<ImageItem> getImageItem() {
        return imageItem;
    }
}