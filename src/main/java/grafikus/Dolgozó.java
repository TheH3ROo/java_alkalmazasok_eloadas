package grafikus;
import javax.persistence.*;
@Entity
@Table(name = "szemelyek")
public class Dolgozó {
    @Id @GeneratedValue // Elsődleges kulcs, Auto_Increment
    @Column(name = "id")
    public int Id;
    @Column(name = "nev")
    public String Név;
    @Column(name = "kor")
    public int Kor;
    @Column(name = "suly")
    public double Súly;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNév() {
        return Név;
    }

    public void setNév(String név) {
        Név = név;
    }

    public int getKor() {
        return Kor;
    }

    public void setKor(int kor) {
        Kor = kor;
    }

    public double getSúly() {
        return Súly;
    }

    public void setSúly(double súly) {
        Súly = súly;
    }

    public Dolgozó() {
    }

    public Dolgozó(String név, int kor, double súly) {
        Név = név;
        Kor = kor;
        Súly = súly;
    }
}
