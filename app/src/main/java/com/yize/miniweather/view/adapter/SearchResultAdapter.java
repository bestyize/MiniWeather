package com.yize.miniweather.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yize.miniweather.R;
import com.yize.miniweather.bean.CityShip;
import com.yize.miniweather.txweather.TxWeatherHelper;
import com.yize.miniweather.txweather.TxWeatherRequestListener;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<CityShip> cityShipList;
    private Context context;
    private TxWeatherRequestListener listener;
    public SearchResultAdapter(List<CityShip> cityShipList) {
        this.cityShipList = cityShipList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_adapter_search_result,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityShip cityShip=cityShipList.get(position);
        String cityShipInfo=cityShip.getProvince()+"/"+cityShip.getCity()+"/"+cityShip.getCountry()+"\t"+cityShip.getCurrentName();
        holder.tv_search_result_adress.setText(cityShipInfo);
        holder.ll_search_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TxWeatherHelper.requestCity(cityShip,listener);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cityShipList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_search_result_adress;
        private LinearLayout ll_search_result;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_search_result_adress=itemView.findViewById(R.id.tv_search_result_adress);
            ll_search_result=itemView.findViewById(R.id.ll_search_result);
        }
    }

    public TxWeatherRequestListener getListener() {
        return listener;
    }

    public void setListener(TxWeatherRequestListener listener) {
        this.listener = listener;
    }
}
