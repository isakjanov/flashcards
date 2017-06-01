package projects.sandbox.flashcards.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 5/31/17.
 */

public class FlashCardsDBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public static final String DB_NAME = "flashcards.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_CARDS =
            "CREATE TABLE " + FlashCardsContract.FlashCardsEntry.TABLE_NAME + " (" +
                    FlashCardsContract.FlashCardsEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    FlashCardsContract.FlashCardsEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    FlashCardsContract.FlashCardsEntry.COLUMN_NAME_TERM + TEXT_TYPE + COMMA_SEP +
                    FlashCardsContract.FlashCardsEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE +
            " )";

    private static final String SQL_DELETE_CARDS =
            "DROP TABLE IF EXISTS " + FlashCardsContract.FlashCardsEntry.TABLE_NAME;

    public FlashCardsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CARDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CARDS);
        onCreate(db);
    }
}
