/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import ecn.librarytp.items.Book;

/**
 *
 * @author ECN
 */
@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @Autowired
    @Lazy
    private BookRepository repository;

    @Override
    public Book update(int id, String title, String authors) {
        if (id > 0) {
            Book item = repository.getById(id);
            item.setBookTitle(title);
            item.setBookAuthors(authors);
            repository.save(item);

            return item;
        }

        return null;
    }

    @Override
    public Book create(String title, String authors) {
        Book item = new Book();
        item.setBookTitle(title);
        item.setBookAuthors(authors);
        repository.save(item);

        Optional<Book> result = repository.findById(item.getBookId());
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        if (id > 0) {
            Book item = repository.getById(id);
            repository.delete(item);
        }
    }
}
