package com.lpf.fvp.fag;

import android.widget.TextView;

import com.lpf.fvp.R;

/**
 * Created by asus on 2016/6/23.
 */
public class FragmentC extends BaseFragment {
    private String title;
    private TextView txt;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_layout_c;
    }

    @Override
    public void initView() {
        txt = (TextView) view.findViewById(R.id.msg);
        txt.setText("FragmentC:" + title);
    }

    public static FragmentC getInstance(String title) {
        FragmentC mf = new FragmentC();
        mf.title = title;
        return mf;
    }
}
