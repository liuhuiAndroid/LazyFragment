package com.enjoy.demo.vp2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enjoy.demo.FragmentDelegate;
import com.enjoy.demo.R;
import com.enjoy.demo.LazyFragment;

// 同学们：这是T2  嵌套了一层 ViewPager的Fragment2_vp_1
public class Fragment2_vp_1 extends LazyFragment {

    private static final String TAG = "Fragment2_vp_1";

    public static Fragment newIntance() {
        Fragment2_vp_1 fragment = new Fragment2_vp_1();
        fragment.setFragmentDelegate(new FragmentDelegate(fragment));
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vp_1;
    }

    @Override
    protected void initView(View view) {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onFragmentLoadStop() {
        super.onFragmentLoadStop();
        Log.d(TAG, "onFragmentLoadStop" + " 停止一切更新");
    }

    @Override
    public void onFragmentLoad() {
        super.onFragmentLoad();
        Log.d(TAG, "onFragmentLoad" + " 真正更新数据");
    }
}
