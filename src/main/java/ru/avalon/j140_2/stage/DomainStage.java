package ru.avalon.j140_2.stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.avalon.j140_2.domain.Domain;
import ru.avalon.j140_2.table.DomainTable;
import ru.avalon.j140_2.table.PersonTable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DomainStage {
    private final Stage ownerStage;
    private final List<Domain> domainList;
    private final PersonTable personTableSelected;

    public DomainStage(Stage ownerStage, PersonTable personTableSelected, List<Domain> domainList) {
        this.ownerStage = ownerStage;
        this.domainList = domainList;
        this.personTableSelected = personTableSelected;
    }

    public void show() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(ownerStage);
        VBox root = new VBox();
        List<DomainTable> domainTableList = new ArrayList<>();

        for (Domain domain : domainList) {
            domainTableList.add(new DomainTable(domain.getId(),
                    domain.getWebName(),
                    domain.getDomainName(),
                    domain.getIpAddress(),
                    domain.getRegisterDate(),
                    domain.getCountry(),
                    domain.getPerson()));
        }

        ObservableList<DomainTable> observableList = FXCollections.observableArrayList(domainTableList);
        TableView<DomainTable> table = new TableView<>(observableList);

        TableColumn<DomainTable, Integer> idColumn = new TableColumn<>("Идентификатор");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().add(idColumn);

        TableColumn<DomainTable, String> webNameColumn = new TableColumn<>("Web имя");
        webNameColumn.setCellValueFactory(new PropertyValueFactory<>("webName"));
        table.getColumns().add(webNameColumn);

        TableColumn<DomainTable, String> domainNameColumn = new TableColumn<>("Доменное имя");
        domainNameColumn.setCellValueFactory(new PropertyValueFactory<>("domainName"));
        table.getColumns().add(domainNameColumn);

        TableColumn<DomainTable, String> ipColumn = new TableColumn<>("Ip");
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip"));
        table.getColumns().add(ipColumn);

        TableColumn<DomainTable, Timestamp> registerDateColumn = new TableColumn<>("Дата регистрации");
        registerDateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        table.getColumns().add(registerDateColumn);

        TableColumn<DomainTable, String> countryColumn = new TableColumn<>("Страна регистрации");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        table.getColumns().add(countryColumn);

        root.getChildren().add(table);
        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("Domain");
        stage.setScene(scene);
        stage.show();
    }
}
