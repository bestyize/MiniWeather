package com.yize.miniweather.txweather;

import com.google.gson.Gson;
import com.yize.miniweather.bean.CityDetailBean;
import com.yize.miniweather.bean.CityShip;
import com.yize.miniweather.bean.WeatherBean;
import com.yize.miniweather.util.AsyncHttpRequestListener;
import com.yize.miniweather.util.HttpAsyncTaskHelper;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxWeatherHelper {
    private static String txWeatherURLBuilder(Map<String,String> map){
        StringBuilder sb=new StringBuilder();
        for(String key:map.keySet()){
            try {
                sb.append(key+"=");
                sb.append(URLEncoder.encode(map.get(key),"utf-8"));
                sb.append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        sb.append("source=pc");
        String baseApi="https://wis.qq.com/weather/common";
        return baseApi+"?"+sb.toString();
    }

    public static String txWeatherURLBuilder(CityShip ship){
        Map<String,String> map=new HashMap<String, String>();
        map.put("province",ship.province);
        map.put("city",ship.city);
        map.put("country",ship.country);
        //默认获取观测站的情况，一小时天气预报，24 小时天气预报，指数，小贴士，日出日落，空气质量
        map.put("weather_type","observe|forecast_1h|forecast_24h|index|tips|rise|air");
        return txWeatherURLBuilder(map);
    }

    public static String txWeatherURLBuilder(CityDetailBean bean){
        Map<String,String> map=new HashMap<String, String>();
        map.put("province",bean.getProvince());
        map.put("city",bean.getCity());
        map.put("country",bean.getCountry());
        //默认获取观测站的情况，一小时天气预报，24 小时天气预报，指数，小贴士，日出日落，空气质量
        map.put("weather_type","observe|forecast_1h|forecast_24h|index|tips|rise|air");
        return txWeatherURLBuilder(map);
    }

    public static List<CityShip> buildCity(String response){
        response=response.substring(response.indexOf(":{")+2,response.indexOf("},"));
        response=response.replaceAll("\"[\\d]+\"","").replaceAll("\\\"","").replaceAll(" ","");
        String citys[]=response.split(":");
        List<CityShip> cityShips=new ArrayList<CityShip>();
        for (String c:citys){
            String ct[]=c.split(",");
            if(ct.length==0){
                return cityShips;
            }else if(ct.length==2) {
                CityShip ship=new CityShip(ct[0],ct[1],"");
                cityShips.add(ship);
            }else if (ct.length==3){
                CityShip ship=new CityShip(ct[0],ct[1],ct[2]);
                cityShips.add(ship);
            }
        }
        return cityShips;
    }

    public static WeatherBean parseWeatherData(String response){
        WeatherBean weatherBean=null;
        try {
            weatherBean=new Gson().fromJson(response,WeatherBean.class);
        }catch (Exception e){

        }

        return weatherBean;
    }

    public static void searchCity(String keyword,TxCityRequestListener listener){
        try {
            String cityLink="https://wis.qq.com/city/like?source=pc&city=" + URLEncoder.encode(keyword, "utf-8");
            HttpAsyncTaskHelper helper=new HttpAsyncTaskHelper(new AsyncHttpRequestListener() {
                @Override
                public void onSuccess(String result) {
                    List<CityShip> cityShip=new TxWeatherHelper().buildCity(result);
                    if(cityShip.size()==0){
                        listener.onFailed("未搜索到城市"+keyword);
                    }
                    for(CityShip ship:cityShip){
                        ship.setCurrentName(keyword);
                    }
                    listener.onSuccess(cityShip);
                }

                @Override
                public void onFailed(String reason) {
                    listener.onFailed(reason);
                }
            });
            helper.execute(cityLink);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void requestCity(CityShip cityShip,TxWeatherRequestListener listener){
        if(cityShip.getCountry().length()>0){
            cityShip.setCurrentName(cityShip.getCountry());
        }else {
            cityShip.setCurrentName(cityShip.getCity());
        }
        parse(TxWeatherHelper.txWeatherURLBuilder(cityShip),cityShip,listener);
    }



    public static void requestCity(String city,TxWeatherRequestListener listener){
        try {
            String cityLink="https://wis.qq.com/city/like?source=pc&city=" + URLEncoder.encode(city, "utf-8");
            HttpAsyncTaskHelper helper=new HttpAsyncTaskHelper(new AsyncHttpRequestListener() {
                @Override
                public void onSuccess(String result) {
                    List<CityShip> cityShip=new TxWeatherHelper().buildCity(result);
                    if(cityShip.isEmpty()==false){
                        cityShip.get(0).setCurrentName(city);
                        parse(TxWeatherHelper.txWeatherURLBuilder(cityShip.get(0)),cityShip.get(0),listener);
                    }else {
                        listener.onFailed("所查询的城市为空");
                    }
                }

                @Override
                public void onFailed(String reason) {
                    listener.onFailed(reason);
                }
            });
            helper.execute(cityLink);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    private static void parse(String link,CityShip cityShip,TxWeatherRequestListener listener){
        HttpAsyncTaskHelper helper=new HttpAsyncTaskHelper(new AsyncHttpRequestListener() {
            @Override
            public void onSuccess(String result) {
                WeatherBean weatherBean=TxWeatherHelper.parseWeatherData(result);
                if(weatherBean==null){
                    listener.onFailed("GSON数据解析失败");
                }
                listener.onSuccess(cityShip,weatherBean);
            }

            @Override
            public void onFailed(String reason) {
                listener.onFailed(reason);
            }
        });
        helper.execute(link);
    }

    public static String getWeatherStateIcon(String weatherCode,boolean day){
        if(day){
            return "http://mat1.gtimg.com/pingjs/ext2020/weather/pc/icon/weather/day/"+weatherCode+".png";
        }else {
            return "http://mat1.gtimg.com/pingjs/ext2020/weather/pc/icon/weather/night/"+weatherCode+".png";
        }
    }



}
