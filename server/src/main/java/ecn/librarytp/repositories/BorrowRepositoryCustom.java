package ecn.librarytp.repositories;

import ecn.librarytp.items.Book;
import ecn.librarytp.items.Borrow;
import ecn.librarytp.items.Person;

import java.util.Date;

public interface BorrowRepositoryCustom {

    /**
     * Create a new borrow
     */
    Borrow create(Person user, Book book);

    /**
     * Create a new borrow
     */
    Borrow create(int userId, int bookId);

    /**
     * Create a new borrow
     */
    Borrow create(Person user, Book book, Date date);

    /**
     * Borrower returns the book
     */
    Borrow returnBook(Borrow borrow, Date date);

    /**
     * Borrower returns the book
     */
    Borrow returnBook(Borrow borrow);

    /**
     * Borrower returns the book
     */
    Borrow returnBook(int borrowId);

}

