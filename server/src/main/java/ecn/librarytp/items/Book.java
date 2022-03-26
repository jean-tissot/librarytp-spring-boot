package ecn.librarytp.items;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "book")
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
@NamedQuery(name = "Book.findByBookId", query = "SELECT b FROM Book b WHERE b.bookId = :bookId")
@NamedQuery(name = "Book.findByBookTitle", query = "SELECT b FROM Book b WHERE b.bookTitle = :bookTitle")
@NamedQuery(name = "Book.findByBookAuthors", query = "SELECT b FROM Book b WHERE b.bookAuthors = :bookAuthors")
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_id")
    private Integer bookId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "book_title")
    private String bookTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "book_authors")
    private String bookAuthors;
    @OneToMany(mappedBy = "bookId")
    @ToString.Exclude
    private Collection<Borrow> borrowCollection;

    public Book(Integer bookId) {
        this.bookId = bookId;
    }

    public Book(Integer bookId, String bookTitle, String bookAuthors) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthors = bookAuthors;
    }

    @Override
    public int hashCode() {
        return (bookId != null ? bookId.hashCode() : 0);
    }

    /**
     * TODO: compare fields when ids are null
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book other)) {
            return false;
        }
        return (this.bookId != null || other.bookId == null) && (this.bookId == null || this.bookId.equals(other.bookId));
    }
}
