package com.example.intentexamples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.intentexamples.receivers.SMSReceiver;

public class MainActivity extends AppCompatActivity {
    private int REQ_CODE = 1;
    private SMSReceiver smsReceiver;
    private IntentFilter intentFilter;
    private String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Check permission
        if (!checkPermission(Manifest.permission.CALL_PHONE) || !checkPermission(Manifest.permission.RECEIVE_SMS)){
            requestPermissions(new String[] {Manifest.permission.CALL_PHONE, Manifest.permission.RECEIVE_SMS}, REQ_CODE);
        }else{
            performAction();
        }
    }

//    Check permision
    private boolean checkPermission(String permission){
        int check = checkSelfPermission(permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
/*        String text = "";
        for (int i = 0; i < grantResults.length; i++) {
            text = text + grantResults[0] + " ";
        }
        Toast.makeText(this, grantResults.length, Toast.LENGTH_SHORT).show();*/
        if (requestCode == REQ_CODE){
            if (permissions.length == grantResults.length){
/*                int i;
                for (i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        break;
                    }
                }
                if (i == grantResults.length){
                    call();
                }else{

                }*/
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        Log.d("permission", "Required permission are not allowed!");
                        return;
                    }
                }
                performAction();
            }
        }
    }

    private void performAction() {
/*        Intent intent  = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:0348477517"));*/

/*        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Intent.EXTRA_TEXT, "Message");
        intent.setType("text/plain");
        startActivity(intent);*/
        Toast.makeText(this, "GOOGOGO", Toast.LENGTH_SHORT).show();
        smsReceiver = new SMSReceiver();
        intentFilter = new IntentFilter(ACTION);
        registerReceiver(smsReceiver, intentFilter);
    }
}