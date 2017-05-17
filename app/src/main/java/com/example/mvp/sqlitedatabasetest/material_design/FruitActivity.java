package com.example.mvp.sqlitedatabasetest.material_design;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp.sqlitedatabasetest.R;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE = "fruit_image";

    public static void intentData(Context context, String fruitName, int fruitImageId) {
        Intent intent = new Intent(context, FruitActivity.class);
        intent.putExtra(FRUIT_NAME, fruitName);
        intent.putExtra(FRUIT_IMAGE, fruitImageId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_fruit);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_ttolbar);
        ImageView imageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView textView = (TextView) findViewById(R.id.fruit_content_text);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitName);

        Glide.with(this).load(fruitImageId).into(imageView);
        String fruitContent = generateFruitContent(fruitName);
        textView.setText(fruitContent);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            builder.append(fruitName);
        }
        return builder.toString();
    }
}
