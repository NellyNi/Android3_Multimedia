package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.movie;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.R;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<Integer> mText;

    public MovieViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<Integer> getText() {
        return mText;
    }

}