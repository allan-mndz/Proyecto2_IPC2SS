create database DBProyecto2SS;
use DBProyecto2SS;

-- acceso
create table Usuario(
	id int unique auto_increment not null,
    username varchar(50) unique not null,
    rol enum('SuperAdmin', 'Admin', 'Bibliotecario', 'Secretaria', 'Maestro', 'Estudiante', 'Mantenimiento') not null,
    contrasena varchar(255) not null,
    activo tinyint default 1 not null,
    primary key(id)
);

create table Recuperacion_contrasena(
	id int unique auto_increment not null,
    usuario_id int unique not null,
    token varchar(255) not null,
    fecha_expiracion datetime not null,
    primary key(id),
    foreign key(usuario_id) references Usuario(id)
		on update cascade
        on delete restrict
);

-- recursos humanos y estudiantes
create table Empleado(
	id int unique auto_increment not null,
    usuario_id int unique not null,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    fecha_contratacion date not null,
    salario decimal(10, 2) not null,
    primary key(id),
    foreign key(usuario_id) references Usuario(id)
		on update cascade 
        on delete restrict
);

create table Estudiante(
	id int unique auto_increment not null,
    usuario_id int unique not null,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    informacion_medica text not null,
    nombre_encargado varchar(50) not null,
    telefono_encargado varchar(12) not null,
    estado enum('Activo', 'Inactivo', 'Graduado') default 'Activo' not null,
    primary key(id),
    foreign key(usuario_id) references Usuario(id)
		on update cascade
        on delete restrict
);

-- Gestion academica
create table Ciclo_escolar(
	id int unique auto_increment not null,
    anio int not null,
    activo tinyint default 0 not null,
    primary key(id)
);

create table Carrera(
	id int unique auto_increment not null,
    nombre varchar(200) not null,
    activo tinyint default 1 not null,
    primary key(id)
);

create table Curso(
	id int unique auto_increment not null,
    nombre varchar(100) not null,
    primary key(id)
);

create table Seccion(
	id int unique auto_increment not null,
    nombre varchar(10) not null,
    primary key(id)
);

create table Grado(
	id int unique auto_increment not null,
    nombre varchar(50) not null,
    nivel enum('PRE_PRIMARIA', 'PRIMARIA', 'BASICO', 'DIVERSIFICADO') NOT NULL,
    carrera_id int null,
    primary key(id),
    foreign key(carrera_id) references Carrera(id)
		on update cascade
        on delete restrict
);

create table Curriculo(
	id int unique auto_increment not null,
    grado_id int not null,
    curso_id int not null,
    ciclo_escolar_id int not null,
    primary key(id),
    foreign key(grado_id) references Grado(id)
		on update cascade
        on delete restrict,
	foreign key(curso_id) references Curso(id)
		on update cascade
        on delete restrict,
	foreign key(ciclo_escolar_id) references Ciclo_escolar(id)
		on update cascade
        on delete restrict
);

create table Inscripcion(
	id int unique auto_increment not null,
    estudiante_id int not null,
    grado_id int not null,
    seccion_id int not null,
    ciclo_escolar_id int not null,
    fecha_inscripcion date not null,
    primary key(id),
    foreign key(estudiante_id) references Estudiante(id)
		on update cascade
        on delete restrict,
	foreign key(grado_id) references Grado(id)
		on update cascade
        on delete restrict,
	foreign key(seccion_id) references Seccion(id)
		on update cascade
        on delete restrict,
	foreign key(ciclo_escolar_id) references Ciclo_escolar(id)
		on update cascade
        on delete restrict
);

create table Asignacion_maestro(
	id int unique auto_increment not null,
    empleado_id int not null,
    curso_id int not null,
    seccion_id int not null,
    ciclo_escolar_id int not null,
    primary key(id),
    foreign key(empleado_id) references Empleado(id)
		on update cascade
        on delete restrict,
	foreign key(curso_id) references Curso(id)
		on update cascade
        on delete restrict,
	foreign key(seccion_id) references Seccion(id)
		on update cascade
        on delete restrict,
	foreign key(ciclo_escolar_id) references Ciclo_escolar(id)
		on update cascade
        on delete restrict
);

create table Asistencia(
	id int unique auto_increment not null,
    inscripcion_id INT NOT NULL, 
    curso_id INT NOT NULL,
    fecha DATE NOT NULL,
    presente TINYINT NOT NULL,
    primary key(id),
    FOREIGN KEY(inscripcion_id) REFERENCES Inscripcion(id) 
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
    FOREIGN KEY(curso_id) REFERENCES Curso(id) 
		ON UPDATE CASCADE 
		ON DELETE RESTRICT
);

create table Nota(
	id int unique auto_increment not null,
	inscripcion_id INT NOT NULL,
    curso_id INT NOT NULL,
    actividad VARCHAR(100) NOT NULL,
    ponderacion DECIMAL(5,2) NOT NULL,
    punteo_obtenido DECIMAL(5,2) NOT NULL,
    primary key(id),
    FOREIGN KEY(inscripcion_id) REFERENCES Inscripcion(id)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
    FOREIGN KEY(curso_id) REFERENCES Curso(id)
		ON UPDATE CASCADE 
		ON DELETE RESTRICT
);

-- secretaria/financias
create table Pago(
	id int unique auto_increment not null,
    estudiante_id int not null,
    secretaria_id int not null,
    concepto enum('Inscripción', 'Mes de colegiatura', 'Actividad', 'Multa Biblioteca') NOT NULL,
    monto_exacto DECIMAL(10, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    primary key(id),
    foreign key(estudiante_id) references Estudiante(id)
		on update cascade
        on delete restrict,
	foreign key(secretaria_id) references Usuario(id)
		on update cascade
        on delete restrict
);

-- biblitecario
create table Libro(
	id int unique auto_increment not null,
    titulo varchar(100) not null,
    autor varchar(75) not null,
    editorial varchar(50) not null,
    cantidad_disponible int not null,
    primary key(id)
);

create table Prestamo(
	id int unique auto_increment not null,
    libro_id int not null,
    usuario_id int not null,
    fecha_prestamo date not null,
    fecha_esperada_devolucion date not null,
    fecha_devolucion_real date null,
    primary key(id),
    foreign key(libro_id) references Libro(id)
		on update cascade
        on delete restrict,
	foreign key(usuario_id) references Usuario(id)
		on update cascade
        on delete restrict
);

create table Multa(
	id int unique auto_increment not null,
    prestamo_id int unique not null,
    pago_id int null,
    monto_calculado decimal(10,2) not null,
    pagado tinyint default 0 not null,
    primary key(id),
    foreign key(prestamo_id) references Prestamo(id)
		on update cascade
        on delete restrict,
	foreign key(pago_id) references Pago(id)
		on update cascade
        on delete restrict
);