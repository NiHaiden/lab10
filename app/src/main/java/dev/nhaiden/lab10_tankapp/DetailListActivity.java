package dev.nhaiden.lab10_tankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import dev.nhaiden.lab10_tankapp.database.TankRepository;
import dev.nhaiden.lab10_tankapp.model.Tankvorgang;

public class DetailListActivity extends AppCompatActivity implements TankvorgangOnClickListener {
    private List<Tankvorgang> tankvorgangList = TankRepository.getRepository(this).getAllTankvorgaenge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        TankvorgangAdapter adapter = new TankvorgangAdapter(tankvorgangList, this);
        RecyclerView rv = findViewById(R.id.rv_tankvorgaenge);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        Tankvorgang tankvorgang = tankvorgangList.get(position);

        double pricePer100 = tankvorgang.getGetankteLiter() * 100 * tankvorgang.getPreisPerLiter() /
                (tankvorgang.getNeuerKMStand() - tankvorgang.getAlterKMStand());

        new AlertDialog.Builder(this)
                .setTitle("Preis per 100 Kilometer")
                .setMessage(String.format("%.3f €", pricePer100))
                .setNeutralButton("OK", (dialog, which) -> dialog.cancel())
                .create()
                .show();
    }
}