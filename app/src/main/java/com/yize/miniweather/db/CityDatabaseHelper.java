package com.yize.miniweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yize.miniweather.bean.CityDetailBean;

import java.util.ArrayList;
import java.util.List;

import static com.yize.miniweather.db.CityDatabase.CITY_NAME;
import static com.yize.miniweather.db.CityDatabase.COUNTRY_NAME;
import static com.yize.miniweather.db.CityDatabase.CURRENT_NAME;
import static com.yize.miniweather.db.CityDatabase.ID;
import static com.yize.miniweather.db.CityDatabase.LAST_UPDATE;
import static com.yize.miniweather.db.CityDatabase.MAX_TEMP;
import static com.yize.miniweather.db.CityDatabase.MIN_TEMP;
import static com.yize.miniweather.db.CityDatabase.PROVINCE_NAME;
import static com.yize.miniweather.db.CityDatabase.TABLE_NAME;
import static com.yize.miniweather.db.CityDatabase.WEATHER_CODE;
import static com.yize.miniweather.db.CityDatabase.WEATHER_DESC;

public class CityDatabaseHelper {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private static final String[] columns={
            ID,
            CURRENT_NAME,
            PROVINCE_NAME,
            CITY_NAME,
            COUNTRY_NAME,
            MIN_TEMP,
            MAX_TEMP,
            WEATHER_DESC,
            WEATHER_CODE,
            LAST_UPDATE
    };

    public CityDatabaseHelper(Context context) {
        helper=new CityDatabase(context);
    }

    public CityDetailBean addCity(CityDetailBean bean){
        long insertTime=System.currentTimeMillis();
        ContentValues values=new ContentValues();
        values.put(CURRENT_NAME,bean.getCurrentName());
        values.put(PROVINCE_NAME,bean.getProvince());
        values.put(CITY_NAME,bean.getCity());
        values.put(COUNTRY_NAME,bean.getCountry());
        values.put(MIN_TEMP,bean.getMinTemp());
        values.put(MAX_TEMP,bean.getMaxTemp());
        values.put(WEATHER_DESC,bean.getWeatherDesc());
        values.put(WEATHER_CODE,bean.getWeatherCode());
        values.put(LAST_UPDATE,insertTime);
        long id=db.insert(TABLE_NAME,null,values);
        bean.setId(id);
        bean.setLastUpdate(insertTime);
        return bean;
    }

    public CityDetailBean updateCity(CityDetailBean bean){
        long insertTime=System.currentTimeMillis();
        ContentValues values=new ContentValues();
        values.put(CURRENT_NAME,bean.getCurrentName());
        values.put(PROVINCE_NAME,bean.getProvince());
        values.put(CITY_NAME,bean.getCity());
        values.put(COUNTRY_NAME,bean.getCountry());
        values.put(MIN_TEMP,bean.getMinTemp());
        values.put(MAX_TEMP,bean.getMaxTemp());
        values.put(WEATHER_DESC,bean.getWeatherDesc());
        values.put(WEATHER_CODE,bean.getWeatherCode());
        values.put(LAST_UPDATE,insertTime);
        long id=db.update(TABLE_NAME,values,ID+"=?",new String[]{String.valueOf(bean.getId())});
        bean.setLastUpdate(insertTime);
        return bean;
    }

    public void removeCity(CityDetailBean cityDetailBean){
        db.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(cityDetailBean.getId())});
    }

    public boolean containsCity(String name){
        Cursor cursor=db.query(TABLE_NAME,columns,CITY_NAME+"=?",new String[]{name},null,null,null);
        return cursor.getCount()>0;
    }

    public CityDetailBean getCity(long id){
        Cursor cursor=db.query(TABLE_NAME,columns,ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
        }
        return reBuildCity(cursor);
    }

    public List<CityDetailBean> getAllCity(){
        Cursor cursor=db.query(TABLE_NAME,columns,null,null,null,null,null);
        List<CityDetailBean> cityDetailBeans=new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                cityDetailBeans.add(reBuildCity(cursor));
            }
        }
        return cityDetailBeans;
    }

    private CityDetailBean reBuildCity(Cursor cursor){
        CityDetailBean bean=new CityDetailBean(cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getLong(9));
        bean.setId(cursor.getLong(0));
        return bean;
    }


    public void removeAllCity(List<CityDetailBean> list){
        list.clear();
        db.execSQL("DROP TABLE "+TABLE_NAME);
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(" +
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                CURRENT_NAME+" TEXT NOT NULL," +
                PROVINCE_NAME+" TEXT NOT NULL," +
                CITY_NAME+" TEXT NOT NULL," +
                COUNTRY_NAME+" TEXT," +
                MIN_TEMP+" TEXT NOT NULL," +
                MAX_TEMP+" TEXT NOT NULL," +
                WEATHER_DESC+" TEXT NOT NULL," +
                WEATHER_CODE+" TEXT NOT NULL," +
                LAST_UPDATE+" Integer NOT NULL);");
    }



    public void open(){
        db=helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }
}
