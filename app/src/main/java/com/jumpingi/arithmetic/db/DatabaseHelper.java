package com.jumpingi.arithmetic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.jumpingi.arithmetic.db.constants.DatabaseConstants;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static Context mContext;

    /*******************************************************************************
     * Init.
     *******************************************************************************/
    public DatabaseHelper(Context context) {
        super(context, DatabaseConstants.ARITHMETIC_HISTORY_DATABASE_NAME, null, DatabaseConstants.ARITHMETIC_HISTORY_DATABASE_VERSION);
    }

    private static class SingletonHolder {
        private static DatabaseHelper Instance = new DatabaseHelper(mContext);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return DatabaseHelper.SingletonHolder.Instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
