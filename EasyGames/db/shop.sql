DROP SCHEMA IF EXISTS easygames;
CREATE SCHEMA easygames;
USE easygames;


CREATE TABLE gioco
(
	id char(3) NOT NULL PRIMARY KEY,
	nome varchar(100) NOT NULL,
	descrizione text(3000) NOT NULL,
	piattaforma varchar(20) NOT NULL,
	quantita int NOT NULL,
	prezzo double NOT NULL,
	copertina mediumblob DEFAULT NULL
);

CREATE TABLE account
(
	nickname varchar(30) NOT NULL PRIMARY KEY,
	nome varchar(25) NOT NULL,
	cognome varchar(25) NOT NULL,
	data_nascita date NOT NULL,
	email varchar(50) NOT NULL UNIQUE,
	password varchar(150) NOT NULL
);

CREATE TABLE cliente
(
	account varchar(30) NOT NULL,
	PRIMARY KEY(account),
	FOREIGN KEY(account) REFERENCES account(nickname) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE amministratore
(
	account varchar(30) NOT NULL,
	PRIMARY KEY(account),
	FOREIGN KEY(account) REFERENCES account(nickname) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE ordine
(
	codice int NOT NULL AUTO_INCREMENT,
	data date NOT NULL,
	ora time NOT NULL,
	account varchar(30) NOT NULL,
	PRIMARY KEY(codice),
	FOREIGN KEY(account) REFERENCES account(nickname) ON UPDATE cascade ON DELETE cascade
)AUTO_INCREMENT=1;

CREATE TABLE giochi_acquistati
(
	gioco char(3) NOT NULL,
	ordine int NOT NULL,
	PRIMARY KEY(gioco,ordine),
	FOREIGN KEY(gioco) REFERENCES gioco(id) ON UPDATE cascade ON DELETE cascade,
	FOREIGN KEY(ordine) REFERENCES ordine(codice) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE genere
(
	genere varchar(25) NOT NULL PRIMARY KEY
);

CREATE TABLE appartieneGenere
(
	gioco char(3) NOT NULL,
	genere varchar(25) NOT NULL,
	PRIMARY KEY(gioco,genere),
	FOREIGN KEY(gioco) REFERENCES gioco(id) ON UPDATE cascade ON DELETE cascade,
	FOREIGN KEY(genere) REFERENCES genere(genere) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE carrello
(
	account varchar(30) NOT NULL PRIMARY KEY,
	FOREIGN KEY(account) REFERENCES account(nickname) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE contiene
(
	carrello varchar(30) NOT NULL,
	gioco char(3) NOT NULL,
	quantita int NOT NULL,
	PRIMARY KEY(carrello,gioco),
	FOREIGN KEY(carrello) REFERENCES carrello(account) ON UPDATE cascade ON DELETE cascade,
	FOREIGN KEY(gioco) REFERENCES gioco(id) ON UPDATE cascade ON DELETE cascade
);

INSERT INTO genere(genere) VALUES('arcade'),('avventura'),('azione'),('giocatore singolo'),('corse'),('sport'),('multiplayer'),('picchiaduro'),('simulazione'),('strategia'),('fps'),('rpg');
INSERT INTO account (nickname, nome, cognome, data_nascita, email, password) VALUES ('Admin01','Francesco','Saporito','2001-08-11','saporitofrancesco01@gmail.com',SHA2('Cavaliere1!',512));
INSERT INTO amministratore (account) VALUES ('Admin01');
INSERT INTO carrello (account) VALUES ('Admin01');
