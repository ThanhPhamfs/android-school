package com.example.intentexamples.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.StringTokenizer;

public class SMSReceiver extends BroadcastReceiver {
    public static String TAG = "RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "Call");
        Bundle data = intent.getExtras();
        if (data != null){
            Object[] pdus = (Object[]) data.get("pdus");
            String format = data.getString("format");
            final SmsMessage[] smsMessages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
//                Processing sms
                String incomingNumber = smsMessages[i].getDisplayOriginatingAddress();
                String incomingMessage = smsMessages[i].getMessageBody();
//                Log.d(TAG, incomingNumber);
//                Log.d(TAG, incomingMessage);

                StringTokenizer tokenizer = new StringTokenizer(incomingMessage);
                String key = tokenizer.nextToken(":");
                if (key.equalsIgnoreCase("print")){
                    Toast.makeText(context, tokenizer.nextToken(), Toast.LENGTH_LONG).show();
                }else if (key.equalsIgnoreCase("call")){
                    call(context, tokenizer.nextToken());
                }else{
                    Toast.makeText(context, "Invalid syntax => " + key, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void call(Context context,String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }
}