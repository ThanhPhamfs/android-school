package com.example.calllog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.calllog.Adapter.CallLogAdapter;
import com.example.calllog.Models.CallLog;

import java.util.ArrayList;

public class ReadCallLogsActivity extends AppCompatActivity {
    private int REQ_CODE = 1;
    RecyclerView recyclerViewCallLog;
    CallLogAdapter adapter;
    ArrayList<CallLog> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCallLog = findViewById(R.id.recyclerViewCallLog);
        recyclerViewCallLog.setHasFixedSize(true);
        recyclerViewCallLog.setLayoutManager(new LinearLayoutManager(ReadCallLogsActivity.this, RecyclerView.VERTICAL, false));
        adapter = new CallLogAdapter(ReadCallLogsActivity.this, R.layout.callog_item, list);
        recyclerViewCallLog.setAdapter(adapter);

        if (!checkPermission(Manifest.permission.READ_CALL_LOG)) {
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, REQ_CODE);
        } else {
            performAction();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE) {
            if (permissions.length == grantResults.length) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Log.d("permission", "Required permission are not allowed!");
                        return;
                    }
                }
                performAction();
            }
        }
    }

    //    Check permision
    private boolean checkPermission(String permission) {
        int check = checkSelfPermission(permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }

    private void performAction() {
//        list.add(new CallLog("4.6", "0998777777", "121234234234", "type1", "08/11/2020"));
//        list.add(new CallLog("5.6", "0898777777", "121234234234", "type1", "08/11/2020"));
//        recyclerViewCallLog = findViewById(R.id.recyclerViewCallLog);
////        recyclerViewCallLog.setHasFixedSize(true);
////        recyclerViewCallLog.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
////        adapter = new CallLogAdapter(MainActivity.this, R.layout.callog_item, list);
////        recyclerViewCallLog.setAdapter(adapter);
        readCallLogs(list);
        adapter.notifyDataSetChanged();
    }

    private void readCallLogs(ArrayList<CallLog> callLogs) {
        Uri uri = android.provider.CallLog.Calls.CONTENT_URI;
        String[] projection = {
                android.provider.CallLog.Calls.DURATION,
                android.provider.CallLog.Calls.NUMBER,
                android.provider.CallLog.Calls.COUNTRY_ISO,
                android.provider.CallLog.Calls.TYPE,
                android.provider.CallLog.Calls.DATE
        };
        Cursor cursor = getContentResolver().query(uri, projection,
                null, null, android.provider.CallLog.Calls.DATE + " DESC");
        while (cursor.moveToNext()) {
            CallLog callLog = new CallLog();
            callLog.setDuration(cursor.getString(cursor.getColumnIndexOrThrow(android.provider.CallLog.Calls.DURATION)));
            callLog.setContryIso(cursor.getString(cursor.getColumnIndexOrThrow(android.provider.CallLog.Calls.COUNTRY_ISO)));
            callLog.setDate(cursor.getString(cursor.getColumnIndexOrThrow(android.provider.CallLog.Calls.DATE)));
            callLog.setNumber(cursor.getString(cursor.getColumnIndexOrThrow(android.provider.CallLog.Calls.NUMBER)));
            callLog.setType(cursor.getString(cursor.getColumnIndexOrThrow(android.provider.CallLog.Calls.TYPE)));

            callLogs.add(callLog);
        }
        Log.d("SDT", callLogs.get(0).getNumber());
    }
}