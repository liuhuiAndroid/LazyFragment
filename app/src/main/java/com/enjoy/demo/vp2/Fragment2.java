package com.enjoy.demo.vp2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enjoy.demo.FragmentDelegate;
import com.enjoy.demo.R;
import com.enjoy.demo.LazyFragment;

import java.util.ArrayList;

public class Fragment2 extends LazyFragment {

    private static final String TAG = "Fragment2";
    private ViewPager viewPager;  //对应的viewPager
    private ArrayList<Fragment> fragmentsList;//view数组

    public static Fragment newIntance() {
        Fragment2 fragment = new Fragment2();
        fragment.setFragmentDelegate(new FragmentDelegate(fragment));
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_2_vp;
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.viewpager01);

        fragmentsList = new ArrayList<>();

        // 又加载四个 子Fragment
        fragmentsList.add(Fragment2_vp_1.newIntance());
        fragmentsList.add(Fragment2_vp_2.newIntance());
        fragmentsList.add(Fragment2_vp_3.newIntance());
        fragmentsList.add(Fragment2_vp_4.newIntance());

        /**
         * 实例化一个PagerAdapter
         * 必须重写的两个方法
         * getCount
         * getItem
         */
        PagerAdapter pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentsList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentsList.size();
            }
        };

        viewPager.setAdapter(pagerAdapter);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container,savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + "Fragment2");
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
