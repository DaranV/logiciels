<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/lux/bootstrap.min.css">
    <title>Login</title>
</head>
<body>

<div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  <a class="navbar-brand" href="#">Connexion</a>

	</nav>

<br><br><br><br>

    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">

              <form action="login" method="POST">
                <legend>Connexion</legend>
                <div class="form-group">
                  <label for="exampleInputEmail1" >Login</label>
                  <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="log" >
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1" >Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1"  name="mdp">
                </div>

                <% if(request.getAttribute("message")!= null) {%>
                  <div class="alert alert-danger my-3" role="alert"><%= request.getAttribute("message")  %></div>
                <%}%>

                <a href="./documentPerdu.jsp" class="mt-2">Retour de livre perdu </a>

                <button type="submit" class="btn btn-primary float-right mt-5">Connexion</button>
            </form>
                
            </div>
        </div> 

    </div>


    
</body>
</html>