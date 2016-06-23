package com.lpf.fvp.fag;

import android.widget.TextView;

import com.lpf.fvp.R;

/**
 * Created by asus on 2016/6/23.
 */
public class FragmentA extends BaseFragment {
    private String title;
    private TextView txt;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_layout_a;
    }

    @Override
    public void initView() {
        txt = (TextView) view.findViewById(R.id.msg);
        txt.setText("FragmentA:" + title);
    }

    public static FragmentA getInstance(String title) {
        FragmentA mf = new FragmentA();
        mf.title = title;
        return mf;
    }
}
