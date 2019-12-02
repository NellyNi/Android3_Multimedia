package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.text;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TextViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }


}