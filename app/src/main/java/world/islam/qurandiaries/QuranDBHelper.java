package world.islam.qurandiaries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohammad on 2016-04-08.
 */
public class QuranDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "QuranReflections.db";

    public QuranDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_TABLE);
        db.execSQL(SQL_CREATE_ENTRIES_SURAHS);
        db.execSQL(SQL_SEED_SURAH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
        switch(oldVersion) {
            case 1: //original version did not have a Surah table
            {
                db.execSQL(SQL_CREATE_ENTRIES_SURAHS);
                db.execSQL(SQL_SEED_SURAH_TABLE);
            }
        }
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATETIME_TYPE = " DATETIME";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES_TABLE =
            "CREATE TABLE " + QuranJournalEntry.QuranJournalEntryInternal.TABLE_NAME + " (" +
                    QuranJournalEntry.QuranJournalEntryInternal._ID + " INTEGER PRIMARY KEY," +
                    QuranJournalEntry.QuranJournalEntryInternal.COLUMN_NAME_AYAH_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJournalEntryInternal.COLUMN_NAME_SURAH_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJournalEntryInternal.COLUMN_NAME_CONTENT+ TEXT_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJournalEntryInternal.COLUMN_NAME_CREATEDDATEUTC+ DATETIME_TYPE + COMMA_SEP +
                    QuranJournalEntry.QuranJournalEntryInternal.COLUMN_NAME_LASTUPDATEDUTC+ DATETIME_TYPE +
            " )";

    private static final String SQL_CREATE_ENTRIES_SURAHS =
            "CREATE TABLE " + Surah.SurahInternal.TABLE_NAME + " (" +
                    Surah.SurahInternal.COLUMN_NAME_SURAH_NUMBER + " INTEGER PRIMARY KEY," +
                    Surah.SurahInternal.COLUMN_NAME_AYAH_COUNT + INTEGER_TYPE + COMMA_SEP +
                    Surah.SurahInternal.COLUMN_NAME_NAME_ARABIC+ TEXT_TYPE + COMMA_SEP +
                    Surah.SurahInternal.COLUMN_NAME_NAME_ENGLISH + TEXT_TYPE +
                    " )";

    private static final String SQL_SEED_SURAH_TABLE =
            "INSERT INTO " + Surah.SurahInternal.TABLE_NAME + " (" +
                    Surah.SurahInternal.COLUMN_NAME_SURAH_NUMBER + COMMA_SEP +
                    Surah.SurahInternal.COLUMN_NAME_AYAH_COUNT + COMMA_SEP +
                    Surah.SurahInternal.COLUMN_NAME_NAME_ARABIC + COMMA_SEP +
                    Surah.SurahInternal.COLUMN_NAME_NAME_ENGLISH +
                    ") VALUES " +
                    "(1,7,\"الفاتحة\",\"Al-Fatihah\")," +
                    "(2,286,\"البقرة\",\"Al-Baqara\")," +
                    "(3,200,\"آل عمران\",\"Al-i'Imran\")," +
                    "(4,176,\"النساء\",\"An-Nisaa\")," +
                    "(5,120,\"المائدة\",\"Al-Maidah\")," +
                    "(6,165,\"الأنعام\",\"Al-An'am\")," +
                    "(7,206,\"الأعراف\",\"Al-A'raf\")," +
                    "(8,75,\"الأنفال\",\"Al-Anfal\")," +
                    "(9,129,\"التوبة\",\"At-Tauba\")," +
                    "(10,109,\"يونس\",\"Yunus\")," +
                    "(11,123,\"هود\",\"Hud\")," +
                    "(12,111,\"يوسف\",\"Yusuf\")," +
                    "(13,43,\"الرعد\",\"Ar-Ra'd\")," +
                    "(14,52,\"إبراهيم\",\"Ibrahim\")," +
                    "(15,99,\"الحجر\",\"Al-Hijr\")," +
                    "(16,128,\"النحل\",\"An-Nahl\")," +
                    "(17,111,\"الإسراء\",\"Al-Israa\")," +
                    "(18,110,\"الكهف\",\"Al-Kahf\")," +
                    "(19,98,\"مريم\",\"Maryam\")," +
                    "(20,135,\"طه\",\"Ta-ha\")," +
                    "(21,112,\"الأنبياء\",\"Al-Anbiyaa\")," +
                    "(22,78,\"الحج\",\"Al-Hajj\")," +
                    "(23,118,\"المؤمنون\",\"Al-Muminun\")," +
                    "(24,64,\"النّور\",\"An-Nur\")," +
                    "(25,77,\"الفرقان\",\"Al-Furqan\")," +
                    "(26,227,\"الشعراء\",\"Ash-Shu'araa\")," +
                    "(27,93,\"النّمل\",\"An-Naml\")," +
                    "(28,88,\"القصص\",\"Al-Qasas\")," +
                    "(29,69,\"العنكبوت\",\"Al-Ankabut\")," +
                    "(30,60,\"الرّوم\",\"Ar-Rum\")," +
                    "(31,34,\"لقمان\",\"Luqman\")," +
                    "(32,30,\"السجدة\",\"As-Sajda\")," +
                    "(33,73,\"الأحزاب\",\"Al-Ahzab\")," +
                    "(34,54,\"سبأ\",\"Saba\")," +
                    "(35,45,\"فاطر\",\"Fatir\")," +
                    "(36,83,\"يس\",\"Ya-Sin\")," +
                    "(37,182,\"الصافات\",\"As-Saffat\")," +
                    "(38,88,\"ص\",\"Sad\")," +
                    "(39,75,\"الزمر\",\"Az-Zumar\")," +
                    "(40,85,\"غافر\",\"Al-Mu'min\")," +
                    "(41,54,\"فصّلت\",\"Ha-Mim\")," +
                    "(42,53,\"الشورى\",\"Ash-Shura\")," +
                    "(43,89,\"الزخرف\",\"Az-Zukhruf\")," +
                    "(44,59,\"الدّخان\",\"Ad-Dukhan\")," +
                    "(45,37,\"الجاثية\",\"Al-Jathiya\")," +
                    "(46,35,\"الأحقاف\",\"Al-Ahqaf\")," +
                    "(47,38,\"محمد\",\"Muhammad\")," +
                    "(48,29,\"الفتح\",\"Al-Fat-h\")," +
                    "(49,18,\"الحجرات\",\"Al-Hujurat\")," +
                    "(50,45,\"ق\",\"Qaf\")," +
                    "(51,60,\"الذاريات\",\"Az-Zariyat\")," +
                    "(52,49,\"الطور\",\"At-Tur\")," +
                    "(53,62,\"النجم\",\"An-Najm\")," +
                    "(54,55,\"القمر\",\"Al-Qamar\")," +
                    "(55,78,\"الرحمن\",\"Ar-Rahman\")," +
                    "(56,96,\"الواقعة\",\"Al-Waqi'a\")," +
                    "(57,29,\"الحديد\",\"Al-Hadid\")," +
                    "(58,22,\"المجادلة\",\"Al-Mujadila\")," +
                    "(59,24,\"الحشر\",\"Al-Hashr\")," +
                    "(60,13,\"الممتحنة\",\"Al-Mumtahana\")," +
                    "(61,14,\"الصف\",\"As-Saff\")," +
                    "(62,11,\"الجمعة\",\"Al-Jumu'a\")," +
                    "(63,11,\"المنافقون\",\"Al-Munafiqun\")," +
                    "(64,18,\"التغابن\",\"At-Tagabun\")," +
                    "(65,12,\"الطلاق\",\"At-Talaq\")," +
                    "(66,12,\"التحريم\",\"At-Tahrim\")," +
                    "(67,30,\"الملك\",\"Al-Mulk\")," +
                    "(68,52,\"القلم\",\"Al-Qalam\")," +
                    "(69,52,\"الحاقة\",\"Al-Haqqa\")," +
                    "(70,44,\"المعارج\",\"Al-Ma'arij\")," +
                    "(71,28,\"نوح\",\"Nuh\")," +
                    "(72,28,\"الجن\",\"Al-Jinn\")," +
                    "(73,20,\"المزّمّل\",\"Al-Muzzammil\")," +
                    "(74,56,\"المدّثر\",\"Al-Muddathth\")," +
                    "(75,40,\"القيامة\",\"Al-Qiyamat\")," +
                    "(76,31,\"الإنسان\",\"Ad-Dahr\")," +
                    "(77,50,\"المرسلات\",\"Al-Mursalat\")," +
                    "(78,40,\"النبأ\",\"An-Nabaa\")," +
                    "(79,46,\"النازعات\",\"An-Nazi'at\")," +
                    "(80,42,\"عبس\",\"Abasa\")," +
                    "(81,29,\"التكوير\",\"At-Takwir\")," +
                    "(82,19,\"الإنفطار\",\"Al-Infitar\")," +
                    "(83,36,\"المطفّفين\",\"Al-Mutaffife\")," +
                    "(84,25,\"الإنشقاق\",\"Al-Inshiqaq\")," +
                    "(85,22,\"البروج\",\"Al-Buruj\")," +
                    "(86,17,\"الطارق\",\"At-Tariq\")," +
                    "(87,19,\"الأعلى\",\"Al-A'la\")," +
                    "(88,26,\"الغاشية\",\"Al-Gashiya\")," +
                    "(89,30,\"الفجر\",\"Al-Fajr\")," +
                    "(90,20,\"البلد\",\"Al-Balad\")," +
                    "(91,15,\"الشمس\",\"Ash-Shams\")," +
                    "(92,21,\"الليل\",\"Al-Lail\")," +
                    "(93,11,\"الضحى\",\"Adh-Dhuha\")," +
                    "(94,8,\"الشرح\",\"Al-Sharh\")," +
                    "(95,8,\"التين\",\"At-Tin\")," +
                    "(96,19,\"العلق\",\"Al-Alaq\")," +
                    "(97,5,\"القدر\",\"Al-Qadr\")," +
                    "(98,8,\"البينة\",\"Al-Baiyina\")," +
                    "(99,8,\"الزلزلة\",\"Al-Zalzalah\")," +
                    "(100,11,\"العاديات\",\"Al-Adiyat\")," +
                    "(101,11,\"القارعة\",\"Al-Qari'a\")," +
                    "(102,8,\"التكاثر\",\"At-Takathur\")," +
                    "(103,3,\"العصر\",\"Al-Asr\")," +
                    "(104,9,\"الهمزة\",\"Al-Humaza\")," +
                    "(105,5,\"الفيل\",\"Al-Fil\")," +
                    "(106,4,\"قريش\",\"Quraish\")," +
                    "(107,7,\"الماعون\",\"Al-Ma'un\")," +
                    "(108,3,\"الكوثر\",\"Al-Kauthar\")," +
                    "(109,6,\"الكافرون\",\"Al-Kafirun\")," +
                    "(110,3,\"النصر\",\"An-Nasr\")," +
                    "(111,5,\"المسد\",\"Al-Lahab\")," +
                    "(112,4,\"الإخلاص\",\"Al-Ikhlas\")," +
                    "(113,5,\"الفلق\",\"Al-Falaq\")," +
                    "(114,6,\"النّاس\",\"Al-Nas\")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + QuranJournalEntry.QuranJournalEntryInternal.TABLE_NAME;

}
