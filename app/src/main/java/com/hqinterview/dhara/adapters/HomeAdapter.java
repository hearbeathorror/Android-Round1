package com.hqinterview.dhara.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hqinterview.dhara.R;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by USER on 26-07-2015.
 */
public class HomeAdapter extends ArrayAdapter<Field>{
    private Context mContext;
    private int RESOURCE;
    private List<Field> mFieldList;

    public HomeAdapter(Context context, int resource, List<Field> objects) {
        super(context, resource, objects);
        mContext = context;
        RESOURCE = resource;
        mFieldList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh = null;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(RESOURCE, parent,false);
            vh = new ViewHolder();
            vh.txtItem = (TextView)view.findViewById(R.id.txtItem);
            view.setTag(vh);
        }else{
            vh = (ViewHolder)view.getTag();
        }

        vh.txtItem.setText(mFieldList.get(position).getName());
        return view;
    }

    static class ViewHolder {
        TextView txtItem;
    }
}
