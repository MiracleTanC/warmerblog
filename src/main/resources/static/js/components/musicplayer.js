Vue.component('warmer-musicplayer-view', {
    props: ['data'],
    data: function () {
        return {
            musicAudio:{},
            musicList:[],
            musicPanelShowState:false,
            errorImg : 'this.src="/images/music/a1.png"',
            isload:false
        };
    },
    filters: {

    },
    watch:{
        musicList:{
            handler:function(val,oldval){
                if(val.length>0){
                    this.musicAudio.list.add(val);
                    this.musicAudio.toggle()
                }
            },
            deep:true
        }
    },
    computed: {

    },
    created () {
        this.initMusic();
    },
    beforeDestroy () {
        document.removeEventListener("scroll", this.listenerFunction);
    },
    mounted() {
        this.initPlayer();
        this.listenerFunction();
    },
    methods: {
        showMusicPanel() {
            this.musicAudio.toggle()
        },
        listenerFunction() {
            var _this=this;
            document.addEventListener('scroll', function (e) {
                if(_this.musicAudio.paused){
                    _this.musicAudio.play()
                    _this.isload=true
                }

            }, true);
            //手指接触屏幕
            document.addEventListener("touchstart", function(e){
                startx = e.touches[0].pageX;
                starty = e.touches[0].pageY;
            }, false);
            //手指离开屏幕
            document.addEventListener("touchend", function(e) {
                var endx, endy;
                endx = e.changedTouches[0].pageX;
                endy = e.changedTouches[0].pageY;
                var direction = getDirection(startx, starty, endx, endy);
                if(direction&&direction>-1&&_this.musicAudio.paused){
                    _this.musicAudio.play()
                    _this.isload=true
                }
            }, false);
        },
        initMusic(){
            var _this=this;
            $.ajax({
                data: {},
                type: "GET",
                url: '/getmusiclist',
                success: function (result) {
                    if(result.code==0){
                        var ms=result.data.rows;
                        _this.musicList=_this.musicList.concat(ms);//合并数组push.apply
                    }
                },
                error: function (data) {}
            });
        },
        initPlayer(){
            this.musicAudio = new APlayer({
                container: document.getElementById('aplayer'),
                theme: 'red',
                fixed: true,
                loop:'all',
                //mini: true,
                //autoplay:true,
                //volume:0,
                order:'list',
                //preload:'auto',
                listFolded: true,
                listMaxHeight: 90,
                lrcType: 0,
                audio: []
            });
        }
    },
    template:
        `
        <div>
            <div @click="showMusicPanel" class="rotate audio_icon">
            <svg t="1580201294240" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2473" width="25" height="25"><path d="M839.8 64c-70.71 0.78-169.12 46.32-236.88 69.2-46.6 15.73-92.32 38.38-140.56 48.83-41.74 9-82.08 5-123.85 10.9-15 2.13-24.41 19.25-25 32.85-3 68.09 14.54 140.18 26.67 207.8-2.68 49.21-6.9 98.4-14.56 148-5.5 35.65-11.13 70.83-4.43 103.84a381.35 381.35 0 0 0-62.92 11.39c-57.61 15.4-117.48 38.31-140.19 98.42-6.42 17-8.07 32.75-6.44 47.32 2.9 53.95 43.24 104.38 108.7 114.9 105.45 16.95 259.74-52.53 251-175.55-3.38-47.39-27.07-73.67-59.35-86.8 9.16-46.45-2.33-115-4.16-139.08-3.87-51-13.07-104.6-20.15-158.29 146.72-2.09 286.82-89.37 430.54-106.87a44 44 0 0 0 10.11-2.53c-8.75 111.27-7.82 233.35-31.33 341.9a160.55 160.55 0 0 1-16.33-1.81c-14.2-2.2-27.69-1.59-42-0.74-36.26 2.16-74.24 11.55-105.32 30.69-112.76 69.42-75.56 228.92 55.54 241.35s220.56-105.33 165.8-211.06c21.71-27.3 16.61-107.71 19-128.68 10-87.75 16-175.91 23.39-263.92C902.63 229.7 952.84 62.76 839.8 64zM701.17 834.71q-68.95-137.57 37.44-134.85a179.65 179.65 0 0 1 50 2.7c8.58 13.74 16 28.09 21.33 43.65q-23.52 86.18-108.77 88.5z m-300.66-67.25c7.3 0.55 12.1-3.06 14.94-8 22.54 38.86-9.6 94.7-43.3 115.37-22.86 14-54 25.82-84.36 32.12 7.32-17.88 13.4-36.18 17.23-55.18 0.78-3.88-5.15-5.56-6-1.65a262.52 262.52 0 0 1-19.73 58.48 199.25 199.25 0 0 1-35.72 3.26 109.82 109.82 0 0 1-21.86-2.67c0.24-46 33.64-90.91 52.21-130.84 2.53-5.43-5.55-10.2-8.09-4.73-19.42 41.77-52.93 84.92-56.31 132.32-0.64-0.21-1.35-0.3-2-0.53 3.81-9.12 2.2-20-9.6-26.5-1.46-0.81-2.6-1.68-4-2.51 8-38.4 23.13-77.56 47.27-107.64 3.89-4.84-2.6-11.76-7-7-25.15 27.39-44.37 67.49-52.4 106-45.32-37.88-7.9-89.13 45.1-109.74a375 375 0 0 1 38.83-12.42 10.07 10.07 0 0 0 6.17 2.59c17.54 1.15 34.08 6.33 51 10.42a2.86 2.86 0 0 0 2.58 3.72l9.64 0.44a4.69 4.69 0 0 0 3-0.93c20.65 3.57 41.62 4.04 62.4 5.62z m435.88-540.29c-6.91-4.54-16-6.66-27.42-4.33-146.11 30-277.25 119.28-425 145.17-4.18-37.26-6.65-74.37-4.8-110.52 34-1.4 67.12-0.32 101.54-9 59.74-15.15 116.45-41.42 175.19-59.85 31.27-9.81 147.7-63.62 175.59-51.51 14.64 6.34 10.39 51.75 4.9 90.04z" fill="#ffffff" p-id="2474"></path></svg></div>
            <div id="aplayer"></div>
            <div id="musicnote">
                <div id="pdyf1" class="note">
                    <i class="fa fa-music" aria-hidden="true"></i>
                </div>
                <div id="pdyf2" class="note">
                    <i class="fa fa-music" aria-hidden="true"></i>
                </div>
                <div id="pdyf3" class="note">
                    <i class="fa fa-music" aria-hidden="true"></i>
                </div>
            </div>
    </div>
		`
})