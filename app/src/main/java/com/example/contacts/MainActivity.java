package com.example.contacts;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;

        String DISPLAY_NAME =ContactsContract.Contacts.DISPLAY_NAME;

        List<String> contacts = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI,null,null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                contacts.add(name);
            }
        }
        listView=findViewById(R.id.contactList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(adapter);
    }
}