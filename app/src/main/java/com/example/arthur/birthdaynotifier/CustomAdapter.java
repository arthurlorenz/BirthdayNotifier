package com.example.arthur.birthdaynotifier;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        rowView.setId(arrayList.get(position).getID());



        //get birthdate
        int bdayDay = arrayList.get(position).getBirthdate().get(Calendar.DAY_OF_MONTH);
        int bdayMonth = arrayList.get(position).getBirthdate().get(GregorianCalendar.MONTH)+1;


        //set zodiac sign as image view
        ImageView zodiac = (ImageView) rowView.findViewById(R.id.zodiacSign);
        Drawable myDrawable;
        try {
            myDrawable = context.getDrawable(getZodiacSign(bdayDay,bdayMonth));
        } catch (Exception e) {
            Log.d("Exception: ", e.getMessage());
            myDrawable = context.getResources().getDrawable(getZodiacSign(bdayDay,bdayMonth));
        }
        zodiac.setImageDrawable(myDrawable);

        //set name
        TextView nameView = (TextView) rowView.findViewById(R.id.contactName);
        nameView.setText(arrayList.get(position).getName());

        //set birthdate
        TextView bdayView = (TextView) rowView.findViewById(R.id.contactBday);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.");
        String bday = sdf.format(arrayList.get(position).getBirthdate().getTime());
        bdayView.setText(bday);

        if (arrayList.get(position).hasBirthday()) {
            bdayView.setTextColor(Color.argb(255, 255, 64, 64));
        }

        bdayView.setTypeface(Typeface.DEFAULT_BOLD);
        return rowView;
    }

    private int getZodiacSign(int date, int month) {

        int value = 0;

        if (month == 1 && date >= 20 || month == 2 && date <= 18) {
            value = R.drawable.aquarius;
        }
        if (month == 2 && date >= 19 || month == 3 && date <= 20) {
            value = R.drawable.pisces;
        }
        if (month == 3 && date >= 21 || month == 4 && date <= 19) {
            value = R.drawable.aries;
        }
        if (month == 4 && date >= 20 || month == 5 && date <= 20) {
            value = R.drawable.taurus;
        }
        if (month == 5 && date >= 21 || month == 6 && date <= 21) {
            value = R.drawable.gemini;
        }
        if (month == 6 && date >= 22 || month == 7 && date <= 22) {
            value = R.drawable.cancer;
        }
        if (month == 7 && date >= 23 || month == 8 && date <= 22) {
            value = R.drawable.leo;
        }
        if (month == 8 && date >= 23 || month == 9 && date <= 22) {
            value = R.drawable.virgo;
        }
        if (month == 9 && date >= 23 || month == 10 && date <= 22) {
            value = R.drawable.libra;
        }
        if (month == 10 && date >= 23 || month == 11 && date <= 21) {
            value = R.drawable.scorpion;
        }
        if (month == 11 && date >= 22 || month == 12 && date <= 21) {
            value = R.drawable.sagittarius;
        }
        if (month == 12 && date >= 22 || month == 1 && date <= 19) {
            value = R.drawable.capricorn;
        }

        return value;
    }
}
