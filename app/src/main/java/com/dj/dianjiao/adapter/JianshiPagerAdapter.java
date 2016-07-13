package com.dj.dianjiao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dj.dianjiao.fragment.JianshiPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class JianshiPagerAdapter extends FragmentStatePagerAdapter {
    public List<JianshiPagerFragment> fragmentList;
    public FragmentManager fm;

    public JianshiPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.fm = fm;
        fragmentList = new ArrayList<JianshiPagerFragment>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

   @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void setFragmentList(List<JianshiPagerFragment> mFragmentList) {
        if(fragmentList.size()>0){
            fragmentList.clear();
        }
        if (mFragmentList!=null && mFragmentList.size() > 0) {
            fragmentList.addAll(mFragmentList);
        }
        notifyDataSetChanged();
    }
}
