Vue.component('warmer-musicplayer-view', {
    props: ['data'],
    data: function () {
        return {
            musicAudio:{},
            musicList:[],
            currentMusic:{
                title:'',
                singer:'',
                totaltime:'',
            },
            volumeValue:0,
            currentMusicIndex:0,
            musicPanelShowState:false,
            musicPlayState:true,
            musicPlayListShowState:false,
            errorImg : 'this.src="/images/music/a1.png"',
            firstscroll:false,
        };
    },
    filters: {

    },
    watch:{
        musicList:{
            handler:function(val,oldval){
                if(val.length>0){
                    //自动播放第一首
                    var _this=this;
                    var firstMusic=val[0];
                    //this.$refs.palya.click();
                    this.currentMusic=firstMusic;
                    this.currentMusicIndex=0;
                    this.musicAudio.src=this.currentMusic.url;
                }
            },
            deep:true//对象内部的属性监听
        }
    },
    computed: {

    },
    created(){
        this.initMusic();
    },
    mounted() {
        var _this = this;
        _this.musicAudio=_this.$refs.musicAudio;
        /*window.addEventListener('scroll', () => {
            if(!_this.firstscroll){
                _this.play();
                _this.firstscroll=true;
            }

        }, true);*/
    },
    methods: {
        initMusic(){
            var _this=this;
            $.ajax({
                data: {},
                type: "GET",
                url: '/getmusiclist',
                success: function (result) {
                    if(result.code==0){
                        var ms=result.data.rows;
                        _this.musicList.push.apply(_this.musicList,ms);//合并数组push.apply
                    }
                },
                error: function (data) {}
            });
        },
        volume(){
            if(this.musicAudio.volume===0){
                this.musicAudio.volume=this.volumeValue;
            }else{
                this.volumeValue=this.musicAudio.volume;
                this.musicAudio.volume=0;
            }
        },
        play(){
            if(this.musicAudio.paused){
                this.musicAudio.play();//播放
                this.musicPlayState=true;//播放图标
                this.$refs.titleRun.start();//走马灯开启
                if(this.musicList.length>0&&this.musicAudio.src===''){//默认播放第一首
                    this.currentMusic=this.musicList[0];
                    this.musicAudio.src=this.currentMusic.url;
                }
            }else{
                this.musicPlayState=false;//暂停播放图标
                this.musicAudio.pause();//暂停
                this.$refs.titleRun.stop();//走马灯关闭
            }
        },
        playMusic(m,index){
            this.currentMusic=m;
            this.currentMusicIndex=index;
            this.musicAudio.src=this.currentMusic.url;
            if (this.musicAudio.paused) { //判读是否播放
                this.musicAudio.paused=false;
                this.musicAudio.onloadeddata = function(){
                    console.log('播放');
                    this.musicAudio.play(); //没有就播放
                };

            }
        },
        prevMusic(){
            if(this.currentMusicIndex<=0){
                this.currentMusicIndex=this.musicList.length-1;//当前为第一首则切换至最后一首
            }else{
                this.currentMusicIndex--;
            }
            this.currentMusic=this.musicList[this.currentMusicIndex];
            this.musicAudio.src=this.currentMusic.url;
            this.musicAudio.play();
        },
        showMusicPanel(){
            this.musicPanelShowState=!this.musicPanelShowState;
        },
        showMusicPlayList(){
            this.musicPlayListShowState=!this.musicPlayListShowState;
        },
        nextMusic(){
            if(this.currentMusicIndex>=this.musicList.length-1){
                this.currentMusicIndex=0;//当前为最后一首则切换至第一首
            }else{
                this.currentMusicIndex++;
            }
            this.currentMusic=this.musicList[this.currentMusicIndex];
            this.musicAudio.src=this.currentMusic.url;
            this.musicAudio.play();
        }
    },
    template:
        `
        <div ref="musicplayer">
            
			<audio ref="musicAudio"  @ended="nextMusic" autoplay="true"></audio>
			<div @click="showMusicPanel" :class="[musicPlayState ? 'rotate' : '', 'audio_icon']"></div>
			<div class="m_player" v-show="musicPanelShowState">
				<!-- 主体 -->
				<div class="m_player_dock">
					<!-- 歌曲信息 -->
					<div class="music_icon">
						<a href="javascript:;" class="album_pic">
							<img :src="currentMusic.converUrl?currentMusic.converUrl:'/images/music/a1.png'" :onerror="errorImg"  alt="">
						</a>
					</div>
					<!-- 暂停 上一首 下一首 -->
					<div class="bar_op">
						<strong class="prev_bt" title="上一首" @click="prevMusic"></strong>
						<a ref="palya" @click="play" href="javascript:;"><strong  :class="[musicPlayState ? 'pause_bt' : 'play_bt','tc']" title="播放"@click="play"></strong></a>
						<strong class="next_bt" title="下一首" @click="nextMusic"></strong>
					</div>
					<div style="margin-left: 110px;margin-top: -15px;">
						<marquee ref="titleRun" width="150" style="color:#fff">
							{{currentMusic.title}}--{{currentMusic.singer}}
						</marquee>
					</div>
				</div>
				<!-- 隐藏显示按钮 -->
				<button @click="showMusicPanel" type="button" class="folded_bt">
					<i class="el-icon-arrow-right " style="color: #fff"></i>
				</button>
				<!-- 歌曲列表 -->
				<div class="play_list_frame" v-show="musicPlayListShowState">
					<div class="play_list_title">
						<ul>
							<li class="current"><a href="javascript:;">播放列表</a></li>
						</ul>
					</div>
					<div class="play_list">
						<div class="play_list_main">
							<div class="single_list">
								<ul v-if="musicList.length>0">
									<li :class="[currentMusicIndex==index ? 'play_current' : '', 'li']" v-for="(m,index) in musicList" @click="playMusic(m,index)">
										<strong :class="[currentMusicIndex==index ? 'play_current' : '', 'music_name line-limit-length']">
											{{m.title}}--{{m.singer}}
										</strong>
										<div class="list_cp">
											<strong class="btn_like" title="喜欢"></strong>
											<strong class="btn_share" title="分享"></strong>
											<strong class="btn_fav" title="收藏到歌单"></strong>
											<strong class="btn_del" title="从列表中删除"></strong>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>

				</div>
				<!-- 打开隐藏歌曲列表-->
				<span @click="showMusicPlayList" class="open_list">
					<i class="el-icon-tickets"></i>
				</span>
			</div>
		</div>
		`
})