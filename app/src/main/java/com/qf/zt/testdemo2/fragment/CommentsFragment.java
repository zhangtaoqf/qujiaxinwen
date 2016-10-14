package com.qf.zt.testdemo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.zt.testdemo2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends BaseFragment {


    public CommentsFragment() {
        // Required empty public constructor
    }

    public static CommentsFragment newInstance() {

        CommentsFragment fragment = new CommentsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

}
