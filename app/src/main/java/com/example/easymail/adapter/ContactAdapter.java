package com.example.easymail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.easymail.R;
import com.example.easymail.javabean.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private int resourceId;

    public ContactAdapter(Context context, int textViewResourceId, List<Contact> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact context = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView contact_name = view.findViewById(R.id.tv_name_contact_item);
        TextView contact_adress = view.findViewById(R.id.tv_adress_contact_item);
        TextView contact_explain = view.findViewById(R.id.tv_explain_contact_item);
        contact_name.setText(context.getName());
        contact_adress.setText(context.getExplan());
        contact_explain.setText(context.getNumber());
        context.getFatherBaseObjId();
        return view;
    }
}
