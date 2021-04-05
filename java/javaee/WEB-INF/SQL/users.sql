SET FEEDBACK OFF
SET LINESIZE 150
SET PAGESIZE 40

ALTER SESSION SET NLS_DATE_FORMAT = 'dd/mm/yyyy';

PROMPT --> DEBUT DU SCRIPT

REM ** Requête SQL de création des tables **

DROP TABLE abonne CASCADE CONSTRAINTS PURGE
/
DROP TABLE bibliothecaire CASCADE CONSTRAINTS PURGE
/

PROMPT CREATION DES TABLES

CREATE TABLE abonne
(
login VARCHAR2(25) NOT NULL,
mdp VARCHAR2(30) NOT NULL,
mail VARCHAR2(30) NOT NULL
)
/

CREATE TABLE bibliothecaire
(
login VARCHAR2(25) NOT NULL,
mdp VARCHAR2(30) NOT NULL,
mail VARCHAR2(30) NOT NULL
)
/

PROMPT INSERTION DE abonne

insert into abonne (login, mdp, mail)
values('dabi','dabi', 'dabi@gmail.com');
insert into abonne (login, mdp, mail)
values('daran','daran', 'daran@hotmail.com');
insert into abonne (login, mdp, mail)
values('bintou','bintou', 'bintou@gmail.com');
insert into abonne (login, mdp, mail)
values('guillaume','guillaume', 'guillaume@gmail.com');
insert into abonne (login, mdp, mail)
values('macoura','macoura', 'macoura@hotmail.fr');

PROMPT INSERTION DE bibliothecaire

insert into bibliothecaire (login, mdp, mail)
values('examples','dabi', 'dabi@gmail.com');
insert into bibliothecaire (login, mdp, mail)
values('daran','daran', 'daran@gmail.com');

PROMPT --> SCRIPT COMPLETEMENT TERMINE

commit;

SET FEEDBACK ON
