Vue.component('warmer-appheader-view', {
    props: ['currenturl'],
    data: function () {
        return {
            currentUrl: '',//当前路由
            navList: [
                {
                    title: '首页', icon: 'glyphicon glyphicon-cog', linkUrl: '/', active: false, childrens: []
                },
                {
                    title: '知识图谱', icon: 'glyphicon glyphicon-cog', linkUrl: '/kg/home', active: false, childrens: []
                },
                {
                    title: '生活',
                    icon: 'glyphicon glyphicon-cog',
                    linkUrl: 'javascript:void(0);',
                    active: false,
                    childrens: [
                        {title: '自律', icon: '', linkUrl: 'javascript:void(0);', active: false, childrens: []},
                        {title: '美食', icon: '', linkUrl: 'javascript:void(0);', active: false, childrens: []},
                        {title: '期待', icon: '', linkUrl: 'javascript:void(0);', active: false, childrens: []}
                    ]
                },
                {
                    title: '前端组件',
                    icon: 'glyphicon glyphicon-cog',
                    linkUrl: 'javascript:void(0);',
                    active: false,
                    childrens: [
                        {title: '穿梭框', icon: '', linkUrl: '/transfer', active: false, childrens: []},
                        {title: 'scrollreveal', icon: '', linkUrl: '/scrollreveal', active: false, childrens: []},
                        {title: '3D相册', icon: '', linkUrl: '/magicphoto', active: false, childrens: []},
                        {title: '时间轴', icon: '', linkUrl: '/timeline', active: false, childrens: []},
                    ]
                },
                {
                    title: '分享',
                    icon: 'glyphicon glyphicon-th-list',
                    linkUrl: 'javascript:void(0);',
                    active: false,
                    childrens: [
                        {title: '杨青博客', icon: '', linkUrl: 'http://www.yangqq.com/', active: false, childrens: []},
                        {title: '程序猿DD', icon: '', linkUrl: 'http://blog.didispace.com/', active: false, childrens: []},
                        {
                            title: 'editor.md',
                            icon: '',
                            linkUrl: 'https://pandao.github.io/editor.md/',
                            active: false,
                            childrens: []
                        },
                        {
                            title: 'hAdmin',
                            icon: '',
                            linkUrl: 'http://demo.mycodes.net/houtai/hAdmin',
                            active: false,
                            childrens: []
                        },
                    ]
                },{
                    title: '关于我', icon: 'glyphicon glyphicon-cog', linkUrl: '/about', active: false, childrens: []
                }
            ],
            isOpen: false,
            search_active: false,
            searchkeyword: '',
            istran:true,
            issearch:false,
        };
    },
    filters: {},
    computed: {},
    created() {

    },
    mounted() {
        window.addEventListener('scroll', this.handleScroll,true);
    },
    methods: {
        switchSearch(){
            this.issearch=!this.issearch;
            if(!this.issearch){
                if(this.searchkeyword){
                    this.searchResult();
                }
            }
        },
        handleScroll() {
            var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
            if(scrollTop>56){
                this.istran=false;
            }else{
                this.istran=true;
            }
        },
        selectStyle(nav) {
            var _this = this;
            this.$nextTick(function () {
                _this.navList.forEach(function (item) {
                    item.active = false;
                });
                nav.active = true;
            });
        },
        outStyle(nav) {
            nav.active = false;
        },
        changeIcon() {
            this.isOpen = !this.isOpen;
        },
        searchActive() {
            this.search_active = !this.search_active;
        },
        clickNav(nav) {
            nav.active = !nav.active;
        },
        searchResult() {
            //父容器app
            app.queryForm.pageIndex = 1;
            app.queryForm.title = this.searchkeyword.trim();
            app.initData();
            app.articleList = [];
        }
    },
    template:
        `
        <div>
		  <div :class="[istran ? 'menu_trans' : '','menu']">
		    <nav class="nav" id="topnav">
		      <h1 class="logo"><a href="/"><img style="width: 60px;margin-left: 50px;" src="/images/logo/logo_o.png"></a></h1>
		      <ul v-if="navList.length>0">
			     <template v-for="nav in navList">
				      <li @mouseover="selectStyle(nav)" @mouseout="outStyle(nav)">
				        <a :class="{'a_active':currentUrl==nav.linkUrl}"  :href="nav.linkUrl">{{nav.title}}</a>
                            <ul class="sub-nav" v-if="nav.childrens.length>0" v-show="nav.active">
                                  <li v-for="children in nav.childrens">
                                    <a target="_blank" :href="children.linkUrl">{{children.title}}</a>
                                  </li>
                            </ul>
				      </li>
				  </template>
				  <li id="controlSearch" class="nav_menu_li">
                      <a id="switchicon" @click="switchSearch" class="navmla2 nav_menu_li_a2">              
                          <i v-if="issearch" class="fa fa-times" style="font-size: medium" aria-hidden="true"></i>
                          <i v-else class="fa fa-search"  style="font-size: medium" aria-hidden="true"></i>
                      </a>
                  </li>
		      </ul>
		      <!--search begin-->
                <div v-show="issearch" id="topsearch" class="topsearch" style="opacity: 1;">
                    <div class="intopsearch">
                        <div class="mainsearch">
                            <div id="search">
                                <input type="text" id="s" v-model="searchkeyword" @keyup.enter="searchResult" name="s" class="text" placeholder="你要找些什么...">
                                <button  @click="searchResult" class="submit">Search</button>
                            </div>
                        </div>
                    </div>
                </div>
			  <!--search end-->
		    </nav>
		    
		  </div>
		  
		 
		  <div id="mnav">
		    <h2 :class="{'open':isOpen}"><a href="/" class="mlogo">瞄了眼</a><span class="navicon" @click="changeIcon"></span></h2>
		    <dl class="list_dl" v-if="navList.length>0" v-show="isOpen">
		     <template v-for="nav in navList">
			      <dt class="list_dt">
			      	  <a @click="clickNav(nav)" :href="nav.linkUrl">{{nav.title}}</a>
			      </dt>
			      <dd class="list_dd" v-if="nav.childrens.length>0" v-show="nav.active">
			        <ul>
			          <li v-for="c in nav.childrens">
			          	<a :href="c.linkUrl">{{c.title}}</a>
			          </li>
			        </ul>
			      </dd>
		       </template>
		    </dl>
		  </div>
		</div>
		
		`
})
