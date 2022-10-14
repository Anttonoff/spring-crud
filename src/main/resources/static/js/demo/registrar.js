$(document).ready(function() {
    // on ready
});


async function userRegister() {
    let datos = {};
    datos.nombre = document.getElementById('name').value;
    datos.apellido = document.getElementById('lastname').value;
    datos.email = document.getElementById('email').value;
    datos.telefono = document.getElementById('phone').value;
    datos.password = document.getElementById('password').value;

    let repetirPassword = document.getElementById('repeatPassword').value;

    if (repetirPassword != datos.password) {
        alert('La contraseña que escribiste es diferente.');
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("La cuenta fue creada con exito!");
    window.location.href = 'login.html'

}