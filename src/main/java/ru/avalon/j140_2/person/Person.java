package ru.avalon.j140_2.person;

public class Person {
    private final Integer id;
    private final String job;
    private final String fullName;
    private final String phone;
    private final String email;

    public Person(Integer id, String job, String fullName, String phone, String email) {
        this.id = id;
        this.job = job;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", job='" + job + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
