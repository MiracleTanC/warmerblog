Vue.component('warmer-musicplayer-view', {
    props: ['data'],
    data: function () {
        return {
            musicAudio:{},
            musicList:[],
            musicPanelShowState:false,
            errorImg : 'this.src="/images/music/a1.png"',
        };
    },
    filters: {

    },
    watch:{
        musicList:{
            handler:function(val,oldval){
                if(val.length>0){
                    _this.musicAudio.audio=_this.musicAudio.audio.concat(val);
                }
            },
            deep:true
        }
    },
    computed: {

    },
    created(){
        this.initMusic();
    },
    mounted() {
        this.initPlayer();
    },
    methods: {
        showMusicPanel(){
            this.musicPanelShowState=!this.musicPanelShowState;
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
                autoplay:true,
                order:'list',
                preload:'auto',
                listFolded: true,
                listMaxHeight: 90,
                lrcType: 0,
                audio: [{
                    name: '光辉岁月',
                    artist: 'beyond',
                    url: 'http://file.miaoleyan.com/E8r%20-%20%E3%80%8AE8r%C2%A0%E9%92%A2%E7%90%B4%E6%9B%B2%E3%80%8B%E6%97%85%E8%A1%8C%E7%9A%84%E6%84%8F%E4%B9%89.mp3',
                    cover: '/images/aaa.jpg',
                    theme: '#ebd0c2'
                },
                    {
                        name: '光辉岁月2',
                        artist: 'beyond',
                        url: 'http://file.miaoleyan.com/E8r%20-%20%E3%80%8AE8r%C2%A0%E9%92%A2%E7%90%B4%E6%9B%B2%E3%80%8B%E6%97%85%E8%A1%8C%E7%9A%84%E6%84%8F%E4%B9%89.mp3',
                        cover: '/images/aaa.jpg',
                        theme: 'red'
                    },
                    {
                        name: '光辉岁月4',
                        artist: 'beyond',
                        url: 'http://file.miaoleyan.com/E8r%20-%20%E3%80%8AE8r%C2%A0%E9%92%A2%E7%90%B4%E6%9B%B2%E3%80%8B%E6%97%85%E8%A1%8C%E7%9A%84%E6%84%8F%E4%B9%89.mp3',
                        cover: '/images/aaa.jpg',
                        theme: '#00A7EB'
                    },
                ]
            });
        }
    },
    template:
        `
        <div>
            <!--<div @click="showMusicPanel" class="rotate audio_icon"></div>-->
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