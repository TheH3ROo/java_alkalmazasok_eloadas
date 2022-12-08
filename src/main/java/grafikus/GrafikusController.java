package grafikus;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javafx.scene.control.Alert.AlertType;
import java.util.List;

public class GrafikusController {
    @FXML private Label lb1, labelFelirat;
    @FXML private GridPane gp1;
    @FXML private HBox hb1, hb2;
    @FXML private ComboBox hb1_cb, delete_cb, update_cb;
    @FXML private TextField tfNév, tfKor, tfSúly;
    @FXML private TextField tfSzam, tfEv, tfHet, tfTalalat, tfDarab, tfErtek;
    @FXML private RadioButton hb1_rb, hb2_rb1, hb2_rb2, hb2_rb3;
    @FXML private TextField hb1_tf;
    @FXML private CheckBox hb1_checkbox;
    @FXML private TextField tfSzam2, tfEv2, tfHet2, tfTalalat2, tfDarab2, tfErtek2;
    @FXML private TableView tv1;
    @FXML private Button torles_bt, update_bt;
//    @FXML private TableColumn<Dolgozó, String> IDCol;
//    @FXML private TableColumn<Dolgozó, String> névCol;
//    @FXML private TableColumn<Dolgozó, String> korCol;
//    @FXML private TableColumn<Dolgozó, String> súlyCol;

    @FXML private TableColumn<Infok, String> idCol, szamCol, evCol, hetCol, talalatCol, darabCol, ertekCol;
    SessionFactory factory;
    @FXML void initialize(){
        ElemekTörlése();
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    void ElemekTörlése(){
        lb1.setVisible(false);  // Eltünteti, de a helyet még foglalja
        lb1.setManaged(false);  // A helyet is felszabadítja
        labelFelirat.setVisible(false);
        labelFelirat.setManaged(false);
        gp1.setVisible(false);
        gp1.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
        hb1.setVisible(false);
        hb1.setManaged(false);
        hb2.setVisible(false);
        hb2.setManaged(false);
        delete_cb.setVisible(false);
        delete_cb.setManaged(false);
        update_cb.setVisible(false);
        update_cb.setManaged(false);
        torles_bt.setVisible(false);
        torles_bt.setManaged(false);
        update_bt.setVisible(false);
        update_bt.setManaged(false);
    }
    @FXML protected void menuCreateClick() {
        ElemekTörlése();
        gp1.setVisible(true);
        gp1.setManaged(true);
    }
    boolean Create(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        if (tfSzam.getText().equals("") || tfEv.getText().equals("") || tfHet.getText().equals("")
                || tfTalalat.getText().equals("") || tfDarab.getText().equals("") || tfErtek.getText().equals(""))
        {
            new Alert(AlertType.WARNING, "Minden mezőt ki kell tölteni!").show();
            return false;
        }
        Infok infok=new Infok(Integer.parseInt(tfSzam.getText()), Integer.parseInt(tfEv.getText()),
                Integer.parseInt(tfHet.getText()), Integer.parseInt(tfTalalat.getText()),
                Integer.parseInt(tfDarab.getText()), Integer.parseInt(tfErtek.getText()));
        //session.save(dolg);
        session.save(infok);
        t.commit();
        return true;
    }

    void Delete(){
        ElemekTörlése();
        delete_cb.setVisible(true);
        delete_cb.setManaged(true);
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        var q = session.createQuery("DELETE FROM Infok WHERE Id = :id")
                .setParameter("id", delete_cb.getValue());
        System.out.println();
        t.commit();
    }

    @FXML void createNewData(){
        if(!Create()) return;
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Adatok beírva az adatbázisba");
    }

    @FXML void updateData(){
        if (!Update()) return;
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Adatok törölve az adatbázisból");
    }

    @FXML void deleteData(){
        Delete();
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Adatok törölve az adatbázisból");
    }


//    @FXML protected void menuReadClick_dolgozo() {
//        ElemekTörlése();
//        tv1.setVisible(true);
//        tv1.setManaged(true);
//        tv1.getColumns().removeAll(tv1.getColumns());
//        IDCol = new TableColumn("Id");
//        névCol = new TableColumn("Név");
//        korCol = new TableColumn("Kor");
//        súlyCol = new TableColumn("Súly");
//        tv1.getColumns().addAll(IDCol, névCol, korCol, súlyCol);
//        IDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
//        névCol.setCellValueFactory(new PropertyValueFactory<>("Név"));
//        korCol.setCellValueFactory(new PropertyValueFactory<>("Kor"));
//        súlyCol.setCellValueFactory(new PropertyValueFactory<>("Súly"));
//        tv1.getItems().clear();
//        Session session = factory.openSession();
//        Transaction t = session.beginTransaction();
//        List<Dolgozó> lista = session.createQuery("FROM Dolgozó").list();
//        for(Dolgozó dolgozó:lista)
//            tv1.getItems().add(dolgozó);
//        System.out.println();
//        t.commit();
//    }
    @FXML protected void menuOlvasClick() {
        ElemekTörlése();
        set_tv1();
        tv1.getItems().clear();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        //create table infok as
        //SELECT huzott.szam as szam, ev, het, talalat, darab, ertek
        //FROM huzott
        //inner join huzas on huzott.huzasid = huzas.id
        //inner join nyeremeny on nyeremeny.id = huzas.id
        //ORDER BY ev;
        List<Infok> lista = session.createQuery("FROM Infok").list();
        for(Infok info:lista)
            tv1.getItems().add(info);
        System.out.println();
        t.commit();
    }

    private void set_tv1() {
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        tableColumnFactory();
        idCol = new TableColumn("Id");
        tv1.getColumns().addAll(idCol, szamCol, evCol, hetCol, talalatCol, darabCol, ertekCol);
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        cellValueFactory();
    }

    private void set_tv1_without_id() {
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        tableColumnFactory();
        tv1.getColumns().addAll(szamCol, evCol, hetCol, talalatCol, darabCol, ertekCol);
        cellValueFactory();
    }

    private void tableColumnFactory() {
        szamCol = new TableColumn("Szám");
        evCol = new TableColumn("Év");
        hetCol = new TableColumn("Hét");
        talalatCol = new TableColumn("Találat");
        darabCol = new TableColumn("Darab");
        ertekCol = new TableColumn("Érték");
    }

    private void cellValueFactory() {
        szamCol.setCellValueFactory(new PropertyValueFactory<>("Szám"));
        evCol.setCellValueFactory(new PropertyValueFactory<>("Év"));
        hetCol.setCellValueFactory(new PropertyValueFactory<>("Hét"));
        talalatCol.setCellValueFactory(new PropertyValueFactory<>("Találat"));
        darabCol.setCellValueFactory(new PropertyValueFactory<>("Darab"));
        ertekCol.setCellValueFactory(new PropertyValueFactory<>("Érték"));
    }

    @FXML protected void menuOlvas2Click() {
        var tGroup = olvas2Init(false);
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        hb1_cb.getSelectionModel().clearSelection();
        fillHB1CB(session);
        List<Infok> lista = session.createQuery("FROM Infok").list();
        for(Infok info:lista)
            tv1.getItems().add(info);
        System.out.println();
        t.commit();
    }

    @FXML protected void cbFilterOlvas2Click() {
        var tGroup = olvas2Init(hb1_checkbox.isSelected());
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Infok> lista = session.createQuery("FROM Infok WHERE Év = :ev")
                .setParameter("ev", hb1_cb.getValue()).list();
        for(Infok info:lista)
            tv1.getItems().add(info);
        System.out.println();
        t.commit();
    }

    @FXML protected void rbFilterOlvas2Click() {
        var tGroup = olvas2Init(hb1_checkbox.isSelected());
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Infok> lista = session.createQuery("FROM Infok WHERE Év = :ev")
                .setParameter("ev", hb1_cb.getValue()).list();
        for(Infok info:lista)
            tv1.getItems().add(info);
        System.out.println();
        t.commit();
    }

    @FXML protected void checkboxFilterOlvas2Click() {
        var tGroup = olvas2Init(hb1_checkbox.isSelected());
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        hb1_cb.getSelectionModel().clearSelection();
        List<Infok> lista = session.createQuery("FROM Infok").list();
        for(Infok info:lista)
            tv1.getItems().add(info);
        System.out.println();
        t.commit();
    }

    private ToggleGroup olvas2Init(Boolean with) {
        ElemekTörlése();
        hb1.setVisible(true);
        hb1.setManaged(true);
        hb2.setVisible(true);
        hb2.setManaged(true);
        ToggleGroup toggleGroup = new ToggleGroup();
        hb2_rb1.setToggleGroup(toggleGroup);
        hb2_rb2.setToggleGroup(toggleGroup);
        hb2_rb3.setToggleGroup(toggleGroup);
        if(with) set_tv1();
        else set_tv1_without_id();
        tv1.getItems().clear();
        return toggleGroup;
    }

    @FXML protected void menuUpdateClick() {
        ElemekTörlése();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        labelFelirat.setText("Új info felvitele");
        gp1.setVisible(true);
        gp1.setManaged(true);
        update_cb.setVisible(true);
        update_cb.setManaged(true);
        update_bt.setVisible(true);
        update_bt.setManaged(true);
        List<Integer> lista = session.createQuery("SELECT Id FROM Infok").list();
        for(Integer info:lista)
            update_cb.getItems().add(info);
        System.out.println();
        t.commit();
    }

    @FXML protected void showDataToUpdate() {
        ElemekTörlése();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        labelFelirat.setText("Új info felvitele");
        gp1.setVisible(true);
        gp1.setManaged(true);
        update_cb.setVisible(true);
        update_cb.setManaged(true);
        update_bt.setVisible(true);
        update_bt.setManaged(true);
        Infok info = (Infok)session.createQuery("FROM Infok WHERE Id = :id")
                .setParameter("id", update_cb.getValue()).list().get(0);
        tfSzam.setText(String.valueOf(info.Szám));
        tfEv.setText(String.valueOf(info.Év));
        tfHet.setText(String.valueOf(info.Hét));
        tfTalalat.setText(String.valueOf(info.Találat));
        tfDarab.setText(String.valueOf(info.Darab));
        tfErtek.setText(String.valueOf(info.Érték));
        System.out.println();
        t.commit();
    }

    @FXML protected Boolean Update() {
        ElemekTörlése();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        if (tfSzam.getText().equals("") || tfEv.getText().equals("") || tfHet.getText().equals("")
                || tfTalalat.getText().equals("") || tfDarab.getText().equals("") || tfErtek.getText().equals(""))
        {
            new Alert(AlertType.WARNING, "Minden mezőt ki kell tölteni!").show();
            return false;
        }
        var q = session.createQuery("UPDATE Infok SET Szám=:szam, Év=:ev, Hét=:het, Találat=:talalat," +
                        "Darab=:darab, Érték=:ertek WHERE Id = :id")
                .setParameter("szam", tfSzam.getText())
                .setParameter("ev", tfEv.getText())
                .setParameter("het", tfHet.getText())
                .setParameter("talalat", tfTalalat.getText())
                .setParameter("darab", tfDarab.getText())
                .setParameter("ertek", tfErtek.getText())
                .setParameter("id", update_cb.getValue());
        System.out.println();
        t.commit();
        return true;
    }

    private void fillHB1CB(Session session) {
        List<Integer> lista = session.createQuery("SELECT Év FROM Infok group by Év").list();
        for(Integer info:lista)
            hb1_cb.getItems().add(info);
    }

    @FXML protected void menuDeleteClick() {
        ElemekTörlése();
        Session session = factory.openSession();
        torles_bt.setVisible(true);
        torles_bt.setManaged(true);
        delete_cb.setVisible(true);
        delete_cb.setManaged(true);
        List<Integer> lista = session.createQuery("SELECT Id FROM Infok").list();
        for(Integer info:lista)
            delete_cb.getItems().add(info);
    }
}
