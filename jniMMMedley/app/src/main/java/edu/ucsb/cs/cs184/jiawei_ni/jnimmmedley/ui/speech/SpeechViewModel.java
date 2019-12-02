package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.speech;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpeechViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpeechViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

}