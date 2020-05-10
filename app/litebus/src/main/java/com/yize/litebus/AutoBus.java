package com.yize.litebus;

import android.app.Activity;

import com.yize.litebus.life.AutoLifeManager;

public class AutoBus {
    private volatile static AutoBus AUTO_LIFE_INSTANCE;
    private AutoLifeManager autoLifeManager;
    public static AutoBus getAutoLifeBus(Activity activity){
        if(AUTO_LIFE_INSTANCE==null){
            synchronized (LiteBus.class){
                if(AUTO_LIFE_INSTANCE==null){
                    AUTO_LIFE_INSTANCE=new AutoBus();
                }
            }
        }
        return AUTO_LIFE_INSTANCE;
    }

    private AutoBus() {
        autoLifeManager=AutoLifeManager.getDefaultManager();
    }

}
