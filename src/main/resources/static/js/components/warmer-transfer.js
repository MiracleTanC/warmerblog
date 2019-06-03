Vue.component('warmer-transfer', {
    props: ['sourcedata','sourcetitle','targetdata','targettitle'],
    data: function () {
        return {
            sourceList: [],
            targetList: [],
        };
    },
    filters: {},
    computed: {
        // 源数据中选中的数量
        sourceRefNum() {
            return this.sourceList.filter(item => item.isSelected).length;
        },
        // 目标数据中选中的数量
        targetRefNum() {
            return this.targetList.filter(item => item.isSelected).length;
        },
        // 选择的源记录数量
        selectSourceItemNumber() {
            return this.sourceList.filter(item => item.isSelected).length;
        },
        // 选择目标记录数量
        selectTargetItemNumber() {
            return this.targetList.filter(item => item.isSelected).length;
        },
    },
    created() {
        if(this.targetdata===null||this.targetdata.length===0){
            this.sourceList = this.formatData(val);
            return;
        }
        var source=[];
        var target=this.targetdata;
        this.sourcedata.forEach(function (item) {
            let data = target.filter(n => n.name==item.name);
            if(data==null||data.length==0){
                source.push(item);
            }
        })
        this.sourceList = this.formatData(source);
        this.targetList = this.formatData(this.targetdata);
    },
    watch: {

    },
    mounted() {

    },
    methods: {
        formatData(dataList) {
            let data = dataList.map(item => {
                return {
                    ...item,
                    isSelected: false
                };
            });
            return data;
        },
        moveItems(direction) {
            let selectedItem = this.targetList.filter(item => item.isSelected).map(item => {
                return item.name;
            });
            if (direction == 1) {//下移
                for (var i = selectedItem.length - 1; i >= 0; i--) {
                    let index = this.targetList.map(item => {
                        return item.name;
                    }).indexOf(selectedItem[i]);
                    if (index >= this.targetList.length - 1) return;
                    this.targetList[index] = this.targetList.splice(index + 1, 1, this.targetList[index])[0];
                }
            }
            if (direction == 0) {//上移
                for (var i = 0; i < selectedItem.length; i++) {
                    let index = this.targetList.map(item => {
                        return item.name;
                    }).indexOf(selectedItem[i]);
                    if (index <= 0) return;
                    this.targetList[index] = this.targetList.splice(index - 1, 1, this.targetList[index])[0];
                }
            }
            if (direction == 3) {//置底
                for (var i = 0; i < selectedItem.length; i++) {
                    let index = this.targetList.map(item => {
                        return item.name;
                    }).indexOf(selectedItem[i]);
                    if (index >= this.targetList.length - 1) return;
                    this.targetList.push(this.targetList[index]);
                    this.targetList.splice(index, 1);
                }
            }
            if (direction == 4) {//置顶
                for (var i = selectedItem.length - 1; i >= 0; i--) {
                    let index = this.targetList.map(item => {
                        return item.name;
                    }).indexOf(selectedItem[i]);
                    if (index <= 0) return;
                    this.targetList.unshift(this.targetList[index]);
                    this.targetList.splice(index + 1, 1);
                }
            }
        },
        exchange(fd, td) {
            let selectedItem = fd.filter(item => item.isSelected).map(item => {
                return {
                    ...item,
                    isSelected: false
                };
            });
            td.push(...selectedItem);
            var selectedlist = fd.filter(item => !item.isSelected);
            return selectedlist;
        },
        // 全选状态
        selectedAllStatus(type) {
            if (type == 0) {
                if (this.selectSourceItemNumber === this.sourceList.length && this.selectSourceItemNumber !== 0) {
                    return true;
                } else {
                    return false;
                }
            }
            else {
                if (this.selectTargetItemNumber === this.targetList.length && this.selectTargetItemNumber !== 0) {
                    return true;
                } else {
                    return false;
                }
            }
        },
        // 全选及反选
        toggleAll(type) {
            if (type == 0) {
                let len = this.sourceList.length;
                let slen = this.sourceList.filter(item => item.isSelected).length;
                if (len !== slen) {
                    this.sourceList.map(item => (item.isSelected = true));
                } else {
                    this.sourceList.map(item => (item.isSelected = false));
                }
            } else {
                let len = this.targetList.length;
                let slen = this.targetList.filter(item => item.isSelected).length;
                if (len !== slen) {
                    this.targetList.map(item => (item.isSelected = true));
                } else {
                    this.targetList.map(item => (item.isSelected = false));
                }
            }
        },
        // 把选择数据转移到目标（右框）
        toTarget() {
            this.sourceList = this.exchange(this.sourceList, this.targetList);
        },
        // 把选择数据转回到源（左框）
        toSource() {
            this.targetList = this.exchange(this.targetList, this.sourceList);
        }
    },
    template:
        `
        <div class="ty-transfer mt20 ml20">
            <div class="fl ty-transfer-list transfer-list-left">
                <div class="ty-transfer-list-head">
                    <div class="tyc-check-blue dib ml10">
                        <input :disabled="sourceList.length == 0" type="checkbox" @click="toggleAll(0)"
                               :checked="selectedAllStatus(0)" class="transfer-all-check">
                        <span>全选</span>
                    </div>
                    <div class="dib fs14 fw ml5">{{sourcetitle}} {{selectSourceItemNumber}}/{{sourceList.length}}</div>
                </div>
                <div class="ty-transfer-list-body">
                    <ul class="ty-tree-select">
                        <li v-for="item in sourceList">
                            <div class="ty-tree-div">
                                <label class="tyue-checkbox-wrapper">
		                            <span class="tyue-checkbox">
		                                <input type="checkbox" v-model="item.isSelected" class="tyue-checkbox-input">
		                                <span class="tyue-checkbox-circle"></span>
		                            </span>
                                    <span class="tyue-checkbox-txt">{{item.name}}</span>
                                </label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="fl ty-transfer-operation">
                <span @click="toTarget"
                      :class="[sourceList.length != 0 && sourceRefNum != 0 ? 'active' : 'disabled', 'ty-transfer-btn-toright to-switch']"></span>
                <span @click="toSource"
                      :class="[targetList.length != 0 && targetRefNum != 0 ? 'active' : 'disabled', 'ty-transfer-btn-toleft to-switch']"></span>
            </div>
            <div class="fl ty-transfer-list transfer-list-right">
                <div class="ty-transfer-list-head">
                    <div class="tyc-check-blue dib ml10">
                        <input :disabled="targetList.length == 0" type="checkbox" @click="toggleAll(1)"
                               :checked="selectedAllStatus(1)" class="transfer-all-check">
                        <span>全选</span>
                    </div>
                    <div class="dib fs14 fw ml5">{{targettitle}} {{selectTargetItemNumber}}/{{targetList.length}}</div>
                </div>
                <div class="ty-transfer-list-body">
                    <ul class="ty-tree-select">
                        <li v-for="item in targetList">
                            <div class="ty-tree-div">
                                <label class="tyue-checkbox-wrapper">
		                            <span class="tyue-checkbox">
		                                <input type="checkbox" v-model="item.isSelected" class="tyue-checkbox-input">
		                                <span class="tyue-checkbox-circle"></span>
		                            </span>
                                    <span class="tyue-checkbox-txt">{{item.name}}</span>
                                </label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="fl ty-transfer-operation four-icon">
                <span @click="moveItems(4)"
                      :class="[targetList.length != 0 && targetRefNum != 0? 'active' : 'disabled', 'ty-transfer-btn-zhiding to-switch  normal']"></span>
                <span @click="moveItems(0)"
                      :class="[targetList.length != 0 && targetRefNum != 0? 'active' : 'disabled', 'ty-transfer-btn-totop to-switch']"></span>
                <span @click="moveItems(1)"
                      :class="[targetList.length != 0 && targetRefNum != 0? 'active' : 'disabled', 'ty-transfer-btn-tobottom to-switch']"></span>
                <span @click="moveItems(3)"
                      :class="[targetList.length != 0 && targetRefNum != 0? 'active' : 'disabled', 'ty-transfer-btn-zhidi to-switch normal']"></span>
            </div>
            <div class="clearboth"></div>

        </div>
		`
})