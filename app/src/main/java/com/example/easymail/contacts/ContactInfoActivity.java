package com.example.easymail.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easymail.MainActivity;
import com.example.easymail.R;
import com.example.easymail.javabean.Contact;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoActivity extends AppCompatActivity {

    TextView tv_id;
    TextView tv_mail;
    TextView tv_explain;
    TextView tv_delete;
    Button btn_send;
    ImageView iv_back;
    int id;
    private List<Contact> contactList = new ArrayList<>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//hide head title
        setContentView(R.layout.activity_contact_info);

        tv_id = findViewById(R.id.tv_person_info_id);
        tv_mail = findViewById(R.id.tv_person_info_mailnum);
        tv_explain = findViewById(R.id.tv_person_info_explain);
        tv_delete=findViewById(R.id.iv_person_info_delete);
        btn_send = findViewById(R.id.btn_person_send);
        iv_back = findViewById(R.id.iv_person_info_back);

        final Intent intent = getIntent();
        long baseid = intent.getLongExtra("id",1);
        id= (int) baseid;
        id--;

        Log.e("add123123", String.valueOf(baseid));


        contactList = LitePal.findAll(Contact.class);
        tv_id.setText(contactList.get(id).getName());
        tv_mail.setText(contactList.get(id).getNumber());
        tv_explain.setText(contactList.get(id).getExplan());

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ContactInfoActivity.this, MainActivity.class);
                intent1.putExtra("id",1);
                startActivity(intent1);
                finish();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactInfoActivity.this,ContactsMainActivity.class));
                finish();
            }
        });

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Contact.class);
                startActivity(new Intent(ContactInfoActivity.this,ContactsMainActivity.class));
                finish();
            }
        });


        Log.e("add123", String.valueOf(baseid));
        Log.e("add123", contactList.get(id--).getName());
    }
}
