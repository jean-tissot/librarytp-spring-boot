package ecn.librarytp.repositories;

import ecn.librarytp.items.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ECN
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {

}
