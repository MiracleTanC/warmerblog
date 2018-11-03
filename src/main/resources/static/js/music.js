$(function (){
  var songs=[];
  $.ajax({
      data: {},
      type: "GET",
      url: '/getmusiclist',
      success: function (result) {
			if(result.code==0){
				var ms=result.data.rows;
				for (var i = 0; i < ms.length; i++) {
					var m=ms[i];
					var item={src:m.url,time:'',name:m.title,singer:'',album:''};
					  songs.push(item);
				}
				if(songs.length>0){
					songsUi();
					index=0;
					 changMusic();
				}
			    
			}
      },
      error: function (data) {}
  });
var initMusic=function(){
	
}
  var audio=$('audio').get(0),
  index=-1,
  volume;

  var songsUi=function () {
    //播放列表
    $.each(songs,function(i,v){
      $('<li class="li"><strong class="music_name">'+v.name+'</strong><strong class="singer_name">'+v.singer+'</strong><strong class="play_time">'+v.time+'</strong><div class="list_cp"><strong class="btn_like" title="喜欢"></strong><strong class="btn_share" title="分享"></strong><strong class="btn_fav" title="收藏到歌单"></strong><strong class="btn_del" title="从列表中删除"></strong></div></li>')
      .appendTo($('.single_list ul'));
    })
    //统计歌曲数
    $('.open_list span').text(songs.length);
  }
  //点击播放
  var changMusic=function () {
	  if(index<0) index=1;
    audio.src=songs[index].src;
    audio.play();
    $('.single_list li').removeClass('play_current');
    $('.single_list li').eq(index).addClass('play_current');
    $('.music_info_main .music_name span').text(songs[index].name);
    $('.music_info_main .singer_name').text(songs[index].singer);
    $('.music_info_main .play_date').text(songs[index].time);
  }

  $('.single_list ul').on('click','li',function () {
    index=$(this).index();
    changMusic();
  })
  //删除歌曲
  $('.single_list ul').on('click','.btn_del',function () {
    var _i=$('.single_list .btn_del').index(this);
    //正在播放切到下一首
    if($(this).closest('li').hasClass('play_current')){
      index=_i+1;
      changMusic();
    }
    songs=$.grep(songs,function (v,i) {
      return _i!==i;
    })
    $(this).closest('li').remove();
    return false;
  })
  //下一首
  $('.next_bt').on('click',function () {
    //随机播放
    if($('#btnplayway').hasClass('unordered_bt')){
      do{_i=Math.floor(Math.random()*songs.length)}
      while(index===_i)
      index=_i;
      //其他
    }else{
      index=index+1;
      if(index>=songs.length){
        index=0;
      }
    }
    changMusic();
  })
  //上一首
  $('.prev_bt').on('click',function () {
    //随机播放
    if($('#btnplayway').hasClass('unordered_bt')){
      do{_i=Math.floor(Math.random()*songs.length)}
      while(index===_i)
      index=_i;
      //其他
    }else{
      index=index-1;
      if(index<=-1){
        index=songs.length-1;
      }
    }
    changMusic();
  })
  //播放模式
  $('#btnplayway').on('click',function () {
    $('.playbar_cp_select').show();
  })

  $('.playbar_cp_select strong').on('click',function () {
    $('#btnplayway')
    .removeClass()
    .addClass($(this).attr('class'))
    .attr('title',$(this).attr('title'));
    $('.playbar_cp_select').hide();
  })

  //播放暂停
  $('.play_bt').on('click',function(){
    if(audio.paused){
      audio.play();
      //默认播放第一首
      if(audio.src===''){
        index=0;
        changMusic();
      }
    }else{
      audio.pause();
    }
  })
  $(audio).on('play',function () {
    $('.play_bt').addClass('pause_bt');
    $("#audio_btn").addClass("rotate"); //控制音乐图标 自转或暂停
  })
  $(audio).on('pause',function () {
    $('.play_bt').removeClass('pause_bt');
    if ($("#audio_btn").hasClass("rotate")) {
		  $("#audio_btn").toggleClass("rotate"); //控制音乐图标 自转或暂停
    }
    //音乐图标暂停旋转
  })
  //静音
  $('.volume_icon').on('click',function(e){
    if(audio.volume===0){
      audio.volume=volume;
    }else{
      volume=audio.volume;
      audio.volume=0;
    }
  })
  $('.volume_regulate').on('click',function (e) {
    audio.volume=e.offsetX/$(this).width();
  })
  $('.volume_op').on('click',function (e) {
    e.stopPropagation();
  })

  //拖动改变音量
  $('.volume_op').on('mousedown',function () {
    $(document).on('mousemove',function (e) {
      var regulate=$('.volume_regulate');
      e.preventDefault();
      var left=(e.clientX-regulate.offset().left-$('.volume_op').width()/2).toFixed(0);
      if(left<0||left>regulate.width()){
        return;
      }
      audio.volume=left/regulate.width();
    })
    $(document).on('mouseup',function () {
      $(this).off('mousemove');
    })
  })
  //音量界面操作
  $(audio).on('volumechange',function () {
    var percent=(this.volume/1.*100).toFixed(2)+'%';
    $('.volume_op').css('left',percent);
    $('.volume_bar').css('width',percent);
    if(this.volume===0){
      $('.volume_icon').addClass('volume_mute');
    }else{
      $('.volume_icon').removeClass('volume_mute');
    }
  })
  //播放条
  $('.play_bar').on('click',function (e) {
    if(audio.src===''){
      return;
    }
    var time=e.offsetX/$(this).width()*audio.duration;
    audio.currentTime=time;
  })
  //拖动播放条
   $('.progress_op').on('mousedown',function () {
     $(document).on('mousemove',function (e) {
       var regulate=$('.play_bar');
       e.preventDefault();
       var left=(e.clientX-$('.progress_op').width()/2).toFixed(0);

       if(left<0||left>regulate.width()){
         return;
       }
       audio.currentTime=left/regulate.width()*audio.duration;
     })
     $(document).on('mouseup',function () {
       $(this).off('mousemove');
     })
   })

  $(audio).on('timeupdate',function () {
    var percent=(audio.currentTime/audio.duration*100).toFixed(2)+'%';
    $('.play_current_bar').css('width',percent);
    $('.progress_op').css('left',percent);
  })
  //歌曲播放完之后播放下一首
  var nextsong=function () {
    //随机播放
    if($('#btnplayway').hasClass('unordered_bt')){
      do{_i=Math.floor(Math.random()*songs.length)}
      while(index===_i)
      index=_i;
      //单曲循环
    }else if($('#btnplayway').hasClass('cycle_single_bt')){
      index=index;
      //顺序播放
    }else if($('#btnplayway').hasClass('ordered_bt')){
      index=index+1;
      if(index===songs.length){
        audio.pause();
        return;
      }
      //列表循环
    }else{
      index=index+1;
      if(index===songs.length){
        index=0;
      }
    }
    changMusic();
  }
  $(audio).on('ended',nextsong)
  //打开隐藏歌曲列表
  $('.open_list').on('click',function () {
    $('.play_list_frame').toggle();
  })
  //鼠标hover显示当前位置时长
  var duration=function (time) {
    var fen=parseInt(time/60);
    var miao=time%60;
    return fen+':'+miao;
  }
  $('.play_bar').on('mouseenter mousemove',function (e) {
    var left=$(this).width()*(e.offsetX/$(this).width())-$('.time_show').width()/2;
    $('.time_show').css({'left':left,'display':'block'})
    if(audio.src===''){
      $('#time_show').text('--:--');
    }else{
      var time=parseInt(e.offsetX/$(this).width()*audio.duration);
      $('#time_show').text(duration(time))
    }
  })
  $('.play_bar').on('mouseleave',function () {
    $('.time_show').css('display','none');
  })
  
  $('#audio_btn').on('click',function () {
	    //$('.play_list_frame').toggle();
	  var ishidden=$(".m_player").is(":hidden");
	  $('.play_list_frame').hide();
	  if(ishidden){
		  $(".m_player").show();
	  }else{
		  $(".m_player").hide(); 
	  }
	 
	  })
  //其它点击打开关闭
  $('.close_list').on('click',function () {
    $('.play_list_frame').toggle();
  })
  $('.btn_lyrics_disabled').on('click',function () {
    $('.y_player_lyrics').toggle();
  })
  $('.close_lyrics').on('click',function () {
    $('.y_player_lyrics').toggle();
  })
  $('.folded_bt').on('click',function () {
    $('.play_list_frame').hide();
    $('.y_player_lyrics').hide();
/*    if(!$('.m_player').hasClass('m_player_folded')){
      $('.m_player').css('left',-540)
    }else{
      $('.m_player').css('left',0)
    }*/
    var ishidden=$(".m_player").is(":hidden");
	  if(ishidden){
		  $(".m_player").show();
	  }else{
		  $(".m_player").hide();
	  }
  /*  $('.m_player')
    .delay(400)
    .queue(function () {
      $(this).toggleClass('m_player_folded').dequeue();
    })*/
  })
})
