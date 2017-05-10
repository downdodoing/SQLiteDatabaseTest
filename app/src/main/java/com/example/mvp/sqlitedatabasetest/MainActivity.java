package com.example.mvp.sqlitedatabasetest;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
    }

    public void click_bnt(View view) {
        Book book = new Book();
        book.setName("Android");
        book.setAuthor("Dan");
        book.setPages(54);
        book.setPrice(16.5);
        book.save();
    }
}
