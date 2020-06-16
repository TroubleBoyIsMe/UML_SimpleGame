package com.example.easymail.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.easymail.R;
import com.example.easymail.adapter.ContactAdapter;
import com.example.easymail.javabean.Contact;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ContactsMainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Contact> contactList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//hide head title
        setContentView(R.layout.activity_contacts_main);
        initContact();
        ContactAdapter adapter = new ContactAdapter(ContactsMainActivity.this, R.layout.contacts_item, contactList);

        listView = findViewById(R.id.contacts);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactList.get(position);
                long baseid = contact.getFatherBaseObjId();
                Log.e("add123", String.valueOf(baseid));
                Intent intent=new Intent(ContactsMainActivity.this, ContactInfoActivity.class);
                intent.putExtra("id",baseid);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
        initView();
    }

    private void initContact() {
        List<Contact> contactList1 = LitePal.findAll(Contact.class);
        for (Contact contact : contactList1) {
            contactList.add(contact);
            Log.e("idtest", String.valueOf(contact.getFatherBaseObjId()));
        }
    }

    private void initView() {
        ImageView iv_addperson = findViewById(R.id.iv_person_add);
        iv_addperson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_person_add:
                startActivity(new Intent(ContactsMainActivity.this, CreatActivity.class));
                break;
            default:
                break;
        }

    }
}
