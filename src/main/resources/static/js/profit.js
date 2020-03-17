layui.use(["form","table","laydate"], function(){
    var $ = layui.jquery;
    var form = layui.form;
    var table = layui.table;
    var laydate = layui.laydate;

    laydate.render({
        elem: '#start-date',
        done: function() {
            // 加载收益曲线
            paintCurve();
            // 加载月柱状图
            paintBar();
        }
    });
    laydate.render({
        elem: '#end-date',
        done: function() {
            // 加载收益曲线
            paintCurve();
            // 加载月柱状图
            paintBar();
        }
    });
    laydate.render({
        elem: '.create-date',
        value: new Date()
    });

    form.on('select(categories)', function(data) {
        // 加载收益曲线
        paintCurve();
        // 加载月柱状图
        paintBar();
    });

    // 加载当月收益和下拉菜单
    loadCurrentMonthProfit();
    // 加载收益曲线
    paintCurve();
    // 加载月柱状图
    paintBar();


    function loadCurrentMonthProfit() {
        $(".current-month-profit").html("<div class='layui-inline'><label class='layui-form-label'><b>当月收益</b></label></div>");
        $(".category").html("<option value='0'>全部</option>");
        $.ajax({
            url: "/get-current-month-profit",
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
                    // 加载下拉菜单
                    var option = "<option value='" + result[i].id + "'>" + result[i].name + "</option>";
                    $(".category").append(option);
                    form.render();
                }
            }
        });
    }

    function paintBar() {
        var xData = [];
        var yData = [];
        $.ajax({
            url: "/get-month-profit?startDate=" + $("#start-date").val() + "&endDate=" + $("#end-date").val() + "&category=" + $(".category").val(),
            async: false,
            success: function(result) {
                for (var i = 0; i < result.length; i ++) {
                    xData.push(result[i].date);
                    yData.push(result[i].value.toFixed(2));
                }
            }
        });
        var myChart = echarts.init(document.getElementById("bar"));
        var option = {
            color: ['#3398DB'],
            title: {
                text: "每月收益"
            },
            tooltip: {
                trigger: "axis"
            },
            toolbox: {
                show: true,
                feature: {
                    magicType: {show: true, type: ['line', 'bar']},
                    saveAsImage: {show: true}
                }
            },
            xAxis: [
                {
                    type: 'category',
                    data: xData
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '收益',
                    type: 'bar',
                    data: yData,
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        }
        myChart.setOption(option);
    }

    function paintCurve() {
        var data = [];
        var total;
        $.ajax({
            url: "/get-curve-data?startDate=" + $("#start-date").val() + "&endDate=" + $("#end-date").val() + "&category=" + $(".category").val(),
            async: false,
            success: function(result) {
                var length = result.length;
                for (var i = 0; i < length; i ++) {
                    data.push({
                        name: result[i].createDate,
                        value: [result[i].createDate, result[i].sum.toFixed(2)]
                    });
                }
                total = result[length - 1].sum;
            }
        });

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
                data: data,
            }]
        };
        myChart.setOption(option);
    }

    $(".add-today-profit").on("click", function() {
        layer.open({
          type: 1,
          area: '500px',
          content: $('#add-today-profit-div'),
          btn: '确定',
          yes: function(index, layero){
            $.ajax({
                url: "/add-profit",
                type: "POST",
                data: JSON.stringify({
                    categoryId: $(".today-category").val(),
                    profit: $(".profit").val(),
                    createDate: $(".create-date").val()
                }),
                contentType: "application/json",
                success: function(result) {
                    layer.msg("添加成功");
                    // 加载收益曲线
                    paintCurve();
                    // 加载月柱状图
                    paintBar();
                    // 加载当月收益和下拉菜单
                    loadCurrentMonthProfit();
                }
            });
            layer.close(index);
          }
        });
    });
});