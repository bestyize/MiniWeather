package com.yize.miniweather.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class CityWeatherFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    public CityWeatherFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList=fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {

        return fragmentList.size();
    }

    private int pageCount=0;
    @Override
    public void notifyDataSetChanged() {
        this.pageCount=getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(pageCount>0){
            pageCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
