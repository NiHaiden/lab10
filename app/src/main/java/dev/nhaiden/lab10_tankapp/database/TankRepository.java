package dev.nhaiden.lab10_tankapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.nhaiden.lab10_tankapp.model.Tankvorgang;

public class TankRepository {
    private static TankRepository repository;
    private SQLiteDatabase database;
    private static TankHelper helper;
    private String[] allCols = {TankHelper.COLUMN_ID, TankHelper.COLUMN_ALTERKMSTAND,
            TankHelper.COLUMN_NEUERKMSTAND, TankHelper.COLUMN_TANKVORGANG_DATUM,
            TankHelper.COLUMN_GETANKTELITER, TankHelper.COLUMN_PREISPROLITER};

    public static TankRepository getRepository(Context context) {
        if (repository == null) {
            repository = new TankRepository(context);
            repository.openDatabase();
        }
        return repository;
    }

    private TankRepository(Context context) {
        helper = new TankHelper(context);
    }

    public void openDatabase() {
        database = helper.getWritableDatabase();
    }

    public void closeDatabase() {
        helper.close();
    }

    private static Tankvorgang convertCursorTankvorgang(Cursor cursor) {
        Long id = cursor.getLong(cursor.getColumnIndex(TankHelper.COLUMN_ID));
        Integer alterKMStand = cursor.getInt(cursor.getColumnIndex(TankHelper.COLUMN_ALTERKMSTAND));
        Integer neuerKMStand = cursor.getInt(cursor.getColumnIndex(TankHelper.COLUMN_NEUERKMSTAND));
        LocalDate tankvorgang_datum = LocalDate.parse(cursor.getString(cursor.getColumnIndex(TankHelper.COLUMN_TANKVORGANG_DATUM)));
        Double getankteLiter = cursor.getDouble(cursor.getColumnIndex(TankHelper.COLUMN_GETANKTELITER));
        Double preisProLiter = cursor.getDouble(cursor.getColumnIndex(TankHelper.COLUMN_PREISPROLITER));

        return new Tankvorgang(id.intValue(), alterKMStand, neuerKMStand, tankvorgang_datum, getankteLiter, preisProLiter);
    }

    public Tankvorgang insertTankvorgang(Tankvorgang tankvorgang) {
        return insertTankvorgang(tankvorgang.getAlterKMStand(), tankvorgang.getNeuerKMStand(), tankvorgang.getTankvorgang_datum(), tankvorgang.getGetankteLiter(), tankvorgang.getPreisPerLiter());
    }

    public Tankvorgang insertTankvorgang(Integer alterKMStand, Integer neuerKMStand, LocalDate tankvorgang_datum, Double getankteLiter, Double preisProLiter) {
        ContentValues values = new ContentValues();

        values.put(TankHelper.COLUMN_ALTERKMSTAND, alterKMStand);
        values.put(TankHelper.COLUMN_NEUERKMSTAND, neuerKMStand);
        values.put(TankHelper.COLUMN_TANKVORGANG_DATUM, String.valueOf(tankvorgang_datum));
        values.put(TankHelper.COLUMN_GETANKTELITER, getankteLiter);
        values.put(TankHelper.COLUMN_PREISPROLITER, preisProLiter);

        long insertId = database.insert(TankHelper.TABLE_TANKVORGANG, null, values);

        Cursor cursor = database.query(TankHelper.TABLE_TANKVORGANG, allCols, TankHelper.COLUMN_ID + " = ?", new String[]{Long.toString(insertId)},
                null, null, null);
        cursor.moveToFirst();
        Tankvorgang tankvorgang = convertCursorTankvorgang(cursor);

        cursor.close();
        return tankvorgang;
    }

    public void deleteTankvorgang(Tankvorgang vorgang) {
        long id = vorgang.getTankvorgangId();
        database.delete(TankHelper.TABLE_TANKVORGANG, TankHelper.COLUMN_ID + " = ? ", new String[]{Long.toString(id)});
    }

    public void deleteAll() {
        database.execSQL("DELETE FROM " + TankHelper.TABLE_TANKVORGANG + ";");
    }

    public List<Tankvorgang> getAllTankvorgaenge() {
        List<Tankvorgang> alleTankvorgaenge = new ArrayList<>();
        Cursor cursor = database.query(TankHelper.TABLE_TANKVORGANG, allCols, null, null, null, null, null);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            alleTankvorgaenge.add(convertCursorTankvorgang(cursor));
        }
        cursor.close();
        return alleTankvorgaenge;
    }
}
