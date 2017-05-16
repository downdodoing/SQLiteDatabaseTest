package com.example.mvp.sqlitedatabasetest.downloadsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.mvp.sqlitedatabasetest.MainActivity;
import com.example.mvp.sqlitedatabasetest.R;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNOtificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNOtificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this, "onSuccess", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNOtificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "onFailed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPause() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "onPause", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "onCanceled", Toast.LENGTH_LONG).show();
        }
    };
    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (null == downloadTask) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading....", 0));
                Toast.makeText(DownloadService.this, "开始下载", Toast.LENGTH_LONG).show();
            }
        }

        public void pauseDownload() {
            if (null != downloadTask) {
                downloadTask.pauseDownload();
            }
            Toast.makeText(DownloadService.this, "停止下载", Toast.LENGTH_LONG).show();
        }

        public void cancelDownload() {
            if (null != downloadTask) {
                downloadTask.cancelDownload();
            } else {
                if (null != downloadUrl) {
                    //取消下载需将文件删除
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);

                    if (file.exists()) {
                        file.delete();
                    }
                    getNOtificationManager().cancel(1);
                    stopForeground(true);
                }
            }
            Toast.makeText(DownloadService.this, "取消下载", Toast.LENGTH_LONG).show();
        }
    }

    private NotificationManager getNOtificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle(title);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }
}
