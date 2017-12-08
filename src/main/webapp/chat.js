/**
 * Created by dxx on 2017/12/8.
 */
$(function () {
    var websocket = null;
    var friendId = parseUrl();
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/qq/ws");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        var result = JSON.parse(event.data);
        setMessageInnerHTML(result.sendUserId + ' ' + result.sendTime + '</br>' + result.msg);
    }

    //连接关闭的回调方法
    websocket.onclose = function (event) {
        setMessageInnerHTML("WebSocket连接关闭");
        console.log(event);
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

    $('#send_button').click(function () {
        var data = {};
        data['msg'] = document.getElementById('text').value;
        data['sendUserId'] = sessionStorage.getItem('userId');
        data['recvUserId'] = friendId;
        console.log(data);
        $('#text').val('');
        websocket.send(JSON.stringify(data));
        setMessageInnerHTML(data.sendUserId + ' ' + getCurrentTime() + '</br>' + data.msg);
    });
});

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

function parseUrl() {
    var url = location.href;
    var i = url.indexOf('?');
    if (i == -1)
        return;
    var querystr = url.substr(i+1);
    var arr = querystr.split('=');
    return arr[1];
}

function getCurrentTime() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}