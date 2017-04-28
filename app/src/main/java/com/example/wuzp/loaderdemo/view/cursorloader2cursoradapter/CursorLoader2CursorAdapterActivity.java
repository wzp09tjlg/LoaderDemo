package com.example.wuzp.loaderdemo.view.cursorloader2cursoradapter;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.wuzp.loaderdemo.view.BaseActivity;

/**
 * Created by wuzp on 2017/4/28.
 */

public class CursorLoader2CursorAdapterActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
   private final int PERMISSION_CALL_LOG = 0x01;
   private CursorLoader2CursorAdapter adapter;

    @Override
    public void initData() {
        //这里出现一个bug 就是获取通话记录的权限时  会先去拨打一下电话，然后再谈授权的框。很奇怪吧
        //没有自己做一个 弹框 处理权限的授权
        checkPermission();
       getLoaderManager().initLoader(0,null,this);
        adapter = new CursorLoader2CursorAdapter(this,null);
        listView.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return  new CursorLoader(this,
                CallLog.Calls.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALL_LOG)){
                //go on
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALL_LOG},//需要授权的全部权限
                        PERMISSION_CALL_LOG);//请求授权的requestCode
            }
        }else{
            //go on
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
                    //go on
                }else {
                    Toast.makeText(this,"finish",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
}
