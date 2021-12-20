package com.enjoy.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDelegate extends Fragment {

    Fragment mFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dumpLifeCycle("onAttach: " + mFragment.hashCode());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dumpLifeCycle("onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dumpLifeCycle("onCreateView");
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dumpLifeCycle("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dumpLifeCycle("onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        dumpLifeCycle("onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        dumpLifeCycle("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        dumpLifeCycle("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        dumpLifeCycle("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        dumpLifeCycle("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dumpLifeCycle("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dumpLifeCycle("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dumpLifeCycle("onDetach");
    }

    @SuppressLint("ValidFragment")
    public FragmentDelegate(Fragment fragment) {
        super();
        this.mFragment = fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i("Derry", mFragment.getClass().getSimpleName() + " -> setUserVisibleHint isVisibleToUser: " + isVisibleToUser + " =============");
    }

    /**
     * 第一次进来不会触发
     * 跳转到下一个页面的时候会触发：true
     * 在回来的时候会触发：false
     * 返回到上一级的时候 不会促发
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.i("Derry", mFragment.getClass().getSimpleName() + " -> onHiddenChanged hidden: " + hidden + " ***************");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("Derry", "requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data);
    }

    public void dumpLifeCycle(final String method) {
        Log.i("Derry", "name: " + mFragment.getClass().getSimpleName() + " -> " + method);
    }
}
