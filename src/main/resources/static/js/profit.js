layui.use(["form","table","laydate"], function(){
    var $ = layui.jquery;
    var form = layui.form;
    var table = layui.table;
    var laydate = layui.laydate;

    var selectOption = [];

    loadCurrentMonthProfitData();
    loadForm();
    paintCurve();

    function loadForm() {
        laydate.render({
            elem: '#start-date',
            done: function() {

            }
        });
        laydate.render({
            elem: '#end-date',
            done: function() {

            }
        });

        form.on('select(categories)', function(data) {

        });

        $(".category").html("<option value='0'>全部</option>");
        for (var i = 0; i < selectOption.length; i ++) {
            var option = "<option value='" + selectOption[i].id + "'>" + selectOption[i].name + "</option>";
            $(".category").append(option);
            form.render();
        }

    }


    function loadCurrentMonthProfitData() {
        $(".current-month-profit").html("<div class='layui-inline'><label class='layui-form-label'><b>当月收益</b></label></div>");
        $.ajax({
            url: "/get-current-month-profit",
            async: false,
            success: function(result) {
                for (var i = 0; i < result.length; i ++) {

                    var html;
                    if (result[i].value < 0) {
                        html = "<div class='layui-inline'>" +
                                    "<label class='layui-form-label'>" + result[i].name + "：</label>" +
                                    "<span class='layui-badge-rim p-value' style='color:green'>" + result[i].value.toFixed(2) + "</span>" +
                               "</div>";
                    } else {
                        html = "<div class='layui-inline'>" +
                                    "<label class='layui-form-label'>" + result[i].name + "：</label>" +
                                    "<span class='layui-badge-rim p-value' style='color:red'>" + result[i].value.toFixed(2) + "</span>" +
                               "</div>";
                    }
                    $(".current-month-profit").append(html);
                    selectOption.push({
                        id : result[i].id,
                        name : result[i].name
                    });
                }
            }
        });
    }

    function paintCurve() {
        var startDate = $("#start-date").val();
        var endDate = $("#end-date").val();
        var category = $(".category").val();
        var sumProfitPerDay = [];
        var total;
        $.ajax({
            url: "/get-sum-profit-per-day?startDate=" + startDate + "&endDate=" + endDate + "&category=" + category,
            async: false,
            success: function(result) {
                var sum = 0;
                for (var i = 0; i < result.length; i ++) {
                    sum = sum + result[i].sum;
                    sumProfitPerDay.push({
                        name: result[i].createDate,
                        value: [result[i].createDate, sum.toFixed(2)]
                    });
                }
                console.log(sumProfitPerDay[length - 1])
                total = sumProfitPerDay[length - 1].value;
                // 渲染曲线
                var myChart = echarts.init(document.getElementById("curve"));
                var option = {
                    title: {
                        text: "累计收益：" + total.toFixed(2)
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params) {
                            params = params[0];
                            var date = new Date(params.name);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ':' + params.value[1];
                        },
                        axisPointer: {
                            animation: false
                        }
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            magicType: {show: true, type: ['line', 'bar']},
                            saveAsImage: {show: true}
                        }
                    },
                    xAxis: {
                        type: 'time',
                        splitLine: {
                            show: false
                        }
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%'],
                        splitLine: {
                            show: true
                        }
                    },
                    series: [{
                        name: '累计收益2',
                        type: 'line',
                        color: '#1E90FF',
                        showSymbol: false,
                        hoverAnimation: false,
                        data: sumProfitPerDay,
                    }]
                };
                myChart.setOption(option);
            }
        });
    }
});