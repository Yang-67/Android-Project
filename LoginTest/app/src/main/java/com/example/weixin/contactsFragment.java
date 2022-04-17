package com.example.weixin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import com.example.logintest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class contactsFragment extends Fragment {


    public contactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab03, container, false);
    }

}
