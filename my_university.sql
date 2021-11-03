CREATE USER uni WITH ENCRYPTED PASSWORD 'c")zX2t)zwU2Ph4E';
CREATE DATABASE my_university OWNER uni;
CREATE SCHEMA IF NOT EXISTS my_university.management_data;
GRANT ALL ON SCHEMA management_data TO uni;

CREATE SEQUENCE IF NOT EXISTS management_data.department_seq START WITH 1 MINVALUE 1 INCREMENT 1;
GRANT USAGE, SELECT ON SEQUENCE management_data.department_seq TO uni;
CREATE TABLE IF NOT EXISTS management_data.department
(
    id   INTEGER
        CONSTRAINT department_pk PRIMARY KEY DEFAULT nextval('management_data.department_seq'),
    name VARCHAR(50) NOT NULL
) WITHOUT OIDS;
ALTER TABLE management_data.department
    OWNER TO uni;
CREATE UNIQUE INDEX department_id_idx ON management_data.department (id);

CREATE SEQUENCE IF NOT EXISTS management_data.professor_seq START WITH 1 MINVALUE 1 INCREMENT 1;
GRANT USAGE, SELECT ON SEQUENCE management_data.professor_seq TO uni;
CREATE TABLE IF NOT EXISTS management_data.professor
(
    id            INTEGER
        CONSTRAINT professor_pk PRIMARY KEY DEFAULT nextval('management_data.professor_seq'),
    name          VARCHAR(50) NOT NULL,
    department_id INTEGER,
    CONSTRAINT professor_department_fk FOREIGN KEY (department_id) REFERENCES management_data.department (id)
) WITHOUT OIDS;
ALTER TABLE management_data.professor
    OWNER TO uni;
CREATE UNIQUE INDEX professor_id_idx ON management_data.professor (id);

CREATE SEQUENCE IF NOT EXISTS management_data.course_seq START WITH 1 MINVALUE 1 INCREMENT 1;
GRANT USAGE, SELECT ON SEQUENCE management_data.course_seq TO uni;
CREATE TABLE IF NOT EXISTS management_data.course
(
    id            INTEGER
        CONSTRAINT course_pk PRIMARY KEY DEFAULT nextval('management_data.course_seq'),
    credits       SMALLINT     NOT NULL,
    name          VARCHAR(100) NOT NULL,
    department_id INTEGER,
    CONSTRAINT course_department_fk FOREIGN KEY (department_id) REFERENCES management_data.department (id)
) WITHOUT OIDS;
ALTER TABLE management_data.course
    OWNER TO uni;
CREATE UNIQUE INDEX course_id_idx ON management_data.course (id);

CREATE TABLE IF NOT EXISTS management_data.schedule
(
    professor_id INTEGER,
    course_id    INTEGER,
    semester     SMALLINT,
    year         SMALLINT,
    CONSTRAINT schedule_pk PRIMARY KEY (professor_id, course_id),
    CONSTRAINT schedule_professor_fk FOREIGN KEY (professor_id) REFERENCES management_data.professor (id),
    CONSTRAINT schedule_course_fk FOREIGN KEY (course_id) REFERENCES management_data.course (id)
);
ALTER TABLE management_data.schedule
    OWNER TO uni;
CREATE INDEX schedule_course_professor_idx ON management_data.schedule (professor_id, course_id);

INSERT INTO management_data.department (name)
VALUES ('Physical Sciences');
INSERT INTO management_data.department (name)
VALUES ('Clinical Medicine');
INSERT INTO management_data.department (name)
VALUES ('Biological Sciences');
INSERT INTO management_data.department (name)
VALUES ('Arts and Humanities');
INSERT INTO management_data.department (name)
VALUES ('Technology');
INSERT INTO management_data.department (name)
VALUES ('Humanities & Social Sciences');

INSERT INTO management_data.professor (name, department_id)
VALUES ('John Doe', 5);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Frida Mcintosh', 2);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Grace Avery', 1);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Ada Osborne', 3);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Rowan Graves', 1);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Selena Owen', 5);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Sarahi Barry', 2);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Camden Lin', 1);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Daniel Hicks', 5);
INSERT INTO management_data.professor (name, department_id)
VALUES ('Timothy Hickman', 4);

INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Pure Mathematics and Mathematical Statistics', 1, 3);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Applied Mathematics and Theoretical Physics', 1, 5);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Earth Science', 1, 7);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Astronomy', 1, 6);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Physics', 1, 8);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Geography', 1, 7);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Materials Science and Metallurgy', 1, 5);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Chemistry', 1, 1);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Clinical Biochemistry', 2, 3);
INSERT INTO management_data.course (name, department_id, credits)
VALUES ('Clinical Neuroscience', 2, 5);

INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (5, 3, 6, 2012);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (7, 3, 1, 2013);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (5, 7, 6, 2010);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (2, 10, 2, 2004);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (5, 1, 1, 2011);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (2, 9, 4, 2005);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (7, 10, 6, 2009);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (5, 6, 4, 2007);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (7, 9, 1, 2014);
INSERT INTO management_data.schedule (professor_id, course_id, semester, year)
VALUES (9, 9, 5, 2011);
