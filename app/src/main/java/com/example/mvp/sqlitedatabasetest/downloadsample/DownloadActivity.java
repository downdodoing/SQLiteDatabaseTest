package com.example.mvp.sqlitedatabasetest.downloadsample;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mvp.sqlitedatabasetest.R;

public class DownloadActivity extends Activity {

    private DownloadService.DownloadBinder downloadBinder;
    public static final String URL = "http://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";

    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void startDownload(View view) {
        downloadBinder.startDownload(URL);
    }

    public void pauseDownload(View view) {
        downloadBinder.pauseDownload();
    }

    public void cancelDownload(View view) {
        downloadBinder.cancelDownload();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (1 == requestCode) {
            if (!(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
