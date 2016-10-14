package com.qf.zt.testdemo2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.zt.testdemo2.R;


public class PicsFragment extends BaseFragment {


    public PicsFragment() {
        // Required empty public constructor
    }


    public static PicsFragment newInstance() {
        PicsFragment fragment = new PicsFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pics, container, false);
    }

}
