package ru.avalon.j140_2.table;

import ru.avalon.j140_2.person.Person;

import java.sql.Timestamp;

public class DomainTable {
    private final Integer id;
    private final String webName;
    private final String domainName;
    private final String ip;
    private final Timestamp registerDate;
    private final String country;
    private final Person person;
    private final String personName;
    private final Integer idPerson;

    public DomainTable(Integer id, String webName, String domainName, String ip, Timestamp registerDate, String country, Person person) {
        this.id = id;
        this.webName = webName;
        this.domainName = domainName;
        this.ip = ip;
        this.registerDate = registerDate;
        this.country = country;
        this.person = person;
        personName = person.getFullName();
        idPerson = person.getId();
    }

    public Integer getId() {
        return id;
    }

    public String getWebName() {
        return webName;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getIp() {
        return ip;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public String getCountry() {
        return country;
    }

    public Person getPerson() {
        return person;
    }

    public String getPersonName() {
        return personName;
    }

    public Integer getIdPerson() {
        return idPerson;
    }
}
