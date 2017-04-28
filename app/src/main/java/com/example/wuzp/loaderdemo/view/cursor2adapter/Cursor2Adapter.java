package com.example.wuzp.loaderdemo.view.cursor2adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wuzp.loaderdemo.R;

/**
 * Created by wuzp on 2017/4/28.
 */

public class Cursor2Adapter extends BaseAdapter {
   private LayoutInflater inflater;
    private Cursor mCursor;

    public Cursor2Adapter(Context context, Cursor cursor){
       inflater = LayoutInflater.from(context);
        mCursor = cursor;
    }

    @Override
    public int getCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_loader,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        mCursor.moveToPosition(position);
        viewHolder.textName.setText(mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.CACHED_NAME)));
        viewHolder.textNum.setText(mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.NUMBER)));
        viewHolder.textDate.setText(mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.DATE)));
        return convertView;
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
