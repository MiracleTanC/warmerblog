Vue.component('warmer-appfocus-view', {
    props: ['data'],
    data: function () {
        return {
            scrollDisable: false,
            scrollTop: 0,
            defaultfollowUsPosition: 0,
            followUsPosition: 0,
            wechatqrurl: '/images/tan_weixin_qr_1.jpg',
            qqgroupimg: '/images/qqgroup.png',
            qqgroupimged: '/images/qqgrouped.png',
            showqqimg: '/images/qqgroup.png',
            showqrcode: '',
            qqloginarr: [
                {
                    title: '说走就走的旅行',
                    url: '//shang.qq.com/wpa/qunwpa?idkey=cd43ce7a73d88cdff96bb954294a0cc8de9cc05f4c99b1646eec922745b9fb63',
                    qrcode: '/images/qrcode/travelgroup.jpg',
                },
                {
                    title: 'java/.net学习交流',
                    url: '//shang.qq.com/wpa/qunwpa?idkey=6fae314e36aeaaf7addd7da78b47f901c1a53074c89e21f033ed9b8c5ad5e55e',
                    qrcode: '/images/qrcode/learngroup.jpg',
                },
                {
                    title: '知识图谱构建与可视化',
                    url: '//shang.qq.com/wpa/qunwpa?idkey=81b65d5dcdaeb3cc423c32eb1c8b1a72480a7af853630feadd40678b702914d9',
                    qrcode: '/images/qrcode/kggroup.jpg',
                }
            ],
            currentindex: 2,
            timer: null
        };
    },
    filters: {},
    computed: {},
    created() {

    },
    mounted() {
        var _this = this;
        // 注册scroll事件并监听
        window.addEventListener('scroll', () => {
            var scrollTop = document.documentElement.scrollTop;
            _this.scrollTop = scrollTop;
            var followUsPosition = _this.$refs.followUs.offsetTop;
            _this.defaultfollowUsPosition = followUsPosition;
            if (scrollTop <= _this.defaultfollowUsPosition) {
                _this.followUsPosition = _this.defaultfollowUsPosition;
            }
        }, true);
        this.timer = setInterval(this.changeImage, 2000);
    },
    methods: {
        changeImage() {
            this.showqrcode = this.qqloginarr[this.currentindex].qrcode;
            if (this.currentindex >= 2) {
                this.currentindex = 0;
            }
            else {
                this.currentindex++;
            }
            console.log(this.currentindex);
        },
        mouseenter() {
            clearInterval(this.timer);
        },
        mouseleave() {
            this.timer = setInterval(this.changeImage, 2000);
        },
    },
    watch: {},
    template:
        `
            <div ref="followUs" :class="[scrollTop>followUsPosition ? 'gd' : '', 'guanzhu']" id="follow-us">
                <h2 class="hometitle">造作啊 骚年！</h2>
                <ul>
                    <li class="wx" @mouseenter="mouseenter" @mouseleave="mouseleave"><img :src="showqrcode"></li>
                    <li>
                        <div class="qqgroup">
                             <a v-for="(m,index) in qqloginarr" target="_blank" :href="m.url">
                                <img border="0" :src="index==currentindex?qqgroupimged:showqqimg" :alt="m.title" :title="m.title">
                             </a>
                         </div>     
                    </li>
                </ul>
		    </div>
		`
})
