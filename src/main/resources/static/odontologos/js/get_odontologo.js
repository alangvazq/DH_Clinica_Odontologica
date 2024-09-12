window.addEventListener("load", function () {
  (function () {
    //con fetch invocamos a la API de peliculas con el método GET
    //nos devolverá un JSON con una colección de peliculas
    const url = "/api/v1/odontologos";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        //recorremos la colección de peliculas del JSON
        for (odontologo of data) {
          //por cada pelicula armaremos una fila de la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
          var table = document.getElementById("odontologoTable");
          var odontologoRow = table.insertRow();
          let tr_id = "tr_" + odontologo.id;
          odontologoRow.id = tr_id;

          //por cada odontólogo creamos un botón delete que agregaremos en cada fila para poder eliminar el mismo
          //dicho botón invocará a la función de JavaScript deleteByKey que se encargará
          //de llamar a la API para eliminar un odontólogo
          let deleteButton =
            "<button" +
            " id=" +
            '"' +
            "btn_delete_" +
            odontologo.id +
            '"' +
            ' type="button" onclick="deleteBy(' +
            odontologo.id +
            ')" class="btn btn-danger btn_delete">' +
            "&times" +
            "</button>";

          //por cada odontólogo creamos un boton que muestra el id y que al hacerle clic invocará
          //a la función de JavaScript findBy que se encargará de buscar la odontólogo que queremos
          //modificar y mostrar los datos de la misma en un formulario.
          let updateButton =
            "<button" +
            " id=" +
            '"' +
            "btn_id_" +
            odontologo.id +
            '"' +
            ' type="button" onclick="findBy(' +
            odontologo.id +
            ')" class="btn btn-info btn_id">' +
            odontologo.id +
            "</button>";

          //armamos cada columna de la fila
          //como primer columna pondremos el botón modificar
          //luego los datos del odontólogo
          //como última columna el botón eliminar
          odontologoRow.innerHTML =
            "<td>" +
            updateButton +
            "</td>" +
            '<td class="td_nombre">' +
            odontologo.nombre.toUpperCase() +
            "</td>" +
            '<td class="td_apellido">' +
            odontologo.apellido.toUpperCase() +
            "</td>" +
            '<td class="td_matricula">' +
            odontologo.matricula +
            "</td>" +
            "<td>" +
            deleteButton +
            "</td>";
        }
      });
  })();
});
