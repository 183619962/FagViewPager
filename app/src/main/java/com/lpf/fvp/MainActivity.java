package com.lpf.fvp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;
import com.lpf.fvp.fag.BaseFragment;
import com.lpf.fvp.fag.FragmentA;
import com.lpf.fvp.fag.FragmentB;
import com.lpf.fvp.fag.FragmentC;
import com.lpf.fvp.fag.FragmentD;
import com.lpf.fvp.fag.FragmentE;
import com.lpf.fvp.fag.FragmentF;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tablayout)
    SlidingTabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private ArrayList<BaseFragment> mFagments = new ArrayList<>();
    private String[] mTitles = {"Tab1", "Tab2", "Tab333344", "Tab4", "Tab5", "Tab6"};

    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {
//        for (String s : mTitles) {
//            mFagments.add(MyFragment.getInstance(s));
//        }
        mFagments.add(FragmentA.getInstance(mTitles[0]));
        mFagments.add(FragmentB.getInstance(mTitles[1]));
        mFagments.add(FragmentC.getInstance(mTitles[2]));
        mFagments.add(FragmentD.getInstance(mTitles[3]));
        mFagments.add(FragmentE.getInstance(mTitles[4]));
        mFagments.add(FragmentF.getInstance(mTitles[5]));
        
        //getChildFragmentManager() 如果是嵌套在fragment中就要用这个
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tablayout.setViewPager(viewPager, mTitles);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }
}
