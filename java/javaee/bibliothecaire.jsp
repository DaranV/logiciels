<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.min.css">
    <title>Bibliothecaire</title>
</head>

<body>
    <%@ page import="java.util.*" %>
    <%@ page import="mediatek2020.items.*" %>
    <%@ page import="persistance.*" %>
    <% 
        List<Document> documents = (List<Document>) request.getAttribute ("documents");
        Bibliothecaire user = (Bibliothecaire) request.getAttribute("user");
     %>

    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">Bibliotheque</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
                aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor01">
            </div>


            <form action="logout" method="POST">
                <button style="float:right" class="btn btn-outline-secondary my-2 my-sm-0 pull-right" type="submit">Deconnexion</button>               
            </form>


        </nav>



    </header>



    <div class="container">

        <div class="row mt-5">
            <div class="col-md-12 text-left">
                <h3>
                    Bienvenue <%= user.name()   %> 
                </h3>
            </div>

        </div>

        <div class="row">
            <div class="col-md-6 mt-5">


                <form action="./ajout" method="post">

                    <legend>Ajout de document</legend>
                    <div class="form-group">
                        <label >Nom du document</label>
                        <input type="text" class="form-control" id="name" aria-describedby="emailHelp"
                            placeholder="" name="document">
                    </div>

                    <div class="form-group">
                        <label >Url de l'image pour le document</label>
                        <input type="text" class="form-control" id="name" aria-describedby="emailHelp"
                            placeholder="" name="url">
                    </div>


                    <div class="form-group">
                        <label for="exampleSelect1">Type du document</label>
                        <select class="form-control" id="exampleSelect1" name="type">
                            <option>Livre</option>
                            <option>DVD</option>
                            <option>CD</option>
                        </select>
                    </div>


                    <button type="submit" class="btn btn-primary float-right mt-2">
                        Valider
                    </button>

                </form>

            </div>


               <div class="col-md-6">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Type</th>
                            <th scope="col">Date d'insertion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Document d : documents){ 
                            Object[] data = (Object[]) d.data();
                         
                        %>
                            <tr>
                                <th scope="row"><%= (String) data[1]  %></th>
                                <td><%= (String) data[2] %></td>
                                <td><%= (String) data[3] %></td>
                            </tr>

                        <% }  %>
                    </tbody>
                </table>
            </div>

        </div>

    </div>

</body>

</html>