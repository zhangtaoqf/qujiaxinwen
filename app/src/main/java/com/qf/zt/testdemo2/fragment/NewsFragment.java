package com.qf.zt.testdemo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.zt.testdemo2.R;
import com.qf.zt.testdemo2.adapter.NewsViewPagerFragmentAdapter;
import com.qf.zt.testdemo2.config.AppConfig;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {


    ViewPager viewPager;
    LinearLayout tab;
    NewsViewPagerFragmentAdapter viewPagerAdapter;
    View indicator;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        indicator = ((View) view.findViewById(R.id.fragment_views_indicatorId));

        LinearLayout.LayoutParams layoutParams = ((LinearLayout.LayoutParams) indicator.getLayoutParams());
        layoutParams.width = getResources().getDisplayMetrics().widthPixels/5;
        layoutParams.height = 10;
        indicator.setLayoutParams(layoutParams);

        viewPager = ((ViewPager) view.findViewById(R.id.fragment_news_viewPagerId));

        tab = ((LinearLayout) view.findViewById(R.id.fragment_news_tablinearLayoutId));

        //初始化ViewPager

        //实例化ViewPagerAdapter
        ArrayList<BaseFragment> fragments = new ArrayList<>();

        int type;
        for (int i = 0; i < 5; i++) {
            type = AppConfig.TYPE_PT;
            if(i == 0)
            {
                type = AppConfig.TYPE_ZX;
            }
            fragments.add(NewsPagerFragment.newInstance(i,type));
        }

        viewPagerAdapter = new NewsViewPagerFragmentAdapter(getChildFragmentManager(), fragments);
        //设置ViewPagerAdapter
        viewPager.setAdapter(viewPagerAdapter);
        //初始化Tab标签
        initTab();

        return view;
    }

    private void initTab() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int lastOffset = -1;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int mOffset = 0 ;
                Log.i("offset","左边滑动:"+positionOffsetPixels);
                mOffset = (int)(positionOffset * indicator.getWidth());
                Log.i("pos:","pos:"+position);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) indicator.getLayoutParams();
                layoutParams.leftMargin = (position)* indicator.getWidth() + mOffset;
                indicator.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //选择默认的选项
        selectTab(0);
    }

    private void selectTab(int position) {
        for (int i = 0; i < tab.getChildCount(); i++) {
            TextView textView = (TextView) tab.getChildAt(i);
            if(position == i)
            {
                //设置选中的样式
               // textView.setTextColor(getResources().getColor(R.color.mtextColor));
                textView.setTextAppearance(getContext(),R.style.MyTextViewSelected);
            }
            else
            {   //设置没有选中的样式
                //textView.setTextColor(Color.BLACK);
                textView.setTextAppearance(getContext(),R.style.MyTextViewNoSelect);
            }
        }
    }

}
