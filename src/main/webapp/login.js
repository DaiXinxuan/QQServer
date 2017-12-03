/**
 * Created by dxx on 2017/11/20.
 */
$(function () {

    $("#login_button").click(function(){
        var userId = $("#login_username_input").val();
        var password = $("#login_password_input").val();
        var jsonData = {
            "userId": userId,
            "passwd": password
        };
        $.ajax({
            url:'http://localhost:8080/qq/user/login',
            type:'POST',
            'contentType' : 'application/json',
            'data' : JSON.stringify(jsonData),
            'dataType' : 'json',
            success:function(data) {
                sessionStorage.setItem("userId", userId);
                if(data.status==true){
                    location.href="./main.html";
                } else {
                    alert(data.errorMsg);
                }
            },
            error : function(data) {
                alert("网络异常");
            }
        });
    });

    $("#register_button").click(function () {
        var userId = $("#login_username_input").val();
        var passwd = $("#login_password_input").val();
        var json = {
            "userId": userId,
            "passwd": passwd
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/qq/user/register",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(json),
            dataType: "json",
            success: function(data, status) {
                if (data.status) {
                    alert("注册成功")
                } else {
                    alert(data.errorMsg);
                }
            },
            error : function(data) {
                alert("网络异常");
            }
        });
    });


});