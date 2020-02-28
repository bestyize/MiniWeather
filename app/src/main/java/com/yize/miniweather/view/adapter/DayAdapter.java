package com.yize.miniweather.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yize.miniweather.R;
import com.yize.miniweather.bean.WeatherBean;
import com.yize.miniweather.txweather.TxWeatherHelper;
import com.yize.miniweather.util.DateHelper;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    private List<WeatherBean.ForecastDay> forecastDayList;
    private Context context;

    public DayAdapter(List<WeatherBean.ForecastDay> forecastDayList) {
        this.forecastDayList = forecastDayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_adapter_day,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherBean.ForecastDay forecastDay=forecastDayList.get(position);
        holder.tv_day_week.setText(DateHelper.convertDateToWeek(forecastDay.getTime()));
        holder.tv_day_day.setText(forecastDay.getTime().substring(5));
        holder.tv_day_max_temp.setText(forecastDay.getMaxDegree()+"℃");
        holder.tv_day_min_temp.setText(forecastDay.getMinDegree()+"℃");
        holder.tv_day_state.setText(forecastDay.getDayWeather());//根据dayweather推出晴转多云等状况
        holder.tv_day_wind_desc.setText(forecastDay.getDayWindDirection());//白天风向
        holder.tv_day_wind_power.setText(forecastDay.getDayWindPower()+"级");//白天风速
        Glide.with(context).load(TxWeatherHelper.getWeatherStateIcon(forecastDay.getDayWeatherCode(),true)).override(50).into(holder.iv_day_icon);//使用Glide加载图片，这里应该根据weatherCode加载
    }

    @Override
    public int getItemCount() {
        return forecastDayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_day_week,tv_day_day,tv_day_state,tv_day_max_temp,tv_day_min_temp,tv_day_wind_desc
                ,tv_day_wind_power;
        private ImageView iv_day_icon;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_day_week=v.findViewById(R.id.tv_day_week);
            tv_day_day=v.findViewById(R.id.tv_day_day);
            tv_day_state=v.findViewById(R.id.tv_day_state);
            tv_day_max_temp=v.findViewById(R.id.tv_day_max_temp);
            tv_day_min_temp=v.findViewById(R.id.tv_day_min_temp);
            tv_day_wind_desc=v.findViewById(R.id.tv_day_wind_desc);
            tv_day_wind_power=v.findViewById(R.id.tv_day_wind_power);
            tv_day_week=v.findViewById(R.id.tv_day_week);
            iv_day_icon=v.findViewById(R.id.iv_day_icon);

        }
    }
}
