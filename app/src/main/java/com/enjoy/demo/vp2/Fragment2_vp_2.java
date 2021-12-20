package com.enjoy.demo.vp2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enjoy.demo.FragmentDelegate;
import com.enjoy.demo.R;
import com.enjoy.demo.LazyFragment;

public class Fragment2_vp_2 extends LazyFragment {

    public static Fragment newIntance() {
        Fragment2_vp_2 fragment = new Fragment2_vp_2();
        fragment.setFragmentDelegate(new FragmentDelegate(fragment));
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vp_2;
    }

    @Override
    protected void initView(View view) { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
