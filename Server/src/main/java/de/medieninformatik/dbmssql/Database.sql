create database if not exists informatik;


create login 'minf'@'localhost' identified by 'prog3';
    grant INSERT, UPDATE, DELETE, SELECT on informatik.* to 'admin'@'localhost';

flush privileges;

drop table if exists informatik.book;

create table if not exists informatik.Book (
    ISBN char(13),
    Titel varchar(255) not null,
    Autor varchar(100) not null,
    Teilgebiet varchar(50),
    Verlag varchar(50),
    Erscheinungsjahr YEAR,
    Seitenzahl numeric(5, 0),
    primary key (ISBN)
);


INSERT INTO informatik.book (Titel, Autor, Teilgebiet, ISBN, Verlag, Erscheinungsjahr, Seitenzahl)
    VALUES ('Informatik für Dummies', 'Carolin Baum', 'Theoretische Informatik', 123456789, 'Dumme', 2001, 123);

INSERT INTO informatik.book (Titel, Autor, Teilgebiet, ISBN, Verlag, Erscheinungsjahr, Seitenzahl)
    VALUES ('Coole Sachen', 'Peter Baum', 'Theoretische und Praktische Informatik', 987654321, 'Coole', 2011, 321);

INSERT INTO informatik.book (Titel, Autor, Teilgebiet, ISBN, Verlag, Erscheinungsjahr, Seitenzahl)
    VALUES ('Cooles Buch', 'Hans Pool', 'Doofe Informatik', 678543219, 'Komische', 2003, 432);

