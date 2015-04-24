# Users schema

# --- !Ups

CREATE TABLE User (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    fullname varchar(255) NOT NULL,
    isAdmin boolean NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE Project(
projectname VARCHAR (255) NOT NULL ,
folder VARCHAR (255)NOT NULL ,
owner VARCHAR (255) NOT NULL ,
FOREIGN KEY (owner) REFERENCES User(email)
);
# --- !Downs

DROP TABLE User;
DROP TABLE Project