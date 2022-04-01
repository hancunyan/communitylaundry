layui.define(['table', 'form'], function (exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form;
    table.render({
        elem: '#LAY-personal-manage',
        url: "sysuser/selectUserPageInfo",
        cols: [
            [{
                type: 'checkbox'
            },{
                field: 'username',
                title: '账号',
                align: 'center'
            }, {
                field: 'mobiles',
                title: '联系电话',
                align: 'center'
            }, {
                field: 'address',
                title: '地址',
                width: 350,
                align: 'center'
            }, {
                title: '操作',
                width: 300,
                align: 'center',
                toolbar: '#table-order-user'
            }]
        ],
        page: true,
        limit: 10,
        height: 'full-80',
        where: {
            usertype:"1"
        },
        text: {
            none: '未查询到相关数据'
        },
        //渲染之后的回调
        done: function () {
        }
    });
    //表格工具监听
    table.on('tool(LAY-personal-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'editgx') {
            layer.open({
                type: 2
                , title: '重置密码'
                , content: 'resetPasswordform'
                , maxmin: false
                , area: ['460px', '290px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                        submitID = 'LAY-personal-submit',
                        submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function(data1) {
                        var field = data1.field;
                        var passStr = field.password;
                        var passStr2 = field.password2;
                        if (passStr.length < 6) {
                            layer.msg("密码长度至少需要六位，请重新输入");
                            return;
                        }
                        if (passStr != passStr2) {
                            layer.msg("两次输入的密码不一致，请重新输入");
                            return;
                        }
                        var jsonstr = {
                            "userid": data.userid,
                            "password": passStr
                        };
                        $.ajax({
                            url: 'sysuser/updatePass',
                            type: "put",
                            contentType: "application/json",
                            data: JSON.stringify(jsonstr),
                            dataType: "json",
                            success: function (data) {
                                var status = data.optStatusCode;
                                if (status == 10400) {
                                    layer.close(index); //关闭弹层
                                    layer.msg("重置密码成功", {icon: 1});
                                    table.reload('LAY-personal-manage');
                                } else {
                                    layer.msg("重置密码失败");
                                }
                            },
                            error: function () {
                                layer.msg("重置密码失败");
                            }
                        });
                    });
                    submit.trigger('click');
                }
                , success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.contents().find("#uid").val(data.id);
                    body.contents().find("#username").val(data.username);
                    body.contents().find("#username").attr("disabled", true);
                }
            });
        }
    });

    exports('personalCenterManage', {})
});
