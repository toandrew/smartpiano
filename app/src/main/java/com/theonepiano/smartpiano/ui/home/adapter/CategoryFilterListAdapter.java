package com.theonepiano.smartpiano.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.theonepiano.smartpiano.R;

import java.util.List;

/**
 * Created by jim on 2017/6/15.
 */

public class CategoryFilterListAdapter extends BaseAdapter {
    private Context mContext;

    private List<String> mList;

    private int mCheckedItemPosition = 0;

    public CategoryFilterListAdapter(Context context, List<String> list) {
        mContext = context;

        mList = list;
    }

    public void setCheckedItemPosition(int position) {
        mCheckedItemPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_dropdown, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.mText.setText(mList.get(position));

        if (mCheckedItemPosition != -1) {
            if (mCheckedItemPosition == position) {
                viewHolder.mText.setTextColor(mContext.getResources().getColor(R.color.drop_down_selected));
                viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(null, null, mContext.getResources().getDrawable(R.drawable.drop_down_checked), null);
            } else {
                viewHolder.mText.setTextColor(mContext.getResources().getColor(R.color.drop_down_unselected));
                viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView mText;

        ViewHolder(View view) {
            mText = (TextView) view.findViewById(R.id.text);
        }
    }
}
