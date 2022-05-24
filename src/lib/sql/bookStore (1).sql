-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 15/07/2021 às 19:20
-- Versão do servidor: 10.4.18-MariaDB
-- Versão do PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bookStore`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `autor`
--

CREATE TABLE `autor` (
  `autorID` int(11) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `sobrenome` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `autor`
--

INSERT INTO `autor` (`autorID`, `nome`, `sobrenome`) VALUES
(1, 'Machado', 'de Assis'),
(4, 'Alexis', 'de ToqueVille'),
(6, 'Benjamim', 'Weeker');

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `clienteID` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`clienteID`, `nome`, `sobrenome`, `cpf`) VALUES
(1, 'Alfredo', 'da Souza', '987654321-00'),
(3, 'Alfredo', 'da Silva', '123456789-00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `contato_cliente`
--

CREATE TABLE `contato_cliente` (
  `clienteID` int(11) DEFAULT NULL,
  `contato` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `contato_editora`
--

CREATE TABLE `contato_editora` (
  `editorID` int(11) DEFAULT NULL,
  `contato` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `contato_funcionario`
--

CREATE TABLE `contato_funcionario` (
  `funcionarioID` int(11) DEFAULT NULL,
  `contato` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `editora`
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

--
-- Despejando dados para a tabela `editora`
--

INSERT INTO `editora` (`editorID`, `nome`, `uf`, `cidade`, `bairro`, `rua`, `numero`, `complemento`, `cep`) VALUES
(1, 'Editora Cultura', 'RS', 'Porto Alegre', 'Rubem Berta', 'rua 5', 123, 'Ao lado da fruteira', '12345-678'),
(3, 'Editora Rio', 'RJ', 'Rio de Janeiro', 'Catanduvas', 'Rua 9', 34, 'Complemento qualquer', '12345-555'),
(5, 'Testando', 'SP', 'Sao Paulo', 'Sao Paulo', 'Sao Geraldo', 123, 'adhasdhsi', '12309-389'),
(6, 'Mais livros +', 'RJ', 'Rio de Janeiro', 'Nao sei', 'Rua 9', 2, 'Nao tem complemento', '12345-672');

-- --------------------------------------------------------

--
-- Estrutura para tabela `escreve`
--

CREATE TABLE `escreve` (
  `autorID` int(11) DEFAULT NULL,
  `bookID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `estoque`
--

CREATE TABLE `estoque` (
  `stockID` int(11) NOT NULL,
  `bookID` int(11) DEFAULT NULL,
  `lote` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
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

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`funcionarioID`, `matricula`, `sexo`, `nome`, `sobrenome`, `dataNasc`, `depto`, `cargo`, `cpf`, `uf`, `cidade`, `bairro`, `rua`, `numero`, `complemento`, `cep`) VALUES
(1, 1, 'M', 'Alfredo', 'da Souza', '2021-06-28', 'Financeiro', 'Gerente', '987654321-00', 'RS', 'Porto Alegre', 'Sarandi', 'Rua dos Bobos', 12, '', '12350-555'),
(2, 1, 'M', 'João', 'da Silva', '2021-06-28', 'Financeiro', 'Gerente', '987654321-00', 'RS', 'Alvorada', 'Sarandi', 'Rua dos Bobos', 12, '', '12350-555');

-- --------------------------------------------------------

--
-- Estrutura para tabela `livro`
--

CREATE TABLE `livro` (
  `bookID` int(11) NOT NULL,
  `editorID` int(11) DEFAULT NULL,
  `autorID` int(11) DEFAULT NULL,
  `titulo` varchar(50) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `acabamento` varchar(30) DEFAULT NULL,
  `codigoBarras` int(11) NOT NULL,
  `lancamento` int(11) NOT NULL,
  `numberPages` int(11) NOT NULL,
  `valorCompra` varchar(20) DEFAULT NULL,
  `valorVenda` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `livro`
--

INSERT INTO `livro` (`bookID`, `editorID`, `autorID`, `titulo`, `genero`, `acabamento`, `codigoBarras`, `lancamento`, `numberPages`, `valorCompra`, `valorVenda`) VALUES
(1, 5, 1, 'Hello World', 'Ação', 'Capa Dura', 123, 2021, 230, '10.00', '30.00'),
(2, 6, 1, 'A bela e a Fera', 'Romance', 'Capa Mole', 1235, 2012, 235, '10.00', '30.00'),
(4, 1, 1, 'Marley e Eu', 'Aventura', 'capa', 456, 2000, 234, '19', '30'),
(8, 1, 6, 'Teste', 'Aventura', 'Brochura', 654, 2004, 400, '12.99', '20.00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `transacao`
--

CREATE TABLE `transacao` (
  `stockID` int(11) DEFAULT NULL,
  `vendaID` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda`
--

CREATE TABLE `venda` (
  `vendaID` int(11) NOT NULL,
  `dataEvento` date NOT NULL,
  `funcionarioID` int(11) DEFAULT NULL,
  `clienteID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`autorID`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`clienteID`);

--
-- Índices de tabela `contato_cliente`
--
ALTER TABLE `contato_cliente`
  ADD KEY `clienteID` (`clienteID`);

--
-- Índices de tabela `contato_editora`
--
ALTER TABLE `contato_editora`
  ADD KEY `editorID` (`editorID`);

--
-- Índices de tabela `contato_funcionario`
--
ALTER TABLE `contato_funcionario`
  ADD KEY `funcionarioID` (`funcionarioID`);

--
-- Índices de tabela `editora`
--
ALTER TABLE `editora`
  ADD PRIMARY KEY (`editorID`);

--
-- Índices de tabela `escreve`
--
ALTER TABLE `escreve`
  ADD KEY `autorID` (`autorID`),
  ADD KEY `bookID` (`bookID`);

--
-- Índices de tabela `estoque`
--
ALTER TABLE `estoque`
  ADD PRIMARY KEY (`stockID`),
  ADD KEY `bookID` (`bookID`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`funcionarioID`);

--
-- Índices de tabela `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`bookID`),
  ADD KEY `editorID` (`editorID`),
  ADD KEY `autorID` (`autorID`);

--
-- Índices de tabela `transacao`
--
ALTER TABLE `transacao`
  ADD KEY `stockID` (`stockID`),
  ADD KEY `vendaID` (`vendaID`);

--
-- Índices de tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`vendaID`),
  ADD KEY `funcionarioID` (`funcionarioID`),
  ADD KEY `clienteID` (`clienteID`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `autor`
--
ALTER TABLE `autor`
  MODIFY `autorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `clienteID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `editora`
--
ALTER TABLE `editora`
  MODIFY `editorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `estoque`
--
ALTER TABLE `estoque`
  MODIFY `stockID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `funcionarioID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `livro`
--
ALTER TABLE `livro`
  MODIFY `bookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `vendaID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `contato_cliente`
--
ALTER TABLE `contato_cliente`
  ADD CONSTRAINT `contato_cliente_ibfk_1` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`);

--
-- Restrições para tabelas `contato_editora`
--
ALTER TABLE `contato_editora`
  ADD CONSTRAINT `contato_editora_ibfk_1` FOREIGN KEY (`editorID`) REFERENCES `editora` (`editorID`);

--
-- Restrições para tabelas `contato_funcionario`
--
ALTER TABLE `contato_funcionario`
  ADD CONSTRAINT `contato_funcionario_ibfk_1` FOREIGN KEY (`funcionarioID`) REFERENCES `funcionario` (`funcionarioID`);

--
-- Restrições para tabelas `escreve`
--
ALTER TABLE `escreve`
  ADD CONSTRAINT `escreve_ibfk_1` FOREIGN KEY (`autorID`) REFERENCES `autor` (`autorID`),
  ADD CONSTRAINT `escreve_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `livro` (`bookID`);

--
-- Restrições para tabelas `estoque`
--
ALTER TABLE `estoque`
  ADD CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `livro` (`bookID`) ON DELETE CASCADE;

--
-- Restrições para tabelas `livro`
--
ALTER TABLE `livro`
  ADD CONSTRAINT `livro_ibfk_1` FOREIGN KEY (`editorID`) REFERENCES `editora` (`editorID`),
  ADD CONSTRAINT `livro_ibfk_2` FOREIGN KEY (`autorID`) REFERENCES `autor` (`autorID`);

--
-- Restrições para tabelas `transacao`
--
ALTER TABLE `transacao`
  ADD CONSTRAINT `transacao_ibfk_1` FOREIGN KEY (`stockID`) REFERENCES `estoque` (`stockID`),
  ADD CONSTRAINT `transacao_ibfk_2` FOREIGN KEY (`vendaID`) REFERENCES `venda` (`vendaID`);

--
-- Restrições para tabelas `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`funcionarioID`) REFERENCES `funcionario` (`funcionarioID`),
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
