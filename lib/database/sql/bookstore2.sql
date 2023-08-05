-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 23-Jun-2021 às 15:23
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `autor`
--

CREATE TABLE `autor` (
  `autorID` int(11) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `sobrenome` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `clienteID` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contato_cliente`
--

CREATE TABLE `contato_cliente` (
  `clienteID` int(11) DEFAULT NULL,
  `contato` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contato_editora`
--

CREATE TABLE `contato_editora` (
  `editorID` int(11) DEFAULT NULL,
  `contato` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contato_funcionario`
--

CREATE TABLE `contato_funcionario` (
  `funcionarioID` int(11) DEFAULT NULL,
  `contato` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `editora`
--

CREATE TABLE `editora` (
  `editorID` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(50) DEFAULT NULL,
  `cep` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `escreve`
--

CREATE TABLE `escreve` (
  `autorID` int(11) DEFAULT NULL,
  `bookID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

CREATE TABLE `estoque` (
  `stockID` int(11) NOT NULL,
  `bookID` int(11) DEFAULT NULL,
  `lote` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `funcionarioID` int(11) NOT NULL,
  `matricula` int(11) NOT NULL,
  `sexo` char(1) DEFAULT NULL,
  `nome` varchar(20) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `dataNasc` date NOT NULL,
  `depto` varchar(20) NOT NULL,
  `cargo` varchar(30) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro`
--

CREATE TABLE `livro` (
  `bookID` int(11) NOT NULL,
  `editorID` int(11) DEFAULT NULL,
  `titulo` varchar(50) NOT NULL,
  `genero` varchar(20) NOT NULL,
  `acabamento` varchar(30) DEFAULT NULL,
  `sinopse` varchar(500) DEFAULT NULL,
  `codigoBarras` int(11) NOT NULL,
  `lancamento` int(11) NOT NULL,
  `numberPages` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `transacao`
--

CREATE TABLE `transacao` (
  `stockID` int(11) DEFAULT NULL,
  `vendaID` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `vendaID` int(11) NOT NULL,
  `dataEvento` date NOT NULL,
  `funcionarioID` int(11) DEFAULT NULL,
  `clienteID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`autorID`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`clienteID`);

--
-- Indexes for table `contato_cliente`
--
ALTER TABLE `contato_cliente`
  ADD KEY `clienteID` (`clienteID`);

--
-- Indexes for table `contato_editora`
--
ALTER TABLE `contato_editora`
  ADD KEY `editorID` (`editorID`);

--
-- Indexes for table `contato_funcionario`
--
ALTER TABLE `contato_funcionario`
  ADD KEY `funcionarioID` (`funcionarioID`);

--
-- Indexes for table `editora`
--
ALTER TABLE `editora`
  ADD PRIMARY KEY (`editorID`);

--
-- Indexes for table `escreve`
--
ALTER TABLE `escreve`
  ADD KEY `autorID` (`autorID`),
  ADD KEY `bookID` (`bookID`);

--
-- Indexes for table `estoque`
--
ALTER TABLE `estoque`
  ADD PRIMARY KEY (`stockID`),
  ADD KEY `bookID` (`bookID`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`funcionarioID`);

--
-- Indexes for table `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`bookID`),
  ADD KEY `editorID` (`editorID`);

--
-- Indexes for table `transacao`
--
ALTER TABLE `transacao`
  ADD KEY `stockID` (`stockID`),
  ADD KEY `vendaID` (`vendaID`);

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`vendaID`),
  ADD KEY `funcionarioID` (`funcionarioID`),
  ADD KEY `clienteID` (`clienteID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `autorID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `clienteID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `editora`
--
ALTER TABLE `editora`
  MODIFY `editorID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `estoque`
--
ALTER TABLE `estoque`
  MODIFY `stockID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `funcionarioID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `livro`
--
ALTER TABLE `livro`
  MODIFY `bookID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `vendaID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `contato_cliente`
--
ALTER TABLE `contato_cliente`
  ADD CONSTRAINT `contato_cliente_ibfk_1` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`);

--
-- Limitadores para a tabela `contato_editora`
--
ALTER TABLE `contato_editora`
  ADD CONSTRAINT `contato_editora_ibfk_1` FOREIGN KEY (`editorID`) REFERENCES `editora` (`editorID`);

--
-- Limitadores para a tabela `contato_funcionario`
--
ALTER TABLE `contato_funcionario`
  ADD CONSTRAINT `contato_funcionario_ibfk_1` FOREIGN KEY (`funcionarioID`) REFERENCES `funcionario` (`funcionarioID`);

--
-- Limitadores para a tabela `escreve`
--
ALTER TABLE `escreve`
  ADD CONSTRAINT `escreve_ibfk_1` FOREIGN KEY (`autorID`) REFERENCES `autor` (`autorID`),
  ADD CONSTRAINT `escreve_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `livro` (`bookID`);

--
-- Limitadores para a tabela `estoque`
--
ALTER TABLE `estoque`
  ADD CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `livro` (`bookID`) ON DELETE CASCADE;

--
-- Limitadores para a tabela `livro`
--
ALTER TABLE `livro`
  ADD CONSTRAINT `livro_ibfk_1` FOREIGN KEY (`editorID`) REFERENCES `editora` (`editorID`) ON DELETE CASCADE;

--
-- Limitadores para a tabela `transacao`
--
ALTER TABLE `transacao`
  ADD CONSTRAINT `transacao_ibfk_1` FOREIGN KEY (`stockID`) REFERENCES `estoque` (`stockID`),
  ADD CONSTRAINT `transacao_ibfk_2` FOREIGN KEY (`vendaID`) REFERENCES `venda` (`vendaID`);

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`funcionarioID`) REFERENCES `funcionario` (`funcionarioID`),
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
