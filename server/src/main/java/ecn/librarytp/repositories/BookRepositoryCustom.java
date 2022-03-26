/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.repositories;

import ecn.librarytp.items.Book;

/**
 *
 * @author ECN
 */
public interface BookRepositoryCustom {
    /**
     * Create new book
     * @param title
     * @param authors
     * @return  
     */
    public Book create(String title, String authors);

    /**
     * Update book
     * @param id
     * @param title
     * @param authors
     * @return 
     */
    public Book update(int id, String title, String authors);
    
    /**
     * Delete book
     * @param id 
     */
    public void delete(int id);
}

