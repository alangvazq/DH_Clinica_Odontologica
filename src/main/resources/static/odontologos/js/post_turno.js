const URL_TURNOS = "/api/v1/turnos?odontologo="

function crearTurno(odontologoId) {
  fetch(`${URL_TURNOS}${odontologoId}`, {
    headers: {
      "Content-Type": "application/json"
    },
    method: "POST"
  }).then((response) => {
    if (response.status >= 400) {
      return response.json().then(({ mensaje }) => {
        throw new Error(mensaje || "Error, intente nuevamente");
      });
    }

    return response.json();
  }).then((turnos) => {
    const textAlerta = turnos.reduce((acumulador, {fechaHora, odontologo}, index) => {
      if(index === 0) {
        acumulador += `${odontologo.nombre} ${odontologo.apellido} tiene los siguientes nuevos turnos:\n`;
      }
      acumulador += `- ${fechaHora.replace("T", " ")}\n`
      return acumulador;
    }, "")

    fetch(URL_ULTIMO_TURNO).then((response) => {
      if(response.ok) return response.json();

      if(response.status === 404) {
        ultimoTurnoContainer.innerHTML = "<p>No hay turnos disponibles</p>"
        return;
      }

      return response.json().then(({ mensaje }) => {
        throw new Error(mensaje || "Error, intente nuevamente");
      });
    }).then((data) => {
      if(!data) return;
      const {odontologo: {nombre, apellido}, fechaHora} = data;
      ultimoTurnoContainer.innerHTML = `
        <p>Fecha y hora: <span>${fechaHora.replace("T", " ")}</span></p>
        <p>Odontólogo: <span>${nombre} ${apellido}</span></p>
      `;
      alert(textAlerta);
    }).catch((error) => alert(error));
  }).catch(error => alert(error));
}