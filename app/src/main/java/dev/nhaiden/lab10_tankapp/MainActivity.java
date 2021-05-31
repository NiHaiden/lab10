package dev.nhaiden.lab10_tankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dev.nhaiden.lab10_tankapp.database.TankRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openErfassung(View view) {
        Intent intent = new Intent(this, ErfasseTankvorgang.class);
        startActivity(intent);
    }

    public void openDetailView(View view) {
        Intent intent = new Intent(this, DetailListActivity.class);
        startActivity(intent);
    }

    public void deleteAll(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Löschung der Liste / Datenbank")
                .setMessage("Wenn sie auf Ja / Yes tippen, wird die gesamte Datenbank gelöscht und alle Daten sind futsch. 💥")
                .setNegativeButton("Cancel", ((dialog, which) -> dialog.cancel()))
                .setPositiveButton("Yes", ((dialog, which) -> {
                    TankRepository.getRepository(this).deleteAll();
                    Toast.makeText(this, "Alle Daten wurden geloescht. 🛵💨", Toast.LENGTH_SHORT).show();
                }))
                .create()
                .show();
    }
}