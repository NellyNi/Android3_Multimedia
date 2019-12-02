package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.fireworks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class FireworksViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Dot>> mText;

    public FireworksViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Dot>> getText() {
        return mText;
    }
}