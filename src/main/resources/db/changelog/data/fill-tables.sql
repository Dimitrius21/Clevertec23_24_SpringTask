--liquibase formatted sql
--changeset dmitri:fill_tables contextFilter:dev

INSERT INTO users (name, nick_name, email) VALUES ('Serge', 'Sergo', 'serge@mail.com'),
                                                   ('Alice', 'Selezneva', 'alice@mail.com');
