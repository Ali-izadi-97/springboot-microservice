var SERVER_URL = "http://localhost:8000/api";
function updateMultiplication()
{
    $.ajax({
        url: SERVER_URL+"/random"
    }).then(function (data) {
        $("#attempt-form").find("input[name='result-attempt']").val("");
        $("#attempt-form").find("input[name='user-alias']").val("");
        $('.multiplication-a').empty().append(data.factorA);
        $('.multiplication-b').empty().append(data.factorB);
    });
}

$(document).ready(function () {
    updateMultiplication();

    $("#attempt-form").submit(function (event) {

        event.preventDefault();
        var a = $('.multiplication-a').text();
        var b = $('.multiplication-b').text();
        var $form = $(this);
        attempt = $form.find("input[name='result-attempt']").val();
        userAlias = $form.find("input[name='user-alias']").val();
        var data = { user: { name: userAlias}, multiplication:
                {factorA: a, factorB: b}, result: attempt};
        console.log(data)
        $.ajax({
            url: '/results',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                if(result.correct) {
                    $('.result-message').empty().append("The result is correct! Congratulations!");
                } else {
                    $('.result-message').empty().append("Oops that's not correct! But keep trying!");
                }
            }
        });
        updateMultiplication();
    });
});