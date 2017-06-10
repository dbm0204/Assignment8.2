package com.example.dbm0204.assignment93;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

class MyAdapter extends BaseAdapter {

    private ArrayList<Item> myList;
    private Activity parentActivity;
    private LayoutInflater inflater;

    public MyAdapter(Activity parent, ArrayList<Item> l) {
        parentActivity = parent;
        myList=l;
        inflater = (LayoutInflater)parentActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView,
                         ViewGroup parent) {
        View view = convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.row, null);

        TextView text1 = (TextView)view.findViewById(R.id.value);
        TextView text2 = (TextView)view.findViewById(R.id.label);
        Item myObj = myList.get(position);
        text1.setText(String.valueOf(myObj.getNumber()));
        text2.setText(myObj.getName());
        return view;
    }
}