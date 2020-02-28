package com.yize.miniweather.txweather;

import com.yize.miniweather.bean.CityShip;
import com.yize.miniweather.bean.WeatherBean;

public interface TxWeatherRequestListener {
    void onSuccess(CityShip cityShip, WeatherBean weatherBean);
    void onFailed(String reason);
}
