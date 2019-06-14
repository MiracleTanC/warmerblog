Vue.component('warmer-record-view', {
    props: ['data','showmore','loading'],
    data: function () {
        return {
            loadingimage:'/images/loading.gif'
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

    },
    methods: {

    },
    watch:{

    },
    template:
        `
        <div>
            <div class="blogs" v-for="item in data">
                <h3 class="blogtitle"><a :href="['/detail/'+item.id]" target="_blank" v-html="item.title">{{item.title}}</a></h3>
                <span v-if="item.showStyle==1" class="blogpic">
                    <a :href="['/detail/'+item.id]" :title="item.title">
                        <img v-if="item.coverImageList&&item.coverImageList.length>0"  :src="item.coverImageList[0].coverImage" alt="">
                    </a>
                </span>
                <span v-else-if="item.showStyle==2" class="bplist">
                    <ul v-if="item.coverImageList&&item.coverImageList.length>0">
                        <li v-for="img in item.coverImageList">
                            <a :href="['/detail/'+item.id]" :title="item.title">
                                <img :src="img.coverImage" alt="">
                            </a>
                        </li>
                    </ul>
                </span>
                <span v-else-if="item.showStyle==3" class="bigpic">
                    <a :href="['/detail/'+item.id]" :title="item.title" target="_blank">
                        <img v-if="item.coverImageList&&item.coverImageList.length>0" :src="item.coverImageList[0].coverImage" alt="">
                    </a>
                </span>
                <a :href="['/detail/'+item.id]" target="_blank"><p class="blogtext" v-html="item.abstractContent">{{item.abstractContent}}</p></a>
                <div class="bloginfo">
                    <ul>
                        <li>
                            <span v-if="item.articleType" class="label label-warn">转载</span>
                            <span v-else class="label label-success">原创</span>
                        </li>
                        <li class="author">{{item.author}}</li>
                        <li class="lmname">{{item.categoryName}}</li>
                        <li class="timer">{{item.createTime}}</li>
                        <li class="view"><span>{{item.viewCount}}</span>已阅读</li>
                    </ul>
                </div>
            </div>
            <div class="loading" v-show="loading">
                <img :src="loadingimage" alt="loading" />
            </div>
            <div v-show="!showmore" style="text-align: center;">这个人很懒,只写了这么多</div>
        </div>
        `
})
