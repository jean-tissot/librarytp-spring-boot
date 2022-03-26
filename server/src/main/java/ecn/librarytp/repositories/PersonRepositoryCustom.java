/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.repositories;

import java.util.Date;

import ecn.librarytp.items.Person;

/**
 *
 * @author ECN
 */
public interface PersonRepositoryCustom {
    /**
     * Create new person
     * @param firstName
     * @param lastName
     * @param birthdate 
     * @return  
     */
    public Person create(String firstName, String lastName, Date birthdate);

    /**
     * Update user
     * @param id
     * @param firstName
     * @param lastName
     * @param birthdate
     * @return 
     */
    public Person update(int id, String firstName, String lastName, Date birthdate);
    
    /**
     * Delete user
     * @param id 
     */
    public void delete(int id);
}

