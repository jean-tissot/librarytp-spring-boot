/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecn.librarytp.items.Book;

/**
 *
 * @author ECN
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {

}
