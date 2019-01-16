<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/login/css/base.css" />
	<link rel="stylesheet" href="/login/css/login.css" />
	<title>基础结构平台</title>
</head>
<body>
	<div id="container">
		<div id="bd">
			<div class="login">
            	<div class="login-top"><h1 class="logo"></h1></div>
                <div class="login-input">
                	<p class="user ue-clear">
                    	<label>用户名</label>
                        <input id="name" type="text" />
                    </p>
                    <p class="password ue-clear">
                    	<label>密&nbsp;&nbsp;&nbsp;码</label>
                        <input id="pass" type="text" />
                    </p>
                </div>
                <div class="login-btn ue-clear">
                	<a href="javascript::" onclick="login()" class="btn">登录</a>
                    <div class="remember ue-clear">
                    	<input type="checkbox" id="remember" />
                        <em></em>
                        <label for="remember">记住密码</label>
                    </div>
                </div>
                <div id="tips" style="position: relative;right: -500px;color: red;font-size: 13px;"></div>
            </div>
		</div>
	</div>
    <div id="ft">CopyRight&nbsp;2014&nbsp;&nbsp;版权所有&nbsp;&nbsp;samxinnet.com专注于ui设计&nbsp;&nbsp;皖ICP备09001111号</div>
</body>
<script type="text/javascript" src="/login/js/jquery.js"></script>
<script type="text/javascript" src="/login/js/common.js"></script>
<script type="text/javascript">
var height = $(window).height();
$("#container").height(height);
$("#bd").css("padding-top",height/2 - $("#bd").height()/2);

$(window).resize(function(){
	var height = $(window).height();
	$("#bd").css("padding-top",$(window).height()/2 - $("#bd").height()/2);
	$("#container").height(height);
	
});

$('#remember').focus(function(){
   $(this).blur();
});

$('#remember').click(function(e) {
	checkRemember($(this));
});

function checkRemember($this){
	if(!-[1,]){
		 if($this.prop("checked")){
			$this.parent().addClass('checked');
		}else{
			$this.parent().removeClass('checked');
		}
	}
}
function login(){
	 $.ajax({
		 type: "GET",
		 url: "system/login",
		 data: {name:$("#name").val(), pass:$("#pass").val()},
		 dataType: "json",
		 success: function(data){
		             if(data.error)
		             	tips(data.errorMessage);
		             else
		             	window.location.href = "/helloworld?page=index";
		          }
	 });

}
function tips(tip){
	$("#tips").html(tip);
	window.setTimeout(function(){$("#tips").html("")},5000); 
}
</script>
</html>