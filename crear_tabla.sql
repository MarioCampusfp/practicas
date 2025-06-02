create database kihabara_db;
use kihabara_db;

create table producto(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255) not null,
    categoria varchar(100),
    precio decimal(10,2),
    stock int
);


CREATE USER userAkihabara IDENTIFIED BY "curso";

grant select, update,delete,insert
on kihabara_db.producto
to userAkihabara;

insert into producto(nombre,categoria,precio,stock) values
('Figura de Naruto Uzumaki', 'Figura', 29.99, 15),
('Manga Naruto Vol. 1', 'Manga', 7.99, 50),
('Póster Attack on Titan', 'Póster', 5.50, 30),
('Llavero One Piece', 'Llavero', 3.00, 100),
('Camiseta Goku', 'Ropa', 19.99, 25),
('Figura de Luffy Gear 4', 'Figura', 34.99, 10),
('Manga One Piece Vol. 5', 'Manga', 8.99, 40),
('Póster My Hero Academia', 'Póster', 6.50, 20),
('Llavero Naruto Konoha', 'Llavero', 2.50, 80),
('Sudadera de Levi (Attack on Titan)', 'Ropa', 39.99, 12),
('Figura de Sailor Moon', 'Figura', 27.50, 18),
('Manga Sailor Moon Vol. 3', 'Manga', 9.50, 30),
('Póster Demon Slayer', 'Póster', 5.99, 25),
('Llavero Tokyo Ghoul Máscara', 'Llavero', 4.00, 60),
('Playera de Tanjiro (Demon Slayer)', 'Ropa', 22.00, 20),
('Figura de Eren Jaeger Titan', 'Figura', 31.99, 9),
('Manga Attack on Titan Vol. 10', 'Manga', 8.49, 35),
('Póster Jujutsu Kaisen', 'Póster', 6.75, 28),
('Llavero Spy x Family Anya', 'Llavero', 3.25, 70),
('Hoodie de Nezuko', 'Ropa', 44.99, 10),
('Figura de Goku Super Saiyan', 'Figura', 36.00, 14),
('Manga Dragon Ball Z Vol. 1', 'Manga', 7.49, 45),
('Póster Death Note', 'Póster', 4.99, 22),
('Llavero Bleach Shinigami', 'Llavero', 3.75, 55),
('Pijama Pikachu', 'Ropa', 29.99, 18);