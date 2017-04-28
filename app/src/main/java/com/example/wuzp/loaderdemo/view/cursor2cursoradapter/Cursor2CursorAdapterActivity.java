package com.example.wuzp.loaderdemo.view.cursor2cursoradapter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.wuzp.loaderdemo.view.BaseActivity;

/**
 * Created by wuzp on 2017/4/28.
 */

public class Cursor2CursorAdapterActivity extends BaseActivity  {
    private final int PERMISSION_CALL_LOG = 0x01;

   private Cursor2CursorAdapter adapter;
    private Cursor mCursor;

    @Override
    public void initData() {
        checkPermission();
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALL_LOG)){
                mCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
                adapter = new Cursor2CursorAdapter(this,mCursor);
                listView.setAdapter(adapter);
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALL_LOG},//需要授权的全部权限
                        PERMISSION_CALL_LOG);//请求授权的requestCode
            }
        }else{
            mCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            adapter = new Cursor2CursorAdapter(this,mCursor);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //被拒绝 就finish 掉整个activity
        switch (requestCode) {
            case PERMISSION_CALL_LOG://请求授权的requestCode
                //permissions // 是申请的权限
                //grantResult //是申请的结果
                //* @see #PERMISSION_GRANTED
                //* @see #PERMISSION_DENIED
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    checkPermission();
                }else {
                    Toast.makeText(this,"finish",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
}
