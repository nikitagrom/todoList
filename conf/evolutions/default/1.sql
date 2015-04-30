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
CREATE TABLE Task(
id bigint(20)NOT NULL AUTO_INCREMENT,
name VARCHAR (255) NOT NULL ,
project VARCHAR (255) NOT NULL ,
isDone boolean NOT NULL ,
dueDate DATE NOT NULL ,
folder VARCHAR (255) NOT NULL ,

FOREIGN KEY (project,folder) REFERENCES Project(projectname,folder),
);
# --- !Downs

DROP TABLE User;
DROP TABLE Project;
DROP TABLE Task;