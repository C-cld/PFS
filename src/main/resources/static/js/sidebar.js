//跳页事件
function redirect(e) {
	var url = e.getAttribute("data-url");
	document.getElementById("main-container").innerHTML = "<iframe src='./" + url + "' width='100%' height='100%' name='iframe' class='iframe'></iframe>";
}
