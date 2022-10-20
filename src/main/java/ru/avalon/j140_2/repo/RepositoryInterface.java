package ru.avalon.j140_2.repo;

import ru.avalon.j140_2.domain.Domain;
import ru.avalon.j140_2.person.Person;

import java.util.List;

public interface RepositoryInterface {
    List<Person> getPersons();
    Person getPersonById(int id);
    List<Domain> getDomains();
    List<Domain> getDomainByPerson(Person person);
}
