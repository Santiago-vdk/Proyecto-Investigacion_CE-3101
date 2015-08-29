/* global swal, chance */

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
        url: 'webresources/users/login',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postData),
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {

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
            console.log("Error en solicitud de Login.");
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
    var hash2 = hex_md5(paramAnswer);
    var postData = {
        "username": paramUsername,
        "password": hash,
        "question": paramQuestion,
        "answer": hash2
    };
    $.ajax({
        type: 'POST',
        url: 'webresources/users/register',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postData),
        dataType: "text", //linea fragil
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.status === 200) {
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
            } else {

                swal({title: ":(!",
                    text: "Ese usuario ya existe! " + "Este esta libre: " + chance.word({length: 8}),
                    type: "error",
                    confirmButtonColor: "#8CD4F5",
                    confirmButtonText: "Ups, ok.",
                    closeOnConfirm: true},
                function (isConfirm) {
                    if (isConfirm) {

                    }
                });
            }
        },
        error: function () {
            console.log("Error en solicitud de register.");
        }
    });
}

function isLogged() {
    $.ajax({
        type: 'GET',
        url: 'webresources/users/auth',
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
                $('#screenName').text(jqXHR.getResponseHeader("username"));
                $('#screenScore').text(jqXHR.getResponseHeader("score"));
            }
            else {
                $('user').css('visibility', 'hidden');
                $('logoutBtn').css('visibility', 'hidden');
                $('a').css('visibility', 'visible');
                $('screenName').text("null");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 500) {
                window.sessionStorage.removeItem("accessToken");
                window.sessionStorage.removeItem("expiresIn");
                window.location.reload();
                $('user').css('visibility', 'hidden');
                $('logoutBtn').css('visibility', 'hidden');
                $('a').css('visibility', 'visible');
                $('screenName').text("null");
            } else {
                console.log("Error en solicitud de auth.");
            }
        }
    });
}


function logout() {
    $.ajax({
        type: 'GET',
        url: 'webresources/users/logout',
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
            console.log("Error en solicitud de logout.");
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
        url: 'webresources/users/forgotpassword',
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
            console.log("Error en solicitud de forgot password.");
        }
    });
}

function checkAnswer(paramUsername, inputValue) {
    var answer = inputValue;
    var hash = hex_md5(inputValue);
    var postAnswer = {
        "username": paramUsername,
        "question": "false",
        "answer": hash
    };
    $.ajax({
        type: 'POST',
        url: 'webresources/users/forgotpassword',
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

                swal({title: "Correcto, cual sera su nueva contraseña:",
                    text: data,
                    type: "input",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    animation: "slide-from-top",
                    inputPlaceholder: "Contraseña:"},
                function (inputValue) {
                    if (inputValue === false) { //Glitch
                        return false;
                    }
                    if (inputValue === "") {
                        swal.showInputError("You need to write something!");
                        return false;
                    }
                    changePassword(paramUsername, inputValue, answer);
                });
            }
        }, error: function () {
            console.log("Error en solicitud de answer.");
        }
    });
}

function changePassword(paramUsername, inputValue, answer) {
    var hash = hex_md5(inputValue);
    var hash2 = hex_md5(answer);
    var postAnswer = {
        "username": paramUsername,
        "password": hash,
        "answer": hash2
    };
    $.ajax({
        type: 'POST',
        url: 'webresources/users/setpassword',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postAnswer),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.status === 200) {
                swal({title: ":)!",
                    text: "Su contraseña fue modificada!",
                    type: "success",
                    confirmButtonColor: "#8CD4F5",
                    confirmButtonText: "Go!",
                    closeOnConfirm: true},
                function (isConfirm) {
                    if (isConfirm) {
                        window.location.reload();
                        return false;
                    }
                });
            }
        }, error: function () {
            console.log("Error en solicitud de change password.");
        }
    });
}


var INTERVALLOG = 1500;

function getLog() {
    $.ajax({
        type: 'GET',
        url: 'webresources/users/serverlog',
        //contentType: 'application/json',
        //dataType: "json", 
        beforeSend: function (xhr) {
            // Set the CSRF Token in the header for security
            var token = window.sessionStorage.accessToken;
            if (token){
                xhr.setRequestHeader('userToken', token);
            } else {
                xhr.abort();
            }
                
        },
        success: function (res, textStatus, jqXHR) {
            if(jqXHR.status !== 204){
                toastr.info(res);
                
            }
            window.setTimeout(getLog, INTERVALLOG);
        },
        error: function () {
            console.log("Error al solicitar log");
        }
    });
}
