package com.example.shela.memoand;

/**
 * Created by Shela on 7/5/2017.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static android.R.attr.id;

public class ViewAll extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DbHelper dbcenter;
    public static ViewAll ma;
    SQLiteDatabase db;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);



        ma = this;
        dbcenter = new DbHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME + " order by date ",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"[ Hapus Tugas ]"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewAll.this);
                builder.setTitle("Warning !!");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){

                            case 0 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from comments_table where title = '"+selection+"'");
                                RefreshList();
                                db.close();

                                break;

                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_viewall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back:
                Intent openMainActivity = new Intent(this, MainActivity.class);
                startActivity(openMainActivity);
                return true;
            case R.id.action_settings:
                Intent ab = new Intent(ViewAll.this, AboutUs.class);
                startActivity(ab);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}