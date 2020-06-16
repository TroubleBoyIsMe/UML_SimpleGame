package com.example.easymail.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easymail.DateUtils;
import com.example.easymail.EmailDetailActivity;
import com.example.easymail.IInBoxItemClickListener;
import com.example.easymail.IReceiveAsyncResponse;
import com.example.easymail.R;
import com.example.easymail.ReceiveAsyncTask;
import com.example.easymail.adapter.InboxAdapter;
import com.example.easymail.javabean.InboxItem;

import java.util.List;

public class InboxFragment extends Fragment implements IReceiveAsyncResponse , IInBoxItemClickListener {

    private static final int EMAIL_DETAIL_REQUEST_CODE = 5;
    private RecyclerView mRecyclerView;
    private InboxAdapter mAdapter;

    private View mView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_inbox, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initView();
    }

    private void initView() {
        mRecyclerView = mView.findViewById(R.id.rv_inbox);
    }

    private void initData() {

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);//1 表示列数
        mRecyclerView.setLayoutManager(layoutManager);

        ReceiveAsyncTask receiveAsyncTask = new ReceiveAsyncTask();
        receiveAsyncTask.setOnAsyncResponse(this);
        receiveAsyncTask.execute(getContext().getExternalFilesDir(null));
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDataReceivedSuccess(List<InboxItem> listData) {

        mAdapter = new InboxAdapter(getContext(), listData);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDataReceivedFailed() {

    }


    @Override
    public void onClickItem(InboxItem inboxItem) {
        //单击跳转邮件详情页面
        Intent intent = new Intent(getContext(), EmailDetailActivity.class);
        intent.putExtra("subject", inboxItem.getSubject());
        intent.putExtra("address", inboxItem.getName());
        intent.putExtra("date", DateUtils.dateToDateString(inboxItem.getDate())
                + " " + DateUtils.dateToTimeString(inboxItem.getDate()));
        intent.putExtra("content", inboxItem.getContent());
        intent.putExtra("file",inboxItem.getFile());
        startActivityForResult(intent, EMAIL_DETAIL_REQUEST_CODE);
    }
}