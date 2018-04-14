package com.example.shela.memoand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class View_Note extends ActionBarActivity {

    SQLiteDatabase db;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        final long id = getIntent().getExtras().getLong(getString(R.string.row_id));

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_NAME + " where " + dbHelper.C_ID + "=" + id, null);
        TextView title = (TextView) findViewById(R.id.title);
        TextView detail = (TextView) findViewById(R.id.detail);
        TextView notetype = (TextView) findViewById(R.id.note_type_ans);
        TextView time = (TextView) findViewById(R.id.alertvalue);
        TextView date = (TextView) findViewById(R.id.datevalue);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                title.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TITLE)));
                detail.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DETAIL)));
                notetype.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TYPE)));
                time.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TIME)));
                date.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DATE)));

            }
            cursor.close();
        }
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final long id = getIntent().getExtras().getLong(getString(R.string.rowID));

        switch (item.getItemId()) {
            case R.id.action_back:
                Intent openMainActivity = new Intent(this, MainActivity.class);
                startActivity(openMainActivity);
                return true;
            case R.id.action_edit:

                Intent openEditNote = new Intent(View_Note.this, Edit_Note.class);
                openEditNote.putExtra(getString(R.string.intent_row_id), id);
                startActivity(openEditNote);
                return true;
            case R.id.action_settings:
                Intent ab = new Intent(View_Note.this, AboutUs.class);
                startActivity(ab);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
