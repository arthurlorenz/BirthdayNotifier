package com.example.arthur.birthdaynotifier;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by arthur on 18.04.16.
 */
public class BirthdayDataFactory {

    public static ArrayList<MyContact> getContacts(Context context) throws ParseException {
        ArrayList<MyContact> arrayList = new ArrayList<>();
        Uri uri = ContactsContract.Data.CONTENT_URI;

        String[] projection = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Event.CONTACT_ID,
                ContactsContract.CommonDataKinds.Event.START_DATE};

        String where = ContactsContract.Data.MIMETYPE + "= ? AND "
                + ContactsContract.CommonDataKinds.Event.TYPE + "="
                + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;

        String[] selectionArgs = new String[]{ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE};
        String sortOrder = ContactsContract.CommonDataKinds.Event.DISPLAY_NAME_ALTERNATIVE+ " ASC";

        Cursor cursor = context.getContentResolver().query(uri, projection, where,
                selectionArgs, sortOrder);

        assert cursor != null;
        int nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        int idColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.CONTACT_ID);
        int bdayColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE);

        while (cursor.moveToNext()) {
            String bday = cursor.getString(bdayColumn);
            arrayList.add(new MyContact(
                    (int) cursor.getLong(idColumn),
                    cursor.getString(nameColumn),
                    Utils.setDate(bday)
            ));
        }

        cursor.close();
        return arrayList;
    }

}
