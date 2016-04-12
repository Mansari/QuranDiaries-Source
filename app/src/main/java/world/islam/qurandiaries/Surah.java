package world.islam.qurandiaries;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by Mohammad on 2016-04-09.
 */
public class Surah {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Surah() {}

    public int surah_Number;
    public int ayah_count;
    public String name_arabic;
    public String name_english;

    /* Inner class that defines the table contents */
    public static  class SurahInternal implements BaseColumns {
        public static final String TABLE_NAME = "surahs";
        public static final String COLUMN_NAME_SURAH_NUMBER = "sura_number";
        public static final String COLUMN_NAME_AYAH_COUNT = "ayah_number";
        public static final String COLUMN_NAME_NAME_ARABIC = "name_arabic";
        public static final String COLUMN_NAME_NAME_ENGLISH = "name_english";
    }

    //Specifically for spinners and adapters
    public int getId () {
        return surah_Number;
    }

    public String getValue () {
        return surah_Number + " - " + name_arabic + " - " + name_english;
    }

    @Override
    public String toString () {
        return getValue();
    }
}
