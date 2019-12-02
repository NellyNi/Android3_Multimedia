package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.movie;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.MainActivity;
import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.R;

public class MovieFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private VideoView videoView;
    private int videoPosition;
    View root;
    private static final String TextExtra = "videoBookmark";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieViewModel =
                ViewModelProviders.of(this).get(MovieViewModel.class);
        root = inflater.inflate(R.layout.fragment_movie, container, false);

        ((MainActivity) getActivity()).hideFloatingActionButton();

        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
        videoView = (VideoView)root.findViewById(R.id.video);
        videoView.setMediaController(new MediaController(getActivity()));

        Uri uri = Uri.parse("android.resource://"+ getActivity().getPackageName() + "/"+R.raw.bigbuck);
        videoView.setVideoURI(uri);
        videoView.start();

        if (savedInstanceState != null && savedInstanceState.containsKey("current")) {
            videoPosition = savedInstanceState.getInt("current");
        }
        if (videoView != null) videoView.seekTo(videoPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        VideoView v = (VideoView) getActivity().findViewById(R.id.video);
        if(v != null) videoPosition = v.getCurrentPosition();
        state.putInt("current", videoPosition);

    }

}


