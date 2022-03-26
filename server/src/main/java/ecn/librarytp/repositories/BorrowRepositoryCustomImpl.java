package ecn.librarytp.repositories;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import ecn.librarytp.items.Book;
import ecn.librarytp.items.Borrow;
import ecn.librarytp.items.Person;

@Repository
public class BorrowRepositoryCustomImpl implements BorrowRepositoryCustom {

    @Autowired
    @Lazy
    private BorrowRepository repository;

    @Autowired
    @Lazy
    private BookRepository bookRepository;

    @Autowired
    @Lazy
    private PersonRepository personRepository;

    @Override
    public Borrow create(Person user, Book book) {
        Calendar aCalendar = Calendar.getInstance();
        Date date = aCalendar.getTime();
        return create(user, book, date);
    }

    @Override
    public Borrow create(int userId, int bookId) {
        Person user = personRepository.getById(userId);
        Book book = bookRepository.getById(bookId);
        return create(user, book);
    }

    @Override
    public Borrow create(Person user, Book book, Date date) {
        if ((user != null) && (book != null) && (date != null)) {
            Borrow item = new Borrow();
            item.setPersonId(user);
            item.setBookId(book);
            item.setBorrowDate(date);
            repository.saveAndFlush(item);

            Optional<Borrow> result = repository.findById(item.getBorrowId());
            if (result.isPresent()) {
                item = result.get();

                // Update book and user repositories
                if (!user.getBorrowCollection().contains(item)) {
                    user.getBorrowCollection().add(item);
                    personRepository.saveAndFlush(user);
                }
                if (!book.getBorrowCollection().contains(item)) {
                    book.getBorrowCollection().add(item);
                    bookRepository.saveAndFlush(book);
                }

                return item;
            }
        }
        return null;
    }

    @Override
    public Borrow returnBook(Borrow item, Date date) {
        if ((item != null) && (date != null)) {
            item.setBorrowReturn(date);
            repository.save(item);
            return item;
        }
        return null;
    }

    @Override
    public Borrow returnBook(Borrow item) {
        Calendar aCalendar = Calendar.getInstance();
        Date date = aCalendar.getTime();
        return returnBook(item, date);
    }

    @Override
    public Borrow returnBook(int borrowId) {
        if (borrowId > 0) {
            Borrow item = repository.getById(borrowId);
            if (item != null) {
                return returnBook(item);
            }
        }
        return null;
    }
}
