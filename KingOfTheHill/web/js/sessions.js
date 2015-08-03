/* global swal */

function loginUser() {

    var paramUsername = document.getElementById("login-username").value;
    var paramPassword = document.getElementById("login-password").value;
    var paramSchool = document.getElementById("login-school").value;
    //Hash para la contraseña
    var hash = hex_md5(paramPassword);
    
    var postData = {
        "username": paramUsername,
        "password": hash,
        "school": paramSchool
    };
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/KingOfTheHill/webresources/users/login',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postData),
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            var opts = {
                lines: 13 // The number of lines to draw
                , length: 28 // The length of each line
                , width: 14 // The line thickness
                , radius: 42 // The radius of the inner circle
                , scale: 1 // Scales overall size of the spinner
                , corners: 1 // Corner roundness (0..1)
                , color: '#000' // #rgb or #rrggbb or array of colors
                , opacity: 0.25 // Opacity of the lines
                , rotate: 0 // The rotation offset
                , direction: 1 // 1: clockwise, -1: counterclockwise
                , speed: 1 // Rounds per second
                , trail: 60 // Afterglow percentage
                , fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
                , zIndex: 2e9 // The z-index (defaults to 2000000000)
                , className: 'spinner' // The CSS class to assign to the spinner
                , top: '50%' // Top position relative to parent
                , left: '50%' // Left position relative to parent
                , shadow: false // Whether to render a shadow
                , hwaccel: false // Whether to use hardware acceleration
                , position: 'absolute' // Element positioning
            };
            var target = document.getElementById('cd-login');
            var spinner = new Spinner(opts).spin(target);

            if (jqXHR.status === 204) {
                swal({title: ":(!",
                    text: "Credenciales Invalidas ",
                    type: "error",
                    confirmButtonColor: "#8CD4F5",
                    confirmButtonText: "Ups, ok.",
                    closeOnConfirm: false},
                function (isConfirm) {
                    if (isConfirm) {
                        window.location.reload();
                    }
                });

            }
            else {
                var parsed = JSON.parse(JSON.stringify(data));

                window.sessionStorage.accessToken = parsed.access_token;
                window.sessionStorage.expiresIn = parsed.expires_in;
                window.location.reload();
            }

        },
        error: function () {
            alert('failure');
        }
    });
}

function registerUser() {
    var paramUsername = document.getElementById("signup-username").value;
    var paramPassword = document.getElementById("signup-password").value;
    var paramQuestion = document.getElementById("signup-question").value;
    var paramAnswer = document.getElementById("signup-answer").value;
    //Hash para la contraseña
    var hash = hex_md5(paramPassword);
    var postData = {
        "username": paramUsername,
        "password": hash,
        "question": paramQuestion,
        "answer": paramAnswer
    };
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/KingOfTheHill/webresources/users/register',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postData),
        dataType: "json", //linea fragil
        success: function (data) {
            //Mensaje del server
            //swal("Good job!", "You clicked the button!", "success");

            swal({title: "Registrado!",
                text: "Utilice el boton de Login!",
                type: "success",
                confirmButtonColor: "#8CD4F5",
                confirmButtonText: "Go!",
                closeOnConfirm: false},
            function (isConfirm) {
                if (isConfirm) {
                    window.location.reload();
                }
            });


        },
        error: function () {
            alert('failure');
        }
    });
}

function isLogged() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/KingOfTheHill/webresources/users/auth',
        // contentType: 'application/json',
        // dataType: "json", //linea fragril
        beforeSend: function (xhr) {
            // Set the CSRF Token in the header for security
            var token = window.sessionStorage.accessToken;
            if (token)
                xhr.setRequestHeader('userToken', token);
        },
        success: function (res, textStatus, jqXHR) {
            //El usuario si esta loggeado
            if (jqXHR.status === 202) {
                $('user').css('visibility', 'visible');
                $('logoutBtn').css('visibility', 'visible');

                $('a').css('visibility', 'hidden');
            }
            else {
                $('user').css('visibility', 'hidden');
                $('logoutBtn').css('visibility', 'hidden');

                $('a').css('visibility', 'visible');
                //Ense;o si el auth es correcto

            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status);


        }, complete: function (jqXHR, textStatus) {

        }
    });
}

function logout() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/KingOfTheHill/webresources/users/logout',
        beforeSend: function (xhr) {
            // Set the CSRF Token in the header for security
            var token = window.sessionStorage.accessToken;
            if (token)
                xhr.setRequestHeader('userToken', token);
        },
        success: function (res, textStatus, jqXHR) {
            //El usuario si esta loggeado
            if (jqXHR.status === 202) {

                window.sessionStorage.removeItem("accessToken");
                window.sessionStorage.removeItem("expiresIn");
                $('user').css('visibility', 'hidden');
                $('logoutBtn').css('visibility', 'hidden');

                $('a').css('visibility', 'visible');
                window.location.reload();


            }
            else {
                $('user').css('visibility', 'visible');
                $('logoutBtn').css('visibility', 'visible');
                $('a').css('visibility', 'hidden');
                window.location.reload();

                //Ense;o si el auth es correcto

            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status);


        }, complete: function (jqXHR, textStatus) {

        }
    });
}

function forgotPassword() {
    var paramUsername = document.getElementById("reset-username").value;
    var postData = {
        "username": paramUsername,
        "question": "true",
        "answer": ""
    };
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/KingOfTheHill/webresources/users/forgotpassword',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postData),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.status === 204) {
                swal({title: ":(!",
                    text: "Usuario Invalido ",
                    type: "error",
                    confirmButtonColor: "#8CD4F5",
                    confirmButtonText: "Ups, ok.",
                    closeOnConfirm: false},
                function (isConfirm) {
                    if (isConfirm) {
                        window.location.reload();
                    }
                });

            } else {
                swal({title: "Su Pregunta es:",
                    text: data,
                    type: "input",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    animation: "slide-from-top",
                    inputPlaceholder: "Respuesta:"},
                function (inputValue) {
                    if (inputValue === false) {
                        return false;
                    }
                    if (inputValue === "") {
                        swal.showInputError("You need to write something!");
                        return false;
                    }
                    checkAnswer(paramUsername, inputValue);
                });

            }

        },
        error: function () {
            alert('failure reset');
        }
    });
}

function checkAnswer(paramUsername, inputValue) {
    var postAnswer = {
        "username": paramUsername,
        "question": "false",
        "answer": inputValue
    };

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/KingOfTheHill/webresources/users/forgotpassword',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postAnswer),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.status === 204) {
                swal({title: ":(!",
                    text: "Respuesta Incorrecta!",
                    type: "error",
                    confirmButtonColor: "#8CD4F5",
                    confirmButtonText: "Ups, ok.",
                    closeOnConfirm: true},
                function (isConfirm) {
                    if (isConfirm) {
                        return false;
                    }
                });

            }
            else {

                swal({title: ":)!",
                    text: "Su contraseña: " + data,
                    type: "success",
                    confirmButtonColor: "#8CD4F5",
                    confirmButtonText: "Gracias!",
                    closeOnConfirm: false},
                function (isConfirm) {
                    if (isConfirm) {
                        window.location.reload();
                    }
                });
            }
        }, error: function () {
            alert('failure answer');
        }
    });

}