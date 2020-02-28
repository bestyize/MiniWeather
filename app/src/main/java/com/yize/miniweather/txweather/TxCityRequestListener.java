package com.yize.miniweather.txweather;

import com.yize.miniweather.bean.CityShip;
import com.yize.miniweather.bean.WeatherBean;

import java.util.List;

public interface TxCityRequestListener {
    void onSuccess(List<CityShip> cityShipList);
    void onFailed(String reason);
}
