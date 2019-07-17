Vue.component('warmer-comment-view', {
    props: ['data'],
    data: function () {
        return {
            emojishow:false,
            emojiItems:[],
            content: '',
            contentFormatStr: '',
            emojiimageList:['angry', 'anguished', "astonished", "disappointed",
                "blush", "bowtie", "zan", "cold_sweat", "confounded", "confused",
                "cry", "crying_cat_face",
                "relieved", "satisfied", "point_up_2",
                "relaxed", "scream", "scream_cat", "see_no_evil",
                "dizzy_face", "expressionless", "fearful",
                "flushed", "frowning", "full_moon_with_face",
                "grin", "grinning", "heart_eyes", "heart_eyes_cat",
                "hushed", "innocent", "joy", "joy_cat",
                "kissing", "kissing_cat", "kissing_heart",
                "neutral_face", "open_mouth", "pensive", "persevere",
                "rage", "pouting_cat", "sleeping", "sleepy", "smile",
                "kissing_smiling_eyes", "laughing", "mask", "smile_cat", "smiling_imp", "smirk",
                "smiley", "smirk_cat", "sob", "stuck_out_tongue", "v", "weary", "wink", "worried", "yum",
                "sweat_smile", "sweat", "triumph", "unamused",
                "heart", "heavy_exclamation_mark",
                "stuck_out_tongue_closed_eyes", "stuck_out_tongue_winking_eye", "sun_with_face",
                "sunglasses", "tired_face",
                "pill", "point_up", "point_down",
                "point_left", "point_right", "poop", "pray", "raised_hands",
                "tada","balloon", "bicyclist", "beer",
                "bomb", "bouquet", "broken_heart", "clap", "crown", "fire", "ghost", "gift", "gift_heart"],
            commentData:{
                useruuid:"",
                touseruuid:"",
                uuid:'',
                content:'',
            }
        };
    },
    filters: {

    },
    computed: {

    },
    created(){
        this.initemoji();
        this.commentData.uuid=this.data.id;
    },
    mounted() {
        var _this=this;

    },
    methods: {
        initemoji(){
            var _this=this;
            this.emojiimageList.forEach(function(m){
                var tag='<span class="emoji emoji_'+m+'"></span>';
                _this.emojiItems.push(tag);
            })
        },
        choose() {
            this.emojishow=!this.emojishow;
        },
        chooseclose(event){
            if(event.target.id!=="choose"&&event.target.className.indexOf("emojic")==-1){
                this.emojishow=false;
            }
        },
        select(m){
            this.content+='['+m+']';
        },
        checkExist(item){
            return this.emojiItems.indexOf(item)>-1;
        },
        contentformat(str){
            var list = str.match(/\[*\w*\]/g);
            var filter = /[\[\]]/g;
            if(list){
                for(var i=0;i<list.length;i++){
                    var item=list[i];
                    var title= item.replace(filter,'');
                    var tag='<span class="emoji emoji_'+title+'"></span>';
                    if(this.checkExist(tag)){
                        str=str.replace(item,tag);
                    }
                }
            }
            return str;
        },
        savecomment(){
            var _this = this;
            _this.commentData.content=_this.content;
            var data=_this.commentData;
            $.ajax({
                type: "POST",
                url: "/comment/addComment",
                data: data,
                success: function (result) {
                    if (result.code == 0) {

                    }
                }
            });
        },
        getCommentlist(){
            var _this = this;
            $.ajax({
                type: "POST",
                url: "/comment/addComment",
                data: {uuid:_this.commentData.uuid},
                success: function (result) {
                    if (result.code == 0) {

                    }
                }
            });
        }
    },
    watch:{

    },
    template:
        `
        <div style="padding: 20px">
                <div id="comment-list" class="comment-list">
                    <div @click="chooseclose($event)">
                        <form class="new-comment">
                            <a class="avatar">
                                <img src="//upload.jianshu.io/users/upload_avatars/13381354/d0afebae-d62c-4c78-a538-093647bd833e?imageMogr2/auto-orient/strip|imageView2/1/w/114/h/114/format/webp">
                            </a>
                            <textarea placeholder="写下你的评论..." v-model="content"  contenteditable="true"></textarea>
                            <div class="write-function-block">
                                <div class="emoji-modal-wrap">
                                    <span class="expression fl" @click="choose" id="choose"></span>
                                </div>
                                <a class="btn btn-send" @click="savecomment">发送</a>
                            </div>
                        </form>
                        <div  class="emojicontainer emojic" v-show="emojishow&&emojiimageList.length>0">
                            <ul class="emojic">
                                <li v-for="m in emojiimageList" class="emojic">
                                    <span @click="select(m)"  :class="'emoji emoji_'+m"></span>
                                </li>
                            </ul>
                        </div>
                    </div> <!---->
                    <div class="comments-placeholder" style="display: none;">
                        <div class="author">
                            <div class="avatar"></div>
                            <div class="info">
                                <div class="name"></div>
                                <div class="meta"></div>
                            </div>
                        </div>
                        <div class="text"></div>
                        <div class="text animation-delay"></div>
                        <div class="tool-group">
                            <i class="iconfont ic-zan-active"></i>
                            <div class="zan"></div>
                            <i class="iconfont ic-list-comments"></i>
                            <div class="zan"></div>
                        </div>
                    </div>
                    <div id="normal-comment-list" class="normal-comment-list">
                        <div><!---->
                            <div>
                                <div class="top-title"><span>评论</span>
                                    <a class="close-btn" style="display: none;">关闭评论</a>
                                </div>
                                <div class="no-comment"></div>
                                <div class="text">
                                    智慧如你，不想<a style="color: orangered">发表一点想法</a>咩~
                                </div>
                            </div> <!---->
                            <div class="comments-placeholder" style="display: none;">
                                <div class="author">
                                    <div class="avatar"></div>
                                    <div class="info">
                                        <div class="name"></div>
                                        <div class="meta"></div>
                                    </div>
                                </div>
                                <div class="text"></div>
                                <div class="text animation-delay"></div>
                                <div class="tool-group"><i class="iconfont ic-zan-active"></i>
                                    <div class="zan"></div>
                                    <i class="iconfont ic-list-comments"></i>
                                    <div class="zan"></div>
                                </div>
                            </div>
                        </div>
                    </div> <!---->
                </div>
        </div>
        `
})
