var dom = document.getElementById("chart");
var myChart = echarts.init(dom);
var chartData = genData();
option = {
	title : {
		text : '宝宝体重变化'
	},
	tooltip : {
		trigger : 'axis'
	},
	legend : {
		data : [ '日期', '早上', '晚上' ]
	},
	toolbox : {
		show : true,
		feature : {
			dataZoom : {
				yAxisIndex : 'none'
			},
			dataView : {
				readOnly : false
			},
			magicType : {
				type : [ 'line', 'bar' ]
			},
			restore : {},
			saveAsImage : {}
		}
	},
	xAxis : {
		type : 'category',
		boundaryGap : false,
		data : chartData.dateData
	},
	yAxis : {
		type : 'value',
		axisLabel : {
			formatter : '{value} Kg'
		}
	},
	series : [ {
		name : '早上',
		type : 'line',
		smooth : true,
		data : chartData.amData,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	}, {
		name : '晚上',
		type : 'line',
		smooth : true,
		data : chartData.pmData,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	} ]
};

if (option && typeof option === "object") {
	myChart.setOption(option, true);
}

window.onload = function() {
	//点击报表
	var d = document, accordionToggles = d
			.querySelectorAll('.js-accordionTrigger'), setAria, setAccordionAria, switchAccordion, touchSupported = ('ontouchstart' in window), pointerSupported = ('pointerdown' in window);

	skipClickDelay = function(e) {
		e.preventDefault();
		e.target.click();
	}
	setAriaAttr = function(el, ariaType, newProperty) {
		el.setAttribute(ariaType, newProperty);
	};
	setAccordionAria = function(el1, el2, expanded) {
		switch (expanded) {
		case "true":
			setAriaAttr(el1, 'aria-expanded', 'true');
			setAriaAttr(el2, 'aria-hidden', 'false');
			break;
		case "false":
			setAriaAttr(el1, 'aria-expanded', 'false');
			setAriaAttr(el2, 'aria-hidden', 'true');
			break;
		default:
			break;
		}
	};
	switchAccordion = function(e) {
		e.preventDefault();
		var thisAnswer = e.target.parentNode.nextElementSibling;
		var thisQuestion = e.target;
		if (thisAnswer.classList.contains('is-collapsed')) {
			setAccordionAria(thisQuestion, thisAnswer, 'true');
		} else {
			setAccordionAria(thisQuestion, thisAnswer, 'false');
		}
		thisQuestion.classList.toggle('is-collapsed');
		thisQuestion.classList.toggle('is-expanded');
		thisAnswer.classList.toggle('is-collapsed');
		thisAnswer.classList.toggle('is-expanded');

		thisAnswer.classList.toggle('animateIn');
	};
	for (var i = 0, len = accordionToggles.length; i < len; i++) {
		if (touchSupported) {
			accordionToggles[i].addEventListener('touchstart', skipClickDelay,
					false);
		}
		if (pointerSupported) {
			accordionToggles[i].addEventListener('pointerdown', skipClickDelay,
					false);
		}
		accordionToggles[i].addEventListener('click', switchAccordion, false);
	}

	//时间选择器格式化
	/* $('#datetimepicker1').datetimepicker({locale: 'zh-cn',viewMode: 'months',format: 'YYYY/MM'});
	$('#datetimepicker2').datetimepicker({locale: 'zh-cn',viewMode: 'months',format: 'YYYY/MM'});
	$("#datetimepicker1").on("dp.change",function (e) {$('#datetimepicker2').data("DateTimePicker").minDate(e.date);});
	$("#datetimepicker2").on("dp.change",function (e) {$('#datetimepicker1').data("DateTimePicker").maxDate(e.date);});*/
	$('#datetimepicker3').datetimepicker({
		locale : 'zh-cn',
		viewMode : 'days',
		format : 'YYYY-MM-DD'
	});
}

$('#secondSelect').on('changed.bs.select', function(e) {
	var selectedValues = [];
	$("#firstSelect option:selected").each(function() {
		selectedValues.push($(this).val());
		console.log($(this).val());
	});
});