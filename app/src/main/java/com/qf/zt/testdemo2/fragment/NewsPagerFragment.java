package com.qf.zt.testdemo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qf.zt.testdemo2.R;
import com.qf.zt.testdemo2.adapter.NewsPagerAdapter;
import com.qf.zt.testdemo2.adapter.NewsPagerViewPagerAdapter;
import com.qf.zt.testdemo2.bean.NewsBannerEntity;
import com.qf.zt.testdemo2.bean.NewsPagerEntity;
import com.qf.zt.testdemo2.config.AppConfig;
import com.qf.zt.testdemo2.uri.AppInterface;
import com.qf.zt.testdemo2.util.BitmapUtils;
import com.qf.zt.testdemo2.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.qf.zt.testdemo2.R.drawable.focus_point_1;

/**
 * 新闻页的tab页
 *
 * A simple {@link Fragment} subclass.
 */
public class NewsPagerFragment extends BaseFragment {

    private static final int[] img_res={focus_point_1,R.drawable.focus_point_2,R.drawable.focus_point_3};
    ListView listView;

    NewsPagerAdapter adapter;
    int type;
    ViewPager viewPager;
    ImageView indicator;
    TextView title;
    ImageView imageIcon;

    public NewsPagerFragment() {
        // Required empty public constructor
    }

    public static NewsPagerFragment newInstance(int i,int type) {
        NewsPagerFragment fragment = new NewsPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tid",i);
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_pager, container, false);
        //1.查找listView控件
        listView = ((ListView) view.findViewById(R.id.fragment_newspager_listViewId));
        //listView添加头部
        type = getArguments().getInt("type");
        addHeaderAndInit(inflater);

        //2.实例化适配器
        adapter = new NewsPagerAdapter(getContext());
        //3.设置适配器
        listView.setAdapter(adapter);

        return view;
    }

    private void initHeader() {
        switch (type){
            case AppConfig.TYPE_ZX:
                break;
            case AppConfig.TYPE_PT:
                break;
        }
    }

    private void addHeaderAndInit(LayoutInflater inflater) {
        View view = null;
        switch (type) {
            case AppConfig.TYPE_ZX:
                view = inflater.inflate(R.layout.header_fragment_newspage_type1,listView,false);
                viewPager = ((ViewPager) view.findViewById(R.id.header_fragment_newspager_type1_viewPagerId));
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        setTitle(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                indicator = ((ImageView) view.findViewById(R.id.header_fragment_newspager_type1_indicatorId));
                title = ((TextView) view.findViewById(R.id.header_fragment_newspager_type1_titleId));
                break;
            case AppConfig.TYPE_PT:
                view = inflater.inflate(R.layout.header_fragment_newspage_type2,listView,false);
                title = ((TextView) view.findViewById(R.id.header_fragment_newspager_type2_titleId));
                imageIcon = ((ImageView) view.findViewById(R.id.header_fragment_newspager_type2_iconId));
                break;
        }
        if(view!=null)
        {
            listView.addHeaderView(view);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int tid = getArguments().getInt("tid");
        //加载数据
        loadData(tid);
    }
    List<NewsBannerEntity> headerDatas = new ArrayList<NewsBannerEntity>();

    private void loadData(int tid) {
        //网络请求数据
        String headUrl ="";
        int headId;
        if(type == AppConfig.TYPE_ZX)
        {
            headUrl = AppInterface.URL_NEWS_PAGER_HAEDER_TYPE0;
        }
        else
        {
            headId = 167 + tid;
            headUrl = String.format(AppInterface.URL_NEWS_PAGER_HAEDER_TYPE1,headId);
        }
        //加载头部
        HttpUtils.getInstance().sendRequest(headUrl, new HttpUtils.Listener() {
            @Override
            public void onResponse(String result) {
                Log.i("info",result);
                try {
                    if(TextUtils.isEmpty(result))
                        return;
                    JSONArray jsonArray = new JSONArray(result);
                    List<View> headerViews = new ArrayList<View>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        NewsBannerEntity bannerEntity = new NewsBannerEntity(jsonArray.optJSONObject(i));
                        if(type == AppConfig.TYPE_ZX )
                        {
                            //初始化ImageView
                            ImageView imageView = new ImageView(getContext());
                            ViewPager.LayoutParams params = new ViewPager.LayoutParams();
                            params.height = ViewPager.LayoutParams.MATCH_PARENT;
                            params.width = ViewPager.LayoutParams.MATCH_PARENT;
                            imageView.setLayoutParams(params);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            headerViews.add(imageView);
                            BitmapUtils.getBitmapUtils().loadImage(bannerEntity.getIcon(),imageView);
                            //初始化indicator
                        }
                        else
                        {
                            BitmapUtils.getBitmapUtils().loadImage(bannerEntity.getIcon(),imageIcon);
                        }
                        headerDatas.add(bannerEntity);
                    }
                    if(type == AppConfig.TYPE_ZX)
                    {
                        NewsPagerViewPagerAdapter pagerViewPagerAdapter = new NewsPagerViewPagerAdapter(headerViews);
                        viewPager.setAdapter(pagerViewPagerAdapter);
                    }
                    setTitle(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //加载身子
        HttpUtils.getInstance().sendRequest(
                String.format(AppInterface.URL_NEWS_PAGER, tid),
                new HttpUtils.Listener() {
                    @Override
                    public void onResponse(String result) {
                        if(!TextUtils.isEmpty(result))
                        {
                            try {
                                List<NewsPagerEntity> dd = new ArrayList<>();
                                JSONArray jsonArray = new JSONArray(result);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                                    NewsPagerEntity newsPagerEntity = new NewsPagerEntity(jsonObject);
                                    dd.add(newsPagerEntity);
                                }
                                adapter.addAll(dd);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    private void setTitle(int index) {
        if( type == AppConfig.TYPE_PT)
        {
            title.setText(headerDatas.get(0).getTitle());
        }
        else
        {
            title.setText(headerDatas.get(index).getTitle());
            indicator.setImageResource(img_res[index]);
        }
    }
}
