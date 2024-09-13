function deleteBy(id) {
  const url = "/api/v1/pacientes/" + id;
  const settings = {
    method: "DELETE",
  };

  fetch(url, settings).then((response) => {
    if (response.status >= 400) {
      return response.json().then(({ mensaje }) => {
        throw new Error(mensaje || "Error, intente nuevamente");
      });
    }
  }).catch((error) => alert(error));

  let row_id = "#tr_" + id;
  document.querySelector(row_id).remove();
}