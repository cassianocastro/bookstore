-- 1
CREATE TABLE IF NOT EXISTS editora (
    editorID    INT NOT NULL auto_increment,
    nome        VARCHAR(50) NOT NULL,
    uf          VARCHAR(2) NOT NULL,
    cidade      VARCHAR(50),
    bairro      VARCHAR(50),
    rua         VARCHAR(50),
    numero      INT NOT NULL,
    complemento VARCHAR(50),
    cep         VARCHAR(9) NOT NULL,

    PRIMARY KEY (editorID)
) engine = InnoDB;

-- 2
CREATE TABLE IF NOT EXISTS livro (
    bookID       INT NOT NULL auto_increment,
    editorID     INT,
    titulo       VARCHAR(50) NOT NULL,
    genero       VARCHAR(20) NOT NULL,
    acabamento   VARCHAR(30),
    sinopse      VARCHAR(500),
    codigoBarras INT NOT NULL,
    lancamento   INT NOT NULL,
    numberPages  INT NOT NULL,

    FOREIGN KEY(editorID) REFERENCES 
        editor(editorID) 
    ON DELETE CASCADE, 

    PRIMARY KEY (bookID)
) engine = InnoDB;

-- 3
CREATE TABLE IF NOT EXISTS cliente (
    clienteID   INT NOT NULL auto_increment,
    nome        VARCHAR(20) NOT NULL,
    sobrenome   VARCHAR(50) NOT NULL,
    cpf         VARCHAR(14) NOT NULL,
    uf          VARCHAR(2),
    cidade      VARCHAR(50),
    bairro      VARCHAR(50),
    rua         VARCHAR(50),
    numero      INT,
    complemento VARCHAR(50),
    cep         VARCHAR(9),

    PRIMARY KEY (clienteID)
) engine = InnoDB;

-- 4
CREATE TABLE IF NOT EXISTS funcionario (
    funcionarioID INT NOT NULL auto_increment,
    matricula     INT NOT NULL,
    sexo          CHAR(1),
    nome          VARCHAR(20) NOT NULL,
    sobrenome     VARCHAR(50) NOT NULL,
    dataNasc      DATE NOT NULL,
    depto         VARCHAR(20) NOT NULL,
    cargo         VARCHAR(30) NOT NULL,
    cpf           VARCHAR(14) NOT NULL,
    uf            VARCHAR(2),
    cidade        VARCHAR(50),
    bairro        VARCHAR(50),
    rua           VARCHAR(50),
    numero        INT,
    complemento   VARCHAR(50),
    cep           VARCHAR(9),

    PRIMARY KEY (funcionarioID)
) engine = InnoDB;

-- 5
CREATE TABLE estoque (
    stockID INT NOT NULL auto_increment,
    bookID  INT,
    lote    VARCHAR(20),

    FOREIGN KEY(bookID) REFERENCES 
        book(bookID) 
    ON DELETE CASCADE,

    PRIMARY KEY (stockID)
) engine = InnoDB;

-- 6
CREATE TABLE IF NOT EXISTS autor (
    autorID   INT NOT NULL auto_increment,
    nome      VARCHAR(30),
    sobrenome VARCHAR(50),

    PRIMARY KEY (autorID)
) engine = InnoDB;

-- 7
CREATE TABLE IF NOT EXISTS venda (
    vendaID       INT NOT NULL auto_increment,
    dataEvento    DATE NOT NULL,
    funcionarioID INT,
    clienteID     INT,

    FOREIGN KEY (funcionarioID) REFERENCES 
        funcionario(funcionarioID),
    FOREIGN KEY (clienteID) REFERENCES 
        cliente(clienteID),

    PRIMARY KEY (vendaID)
) engine = InnoDB;

-- 8
CREATE TABLE IF NOT EXISTS escreve (
    autorID INT,
    bookID  INT,

    FOREIGN KEY (autorID) REFERENCES autor(autorID),
    FOREIGN KEY (bookID) REFERENCES livro(bookID)
) engine = InnoDB;

-- 9
CREATE TABLE IF NOT EXISTS transacao (
    stockID    INT,
    vendaID    INT,
    quantidade INT,

    FOREIGN KEY (stockID) REFERENCES estoque(stockID),
    FOREIGN KEY (vendaID) REFERENCES venda(vendaID)
) engine = InnoDB;

-- 10
CREATE TABLE IF NOT EXISTS contatoEditora (
    editorID INT,
    contato  VARCHAR(40),

    FOREIGN KEY (editorID) REFERENCES editora(editorID)
) engine = InnoDB;

-- 11
CREATE TABLE IF NOT EXISTS contatoCliente (
    clienteID INT,
    contato   VARCHAR(40),

    FOREIGN KEY (clienteID) REFERENCES cliente(clienteID)
) engine = InnoDB;

-- 12
CREATE TABLE IF NOT EXISTS contato (
    funcionarioID INT,
    contato       VARCHAR(40),

    FOREIGN KEY (funcionarioID) REFERENCES funcionario(funcionarioID)
) engine = InnoDB;