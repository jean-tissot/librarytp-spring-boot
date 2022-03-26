package ecn.librarytp.items;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "borrow")
@NamedQuery(name = "Borrow.findAll", query = "SELECT b FROM Borrow b")
@NamedQuery(name = "Borrow.findByBorrowId", query = "SELECT b FROM Borrow b WHERE b.borrowId = :borrowId")
@NamedQuery(name = "Borrow.findByBorrowDate", query = "SELECT b FROM Borrow b WHERE b.borrowDate = :borrowDate")
@NamedQuery(name = "Borrow.findByBorrowReturn", query = "SELECT b FROM Borrow b WHERE b.borrowReturn = :borrowReturn")
public class Borrow implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "borrow_id")
    private Integer borrowId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrow_date")
    @Temporal(TemporalType.DATE)
    private Date borrowDate;
    @Column(name = "borrow_return")
    @Temporal(TemporalType.DATE)
    private Date borrowReturn;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne
    private Book bookId;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private Person personId;

    public Borrow(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Borrow(Integer borrowId, Date borrowDate) {
        this.borrowId = borrowId;
        this.borrowDate = borrowDate;
    }

    @Override
    public int hashCode() {
        return (borrowId != null ? borrowId.hashCode() : 0);
    }

    /**
     * TODO: compare fields when ids are null
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Borrow other)) {
            return false;
        }
        return (this.borrowId != null || other.borrowId == null) && (this.borrowId == null || this.borrowId.equals(other.borrowId));
    }

}
