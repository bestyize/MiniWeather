package com.yize.litebus.life;

import java.lang.ref.WeakReference;

public interface LifeCycle {
    void addListener(AutoLifeListener lifeListener);
}
