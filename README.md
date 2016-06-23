[toc]

###前言
首先上鸡汤，[FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout)，一个强大的第三方tabbar库（有各种体位，总有个姿势会让你舒适），点击查看相信你不会后悔。

正如标题所说，只需要不到20行的代码，实现tabbar，菜单选择效果，先上图。
![](http://img.blog.csdn.net/20160623214436332)

###编码
- 首先创建一个项目

- 布局，很简单
	```
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    xmlns:tl="http://schemas.android.com/apk/res-auto"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical"
	    app:layout_behavior="@string/appbar_scrolling_view_behavior"
	    tools:context="com.lpf.fvp.MainActivity"
	    tools:showIn="@layout/activity_main">
	
	    <com.flyco.tablayout.SlidingTabLayout
	        android:id="@+id/tablayout"
	        android:layout_width="match_parent"
	        android:layout_height="48dp"
	        android:background="?attr/colorPrimary"
	        tl:tl_divider_color="#1AFFFFFF"
	        tl:tl_divider_padding="13dp"
	        tl:tl_divider_width="1dp"
	        tl:tl_indicator_color="#FFFFFF"
	        tl:tl_indicator_height="1.5dp"
	        tl:tl_indicator_width_equal_title="true"
	        tl:tl_tab_padding="22dp"
	        tl:tl_tab_space_equal="true"
	        tl:tl_textSelectColor="#FFFFFF"
	        tl:tl_textUnselectColor="#66FFFFFF"
	        tl:tl_underline_color="#1AFFFFFF"
	        tl:tl_underline_height="1dp" />
	
	    <android.support.v4.view.ViewPager
	        android:id="@+id/view_pager"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent" />
	</LinearLayout>
	```
	>具体控件的使用请查看源项目[FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout)，有详细的介绍，在此不多说。

- 创建一个fragment
	```
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
	```
	```
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
	
	    <TextView
	        android:id="@+id/msg"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:gravity="center" />
	
	</LinearLayout>
	```
	>此处应该不存在任何疑惑，绑定布局，获取一个textview而已。

- 主界面，MainActivity代码实现
	```
	public class MainActivity extends AppCompatActivity {
	
	    @InjectView(R.id.toolbar)
	    Toolbar toolbar;
	    @InjectView(R.id.tablayout)
	    SlidingTabLayout tablayout;
	    @InjectView(R.id.view_pager)
	    ViewPager viewPager;
	    @InjectView(R.id.fab)
	    FloatingActionButton fab;
	
	    private ArrayList<MyFragment> mFagments = new ArrayList<>();
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
	        for (String s : mTitles) {
	            mFagments.add(MyFragment.getInstance(s));
	        }
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
	```
	>这里就说一下MyPagerAdapter,用于viewpager绑定fragment使用，在activity中FragmentManager通过getSupportFragmentManager()去获取，如果在是在fragment中就需要通过getChildFragmentManager()去说去。
	我想又得开始吐槽了，特麽这只有20行代码？哈哈，刨去初始化控件啊什么乱七八糟的，真的没有20行，亲自数过。

- 其他同方法实现的效果
![](http://img.blog.csdn.net/20160623214727849)

###补充
- 补充说明
	>可能有人会思考，你这现有的布局，都长的差不多，可以使用一个fragment绑定就可以了，如何使用多个fragment，绑定不同的页面呢？简单啦...

- 为了方便绑定数据，我们先创建一个BaseFragent的抽象类
	```
	public abstract class BaseFragment extends Fragment {
	    public abstract int getLayoutID();
	
	    public abstract void initView();
	
	    public View view;
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        view = inflater.inflate(getLayoutID(), container, false);
	        return view;
	    }
	
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        initView();
	    }
	}
	```
- 各自的fragment继承并绑定各自的布局，关联各自的数据，互不干扰
FragmentA
	```
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
	```
FragmentB
	```
	public class FragmentB extends BaseFragment {
	    private String title;
	    private TextView txt;
	
	    @Override
	    public int getLayoutID() {
	        return R.layout.fragment_layout_b;
	    }
	
	    @Override
	    public void initView() {
	        txt = (TextView) view.findViewById(R.id.msg);
	        txt.setText("FragmentB:" + title);
	    }
	
	    public static FragmentB getInstance(String title) {
	        FragmentB mf = new FragmentB();
	        mf.title = title;
	        return mf;
	    }
	}
	```
......FragmentC、FragmentD、FragmentE、FragmentF。


- 修改绑定viewpager的arraylist的对象类型

	```
	private ArrayList<BaseFragment> mFagments = new ArrayList<>();
	```

- 添加数据
	```
	//for (String s : mTitles) {
	//     mFagments.add(MyFragment.getInstance(s));
	//}
	mFagments.add(FragmentA.getInstance(mTitles[0]));
	mFagments.add(FragmentB.getInstance(mTitles[1]));
	mFagments.add(FragmentC.getInstance(mTitles[2]));
	mFagments.add(FragmentD.getInstance(mTitles[3]));
	mFagments.add(FragmentE.getInstance(mTitles[4]));
	mFagments.add(FragmentF.getInstance(mTitles[5]));
	```
>以上即可实现绑定不同的Fragment，效果如下：

	![](http://img.blog.csdn.net/20160623224312841)

[源码下载](https://github.com/183619962/FagViewPager)
	
