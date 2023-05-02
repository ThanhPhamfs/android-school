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
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.util.Log;

import com.example.calllog.Adapter.ContactAdapter;
import com.example.calllog.Adapter.SMSMessageAdapter;
import com.example.calllog.Models.Contact;
import com.example.calllog.Models.SMSMessage;

import java.util.ArrayList;

public class ReadContactsActivity extends AppCompatActivity {
    private int REQ_CODE = 1;
    RecyclerView recyclerViewContacts;
    ContactAdapter adapter;
    ArrayList<Contact> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contacts);

        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        recyclerViewContacts.setHasFixedSize(true);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(ReadContactsActivity.this, RecyclerView.VERTICAL, false));
        adapter = new ContactAdapter(ReadContactsActivity.this, R.layout.contact_item, list);
        recyclerViewContacts.setAdapter(adapter);

        if (!checkPermission(Manifest.permission.READ_CONTACTS)) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQ_CODE);
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
//        Contact contact = new Contact();
//        contact.setName("Thanh");
//        contact.setAddress("489424897289", "435738958973", "45385738475");
//        contact.setEmails("489424897289", "435738958973", "45385738475");
//        contact.setNumbers("489424897289", "435738958973", "45385738475");
//        list.add(contact);
        readContact(list);
        adapter.notifyDataSetChanged();
    }

    private void readContact(ArrayList<Contact> contacts) {
        Uri contactURI = ContactsContract.Contacts.CONTENT_URI;
        Uri contactInfoURI = ContactsContract.Data.CONTENT_URI;
        Uri emailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        Uri addressUri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;

        String[] projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
        };
        String[] contactInfoProjection = {
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        Cursor cursor = getContentResolver().query(contactURI, projection,
                null, null, ContactsContract.Contacts._ID + " DESC");

        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)));
            String contactID = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

            String contactInfoWhere = ContactsContract.Data.CONTACT_ID + " =? AND " +
                    ContactsContract.Data.MIMETYPE + " =?";
            String[] contactInfoWhereArg = {
                    contactID,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
            };
            Cursor cursorNumber = getContentResolver().query(contactInfoURI, contactInfoProjection, contactInfoWhere, contactInfoWhereArg, ContactsContract.Data.DISPLAY_NAME + " DESC" );
            while (cursorNumber.moveToNext()){
                contact.setNumbers(cursorNumber.getString(cursorNumber.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            }

            String emailWhere = ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?";
            String[] emailWhereArg = {contactID};
            Cursor cursorEmail = getContentResolver().query(emailUri, null, emailWhere, emailWhereArg, null );
            while (cursorEmail.moveToNext()){
                contact.setEmails(cursorEmail.getString(cursorEmail.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)));
            }

            Cursor cursorAddress = getContentResolver().query(addressUri, null, emailWhere, emailWhereArg, null);
            while (cursorAddress.moveToNext()){
                contact.setAddress(cursorAddress.getString(cursorAddress.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS)));
            }

            contacts.add(contact);
        }
    }
}