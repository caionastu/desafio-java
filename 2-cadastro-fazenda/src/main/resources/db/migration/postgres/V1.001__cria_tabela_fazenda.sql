CREATE TABLE fazenda (
    id UUID NOT NULL PRIMARY KEY,
    nome varchar(200) NOT NULL ,
    cnpj varchar(14) NOT NULL,
    cidade varchar(150) NOT NULL,
    estado varchar(2) NOT NULL,
    logradouro varchar(255) NOT NUll
);