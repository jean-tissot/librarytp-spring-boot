package ecn.librarytp.repositories;

import java.util.Date;

import ecn.librarytp.items.Person;

public interface PersonRepositoryCustom {
    /**
     * Create a new person
     */
    public Person create(String firstName, String lastName, Date birthdate);

    /**
     * Update a user
     */
    public Person update(int id, String firstName, String lastName, Date birthdate);
    
    /**
     * Delete a user
     */
    public void delete(int id);
}

