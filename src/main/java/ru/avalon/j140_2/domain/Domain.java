package ru.avalon.j140_2.domain;

import ru.avalon.j140_2.person.Person;

import java.sql.Timestamp;

public class Domain {
    private final Integer id;
    private final String webName;
    private final String domainName;
    private final String ipAddress;
    private final Timestamp registerDate;
    private final String country;
    private final Person person;

    public Domain(Integer id, String webName, String domainName, String ipAddress, Timestamp registerDate, String country, Person person) {
        this.id = id;
        this.webName = webName;
        this.domainName = domainName;
        this.ipAddress = ipAddress;
        this.registerDate = registerDate;
        this.country = country;
        this.person = person;
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

    public String getIpAddress() {
        return ipAddress;
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

    @Override
    public String toString() {
        return "Domain{" +
                "id=" + id +
                ", webName='" + webName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", registerDate=" + registerDate +
                ", country='" + country + '\'' +
                ", person=" + person +
                '}';
    }
}
