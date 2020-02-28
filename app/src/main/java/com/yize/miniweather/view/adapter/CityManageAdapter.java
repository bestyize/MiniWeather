package com.yize.miniweather.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yize.miniweather.R;
import com.yize.miniweather.bean.CityDetailBean;
import com.yize.miniweather.bean.CityShip;
import com.yize.miniweather.db.CityDatabaseHelper;

import java.util.List;

public class CityManageAdapter extends RecyclerView.Adapter<CityManageAdapter.ViewHolder> {
    private List<CityDetailBean> cityDetailBeanList;
    private Context context;
    private CityDatabaseHelper helper;

    public CityManageAdapter(List<CityDetailBean> cityDetailBeanList,CityDatabaseHelper helper) {
        this.cityDetailBeanList = cityDetailBeanList;
        this.helper=helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_adapter_city,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityDetailBean cityDetailBean=cityDetailBeanList.get(position);
        holder.tv_city_manage_city_name.setText(cityDetailBean.getCurrentName());
        holder.tv_city_manage_temp.setText(cityDetailBean.getMinTemp()+"℃~"+cityDetailBean.getMaxTemp()+"℃");
        holder.tv_city_manage_weather_desc.setText(cityDetailBean.getWeatherDesc());
        Glide.with(context).load(R.drawable.ic_remove_black_50dp).override(50).into(holder.iv_remove_city);
        holder.iv_remove_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("温馨提示");
                builder.setMessage("确定移除"+cityDetailBean.getCurrentName()+"?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityDetailBeanList.remove(cityDetailBean);
                        notifyDataSetChanged();
                        helper.open();
                        helper.removeCity(cityDetailBean);
                        helper.close();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return cityDetailBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_city_manage_city_name,tv_city_manage_temp,tv_city_manage_weather_desc;
        private ImageView iv_remove_city;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_city_manage_city_name=itemView.findViewById(R.id.tv_city_manage_city_name);
            tv_city_manage_temp=itemView.findViewById(R.id.tv_city_manage_temp);
            tv_city_manage_weather_desc=itemView.findViewById(R.id.tv_city_manage_weather_desc);
            iv_remove_city=itemView.findViewById(R.id.iv_remove_city);
        }
    }
}
