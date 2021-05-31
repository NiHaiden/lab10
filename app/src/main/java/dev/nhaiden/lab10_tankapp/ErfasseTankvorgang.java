package dev.nhaiden.lab10_tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

import dev.nhaiden.lab10_tankapp.database.TankRepository;
import dev.nhaiden.lab10_tankapp.model.Tankvorgang;

public class ErfasseTankvorgang extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erfasse_tankvorgang);
        setTitle("Erfasse Tankvorgang");


        TankRepository repository = TankRepository.getRepository(this);

        Optional<Integer> hoechsterStand = repository.getAllTankvorgaenge().stream()
                .map(Tankvorgang::getNeuerKMStand)
                .sorted(Comparator.reverseOrder())
                .findFirst();

        hoechsterStand.ifPresent(stand -> {
            ((EditText) findViewById(R.id.alterKMStand)).setText(stand.toString());
        });
        DatePicker picker = findViewById(R.id.datePicker);
        picker.init(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), null);

    }

    public void saveTankvorgang(View view) {
        DatePicker picker = findViewById(R.id.datePicker);
        TankRepository repository = TankRepository.getRepository(this);
        try {
            Tankvorgang tankvorgang = new Tankvorgang(
                    Integer.parseInt(((EditText) findViewById(R.id.alterKMStand)).getText().toString()),
                    Integer.parseInt(((EditText) findViewById(R.id.neuerKMStand)).getText().toString()),
                    LocalDate.of(picker.getYear(), picker.getMonth(), picker.getDayOfMonth()),
                    Double.parseDouble(((EditText) findViewById(R.id.getankteLiter)).getText().toString()),
                    Double.parseDouble(((EditText) findViewById(R.id.preisProLiter)).getText().toString()));

            if (tankvorgang.getAlterKMStand() > tankvorgang.getNeuerKMStand()) {
                Toast.makeText(this, "Neuer KM Stand muss höher als alter sein!", Toast.LENGTH_LONG).show();
            } else {
                repository.insertTankvorgang(tankvorgang);
                Toast.makeText(this, "Tankvorgang wurde gespeichert. 😀", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (NumberFormatException numberFormatException) {
            Toast.makeText(this, "Incomplete or invalid input", Toast.LENGTH_LONG).show();
        }
    }

}