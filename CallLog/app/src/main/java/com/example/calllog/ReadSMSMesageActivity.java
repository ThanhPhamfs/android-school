package com.example.calllog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;

import com.example.calllog.Adapter.CallLogAdapter;
import com.example.calllog.Adapter.SMSMessageAdapter;
import com.example.calllog.Models.CallLog;
import com.example.calllog.Models.SMSMessage;

import java.util.ArrayList;

public class ReadSMSMesageActivity extends AppCompatActivity {
    private int REQ_CODE = 1;
    RecyclerView recyclerViewSMSMessage;
    SMSMessageAdapter adapter;
    ArrayList<SMSMessage> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_smsmesage);

        recyclerViewSMSMessage = findViewById(R.id.recyclerViewSMSMessage);
        recyclerViewSMSMessage.setHasFixedSize(true);
        recyclerViewSMSMessage.setLayoutManager(new LinearLayoutManager(ReadSMSMesageActivity.this, RecyclerView.VERTICAL, false));
        adapter = new SMSMessageAdapter(ReadSMSMesageActivity.this, R.layout.callog_item, list);
        recyclerViewSMSMessage.setAdapter(adapter);

        if (!checkPermission(Manifest.permission.READ_SMS)) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, REQ_CODE);
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
        readSMSMessages(list);
        adapter.notifyDataSetChanged();
    }

    private void readSMSMessages(ArrayList<SMSMessage> smsMessages) {
        Uri uri = Telephony.Sms.CONTENT_URI;
        String[] projection = {
                Telephony.Sms.ADDRESS,
                Telephony.Sms.BODY,
                Telephony.Sms.DATE
        };
        Cursor cursor = getContentResolver().query(uri, projection,
                null, null, Telephony.Sms.DATE + " DESC");
        while (cursor.moveToNext()) {
            SMSMessage smsMessage = new SMSMessage();
            smsMessage.setSender(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)));
            smsMessage.setBody(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)));
            smsMessage.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE)));
            smsMessages.add(smsMessage);
        }
    }
}