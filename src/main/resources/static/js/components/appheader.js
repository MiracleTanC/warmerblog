Vue.component('warmer-appheader-view', {
    props: ['currenturl'],
    data: function () {
        return {
            currentUrl:'',//当前路由
            navList:[
                {
                    title:'首页',icon:'glyphicon glyphicon-cog',linkUrl:'/',active:false,childrens:[]
                },
                {
                    title:'知识图谱',icon:'glyphicon glyphicon-cog',linkUrl:'/kg/home',active:false,childrens:[]
                },
                {
                    title:'生活',icon:'glyphicon glyphicon-cog',linkUrl:'/about',active:false,childrens:[]
                },
                {
                    title:'get新技能',icon:'glyphicon glyphicon-cog',linkUrl:'/about',active:false,childrens:[]
                },
                {
                    title:'关于我',icon:'glyphicon glyphicon-cog',linkUrl:'/about',active:false,childrens:[]
                },
                {
                    title:'分享',icon:'glyphicon glyphicon-th-list',linkUrl:'javascript:void(0);',active:false,childrens:[
                        {title:'杨青博客',icon:'',linkUrl:'http://www.yangqq.com/',active:false,childrens:[]},
                        {title:'程序猿DD',icon:'',linkUrl:'http://blog.didispace.com/',active:false,childrens:[]},
                        {title:'editor.md',icon:'',linkUrl:'https://pandao.github.io/editor.md/',active:false,childrens:[]},
                        {title:'hAdmin',icon:'',linkUrl:'http://demo.mycodes.net/houtai/hAdmin',active:false,childrens:[]},
                    ]
                }
            ],
            isOpen:false,
            search_active:false,
            searchkeyword:'',
        };
    },
    filters: {

    },
    computed: {

    },
    created(){

    },
    mounted() {

    },
    methods: {
        selectStyle(nav){
            var _this=this;
            this.$nextTick(function () {
                _this.navList.forEach(function (item) {
                    item.active=false;
                });
                nav.active=true;
            });
        },
        outStyle(nav) {
            nav.active=false;
        },
        changeIcon(){
            this.isOpen=!this.isOpen;
        },
        searchActive() {
            this.search_active=!this.search_active;
        },
        clickNav(nav){
            nav.active=!nav.active;
        },
        searchToggle(evt){
            var obj=evt.currentTarget;
            var container = $(obj).closest('.search-wrapper');
            if(!container.hasClass('active')){
                container.addClass('active');
                evt.preventDefault();
            }
            else if(container.hasClass('active') && $(obj).closest('.input-holder').length == 0){
                container.removeClass('active');
                this.searchkeyword="";
            }else{
                this.searchResult();
            }
        },
        searchResult(){
            //父容器app
            app.queryForm.pageIndex=1;
            app.queryForm.title=this.searchkeyword.trim();
            app.initData();
            app.articleList=[];
        }
    },
    template:
        `
        <div>
		  <div class="menu">
		    <nav class="nav" id="topnav">
		      <h1 class="logo"><a href="/"><img style="width: 60px;margin-left: 50px;" src="/images/logo/logo_o.png"></a></h1>
		      <ul v-if="navList.length>0">
			      <template v-for="nav in navList">
				      <li @mouseover="selectStyle(nav)" @mouseout="outStyle(nav)">
				        <a :class="{'a_active':currentUrl==nav.linkUrl}"  :href="nav.linkUrl">{{nav.title}}</a>
				        <ul class="sub-nav" v-if="nav.childrens.length>0" v-show="nav.active">
				          <li v-for="children in nav.childrens">
				           	<a :href="children.linkUrl">{{children.title}}</a>
				          </li>
				        </ul>
				      </li>
				      </template>
		      </ul>
		      <div class="search-wrapper">
					<div class="input-holder">
						<input type="text" v-model="searchkeyword" @keyup.enter.native="searchResult" class="search-input" placeholder="想搜点什么呢..." />
					    <button class="search-icon" @click="searchToggle($event)"><span></span></button>
					</div>
					<span class="close" @click="searchToggle($event)"></span>
					<div class="result-container"></div>
			    </div>
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
