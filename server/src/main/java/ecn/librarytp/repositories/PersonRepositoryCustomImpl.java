/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import ecn.librarytp.items.Person;

/**
 *
 * @author ECN
 */
@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @Autowired
    @Lazy
    private PersonRepository repository;

    @Override
    public Person update(int id, String firstName, String lastName, Date birthdate) {
        if (id > 0) {
            Person item = repository.getById(id);
            item.setPersonFirstname(firstName);
            item.setPersonLastname(lastName);
            if (birthdate != null) {
                item.setPersonBirthdate(birthdate);
            }
            repository.save(item);
            
            return item;
        }

        return null;
    }

    @Override
    public Person create(String firstName, String lastName, Date birthdate) {
        Person item = new Person();
        item.setPersonFirstname(firstName);
        item.setPersonLastname(lastName);
        if (birthdate != null) {
            item.setPersonBirthdate(birthdate);
        }
        repository.save(item);

        Optional<Person> result = repository.findById(item.getPersonId());
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        if (id > 0) {
            Person person = repository.getById(id);
            repository.delete(person);
        }
    }

}
