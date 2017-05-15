package com.example.mvp.sqlitedatabasetest.downloadsample;

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPause();

    void onCanceled();
}
