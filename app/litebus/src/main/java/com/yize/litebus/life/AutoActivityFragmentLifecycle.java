package com.yize.litebus.life;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class AutoActivityFragmentLifecycle implements LifeCycle {
    private final Set<AutoLifeListener> autoLifeListeners=new HashSet<>();
    private boolean isStarted;
    private boolean isDestroyed;
    @Override
    public void addListener(AutoLifeListener lifeListener) {
        autoLifeListeners.add(lifeListener);
        if(isDestroyed){
            lifeListener.onDestroy();
        }else if(isStarted){
            lifeListener.onStart();
        }else {
            lifeListener.onStop();
        }
    }

    public void onStart(){
        isStarted=true;
        for (AutoLifeListener lifeListener:autoLifeListeners){
            lifeListener.onStart();
        }
    }

    public void onStop(){
        isStarted=false;
        for (AutoLifeListener lifeListener:autoLifeListeners){
            lifeListener.onStop();
        }
    }

    public void onDestroy(){
        isDestroyed=true;
        for (AutoLifeListener lifeListener:autoLifeListeners){
            lifeListener.onDestroy();
        }
    }


}
