tinymce.PluginManager.add('uploadimage', function (editor) {

    function selectLocalImages() {
        var dom = editor.dom;
        var input_f = $('<input type="file" name="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple="multiple">');
        input_f.on('change', function () {
            var form = $("<form/>",
                {
                    action: editor.settings.upload_image_url, //设置上传图片的路由，配置在初始化时
                    style: 'display:none',
                    method: 'post',
                    enctype: 'multipart/form-data'
                }
            );
            form.append(input_f);
            //ajax提交表单
            form.ajaxSubmit({
                beforeSubmit: function () {
                    return true;
                },
                success: function (data) {
                    if (data.success==1) {
                        editor.focus();
                        data.results.forEach(function (file) {
                        	console.log(file);
                            editor.selection.setContent(dom.createHTML('img', {src: file.url}));
                        })
                    }
                }
            });
        });

        input_f.click();
    }

    editor.addCommand("mceUploadImageEditor", selectLocalImages);

    editor.addButton('uploadimage', {
        icon: 'image',
        tooltip: '上传本地图片',
        onclick: selectLocalImages
    });

    editor.addMenuItem('uploadimage', {
        icon: 'image',
        text: '上传本地图片',
        context: 'tools',
        onclick: selectLocalImages
    });
});