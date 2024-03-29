DROP TABLE Apunte;
DROP TABLE Rating;
DROP TABLE Notification;
DROP TABLE Comentario;
DROP TABLE IF EXISTS EtiquetaOfPost;
DROP TABLE Post;
DROP TABLE IF EXISTS Etiqueta;
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
) engine=innodb;

/*Creacion usuario con userName y contraseña test*/
INSERT INTO Users (userName, password, firstName, lastName, email, role, avatar) VALUES ('test', '$2a$10$tAX5UGkz3VvxhLe8.463oOuYMOXGFXB..pZzc2/sXXbOnJ2eWO2NO', 'a', 'a', 'a@a', 0, '');


CREATE TABLE IF NOT EXISTS FollowedUser (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   followedId BIGINT NOT NULL,
   followerId BIGINT NOT NULL,
   CONSTRAINT FollowedUserFollowerIdFK FOREIGN KEY (followedId)
       REFERENCES Users (id)
       ON DELETE CASCADE,
   CONSTRAINT FollowedUserFollowedIdFK FOREIGN KEY (followerId)
       REFERENCES Users (id)
       ON DELETE CASCADE
) engine=innodb;


CREATE TABLE IF NOT EXISTS University (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uniName VARCHAR(60) NOT NULL
) engine=innodb;

INSERT INTO University (uniName) VALUES ('UDC');
INSERT INTO University (uniName) VALUES ('USC');


CREATE TABLE IF NOT EXISTS Subject (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    subjectName VARCHAR(60) NOT NULL,
    universityId BIGINT NOT NULL,
    CONSTRAINT SubjectUniversityIdFK FOREIGN KEY (universityId)
                            REFERENCES University (id)
                            ON DELETE CASCADE
) engine=innodb;

INSERT INTO Subject (subjectname, universityid) VALUES ('Teoria da Computacion', 1);
INSERT INTO Subject (subjectname, universityid) VALUES ('Lexislacion e Seguridade Informatica', 1);

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
) engine=innodb;


CREATE TABLE IF NOT EXISTS Post (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    creationDate DATETIME NOT NULL,
    academicYear INT NOT NULL,
    userId BIGINT NOT NULL,
    universityId BIGINT NOT NULL,
    subjectId BIGINT NOT NULL,
    avgRating DECIMAL(2, 1) NOT NULL,
    CONSTRAINT PostUserIdFK FOREIGN KEY (userId)
                            REFERENCES Users (id)
                            ON DELETE CASCADE,
    CONSTRAINT PostSubjectIdFK FOREIGN KEY (subjectId)
                            REFERENCES Subject (id)
                            ON DELETE CASCADE
) engine=innodb;


CREATE TABLE IF NOT EXISTS Etiqueta (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `key` VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL
) engine=innodb;


CREATE TABLE IF NOT EXISTS Apunte (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contentType VARCHAR(255) NOT NULL,
    size BIGINT NOT NULL,
    storagePath VARCHAR(255) NOT NULL,
    postId BIGINT NOT NULL,
    CONSTRAINT ApuntePostIdFK FOREIGN KEY (postId)
                            REFERENCES Post (id)
                            ON DELETE CASCADE
) engine=innodb;


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
) engine=innodb;

CREATE TABLE IF NOT EXISTS EtiquetaOfPost (
      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      postId BIGINT NOT NULL,
      etiquetaId BIGINT NOT NULL,
      CONSTRAINT EtiquetaOfPostPostIdFK FOREIGN KEY (postId)
          REFERENCES Post (id)
          ON DELETE CASCADE,
      CONSTRAINT EtiquetaOfPostEtiquetaIdFK FOREIGN KEY (etiquetaId)
          REFERENCES Etiqueta (id)
          ON DELETE CASCADE
) engine=innodb;

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
) engine=innodb;


CREATE TABLE IF NOT EXISTS Notification (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `read` BOOLEAN NOT NULL,
    newPost BOOLEAN NOT NULL,
    newPostSubject BOOLEAN NOT NULL,
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
) engine=innodb;
