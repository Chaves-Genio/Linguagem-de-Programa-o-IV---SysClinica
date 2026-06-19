create database clinicadb;
use clinicadb;

CREATE TABLE pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    bi VARCHAR(14),
    nascimento DATE,
    telefone VARCHAR(20),
    endereco TEXT,
    historico TEXT,
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    crm VARCHAR(50) UNIQUE,
    especialidade VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(100),
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT NOT NULL,
    data_consulta DATE NOT NULL,
    hora_consulta TIME NOT NULL,
    status ENUM('AGENDADA', 'CONFIRMADA', 'REALIZADA', 'CANCELADA') DEFAULT 'AGENDADA',
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (medico_id) REFERENCES medicos(id)
);


CREATE TABLE prontuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consulta_id INT NOT NULL,
    diagnostico TEXT,
    medicamentos TEXT,
    observacoes TEXT,
    FOREIGN KEY (consulta_id) REFERENCES consultas(id)
);


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil ENUM('ADMIN','MEDICO','RECEPCIONISTA') NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);