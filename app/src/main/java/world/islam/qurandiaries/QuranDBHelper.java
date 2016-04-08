package world.islam.qurandiaries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohammad on 2016-04-08.
 */
public class QuranDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "QuranReflections.db";

    public QuranDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATETIME_TYPE = " DATETIME";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + QuranJournalEntry.QuranJouranlEntry.TABLE_NAME + " (" +
                    QuranJournalEntry.QuranJouranlEntry._ID + " INTEGER PRIMARY KEY," +
                    QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_AYAH_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_SURAH_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CONTENT+ TEXT_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CREATEDDATEUTC+ DATETIME_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_LASTUPDATEDUTC+ DATETIME_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + QuranJournalEntry.QuranJouranlEntry.TABLE_NAME;

}
