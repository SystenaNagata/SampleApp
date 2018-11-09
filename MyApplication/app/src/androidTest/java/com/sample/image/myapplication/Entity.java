package com.sample.image.myapplication;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by systena on 2018/11/05.
 */

public class Entity {
    private ContentValues mContentValues = new ContentValues();

    /**
     * カラム name.
     */
    private static String name = null;

    /**
     * カラム age.
     */
    private static String age = null;

    public Entity(Cursor cursor) {
        name = cursor.getString(cursor.getColumnIndex(Columns.name));
        age = cursor.getString(cursor.getColumnIndex(Columns.age));
        putAllContentValues();
    }

    private void putAllContentValues() {
        mContentValues.put(Columns.name, name);
        mContentValues.put(Columns.age, age);
    }

    public static String getName(){
        return name;
    }

    public static String getAge(){
        return age;
    }
}
