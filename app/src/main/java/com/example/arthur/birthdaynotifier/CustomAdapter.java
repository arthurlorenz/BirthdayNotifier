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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by arthur on 19.04.16.
 */
public class CustomAdapter extends ArrayAdapter implements Filterable {
    private final Context context;
    private CustomFilter friendFilter;
    private ArrayList<MyContact> arrayList;
    private ArrayList<MyContact> filteredList;
    private String bday;

    public CustomAdapter(Context context, ArrayList<MyContact> arrayList) {
        super(context, -1, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        this.filteredList = arrayList;
        getFilter();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        MyContact myContact = (MyContact) getItem(position);
        rowView.setId(myContact.getID());



        //get birthdate
        int bdayDay = myContact.getBirthdate().get(Calendar.DAY_OF_MONTH);
        int bdayMonth = myContact.getBirthdate().get(GregorianCalendar.MONTH)+1;


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
        nameView.setText(myContact.getName());

        //set birthdate
        TextView bdayView = (TextView) rowView.findViewById(R.id.contactBday);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.");
        bday = sdf.format(myContact.getBirthdate().getTime());
        bdayView.setText(bday);

        if (myContact.hasBirthday()) {
            bdayView.setTextColor(Color.argb(255, 255, 64, 64));
        }

        bdayView.setTypeface(Typeface.DEFAULT_BOLD);
        return rowView;
    }

    /**
     * Get custom filter
     * @return filter
     */
    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new CustomFilter();
        }

        return friendFilter;
    }

    /**
     * Get specific item from user list
     * @param i item index
     * @return list item
     */
    @Override
    public Object getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public int getCount() {
        return filteredList.size();
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

    /**
     * custom filter for contact list
     */
    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint != null && constraint.length() > 0) {
                ArrayList<MyContact> tempList = new ArrayList<>();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.");

                for(MyContact myContact : arrayList) {
                    bday = sdf.format(myContact.getBirthdate().getTime());
                    if(myContact.getName().toLowerCase().contains(constraint.toString().toLowerCase()) || bday.contains(constraint.toString())) {
                        tempList.add(myContact);
                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = arrayList.size();
                filterResults.values = arrayList;
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<MyContact>) results.values;
            notifyDataSetChanged();
        }
    }
}
