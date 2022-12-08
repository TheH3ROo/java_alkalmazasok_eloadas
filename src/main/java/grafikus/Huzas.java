package grafikus;

import javax.persistence.*;

@Entity
@Table(name = "huzas")
public class Huzas {
    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    public int Id;
    @Column(name = "ev")
    public int ev;
    @Column(name = "het")
    public int het;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public int getHet() {
        return het;
    }

    public void setHet(int het) {
        this.het = het;
    }

    public Huzas() {
    }

    public Huzas(int id, int ev, int het) {
        Id = id;
        this.ev = ev;
        this.het = het;
    }
}
