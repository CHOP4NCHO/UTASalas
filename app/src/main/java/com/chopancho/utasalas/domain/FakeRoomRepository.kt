package com.chopancho.utasalas.domain

class FakeRoomRepository: RoomRepository {
    override fun getRoomInfo(name: String): RoomInfo {


        return when (name) {
            "guallatire" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Algoritmos y estructuras de Datos GRUPO A",
                currentTeacherName = "Ibar Ramírez",
                scheduleEntries = listOf(
                    ScheduleEntry(dayIndex = 0, time = "08:00 AM", className = "Física Cuántica"),
                    ScheduleEntry(dayIndex = 0, time = "09:00 AM", className = "Álgebra Lineal"),
                    ScheduleEntry(dayIndex = 0, time = "10:00 AM", className = "Programación Android"),
                    ScheduleEntry(dayIndex = 0, time = "11:00 AM", className = "Inteligencia Artificial"),
                    ScheduleEntry(dayIndex = 0, time = "12:00 PM", className = "Redes de Computadoras"),

                    ScheduleEntry(dayIndex = 1, time = "08:00 AM", className = "Cálculo I"),
                    ScheduleEntry(dayIndex = 1, time = "09:00 AM", className = "Bases de Datos"),
                    ScheduleEntry(dayIndex = 1, time = "10:00 AM", className = "Programación Web"),
                    ScheduleEntry(dayIndex = 1, time = "11:00 AM", className = "Sistemas Operativos"),
                    ScheduleEntry(dayIndex = 1, time = "12:00 PM", className = "Arquitectura de Software"),

                    ScheduleEntry(dayIndex = 2, time = "08:00 AM", className = "Estructuras de Datos"),
                    ScheduleEntry(dayIndex = 2, time = "09:00 AM", className = "Ingeniería de Software"),
                    ScheduleEntry(dayIndex = 2, time = "10:00 AM", className = "Seguridad Informática"),
                    ScheduleEntry(dayIndex = 2, time = "11:00 AM", className = "Análisis de Algoritmos"),
                    ScheduleEntry(dayIndex = 2, time = "12:00 PM", className = "Sistemas Distribuidos"),

                    ScheduleEntry(dayIndex = 3, time = "08:00 AM", className = "Taller de Programación"),
                    ScheduleEntry(dayIndex = 3, time = "09:00 AM", className = "Gestión de Proyectos"),
                    ScheduleEntry(dayIndex = 3, time = "10:00 AM", className = "Ciencia de Datos"),
                    ScheduleEntry(dayIndex = 3, time = "11:00 AM", className = "Aprendizaje Automático"),
                    ScheduleEntry(dayIndex = 3, time = "12:00 PM", className = "Interacción Humano-Computadora"),

                    ScheduleEntry(dayIndex = 4, time = "08:00 AM", className = "Ética Profesional"),
                    ScheduleEntry(dayIndex = 4, time = "09:00 AM", className = "Emprendimiento"),
                    ScheduleEntry(dayIndex = 4, time = "10:00 AM", className = "Seminario de Título"),
                    ScheduleEntry(dayIndex = 4, time = "11:00 AM", className = "Desarrollo de Videojuegos"),
                    ScheduleEntry(dayIndex = 4, time = "12:00 PM", className = "Computación Gráfica")
                )
            )

            "socompa" -> RoomInfo(
                name = name, isTaken = false,
                currentClassName = null,
                currentTeacherName = null,
                scheduleEntries = listOf(
                    ScheduleEntry(dayIndex = 0, time = "08:00 AM", className = "Matemáticas Discretas"),
                    ScheduleEntry(dayIndex = 0, time = "09:00 AM", className = "Ecuaciones Diferenciales"),
                    ScheduleEntry(dayIndex = 0, time = "10:00 AM", className = "Teoría de la Computación"),
                    ScheduleEntry(dayIndex = 0, time = "11:00 AM", className = "Lógica y Circuitos Digitales"),
                    ScheduleEntry(dayIndex = 0, time = "12:00 PM", className = "Física Clásica"),

                    ScheduleEntry(dayIndex = 1, time = "08:00 AM", className = "Programación Orientada a Objetos"),
                    ScheduleEntry(dayIndex = 1, time = "09:00 AM", className = "Bases de Datos Avanzadas"),
                    ScheduleEntry(dayIndex = 1, time = "10:00 AM", className = "Redes Avanzadas"),
                    ScheduleEntry(dayIndex = 1, time = "11:00 AM", className = "Criptografía"),
                    ScheduleEntry(dayIndex = 1, time = "12:00 PM", className = "Sistemas de Información"),

                    ScheduleEntry(dayIndex = 2, time = "08:00 AM", className = "Compiladores"),
                    ScheduleEntry(dayIndex = 2, time = "09:00 AM", className = "Programación Funcional"),
                    ScheduleEntry(dayIndex = 2, time = "10:00 AM", className = "Robótica"),
                    ScheduleEntry(dayIndex = 2, time = "11:00 AM", className = "Sistemas Embebidos"),
                    ScheduleEntry(dayIndex = 2, time = "12:00 PM", className = "Visión por Computadora"),

                    ScheduleEntry(dayIndex = 3, time = "08:00 AM", className = "Computación en la Nube"),
                    ScheduleEntry(dayIndex = 3, time = "09:00 AM", className = "Big Data"),
                    ScheduleEntry(dayIndex = 3, time = "10:00 AM", className = "Bioinformática"),
                    ScheduleEntry(dayIndex = 3, time = "11:00 AM", className = "Procesamiento de Lenguaje Natural"),
                    ScheduleEntry(dayIndex = 3, time = "12:00 PM", className = "Geoinformática"),

                    ScheduleEntry(dayIndex = 4, time = "08:00 AM", className = "Gestión de la Innovación"),
                    ScheduleEntry(dayIndex = 4, time = "09:00 AM", className = "Marketing Digital"),
                    ScheduleEntry(dayIndex = 4, time = "10:00 AM", className = "Finanzas para Ingenieros"),
                    ScheduleEntry(dayIndex = 4, time = "11:00 AM", className = "Derecho Informático"),
                    ScheduleEntry(dayIndex = 4, time = "12:00 PM", className = "Inglés Técnico")
                )
            )

            "licancabur" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Arquitectura de Software",
                currentTeacherName = "Andrés Colque",
                scheduleEntries = listOf(
                    ScheduleEntry(dayIndex = 0, time = "08:00 AM", className = "Cálculo en Varias Variables"),
                    ScheduleEntry(dayIndex = 0, time = "09:00 AM", className = "Álgebra Abstracta"),
                    ScheduleEntry(dayIndex = 0, time = "10:00 AM", className = "Geometría Diferencial"),
                    ScheduleEntry(dayIndex = 0, time = "11:00 AM", className = "Análisis Real"),
                    ScheduleEntry(dayIndex = 0, time = "12:00 PM", className = "Topología"),

                    ScheduleEntry(dayIndex = 1, time = "08:00 AM", className = "Métodos Numéricos"),
                    ScheduleEntry(dayIndex = 1, time = "09:00 AM", className = "Estadística Descriptiva"),
                    ScheduleEntry(dayIndex = 1, time = "10:00 AM", className = "Estadística Inferencial"),
                    ScheduleEntry(dayIndex = 1, time = "11:00 AM", className = "Simulación"),
                    ScheduleEntry(dayIndex = 1, time = "12:00 PM", className = "Investigación de Operaciones"),

                    ScheduleEntry(dayIndex = 2, time = "08:00 AM", className = "Teoría de la Información"),
                    ScheduleEntry(dayIndex = 2, time = "09:00 AM", className = "Teoría de Grafos"),
                    ScheduleEntry(dayIndex = 2, time = "10:00 AM", className = "Inteligencia Computacional"),
                    ScheduleEntry(dayIndex = 2, time = "11:00 AM", className = "Cómputo Paralelo"),
                    ScheduleEntry(dayIndex = 2, time = "12:00 PM", className = "Cómputo Cuántico"),

                    ScheduleEntry(dayIndex = 3, time = "08:00 AM", className = "Programación de Videojuegos"),
                    ScheduleEntry(dayIndex = 3, time = "09:00 AM", className = "Realidad Virtual"),
                    ScheduleEntry(dayIndex = 3, time = "10:00 AM", className = "Realidad Aumentada"),
                    ScheduleEntry(dayIndex = 3, time = "11:00 AM", className = "Diseño de Interacción"),
                    ScheduleEntry(dayIndex = 3, time = "12:00 PM", className = "Usabilidad"),

                    ScheduleEntry(dayIndex = 4, time = "08:00 AM", className = "Tecnologías Móviles"),
                    ScheduleEntry(dayIndex = 4, time = "09:00 AM", className = "Computación Ubicua"),
                    ScheduleEntry(dayIndex = 4, time = "10:00 AM", className = "Internet de las Cosas (IoT)"),
                    ScheduleEntry(dayIndex = 4, time = "11:00 AM", className = "Sistemas de Recomendación"),
                    ScheduleEntry(dayIndex = 4, time = "12:00 PM", className = "Minería de Datos")
                )
            )

            "azufre" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Ayudantía de POO",
                currentTeacherName = "Pablo Varas",
                scheduleEntries = listOf(
                    ScheduleEntry(dayIndex = 0, time = "08:00 AM", className = "Química Orgánica"),
                    ScheduleEntry(dayIndex = 0, time = "09:00 AM", className = "Química Inorgánica"),
                    ScheduleEntry(dayIndex = 0, time = "10:00 AM", className = "Física del Estado Sólido"),
                    ScheduleEntry(dayIndex = 0, time = "11:00 AM", className = "Electromagnetismo"),
                    ScheduleEntry(dayIndex = 0, time = "12:00 PM", className = "Óptica"),

                    ScheduleEntry(dayIndex = 1, time = "08:00 AM", className = "Termodinámica"),
                    ScheduleEntry(dayIndex = 1, time = "09:00 AM", className = "Mecánica de Fluidos"),
                    ScheduleEntry(dayIndex = 1, time = "10:00 AM", className = "Resistencia de Materiales"),
                    ScheduleEntry(dayIndex = 1, time = "11:00 AM", className = "Dibujo Técnico"),
                    ScheduleEntry(dayIndex = 1, time = "12:00 PM", className = "Geología para Ingenieros"),

                    ScheduleEntry(dayIndex = 2, time = "08:00 AM", className = "Principios de Economía"),
                    ScheduleEntry(dayIndex = 2, time = "09:00 AM", className = "Macroeconomía"),
                    ScheduleEntry(dayIndex = 2, time = "10:00 AM", className = "Microeconomía"),
                    ScheduleEntry(dayIndex = 2, time = "11:00 AM", className = "Contabilidad"),
                    ScheduleEntry(dayIndex = 2, time = "12:00 PM", className = "Finanzas Corporativas"),

                    ScheduleEntry(dayIndex = 3, time = "08:00 AM", className = "Historia de Chile"),
                    ScheduleEntry(dayIndex = 3, time = "09:00 AM", className = "Historia Universal"),
                    ScheduleEntry(dayIndex = 3, time = "10:00 AM", className = "Filosofía"),
                    ScheduleEntry(dayIndex = 3, time = "11:00 AM", className = "Sociología"),
                    ScheduleEntry(dayIndex = 3, time = "12:00 PM", className = "Antropología"),

                    ScheduleEntry(dayIndex = 4, time = "08:00 AM", className = "Psicología General"),
                    ScheduleEntry(dayIndex = 4, time = "09:00 AM", className = "Psicología Social"),
                    ScheduleEntry(dayIndex = 4, time = "10:00 AM", className = "Psicología Organizacional"),
                    ScheduleEntry(dayIndex = 4, time = "11:00 AM", className = "Neurología"),
                    ScheduleEntry(dayIndex = 4, time = "12:00 PM", className = "Genética")
                )
            )

            "parinacota" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Sistemas Operativos",
                currentTeacherName = "Hector Ossandon",
                scheduleEntries = listOf(
                    ScheduleEntry(dayIndex = 0, time = "08:00 AM", className = "Biología Celular"),
                    ScheduleEntry(dayIndex = 0, time = "09:00 AM", className = "Biología Molecular"),
                    ScheduleEntry(dayIndex = 0, time = "10:00 AM", className = "Ecología"),
                    ScheduleEntry(dayIndex = 0, time = "11:00 AM", className = "Botánica"),
                    ScheduleEntry(dayIndex = 0, time = "12:00 PM", className = "Zoología"),

                    ScheduleEntry(dayIndex = 1, time = "08:00 AM", className = "Microbiología"),
                    ScheduleEntry(dayIndex = 1, time = "09:00 AM", className = "Virología"),
                    ScheduleEntry(dayIndex = 1, time = "10:00 AM", className = "Inmunología"),
                    ScheduleEntry(dayIndex = 1, time = "11:00 AM", className = "Farmacología"),
                    ScheduleEntry(dayIndex = 1, time = "12:00 PM", className = "Toxicología"),

                    ScheduleEntry(dayIndex = 2, time = "08:00 AM", className = "Fisiología"),
                    ScheduleEntry(dayIndex = 2, time = "09:00 AM", className = "Anatomía"),
                    ScheduleEntry(dayIndex = 2, time = "10:00 AM", className = "Patología"),
                    ScheduleEntry(dayIndex = 2, time = "11:00 AM", className = "Histología"),
                    ScheduleEntry(dayIndex = 2, time = "12:00 PM", className = "Embriología"),

                    ScheduleEntry(dayIndex = 3, time = "08:00 AM", className = "Nutrición"),
                    ScheduleEntry(dayIndex = 3, time = "09:00 AM", className = "Dietética"),
                    ScheduleEntry(dayIndex = 3, time = "10:00 AM", className = "Educación Física"),
                    ScheduleEntry(dayIndex = 3, time = "11:00 AM", className = "Deporte y Salud"),
                    ScheduleEntry(dayIndex = 3, time = "12:00 PM", className = "Primeros Auxilios"),

                    ScheduleEntry(dayIndex = 4, time = "08:00 AM", className = "Arte Moderno"),
                    ScheduleEntry(dayIndex = 4, time = "09:00 AM", className = "Historia del Arte"),
                    ScheduleEntry(dayIndex = 4, time = "10:00 AM", className = "Teatro"),
                    ScheduleEntry(dayIndex = 4, time = "11:00 AM", className = "Música"),
                    ScheduleEntry(dayIndex = 4, time = "12:00 PM", className = "Literatura")
                )
            )

            "pomerape" -> RoomInfo(
                name = name, isTaken = false,
                currentClassName = null,
                currentTeacherName = null,
                scheduleEntries = listOf(
                    ScheduleEntry(dayIndex = 0, time = "08:00 AM", className = "Dibujo Arquitectónico"),
                    ScheduleEntry(dayIndex = 0, time = "09:00 AM", className = "Urbanismo"),
                    ScheduleEntry(dayIndex = 0, time = "10:00 AM", className = "Construcción"),
                    ScheduleEntry(dayIndex = 0, time = "11:00 AM", className = "Diseño Interior"),
                    ScheduleEntry(dayIndex = 0, time = "12:00 PM", className = "Paisajismo"),

                    ScheduleEntry(dayIndex = 1, time = "08:00 AM", className = "Cine y Audiovisual"),
                    ScheduleEntry(dayIndex = 1, time = "09:00 AM", className = "Fotografía"),
                    ScheduleEntry(dayIndex = 1, time = "10:00 AM", className = "Animación"),
                    ScheduleEntry(dayIndex = 1, time = "11:00 AM", className = "Edición de Video"),
                    ScheduleEntry(dayIndex = 1, time = "12:00 PM", className = "Diseño Gráfico"),

                    ScheduleEntry(dayIndex = 2, time = "08:00 AM", className = "Periodismo Digital"),
                    ScheduleEntry(dayIndex = 2, time = "09:00 AM", className = "Comunicación Estratégica"),
                    ScheduleEntry(dayIndex = 2, time = "10:00 AM", className = "Publicidad"),
                    ScheduleEntry(dayIndex = 2, time = "11:00 AM", className = "Relaciones Públicas"),
                    ScheduleEntry(dayIndex = 2, time = "12:00 PM", className = "Marketing"),

                    ScheduleEntry(dayIndex = 3, time = "08:00 AM", className = "Derecho Civil"),
                    ScheduleEntry(dayIndex = 3, time = "09:00 AM", className = "Derecho Penal"),
                    ScheduleEntry(dayIndex = 3, time = "10:00 AM", className = "Derecho Laboral"),
                    ScheduleEntry(dayIndex = 3, time = "11:00 AM", className = "Derecho de Familia"),
                    ScheduleEntry(dayIndex = 3, time = "12:00 PM", className = "Derecho Constitucional"),

                    ScheduleEntry(dayIndex = 4, time = "08:00 AM", className = "Recursos Humanos"),
                    ScheduleEntry(dayIndex = 4, time = "09:00 AM", className = "Administración de Empresas"),
                    ScheduleEntry(dayIndex = 4, time = "10:00 AM", className = "Logística"),
                    ScheduleEntry(dayIndex = 4, time = "11:00 AM", className = "Negocios Internacionales"),
                    ScheduleEntry(dayIndex = 4, time = "12:00 PM", className = "Gestión de la Calidad")
                )
            )

            else -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Error, no hay datos de esa sala",
                currentTeacherName = null
            )
        }
    }

    override fun getRoomNames(): List<String> {
        return listOf(
            "parinacota",
            "pomerape",
            "socompa",
            "azufre",
            "guallatire",
            "licancabur"
        )
    }
}