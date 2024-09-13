const buscarPacienteForm = document.getElementById("buscarPacienteForm");
const pacienteDniInput = document.getElementById("pacienteDni");
const datosPacienteContainer = document.getElementById("datosPaciente");
const datosPacienteTemplate = document.getElementById("datosPacienteTemplate");
const mensajeBuscarPacienteContainer = document.getElementById("respuestaBuscarPaciente");
const asignarTurnoForm = document.getElementById("asignarTurnoForm");
const turnoIdSelect = document.getElementById("turnoId");
const mensajeAsignarTurnoContainer = document.getElementById("respuestaAsignarTurno");

const URL_BASE = "/api/v1"
const URL_PACIENTES = URL_BASE + "/pacientes"
const URL_TURNOS = URL_BASE + "/turnos"

let pacienteId = null;
let turnoId = null;
let turnosDisponibles = [];

const getAlertaHTML = (message, color = "danger") => (
  `<div class="alert alert-${color} alert-dismissible">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>${message}</strong>
  </div>`
)

buscarPacienteForm.addEventListener("submit", e => {
  e.preventDefault();
  const pacienteDni = pacienteDniInput.value;

  if(!pacienteDni.trim()) {
    mensajeBuscarPacienteContainer.innerHTML = getAlertaHTML("El DNI no puede quedar en blanco")
    mensajeBuscarPacienteContainer.classList.remove("d-none");
    return;
  }

  mensajeBuscarPacienteContainer.innerHTML = "";
  mensajeBuscarPacienteContainer.classList.add("d-none");

  fetch(`${URL_PACIENTES}/search?dni=${pacienteDni}`)
    .then((response) => {
      if(!response.ok) {
        return response.json().then(({ mensaje }) => {
          throw new Error(mensaje || "Error, intente nuevamente");
        });
      }
      return response.json();
    })
    .then(({id, nombre, apellido, dni, fechaAlta, domicilio: {calle, numero, localidad, provincia}}) => {
      if(datosPacienteContainer.children.length === 0) {
        const clonPlantillaDatosPaciente = datosPacienteTemplate.content.cloneNode(true);
        datosPacienteContainer.innerHTML = "";
        datosPacienteContainer.appendChild(clonPlantillaDatosPaciente);
      }

      datosPacienteContainer.querySelector("[data-nombre]").textContent = `${nombre} ${apellido}`;
      datosPacienteContainer.querySelector("[data-dni]").textContent = dni;
      datosPacienteContainer.querySelector("[data-fechaAlta]").textContent = fechaAlta;
      datosPacienteContainer.querySelector("[data-domicilio]").textContent = `Calle ${calle} # ${numero}, Localidad ${localidad}, Provincia ${provincia}`;

      datosPacienteContainer.classList.remove("d-none");
      pacienteId = id;

      asignarTurnoForm.classList.add("d-none");
      mensajeAsignarTurnoContainer.classList.add("d-none");

      fetch(URL_TURNOS).then((response) => {
        if(!response.ok) {
          return response.json().then(({ mensaje }) => {
            throw new Error(mensaje || "Error, intente nuevamente");
          });
        }
        return response.json();
      })
      .then((turnos = []) => {
        if(!turnos.length) {
          mensajeAsignarTurnoContainer.innerHTML = getAlertaHTML("No hay turnos disponibles. Por favor, intente más tarde.", "info");
          mensajeAsignarTurnoContainer.classList.remove("d-none");
          return;
        }

        turnoIdSelect.innerHTML = `<option value="" disabled>--Por favor, escoja un turno--</option>`;
        turnos.forEach(({id, fechaHora, odontologo: {nombre, apellido}}) => {
          const option = document.createElement("option");
          option.value = id;
          option.textContent = `${fechaHora.replace("T", " ")} - ${nombre} ${apellido}`;
          turnoIdSelect.appendChild(option);
        })
        asignarTurnoForm.classList.remove("d-none");
      })
      .catch((error) => {
        turnoIdSelect.innerHTML = `<option value="" disabled>--Por favor, escoja un turno--</option>`;
        asignarTurnoForm.classList.remove("d-none");
        mensajeAsignarTurnoContainer.innerHTML = getAlertaHTML(error);
        mensajeAsignarTurnoContainer.classList.add("d-none");
      })
    }).catch((error) => {
      datosPacienteContainer.innerHTML = "";
      mensajeBuscarPacienteContainer.innerHTML = getAlertaHTML(error)
      mensajeBuscarPacienteContainer.classList.remove("d-none");
      asignarTurnoForm.classList.add("d-none");
      mensajeAsignarTurnoContainer.classList.add("d-none");
    })
})

asignarTurnoForm.addEventListener("submit", e => {
  e.preventDefault();
  turnoId = turnoIdSelect.value;

  if(!turnoId) {
    mensajeBuscarPacienteContainer.innerHTML = getAlertaHTML("Debe escoger un turno")
    mensajeBuscarPacienteContainer.classList.remove("d-none");
    return;
  }

  fetch(`${URL_TURNOS}/${turnoId}?paciente=${pacienteId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    }
  })
    .then((response) => {
      if(!response.ok) {
        return response.json().then(({ mensaje }) => {
          throw new Error(mensaje || "Error, intente nuevamente");
        });
      }
      return response.json();
    }).then(({fechaHora, paciente, odontologo}) => {
      datosPacienteContainer.innerHTML = "";
      asignarTurnoForm.classList.add("d-none");
      pacienteDniInput.value = "";

      const mensaje = `
      <p><b>¡Turno asignado con éxito!</b></p>
      <p>Paciente: ${paciente.nombre} ${paciente.apellido}</p>
      <p>Fecha y hora: ${fechaHora.replace("T", " ")}</p>
      <p>Odontólog@: ${odontologo.nombre} ${odontologo.apellido}</p>
      `
      mensajeAsignarTurnoContainer.innerHTML = getAlertaHTML(mensaje, "success");
    }).catch(error => {
      mensajeAsignarTurnoContainer.innerHTML = getAlertaHTML(error);
    }).finally(() => {
      mensajeAsignarTurnoContainer.classList.remove("d-none");
    })
})