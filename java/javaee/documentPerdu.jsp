<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/lux/bootstrap.min.css">
     <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>


    <script src="./JS/documentPerdu.js"></script>
    <title>Document perdu</title>
</head>
<body>

<div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  <a class="navbar-brand" href="./login">Bibliotheque</a>

	</nav>

<br><br><br><br>

    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">

           
                <legend>Document perdu</legend>
                <div class="form-group">
                  <label for="exampleInputEmail1" >Veuillez scanner le code bar ou saisir l'ID du document</label>
                  <input type="number" class="form-control" id="docID-input" aria-describedby="emailHelp" name="docID" >
                </div>
               <div class="alert alert-danger pb-0 pt-1 form-control" role="alert" id="errorDiv"  style="display:none" >
                    <p id="errorMessage"></p>
               </div>

                 <div class="alert alert-success pb-0 pt-1 form-control" id="successDiv" role="alert" style="display:none"  >
                    <p id="successMessage"></p>
               </div>
                <button type="submit" class="btn btn-primary btn-doc-retour float-right mt-5">Connexion</button>

                
            </div>
        </div> 
           <img id="loadingGif" src="./images/load.gif" style=" position: absolute; left: 45%; top: 50%;display:none"/>
    </div>


    
</body>
</html>