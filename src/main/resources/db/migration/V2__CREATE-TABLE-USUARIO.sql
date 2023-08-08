CREATE TABLE usuarios(
    id_usuario int AUTO_INCREMENT PRIMARY KEY,
    email varchar(255) not null,
    senha varchar(255) not null,
    idTypeuser int not null,
    FOREIGN KEY (idTypeuser) references typeuser(id_typeuser)
);