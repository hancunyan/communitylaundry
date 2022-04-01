/**

 @Name：
 @Author：liumei caolijun

 */
layui.define(['table', 'form', 'laydate'], function (exports) {
    var $ = layui.$,
        e1 = echarts.init(document.getElementById("e1")),
        pageLocal=getQueryString('time'),
        reqArr,
        barReqPath='',
        dataOjb = {},
        searchData = '';
    if(pageLocal=='week'){
        //按周统计
        reqArr = [{
            'path': 'statis/queryWeekNormalCount',
            'domName': '#normalNum',
            'dataObjKey': 'normalNum'
        }, {
            'path': 'statis/queryWeekAbnormalCount',
            'domName': '#notNormalNum',
            'dataObjKey': 'notNormalNum'
        }, {
            'path': 'statis/queryWeekCount',
            'domName': '#currMarkNum',
            'dataObjKey': 'currMarkNum'
        }];
        barReqPath='statis/queryWeekCountByDay';
    }else if(pageLocal=='month'){
        //按月统计
        reqArr = [{
            'path': 'statis/queryMouthNormalCount',
            'domName': '#normalNum',
            'dataObjKey': 'normalNum'
        }, {
            'path': 'statis/queryMouthAbnormalCount',
            'domName': '#notNormalNum',
            'dataObjKey': 'notNormalNum'
        }, {
            'path': 'statis/queryMouthCount',
            'domName': '#currMarkNum',
            'dataObjKey': 'currMarkNum'
        }];
        barReqPath='statis/queryMouthCountByDay';
    }else{
        layer.msg('功能开发中',{icon:7});
    }
    var option1 = {
        legend: {
            bottom: '0'
        },
        tooltip: {},
        title: {
            text: '体温数据按设备统计',
            left: 'center',
            top: '6',
            textStyle: {
                fontSize: '16',
                color: '#4f5451',
                fontFamily: 'sans-serif'
            }
        },
        color: ['#67ce84', '#796fd6'],
        grid: {
            right: '0',
            left: '30'
        },
        dataset: {
            dimensions: ['product', '正常', '异常'],
            source: []
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {},
        series: [{
                type: 'bar',
                barWidth: '20%',
            },
            {
                type: 'bar',
                barWidth: '20%',
            }
        ]
    };
    e1.setOption(option1);
    //面板数据
    function reqAllData(i) {
        var pathObj = reqArr[i];
        $.ajax({
            url: pathObj.path,
            type: "get",
            data: {
                days: searchData
            },
            success: function (data) {
                var code = data.optStatusCode;
                if (code == 10400) {
                    var domName = pathObj['domName'];
                    var variable = pathObj['dataObjKey'];
                    dataOjb[variable] = data.data.count;
                    $(domName).text(data.data.count);
                }
            },
            error: function (errMess) {
                layer.msg('数据请求失败，请联系管理员！', {icon: 7});
            },
            complete: function () {
                if (i >= (reqArr.length - 1)) {
                    return;
                }
                i++;
                reqAllData(i);
            }
        });
    };
    reqAllData(0);
    getTheBar();
    //统计图数据
    function getTheBar() {
        $.ajax({
            url: barReqPath,
            type: "get",
            data: {},
            success: function (data) {
                var code = data.optStatusCode;
                if (code == 10400) {
                    var theData = data.data;
                    renderTheBar(theData);
                }
            },
            error: function (errMess) {
                layer.msg('数据请求失败，请联系管理员！', {icon: 7});
            }
        });
    };
    function renderTheBar(theData) {
        var sourceArr = [];
        for (var i = 0; i < theData.length; i++) {
            var dataIt = theData[i];
            var pushObj = {};
            pushObj['product'] = dataIt.common;
            pushObj['正常'] = dataIt['normalCount'];
            pushObj['异常'] = dataIt['abnormalCount'];
            sourceArr.push(pushObj);
        }
        option1.dataset.source = sourceArr;
        e1.setOption(option1);
    };
    window.onresize = function () {
        e1.resize();
    };
    //获取url参数
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }
    exports('timeFragment', {})
});
