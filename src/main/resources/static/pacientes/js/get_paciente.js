window.addEventListener("load", function () {
  (function () {
    const url = "/api/v1/pacientes";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => {
        if (!response.ok) {
          return response.json().then(({ mensaje }) => {
            throw new Error(mensaje || "Error, intente nuevamente");
          });
        }
        return response.json()
      })
      .then((data) => {
        for ({id: pacienteId, nombre, apellido, dni, fechaAlta, domicilio: {calle, numero, localidad, provincia}} of data) {
          const tableBody = document.getElementById("pacienteTableBody");
          const pacienteRow = tableBody.insertRow();
          const tr_id = "tr_" + pacienteId;
          pacienteRow.id = tr_id;

          const textoDomicilio = `Calle ${calle} # ${numero}, Localidad ${localidad}, Provincia ${provincia}`;

          const deleteButton =
            `<button
              id="btn_delete_${pacienteId}"
              type="button" onclick="deleteBy(${pacienteId})"
              class="btn btn-danger btn_delete">
                &times
            </button>`;

            const updateButton =
            `<button
              id="btn_id_${pacienteId}"
              type="button" onclick="findBy(${pacienteId})"
              class="btn btn-info btn_id">
                ${pacienteId}
            </button>`;

            pacienteRow.innerHTML =
            `<td>${updateButton}</td>
            <td class="td_nombre text-capitalize">${nombre}</td>
            <td class="td_apellido text-capitalize">${apellido}</td>
            <td class="td_dni">${dni}</td>
            <td class="td_fechaAlta">${fechaAlta}</td>
            <td class="td_domicilio">${textoDomicilio}</td>
            <td>${deleteButton}</td>`;
        }
      });
  })();
});
