package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.draw;

import android.graphics.Path;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Map;

public class DrawViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Path>> mText;

    public DrawViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Path>> getText() {
        return mText;
    }

}