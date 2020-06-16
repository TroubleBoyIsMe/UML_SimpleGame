package com.example.easymail.ui.dashboard;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.easymail.FileUtils;
import com.example.easymail.GlideEngine;
import com.example.easymail.MainActivity;
import com.example.easymail.R;
import com.example.easymail.SendAsyncTask;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;


public class SendmailFragment extends Fragment implements View.OnClickListener{

    public static final int SEND_SUCCESS_CODE = 1;
    private static final String TAG = "WriteEmailLog";
    private static final int REQUEST_CODE_CHOOSE = 7;
    private boolean isSend = false;

    private EditText etRecipient;
    private EditText etSubject;
    private EditText etContent;

    private ViewStub vsAddAttachment;
    private ImageView ivAttachment;

    private Uri mAttachmentUri;

    private boolean isAttachmentInit = false;
    private boolean isShow = false;

    private SendmailViewModel sendmailViewModel;

    private View mView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendmailViewModel =
                ViewModelProviders.of(this).get(SendmailViewModel.class);
        mView = inflater.inflate(R.layout.fragment_sendmail, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mView.findViewById(R.id.btn_send).setOnClickListener(this);

        etRecipient = mView.findViewById(R.id.et_input_recipient);
        etSubject = mView.findViewById(R.id.et_input_subject);
        etContent = mView.findViewById(R.id.et_input_content);
    }

    @Override
    public void onClick(View v) {
        final MainActivity activity = (MainActivity) requireActivity();
        switch (v.getId()) {
            case R.id.btn_send:
                if (!isSend) {
                    String recipientAddress = etRecipient.getText().toString();
                    String subject = etSubject.getText().toString();
                    String content = etContent.getText().toString();
                    if (!recipientAddress.isEmpty() && !subject.isEmpty() && !content.isEmpty()) {
                        sendEmail(recipientAddress, subject, content);
                        Toast.makeText(getContext(),
                                "邮件发送成功！", Toast.LENGTH_SHORT).show();
                        activity.navController.navigate(R.id.navigation_home);
                    } else {
                        Toast.makeText(getContext(),
                                "请填写完整各处信息", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

//    //初始化添加附件ViewStub
//    private void initViewStub(Intent data) {
//        vsAddAttachment.inflate();
//        ivAttachment = mView.findViewById(R.id.iv_add_attachment_vs);
//
//        List<Uri> mSelected = Matisse.obtainResult(data);
//        mAttachmentUri = mSelected.get(0);
//        Glide.with(this).load(mAttachmentUri).into(ivAttachment);
//    }

    //发送邮件
    private void sendEmail(String address, String subject, String content) {
        isSend = true;

        SendAsyncTask sendAsyncTask = new SendAsyncTask();

        if (mAttachmentUri != null) {  //带附件
            sendAsyncTask.execute(address, subject, content
                    , FileUtils.getRealPathFromURI(getContext(), mAttachmentUri));
        } else {              //不带附件
            sendAsyncTask.execute(address, subject, content
                    , "");
        }
    }

    //打开相册，且在相册中可以进行拍照选项
    private void openCamera() {

        //获取外部存储位置的uri
        File file = new File(getContext().getExternalFilesDir(null), "Attachment.jpg");
        Uri uri = Uri.fromFile(file);
        String imagePath = uri.getPath();

        Matisse.from(this)
                .choose(MimeType.ofAll()) // 选择 mime 的类型
                .countable(true) // 显示选择的数量
                .capture(true)  // 开启相机，和 captureStrategy 一并使用否则报错
                .captureStrategy(new CaptureStrategy(true, imagePath)) // 拍照的图片路径
                .theme(R.style.Matisse_Dracula) // 黑色背景
                .maxSelectable(1) // 图片选择的最多数量
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size)) // 列表中显示的图片大小
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码，返回图片时使用
    }

    //判断是否需要显示添加附件界面
    private void showAttachment() {
        if (isShow) {
            vsAddAttachment.setVisibility(View.GONE);
        } else {
            vsAddAttachment.setVisibility(View.VISIBLE);
        }
    }
}