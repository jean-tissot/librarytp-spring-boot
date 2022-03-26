BEGIN;

CREATE TABLE public.book (
                book_id SERIAL NOT NULL,
                book_title VARCHAR(256) NOT NULL,
                book_authors VARCHAR(256) NOT NULL,
                CONSTRAINT pk_book PRIMARY KEY (book_id)
);

CREATE TABLE public.person (
                person_id SERIAL NOT NULL,
                person_firstname VARCHAR(128) NOT NULL,
                person_lastname VARCHAR(128) NOT NULL,
                person_birthdate DATE,
                CONSTRAINT pk_person PRIMARY KEY (person_id)
);

CREATE TABLE public.borrow (
                borrow_id SERIAL NOT NULL,
                person_id INTEGER NOT NULL,
                book_id INTEGER NOT NULL,
                borrow_date DATE NOT NULL,
                borrow_return DATE,
                CONSTRAINT pk_borrow PRIMARY KEY (borrow_id)
);

TRUNCATE public.borrow CASCADE;
TRUNCATE public.person CASCADE;
TRUNCATE public.book CASCADE;

ALTER SEQUENCE book_book_id_seq RESTART WITH 1;
ALTER SEQUENCE person_person_id_seq RESTART WITH 1;
ALTER SEQUENCE borrow_borrow_id_seq RESTART WITH 1;

ALTER TABLE public.borrow ADD CONSTRAINT book_borrow_fk
FOREIGN KEY (book_id)
REFERENCES public.book (book_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.borrow ADD CONSTRAINT person_borrow_fk
FOREIGN KEY (person_id)
REFERENCES public.person (person_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

END;