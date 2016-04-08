package world.islam.qurandiaries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.jar.JarOutputStream;

/**
 * Created by Mohammad on 2016-04-08.
 */
public class QuranDBRepo
{
    private QuranDBHelper dbHelper;

    public QuranDBRepo(Context context) {
        dbHelper = new QuranDBHelper(context);
    }

    public int insert(QuranJournalEntry entry) {
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        DateFormat df = DateFormat.getTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("gmt"));
        String gmtTime = df.format(new Date());

        values.put(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_SURAH_NUMBER, entry.surah_Number);
        values.put(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_AYAH_NUMBER, entry.ayah_Number);
        values.put(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CONTENT, entry.content);
        values.put(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CREATEDDATEUTC, gmtTime);
        values.put(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_LASTUPDATEDUTC, gmtTime);


        // Inserting Row
        long entry_id = db.insert(QuranJournalEntry.QuranJouranlEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return (int) entry_id;
    }

    public void delete(int entry_id) {

    }

    public void update(QuranJournalEntry.QuranJouranlEntry entry) {

    }

    public ArrayList<HashMap<String, String>> getNoteList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                QuranJournalEntry.QuranJouranlEntry._ID + "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_AYAH_NUMBER + "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_SURAH_NUMBER + "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CONTENT + "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CREATEDDATEUTC + "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_LASTUPDATEDUTC +
                " FROM " +  QuranJournalEntry.QuranJouranlEntry.TABLE_NAME +
                " ORDER BY " + QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_SURAH_NUMBER + " ASC" +
                ", " + QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_AYAH_NUMBER + " ASC" +
                ", " + QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_LASTUPDATEDUTC + " ASC";


        //Student student = new Student();
        ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> entry = new HashMap<String, String>();
                entry.put("surah_aya", "Surah " + cursor.getString(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_SURAH_NUMBER)) + " Aya " + cursor.getString(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_AYAH_NUMBER)));
                entry.put("content", cursor.getString(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CONTENT)));
                noteList.add(entry);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return noteList;
    }

    public QuranJournalEntry getNoteById(int Id) throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                QuranJournalEntry.QuranJouranlEntry._ID + "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_SURAH_NUMBER+ "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_AYAH_NUMBER+ "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CONTENT+ "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CREATEDDATEUTC+ "," +
                QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_LASTUPDATEDUTC+ "," +
                " FROM " + QuranJournalEntry.QuranJouranlEntry.TABLE_NAME
                + " WHERE " +
                QuranJournalEntry.QuranJouranlEntry._ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        QuranJournalEntry note = new QuranJournalEntry() {
        };

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (cursor.moveToFirst()) {
            do {
                note.entry_id =cursor.getInt(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry._ID));
                note.ayah_Number  =cursor.getInt(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_AYAH_NUMBER));
                note.surah_Number  =cursor.getInt(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_SURAH_NUMBER));
                note.content  =cursor.getString(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CONTENT));
                note.createdDate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_CREATEDDATEUTC)));
                note.lastupdatedDate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(QuranJournalEntry.QuranJouranlEntry.COLUMN_NAME_LASTUPDATEDUTC)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return note;
    }


}
