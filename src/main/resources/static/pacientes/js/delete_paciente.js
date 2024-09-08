function deleteBy(id) {
  const url = "/api/v1/pacientes/" + id;
  const settings = {
    method: "DELETE",
  };
  fetch(url, settings).then((response) => response.json());

  let row_id = "#tr_" + id;
  document.querySelector(row_id).remove();
}