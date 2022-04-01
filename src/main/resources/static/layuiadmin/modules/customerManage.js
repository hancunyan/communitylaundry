layui.define(['table', 'form'], function(exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form;
    table.render({
        elem: '#LAY-customer-manage',
        url:'sysuser/selectUserPageInfo',
        cols: [
            [{
                type: 'checkbox'
            }, {
                field: 'username',
                title: '用户名',
                align: 'center'
            }, {
                field: 'sex',
                title: '性别',
                align: 'center'
            }, {
                field: 'mobiles',
                title: '联系电话',
                align: 'center'
            }, {
                field: 'emails',
                title: '邮箱地址',
                align: 'center'
            }, {
                field: 'address',
                title: '地址',
                align: 'center'
            }, {
                field: 'createtime',
                title: '创建时间',
                align: 'center'
            }, {
                title: '操作',
                width: 350,
                align: 'center',
                toolbar: '#table-customer-user'
            }]
        ],
        page: true,
        limit: 10,
        height: 'full-165',
        where: {
            usertype:"2"
        },
        text: {
            none: '未查询到相关数据'
        },
        //渲染之后的回调
        done: function() {}
    });
    //表格工具监听
    table.on('tool(LAY-customer-manage)', function(obj) {
        var data = obj.data;
        if (obj.event === 'editgx') {
            layer.open({
                type: 2,
                title: '用户详细信息',
                content: 'customerForm',
                area: ['560px', '60%'],
                btn: ['确定', '取消'],
                success: function(layero, index) {
                    var body = layer.getChildFrame('body', index);
                    //回显方法
                    body.contents().find("#username").val(data.username);
                    body.contents().find("#sex").val(data.sex);
                    body.contents().find("#mobiles").val(data.mobiles);
                    body.contents().find("#emails").val(data.emails);
                    body.contents().find("#address").val(data.address);
                    body.contents().find("#createtime").val(data.createtime);
                    body.contents().find("#username").attr("disabled", true);
                    body.contents().find("#sex").attr("disabled", true);
                    body.contents().find("#mobiles").attr("disabled", true);
                    body.contents().find("#emails").attr("disabled", true);
                    body.contents().find("#address").attr("disabled", true);
                    body.contents().find("#createtime").attr("disabled", true);
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.initEditSelect(data.washingobjec);
                }
            });
        }
    });
    exports('customerManage', {})
});
