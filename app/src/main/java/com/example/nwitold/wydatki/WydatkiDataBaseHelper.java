package com.example.nwitold.wydatki;

/**
 * Created by nwitold on 2016-06-02.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class WydatkiDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "wydatkidb"; //nazwa bazy danych
    private static final int DB_VERSION = 1; //nr wersji bazy danych

    WydatkiDataBaseHelper(Context context){
        // wywołujemy konstruktor klasy bazowej SQLiteOpenHelper przekazując nazwę bazy i numer wersji
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE ODBIORCA (id_odbiorca INTEGER PRIMARY KEY, " +
                        "nazwa TEXT NOT NULL, " +
                        "nr_konta TEXT NOT NULL);" +

                "CREATE TABLE DATA (id_data INTEGER PRIMARY KEY AUTINCREMENT, " +
                        "data NUMERIC, " +
                        "rok INTEGER, " +
                        "miesiac INTEGER, " +
                        "dzien INTEGER, " +
                        "dzienTygodnia TEXT, " +
                        "weekend TEXT, " +
                        "dzienPracujacy TEXT);" +

                "CREATE TABLE SZCZEGOLY (id_szczegol INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tytul TEXT); " +

                "CREATE TABLE RODZAJ_OPERACJI (id_rodzaj_operacji INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Rodzaj TEXT); " +

                "CREATE TABLE DATA (id_data INTEGER PRIMARY KEY AUTINCREMENT, " +
                        "data NUMERIC, " +
                        "rok INTEGER, " +
                        "miesiac INTEGER, " +
                        "dzien INTEGER, " +
                        "dzienTygodnia TEXT, " +
                        "weekend TEXT, " +
                        "dzienPracujacy TEXT);" +

                "CREATE TABLE WYDATKI (id_wydatki INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "koszt REAL NOT NULL, " +
                        "id_daty INTEGER, " +
                        "id_odbiorcy INTEGER, " +
                        "id_szczegoly INTEGER, " +
                        "id_rodzaj_operacji, " +
                        "FOREIGN KEY(id_daty) REFERENCES DATA(id_data), " +
                        "FOREIGN KEY(id_odbiorcy) REFERENCES ODBIORCA(id_odbiorca), " +
                        "FOREIGN KEY(id_szczegoly) REFERENCES SZCZEGOLY(id_szczegol), " +
                        "FOREIGN KEY(id_rodzaj_operacji) REFERENCES RODZAJ_OPERACJI(id_rodzaj_operacji));");

        insertOdbiorca(db, 1, "Grzegorz Netel", "57 1140 2004 0000 3202 6391 4220");
        insertOdbiorca(db, 2, "Agnieszka Konieczna", "57 1140 2004 0000 3202 6391 4220");
        insertOdbiorca(db, 3, "JanKowalski", "57 1140 2004 0000 3202 6391 4220");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olddVersion, int newVersion) {

    }

    public static void insertOdbiorca(SQLiteDatabase db, int iid_odbiorca, String inazwa, String inr_konta) {
        ContentValues odbiorcaValues = new ContentValues();
        odbiorcaValues.put("id_odbiorca", iid_odbiorca);
        odbiorcaValues.put("nazwa", inazwa);
        odbiorcaValues.put("nr_konta", inr_konta);
        db.insert("ODBIORCA", null, odbiorcaValues);
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE ODBIORCA IF EXIST;");
    }
}
