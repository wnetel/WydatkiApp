package com.example.nwitold.wydatki;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Wydatki extends Activity {


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wydatki);




        try {
            SQLiteOpenHelper wydatkiDatabaseHelper = new WydatkiDataBaseHelper(this);
            SQLiteDatabase db = wydatkiDatabaseHelper.getReadableDatabase();

            Toast toast = Toast.makeText(this, "Udało się połączyć z bazą danych Wydatki", Toast.LENGTH_SHORT);
            toast.show();

            EditText idProvider = (EditText) findViewById(R.id.edit_id);
            String providedId = idProvider.getText().toString();

            Cursor cursor = db.query("ODBIORCA",
                    new String[] {"id_odbiorca, nazwa, nr_konta"},
                    "id_odbiorca = 2", null, null,null,null);

            if (cursor != null && cursor.moveToLast()) {
                String nazwaText = cursor.getString(1);
                String nrkontaText = cursor.getString(2);

                TextView nazwaOdbiorcy = (TextView) findViewById(R.id.nazwaodbiorcy);
                nazwaOdbiorcy.setText(nazwaText);

                TextView nrkonta = (TextView) findViewById(R.id.nrkonta);
                nrkonta.setText(nrkontaText);
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Nie udało się połączyć z bazą danych wydatki", Toast.LENGTH_SHORT );

            toast.show();
        }

    }


}
