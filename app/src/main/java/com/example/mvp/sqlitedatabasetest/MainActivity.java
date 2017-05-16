package com.example.mvp.sqlitedatabasetest;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
<<<<<<< Updated upstream
import android.widget.Toast;
=======

import com.example.mvp.sqlitedatabasetest.downloadsample.DownloadActivity;
import com.example.mvp.sqlitedatabasetest.lbs.LocationActivity;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======

    public void getData(View view) {
        List<Book> books = DataSupport.findAll(Book.class);
        for (Book book1 : books) {
            Log.i("MainActivity", book1.getAuthor() + " " + book1.getPrice());
        }
    }

    public void getContacts(View view) {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void notification(View view) {
        Intent intent = new Intent(this, ContactsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content tittle")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        manager.notify(1, notification);
    }

    public void camara(View view) {
        Intent intent = new Intent(this, TakePhotoActivity.class);
        startActivity(intent);
    }

    public void getIntelData(View view) {
        Intent intent = new Intent(this, GetIntelDataActivity.class);
        startActivity(intent);
    }

    public void toService(View view) {
        Intent intent = new Intent(this, MyServiceActivity.class);
        startActivity(intent);
    }

    public void download(View view) {
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
    }

    public void location(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }
>>>>>>> Stashed changes
}
