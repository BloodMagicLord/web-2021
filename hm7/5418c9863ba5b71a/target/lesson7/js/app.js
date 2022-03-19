window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.ajax = function (request, error) {
    let abstractRequest = {
        type: "POST",
        url: "",
        dataType: "json",

        success: function(response) {
            if (response["redirect"]) {
                location.href = response["redirect"]
            }
            if (response["error"]) {
                error.text(response["error"])
            }
        }
    }

    $.extend(abstractRequest, request)
    $.ajax(abstractRequest)

    return false;
}

