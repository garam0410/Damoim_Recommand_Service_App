package com.main.damoim.main.category;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.damoim.R;

import java.util.ArrayList;

public class myAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<Position> position;
    private LayoutInflater inflater;

    public myAdapter (Context mContext, ArrayList<Position> position) {

        this.mContext = mContext;
        this.position = position;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getGroupCount() {
        return position.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return position.get(groupPosition).parent_category.size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return position.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return position.get(groupPosition).parent_category.get(childPosition);
    }

    //position ID
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.category_parent, null);
        }

        Position position = (Position) getGroup(groupPosition);

        String positionName = position.position;

        TextView textView = (TextView) convertView.findViewById(R.id.position_tv);
        textView.setText(positionName);

        convertView.setBackgroundColor(Color.LTGRAY);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.category_child, null);
        }

        String child = (String) getChild(groupPosition, childPosition);

        TextView name = (TextView) convertView.findViewById(R.id.name_tv);

        name.setText(child);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}