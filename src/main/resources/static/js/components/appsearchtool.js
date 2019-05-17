Vue.component('warmer-appsearchtool-view', {
    props: ['data'],
    data: function () {
        return {
            offset:300,
            offset_opacity:1200,
            scroll_top_duration:700,
            scrollTop:0,
        };
    },
    filters: {

    },
    computed: {

    },
    created(){

    },
    mounted() {
        window.addEventListener('scroll', ()=> {
            var scrollTop = document.documentElement.scrollTop;
            this.scrollTop=scrollTop;
        })
    },
    methods: {
        backToTop(){
            timer = setInterval(function () {
                var osTop = document.documentElement.scrollTop || document.body.scrollTop
                var ispeed = Math.floor(-osTop / 5)
                document.documentElement.scrollTop = document.body.scrollTop = osTop + ispeed
                if (osTop === 0) {
                    clearInterval(timer);
                }
            },30)
        }
    },
    template:
        `
        <div id="appbackTop">
            <a @click="backToTop" href="javascript:void(0)" :class="[scrollTop>offset ? 'cd-is-visible' : '', 'cd-top']">Top</a>
        </div>
		`
})