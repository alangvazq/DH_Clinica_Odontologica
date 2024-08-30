document.getElementById("update_odontologo_form").onsubmit = function (e) {
    e.preventDefault();
};

function findBy(id) {
    const formulario_div = document.getElementById("div_odontologo_updating");
    formulario_div.classList.remove("d-none");

    const formulario = document.querySelector('#update_odontologo_form');

    //Ante un submit del formulario se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
        console.log("hola")
        //creamos un JSON que tendrá los datos del odontólogo
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };
        //invocamos utilizando la función fetch la API peliculas con el método PUT que guardará
        //la película que enviaremos en formato JSON
        const url = '/api/v1/odontologos/' + id.toString();
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings).then((response) => response.json());
        resetUploadForm()
    });

    function resetUploadForm() {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";

    }
}

