Vue.component('warmer-comment-view', {
    props: ['data', 'user'],
    data: function () {
        return {
            emojishow: false,
            emojiItems: [],
            content: '',
            contentFormatStr: '',
            emojiimageList: ['angry', 'anguished', "astonished", "disappointed",
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
                "tada", "balloon", "bicyclist", "beer",
                "bomb", "bouquet", "broken_heart", "clap", "crown", "fire", "ghost", "gift", "gift_heart"],
            commentData: {
                useruuid: 0,
                touseruuid: 0,
                uuid: '',
                pid:0,
                content: '',
            },
            commentList: [],
            avatar: '',
            showlogin: false
        };
    },
    filters: {},
    computed: {},
    created() {
        this.initemoji();
        this.commentData.uuid = this.data.id;
        if (JSON.stringify(this.user) != "{}") {
            var key = "content_" + this.data.id;//初始化评论内容，（未登录先输入评论点发送跳转，登录成功后回显）
            var cacheContent=sessionStorage.getItem(key);
            if(cacheContent){
                var content = JSON.parse(cacheContent);
                if(content){
                    this.commentData.content = content;
                    this.content = content;
                }
            }
            this.avatar = this.user.avatar;
            this.commentData.useruuid = this.user.userId;
        }
        this.getCommentlist();
    },
    mounted() {
        if (this.content) {
            this.returnComment();
        }
    },
    methods: {
        login() {
            this.showlogin = !this.showlogin;
            this.$emit('showlogin', this.showlogin)
        },
        initemoji() {
            var _this = this;
            this.emojiimageList.forEach(function (m) {
                var tag = '<span class="emoji emoji_' + m + '"></span>';
                _this.emojiItems.push(tag);
            })
        },
        choose() {
            this.emojishow = !this.emojishow;
        },
        choosep(m) {
            m.emojishow = !m.emojishow;
        },
        select(m) {
            this.commentData.content+='[' + m + ']';
            this.emojishow=false;
        },
        selectp(p,m) {
            this.commentData.content+='[' + m + ']';
            p.emojishow = false;
        },
        checkExist(item) {
            return this.emojiItems.indexOf(item) > -1;
        },
        contentformat(str) {
            var list = str.match(/\[*\w*\]/g);
            var filter = /[\[\]]/g;
            if (list) {
                for (var i = 0; i < list.length; i++) {
                    var item = list[i];
                    var title = item.replace(filter, '');
                    var tag = '<span class="emoji emoji_' + title + '"></span>';
                    if (this.checkExist(tag)) {
                        str = str.replace(item, tag);
                    }
                }
            }
            return str;
        },
        savecomment() {
            var _this = this;
            if (JSON.stringify(_this.user) == "{}") {
                _this.showlogin = true;
                var key = "content_" + this.data.id;
                sessionStorage.setItem(key, JSON.stringify(_this.commentData.content));
                this.$emit('showlogin', _this.showlogin)
                return;
            }
            var data = _this.commentData;
            $.ajax({
                type: "POST",
                url: "/comment/addComment",
                data: data,
                success: function (result) {
                    if (result.code == 0) {
                        var key = "content_" + _this.data.id;
                        sessionStorage.removeItem(key);
                        _this.$message({
                            message: result.msg,
                            type: 'success'
                        });

                        _this.getCommentlist();
                        _this.resetData();
                    }
                }
            });
        },
        getCommentlist() {
            var _this = this;
            $.ajax({
                type: "POST",
                url: "/comment/getCommentlist",
                data: {uuid: _this.commentData.uuid},
                success: function (result) {
                    if (result.code == 0) {
                        let data =  result.data.map(item => {
                            return {
                                ...item,
                                isshownew: false
                            };
                        });
                        _this.commentList =data;
                    }
                }
            });
        },
        returnComment() {
            var returnEle = document.querySelector("#comment-list");
            if (!!returnEle) {
                returnEle.scrollIntoView(true);
            }
        },
        zan(){

        },
        reply(m){
            m.isshownew=!m.isshownew;
            if(m.isshownew){
                this.commentData={
                    useruuid: this.commentData.useruuid,
                    touseruuid: m.useruuid,
                    uuid: m.uuid,
                    pid:m.id,
                    content: '',
                }
            }else{
                this.commentData={
                    useruuid: this.commentData.useruuid,
                    touseruuid: 0,
                    uuid: '',
                    pid:0,
                    content: '',
                }
            }
        },
        resetData(){
            this.commentData={
                useruuid: this.commentData.useruuid,
                touseruuid: 0,
                uuid: '',
                pid:0,
                content: '',
            }
        },
        replyParent(p,m){
            m.isshownew=!m.isshownew;
            if(m.isshownew){
                this.commentData={
                    useruuid: this.commentData.useruuid,
                    touseruuid: m.useruuid,
                    uuid: m.uuid,
                    pid:p.id,
                    content: '',
                }
            }else{
                this.resetData();
            }
        }
    },
    watch: {
        'commentData.content':{
            handler:function(val,oldval){
                var key = "content_" + this.data.id;
                sessionStorage.setItem(key, val);
            },
            deep:true
        }
    },
    template:
        `
        <div id="comment-list" style="padding: 20px;">
            <div @click="returnComment" class="quick_comment"></div>
            <div  class="comment-list">
                <div>
                    <form class="new-comment">
                        <a v-if="avatar" class="avatar">
                            <img :src="avatar">
                        </a>
                        <p v-else>
                            点击<a href="javascript:void(0);" @click="login">登陆</a>后评论
                        </p>
                        <textarea placeholder="写下你的评论..." v-model="commentData.content"  contenteditable="true"></textarea>
                        <div class="write-function-block">
                            <div class="emoji-modal-wrap">
                            <el-popover
                                v-model="emojishow"
                                placement="right-start"
                                width="450"
                                trigger="click"
                                popper-class="emojicontainer emojic">
                                <ul >
                                    <li v-for="e in emojiimageList" >
                                        <span @click="select(e)"  :class="'emoji emoji_'+e"></span>
                                    </li>
                                </ul>
                                <span class="expression fl" slot="reference" @click="choose"></span>
                              </el-popover>
                                
                            </div>
                            <a class="btn btn-send" @click="savecomment">发送</a>
                        </div>
                    </form>
                    

                    
                </div>                    
                <div id="normal-comment-list" class="normal-comment-list">
                    <div>
                        <div v-if="commentList.length==0">
                            <div class="top-title"><span>评论</span>
                                <a class="close-btn" style="display: none;">关闭评论</a>
                            </div>
                            <div class="no-comment"></div>
                            <div class="text">
                                智慧如你，不想<a @click="returnComment" style="color: orangered">发表一点想法</a>咩~
                            </div>
                        </div>
                        <div v-else>
                            <div v-for="m in commentList" class="comment">
                                <div class="author">
                                    <div class="v-tooltip-container" style="z-index: 0;">
                                        <div class="v-tooltip-content">
                                             <a href="#" target="_blank" class="avatar">
                                                <img :src="m.useravatar">
                                             </a>
                                        </div> 
                                    </div> 
                                    <div class="info">
                                        <a href="#" target="_blank" class="name">{{m.usernickname}}</a>
                                        <div class="meta">
                                            <span>{{m.createtime}}</span>
                                        </div>
                                    </div>
                                  </div> 
                                <div class="comment-wrap">
                                    <p v-html="contentformat(m.content)"></p>
                                    <div class="tool-group">
                                    <a @click="zan" class="like-button">
                                        <span>赞</span>
                                    </a> 
                                    <a @click="reply(m)" class="">
                                        <i class="iconfont ic-comment"></i> 
                                        <span>回复</span>
                                    </a> 
                                </div>
                              </div>
                                <div v-if="m.replylist.length>0"  class="sub-comment-list">
                                    <div v-for="s in m.replylist" class="sub-comment">
                                        <p>
                                            <div class="v-tooltip-container" style="z-index: 0;">
                                                <div class="v-tooltip-content">
                                                    <a href="#" target="_blank">{{s.usernickname}}</a>：<a href="#" class="maleskine-author" target="_blank">@{{s.tousernickname}}</a> 
                                                </div> <!---->
                                           </div> 
                                           <span v-html="contentformat(s.content)"></span> 
                                        </p> 
                                        <div class="sub-tool-group">
                                            <span>{{s.createtime}}</span> 
                                            <a @click="replyParent(m,s)" class=""><i class="iconfont ic-comment"></i> <span>回复</span></a> 
                                        </div>
                                        <div class="new-comment" v-show="s.isshownew">
                                            <textarea placeholder="写下你的评论..." v-model="commentData.content"  contenteditable="true"></textarea>
                                            <div class="write-function-block">
                                                <div class="emoji-modal-wrap">
                                                   <el-popover
                                                        v-model="s.emojishow"
                                                        placement="right-start"
                                                        width="450"
                                                        trigger="click"
                                                        popper-class="emojicontainer emojic">
                                                        <ul >
                                                            <li v-for="e in emojiimageList" >
                                                                <span @click="selectp(s,e)"  :class="'emoji emoji_'+e"></span>
                                                            </li>
                                                        </ul>
                                                        <span class="expression fl" slot="reference" @click="choosep(s)"></span>
                                                      </el-popover>
                                                </div>
                                                <a class="btn btn-send" @click="savecomment">发送</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="new-comment" v-show="m.isshownew">
                                    <textarea placeholder="写下你的评论..." v-model="commentData.content"  contenteditable="true"></textarea>
                                    <div class="write-function-block">
                                        <div class="emoji-modal-wrap">
                                            <el-popover
                                                v-model="m.emojishow"
                                                placement="right-start"
                                                width="450"
                                                trigger="click"
                                                popper-class="emojicontainer emojic">
                                                <ul >
                                                    <li v-for="e in emojiimageList" >
                                                        <span @click="selectp(m,e)"  :class="'emoji emoji_'+e"></span>
                                                    </li>
                                                </ul>
                                                <span class="expression fl" slot="reference" @click="choosep(m)" id="choose"></span>
                                              </el-popover>
                                        </div>
                                        <a class="btn btn-send" @click="savecomment">发送</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        `
})
