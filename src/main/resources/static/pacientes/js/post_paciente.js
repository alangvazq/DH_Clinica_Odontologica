document.getElementById("add_new_paciente").onsubmit = function (e) {
  e.preventDefault();
};

window.addEventListener("load", function () {
  const formulario = document.querySelector("#add_new_paciente");

  formulario.addEventListener("submit", function (event) {
    const formData = {
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      dni: document.querySelector("#dni").value,
      fechaAlta: document.querySelector("#fechaAlta").value,
      domicilio: {
        calle: document.querySelector("#calle").value,
        numero: document.querySelector("#numero").value,
        localidad: document.querySelector("#localidad").value,
        provincia: document.querySelector("#provincia").value,
      },
    };

    const url = "/api/v1/pacientes";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
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
        let successAlert = `<div class="alert alert-success alert-dismissible">
          <button type="button" class="close" data-dismiss="alert">&times;</button>
          <strong></strong>Paciente agregado </div>`;

        document.querySelector("#response").innerHTML = successAlert;
        document.querySelector("#response").style.display = "block";
        resetUploadForm();
      })
      .catch((error) => {
        let errorAlert =
          `<div class="alert alert-danger alert-dismissible">
          <button type="button" class="close" data-dismiss="alert">&times;</button>
          <strong> ${error} </strong> </div>`;

        document.querySelector("#response").innerHTML = errorAlert;
        document.querySelector("#response").style.display = "block";
      });
  });

  function resetUploadForm() {
    document.querySelector("#nombre").value = "";
    document.querySelector("#apellido").value = "";
    document.querySelector("#dni").value = "";
    document.querySelector("#fechaAlta").value = "";
    document.querySelector("#calle").value = "";
    document.querySelector("#numero").value = "";
    document.querySelector("#localidad").value = "";
    document.querySelector("#provincia").value = "";
  }
});
