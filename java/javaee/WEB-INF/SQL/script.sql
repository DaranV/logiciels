
SET LINESIZE 300
    SET FEEDBACK OFF
    CLEAR SCREEN



DROP TABLE abonne CASCADE CONSTRAINTS PURGE
/
DROP TABLE bibliothecaire CASCADE CONSTRAINTS PURGE
/

PROMPT CREATION DES TABLES

CREATE TABLE abonne
(
idAbonne INTEGER PRIMARY KEY NOT NULL,
login VARCHAR2(25) NOT NULL,
mdp VARCHAR2(30) NOT NULL,
mail VARCHAR2(30) NOT NULL
)
/
DROP SEQUENCE abonne_ID;
CREATE SEQUENCE abonne_ID START WITH 1 INCREMENT BY 1;

CREATE TABLE bibliothecaire
(
idBiblio INTEGER PRIMARY KEY,
login VARCHAR2(25) NOT NULL,
mdp VARCHAR2(30) NOT NULL,
mail VARCHAR2(30) NOT NULL
)
/

DROP SEQUENCE biblio_ID;
CREATE SEQUENCE biblio_ID START WITH 1 INCREMENT BY 1;



PROMPT INSERTION DE abonne

insert into abonne (idAbonne,login, mdp, mail)
values(abonne_ID.nextval,'dabi','dabi', 'dabi@gmail.com');

insert into abonne (idAbonne,login, mdp, mail)
values(abonne_ID.nextval,'daran','daran', 'daran@hotmail.com');
insert into abonne (idAbonne,login, mdp, mail)
values(abonne_ID.nextval,'bintou','bintou', 'bintou@gmail.com');
insert into abonne (idAbonne,login, mdp, mail)
values(abonne_ID.nextval,'guillaume','guillaume', 'guillaume@gmail.com');
insert into abonne (idAbonne,login, mdp, mail)
values(abonne_ID.nextval,'macoura','macoura', 'macoura@hotmail.fr');

PROMPT INSERTION DE bibliothecaire

insert into bibliothecaire (idBiblio,login, mdp, mail)
values(biblio_ID.nextval,'examples','dabi', 'dabi@gmail.com');
insert into bibliothecaire (idBiblio,login, mdp, mail)
values(biblio_ID.nextval,'daran','daran', 'daran@gmail.com');


DROP TABLE DOCUMENT CASCADE CONSTRAINT PURGE
/
CREATE TABLE DOCUMENT 
(
    IdDoc INTEGER PRIMARY KEY,
    NomDoc VARCHAR(100) Not null,
    TypeDoc VARCHAR(100) Not null,
    DateCreation DATE Not null,
    ImageURL VARCHAR(500) 
)
/

DROP SEQUENCE doc_ID;
CREATE SEQUENCE doc_ID START WITH 1 INCREMENT BY 1;



Insert into DOCUMENT(IdDoc,NomDoc,TypeDoc,DateCreation,ImageURL) VALUES(doc_ID.nextval,'TOTO 1 ','Livre','01/11/2018','https://img2.freepng.fr/20180318/roq/kisspng-book-cover-reading-clip-art-opened-books-5aae5218422668.293078021521373720271.jpg');
Insert into DOCUMENT(IdDoc,NomDoc,TypeDoc,DateCreation,ImageURL) VALUES(doc_ID.nextval,'TOTO 2 ','CD','01/11/2018','https://img2.freepng.fr/20180318/roq/kisspng-book-cover-reading-clip-art-opened-books-5aae5218422668.293078021521373720271.jpg');
Insert into DOCUMENT(IdDoc,NomDoc,TypeDoc,DateCreation,ImageURL) VALUES(doc_ID.nextval,'TOTO 3 ','DVD','01/11/2018','https://img2.freepng.fr/20180318/roq/kisspng-book-cover-reading-clip-art-opened-books-5aae5218422668.293078021521373720271.jpg');
Insert into DOCUMENT(IdDoc,NomDoc,TypeDoc,DateCreation,ImageURL) VALUES(doc_ID.nextval,'TOTO 4 ','Livre','01/11/2018','https://img2.freepng.fr/20180318/roq/kisspng-book-cover-reading-clip-art-opened-books-5aae5218422668.293078021521373720271.jpg');
Insert into DOCUMENT(IdDoc,NomDoc,TypeDoc,DateCreation,ImageURL) VALUES(doc_ID.nextval,'TOTO 5 ','CD','01/11/2018','https://img2.freepng.fr/20180318/roq/kisspng-book-cover-reading-clip-art-opened-books-5aae5218422668.293078021521373720271.jpg');
Insert into DOCUMENT(IdDoc,NomDoc,TypeDoc,DateCreation,ImageURL) VALUES(doc_ID.nextval,'TOTO 6 ','Livre','01/11/2018','https://img2.freepng.fr/20180318/roq/kisspng-book-cover-reading-clip-art-opened-books-5aae5218422668.293078021521373720271.jpg');



DROP TABLE EMPRUNT CASCADE CONSTRAINT PURGE
/
CREATE TABLE EMPRUNT
(
    IdDoc_Emprunter INTEGER Not null UNIQUE,
    IdUser INTEGER Not null,
    PRIMARY KEY (IdDoc_Emprunter, IdUser)
)
/

Alter table EMPRUNT
ADD CONSTRAINT FK_EMPRUNT_Abonne
FOREIGN KEY (IdUser) REFERENCES abonne(idAbonne);



Alter table EMPRUNT
ADD CONSTRAINT FK_EMPRUNT_DOCUMENT
FOREIGN KEY (IdDoc_Emprunter) REFERENCES DOCUMENT(IdDoc);






PROMPT -- TABLE COMMANDE
SELECT * FROM DOCUMENT
/
PROMPT

SET FEEDBACK ON


COMMIT WORK;