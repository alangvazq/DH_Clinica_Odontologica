const URL_ULTIMO_TURNO = "/api/v1/turnos/ultimo"
const ultimoTurnoContainer = document.getElementById("ultimo-turno");

window.addEventListener("load", () => {
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
  }).catch((error) => alert(error));
})