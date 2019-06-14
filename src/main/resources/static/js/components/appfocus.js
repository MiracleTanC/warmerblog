Vue.component('warmer-appfocus-view', {
    props: ['data'],
    data: function () {
        return {
            scrollDisable :false,
            scrollTop:0,
            defaultfollowUsPosition:0,
            followUsPosition:0,
            wechatqrurl:'/images/tan_weixin_qr_1.jpg'
        };
    },
    filters: {

    },
    computed: {

    },
    created(){

    },
    mounted() {
        var _this=this;
        // 注册scroll事件并监听
        window.addEventListener('scroll', ()=> {
            var scrollTop = document.documentElement.scrollTop;
            _this.scrollTop=scrollTop;
            var followUsPosition = _this.$refs.followUs.offsetTop;
            _this.defaultfollowUsPosition=followUsPosition;
            if(scrollTop<=_this.defaultfollowUsPosition ){
                _this.followUsPosition=_this.defaultfollowUsPosition;
            }
        },true);
    },
    methods: {

    },
    watch:{

    },
    template:
        `
            <div ref="followUs" :class="[scrollTop>followUsPosition ? 'gd' : '', 'guanzhu']" id="follow-us">
                <h2 class="hometitle">关注我们 么么哒！</h2>
                <ul>
                    <li class="qq"><a href="/" target="_blank"><span>QQ号</span>1130196938</a></li>
                    <li class="email"><a href="/" target="_blank"><span>邮箱帐号</span>1130196938@qq.com</a></li>
                    <li class="wx"><img :src="wechatqrurl"></li>
                </ul>
		    </div>
		`
})
