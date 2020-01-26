Vue.component('warmer-github-view', {
    props: ['data'],
    data: function () {
        return {
            offset:300
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
    template:
        `
            <a target="_blank" href="https://github.com/MiracleTanC?tab=repositories">
				<img class="githubflag" src="http://file.miaoleyan.com/forkme_right_red_aa0000.png" alt="Fork me on GitHub">
			</a>
		`
})