function deleteBy(id) {
  //con fetch invocamos a la API de odontólogos con el método DELETE
  //pasándole el id en la URL
  const url = "/api/v1/odontologos/" + id;
  const settings = {
    method: "DELETE",
  };
  fetch(url, settings).then((response) => response.json());

  // borrar la fila de la odontólogo eliminada
  let row_id = "#tr_" + id;
  document.querySelector(row_id).remove();
}