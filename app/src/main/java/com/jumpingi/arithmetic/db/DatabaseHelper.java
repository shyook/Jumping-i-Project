package com.jumpingi.arithmetic.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.jumpingi.arithmetic.db.constants.DatabaseConstants;
import com.jumpingi.arithmetic.db.utils.Converters;
import com.jumpingi.arithmetic.ui.data.QuestionData;

@Database(entities = {QuestionData.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class DatabaseHelper extends RoomDatabase {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static Context mContext;
    private static volatile DatabaseHelper instance;

    /*******************************************************************************
     * Init.
     *******************************************************************************/
    private static DatabaseHelper create(final Context context) {
        return Room.databaseBuilder(context
                , DatabaseHelper.class
                , DatabaseConstants.ARITHMETIC_HISTORY_DATABASE_NAME).build();
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    public abstract DataAccessObject getQuestionDao();

}
