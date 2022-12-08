package grafikus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "infok")
public class Infok {
    @javax.persistence.Id
    @Column(name = "id")
    public int Id;
    @Column(name = "szam")
    public int Szám;
    @Column(name = "ev")
    public int Év;
    @Column(name = "het")
    public int Hét;
    @Column(name = "talalat")
    public int Találat;
    @Column(name = "darab")
    public int Darab;
    @Column(name = "ertek")
    public int Érték;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSzám() {
        return Szám;
    }

    public void setSzám(int szám) {
        Szám = szám;
    }

    public int getÉv() {
        return Év;
    }

    public void setÉv(int év) {
        Év = év;
    }

    public int getHét() {
        return Hét;
    }

    public void setHét(int hét) {
        Hét = hét;
    }

    public int getTalálat() {
        return Találat;
    }

    public void setTalálat(int találat) {
        Találat = találat;
    }

    public int getDarab() {
        return Darab;
    }

    public void setDarab(int darab) {
        Darab = darab;
    }

    public int getÉrték() {
        return Érték;
    }

    public void setÉrték(int érték) {
        Érték = érték;
    }

    public Infok() {
    }

    public Infok(int szám, int év, int hét, int találat, int darab, int érték) {
        Szám = szám;
        Év = év;
        Hét = hét;
        Találat = találat;
        Darab = darab;
        Érték = érték;
    }

    public Infok(int id, int szám, int év, int hét, int találat, int darab, int érték) {
        Id = id;
        Szám = szám;
        Év = év;
        Hét = hét;
        Találat = találat;
        Darab = darab;
        Érték = érték;
    }
}