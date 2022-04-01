layui.define(['table', 'form'], function (exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form;
    table.render({
        elem: '#LAY-order-manage',
        url: "laundryorder/queryOrderPaging",
        cols: [
            [{
                type: 'checkbox'
            },{
                field: 'username',
                title: '下单用户',
                align: 'center'
            }, {
                field: 'mobiles',
                title: '联系电话',
                align: 'center'
            }, {
                field: 'address',
                title: '下单地址',
                width: 350,
                align: 'center'
            }, {
                field: 'price',
                title: '订单价格',
                align: 'center'
            }, {
                field: 'requirement',
                title: '订单要求',
                align: 'center'
            }, {
                field: 'reservationstatus',
                templet: '#stateTpl',
                title: '订单状态',
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
        where:{
            state : "6"
        },
        height: 'full-140',
        text: {
            none: '未查询到相关数据'
        },
        //渲染之后的回调
        done: function () {
        }
    });
    //表格工具监听
    table.on('tool(LAY-order-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'editgx') {
            layer.open({
                type: 2
                , title: '配送预约'
                , content: 'deliverOrderForm'
                , maxmin: false
                , area: ['460px', '600px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                        submitID = 'LAY-deliver-submit',
                        submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function(data1) {
                        var field = data1.field;
                        var jsonstr = {
                            "id": data.id,
                            "reservationstatus":"7",
                            "reservationgivetime": field.reservationgivetime
                        };
                        $.ajax({
                            url: 'laundryorder/updateUpdoorStatus',
                            type: "post",
                            contentType: "application/json",
                            data: JSON.stringify(jsonstr),
                            dataType: "json",
                            success: function (data) {
                                var status = data.optStatusCode;
                                if (status == 10400) {
                                    layer.close(index); //关闭弹层
                                    layer.msg("预约成功", {icon: 1});
                                    table.reload('LAY-order-manage');
                                } else {
                                    layer.msg("预约失败");
                                }
                            },
                            error: function () {
                                layer.msg("预约失败");
                            }
                        });
                    });
                    submit.trigger('click');
                }
                , success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.contents().find("#uid").val(data.id);
                    body.contents().find("#address").val(data.address);
                    body.contents().find("#address").attr("disabled", true);
                }
            });

        } else if (obj.event === 'detail') {
            var ordernum = data.ordernum;
            layer.open({
                type: 2
                , title: '衣服详情'
                , content: 'orderClothesForm'
                , maxmin: false
                , area: ['680px', '500px']
                , btn: ['确定']
                , success: function (layero, index) {
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
                    //调用弹出界面的方法
                    iframeWin.renderOrder(ordernum);
                }
            });
        }
    });

    exports('deliverOrderManage', {})
});
