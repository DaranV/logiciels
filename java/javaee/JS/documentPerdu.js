const RETOUR_URL = "http://localhost:8080/Projet-JavaEE/retour";


$(document).ready(() => {
    $(".btn-doc-retour").each((index, element) => {

        $(element).click(() => {
            $("#loadingGif").show();
            $("button").prop('disabled', true);
            $("#successDiv").hide();
            $("#errorDiv").hide();
            
            const docID = $("#docID-input").val();
            console.log(docID);

            //Dans docID ta l'iD du doc à rendre


             const URL = RETOUR_URL + "?docId=" + docID;



            fetch(URL)
                .then(response => response.text())
                .then((data) => {

                    if (data.toLowerCase() != "clear") {
                        $("#loadingGif").hide();
                       
                        $("button").prop('disabled', false);
                        $("#errorDiv").show();
                        $("#errorMessage").html(data);

                    } else {
                        $("#loadingGif").hide();
                        $("button").prop('disabled', false);
                        $("#successDiv").show();
                        $("#successMessage").html("Merci de nous avoir retourné ce document !");
                    }
                })




        })


    });


})
