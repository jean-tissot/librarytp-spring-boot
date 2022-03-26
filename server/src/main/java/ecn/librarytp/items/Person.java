package ecn.librarytp.items;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
@NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId")
@NamedQuery(name = "Person.findByPersonFirstname", query = "SELECT p FROM Person p WHERE p.personFirstname = :personFirstname")
@NamedQuery(name = "Person.findByPersonLastname", query = "SELECT p FROM Person p WHERE p.personLastname = :personLastname")
@NamedQuery(name = "Person.findByPersonBirthdate", query = "SELECT p FROM Person p WHERE p.personBirthdate = :personBirthdate")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "person_firstname")
    private String personFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "person_lastname")
    private String personLastname;
    @Column(name = "person_birthdate")
    @Temporal(TemporalType.DATE)
    private Date personBirthdate;
    @OneToMany(mappedBy = "personId")
    @ToString.Exclude
    private Collection<Borrow> borrowCollection;

    public Person(Integer personId) {
        this.personId = personId;
    }

    public Person(Integer personId, String personFirstname, String personLastname) {
        this.personId = personId;
        this.personFirstname = personFirstname;
        this.personLastname = personLastname;
    }

    @Override
    public int hashCode() {
        return (personId != null ? personId.hashCode() : 0);
    }

    /**
     * TODO: compare fields when ids are null
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person other)) {
            return false;
        }
        return (this.personId != null || other.personId == null) && (this.personId == null || this.personId.equals(other.personId));
    }

}
