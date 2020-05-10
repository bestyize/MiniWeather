package com.yize.litebus.life;


import android.app.Fragment;

public class AutoLifeBlankFragment extends Fragment {
    private final AutoActivityFragmentLifecycle lifecycle;

    public AutoLifeBlankFragment() {
        lifecycle=new AutoActivityFragmentLifecycle();
    }

    public AutoActivityFragmentLifecycle getLifecycle(){
        return lifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }
}
