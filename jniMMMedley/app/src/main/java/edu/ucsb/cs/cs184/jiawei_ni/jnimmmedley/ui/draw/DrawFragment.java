package edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.MainActivity;
import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.R;
import edu.ucsb.cs.cs184.jiawei_ni.jnimmmedley.ui.fireworks.Dot;

public class DrawFragment extends Fragment {

    private DrawViewModel drawViewModel;
    DrawCanvas canvasView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        drawViewModel =
                ViewModelProviders.of(this).get(DrawViewModel.class);
        View root = inflater.inflate(R.layout.fragment_draw, container, false);

        ((MainActivity) getActivity()).showFloatingActionButton();
        ((MainActivity) getActivity()).changeColor();

        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        canvasView = (DrawCanvas) getActivity().findViewById(R.id.canvas);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.clearCanvas();
            }
        });


    }

}


