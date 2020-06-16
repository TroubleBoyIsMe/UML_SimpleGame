package com.example.easymail.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.easymail.MainActivity;
import com.example.easymail.R;
import com.example.easymail.contacts.ContactsMainActivity;
import com.example.easymail.ui.dashboard.SendmailFragment;
import com.example.easymail.ui.home.InboxFragment;
import com.example.easymail.ui.home.InboxViewModel;

public class PersonalFragment extends Fragment implements View.OnClickListener {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal, container, false);

        super.onCreate(savedInstanceState);


        LinearLayout line_list = root.findViewById(R.id.line_list);
        LinearLayout line_sended = root.findViewById(R.id.line_sended);
        LinearLayout line_inbox = root.findViewById(R.id.line_inbox);
        LinearLayout line_sent = root.findViewById(R.id.line_send);
        LinearLayout line_draft = root.findViewById(R.id.line_draft);
        line_list.setOnClickListener(this);
        line_sended.setOnClickListener(this);
        line_inbox.setOnClickListener(this);
        line_sent.setOnClickListener(this);
        line_draft.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        final MainActivity activity = (MainActivity) requireActivity();
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.line_list:
                Intent intent=new Intent(getActivity(), ContactsMainActivity.class);
                startActivity(intent);
                break;
            case R.id.line_inbox:
                activity.navController.navigate(R.id.navigation_home);
                break;
            case R.id.line_send:
                activity.navController.navigate(R.id.navigation_dashboard);
                break;
            case R.id.line_draft:
                break;
            default:
                break;
        }
    }

}