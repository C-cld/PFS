// 选中标签
var selectedTag = [];
resizeTagAndFile();
$(window).resize(function () {
    resizeTagAndFile();
});

function resizeTagAndFile() {
    var bodyHeight = $("body").height();
    var tagHeight = $(".tag").height();
    var fileHeight = bodyHeight - tagHeight;
    /*$(".file").height(bodyHeight - tagHeight);*/
    $(".files").css({"height": fileHeight + "px", "margin-top" : tagHeight + "px"});
}

// 标签库里的标签添加点击事件
$(".label-items").on("click", "li", function(){
    var id = $(this).attr("data");
    var text = $(this).children("span:nth-child(2)").html();
    if ($(this).hasClass("selected")) {
        return false;
    }
    if (addLabel(id, text)) {
        $(this).addClass("selected");
        selectedTag.push(id);
    }
    // 查询选中标签
});
// 添加标签到顶端区域
function addLabel(id,text) {
    var labelHTML = "<li data='"+id+"''>"+text+"<div class='delete'></div></li>";
    $(".label-selected").append(labelHTML);
    return true;
}
// 删除标签
$(".label-selected").on("click", "li .delete", function(){
    var id = $(this).parent().attr("data");
    // 从选中标签中移除
    $(this).parent().remove();
    // 还原标签库中选中标签的颜色
    $(".label-items").find("li[data='"+id+"']").removeClass("selected");
});

// 添加标签
$(".add-tag").on("click", function(){
    var tagName = $(".new-tag").val();
    $.ajax({
        url:"./add-tag?tagName=" + tagName,
        success:function(data){
            if (data != null) {
                var labelHTML = "<li data=" + data.id + "><span class='glyphicon glyphicon-plus' aria-hidden='true'></span><span>" + data.name + "</span>";
                $(".label-items").append(labelHTML);
                alert("添加成功");
                $(".new-tag").val("");
            }
        }
    });
});

// 上传文件
$(".upload").on("click", function(){
    var formData = new FormData();
    formData.append("videoFile", $(".file")[0].files[0]);
    formData.append("videoTags", selectedTag);
    $.ajax({
        url:"./upload-file",
        type:"POST",
        data: formData,
        enctype: 'multipart/form-data',
        cache: false,
        processData:false, // 使数据不做处理
        contentType:false, // 不要设置Content-Type请求头
        success:function(data){
            if (data == 'success') {
                alert('上传成功！');
                $(".file")[0].files[0] = null;
            }
        },
        error:function(request) {
            alert("上传文件失败 ");
        }
    });
});