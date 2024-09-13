# Proyecto final Backend CTD

## Clínica Odontológica

### Integrantes:

- [Alan García Vázquez](https://github.com/alangvazq)
- [Ayxa Chaverra](https://github.com/achaverrar)

### Tabla de contenidos

- [Endpoints Odontólogos](#endpoints-odontólogos)
  - [Listar todos los odontólogos](#listar-todos-los-odontólogos)
  - [Buscar odontólogo por ID](#buscar-odontólogo-por-id)
  - [Agregar un nuevo odontólogo](#agregar-un-nuevo-odontólogo)
  - [Modificar un odontólogo](#modificar-un-odontólogo)
  - [Eliminar un odontólogo](#eliminar-un-odontólogo)
- [Endpoints Pacientes](#endpoints-pacientes)
  - [Listar todos los pacientes](#listar-todos-los-pacientes)
  - [Buscar paciente por ID](#buscar-odontólogo-por-id)
  - [Buscar paciente por DNI](#buscar-paciente-por-dni)
  - [Agregar un nuevo paciente](#agregar-un-nuevo-paciente)
  - [Modificar un paciente](#modificar-un-paciente)
  - [Eliminar un paciente](#eliminar-un-paciente)
- [Endpoints Turnos](#endpoints-turnos)
  - [Listar todos los turnos](#listar-todos-los-turnos)
  - [Buscar el último turno](#buscar-el-último-turno)
  - [Crear turnos para un odontólogo](#crear-turnos-para-un-odontólogo)
  - [Asignar un turno a un paciente](#asignar-un-turno-a-un-paciente)

### Endpoints Odontólogos

Este conjunto de endpoints permite gestionar los odontólogos de la clínica dental.

#### Listar todos los odontólogos

**GET** `/api/v1/odontologos`

Retorna una lista de todos los odontólogos registrados.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
[
  {
    "id": 1,
    "nombre": "Laura",
    "apellido": "García",
    "matricula": "ABC123"
  }
]
```

#### Buscar odontólogo por ID

**GET** `/api/v1/odontologos/{odontologoId}`

Retorna un odontólogo específico según su ID.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
{
  "id": 1,
  "nombre": "Laura",
  "apellido": "García",
  "matricula": "ABC123"
}
```

#### Agregar un nuevo odontólogo

**POST** `/api/v1/odontologos`

Crea un nuevo odontólogo.

**Cuerpo de la petición:**

```json
{
  "nombre": "Laura",
  "apellido": "García",
  "matricula": "ABC123"
}
```

**Respuesta exitosa:**

- **Código:** `201 Created`
- **Cuerpo:**

```json
{
  "id": 1,
  "nombre": "Laura",
  "apellido": "García",
  "matricula": "ABC123"
}
```

#### Modificar un odontólogo

**PUT** `/api/v1/odontologos/{odontologoId}`

Actualiza la información de un odontólogo existente.

**Cuerpo de la petición:**

```json
{
  "nombre": "Laura",
  "apellido": "García",
  "matricula": "XYZ456"
}
```

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
{
  "id": 1,
  "nombre": "Laura",
  "apellido": "García",
  "matricula": "XYZ456"
}
```

#### Eliminar un odontólogo

**DELETE** `/api/v1/odontologos/{odontologoId}`

Elimina un odontólogo por su ID, junto con los turnos asociados a él.

**Respuesta exitosa:**

- **Código:** `204 No Content`
- **Cuerpo:** (Ninguno)

### Endpoints Pacientes

#### Listar todos los pacientes

**GET** `/api/v1/pacientes`

Retorna una lista de todos los pacientes registrados.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
[
  {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pérez",
    "dni": "12345678",
    "fechaAlta": "2023-09-01",
    "domicilio": {
      "id": 1,
      "calle": "Calle Falsa",
      "numero": 123,
      "localidad": "Ciudad",
      "provincia": "Provincia"
    }
  }
]
```

#### Buscar paciente por ID

**GET** `/api/v1/pacientes/{pacienteId}`

Retorna un paciente específico según su ID.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "dni": "12345678",
  "fechaAlta": "2023-09-01",
  "domicilio": {
    "id": 1,
    "calle": "Calle Falsa",
    "numero": 123,
    "localidad": "Ciudad",
    "provincia": "Provincia"
  }
}
```

#### Buscar paciente por DNI

**GET** `/api/v1/pacientes/search?dni={pacienteDni}`

Busca un paciente por su número de DNI.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "dni": "12345678",
  "fechaAlta": "2023-09-01",
  "domicilio": {
    "id": 1,
    "calle": "Calle Falsa",
    "numero": 123,
    "localidad": "Ciudad",
    "provincia": "Provincia"
  }
}
```

#### Agregar un nuevo paciente

**POST** `/api/v1/pacientes`

Crea un nuevo paciente.

**Cuerpo de la petición:**

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "dni": "12345678",
  "domicilio": {
    "calle": "Calle Falsa",
    "numero": 123,
    "localidad": "Ciudad",
    "provincia": "Provincia"
  }
}
```

**Respuesta exitosa:**

- **Código:** `201 Created`
- **Cuerpo:**

```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "dni": "12345678",
  "fechaAlta": "2023-09-01",
  "domicilio": {
    "id": 1,
    "calle": "Calle Falsa",
    "numero": 123,
    "localidad": "Ciudad",
    "provincia": "Provincia"
  }
}
```

#### Modificar un paciente

**PUT** `/api/v1/pacientes/{pacienteId}`

Actualiza la información de un paciente existente.

**Cuerpo de la petición:**

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "dni": "12345678",
  "domicilio": {
    "calle": "Calle Verdadera",
    "numero": 456,
    "localidad": "Nueva Ciudad",
    "provincia": "Nueva Provincia"
  }
}
```

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "dni": "12345678",
  "fechaAlta": "2023-09-01",
  "domicilio": {
    "id": 1,
    "calle": "Calle Verdadera",
    "numero": 456,
    "localidad": "Nueva Ciudad",
    "provincia": "Nueva Provincia"
  }
}
```

#### Eliminar un paciente

**DELETE** `/api/v1/pacientes/{pacienteId}`

Elimina un paciente por su ID y disponibiliza los turnos asociados a él.

**Respuesta exitosa:**

- **Código:** `204 No Content`
- **Cuerpo:** (Ninguno)

### Endpoints Turnos

Este conjunto de endpoints permite gestionar los turnos de la clínica dental.

#### Listar todos los turnos

**GET** `/api/v1/turnos`

Retorna una lista de todos los turnos registrados.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
[
  {
    "id": 1,
    "fechaHora": "2024-09-12T15:00:00",
    "odontologo": {
      "id": 1,
      "nombre": "Laura",
      "apellido": "García",
      "matricula": "ABC123"
    },
    "paciente": {
      "id": 1,
      "nombre": "Juan",
      "apellido": "Pérez",
      "dni": "12345678"
    }
  }
]
```

#### Buscar el último turno

**GET** `/api/v1/turnos/ultimo`

Retorna el último turno registrado.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
{
  "id": 1,
  "fechaHora": "2024-09-12T15:00:00",
  "odontologo": {
    "id": 1,
    "nombre": "Laura",
    "apellido": "García",
    "matricula": "ABC123"
  },
  "paciente": {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pérez",
    "dni": "12345678"
  }
}
```

#### Crear turnos para un odontólogo

**POST** `/api/v1/turnos?odontologo={odontologoId}`

Crea 3 turnos de fecha y hora consecutivos para un odontólogo especificado.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
[
  {
    "id": 1,
    "fechaHora": "2024-09-12T15:00:00",
    "odontologo": {
      "id": 1,
      "nombre": "Laura",
      "apellido": "García",
      "matricula": "ABC123"
    },
    "paciente": null
  }
]
```

#### Asignar un turno a un paciente

**PUT** `/api/v1/turnos/{turnoId}?paciente={pacienteId}`

Asigna un turno existente a un paciente, y deja de listarlo como disponible.

**Respuesta exitosa:**

- **Código:** `200 OK`
- **Cuerpo:**

```json
{
  "id": 1,
  "fechaHora": "2024-09-12T15:00:00",
  "odontologo": {
    "id": 1,
    "nombre": "Laura",
    "apellido": "García",
    "matricula": "ABC123"
  },
  "paciente": {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pérez",
    "dni": "12345678"
  }
}
```
