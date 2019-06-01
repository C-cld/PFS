/*window.onload = function () {
	//给所有侧边栏选项添加点击事件
	var aa = document.getElementsByClassName("aa");
	for(var i = 0;i<aa.length;i++){
		aa[i].onclick = redirect;
		}
}*/

//跳页事件
function redirect(e) {
	var url = e.getAttribute("data-url");
	console.log(url);
	document.getElementById("main-container").innerHTML = "<iframe src='./" + url + "' width='100%' height='100%' name='iframe' class='iframe'></iframe>";
}
