<%-- 
    Document   : index
    Created on : Jul 26, 2015, 2:36:44 PM
    Author     : Shagy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="desktop">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBGh7D7tuU8uPFGbK2Og8cVz0bRC9vYkfo"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        
        <script src="js/sweetalert.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/sweetalert.css">
        <script src="js/spin.js"></script>
        <script type="text/javascript" src="js/maps.js"></script>
        <script type="text/javascript" src="js/md5-min.js"></script>
        <script type="text/javascript" src="js/sessions.js"></script>
        <script src="js/chance.js"></script>
        
        <script type="text/javascript">
            $(window).load(function () {
                isLogged();
                
                
                //if(window.sessionStorage.accessToken !=== null){
                    getMarkers();
                    getRectangles();
                //}
                
            });

        </script>

        <script type="text/javascript">
    if (/Android|webOS|iPhone|iPad|iPod|pocket|psp|kindle|avantgo|blazer|midori|Tablet|Palm|maemo|plucker|phone|BlackBerry|symbian|IEMobile|mobile|ZuneWP7|Windows Phone|Opera Mini/i.test(navigator.userAgent)){
     window.onload = function (){
         document.getElementById('menu').style.display = 'none';
         
     }
   };
        </script>

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

    </head>
    <body>    
        <header id="menu" role="banner" >
            <div id="cd-logo">
                <user id="screenName" disabled>username</user>
                <user id="screenPuntaje"  disabled> - PUNTAJE: </user>
               <user id="screenScore" style="color:#FFD700" disabled>500</user>
            </div> 
            <div id="cd-logout">
                 <logoutBtn id="btnlogout" onclick="logout()">logout</logoutBtn>
                
            </div>
            <nav class="main-nav">
                <ul>
                    <!-- inser more links here -->
                    <li><a id="menuLogin" class="cd-signin" href="#0">Sign in</a></li>
                    <li><a id="menuRegister" class="cd-signup" href="#0">Sign up</a></li>
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
                    <form class="cd-form" onSubmit="loginUser();
                return false;" method="POST">
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
                            <input class="full-width" type="submit" value="Login" id="test">
                        </p>
                    </form>

                    <p class="cd-form-bottom-message"><a href="#0">Forgot your password?</a></p>
                    <!-- <a href="#0" class="cd-close-form">Close</a> -->
                </div>
                <!-- cd-login -->

                <div id="cd-signup">
                    <!-- sign up form 
                    
                    -->
                    <form class="cd-form" onSubmit="registerUser();
                            return false;" method="POST">
                        <p class="fieldset">
                            <label class="image-replace cd-username" for="signup-username">Username</label>
                            <input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="Username" pattern=".{3,}"  required title="Mínimo de caracteres: 4">
                            <!--  <span class="cd-error-message">Error message here!</span> -->
                        </p>

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
                    <p class="cd-form-message">Lost your password? Please enter your username. Let the monkeys bring you the Security Question</p>

                    <form class="cd-form" onSubmit="forgotPassword();
                            return false;" method="POST">
                        <p class="fieldset">
                            <label class="image-replace cd-username" for="reset-user">Username</label>
                            <input class="full-width has-padding has-border" id="reset-username" type="user" placeholder="Username" pattern=".{3,}"  required title="Mínimo de caracteres: 4">

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
