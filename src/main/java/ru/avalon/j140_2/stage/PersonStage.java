package ru.avalon.j140_2.stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import ru.avalon.j140_2.domain.Domain;
import ru.avalon.j140_2.person.Person;
import ru.avalon.j140_2.repo.Repository;
import ru.avalon.j140_2.table.PersonTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonStage {
    private Stage stage;

    Map<Integer, List<Domain>> domainOfPersonMap = new HashMap<>();

    public Map<Integer, List<Domain>> getDomainOfPersonMap() {
        return domainOfPersonMap;
    }

    public void show() {
        stage = new Stage();
        VBox root = new VBox();
        List<PersonTable> personTableList = new ArrayList<>();

        List<Person> personList = new Repository().getPersons();
        for (Person person : personList) {
            List<Domain> domainListOfPerson = new Repository().getDomainByPerson(person);
            int idPerson = person.getId();
            domainOfPersonMap.put(idPerson, domainListOfPerson);
            int numberOfDomain = new Repository().getDomainByPerson(person).size();
            personTableList.add(new PersonTable(person.getId(),
                    person.getJob(),
                    person.getFullName(),
                    person.getPhone(),
                    person.getEmail(),
                    numberOfDomain));
        }

        ObservableList<PersonTable> observableList = FXCollections.observableArrayList(personTableList);
        TableView<PersonTable> table = new TableView<>(observableList);

        TableColumn<PersonTable, Integer> idColumn = new TableColumn<>("Идентификатор");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().add(idColumn);

        TableColumn<PersonTable, String> jobColumn = new TableColumn<>("Профессия");
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("job"));
        table.getColumns().add(jobColumn);

        TableColumn<PersonTable, String> fullNameColumn = new TableColumn<>("Имя Фамилия");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        table.getColumns().add(fullNameColumn);

        TableColumn<PersonTable, String> phoneColumn = new TableColumn<>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table.getColumns().add(phoneColumn);

        TableColumn<PersonTable, String> emailColumn = new TableColumn<>("Электронная почта");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.getColumns().add(emailColumn);

        TableColumn<PersonTable, Integer> numberOfDomainsColumn = new TableColumn<>("Кол-во доменов");
        numberOfDomainsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDomains"));
        table.getColumns().add(numberOfDomainsColumn);

        table.setRowFactory(tv -> {
            TableRow<PersonTable> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty())) {
                    PersonTable personTableSelected = row.getItem();
                    Integer idPerson = personTableSelected.getId();
                    new DomainStage(stage, personTableSelected, domainOfPersonMap.get(idPerson)).show();
                }
            });
            return row;
        });

        root.getChildren().add(table);
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Persons");
        stage.setScene(scene);
        stage.show();
    }
}
