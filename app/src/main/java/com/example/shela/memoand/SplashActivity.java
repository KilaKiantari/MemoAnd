package com.example.shela.memoand;

/**
 * Created by Shela on 7/5/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Asus-Desktop on 6/13/2017.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    //berapa lama splash screen muncul dalam satuan milisecond
                    sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {

                    //activity yang akan dijalankan setelah splash screen selesai
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                    //sertakan method finish agar splash screen tidak bisa diakses kembali dgngan tombol back
                    finish();
                }
            }

            ;
        };
        timer.start();
    }
}
