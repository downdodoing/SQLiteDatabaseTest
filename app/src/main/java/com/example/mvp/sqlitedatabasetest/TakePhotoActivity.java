package com.example.mvp.sqlitedatabasetest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TakePhotoActivity extends Activity {
    private ImageView show_image;

    public static final String TAG = "TakePhotoActivity";

    private Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);
        show_image = (ImageView) findViewById(R.id.show_image);
    }

    public void takePhoto(View view) {
        //将图片存放在手机SD卡的应用关联缓存目录下
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

        if (outputImage.exists()) {
            outputImage.delete();
        }
        try {
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //用于判断当前手机Android的版本
        Toast.makeText(this, "takePhoto: " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(this, "com.example.mvp.sqlitedatabasetest", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (1 == requestCode) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                show_image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
