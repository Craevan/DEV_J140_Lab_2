package ru.avalon.j140_2.table;

public class PersonTable {
    private final Integer id;
    private final String job;
    private final String fullName;
    private final String phone;
    private final String email;
    private final Integer numberOfDomains;

    public PersonTable(Integer id, String job, String fullName, String phone, String email, Integer numberOfDomains) {
        this.id = id;
        this.job = job;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.numberOfDomains = numberOfDomains;
    }

    public Integer getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getNumberOfDomains() {
        return numberOfDomains;
    }
}
