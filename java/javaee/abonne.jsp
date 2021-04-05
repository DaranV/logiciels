<!DOCTYPE html>
<html lang="fr">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.min.css">
  
    <title>Document</title>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>


    <script src="./JS/abonne.js"></script>
     
</head>

<body>

    <%@ page import="java.util.*" %>
    <%@ page import="mediatek2020.items.*" %>
    <%@ page import="persistance.*" %>
    <% 
        List<Document> documents = (List<Document>) request.getAttribute ("documents");
        List<Document> documentsEmprunter = (List<Document>) request.getAttribute ("documentsEmprunter");
        Utilisateur user = (Abonne) request.getAttribute("user");
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

        <div class="row mt-3">
            <div class="col-md-5">

           
                <table class="table table-hover ">
                    <thead>
                        <tr>
                          <th scope="col" ></th>
                            <th scope="col">Nom</th>
                            <th scope="col">Type</th>
                            <th scope="col">Status</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Document d : documents){ 
                            Object[] data = (Object[]) d.data();
                         
                        %>
                        <tr>
                             <td style="min-width:100px"><img src="<%= (String) data[5]  %>" width="140%"></td>
                            <td><%= (String) data[1]  %></td>
                            <td><%=(String)  data[2] %></td>

                             <% if( !(Boolean) data[4]){
                                %>
                                   <td>
                                        <p>Non disponible</p>
                                    </td>
                                     <td><button class="btn btn-primary btn-doc" docId="<%= data[0] %>" disabled aria-disabled="true" >Emprunter </button></td>

                                <%}else{   %>
                                    <td>
                                        <p>Disponible</p>
                                    </td>
                                     <td><button class="btn btn-primary btn-doc" docId="<%= data[0] %>" >Emprunter </button></td>

                                <%} %>
                            </tr>

                        <% }  %>
                    </tbody>
                </table>
            </div>

             <div class="col-md-5 offset-md-2">


                <div class="card border-primary mb-3">
                    <div class="card-header">Liste des documents empruntes</div>
                </div>

                <table class="table table-hover">
                    <thead>
                        <tr>
                          <th scope="col"></th>
                            <th scope="col">Nom</th>
                            <th scope="col">Type</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Document d : documentsEmprunter){ 
                            Object[] data = (Object[]) d.data();
                         
                        %>
                        <tr>
                             <td><img src="<%= (String) data[5]  %>" width="80%"></td>
                            <td><%= (String) data[1]  %></td>
                            <td><%=(String)  data[2] %></td>
                             <td><button class="btn btn-danger btn-doc-retour" docId="<%= data[0] %>" >Rendre </button></td>

                        </tr>

                        <% }  %>
                    </tbody>
                </table>


             </div>

        </div>

        <img id="loadingGif" src="./images/load.gif" style=" position: absolute; left: 45%; top: 50%;display:none"/>

</div>

    </div>
      
</body >
</html >