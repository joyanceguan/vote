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
        <input type="text" placeholder="名称" id="title" value="${(vote.title)!''}"/>
    </p>
    <div class="short-input select ue-clear">
    	<label>选择类型：</label>
        <div class="select-wrap">
        	<div class="select-title ue-clear" id="vote_choose_type" dataVal="${(vote.vote_choose_type)!'single'}"><span>单选</span><i class="icon"></i></div>
            <ul class="select-list">
                <#list voteChooseTypes as voteChooseType>
                   <li dataVal="${voteChooseType}">${voteChooseType.desc}</li>
                </#list>
            </ul>
        </div>
    </div>
    <div class="short-input select ue-clear">
    	<label>是否登录：</label>
        <div class="select-wrap">
        	<div class="select-title ue-clear" id="isLogin" dataVal="${(vote.voteRule.loginLimit)!'true'}">
        	<span><#if vote.voteRule.loginLimit?? && vote.voteRule.loginLimit==false>否<#else>是</#if></span><i class="icon"></i></div>
            <ul class="select-list">
                <li dataVal="true">是</li>
            	<li dataVal="false">否</li>
            </ul>
        </div>
    </div>
    <div id="everyoneLimit" <#if vote.voteRule.loginLimit ?? && vote.voteRule.loginLimit == false>style="display:none"</#if>>
    <p class="short-input ue-clear">
    	<label>每人限投：</label>
        <input type="text" placeholder="每人限投" id="everyone_count" onkeyup="this.value=this.value.replace(/\D/g,'')" value="<#if vote.voteRule.everyoneCount!=0>${vote.voteRule.everyoneCount}</#if>"/>
        <span>次数</span>
    </p>
    <p class="short-input ue-clear">
    	<label>投票频率：</label>
        <input type="text" placeholder="时间（分钟）" style="width: 104px; margin-right: 10px;" id="everyone_time" onkeyup="this.value=this.value.replace(/\D/g,'')" value="<#if vote.voteRule.everyoneTime!=0>${vote.voteRule.everyoneTime}</#if>"/>
        <input type="text" placeholder="次数" style="width: 104px;" id="everyone_rate_count" onkeyup="this.value=this.value.replace(/\D/g,'')" value="<#if vote.voteRule.everyoneRateCount!=0>${vote.voteRule.everyoneRateCount}</#if>"/>
        <span>时间以分钟为单位，例如每天一次 60*24 1</span>
    </p>
    </div>
    <div id="ipLimit" <#if vote.voteRule.loginLimit ?? && vote.voteRule.loginLimit == false><#else>style="display:none"</#if>>
    <p class="short-input ue-clear">
	    <label>ip限投：</label>
        <input type="text" placeholder="ip限投次数" id="ip_count" onkeyup="this.value=this.value.replace(/\D/g,'')" value="<#if vote.voteRule.ipCount!=0>${vote.voteRule.ipCount}</#if>"/>
        <span>次数 当投票不限制登录时可用</span>
    </p> 
    <p class="short-input ue-clear">
	    <label>ip投票频率：</label>
        <input type="text" placeholder="时间（分钟）" style="width: 104px; margin-right: 10px;" id="ip_time" onkeyup="this.value=this.value.replace(/\D/g,'')" value="<#if vote.voteRule.ipTime!=0>${vote.voteRule.ipTime}</#if>"/>
        <input type="text" placeholder="次数" style="width: 104px;" id="ip_rate_count" onkeyup="this.value=this.value.replace(/\D/g,'')" value="<#if vote.voteRule.ipRateCount!=0>${vote.voteRule.ipRateCount}</#if>"/>
        <span>时间以分钟为单位，例如每天一次 60*24 1</span>
    </p>
    </div>
    <p class="short-input ue-clear">
    	<label>投票时间：</label>
        <input type="text" placeholder="开始时间" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d'});" style="width: 104px; margin-right: 10px;" id="beginTime" value='${(vote.begin_time?string("yyyy-MM-dd HH:mm"))!""}'/>
        <input type="text" placeholder="结束时间" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginTime\')}'});" style="width: 104px;" id="endTime" value='${(vote.end_time?string("yyyy-MM-dd HH:mm"))!""}'/>
        <span><em>*</em>请输入正确的时间格式</span>
    </p>
    <div class="long-input select ue-clear" style="">
    	<label>选项：</label>
    	<div style="padding-left: 82px;">
    	
    	<#if vote.id??>
    	  <#list vote.voteOptionMini as voteOption>
    	    <p name="chooseItem" class="short-input ue-clear" style="padding-left:0px">
              <input type="text" placeholder="序号" style="width:25px;text-align:center;margin-right:10px" name="sorts" value="${voteOption.sort}"/>
              <input type="text" placeholder="选项"style="width:414px;margin-right:10px" name="options" value="${voteOption.option}"/>
    	      <img onclick="addItem(this)" src="/login/images/add.png" style="width: 20px; height: 20px; vertical-align: text-top;"/>
    	      <img onclick="delItem(this)" src="/login/images/close4.png" style="width: 20px; height: 20px; vertical-align: text-top;"/>
            </p>
    	  </#list>
        <#else>	  
            <p name="chooseItem" class="short-input ue-clear" style="padding-left:0px">
              <input type="text" placeholder="序号" style="width:25px;text-align:center;margin-right:10px" name="sorts"/>
              <input type="text" placeholder="选项"style="width:414px;margin-right:10px" name="options"/>
	          <img onclick="addItem(this)" src="/login/images/add.png" style="width: 20px; height: 20px; vertical-align: text-top;"/>
	          <img onclick="delItem(this)" src="/login/images/close4.png" style="width: 20px; height: 20px; vertical-align: text-top;"/>
            </p>
    	</#if>
	    </div>
    </div>
    <p class="short-input ue-clear">
      <label>类型：</label>
      <#list voteTypes as type>
        <span><input type="checkbox" name="types" value="${type.id}">${type.type_name}&nbsp;&nbsp;</span>
      </#list>
    </p>
    <p class="short-input ue-clear">
    	<label>投票说明：</label>
        <textarea placeholder="请输入内容（限制300字）" id="vote_explain">${(vote.vote_explain)!''}</textarea>
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
$(".select-title").on("click",function(){
	$(this).next().toggle();
	return false;
});
$(".select-list").on("click","li",function(){
	var txt = $(this).text();
	$(this).parent().prev().find("span").text(txt);
	var value = $(this).attr('dataVal');
	$(this).parent().prev().attr('dataVal',value);
	if(txt == '是'){
		$("#everyoneLimit").show();
		$("#ipLimit").hide();
	}else{
		$("#everyoneLimit").hide();
		$("#ipLimit").show();
	}
});
function addItem(that_){
	var that = $(that_).parent();
	that.after(that.prop("outerHTML"));
}
function delItem(that_){
 	if($("p[name='chooseItem']").length>1){
 		$(that_).parent().remove();
 	}else{
 		alert("选项至少保留1个");
 	}
}

$("#vote_explain").on("input propertychange", function() {
    var $this = $(this),
        _val = $this.val(),
        count = "";
    if (_val.length > 300) {
        $this.val(_val.substring(0, 300));
    }
});


showRemind('input[type=text], textarea','placeholder');

var contextPath = "${contextPath}";
var vote_id = "${vote.id}"
$(".confirm").click(function(){
	var title = $.trim($("#title").val());
	var vote_choose_type = $("#vote_choose_type").attr('dataVal');
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var isLoginLimit = $("#isLogin").attr('dataVal');
	var everyone_count = $("#everyone_count").val();
	var everyone_time = $("#everyone_time").val();
	var everyone_rate_count = $("#everyone_rate_count").val();
	var ip_count = $("#ip_count").val();
	var ip_time = $("#ip_time").val();
	var ip_rate_count = $("#ip_rate_count").val();
	var vote_explain = $("#vote_explain").val();
	var types =  $("input[name='types']:checked");
	var typeList = new Array();
	
	var everyoneTotalLimit = false;
	var everyoneRateLimit = false;
	var ipTotalLimit = false;
	var ipRateLimit = false;
	
	if(title == null || title == '' || title.length > 300){
		alert('标题不能为空');
		return;
	}
	if(types == null || types.length == 0){
		alert('请选择至少一个类型');
		return;
	}else{
		 for(var i=0;i<types.length;i++){
			 typeList.push(parseInt($(types[i]).val()));
		 }
	}
	
	if(isLoginLimit == 'true'){
		if(everyone_count!='' && parseInt(everyone_count)>0){
			everyoneTotalLimit = true;
		}
		if(everyone_time!='' && everyone_rate_count!='' && parseInt(everyone_time) > 0 && parseInt(everyone_rate_count) > 0) {
			everyoneRateLimit = true;
		}
	}else{
		if(ip_count!='' && parseInt(ip_count)>0){
			ipTotalLimit = true;
		}
		if(ip_time!='' && ip_rate_count!='' && parseInt(ip_time) > 0 && parseInt(ip_rate_count) > 0) {
			ipRateLimit = true;
		}
	}
	
	//选项
	var options = $("input[name='options']");
	var sorts = $("input[name='sorts']");
	if(options == null || sorts == null || options.length != sorts.length){
		alert('错误的选项信息');
		return;
	}
	for(var i = 0;i < options.length; i++){
		var optionDesc = $.trim($(options[i]).val());
		var sort = $(sorts[i]).val();
		if(optionDesc == null || optionDesc == '' || optionDesc.length > 200){
			alert('无效的选项信息');
			return;
		}
		if(sort == ''){
			alert('无效的排序信息');
			return;
		}
	}
	
	//组装入参
	var param = {};
	var vote = {};
	vote.title = title;
	vote.beginTime = beginTime;
	vote.endTime = endTime;
	vote.voteChooseType = vote_choose_type;
	vote.voteExplain = vote_explain;
	vote.voteId = vote_id;
	param.vote = vote;
	
	var optionArray = [];
	for(var i = 0;i < options.length; i++){
		var optionDesc = $.trim($(options[i]).val());
		var sort = $(sorts[i]).val();
		var voteOptions = {};
		voteOptions.option_desc = optionDesc;
		voteOptions.sort = sort;
		//先写死是文字
		voteOptions.vote_option_type = "character";
		optionArray.push(voteOptions);
	}
	param.options = optionArray;
	param.types = typeList;
	
	if(isLoginLimit == 'true' || ipTotalLimit || ipRateLimit){
		var voteRule = {};
		voteRule.loginLimit = isLoginLimit;
		voteRule.everyoneTotalLimit = everyoneTotalLimit;
		voteRule.everyoneRateLimit = everyoneRateLimit;
		voteRule.ipTotalLimit = ipTotalLimit;
		voteRule.ipRateLimit = ipRateLimit;
		if(isLoginLimit == 'true'){
			if(everyoneTotalLimit){
				voteRule.everyoneCount = everyone_count;
			}
			if(everyoneRateLimit){
				voteRule.everyoneTime = everyone_time;
				voteRule.everyoneRateCount = everyone_rate_count;
			}
		}else{
			if(ipTotalLimit){
				voteRule.ipCount = ip_count;
			}
			if(ipRateLimit){
				voteRule.ipTime = ip_time;
				voteRule.ipRateCount = ip_rate_count;
			}
		}
		param.voteRule = voteRule;
	}
	
	$.ajax({
		   async:false,
		   data:JSON.stringify(param),
		   dataType:"json",
		   url:"/admin/saveVote",
		   type:"POST",
		   contentType:"application/json",
		   success:function(data) {
			   if(data.errorType == 'SUCCESS'){
				   alert('保存成功');
				   window.location.href = "/admin/voteList"
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
