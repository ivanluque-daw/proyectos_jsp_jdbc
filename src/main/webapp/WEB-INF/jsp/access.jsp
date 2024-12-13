<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control de acceso</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" type="text/css" href="css/materialize.css" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="js/materialize.js" defer></script>
</head>
<body>
    <div class="row">
        <div class="col s12 m4">
            <div class="card">
                <div class="card-content">
                    <span class="card-title center-align">Control de acceso</span>
                    <form>
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">person</i>

                                <input id="username" type="text" class="validate" required>
                                <label for="username">Usuario</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">lock</i>

                                <input id="password" type="password" class="validate" required>
                                <label for="password">Contraseña</label>
                            </div>
                        </div>
                        <div class="row center-align">
                            <div class="col s12">
                                <button class="btn waves-effect waves-light" type="submit" name="action">
                                    Aceptar
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
