package ecn.librarytp.repositories;

import ecn.librarytp.items.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer>, BorrowRepositoryCustom {

}
