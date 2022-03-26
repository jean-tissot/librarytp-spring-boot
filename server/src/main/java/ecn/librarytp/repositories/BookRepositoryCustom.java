package ecn.librarytp.repositories;

import ecn.librarytp.items.Book;

public interface BookRepositoryCustom {
    /**
     * Create a new book
     */
    Book create(String title, String authors);

    /**
     * Update a book
     */
    Book update(int id, String title, String authors);

    /**
     * Delete a book
     */
    void delete(int id);
}

