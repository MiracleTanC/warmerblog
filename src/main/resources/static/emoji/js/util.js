function addEvent(n, t, e, o) {
    n.addEventListener ? n.addEventListener(t, e, o) : n.attachEvent ? n.attachEvent("on" + t, e) : n["on" + t] = e
}

function removeEvent(n, t, e, o) {
    n.addEventListener ? n.removeEventListener(t, e, o) : n.attachEvent ? n.detachEvent("on" + t, e) : n["on" + t] = null
}

function onttt(n, t, e, o) {
    console.log("xxxxyyyx"), addEvent(n, e, function (n) {
        var n = n || window.event, e = n.target || n.srcElement;
        console.log(t), console.log(e)
    }, !0)
}

var o = $("#out"), inx = o.find("td");
onttt($("#out"), inx, "click"), addEvent($("#out"), "click", function () {
    console.log("test")
}, !0), console.log("xx");