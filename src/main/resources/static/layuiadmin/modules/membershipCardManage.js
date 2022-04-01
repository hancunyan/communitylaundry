layui.define(['table', 'form'], function(exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form;
    table.render({
        elem: '#LAY-membershipCard-manage',
        url:'member/queryMembershipCardPaging',
        cols: [
            [{
                type: 'checkbox'
            },/* {
                field: 'id',
                title: '序号',
                align: 'center'
            },*/ {
                field: 'cardnumber',
                title: '会员卡号',
                align: 'center'
            }, {
                field: 'telephone',
                title: '电话号码',
                align: 'center'
            }, {
                field: 'gradename',
                title: '会员等级',
                align: 'center'
            }, {
                field: 'integral',
                title: '积分',
                align: 'center'
            }, {
                field: 'balance',
                title: '余额',
                align: 'center'
            },{
                field: 'introduce',
                title: '介绍',
                align: 'center'
            },{
                field: 'discount',
                title: '折扣',
                align: 'center'
            }, {
                field: 'createtime',
                title: '创建时间',
                align: 'center'
            }, {
                title: '操作',
                width: 350,
                align: 'center',
                toolbar: '#table-membershipCard-user'
            }]
        ],
        page: true,
        limit: 10,
        height: 'full-165',
        text: {
            none: '未查询到相关数据'
        },
        //渲染之后的回调
        done: function() {}
    });
    //表格工具监听
    table.on('tool(LAY-membershipCard-manage)', function(obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('您确定真的删除么？', function (index) {
                layer.close(index);
                $.ajax({
                    url: 'member/delCard?id=' + data.id,
                    method: 'delete',
                    success: function (res) {
                        var status = res.optStatusCode;
                        if (status == 10400) {
                            layer.msg("删除成功", {icon: 5});
                            table.reload('LAY-membershipCard-manage');
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
            window.PartitionId=1
            window.deviceId=data['id']
            layer.open({
                type: 2,
                title: '编辑会员信息',
                content: 'membershipCardForm',
                area: ['560px', '90%'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                        submitID = 'LAY-membershipCard-submit',
                        submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function(data) {
                        //表单数据实体对象
                        var field = data.field;
                        //提交 Ajax 成功后，静态更新表格中的数据
                        var jsonstr = {
                            "id":obj.data.id,
                            "cardnumber":field.cardnumber,
                            "telephone":field.telephone,
                            "gradeid":1,
                            "integral":field.integral,
                            "balance":field.balance,
                            "introduce":field.introduce,
                            "discount":field.discount
                        };
                        $.ajax({
                            url: 'member/upCard',
                            type:"put",
                            contentType:"application/json",//设置请求参数类型为json字符串
                            data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
                            dataType: "json",
                            success: function (data) {
                                var status = data.optStatusCode;
                                if (status == 10400) {
                                    layer.close(index); //关闭弹层
                                    layer.msg("编辑成功", {icon: 1});
                                    table.reload('LAY-membershipCard-manage');
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
                    body.contents().find("#cardnumber").val(data.cardnumber);
                    body.contents().find("#telephone").val(data.telephone);
                    body.contents().find("#integral").val(data.integral);
                    body.contents().find("#balance").val(data.balance);
                    body.contents().find("#introduce").val(data.introduce);
                    // body.contents().find("#washingobjec").val(data.washingobjec);
                    body.contents().find("#discount").val(data.discount);
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                }
            });
        } else if (obj.event === 'detail') {
            layer.open({
                type: 2,
                title: '会员详细信息',
                content: 'membershipCardForm',
                area: ['560px', '90%'],
                btn: ['确定', '取消'],
                success: function(layero, index) {
                    var body = layer.getChildFrame('body', index);
                    //回显方法
                    body.contents().find("#cardnumber").val(data.cardnumber);
                    body.contents().find("#telephone").val(data.telephone);
                    body.contents().find("#integral").val(data.integral);
                    body.contents().find("#balance").val(data.balance);
                    body.contents().find("#introduce").val(data.introduce);
                    body.contents().find("#discount").val(data.discount);
                    body.contents().find("#cardnumber").attr("disabled", true);
                    body.contents().find("#telephone").attr("disabled", true);
                    body.contents().find("#integral").attr("disabled", true);
                    body.contents().find("#balance").attr("disabled", true);
                    body.contents().find("#introduce").attr("disabled", true);
                    body.contents().find("#discount").attr("disabled", true);
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                }
            });
        }
    });
    exports('membershipCardManage', {})
});
