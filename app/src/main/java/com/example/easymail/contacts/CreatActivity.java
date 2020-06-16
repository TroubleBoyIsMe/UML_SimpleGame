package com.example.easymail.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.easymail.R;
import com.example.easymail.javabean.Contact;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class CreatActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_adress;
    private EditText et_explain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);
        getSupportActionBar().hide();//hide head title
        initView();
    }

    private void initView() {
        Button btn_add_person = findViewById(R.id.btn_add_person);
        ImageView iv_add_person_back=findViewById(R.id.iv_add_person_back);
        et_name = findViewById(R.id.et_name_add_person);
        et_adress = findViewById(R.id.et_adress_add_person);
        et_explain = findViewById(R.id.et_explain_add_person);
        btn_add_person.setOnClickListener(this);
        iv_add_person_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_person:
                if (TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_adress.getText())) {
                    Toast.makeText(CreatActivity.this, "添加失败，姓名邮箱不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact1 = new Contact();
                    contact1.setName(et_name.getText().toString());
                    contact1.setNumber(et_adress.getText().toString());
                    contact1.setExplan(et_explain.getText().toString());
                    contact1.save();
                    Toast.makeText(CreatActivity.this, "添加联系人成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreatActivity.this,ContactsMainActivity.class));
                    finish();
                }
                break;
            case R.id.iv_add_person_back:
                finish();
                break;
            default:
                break;
        }
    }
}
