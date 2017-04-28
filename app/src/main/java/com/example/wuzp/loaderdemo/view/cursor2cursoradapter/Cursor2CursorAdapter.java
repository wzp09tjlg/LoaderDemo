package com.example.wuzp.loaderdemo.view.cursor2cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wuzp.loaderdemo.R;

/**
 * Created by wuzp on 2017/4/28.
 */

public class Cursor2CursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;
    private Cursor mCursor;

    public Cursor2CursorAdapter(Context context, Cursor cursor){
        super(context,cursor);
        inflater = LayoutInflater.from(context);
        mCursor = cursor;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_loader,null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.textName.setText(mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.CACHED_NAME)));
        viewHolder.textNum.setText(mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.NUMBER)));
        viewHolder.textDate.setText(mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.DATE)));
    }

    static class ViewHolder{
        TextView textName;
        TextView textNum;
        TextView textDate;

        public ViewHolder(View view){
            textName = (TextView)view.findViewById(R.id.text_name);
            textNum = (TextView)view.findViewById(R.id.text_num);
            textDate = (TextView)view.findViewById(R.id.text_date);
        }
    }
}
