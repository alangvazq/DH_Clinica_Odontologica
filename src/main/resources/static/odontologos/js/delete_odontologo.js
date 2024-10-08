function deleteBy(id) {
  //con fetch invocamos a la API de odontólogos con el método DELETE
  //pasándole el id en la URL
  const url = "/api/v1/odontologos/" + id;
  const settings = {
    method: "DELETE",
  };

  fetch(url, settings).then((response) => {
    if (response.status >= 400) {
      return response.json().then(({ mensaje }) => {
        throw new Error(mensaje || "Error, intente nuevamente");
      });
    }

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
  .catch((error) => alert(error));

  // borrar la fila de la odontólogo eliminada
  let row_id = "#tr_" + id;
  document.querySelector(row_id).remove();
}
