package com.yize.litebus.life;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

public class AutoLifeManager {

    /**
     * 单例模式创建自动生命周期管理器
     */
    private static final String TAG="com.yize.litebus.life";
    private volatile static AutoLifeManager DEFAULT_MANAGER;

    private AutoLifeManager(){}
    public static AutoLifeManager getDefaultManager(){
        if(DEFAULT_MANAGER==null){
            synchronized (AutoLifeManager.class){
                if(DEFAULT_MANAGER==null){
                    DEFAULT_MANAGER=new AutoLifeManager();
                }
            }
        }
        return DEFAULT_MANAGER;
    }

    /**
     * 绑定生命周期
     * @param activity
     * @param lifeListener
     */
    public void bindLifeCycle(Activity activity, AutoLifeListener lifeListener){
        FragmentManager fm=activity.getFragmentManager();
        AutoLifeBlankFragment blankFragment=getRequestManagerFragment(fm);
        blankFragment.getLifecycle().addListener(lifeListener);
    }
    private AutoLifeBlankFragment getRequestManagerFragment(final FragmentManager fm){
        AutoLifeBlankFragment blankFragment=(AutoLifeBlankFragment)fm.findFragmentByTag(TAG);
        if(blankFragment==null){
            synchronized (this){
                if(blankFragment==null){
                    blankFragment=new AutoLifeBlankFragment();
                    fm.beginTransaction().add(blankFragment,TAG).commitAllowingStateLoss();
                }
            }
        }
        return blankFragment;
    }
}
