 //是否显示导航栏
 var showNavBar = true;
 //是否展开导航栏
 var expandNavBar = true;

 $(document).ready(function(){
    var h1s = $("#divcontent").find("h1");
    var h2s = $("#divcontent").find("h2");
    var h3s = $("#divcontent").find("h3");
    var h4s = $("#divcontent").find("h4");
    var h5s = $("#divcontent").find("h5");
    var h6s = $("#divcontent").find("h6");

    var headCounts = [h1s.length, h2s.length, h3s.length, h4s.length, h5s.length, h6s.length];
    var vHMaxTag = null;
    var vHMinTag = null;
    //找到文章内标题最大的和最小的
    for(var i = 0; i < headCounts.length; i++){
        if(headCounts[i] > 0){
            if(vHMaxTag == null){
                vHMaxTag = 'h' + (i + 1);
            }else{
                vHMinTag = 'h' + (i + 1);
            }
        }
    }
    if(vHMaxTag == null) return;//没有任何标题则返回

    $("body").prepend('<div class="BlogAnchor">' + 
        '<span style="color:red;position:absolute;top:-5px;left:4px;cursor:pointer;" onclick="$(\'.BlogAnchor\').hide();">×</span>' +
        '<p>' + 
            '<b id="AnchorContentToggle" title="收起" style="cursor:pointer;">目录 </b>' + 
        '</p>' + 
        '<div class="AnchorContent" > <ul id="AnchorContent"></ul></div>' +
    '</div>' );
    $("#divcontent").find("h1,h2,h3,h4,h5,h6").each(function(i,item){
        var id = '';
        var name = '';
        var tag = $(item).get(0).tagName.toLowerCase();
        id = "_"+i;
        var hindex=tag.substr(1,1);
        if(headCounts[hindex-1]==0) hindex--;
        var maxIndex=vHMaxTag.substr(1,1);
        var useIndex=parseInt(hindex)-parseInt(maxIndex);
        if(useIndex==0) useIndex=1;
        var className = 'item_h'+useIndex;
        $(item).attr("id","wow"+id);
        $(item).addClass("wow_head");
        $("#AnchorContent").css('max-height', ($(window).height() - 300) + 'px');
        $("#AnchorContent").css('min-width', ($(window).height()*0.25) + 'px');
        $("#AnchorContent").css('max-width', ($(window).height()*0.25)+80 + 'px');
        $("#AnchorContent").css('overflow-x', 'hidden');
        $("#AnchorContent").css('overflow-y', 'auto');
        $("#AnchorContent").css('text-overflow', 'ellipsis');
        $("#AnchorContent").css('white-space', 'nowrap');
        $("#AnchorContent").append('<li><a class="nav_item '+className+' anchor-link" onclick="return false;" href="#" title="'+$(this).text()+'" link="#wow'+id+'">'+$(this).text()+'</a></li>');
    });

    $("#AnchorContentToggle").click(function(){
        var text = $(this).html();
        if(text=="目录"){
            $(this).html("目录");
            $(this).attr({"title":"展开"});
        }else{
            $(this).html("目录");
            $(this).attr({"title":"收起"});
        }
        $("#AnchorContent").toggle();
    });
    $(".anchor-link").click(function(){
        $("html,body").animate({scrollTop: $($(this).attr("link")).offset().top-70}, 500);//这里减70是为了避免header的遮挡
    });

    var headerNavs = $(".BlogAnchor li .nav_item");
    var headerTops = [];
    $(".wow_head").each(function(i, n){
        headerTops.push($(n).offset().top);
    });
    $(window).scroll(function(){
        var scrollTop = $(window).scrollTop();
        $.each(headerTops, function(i, n){
            var distance = n - scrollTop;
            if(distance >= 0){
                $(".BlogAnchor li .nav_item.current").removeClass('current');
                $(headerNavs[i]).addClass('current');
                return false;
            }
        });
    });

    if(!showNavBar){
        $('.BlogAnchor').hide();
    }
    if(!expandNavBar){
        $(this).html("目录");
        $(this).attr({"title":"展开"});
        $("#AnchorContent").hide();
    }
 });
