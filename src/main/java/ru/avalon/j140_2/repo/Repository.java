package ru.avalon.j140_2.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avalon.j140_2.auth.User;
import ru.avalon.j140_2.config.ApplicationProperties;
import ru.avalon.j140_2.domain.Domain;
import ru.avalon.j140_2.person.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {

    private static final Logger logger = LoggerFactory.getLogger(Repository.class);

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String password = resultSet.getString(3);
                User user = new User(id, userName, password);
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        }
        return list;
    }

    @Override
    public List<Person> getPersons() {
        List<Person> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM person")) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                getData(list, resultSet, id);
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        }
        return list;
    }

    @Override
    public Person getPersonById(int id) {
        List<Person> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE id=" + id)) {
            while (resultSet.next()) {
                int personId = resultSet.getInt(1);
                getData(list, resultSet, personId);
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Domain> getDomains() {
        List<Domain> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM domains")) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String webName = resultSet.getString(2);
                String domainName = resultSet.getString(3);
                String ip = resultSet.getString(4);
                Timestamp registerDate = resultSet.getTimestamp(5);
                String country = resultSet.getString(6);

                Person person = getPersonById(id);
                Domain domain = new Domain(id, webName, domainName, ip, registerDate, country, person);
                list.add(domain);
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        }
        return list;
    }

    @Override
    public List<Domain> getDomainByPerson(Person person) {
        List<Domain> list = new ArrayList<>();
        if (person != null) {
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM domains WHERE personid=" + person.getId())) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String webName = resultSet.getString(2);
                    String domainName = resultSet.getString(3);
                    String ip = resultSet.getString(4);
                    Timestamp registerDate = resultSet.getTimestamp(5);
                    String country = resultSet.getString(6);
                    Domain domain = new Domain(id, webName, domainName, ip, registerDate, country, person);
                    list.add(domain);
                }
            } catch (SQLException e) {
                logger.error("SQL Error", e);
            }
        }
        return list;
    }

    private void getData(List<Person> list, ResultSet resultSet, Integer id) throws SQLException {
        String job = resultSet.getString(2);
        String fullName = resultSet.getString(3);
        String phone = resultSet.getString(4);
        String email = resultSet.getString(5);
        Person person = new Person(id, job, fullName, phone, email);
        list.add(person);
    }

    private Connection getConnection() throws SQLException {
        String url = ApplicationProperties.getInstance().getProperty("db.url");
        String dbUser = ApplicationProperties.getInstance().getProperty("db.user");
        String dbPassword = ApplicationProperties.getInstance().getProperty("db.password");
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }
}
