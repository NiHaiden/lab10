package dev.nhaiden.lab10_tankapp.model;

import java.time.LocalDate;

public class Tankvorgang {
    private Integer tankvorgangId;
    private Integer alterKMStand;
    private Integer neuerKMStand;
    private LocalDate tankvorgang_datum;
    private Double getankteLiter;
    private Double preisPerLiter;

    public Tankvorgang(Integer alterKMStand, Integer neuerKMStand, LocalDate tankvorgang_datum, Double getankteLiter, Double preisProLiter) {
        this(-1, alterKMStand, neuerKMStand, tankvorgang_datum, getankteLiter, preisProLiter);
    }

    public Tankvorgang(int tankvorgangId,Integer alterKMStand, Integer neuerKMStand, LocalDate tankvorgang_datum, Double getankteLiter, Double preisProLiter) {
        this.tankvorgangId = tankvorgangId;
        this.alterKMStand = alterKMStand;
        this.neuerKMStand = neuerKMStand;
        this.tankvorgang_datum = tankvorgang_datum;
        this.getankteLiter = getankteLiter;
        this.preisPerLiter = preisProLiter;
    }

    public Integer getTankvorgangId() {
        return tankvorgangId;
    }

    public void setTankvorgangId(Integer tankvorgangId) {
        this.tankvorgangId = tankvorgangId;
    }

    public int getAlterKMStand() {
        return alterKMStand;
    }

    public void setAlterKMStand(int alterKMStand) {
        this.alterKMStand = alterKMStand;
    }

    public int getNeuerKMStand() {
        return neuerKMStand;
    }

    public void setNeuerKMStand(int neuerKMStand) {
        this.neuerKMStand = neuerKMStand;
    }

    public LocalDate getTankvorgang_datum() {
        return tankvorgang_datum;
    }

    public void setTankvorgang_datum(LocalDate tankvorgang_datum) {
        this.tankvorgang_datum = tankvorgang_datum;
    }

    public Double getGetankteLiter() {
        return getankteLiter;
    }

    public void setGetankteLiter(Double getankteLiter) {
        this.getankteLiter = getankteLiter;
    }

    public Double getPreisPerLiter() {
        return preisPerLiter;
    }

    public void setPreisPerLiter(Double preisPerLiter) {
        this.preisPerLiter = preisPerLiter;
    }
}
