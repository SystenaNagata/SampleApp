package com.sample.image.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by systena on 2018/11/02.
 */

public class Dao {
    public static ArrayList<Entity> queryNameAndAge(SQLiteDatabase db, String tableName, String name, int age) {
        ArrayList<Entity> EntityArrayList = new ArrayList<Entity>();
        final String columns[] = new String[]{"name","age"};
        String where = "name like ? AND age like ?";
        String param[] = new String[]{name, String.valueOf(age)};

        Cursor c = db.query(tableName, columns, where, param, null, null, null, null);

        if (c != null) {
            if (c.getCount() > 0) {
                // 最初のレコード
                boolean next = c.moveToFirst();
                while (next) {
                    EntityArrayList.add(new Entity(c));
                    // 次のレコード
                    next = c.moveToNext();
                }
                Log.d("test","EntityArrayList"+EntityArrayList);

                return EntityArrayList;
            } else {
                Log.d("test", "getCount == 0.");
                return null;
            }
        } else {
            Log.d("test", "no data.");
            return null;
        }
    }
}
