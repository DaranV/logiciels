
const EMPRUNT_URL = "http://localhost:8080/Projet-JavaEE/emprunt";
const RETOUR_URL = "http://localhost:8080/Projet-JavaEE/retour";


$(document).ready(() => {


    $(".btn-doc").each((index, element) => {

        $(element).click(() => {

            $("#loadingGif").show();
            //On désactive tout les bouton sur la page
            $("button").prop('disabled', true);


            const docID = parseInt($(element).attr("docId"));


            const URL = EMPRUNT_URL + "?docId=" + docID;



            fetch(URL)
                .then(response => response.text())
                .then((data) => {

                    if (data.toLowerCase() != "clear") {

                        $("#loadingGif").hide();
                        alert("Une erreur est survenue.\n" + data);
                        document.location.reload(true);
                        $("button").prop('disabled', false);

                    } else {
                        //On rafraichit la page 
                        setTimeout(() => {
                            document.location.reload(true);
                        }, 1500);
                    }
                })


        })

    });




    $(".btn-doc-retour").each((index, element) => {

        $(element).click(() => {


            $("#loadingGif").show();
            $("button").prop('disabled', true);
            
            const docID = parseInt($(element).attr("docId"));
            //Dans docID ta l'iD du doc à rendre


             const URL = RETOUR_URL + "?docId=" + docID;



            fetch(URL)
                .then(response => response.text())
                .then((data) => {

                    if (data.toLowerCase() != "clear") {

                        $("#loadingGif").hide();
                        alert("Une erreur est survenue.\n Veuillez nous en excuser.");
                        $("button").prop('disabled', false);

                    } else {
                        //On rafraichit la page 
                        setTimeout(() => {
                            document.location.reload(true);
                        }, 1500);
                    }
                })




        })


    });


})
