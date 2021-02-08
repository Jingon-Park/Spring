window.onload = function () {
    console.log("load");

    $.ajax({
        url : "http://localhost:8080/test",
        type: "POST",
        data: JSON.stringify({what : "data", why : "why"}),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",
        success: function(data){
            console.log(data);
        },
        error: function(){
            alert("stringify err");
        }
    });
};