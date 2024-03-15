DROP TABLE Apunte;
DROP TABLE Rating;
DROP TABLE Notification;
DROP TABLE Comentario;
DROP TABLE Post;
DROP TABLE FollowedSubject;
DROP TABLE FollowedUser;
DROP TABLE Subject;
DROP TABLE Users;
DROP TABLE University;

CREATE TABLE IF NOT EXISTS Users (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   userName VARCHAR(60) NOT NULL,
   password VARCHAR(60) NOT NULL,
   firstName VARCHAR(60) NOT NULL,
   lastName VARCHAR(60) NOT NULL,
   email VARCHAR(60) NOT NULL,
   role TINYINT NOT NULL,
   avatar BLOB 
);

/*Creacion usuario con userName y contraseña test*/
INSERT INTO Users (userName, password, firstName, lastName, email, role, avatar) VALUES ('test', '$2a$10$tAX5UGkz3VvxhLe8.463oOuYMOXGFXB..pZzc2/sXXbOnJ2eWO2NO', 'a', 'a', 'a@a', 0, '');


CREATE TABLE IF NOT EXISTS FollowedUser (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   followedId BIGINT NOT NULL,
   followerId BIGINT NOT NULL,
   CONSTRAINT FollowedSubjectSubjectIdFK FOREIGN KEY (followedId)
       REFERENCES Users (id)
       ON DELETE CASCADE,
   CONSTRAINT FollowedSubjectUserIdFK FOREIGN KEY (followerId)
       REFERENCES Users (id)
       ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS FollowedUser (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    followerId BIGINT NOT NULL,
    followedId BIGINT NOT NULL,
    CONSTRAINT FollowedUserFollowerIdFK FOREIGN KEY (followerId)
                            REFERENCES Users (id)
                            ON DELETE CASCADE,
    CONSTRAINT FollowedUserFollowedIdFK FOREIGN KEY (followedId)
                            REFERENCES Users (id)
                            ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS University (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uniName VARCHAR(60) NOT NULL
);

INSERT INTO University (uniName) VALUES ('UDC');
INSERT INTO University (uniName) VALUES ('USC');


CREATE TABLE IF NOT EXISTS Subject (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    subjectName VARCHAR(60) NOT NULL,
    universityId BIGINT NOT NULL,
    CONSTRAINT SubjectUniversityIdFK FOREIGN KEY (universityId)
                            REFERENCES University (id)
                            ON DELETE CASCADE
);

INSERT INTO Subject (subjectname, universityid) VALUES ('Teoría da Computación', 1);
INSERT INTO Subject (subjectname, universityid) VALUES ('Lexislación e Seguridade Informática', 1);

CREATE TABLE IF NOT EXISTS FollowedSubject (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT NOT NULL,
    subjectId BIGINT NOT NULL,
    CONSTRAINT FollowedSubjectSubjectIdFK FOREIGN KEY (subjectId)
                            REFERENCES Subject (id)
                            ON DELETE CASCADE,
    CONSTRAINT FollowedSubjectUserIdFK FOREIGN KEY (userId)
                            REFERENCES Users (id)
                            ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Post (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    creationDate DATETIME NOT NULL,
    academicYear VARCHAR(255) NOT NULL,
    userId VARCHAR(255) NOT NULL,
    subjectId BIGINT NOT NULL,
    avgRating DECIMAL(2, 1) NOT NULL,
    CONSTRAINT PostUserIdFK FOREIGN KEY (userId)
                            REFERENCES Users (id)
                            ON DELETE CASCADE,
    CONSTRAINT PostSubjectIdFK FOREIGN KEY (subjectId)
                            REFERENCES Subject (id)
                            ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Apunte (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    path VARCHAR(255) NOT NULL,
    postId BIGINT NOT NULL,
    CONSTRAINT ApuntePostIdFK FOREIGN KEY (postId)
                            REFERENCES Post (id)
                            ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Rating (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rating INT NOT NULL,
    postId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    CONSTRAINT RatingPostIdFK FOREIGN KEY (postId)
                            REFERENCES Post (id)
                            ON DELETE CASCADE,
    CONSTRAINT RatingUserIdFK FOREIGN KEY (userId)
                            REFERENCES Users (id)
                            ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Comentario (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    textoComentario VARCHAR(255) NOT NULL,
    userId BIGINT NOT NULL,
    postId BIGINT NOT NULL,
    comentarioPadreId BIGINT,
    CONSTRAINT commentPostIdFK FOREIGN KEY(postId)
                            REFERENCES Post (id)
                            ON DELETE CASCADE,
    CONSTRAINT commentUserIdFK FOREIGN KEY(userId)
                            REFERENCES Users (id)
                            ON DELETE CASCADE,
    CONSTRAINT commentRespuestaIdFK FOREIGN KEY(comentarioPadreId)
                            REFERENCES Comentario (id)
                            ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Notification (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `read` BOOLEAN NOT NULL,
    newPost BOOLEAN NOT NULL,
    userId BIGINT NOT NULL,
    comentarioId BIGINT,
    postId BIGINT,
    CONSTRAINT NotificationUserIdFK FOREIGN KEY (userId)
                    REFERENCES Users(id)
                    ON DELETE CASCADE,
    CONSTRAINT NotificationComentarioIdFK FOREIGN KEY (comentarioId)
                    REFERENCES Comentario(id)
                    ON DELETE CASCADE,
    CONSTRAINT NotificationPostIdFK FOREIGN KEY (postId)
                    REFERENCES Post(id)
                    ON DELETE CASCADE
);
