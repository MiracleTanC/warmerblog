Vue.component('warmer-applogin-view', {
    props: ['returnurl'],
    data: function () {
        return {
            form:{
                username:'',
                password:'',
                _csrf:'',
                url:''
            }
        };
    },
    filters: {

    },
    computed: {

    },
    created(){
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
            xhr.setRequestHeader("return", this.returnurl);
        });
        this.form._csrf=token;
        this.form.url=encodeURI(this.returnurl);
    },
    mounted() {

    },
    methods: {
        close(){
            this.$emit('showlogin',false)
        },
        github(){
            window.location.href='/login/github?return='+encodeURI(this.form.url);
        },
        gitee(){
            window.location.href='/login/gitee?return='+encodeURI(this.form.url);
        }
    },
    template:
        `
       			<div class="loginBox">
                    <a href="javascript:void(0)" @click="close" style="width: 20px;height: 20px;float: right;margin-right: -40px;margin-top: -15px;text-align: center;" >x</a>
				<div>
                <div class="baseLogin">
                    <ul class="sns-login">
                        <a class="mr" @click="github" href="javascript:void(0);">
                            <li class="sns github-login" > 
                                <span>Github</span> 
                            </li>
                        </a>
                        <a class="mr" @click="gitee" href="javascript:void(0);">
                            <li class="sns gitee-login" > 
                                <span>gitee</span> 
                            </li>
                        </a>
                        <a class="mr" href="javascript:void(0);">
                            <li class="sns wechat-login" > 
                                <span>微信</span> 
                            </li>
                        </a>
                        <a class="mr" href="javascript:void(0);">
                            <li class="sns qq-login" >
                                <span>QQ</span>
                            </li>
                        </a>
                    </ul>
                </div>
				</div>
			</div>
		`
})