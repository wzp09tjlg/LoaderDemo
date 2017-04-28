package com.example.wuzp.loaderdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.wuzp.loaderdemo.R;

/**
 * Created by wuzp on 2017/4/28.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        initData();
    }

    private void initView(){
        listView = $(R.id.list);
    }

    private <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    public abstract void initData();
}
