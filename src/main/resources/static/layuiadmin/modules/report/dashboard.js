/**

 @Name： 
 @Author：liumei caolijun

 */
layui.define(['table', 'form', 'laydate'], function(exports) {
	var $ = layui.$,
		laydate = layui.laydate,
		form = layui.form,
		e1 = echarts.init(document.getElementById("e1")),
		e2 = echarts.init(document.getElementById("e2"));
	form.on('submit(LAY-user-search)', function(data) {
		var field = data.field;
		console.log(field);
	});
	laydate.render({
		elem:'#_date',
		type:'date'
	})
	var option1 = {
		title: {
			text: '体温状态统计',
			left: 'center',
			top: '6',
			textStyle: {
				color: '#4f5451',
				fontSize: '16'
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: '{b} : {c}<br/>{d}%'
		},
		legend: {
			orient: 'vertical',
			left: 'left',
			top: '42',
			data: ['体温正常数', '体温异常数']
		},
		color: ['#67ce84', '#796fd6'],
		series: [{
			type: 'pie',
			radius: '55%',
			center: ['50%', '60%'],
			data: [{
					value: 335,
					name: '体温正常数'
				},
				{
					value: 310,
					name: '体温异常数'
				}
			],
			emphasis: {
				itemStyle: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
	var option2 = {
		title: {
			text: '当日体温偏高前10人',
			left: 'center',
			top: '6',
			textStyle: {
				color: '#4f5451',
				fontSize: '16'
			}
		},
		color: ['#796fd6'],
		tooltip: {
			trigger: 'item',
			formatter: '{b}:<br/> {c}'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			data: [],
			axisTick: {
				alignWithLabel: true
			}
		}],
		yAxis: [{
			type: 'value'
		}],
		series: [{
			name: '体温℃',
			type: 'bar',
			barWidth: '30%',
			data: []
		}]
	};
	e1.setOption(option1);
	e2.setOption(option2);
	window.onresize=function(){
		e1.resize();
		e2.resize();
	};
	var dataOjb={};
	var reqArr=[{
		'path':'statis/queryDayPunchCount',
		'domName':'#currMarkNum',
		'dataObjKey':'currMarkNum'
	},{
		'path':'statis/queryDayNormalCount',
		'domName':'#normalNum',
		'dataObjKey':'normalNum'
	},{
		'path':'statis/queryDayAbnormalCount',
		'domName':'#notNormalNum',
		'dataObjKey':'notNormalNum'
	},{
		'path':'statis/queryDayCount',
		'domName':'#totalMark',
		'dataObjKey':'totalMark'
	}];
	function reqAllData(i){
		var pathObj=reqArr[i];
		$.ajax({
			url: pathObj.path,
			type: "get",
			success: function(data) {
				var code=data.optStatusCode;
				if(code==10400){
					var domName=pathObj['domName'];
					var variable=pathObj['dataObjKey'];
					dataOjb[variable]=data.data.count;
					$(domName).text(data.data.count);
				}
			},
			error:function(errMess){
				layer.msg('数据请求失败，请联系管理员！',{icon:7});
			},
			complete:function(){
				if(i>=(reqArr.length-1)){
					return;
				}
				i++;
				if(i==(reqArr.length-1)){
					renderE1();
				}
				reqAllData(i);
			}
		});
	};
	reqAllData(0);
	function renderE1(){
		option1.series[0].data[0].value=dataOjb['normalNum'];
		option1.series[0].data[1].value=dataOjb['notNormalNum'];
		e1.setOption(option1);
	};
	//体温偏高前十人
	function getBefore10(){
		$.ajax({
			url: 'statis/queryDayTopten',
			type: "get",
			success: function(data) {
				var code=data.optStatusCode;
				if(code==10400){
					var dataArr=data.data;
					renderData10(dataArr);
				}
			},
			error:function(errMess){
				layer.msg('数据请求失败，请联系管理员！',{icon:7});
			}
		});
	}
	//渲染柱子10
	function renderData10(dataArr){
		var nameArr=[];
		var temArr=[];
		for(var i=0;i<dataArr.length;i++){
			var arrIt=dataArr[i];
			nameArr.push(arrIt['personname']);
			temArr.push(arrIt['detectiontemp']);
		};
		option2.xAxis[0].data=nameArr;
		option2.series[0].data=temArr;
		e2.setOption(option2);
	}
	getBefore10();
	exports('dashboard', {})
});
