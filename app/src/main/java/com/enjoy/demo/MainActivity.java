package com.enjoy.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 mViewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyFragment.newInstance(1));
        fragmentList.add(MyFragment.newInstance(2));
        // fragmentList.add(Fragment2.newIntance());
        fragmentList.add(MyFragment.newInstance(3));
        fragmentList.add(MyFragment.newInstance(4));
        fragmentList.add(Fragment5.instance());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(this, fragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.registerOnPageChangeCallback(onPageChangeCallback);
    }

    ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            int itemId = R.id.fragment_1;
            switch (i) {
                case 0:
                    itemId = R.id.fragment_1;
                    break;
                case 1:
                    itemId = R.id.fragment_2;
                    break;
                case 2:
                    itemId = R.id.fragment_3;
                    break;
                case 3:
                    itemId = R.id.fragment_4;
                    break;
                case 4:
                    itemId = R.id.fragment_5;
                    break;
            }
            bottomNavigationView.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fragment_1:
                    mViewPager.setCurrentItem(0, true);
                    return true;
                case R.id.fragment_2:
                    mViewPager.setCurrentItem(1, true);
                    return true;
                case R.id.fragment_3:
                    mViewPager.setCurrentItem(2, true);
                    return true;
                case R.id.fragment_4:
                    mViewPager.setCurrentItem(3, true);
                    return true;
                case R.id.fragment_5:
                    mViewPager.setCurrentItem(4, true);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.unregisterOnPageChangeCallback(onPageChangeCallback);
    }
}
