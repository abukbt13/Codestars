package com.example.monitor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//private TextView myTextview;
private TextView myTextView;
private TextView myCalllog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.textView);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        myCalllog= findViewById(R.id.calllog);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, PackageManager.PERMISSION_GRANTED);



    }
    public void READ_SMS(View View){

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null,null,null);
        cursor.moveToFirst();

        myTextView.setText(cursor.getString(12));

        cursor.close();

    }
    public void showcalllocks(View View) {

        String stringoutput = "";
        Uri uriCalllogs = Uri.parse("content://call_log/calls");
        Cursor cursorCalllogs = getContentResolver().query(uriCalllogs, null, null, null, null);
        cursorCalllogs.moveToFirst();
        do {
            String stringNumber=cursorCalllogs.getString(cursorCalllogs.getColumnIndex(CallLog.Calls.NUMBER));
            String stringName=cursorCalllogs.getString(cursorCalllogs.getColumnIndex(CallLog.Calls.CACHED_NAME));

            String stringDuration=cursorCalllogs.getString(cursorCalllogs.getColumnIndex(CallLog.Calls.DURATION));

            String stringType=cursorCalllogs.getString(cursorCalllogs.getColumnIndex(CallLog.Calls.TYPE));
            stringoutput=stringoutput + "Number: " + stringNumber
                    +"\nName:"+stringName
                    +"\nDuration:" + stringDuration
                    +"\nType:" + stringType
                            + "\n\n";
            cursorCalllogs.close();
        }
        while (cursorCalllogs.moveToNext());
        myCalllog.setText(stringoutput);



    }

}