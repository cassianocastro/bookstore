CREATE TABLE IF NOT EXISTS address (
    ID         INTEGER PRIMARY KEY,
    state      TEXT    NOT NULL,
    city       TEXT    NOT NULL,
    district   TEXT    NOT NULL,
    street     TEXT    NOT NULL,
    number     INTEGER NOT NULL,
    cep        INTEGER NOT NULL,
    complement TEXT
);

CREATE TABLE IF NOT EXISTS stock (
    ID       INTEGER PRIMARY KEY,
    quantity INTEGER,
    lote     TEXT
);

CREATE TABLE IF NOT EXISTS book (
    ID         INTEGER PRIMARY KEY,
    -- FK_stockID INTEGER,
    code       INTEGER NOT NULL,
    title      TEXT NOT NULL,
    release    INTEGER NOT NULL,
    pages      INTEGER NOT NULL,
    finishing  TEXT,
--     buyValue   REAL,
--     sellValue  REAL,
    
--     FOREIGN KEY (FK_stockID) REFERENCES 
--         stock (PK_ID) 
--     ON DELETE CASCADE 
--     ON UPDATE CASCADE
);

-- CREATE INDEX index_stockID ON book(FK_stockID);

CREATE TABLE IF NOT EXISTS author (
    ID        INTEGER PRIMARY KEY,
    firstName TEXT NOT NULL,
    lastName  TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS writes (
    FK_authorID INTEGER,
    FK_bookID   INTEGER,

    FOREIGN KEY (FK_authorID) REFERENCES 
        author (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_bookID) REFERENCES 
        book (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_authorID, FK_bookID)
);

CREATE TABLE IF NOT EXISTS category (
    ID          INTEGER PRIMARY KEY,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS have (
    FK_bookID     INTEGER,
    FK_categoryID INTEGER,

    FOREIGN KEY (FK_bookID) REFERENCES 
        book (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_categoryID) REFERENCES 
        category (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_bookID, FK_categoryID)
);

CREATE TABLE IF NOT EXISTS publishingCia (
    ID   INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS publishingPhones (
    FK_publishingID INTEGER,
    phone           TEXT,

    FOREIGN KEY (FK_publishingID) REFERENCES 
        publishingCia (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_publishingID, phone)
);

CREATE TABLE IF NOT EXISTS publishingAddress (
    FK_publishingID INTEGER,
    FK_addressID    INTEGER,

    FOREIGN KEY (FK_publishingID) REFERENCES 
        publishingCia (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_addressID) REFERENCES 
        address (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_publishingID, FK_addressID)
);

CREATE TABLE IF NOT EXISTS publishedBy (
    FK_publishingID INTEGER,
    FK_bookID       INTEGER,

    FOREIGN KEY (FK_publishingID) REFERENCES 
        publishingCia (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    FOREIGN KEY (FK_bookID) REFERENCES 
        book (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_publishingID, FK_bookID)
);

CREATE TABLE IF NOT EXISTS client (
    ID           INTEGER PRIMARY KEY,
    FK_addressID INTEGER,
    firstName    TEXT NOT NULL,
    lastName     TEXT NOT NULL,
    cpf          INTEGER UNIQUE,
    birthDate    INTEGER,

    FOREIGN KEY (FK_addressID) REFERENCES 
        address (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_addressID ON client(FK_addressID);

CREATE TABLE IF NOT EXISTS clientPhones (
    FK_clientID INTEGER,
    phone       TEXT,

    FOREIGN KEY (FK_clientID) REFERENCES 
        client (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
    
    PRIMARY KEY (FK_clientID, phone)
);

CREATE TABLE IF NOT EXISTS department (
    ID   INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
    ID              INTEGER PRIMARY KEY,
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
        department (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE

    FOREIGN KEY (FK_addressID) REFERENCES 
        address (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE INDEX index_departmentID ON employee(FK_departmentID);
CREATE INDEX index_addressEmployeeID ON employee(FK_addressID);

CREATE TABLE IF NOT EXISTS employeePhones (
    FK_employeeID INTEGER,
    phone         TEXT,

    FOREIGN KEY (FK_employeeID) REFERENCES 
        employee (ID) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,

    PRIMARY KEY (FK_employeeID, phone)
);