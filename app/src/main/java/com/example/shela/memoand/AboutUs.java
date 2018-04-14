package com.example.shela.memoand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shela on 7/5/2017.
 */

public class AboutUs extends ActionBarActivity {

    ImageView img1, img2;
    TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ImageView img1 =(ImageView) findViewById(R.id.imageView1);
        TextView txt1 =(TextView) findViewById(R.id.TextView1);
        TextView txt2 =(TextView) findViewById(R.id.TextView2);
        TextView txt3 =(TextView) findViewById(R.id.TextView3);
        TextView txt4 =(TextView) findViewById(R.id.TextView4);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }
        @Override
            public boolean onCreateOptionsMenu(Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_about, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back:
                Intent openMainActivity = new Intent(this, MainActivity.class);
                startActivity(openMainActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
