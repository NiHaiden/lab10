package dev.nhaiden.lab10_tankapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TankHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TankHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "tank.db";
    private static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_TANKVORGANG = "tankvorgang";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ALTERKMSTAND = "alter_km_stand";
    public static final String COLUMN_NEUERKMSTAND = "neuer_km_stand";
    public static final String COLUMN_TANKVORGANG_DATUM = "tankvorgang_datum";
    public static final String COLUMN_GETANKTELITER = "getankte_liter";
    public static final String COLUMN_PREISPROLITER = "preis_pro_liter";

    private static String DATABASE_CREATE_SCRIPT =
            "create table " + TABLE_TANKVORGANG + "("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_ALTERKMSTAND + " integer, "
                    + COLUMN_NEUERKMSTAND + " integer, "
                    + COLUMN_TANKVORGANG_DATUM + " date, "
                    + COLUMN_GETANKTELITER + " double, "
                    + COLUMN_PREISPROLITER + " double);";

    public TankHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from " + oldVersion + "to new version " + newVersion + ", destroying all data in the process.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TANKVORGANG);
        onCreate(db);
    }
}
