<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/login/css/base.css" />
<link rel="stylesheet" href="/login/css/info-reg.css" />
<title>基础结构平台</title>
</head>

<body>
<div class="title"><h2>新增投票</h2></div>
<div class="main">
	<p class="long-input ue-clear">
    	<label>名称：</label>
        <input type="text" placeholder="类型名称" id="typeName" value="" maxlength="4"/>
    </p>
</div>
<div class="btn ue-clear">
	<a href="javascript:;" class="confirm">确定</a>
    <a href="javascript:;" class="clear">清空内容</a>
</div>
</body>
<script type="text/javascript" src="/login/js/jquery.js"></script>
<script type="text/javascript" src="/login/js/common.js"></script>
<script type="text/javascript" src="/login/js/WdatePicker.js"></script>
<script type="text/javascript">
$(".confirm").click(function(){
	var typeName = $.trim($("#typeName").val());
	$.ajax({
		   async:false,
		   data:{name:typeName},
		   dataType:"json",
		   url:"/admin/saveVoteType",
		   type:"POST",
		   success:function(data) {
			   if(data.errorType == 'SUCCESS'){
				   alert('保存成功');
				   window.location.href = "/admin/voteTypeList"
			   }else if(data.errorMessage!=null && data.errorMessage!=''){
				   alert(data.errorMessage);
			   }else{
				   alert("服务器繁忙...");
			   }
       }, 
       error:function(){
          console.log("服务器繁忙...");
       }
   })
});
</script>
</html>
