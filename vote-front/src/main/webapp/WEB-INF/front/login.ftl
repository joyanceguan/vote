<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>Amaze UI Examples</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="stylesheet" href="${contextPath}/css/amazeui.min.css">
  <link rel="stylesheet" href="${contextPath}/css/app.css">
</head>
<body>
<div class="am-g myapp-login">
	<div class="myapp-login-bg">
		 <div class="myapp-login-logo">
		 	<i class="am-icon-stumbleupon"></i>
		 </div>
		 <div class="am-u-sm-10 myapp-login-form">
		 	<form class="am-form">
			  <fieldset>
			    <div class="am-form-group">
			      <input type="email" class="" id="doc-ipt-email-1" value=" " placeholder="邮箱">
			    </div>
			    <div class="am-form-group">
			      <input type="password" class="" id="doc-ipt-pwd-1" value=""  placeholder="密码">
			    </div>
			    <p><button type="submit" class="am-btn am-btn-default">Login</button></p>
			    <div class="login-text">
			    	Forgot Password?
			    </div>
			  </fieldset>
			</form>
		 </div>
	</div>
</div>
<script src="${contextPath}/js/jquery.min.js"></script>
<script src="${contextPath}/js/amazeui.min.js"></script>
<script src="${contextPath}/js/app.js"></script>
<script>
   var contextPath = '${contextPath}';
   $("button[type='submit']").click(function(){
	   var email = $("#doc-ipt-email-1").val();
	   var passwd = $("#doc-ipt-pwd-1").val();
	   if(email == '' || passwd == ''){
		   alert('请输入邮箱和密码');
		   return;
	   }
	   $.ajax({
			   async:false,
			   data:{email:email,password:passwd},
			   dataType:"json",
			   url:contextPath+"/dologin",
			   type:"POST",
			   success:function(data) {
				   if(data.errorType == 'SYSTEM_ERROR'){
					   alert('服务器繁忙...');
				   }else if(data.errorType == 'PARAM_ERROR'){
					   alert('请传入正确的邮箱');
				   } else{
					   if(data.errorType == 'SERVICE_ERROR'){
						  if(data.errorCode == 'INVALID_PASSWD'){
							  alert('密码错误');
						  }else if(data.errorCode == 'USER_FREEZE'){
							  alert('该用户已被冻结');
						  }
					   } else{
						   window.location.href = contextPath+'/index';
					   }
				   }
	            }, 
	            error:function(){
	               console.log("服务器繁忙...");
	            }
	   })
   })
</script>
</body>
</html>