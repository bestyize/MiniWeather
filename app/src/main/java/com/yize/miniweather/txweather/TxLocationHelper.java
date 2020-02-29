package com.yize.miniweather.txweather;

import android.content.Context;
import android.util.Log;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.yize.miniweather.bean.CityShip;
//https://lbs.qq.com/geo/guide-use.html
public class TxLocationHelper {

    public static void getCurrentLocation(Context context,TxCityLocationListener locationListener){
        TencentLocationRequest request=TencentLocationRequest.create().setInterval(100).setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA)
                .setAllowDirection(true).setAllowGPS(true);
        TencentLocationManager locationManager = TencentLocationManager.getInstance(context);
        int error = locationManager.requestLocationUpdates(request, new TencentLocationListener() {
            @Override
            public void onLocationChanged(TencentLocation location, int i, String s) {
                if(i==TencentLocation.ERROR_OK){
                    CityShip cityShip=new CityShip(location.getProvince(),location.getCity(),location.getDistrict());
                    cityShip.setCurrentName(location.getDistrict());
                    Log.i("定位结果",cityShip.toString());
                    locationListener.onSuccess(cityShip);
                }else {
                    Log.i("Failed","定位失败");
                    locationListener.onFailed("定位失败："+i);
                }
                locationManager.removeUpdates(this);
            }

            @Override
            public void onStatusUpdate(String s, int i, String s1) {

            }
        });

        if(error!=0){
            locationListener.onFailed("初始化定位失败："+request);
        }

    }



}
