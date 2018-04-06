package com.example.tranvanmanh.week5excersise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tranvanmanh on 4/4/2018.
 */

public class TaskItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TaskItem> arrayList;
    private int layout;

    public TaskItemAdapter(Context context, ArrayList<TaskItem> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class  viewHolder{
        TextView tvTask, tvDate, tvPriority;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        viewHolder holder;

        if(view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new viewHolder();
            holder.tvTask = (TextView)view.findViewById(R.id.tvtask);
            holder.tvDate = (TextView)view.findViewById(R.id.tvdate);
            holder.tvPriority = (TextView)view.findViewById(R.id.tvpriority);
            view.setTag(holder);
        }
        else {
            holder = (viewHolder) view.getTag();
        }
        holder.tvTask.setText(arrayList.get(i).getNameTask());
        holder.tvDate.setText(arrayList.get(i).getDate());
        holder.tvPriority.setText(arrayList.get(i).getPriority());
        if(arrayList.get(i).getPriority() != null) {
            if (arrayList.get(i).getPriority().equals("High")) {
                holder.tvPriority.setTextColor(view.getResources().getColor(R.color.highColor));
            }
            if (arrayList.get(i).getPriority().equals("Low")) {
                holder.tvPriority.setTextColor(view.getResources().getColor(R.color.lowColor));
            }
            if (arrayList.get(i).getPriority().equals("Normal")) {
                holder.tvPriority.setTextColor(view.getResources().getColor(R.color.normalColor));
            }
        }
        return view;
    }
}
