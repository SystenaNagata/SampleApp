package com.sample.image.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FindTest {
    private static final String DB_NAME_ASSET = "TestDB.db";
    private static final String DB_NAME_TABLE = "testdb";

    private TestOpenHelper mHelper;
    private SQLiteDatabase mDb;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.sample.image.myapplication", appContext.getPackageName());

        if(mHelper == null){
            mHelper = new TestOpenHelper(appContext,
                    InstrumentationRegistry.getTargetContext().getDatabasePath(DB_NAME_ASSET).getAbsolutePath());
        }

        if(mDb == null){
            mDb = mHelper.openDataBase();
        }

        // DBから目的の値を取得出来たかのテスト.
        ArrayList<Entity> entityList = Dao.queryNameAndAge(mDb, DB_NAME_TABLE, "たなか", 20);
        String name = entityList.get(0).getName();
        assertThat(name, is("たなか"));
        String age = entityList.get(0).getAge();
        assertThat(age, is("20"));

    }
}
