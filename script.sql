DROP DATABASE BDEXEMPLO;
CREATE DATABASE BDEXEMPLO;
USE BDEXEMPLO;

CREATE TABLE Funcionario(

	codigo INT AUTO_INCREMENT,
    nome VARCHAR(250) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    salario REAL NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO Funcionario(nome, cargo, salario) VALUES('Joao', 'Analista', 7500);

SELECT * FROM 	Funcionario;