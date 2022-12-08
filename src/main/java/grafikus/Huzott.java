package grafikus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "huzott")
public class Huzott {
    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    public int Id;
    @Column(name = "huzasid")
    public int huzasid;
    @Column(name = "szam")
    public int szam;

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

    public int getSzam() {
        return szam;
    }

    public void setSzam(int szam) {
        this.szam = szam;
    }

    public Huzott() {
    }

    public Huzott(int id, int huzasid, int szam) {
        Id = id;
        this.huzasid = huzasid;
        this.szam = szam;
    }
}
