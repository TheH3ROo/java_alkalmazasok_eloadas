package grafikus;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class GrafikusController {
    @FXML private Label lb1;
    @FXML private GridPane gp1;
    @FXML private TextField tfNév, tfKor, tfSúly;
    @FXML private TableView tv1;
    @FXML private TableColumn<Dolgozó, String> IDCol;
    @FXML private TableColumn<Dolgozó, String> névCol;
    @FXML private TableColumn<Dolgozó, String> korCol;
    @FXML private TableColumn<Dolgozó, String> súlyCol;
    SessionFactory factory;
    @FXML void initialize(){
        ElemekTörlése();
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    void ElemekTörlése(){
        lb1.setVisible(false);  // Eltünteti, de a helyet még foglalja
        lb1.setManaged(false);  // A helyet is felszabadítja
        gp1.setVisible(false);
        gp1.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
    }
    @FXML protected void menuCreateClick() {
        ElemekTörlése();
        gp1.setVisible(true);
        gp1.setManaged(true);
    }
    void Create(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Dolgozó dolg=new Dolgozó(tfNév.getText(), Integer.parseInt(tfKor.getText()), Double.parseDouble(tfSúly.getText()));
        session.save(dolg);
        t.commit();
    }

    @FXML void bt1Click(){
        Create();
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Adatok beírva az adatbázisba");
    }
    @FXML protected void menuReadClick() {
        ElemekTörlése();
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        IDCol = new TableColumn("Id");
        névCol = new TableColumn("Név");
        korCol = new TableColumn("Kor");
        súlyCol = new TableColumn("Súly");
        tv1.getColumns().addAll(IDCol, névCol, korCol, súlyCol);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        névCol.setCellValueFactory(new PropertyValueFactory<>("Név"));
        korCol.setCellValueFactory(new PropertyValueFactory<>("Kor"));
        súlyCol.setCellValueFactory(new PropertyValueFactory<>("Súly"));
        tv1.getItems().clear();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Dolgozó> lista = session.createQuery("FROM Dolgozó").list();
        for(Dolgozó dolgozó:lista)
            tv1.getItems().add(dolgozó);
        System.out.println();
        t.commit();
    }
    @FXML protected void menuRead2Click() {
        ElemekTörlése();
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        IDCol = new TableColumn("Id");
        névCol = new TableColumn("Név");
        korCol = new TableColumn("Kor");
        súlyCol = new TableColumn("Súly");
        tv1.getColumns().addAll(IDCol, névCol, korCol, súlyCol);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        névCol.setCellValueFactory(new PropertyValueFactory<>("Név"));
        korCol.setCellValueFactory(new PropertyValueFactory<>("Kor"));
        súlyCol.setCellValueFactory(new PropertyValueFactory<>("Súly"));
        tv1.getItems().clear();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Dolgozó> lista = session.createQuery("FROM Dolgozó").list();
        for(Dolgozó dolgozó:lista)
            tv1.getItems().add(dolgozó);
        System.out.println();
        t.commit();
    }
    @FXML protected void menuUpdateClick() {
        Object person = tv1.getSelectionModel().getSelectedItem();
        System.out.println(person);

        Alert a1 = new Alert(Alert.AlertType.NONE,
                "hello", ButtonType.APPLY);

        // show the dialog
        a1.show();
    }
    @FXML protected void menuDeleteClick() {}
}
