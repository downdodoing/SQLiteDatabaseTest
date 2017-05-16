package com.example.mvp.sqlitedatabasetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyServiceActivity extends AppCompatActivity {
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ((MyService.DownloadBinder) service).startDownload();
            ((MyService.DownloadBinder) service).getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void unBindService(View view) {
        unbindService(connection);
    }
}
