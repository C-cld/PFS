// 标签库里的标签添加点击事件
$(".label-items").on("click", "li", function(){
    var id = $(this).attr("data");
    var text = $(this).children("span:nth-child(2)").html();
    if ($(this).hasClass("selected")) {
        return false;
    }
    if (addLabel(id, text)) {
        $(this).addClass("selected");
    }
});
// 添加标签到顶端区域
function addLabel(id,text) {
    var labelHTML = "<li data='"+id+"''>"+text+"<div class='delete'></div></li>";
    $(".label-selected").append(labelHTML);
}