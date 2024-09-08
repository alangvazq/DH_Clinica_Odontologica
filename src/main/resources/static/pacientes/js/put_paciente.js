document.getElementById("update_paciente_form").onsubmit = function (e) {
  e.preventDefault();
};

function findBy(id) {
  const URL = `/api/v1/pacientes/${id}`;
  fetch(URL, {
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        return response.json().then(({ mensaje }) => {
          throw new Error(mensaje || "Error, intente nuevamente");
        });
      }
      return response.json();
    })
    .then((data) => {
      const { domicilio, ...datosBasicos } = data;
      Object.entries({ ...datosBasicos, ...domicilio }).forEach((entry) => {
        const [id, value] = entry;
        document.getElementById(id).value = value;
      });
    })
    .catch((error) => alert(error));

  const formulario = document.querySelector("#update_paciente_form");
  const formulario_div = document.getElementById("div_paciente_updating");
  formulario_div.classList.remove("d-none");

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

    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    fetch(URL, settings)
      .then((response) => {
        if (!response.ok) {
          return response.json().then(({ mensaje }) => {
            throw new Error(mensaje || "Error, intente nuevamente");
          });
        }
        return response.json();
      })
      .then(({ domicilio, ...data }) => {
        const pacienteRow = document.getElementById(`tr_${id}`);

        ["nombre", "apellido", "dni", "fechaAlta"].forEach((propiedad) => {
          const td = pacienteRow.querySelector(`.td_${propiedad}`);
          td.textContent = data[propiedad];
        });
        pacienteRow.querySelector(".td_domicilio").textContent = `Calle ${domicilio.calle} # ${domicilio.numero}, Localidad ${domicilio.localidad}, Provincia ${domicilio.provincia}`;

        resetUploadForm();
        formulario_div.classList.add("d-none");
      })
      .catch((error) => {
        alert(error);
      });
  });

  function resetUploadForm() {
    document.querySelector("#id").value = "";
    document.querySelector("#nombre").value = "";
    document.querySelector("#apellido").value = "";
    document.querySelector("#dni").value = "";
    document.querySelector("#fechaAlta").value = "";
    document.querySelector("#calle").value = "";
    document.querySelector("#numero").value = "";
    document.querySelector("#localidad").value = "";
    document.querySelector("#provincia").value = "";
  }
}
