SET datestyle = dmy;
CREATE TYPE evaluacion AS ENUM ('muy baja', 'baja', 'media','alta','muy alta');

CREATE TABLE alumno(
    alumnoID SERIAL,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    segundo_apellido VARCHAR(100),
    email VARCHAR(100),
    telefono VARCHAR(100),
    repetidor BOOLEAN
);

CREATE TABLE curso(
    cursoID SERIAL,
    nombre VARCHAR(100),
    descripcion VARCHAR(100),
    profesorID INT
);

CREATE TABLE profesor(
    profesorID SERIAL,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    segundo_apellido VARCHAR(100),
    email VARCHAR(100),
    telefono VARCHAR(100)
);

CREATE TABLE matricula(
    matriculaID SERIAL,
    alumnoID INT,
    cursoID INT,
    fechaInit DATE
);

CREATE TABLE feedback_semanal(
    semanalID SERIAL,
    asignaturaID INT,
    feedbackID INT,
    semana INT
);

CREATE TABLE feedback(
    feedbackID SERIAL,
    atencion evaluacion,
    motivacion evaluacion,
    horas_trabajadas DECIMAL(10,9)
);

CREATE TABLE entregable(
    entregableID SERIAL,
    nombre VARCHAR(100),
    descripcion VARCHAR(100),
    competencia VARCHAR(100),
    peso INT,
    nota DECIMAL(3,2),
    fecha DATE,
    asignaturaID INT
);

CREATE TABLE examen(
    examenID SERIAL,
    nombre VARCHAR(100),
    descripcion VARCHAR(100),
    competencia VARCHAR(100),
    peso INT,
    nota DECIMAL(3,2),
    fecha DATE,
    asignaturaID INT
);

CREATE TABLE asignatura(
    asignaturaID SERIAL,
    matriculaID INT,
    nombre VARCHAR(100),
    descripcion VARCHAR(100),
    peso INT,
    profesorID INT,
    alarmaID INT
);

CREATE TABLE comportamiento(
    comportamientoID SERIAL,
    atencion evaluacion,
    motivacion evaluacion,
    asistencia INT
);

CREATE TABLE comportamiento_semanal(
    csemanalID SERIAL,
    asignaturaID INT,
    comportamientoID INT,
    semana INT
);

CREATE TABLE alarma(
    alarmaID SERIAL,
    tipo VARCHAR(100),
    descripcion VARCHAR (100),
    correlacion_asistencia NUMERIC (5,5),
    correlacion_nota NUMERIC (5,5),
    correlacion_entregable NUMERIC (5,5),
    correlacion_entregable_nota NUMERIC (5,5),
    correlacion_horas NUMERIC (5,5),
    correlacion_atp NUMERIC (5,5),
    correlacion_motp NUMERIC (5,5),
    correlacion_ata NUMERIC (5,5),
    correlacion_mota NUMERIC (5,5)
);

CREATE TABLE alarma_semanal(
    asemanalID SERIAL,
    alumnoID int,
    alarmaID int,
    semana int
);

ALTER TABLE alarma
ADD CONSTRAINT ALARMA_PK PRIMARY KEY(alarmaID);

ALTER TABLE alumno
ADD CONSTRAINT ALUMNO_PK PRIMARY KEY (alumnoID);

ALTER TABLE alarma_semanal
ADD CONSTRAINT ASEMANAL_PK PRIMARY KEY (asemanalID),
ADD CONSTRAINT ALARMA_FK FOREIGN KEY (alarmaID) REFERENCES alarma (alarmaID),
ADD CONSTRAINT ALUMNO_FK FOREIGN KEY (alumnoID) REFERENCES alumno (alumnoID);


ALTER TABLE profesor
ADD CONSTRAINT PROFESOR_PK PRIMARY KEY (profesorID);

ALTER TABLE curso
ADD CONSTRAINT CURSO_PK PRIMARY KEY (cursoID),
ADD CONSTRAINT PROFESOR_FK FOREIGN KEY (profesorID) REFERENCES profesor (profesorID);

ALTER TABLE matricula
ADD CONSTRAINT MATRICULA_PK PRIMARY KEY (matriculaID),
ADD CONSTRAINT ALUMNO_FK FOREIGN KEY (alumnoID) REFERENCES alumno (alumnoID),
ADD CONSTRAINT CURSO_FK FOREIGN KEY (cursoID) REFERENCES curso (cursoID);

ALTER TABLE feedback
ADD CONSTRAINT FEEDBACK_PK PRIMARY KEY (feedbackID);

ALTER TABLE asignatura
ADD CONSTRAINT ASIGNATURA_PK PRIMARY KEY (asignaturaID),
ADD CONSTRAINT ALARMA_FK FOREIGN KEY (alarmaID) REFERENCES alarma (alarmaID),
ADD CONSTRAINT MATRICULA_FK FOREIGN KEY (matriculaID) REFERENCES matricula (matriculaID),
ADD CONSTRAINT PROFESOR_FK FOREIGN KEY (profesorID) REFERENCES profesor (profesorID);

ALTER TABLE comportamiento
ADD CONSTRAINT COMPORTAMIENTO_PK PRIMARY KEY (comportamientoID);

ALTER TABLE examen
ADD CONSTRAINT EXAMEN_PK PRIMARY KEY (examenID),
ADD CONSTRAINT ASIGNATURA_FK FOREIGN KEY (asignaturaID) REFERENCES asignatura (asignaturaID);

ALTER TABLE entregable
ADD CONSTRAINT ENTREGABLE_PK PRIMARY KEY (entregableID),
ADD CONSTRAINT ASIGNATURA_FK FOREIGN KEY (asignaturaID) REFERENCES asignatura (asignaturaID);

ALTER TABLE feedback_semanal
ADD CONSTRAINT FEEDBACK_SEMANAL_PK PRIMARY KEY (semanalID),
ADD CONSTRAINT ASIGNATURA_FK FOREIGN KEY (asignaturaID) REFERENCES asignatura (asignaturaID),
ADD CONSTRAINT FEEDBACK_FK FOREIGN KEY (feedbackID) REFERENCES feedback (feedbackID);

ALTER TABLE comportamiento_semanal
ADD CONSTRAINT CSEMANAL_PK PRIMARY KEY (csemanalID),
ADD CONSTRAINT ASIGNATURA_FK FOREIGN KEY (asignaturaID) REFERENCES asignatura (asignaturaID),
ADD CONSTRAINT COMPORTAMIENTO_FK FOREIGN KEY (comportamientoID) REFERENCES comportamiento (comportamientoID);
