package world.islam.qurandiaries;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by Mohammad on 2016-04-08.
 */
public class QuranJournalEntry {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public QuranJournalEntry() {}

    public int entry_id;
    public int surah_Number;
    public int ayah_Number;
    public String content;
    public Date createdDate;
    public Date lastupdatedDate;

    /* Inner class that defines the table contents */
    public static  class QuranJournalEntryInternal implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_SURAH_NUMBER = "sura_number";
        public static final String COLUMN_NAME_AYAH_NUMBER = "ayah_number";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_CREATEDDATEUTC = "createddat_utc";
        public static final String COLUMN_NAME_LASTUPDATEDUTC = "updateddat_utc";
    }
}
