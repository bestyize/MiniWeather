package com.yize.miniweather.txweather;

import com.yize.miniweather.bean.CityShip;

public interface TxCityLocationListener {
    void onSuccess(CityShip cityShip);
    void onFailed(String reason);
}
