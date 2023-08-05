/*
 *
 *
 */

CREATE TABLE IF NOT EXISTS address (
    PK_ID      INTEGER PRIMARY KEY,
    uf         TEXT NOT NULL,
    city       TEXT NOT NULL,
    district   TEXT NOT NULL,
    street     TEXT NOT NULL,
    number     INTEGER NOT NULL,
    cep        TEXT NOT NULL,
    complement TEXT
);

CREATE TABLE IF NOT EXISTS publishingCia (
    PK_ID        INTEGER PRIMARY KEY,
    FK_addressID INTEGER,
    name         TEXT NOT NULL,
    
    FOREIGN KEY (FK_addressID) REFERENCES 
        address (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_addressID ON publishinCia(FK_addressID);

CREATE TABLE IF NOT EXISTS book (
    PK_ID           INTEGER PRIMARY KEY,
    FK_publishingID INTEGER,
    FK_categoryID   INTEGER,
    code            INTEGER NOT NULL,
    title           TEXT NOT NULL,
    finishing       TEXT,
    release         INTEGER NOT NULL,
    pages           INTEGER NOT NULL,

    FOREIGN KEY (FK_publishingID) REFERENCES 
        publishingCia (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_categoryID) REFERENCES 
        category (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_publishingID ON book(FK_publishingID);
CREATE INDEX index_categoryID ON book(FK_categoryID);

CREATE TABLE IF NOT EXISTS client (
    PK_ID        INTEGER PRIMARY KEY,
    FK_addressID INTEGER,
    firstName    TEXT NOT NULL,
    lastName     TEXT NOT NULL,
    cpf          INTEGER UNIQUE NOT NULL,

    FOREIGN KEY (FK_addressID) REFERENCES 
        address (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_addressID ON client(FK_addressID);

CREATE TABLE IF NOT EXISTS employee (
    PK_ID           INTEGER PRIMARY KEY,
    FK_departmentID INTEGER,
    FK_addressID    INTEGER,
    register        INTEGER NOT NULL,
    firstName       TEXT NOT NULL,
    lastName        TEXT NOT NULL,
    sex             TEXT,
    cpf             INTEGER UNIQUE NOT NULL,
    birthDate       INTEGER,
    office          TEXT NOT NULL,
    
    FOREIGN KEY (FK_departmentID) REFERENCES 
        department (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE

    FOREIGN KEY (FK_addressID) REFERENCES 
        address (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_departmentID ON employee(FK_departmentID);
CREATE INDEX index_addressID ON employee(FK_addressID);

CREATE TABLE IF NOT EXISTS author (
    PK_ID     INTEGER PRIMARY KEY,
    firstName TEXT,
    lastName  TEXT,
);

CREATE TABLE IF NOT EXISTS escreve (
    FK_authorID INTEGER,
    FK_bookID  INTEGER,

    FOREIGN KEY (FK_authorID) REFERENCES 
        author (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_bookID) REFERENCES 
        book (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_authorID, FK_bookID)
);

CREATE TABLE IF NOT EXISTS stock (
    PK_ID      INTEGER PRIMARY KEY,
    FK_bookID  INTEGER,
    lote       TEXT,

    FOREIGN KEY (FK_bookID) REFERENCES 
        book (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_bookID ON stock(FK_bookID);

CREATE TABLE IF NOT EXISTS sell (
    PK_ID         INTEGER PRIMARY KEY,
    FK_employeeID INTEGER,
    FK_clientID   INTEGER,
    eventDate     INTEGER NOT NULL,
    
    FOREIGN KEY (FK_employeeID) REFERENCES 
        employee (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_clientID) REFERENCES 
        client (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_employeeID ON sell(FK_employeeID);
CREATE INDEX index_clientID ON sell(FK_clientID);

CREATE TABLE IF NOT EXISTS transacao (
    FK_stockID INTEGER,
    FK_sellID  INTEGER,
    quantity   INTEGER,

    FOREIGN KEY (FK_stockID) REFERENCES 
        stock (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_sellID) REFERENCES 
        sell (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_stockID, FK_sellID)
);

CREATE TABLE IF NOT EXISTS publishingContact (
    FK_publishingID INTEGER,
    contact         TEXT,

    FOREIGN KEY (FK_publishingID) REFERENCES 
        publishingCia (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_publishingID, contact)
);

CREATE TABLE IF NOT EXISTS clientContact (
    FK_clientID INTEGER,
    contact     TEXT,

    FOREIGN KEY (FK_clientID) REFERENCES 
        client (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    
    PRIMARY KEY (FK_clientID, contact)
);

CREATE TABLE IF NOT EXISTS employeeContact (
    FK_employeeID INTEGER,
    contact       TEXT,

    FOREIGN KEY (FK_employeeID) REFERENCES 
        employee (PK_ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_employeeID, contact)
);