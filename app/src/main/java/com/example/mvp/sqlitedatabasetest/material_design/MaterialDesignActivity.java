package com.example.mvp.sqlitedatabasetest.material_design;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mvp.sqlitedatabasetest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialDesignActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private FruitAdapter adapter;

    private Fruit fruit[] = {new Fruit("Apple", R.mipmap.picture),
            new Fruit("Apple", R.mipmap.picture1),
            new Fruit("banana", R.mipmap.picture2),
            new Fruit("orange", R.mipmap.picture3),
            new Fruit("pear", R.mipmap.picture4),
            new Fruit("watermelon", R.mipmap.picture5),
            new Fruit("grape", R.mipmap.picture6),
            new Fruit("strawberry", R.mipmap.picture7),
            new Fruit("manago", R.mipmap.picture8),
            new Fruit("pear1", R.mipmap.picture9)};
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            //让导航按钮显示出来，并设置显示的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        initFruit();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        mRecyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.red_);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rereshFruits();
            }
        });
    }

    private void rereshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruit();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruit() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruit.length);
            fruitList.add(fruit[index]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
