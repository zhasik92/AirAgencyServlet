/**
 * Created by Жасулан on 29.01.2016.
 */
$(document).ready(function () {

    var f = function (command, time) {
        //get the form data and then serialize that
        dataString = $("#myAjaxRequestForm").serialize();

        //get the form data using another method
        var depCity = $("input#depCity").val();
        var arrCity = $("input#arrCity").val();
        if (time == undefined) {
            time = '';
        }
        dataString = "command=" + command + " " + depCity + " " + arrCity + " " + time;

        //make the AJAX request, dataType is set to json
        //meaning we are expecting JSON data in response from the server
        $.ajax({
            type: "POST",
            url: "sanduri",
            data: dataString,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if (data.success) {
                    $("#ajaxResponse").html("");
                    var flight = data.flight;
                    for (var i in flight) {
                        $("#ajaxResponse").append("<b>Departure city:</b> " + flight[i].from + "<br/>");
                        $("#ajaxResponse").append("<b>Arrival city:</b> " + flight[i].to + "<br/>");
                        $("#ajaxResponse").append("<b>Departure time:</b> " + flight[i].departureTime + "<br/>");
                        $("#ajaxResponse").append("<b>Arrival time:</b> " + flight[i].arrivalTime + "<br/>");
                        $("#ajaxResponse").append("<b>Airplane type:</b> " + flight[i].airplane + "<br/>");
                        $("#ajaxResponse").append("<b>Price:</b> " + flight[i].price + " <br/>");
                        $("#ajaxResponse").append("<p></p>");
                    }
                   $("#ajaxResponse").append("<form action='buy' method='post'>");
                     $("#ajaxResponse").append("<input type='hidden' name='depCity' value='"+depCity.toString()+"'/>");
                     $("#ajaxResponse").append("<input type='hidden' name='arrCity' value='"+arrCity.toString()+"'/>");
                     $("#ajaxResponse").append("<input id='buyButton' type='button' value='buy'/>");
                     $("#ajaxResponse").append("</form>");
                    $("#buyButton").click("click", function(){
                        $.post("buy",dataString);
                    });
                    /*$("#ajaxResponse").append("<ul id='myUL'> <li>One</li> <li>Two</li> </ul>");*/
                }
                //display error message
                else {
                    $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                }
            },

            //If there was no resonse from the server
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                $("#ajaxResponse").html(jqXHR.responseText);
            },

            //capture the request before it was sent to server
            beforeSend: function (jqXHR, settings) {
                //adding some Dummy data to the request
                settings.data += "&dummyData=whatever";
                //disable the button until we get the response
                $('#myButton').attr("disabled", true);
                $('#myButton2').attr("disabled", true);
            },

            //this is called after the response or error functions are finished
            //so that we can take some action
            complete: function (jqXHR, textStatus) {
                //enable the button
                $('#myButton').attr("disabled", false);
                $('#myButton2').attr("disabled", false);
            }

        });
    };
    //Stops the submit request
    $("#myAjaxRequestForm").submit(function (e) {
        e.preventDefault();
    });
    //checks for the button click event
    $("#myButton").click(function (e) {
        var x = 'find_routes';
        f(x);
    });

    $("#myButton2").click(function (e) {
        var x = 'find_short_route';
        var time = '08:30';
        f(x, time);
    });
});