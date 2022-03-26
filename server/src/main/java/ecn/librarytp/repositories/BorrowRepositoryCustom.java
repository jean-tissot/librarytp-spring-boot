/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.repositories;

import java.util.Date;

import ecn.librarytp.items.Book;
import ecn.librarytp.items.Borrow;
import ecn.librarytp.items.Person;

/**
 *
 * @author ECN
 */
public interface BorrowRepositoryCustom {

    /**
     * Create new borrow
     * @param user
     * @param book
     * @return  
     */
    public Borrow create(Person user, Book book);

    /**
     * Create new borrow
     * @param userId
     * @param bookId
     * @return  
     */
    public Borrow create(int userId, int bookId);

    /**
     * Create new borrow
     * @param user
     * @param book
     * @return  
     */
    public Borrow create(Person user, Book book, Date date);

    /**
     * Borrower returns book
     * @param borrow
     * @param date
     * @return  
     */
    public Borrow returnBook(Borrow borrow, Date date);

    /**
     * Borrower returns book
     * @param borrow
     * @return  
     */
    public Borrow returnBook(Borrow borrow);
     
    /**
     * Borrower returns book
     * @param borrowId
     * @return  
     */
    public Borrow returnBook(int borrowId);
     
}

