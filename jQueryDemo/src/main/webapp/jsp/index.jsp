<html>
<head>
<%@page language="java" contentType="text/html; charset=utf-8"%>
<head>
<script type="text/javascript">
	function showProvince(str) {
		var xmlhttp;
		if (str == "") {
			document.getElementById("txtHint").innerHTML = "";
			return;
		}
		if (window.XMLHttpRequest) {
			//IE7+,Firefox,Chrome,Opera,Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			//IE5,IE6
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("txtHint").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "/ajax/getProvince.asp?q=" + str, true);
		xmlhttp.send();
	}
</script>
</head>

<body>
	<p>1. Ajax可用来与数据库进行通信</p>
	<form action="" sytle="margin-top:15px;">
		<label>请选择一个省份： <select name="provinces"
			onchange="showProvince(this.value)"
			style="font-family: Verdana, Arial, Helvetica, sans-serif;">
				<option value="gansu">甘肃，兰州.</option>
				<option value="zhejiang">浙江，杭州.</option>
				<option value="jiangsu">江苏，南京.</option>
				<option value="shanghai">上海，上海.</option>
				<option value="guangdong">广东，广州.</option>
		</select>
		</label>
	</form>

	<br />
	<div id="txtHint">省份信息将在此列出......</div>
</body>
</html>
