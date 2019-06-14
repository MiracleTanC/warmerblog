$(function() {
    "use strict";
    function s() {
        return /(Android|webOS|Phone|iPad|iPod|BlackBerry|Windows Phone)/i.test(navigator.userAgent)
    }
    function e() {
        $(".y-wrap").css({
            width: $(window).width(),
            height: $(window).height()
        })
    }

    
    $(window).resize(function() {
        e()
    }).resize(),
    $("html").addClass("animate"),
    $(".y-wrap").vegas({
        overlay: !0,
        transitionDuration: 4e3,
        delay: 1e4,
        slides: [{
            src: "/images/bg/unsplash1.jpg",
            color: "#DBC9B3",
            transition: "fade2",
            transitionDuration: 1e4
        },
        {
            src: "/images/bg/unsplash3.jpg",
            color: "#F6B700",
            transition: "zoomOut",
            transitionDuration: 8e3
        },
        {
            src: "/images/bg/unsplash2.jpg",
            color: "#C47F48",
            transition: "swirlRight"
        },
        {
            src: "/images/bg/unsplash5.jpg",
            color: "#EFAF41",
            animation: "random"
        },
        {
            src: "/images/bg/unsplash4.jpg",
            color: "#CBC2B9",
            animation: "kenburns",
            transition: "swirlLeft2"
        },
        {
            src: "/images/bg/unsplash8.jpg",
            color: "#ECA24D",
            animation: "random"
        },
        {
            src: "/images/bg/unsplash6.jpg",
            color: "#B89E2F",
            animation: "random"
        },
        {
            src: "/images/bg/unsplash7.jpg",
            color: "#7BBBBB",
            animation: "random",
            transition: "swirlRight2"
        }],
        walk: function(s, e) {
            s = null,
            $(".content").find("a").css("color", e.color),
            $(".vegas-timer-progress").css("backgroundColor", e.color)
        }
    });
});
