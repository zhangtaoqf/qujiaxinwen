package com.qf.zt.testdemo2.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qf.zt.testdemo2.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class NewsViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public NewsViewPagerFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
