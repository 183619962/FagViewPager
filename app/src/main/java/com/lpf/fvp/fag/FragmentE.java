package com.lpf.fvp.fag;

import android.widget.TextView;

import com.lpf.fvp.R;

/**
 * Created by asus on 2016/6/23.
 */
public class FragmentE extends BaseFragment {
    private String title;
    private TextView txt;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_layout_e;
    }

    @Override
    public void initView() {
        txt = (TextView) view.findViewById(R.id.msg);
        txt.setText("FragmentE:" + title);
    }

    public static FragmentE getInstance(String title) {
        FragmentE mf = new FragmentE();
        mf.title = title;
        return mf;
    }
}
