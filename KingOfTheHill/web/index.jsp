<%-- 
    Document   : index
    Created on : Jul 26, 2015, 2:36:44 PM
    Author     : Shagy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="css/reset.css">
        <!-- CSS reset -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Gem style -->
        <script src="js/modernizr.js"></script>
        <!-- Modernizr -->

        <style type="text/css">
            html,
            body,
            #map-canvas {
                height: 100%;
                width: 100%;
                margin: 0;
                padding: 0;
            }
        </style>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBGh7D7tuU8uPFGbK2Og8cVz0bRC9vYkfo"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script type="text/javascript" src="js/maps.js"></script>
        <script type="text/javascript" src="js/md5-min.js"></script>

        <script type="text/javascript">
            
            window.onload = function () {
                    
                    
                    getMarkers();
                    //validation code to see State field is mandatory.  
                
            }

        </script>



        <script type="text/javascript">
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
                    url: 'http://192.168.1.135:8080/KingOfTheHill/webresources/users/register',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(postData),
                    dataType: "json", //linea fragil
                    success: function (data) {
                        alert('success register');
                        window.location.reload();
                    },
                    error: function () {
                        alert('failure');
                    }
                });
            }
        </script>

        <script type="text/javascript">
            function loginUser() {
                var paramUsername = document.getElementById("login-username").value;
                var paramPassword = document.getElementById("login-password").value;
                var paramSchool = document.getElementById("login-school").value;
                //Hash para la contraseña
                var hash = hex_md5(paramPassword);


                var postData = {
                    "username": paramUsername,
                    "password": hash,
                    "question": paramSchool
                };
                $.ajax({
                    type: 'POST',
                    url: 'http://192.168.1.135:8080/KingOfTheHill/webresources/users/login',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(postData),
                    dataType: "json", //linea fragil
                    success: function (data) {
                        alert('success login');
                        window.location.reload();
                        //Terminar
                    },
                    error: function () {
                        alert('failure');
                    }
                });
            }
        </script>


    </head>
    <body>

        <header role="banner">
            <!--<div id="cd-logo">
                <a href="#0"><img src="img/cd-logo.svg" alt="Logo"></a>
            </div> -->
            <nav class="main-nav">
                <ul>
                    <!-- inser more links here -->
                    <li><a class="cd-signin" href="#0">Sign in</a></li>
                    <li><a class="cd-signup" href="#0">Sign up</a></li>
                </ul>
            </nav>
        </header>


        <div class="cd-user-modal">
            <!-- this is the entire modal form, including the background -->
            <div class="cd-user-modal-container">
                <!-- this is the container wrapper -->
                <ul class="cd-switcher">
                    <li><a href="">Sign in</a></li>
                    <li><a href="">New account</a></li>
                </ul>

                <div id="cd-login">
                    <!-- log in form -->
                    <form class="cd-form" action="javascript:loginUser();" method="POST">
                        <p class="fieldset">
                            <label class="image-replace cd-username" for="login-username">Username</label>
                            <input class="full-width has-padding has-border" id="login-username" type="text" placeholder="Username" pattern=".{3,}"  required title="Mínimo de caracteres: 4">
                            <span class="cd-error-message">Error message here!</span>
                        </p>

                        <p class="fieldset">
                            <label class="image-replace cd-password" for="login-password">Password</label>
                            <input class="full-width has-padding has-border" id="login-password" type="text" placeholder="Password" pattern=".{6,}" required title="Mínimo de caracteres: 6">
                            <a href="#0" class="hide-password">Hide</a>
                            <span class="cd-error-message">Error message here!</span>
                        </p>

                        <p class="fieldset">
                            <label for="school" style="font-size:110%">Seleccione una Escuela:</label>
                            <select name="school" id="login-school">
                                <option value="Computacion">Computacion</option>
                                <option value="Matematica" selected>Matematica</option>
                                <option value="Fisica">Fisica</option>
                                <option value="Forestal" selected>Forestal</option>
                                <option value="Electromecanica">Electromecanica</option>
                                <option value="Produccion Indus" selected>Produccion Indus.</option>
                                <option value="Disenio Indus">Diseño Indus.</option>
                            </select>
                        </p>

                        <p class="fieldset">
                            <input type="checkbox" id="remember-me" checked>
                            <label for="remember-me">Remember me</label>
                        </p>

                        <p class="fieldset">
                            <input class="full-width" type="submit" value="Login" >
                        </p>
                    </form>

                    <p class="cd-form-bottom-message"><a href="#0">Forgot your password?</a></p>
                    <!-- <a href="#0" class="cd-close-form">Close</a> -->
                </div>
                <!-- cd-login -->

                <div id="cd-signup">
                    <!-- sign up form 
                    
                    -->
                    <form class="cd-form" action="javascript:registerUser();" method="POST">
                        <p class="fieldset">
                            <label class="image-replace cd-username" for="signup-username">Username</label>
                            <input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="Username" pattern=".{3,}"  required title="Mínimo de caracteres: 4">
                            <!--  <span class="cd-error-message">Error message here!</span> -->
                        </p>
                        <!--
                        <p class="fieldset">
                            <label class="image-replace cd-email" for="signup-email">E-mail</label>
                            <input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="E-mail" required>
                            <span class="cd-error-message">Error message here!</span>
                        </p>
                        -->

                        <p class="fieldset">
                            <label class="image-replace cd-password" for="signup-password">Password</label>
                            <input class="full-width has-padding has-border" id="signup-password" type="text" placeholder="Password" pattern=".{6,}" required title="Mínimo de caracteres: 6">
                            <a href="#0" class="hide-password">Hide</a>
                            <!--  <span class="cd-error-message">Error message here!</span> -->
                        </p>

                        <p class="fieldset">
                            <label for="question" style="font-size:110%">Security Question:</label>
                            <select name="question" id="signup-question">
                                <option value="Nombre mascota">Nombre mascota</option>
                                <option value="Curso Favorito" selected>Curso Favorito</option>
                            </select>
                        </p>

                        <p class="fieldset">
                            <label class="image-replace cd-form" for="signup-answer">Answer</label>
                            <input class="full-width has-padding has-border" id="signup-answer" type="text" placeholder="Answer" required>
                        </p>

                        <p class="fieldset">
                            <input class="full-width has-padding" type="submit" value="Create account">
                        </p>

                    </form>

                    <a href="#0" class="cd-close-form">Close</a>
                </div>
                <!-- cd-signup -->

                <div id="cd-reset-password">
                    <!-- reset password form -->
                    <p class="cd-form-message">Lost your password? Please enter your email address. You will receive a link to create a new password.</p>

                    <form class="cd-form">
                        <p class="fieldset">
                            <label class="image-replace cd-email" for="reset-email">E-mail</label>
                            <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
                            <span class="cd-error-message">Error message here!</span>
                        </p>

                        <p class="fieldset">
                            <input class="full-width has-padding" type="submit" value="Reset password">
                        </p>
                    </form>

                    <p class="cd-form-bottom-message"><a href="#0">Back to log-in</a></p>
                </div>
                <!-- cd-reset-password -->
                <a href="#0" class="cd-close-form">Close</a>
            </div>
            <!-- cd-user-modal-container -->
        </div>
        <!-- cd-user-modal -->


        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <!-- Gem jQuery -->

        <div id="map-canvas"></div>
    </body>
</html>
