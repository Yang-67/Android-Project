package com.example.tabFragment;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.newsnow.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuZhouFragment extends Fragment {
    public FuZhouFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fuzhou, container, false);
    }
}