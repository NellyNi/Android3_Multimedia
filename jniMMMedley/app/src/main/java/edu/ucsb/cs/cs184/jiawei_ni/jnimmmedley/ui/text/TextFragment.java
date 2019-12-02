package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.text;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Locale;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.MainActivity;
import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.R;

public class TextFragment extends Fragment implements View.OnClickListener {
    private String currentText;
    private TextToSpeech speaker = null;
    private boolean readyToSpeak = false;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            // if the TTS object has not been initialized yet, pause this thread until it has:
            while (!readyToSpeak) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Thread Exception","Thread"+e);
                    return;
                }
            }

            // Now, get the text to be spoken, and let the TTS object speak it:
            String text = ((EditText) getActivity().findViewById(R.id.text_speech)).getText().toString();
            speaker.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle state) {
        View v = inflater.inflate(R.layout.fragment_text, container, false);
        ((MainActivity) getActivity()).showFloatingActionButton();
        ((MainActivity) getActivity()).changeFABColor();
        return v;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        // set the icon to a speaker (needs to be in resources-->drawable):
        fab.setImageResource(R.drawable.ic_volume_up_24px);
        fab.show();
        fab.setOnClickListener(this);

        if (speaker == null) {
            readyToSpeak = false;
            speaker = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        speaker.setLanguage(Locale.getDefault());
                        readyToSpeak = true;
                    }
                }
            });
        }

        EditText outView = (EditText) getActivity().findViewById(R.id.text_speech);
        if (state != null && state.containsKey("currentText")) {
            currentText = state.getString("currentText");
        }
        if (outView != null) outView.setText(currentText);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        EditText t = (EditText) getActivity().findViewById(R.id.text_speech);
        if(t != null) currentText = t.getText().toString();
        state.putString("currentText", currentText);

        if (speaker != null) {
            speaker.stop();
            speaker.shutdown();
            speaker = null;
            readyToSpeak = false;
        }
    }
}