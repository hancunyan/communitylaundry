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
            state : "5"
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
            var jsonstr = {
                "id":data.id,
                "reservationstatus": "6"
            };
            $.ajax({
                url: 'laundryorder/updateUpdoorStatus',
                type:"post",
                contentType:"application/json",//设置请求参数类型为json字符串
                data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
                dataType: "json",
                success: function (data) {
                    var status = data.optStatusCode;
                    if (status == 10400) {
                        layer.msg("清洗完成确认成功", {icon: 5});
                        table.reload('LAY-order-manage');//数据刷新
                    } else {
                        layer.msg("清洗完成确认失败");
                    }
                },
                error: function () {
                    layer.msg("清洗完成确认失败");
                }
            });
        } else if (obj.event === 'detail') {
            var ordernum = data.ordernum;
            layer.open({
                type: 2
                , title: '衣服详情'
                , content: 'complereOrderForm'
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

    exports('completeOrderManage', {})
});
