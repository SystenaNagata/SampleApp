package com.sample.image.myapplication;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by systena on 2018/11/02.
 */

public class TestOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    public static final int DATABASE_VERSION = 1;

    private static final String DB_NAME = "TestDB";
    private static final String DB_NAME_ASSET = "TestDB.db";

    // データーベース名
    private static final String DATABASE_NAME = "TestDB";

    private SQLiteDatabase mDatabase;
    private final Context mContext;

    private File mDatabasePath;

    public TestOpenHelper(Context context, String name) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        try {
            copyDataBaseFromAsset();
        } catch (Exception e){
e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        // assetsから持ってくる場合は使用しない。
        //db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * asset に格納したデーだベースをデフォルトのデータベースパスに作成したからのデータベースにコピーする
     */
    private void copyDataBaseFromAsset() throws IOException{
        mDatabasePath = mContext.getDatabasePath(DB_NAME);

        InputStream mInput = mContext.getAssets().open(DB_NAME_ASSET);
        OutputStream mOutput = new FileOutputStream(mDatabasePath);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        return getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        if(mDatabase != null)
            mDatabase.close();

        super.close();
    }
}

