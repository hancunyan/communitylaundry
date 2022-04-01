layui.define(['table', 'form'], function(exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form;
    table.render({
        elem: '#LAY-device-manage',
        url:'equipment/queryEquipmentPaging',
        cols: [
            [{
                type: 'checkbox'
            },/* {
                field: 'id',
                title: '序号',
                align: 'center'
            },*/ {
                field: 'equipmentname',
                title: '设备名称',
                align: 'center'
            }, {
                field: 'manufactor',
                title: '设备厂家',
                align: 'center'
            }, {
                field: 'model',
                title: '设备型号',
                align: 'center'
            }, {
                field: 'capacity',
                title: '设备容量',
                align: 'center'
            }, {
                field: 'workinglimit',
                title: '工作时长限制',
                align: 'center'
            },{
                field: 'washingobjec',
                title: '洗涤对象',
                align: 'center'
            }, {
                field: 'status',
                title: '设备状态',
                align: 'center'
                ,templet: function(d){
                    var str = "";
                    if(d.status == '1'){
                        str = "未使用";
                    }else if(d.status == '2'){
                        str = "使用中";
                    }
                    return str;
                }
            }, {
                title: '操作',
                width: 350,
                align: 'center',
                toolbar: '#table-device-user'
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
    table.on('tool(LAY-device-manage)', function(obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('您确定真的删除么？', function (index) {
                layer.close(index);
                $.ajax({
                    url: 'equipment/delEquipments?equipmentid=' + data.id,
                    method: 'delete',
                    success: function (res) {
                        var status = res.optStatusCode;
                        if (status == 10400) {
                            layer.msg("删除成功", {icon: 5});
                            table.reload('LAY-device-manage');
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
                title: '编辑设备信息',
                content: 'deviceForm',
                area: ['560px', '90%'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                        submitID = 'LAY-device-submit',
                        submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function(data) {
                        //表单数据实体对象
                        var field = data.field;
                        //提交 Ajax 成功后，静态更新表格中的数据
                        var jsonstr = {
                            "id":field.uid,
                            "equipmentname":field.equipmentname,
                            "manufactor":field.manufactor,
                            "model":field.model,
                            "capacity":field.capacity,
                            "workinglimit":field.workinglimit,
                            "washingobjec":field.washingobjec,
                            "characteristic":field.characteristic
                        };
                        $.ajax({
                            url: 'equipment/updateEquipments',
                            type:"put",
                            contentType:"application/json",//设置请求参数类型为json字符串
                            data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
                            dataType: "json",
                            success: function (data) {
                                var status = data.optStatusCode;
                                if (status == 10400) {
                                    layer.close(index); //关闭弹层
                                    layer.msg("编辑成功", {icon: 1});
                                    table.reload('LAY-device-manage');
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
                    body.contents().find("#equipmentname").val(data.equipmentname);
                    body.contents().find("#manufactor").val(data.manufactor);
                    body.contents().find("#model").val(data.model);
                    body.contents().find("#capacity").val(data.capacity);
                    body.contents().find("#workinglimit").val(data.workinglimit);
                    // body.contents().find("#washingobjec").val(data.washingobjec);
                    body.contents().find("#characteristic").val(data.characteristic);
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.initEditSelect(data.washingobjec);
                }
            });
        } else if (obj.event === 'detail') {
            layer.open({
                type: 2,
                title: '设备详细信息',
                content: 'deviceForm',
                area: ['560px', '90%'],
                btn: ['确定', '取消'],
                success: function(layero, index) {
                    var body = layer.getChildFrame('body', index);
                    //回显方法
                    body.contents().find("#uid").val(data.id);
                    body.contents().find("#equipmentname").val(data.equipmentname);
                    body.contents().find("#manufactor").val(data.manufactor);
                    body.contents().find("#model").val(data.model);
                    body.contents().find("#capacity").val(data.capacity);
                    body.contents().find("#workinglimit").val(data.workinglimit);
                    body.contents().find("#washingobjec").val(data.washingobjec);
                    body.contents().find("#characteristic").val(data.characteristic);
                    body.contents().find("#equipmentname").attr("disabled", true);
                    body.contents().find("#manufactor").attr("disabled", true);
                    body.contents().find("#model").attr("disabled", true);
                    body.contents().find("#capacity").attr("disabled", true);
                    body.contents().find("#workinglimit").attr("disabled", true);
                    body.contents().find("#washingobjec").attr("disabled", true);
                    body.contents().find("#characteristic").attr("disabled", true);
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.initEditSelect(data.washingobjec);
                }
            });
        }
    });
    exports('deviceManage', {})
});
