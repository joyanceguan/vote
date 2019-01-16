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
			      <input type="email" class="" id="email" value="" placeholder="邮箱"> 
			    </div>
			    <div class="am-form-group">
			      <input type="password" class="" id="password" value=""  placeholder="密码（6-20）">
			    </div>
			    <div class="am-form-group">
			      <input type="password" class="" id="confirm_password" value="" placeholder="确认密码">
			    </div>
			    <div class="am-form-group">
			      <select id="sex">
			        <option value = "-1">性别</option>
			        <#list sexList as sex>
			           <option value="${sex}">${sex.desc}</option>
                    </#list>
                  </select>
			    </div>
			    <div class="am-form-group">
			      <input type="text" class="" id="age" value="" placeholder="年龄（7-120之间）"> 
			    </div>
			    <p><button type="submit" class="am-btn am-btn-default">registe</button></p>
			    <div class="login-text">
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
	   var email = $.trim($("#email").val());
	   var password = $.trim($("#password").val());
	   var confirm_password = $.trim($("#confirm_password").val());
	   var sex = $("#sex").find("option:selected").val(); 
	   var age = $("#age").val();
	   if(email == '' || password == '' || confirm_password == '' || sex == '-1' || age == ''){
		   alert('请输入必填信息');
		   return;
	   }
	   var email_regex = new RegExp("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
	   if(!email_regex.test(email)){
		   alert('请输入正确的邮箱格式');
		   return;
	   }
	   if(password.length < 6 || password.length > 20){
		   alert('密码长度在6-20个字符之间');
		   return;
	   }
	   if(password!=confirm_password){
		   alert('密码和确认密码不一致');
		   return;
	   }
	   age = parseInt(age);
	   if(age < 7 || age > 120){
		   alert('请输入正确的年龄');
		   return;
	   }
	   
	   $.ajax({
			   async:false,
			   data:{email:email,sex:sex,age:age,password:password,confirmPassword:confirm_password},
			   dataType:"json",
			   url:contextPath+"/registeUser",
			   type:"POST",
			   success:function(data) {
				   if(data.errorType == 'SUCCESS'){
					   alert('注册成功');
					   window.location.href = contextPath+"/login"
				   }else if(data.errorType == 'PARAM_ERROR'){
					   alert(data.errorMessage);
				   }else {
					   alert('服务器繁忙...');
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