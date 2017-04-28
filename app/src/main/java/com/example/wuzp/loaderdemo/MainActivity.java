package com.example.wuzp.loaderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wuzp.loaderdemo.view.cursor2adapter.Cursor2AdapterActivity;
import com.example.wuzp.loaderdemo.view.cursor2cursoradapter.Cursor2CursorAdapterActivity;
import com.example.wuzp.loaderdemo.view.cursorloader2adapter.CursorLoader2AdapterActivity;
import com.example.wuzp.loaderdemo.view.cursorloader2cursoradapter.CursorLoader2CursorAdapterActivity;


/**
 * demo 用于测试1.cursor + adapter
 * 2.cursor + cursoradapter
 * 3.cursorloder + adapter
 * 4.cursorloader + cursoradapter
 * <p>
 * 因为访问数据是获取通话记录
 * 需要读取(写入权限可以不加)通话记录的权限
 * 只是针对ListView做处理，对RecyclerView的适配暂时没加入,网上也有适配
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCursor2Adapter;
    private Button btnCursor2CursorAdapter;
    private Button btnCursorLoader2Adapter;
    private Button btnCursorLoader2CursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnCursor2Adapter = $(R.id.btn_cursor_adapter);
        btnCursor2CursorAdapter = $(R.id.btn_cursor_cursoradapter);
        btnCursorLoader2Adapter = $(R.id.btn_cursorloader_adapter);
        btnCursorLoader2CursorAdapter = $(R.id.btn_cursorloader_cursoradapter);

        btnCursor2Adapter.setOnClickListener(this);
        btnCursor2CursorAdapter.setOnClickListener(this);
        btnCursorLoader2Adapter.setOnClickListener(this);
        btnCursorLoader2CursorAdapter.setOnClickListener(this);
    }

    private <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_cursor_adapter:
                intent = new Intent(this, Cursor2AdapterActivity.class);
                break;
            case R.id.btn_cursor_cursoradapter:
                intent = new Intent(this, Cursor2CursorAdapterActivity.class);
                break;
            case R.id.btn_cursorloader_adapter:
                intent = new Intent(this, CursorLoader2AdapterActivity.class);
                break;
            case R.id.btn_cursorloader_cursoradapter:
                intent = new Intent(this, CursorLoader2CursorAdapterActivity.class);
                break;
        }
        startActivity(intent);
    }
}
