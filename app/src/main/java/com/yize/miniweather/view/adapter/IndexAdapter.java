package com.yize.miniweather.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
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

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {
    private List<WeatherBean.Index> indexList;
    private Context context;

    public IndexAdapter(List<WeatherBean.Index> indexList) {
        this.indexList = indexList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_adapter_index,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherBean.Index index=indexList.get(position);
        holder.tv_index_info.setText(index.getInfo());
        holder.tv_index_name.setText(index.getName());
        //Log.i("指数调试","onBindViewHolder()"+index.toString());
        Glide.with(context).load(R.mipmap.night_cloud).override(50).into(holder.iv_index_icon);
        holder.iv_index_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(index.getName());
                builder.setMessage(index.getDetail());
                builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
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
        return indexList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_index_info,tv_index_name;
        private ImageView iv_index_icon;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_index_info=v.findViewById(R.id.tv_index_info);
            tv_index_name=v.findViewById(R.id.tv_index_name);
            iv_index_icon=v.findViewById(R.id.iv_index_icon);

        }
    }
}
