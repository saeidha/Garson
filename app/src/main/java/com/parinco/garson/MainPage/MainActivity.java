package com.parinco.garson.MainPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.parinco.garson.LoginAndSignup.LoginActivity;
import com.parinco.garson.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ActionBar actionBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    ListView lv;
    private LinearLayout linearLayout;
    int tabicons[] = {R.drawable.home, R.drawable.cafe2,
            R.drawable.restaurant,
            R.drawable.delivery, R.drawable.hamburger
            , R.drawable.beveragejuice2,
            R.drawable.map};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //check login


        //slidmenu start
        linearLayout = (LinearLayout) findViewById(R.id.linearslidemenu);
        lv = (ListView) findViewById(R.id.listview);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this
                , drawerLayout, R.string.open, R.string.close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //slidmenu finish
        SharedPreferences loginsp = getSharedPreferences
                ("loginSharedPrefrencess",MODE_PRIVATE);
        SharedPreferences.Editor she = loginsp.edit();
        boolean login = loginsp.getBoolean("login",false);
        Log.i("my",String.valueOf(login));
        if(!login){
            Intent loginintent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(loginintent);
        }


        actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setIcon(tabicons[0]);
        tabLayout.getTabAt(1).setIcon(tabicons[1]);
        tabLayout.getTabAt(2).setIcon(tabicons[2]);
        tabLayout.getTabAt(3).setIcon(tabicons[3]);
        tabLayout.getTabAt(4).setIcon(tabicons[4]);
        tabLayout.getTabAt(5).setIcon(tabicons[5]);
        tabLayout.getTabAt(6).setIcon(tabicons[6]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Homefragment(), "Home");
        adapter.addFragment(new Caffefragment(), "CaffeShop");
        adapter.addFragment(new Resturantfragment(), "Resturant");
        adapter.addFragment(new Cateringfragment(), "Catering");
        adapter.addFragment(new FastFoodfragment(), "FastFood");
        adapter.addFragment(new Juicefragment(), "Juice");
        adapter.addFragment(new NearMefragment(), "NearMe");
        viewPager.setAdapter(adapter);
    }

    //slidmenu start
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //slidmenu finish

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        //slidmenu start
        if (id == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(linearLayout)) {
                drawerLayout.closeDrawer(linearLayout);
            } else {
                drawerLayout.openDrawer(linearLayout);
            }
        }
        //slidmenu finish

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return mFragmentTitleList.get(position);
            return null;
        }
    }
}
