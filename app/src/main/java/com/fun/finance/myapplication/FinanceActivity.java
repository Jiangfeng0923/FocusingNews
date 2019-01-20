package com.fun.finance.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static android.view.KeyEvent.KEYCODE_BACK;
import static com.fun.finance.myapplication.settings.Constant.BAIDU;
import static com.fun.finance.myapplication.settings.Constant.BAIDU_NEWS;
import static com.fun.finance.myapplication.settings.Constant.SOGOU_NEWS;
import static com.fun.finance.myapplication.settings.Constant._360;
import static com.fun.finance.myapplication.settings.Constant.KEY_ENGINE;
import static com.fun.finance.myapplication.settings.Constant.SOGOU;
import static com.fun.finance.myapplication.settings.Constant._360_NEWS;

public class FinanceActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Map<String, ?> mConcernedMarket;
    private String SETTINGS_ACTION = "finance.intent.action.edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSectionsPagerAdapter.currentFragment != null) {
                    mSectionsPagerAdapter.currentFragment.loadWeb();
                }
            }
        });

        refresh();
    }

    private void refresh() {
        SharedPreferences sp = getSharedPreferences("markets", Context.MODE_PRIVATE);
        mConcernedMarket = sp.getAll();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.removeAllTabs();
        Iterator iterator = mConcernedMarket.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();

            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(key);
            tabLayout.addTab(tab);
        }

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            startActivity(new Intent(SETTINGS_ACTION));
            return true;
        } else if (id == R.id.action_add_delete) {
            startActivity(new Intent("finance.intent.action.add_delete"));
        } else if (id == R.id.action_refresh) {
            refresh();
        } else if (id == R.id.search_engines_settings) {
            startActivity(new Intent("finance.intent.action.search_settings"));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        WebView mWebView;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_MARKETS = "markets";
        ArrayList<String> mMarketsList;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_finance, container, false);
            mWebView = (WebView) rootView.findViewById(R.id.webview);
            Log.d("JIANG","getX5WebViewExtension:"+mWebView.getX5WebViewExtension());
            setWebView();
            loadWeb();
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        public WebView getWebView() {
            return mWebView;
        }

        private void setWebView() {
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (request.isRedirect()) {
                        view.loadUrl(request.getUrl().toString());
                        return true;
                    }
                    return false;
                }
            });

            WebSettings webSettings = mWebView.getSettings();
            // 让WebView能够执行javaScript
            webSettings.setJavaScriptEnabled(true);
            // 让JavaScript可以自动打开windows
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            // 设置缓存
            webSettings.setAppCacheEnabled(true);
            // 设置缓存模式,一共有四种模式
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            // 设置缓存路径
//        webSettings.setAppCachePath("");
            // 支持缩放(适配到当前屏幕)
            webSettings.setSupportZoom(true);
            // 将图片调整到合适的大小
            webSettings.setUseWideViewPort(true);
            // 支持内容重新布局,一共有四种方式
            // 默认的是NARROW_COLUMNS
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            // 设置可以被显示的屏幕控制
            webSettings.setDisplayZoomControls(true);
            // 设置默认字体大小
            webSettings.setDefaultFontSize(12);
        }

        public void loadWeb() {
            SharedPreferences sp = getContext().getSharedPreferences("markets", Context.MODE_PRIVATE);
            Map<String, ?> markets = sp.getAll();

            mMarketsList = new ArrayList<>();
            Iterator iter = markets.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                mMarketsList.add(key);
            }

            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            if (mMarketsList != null && mMarketsList.size() > 0) {
                SharedPreferences spSearch = getContext().getSharedPreferences("search_engines", Context.MODE_PRIVATE);
                String realTime = SOGOU_NEWS;
                if (spSearch.getString(KEY_ENGINE, SOGOU).equals(SOGOU)) {
                    realTime = SOGOU_NEWS;
                } else if (spSearch.getString(KEY_ENGINE, SOGOU).equals(_360)) {
                    realTime = _360_NEWS;
                } else if (spSearch.getString(KEY_ENGINE, SOGOU).equals(BAIDU)) {
                    realTime = BAIDU_NEWS;
                }
                StringBuilder sb = new StringBuilder(realTime);
                sb.append(mMarketsList.get(position));
                mWebView.loadUrl(sb.toString());

            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        PlaceholderFragment currentFragment;


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mConcernedMarket == null ? 0 : mConcernedMarket.size();
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            currentFragment = (PlaceholderFragment) object;
            super.setPrimaryItem(container, position, object);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mSectionsPagerAdapter.currentFragment != null) {
            WebView currentWebView = mSectionsPagerAdapter.currentFragment.getWebView();
            if ((keyCode == KEYCODE_BACK) && currentWebView != null && currentWebView.canGoBack()) {
                currentWebView.goBack();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
