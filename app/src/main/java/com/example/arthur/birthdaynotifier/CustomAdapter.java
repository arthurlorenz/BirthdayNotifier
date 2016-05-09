package com.example.arthur.birthdaynotifier;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by arthur on 19.04.16.
 */
public class CustomAdapter extends ArrayAdapter {
    private final Context context;
    ArrayList<MyContact> arrayList;

    public CustomAdapter(Context context, ArrayList<MyContact> arrayList) {
        super(context, -1, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        rowView.setId(arrayList.get(position).getID());

        TextView nameView = (TextView) rowView.findViewById(R.id.contactName);
        nameView.setText(arrayList.get(position).getName());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.");
        String bday = sdf.format(arrayList.get(position).getBirthdate().getTime());
        TextView bdayView = (TextView) rowView.findViewById(R.id.contactBday);
        bdayView.setText(bday);

        if (arrayList.get(position).hasBirthday()) {
            bdayView.setTextColor(Color.argb(255, 255, 64, 64));
        }

        bdayView.setTypeface(Typeface.DEFAULT_BOLD);
        return rowView;
    }
}
