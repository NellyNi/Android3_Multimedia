package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.speech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.MainActivity;
import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.R;

public class SpeechFragment extends Fragment {

    private SpeechViewModel speechViewModel;
    private static final int SPEECH_TO_TEXT_REQUEST = 1;  // The request code
    private static final String PROMPT_TEXT = "Tap to speak.";
    private static final String EXCEPTION_TEXT = "Sorry, this function is not supported by your phone.";
    private String spokenText;
    private TextView speechText;
    private String currentText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        speechViewModel =
                ViewModelProviders.of(this).get(SpeechViewModel.class);
        View root = inflater.inflate(R.layout.fragment_speech, container, false);

        ((MainActivity) getActivity()).showFloatingActionButton();
        ((MainActivity) getActivity()).changeSpeakColor();
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        // set the icon to a speaker (needs to be in resources-->drawable):
        fab.setImageResource(R.drawable.ic_keyboard_voice_24px);
        fab.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Button Clicked.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); // chooose language
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, PROMPT_TEXT); // prompt text

                try {
                    startActivityForResult(intent, SPEECH_TO_TEXT_REQUEST);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(), EXCEPTION_TEXT, Toast.LENGTH_LONG).show();
                }

            }
        });

        if (spokenText != null) {
            ((TextView) getActivity().findViewById(R.id.speech_text)).setText(spokenText);
        }
        final TextView outView = (TextView) getActivity().findViewById(R.id.speech_text);
        if (savedInstanceState != null && savedInstanceState.containsKey("currentText")) {
            currentText = savedInstanceState.getString("currentText");
        }
        if (outView != null) outView.setText(currentText);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (intent != null) {
            ArrayList<String> list = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            spokenText = list.get(0);
            if (spokenText != null) {
                speechText = (TextView) getActivity().findViewById(R.id.speech_text);
                speechText.setText(spokenText);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        TextView t = (TextView) getActivity().findViewById(R.id.speech_text);
        if(t != null) currentText = t.getText().toString();
        state.putString("currentText", currentText);

    }

}