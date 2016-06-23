package com.lpf.fvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 */
public class MyFragment extends Fragment {
    private View view;
    private String title;
    private TextView txt;

    public static MyFragment getInstance(String title) {
        MyFragment mf = new MyFragment();
        mf.title = title;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        txt = (TextView) view.findViewById(R.id.msg);
        txt.setText(title);
    }
}
