Vue.component('warmer-record-view', {
    props: ['data','showmore','loading'],
    data: function () {
        return {
            loadingimage:'/images/loading.svg'
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
                <img :src="loadingimage">
            </div>
            <div v-show="!showmore" style="text-align: center;"><svg t="1580220073008" class="icon" viewBox="0 0 1100 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="26675" width="32" height="32"><path d="M998.4 888.685714h-138.971429c-25.6 0-43.885714-21.942857-43.885714-43.885714 0-25.6 18.285714-43.885714 43.885714-43.885714h102.4l40.228572-215.771429h-95.085714c-25.6 0-43.885714-21.942857-43.885715-43.885714 0-25.6 18.285714-43.885714 43.885715-43.885714h149.942857c14.628571 0 25.6 7.314286 32.914285 14.628571 7.314286 10.971429 10.971429 21.942857 10.971429 36.571429l-54.857143 307.2c-7.314286 18.285714-25.6 32.914286-47.542857 32.914285zM709.485714 1024H135.314286c-21.942857 0-40.228571-14.628571-43.885715-40.228571L14.628571 449.828571c-3.657143-14.628571 3.657143-25.6 10.971429-36.571428 7.314286-10.971429 21.942857-14.628571 32.914286-14.628572h727.771428c10.971429 0 25.6 7.314286 32.914286 14.628572 7.314286 10.971429 10.971429 21.942857 10.971429 36.571428l-76.8 533.942858c-3.657143 25.6-21.942857 40.228571-43.885715 40.228571zM409.6 362.057143c-3.657143 0-3.657143 0 0 0-102.4-43.885714-102.4-109.714286-102.4-113.371429 0-32.914286 25.6-58.514286 58.514286-58.514285 18.285714 0 32.914286 7.314286 43.885714 21.942857 10.971429-10.971429 29.257143-21.942857 43.885714-21.942857 29.257143 0 54.857143 25.6 54.857143 58.514285 0 3.657143 0 69.485714-98.742857 113.371429z m-259.657143-109.714286H146.285714C0 186.514286 0 95.085714 0 84.114286 0 40.228571 40.228571 0 87.771429 0 109.714286 0 135.314286 10.971429 149.942857 29.257143 164.571429 10.971429 190.171429 0 215.771429 0c43.885714 0 80.457143 36.571429 80.457142 84.114286 0 7.314286 0 102.4-142.628571 164.571428l-3.657143 3.657143z" p-id="26676" fill="#d81e06"></path></svg></div>
        </div>
        `
})
