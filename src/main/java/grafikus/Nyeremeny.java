package grafikus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "nyeremeny")
public class Nyeremeny {
    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    public int Id;
    @Column(name = "huzasid")
    public int huzasid;
    @Column(name = "talalat")
    public int talalat;
    @Column(name = "darab")
    public int darab;
    @Column(name = "ertek")
    public int ertek;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getHuzasid() {
        return huzasid;
    }

    public void setHuzasid(int huzasid) {
        this.huzasid = huzasid;
    }

    public int getTalalat() {
        return talalat;
    }

    public void setTalalat(int talalat) {
        this.talalat = talalat;
    }

    public int getDarab() {
        return darab;
    }

    public void setDarab(int darab) {
        this.darab = darab;
    }

    public int getErtek() {
        return ertek;
    }

    public void setErtek(int ertek) {
        this.ertek = ertek;
    }

    public Nyeremeny() {
    }

    public Nyeremeny(int id, int huzasid, int talalat, int darab, int ertek) {
        Id = id;
        this.huzasid = huzasid;
        this.talalat = talalat;
        this.darab = darab;
        this.ertek = ertek;
    }
}
