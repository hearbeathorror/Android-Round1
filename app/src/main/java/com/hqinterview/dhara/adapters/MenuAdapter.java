package com.hqinterview.dhara.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hqinterview.dhara.R;
import com.hqinterview.dhara.models.Module;

import java.util.List;

/**
 * Created by USER on 25-07-2015.
 */
public class MenuAdapter extends ArrayAdapter<String>{
    private Context mContext;
    private List<String> mModules;
    private int RESOURCE;

    public MenuAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mContext = context;
        RESOURCE = resource;
        mModules = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh = null;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(RESOURCE, parent,false);
            vh = new ViewHolder();
            vh.txtMenuItem = (TextView)view.findViewById(R.id.txtMenuItem);
            view.setTag(vh);
        }else{
            vh = (ViewHolder)view.getTag();
        }

        vh.txtMenuItem.setText(mModules.get(position));
        return view;
    }

    static class ViewHolder  {
        TextView txtMenuItem;
    }
}
