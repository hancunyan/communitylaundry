layui.define(['table', 'form'], function(exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form;
    table.render({
        elem: '#LAY-promotion-manage',
        url:'knowledge/queryPromotion',
        cols: [
            [{
                type: 'checkbox'
            },{
                field: 'marketingactivities',
                title: '优惠活动',
                align: 'center'
            },{
                title: '操作',
                width: 350,
                align: 'center',
                toolbar: '#table-promotion-user'
            }]
        ],
        page: true,
        limit: 10,
        height: 'full-100',
        text: {
            none: '未查询到相关数据'
        },
        //渲染之后的回调
        done: function() {}
    });
    //表格工具监听
    table.on('tool(LAY-promotion-manage)', function(obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('您确定真的删除么？', function (index) {
                layer.close(index);
                $.ajax({
                    url: 'knowledge/delKnowledge?id=' + data.id,
                    method: 'delete',
                    success: function (res) {
                        var status = res.optStatusCode;
                        if (status == 10400) {
                            layer.msg("删除成功", {icon: 5});
                            table.reload('LAY-promotion-manage');
                        } else {
                            layer.msg("删除数据失败");
                        }
                    },
                    error: function () {
                        layer.msg("删除数据失败");
                    }
                })
            });
        } else if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '编辑优惠信息',
                content: 'promotionForm',
                area:['800px', '90%'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                        submitID = 'LAY-promotion-submit',
                        submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function(data) {
                        //表单数据实体对象
                        var field = data.field;
                        //提交 Ajax 成功后，静态更新表格中的数据
                        var jsonstr = {
                            "id":field.uid,
                            "marketingactivities":field.marketingactivities
                        };
                        $.ajax({
                            url: 'knowledge/updateKnowledge',
                            type:"put",
                            contentType:"application/json",//设置请求参数类型为json字符串
                            data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
                            dataType: "json",
                            success: function (data) {
                                var status = data.optStatusCode;
                                if (status == 10400) {
                                    layer.close(index); //关闭弹层
                                    layer.msg("编辑成功", {icon: 1});
                                    table.reload('LAY-promotion-manage');
                                } else {
                                    layer.msg("编辑数据失败");
                                }
                            },
                            error: function () {
                                layer.msg("编辑数据失败");
                            }
                        });
                    });
                    submit.trigger('click');
                },
                success: function(layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.contents().find("#uid").val(data.id);
                    body.contents().find("#marketingactivities").val(data.marketingactivities);
                }
            });
        } else if (obj.event === 'detail') {
            layer.open({
                type: 2,
                title: '优惠详细信息',
                content: 'promotionForm',
                area:['800px', '90%'],
                btn: ['确定', '取消'],
                success: function(layero, index) {
                    var body = layer.getChildFrame('body', index);
                    //回显方法
                    body.contents().find("#uid").val(data.id);
                    body.contents().find("#marketingactivities").val(data.marketingactivities);
                }
            });
        }
    });
    exports('promotionManage', {})
});
