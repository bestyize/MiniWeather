package com.yize.miniweather.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.yize.miniweather.R;
import com.yize.miniweather.bean.CityDetailBean;
import com.yize.miniweather.bean.CityShip;
import com.yize.miniweather.bean.WeatherBean;
import com.yize.miniweather.db.CityDatabaseHelper;
import com.yize.miniweather.view.adapter.CityManageAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.yize.miniweather.util.DefaultParams.OPCODE_ADD;
import static com.yize.miniweather.util.DefaultParams.OPCODE_NONE;

public class CityManageAcitivity extends AppCompatActivity {
    private static final String TAG="城市管理";
    private RecyclerView rv_city_manage;
    private List<CityDetailBean> cityList=new ArrayList<>();
    private CityManageAdapter cityManageAdapter;
    private CityDatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage_acitivity);
        initView();
        refreshView();
    }

    private void initView(){
        Toolbar tb_city_manage=findViewById(R.id.tb_city_manage);
        setSupportActionBar(tb_city_manage);
        getSupportActionBar().setTitle("城市管理");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_city_manage=findViewById(R.id.rv_city_manage);
        helper=new CityDatabaseHelper(this);
        cityManageAdapter=new CityManageAdapter(cityList,helper);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv_city_manage.setLayoutManager(manager);
        rv_city_manage.setAdapter(cityManageAdapter);

    }

    private void refreshView(){
        if(cityList.size()!=0){
            cityList.clear();
        }
        Log.i(TAG,"refreshView");
        helper.open();
        cityList.addAll(new ArrayList<>(helper.getAllCity()));
        helper.close();
        cityManageAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_city_manage,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_city_manage_add:
                Intent intent=new Intent(CityManageAcitivity.this,SearchCityActivity.class);
                startActivityForResult(intent,0);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int opcode=data.getIntExtra("opcode",OPCODE_NONE);
        Log.i(TAG,String.valueOf(opcode));
        if(opcode==OPCODE_ADD){
            CityShip cityShip=(CityShip)data.getSerializableExtra("cityShip");
            WeatherBean weatherBean=(WeatherBean)data.getSerializableExtra("weatherBean");
            helper.open();
            CityDetailBean city=buildCityDetailBean(cityShip,weatherBean);
            Log.i(TAG,city.toString());
            helper.addCity(city);
            helper.close();
            refreshView();
        }
    }

    private CityDetailBean buildCityDetailBean(CityShip cityShip,WeatherBean weatherBean){
        WeatherBean.ForecastDay today=weatherBean.getData().getForecastDay().get("1");
        CityDetailBean city=new CityDetailBean(cityShip.getCurrentName(),
                cityShip.getProvince(),
                cityShip.getCity(),
                cityShip.getCountry(),
                today.getMinDegree(),
                today.getMaxDegree(),
                today.getDayWeather(),
                today.getDayWeatherCode(),
                0
                );
        return city;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
