package com.enjoy.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class LazyFragment extends Fragment {

    FragmentDelegate mFragmentDelegate;
    private View rootView = null;
    private boolean isViewCreated = false;
    // 记录上一次可见的状态
    private boolean isVisibleStateUp = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        E("onCreateView");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        isViewCreated = true;
        initView(rootView);
        // 解决第一次一直初始化loading一直显示的问题 【第二版2.1】
        if (getUserVisibleHint()) {
            // 手动来分发下
            setUserVisibleHint(true);
        }
        return rootView;
    }

    /**
     * 判断 Fragment 是否可见
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        E("setUserVisibleHint");
        if (isViewCreated) {
            if (isVisibleToUser && !isVisibleStateUp) {
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && isVisibleStateUp) {
                dispatchUserVisibleHint(false);
            }
        }
    }

    /**
     * 分发可见和不可见的动作
     *
     * @param visibleState
     */
    private void dispatchUserVisibleHint(boolean visibleState) {
        // 记录上一次可见的状态
        this.isVisibleStateUp = visibleState;
        if (visibleState && isParentInvisible()) {
            return;
        }
        if (visibleState) {
            // 加载数据， 对第一层有效，嵌套无效
            onFragmentLoad();
            // 手动嵌套分发执行
            // 在双重ViewPager嵌套的情况下，第一次滑到Frgment 嵌套ViewPager(fragment)的场景的时候
            // 此时只会加载外层Fragment的数据，而不会加载内嵌viewPager中的fragment的数据，因此，我们
            // 需要在此增加一个当外层Fragment可见的时候，分发可见事件给自己内嵌的所有Fragment显示
            dispatchChildVisibleState(true);

        } else {
            // 停止一切操作， 对第一层有效，嵌套无效
            onFragmentLoadStop();
            dispatchChildVisibleState(false);
        }
    }

    // 嵌套嵌套问题
    protected void dispatchChildVisibleState(boolean state) {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            // 循环遍历 嵌套里面的 子 Fragment 来分发事件操作
            for (Fragment fragment : fragments) {
                if (fragment instanceof LazyFragment && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                    ((LazyFragment) fragment).dispatchUserVisibleHint(state);
                }
            }
        }
    }

    /**
     * 判断父控件是否可见
     *
     * @return
     */
    private boolean isParentInvisible() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof LazyFragment) {
            LazyFragment fragment = (LazyFragment) parentFragment;
            return !fragment.isVisibleStateUp;
        }
        return false;
    }

    /**
     * 初始化布局和控件
     *
     * @param rootView
     */
    protected abstract void initView(View rootView);

    protected abstract int getLayoutRes();

    /**
     * 停止网络数据请求
     */
    public void onFragmentLoadStop() {
        E("onFragmentLoadStop");
    }

    /**
     * 加载网络数据请求
     */
    public void onFragmentLoad() {
        E("onFragmentLoad");
    }

    @Override
    public void onResume() {
        super.onResume();
        E("onResume");
        if (getUserVisibleHint() && !isVisibleStateUp) {
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        E("onPause");
        if (getUserVisibleHint() && isVisibleStateUp) {
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        E("onDestroyView");
    }

    public void setFragmentDelegate(FragmentDelegate fragmentDelegater) {
        mFragmentDelegate = fragmentDelegater;
    }

    private void E(String string) {
        if (mFragmentDelegate != null) {
            mFragmentDelegate.dumpLifeCycle(string);
        }
    }
}
