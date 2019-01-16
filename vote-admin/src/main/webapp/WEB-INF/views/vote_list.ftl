<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/login/css/base.css" />
<link rel="stylesheet" href="/login/css/info-mgt.css" />
<link rel="stylesheet" href="/login/css/WdatePicker.css" />
<title>基础结构平台</title>
</head>

<body>
<div class="title"><h2>投票管理</h2></div>
<div class="query">
    <form action="/admin/voteList" method="post" id="form1">
	<div class="query-conditions ue-clear">
	    <div class="conditions staff ue-clear">
           <label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label>
           <input type="text" placeholder="可以直接输入" name="title" value="${(condition.title)!''}"/>
        </div>
        <div class="conditions name ue-clear">
            <label>状态：</label>
            <div class="select-wrap">
                <div class="select-title ue-clear" dataVal="${(condition.status)!''}" id="statusDiv"><span>${(condition.status.desc)!'请选择'}</span><i class="icon"></i></div>
                <ul class="select-list">
                     <li dataVal="">请选择</li>
                     <#list statusList as status>
                      <li dataVal="${status}">${status.desc}</li>
                     </#list>
                </ul>
            </div>
        </div>
    </div>
    <div class="query-btn ue-clear">
    	<a href="javascript:;" class="confirm">查询</a>
        <a href="javascript:;" class="clear">清空条件</a> 
    </div>
    <input type="hidden" name="status">
    </form>
</div>
<div class="table-operate ue-clear">
	<a href="/admin/vote" class="add">添加</a>
    <a href="javascript:;" class="del">删除</a>
    <a href="javascript:;" class="edit">编辑</a>
    <a href="javascript:;" class="count">统计</a>
    <a href="javascript:;" class="check">审核</a>
</div>
<div class="table-box">
	<table>
    	<thead>
        	<tr>
            	<th class="num">序号</th>
                <th class="name">标题</th>
                <th class="process">状态</th>
                <th class="node">选择类型</th>
                <th class="node">类型</th>
                <th class="time">时间</span></th>
                <th class="operate">操作</th>
            </tr>
        </thead>
        <tbody>
            <#list pageinfo.objects as vote>
        	<tr>
            	<td class="num">${vote.id}</td>
                <td class="name">${vote.title}</td>
                <td class="process">${vote.status.desc}</td>
                <td class="node">${vote.vote_choose_type.desc}</td>
                <td class="node">
                  <#list extendInfo.get(vote.id) as typeInfo>
                    ${typeInfo.type_name}
                  </#list>
                </td>
                <td class="time">
                   起始时间${vote.begin_time?string("yyyy-MM-dd HH:mm")}/${vote.end_time?string("yyyy-MM-dd HH:mm")}
                </td>
                <td class="operate">
                   <a href="/admin/vote?id=${vote.id}">查看</a>
                   <a href="/admin/updatevotestatus?id=${vote.id}&status=<#if vote.status == 'normal'>close<#else>normal</#if>"><#if vote.status == 'normal'>下架<#else>上架</#if></a>
                   <a href="/admin/deleteVote?id=${vote.id}">删除</a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<div class="pagination ue-clear"></div>
</body>
<script type="text/javascript"src="/login/js/jquery.js"></script>
<script type="text/javascript"src="/login/js/common.js"></script>
<script type="text/javascript"src="/login/js/WdatePicker.js"></script>
<script type="text/javascript"src="/login/js/jquery.pagination.js"></script>
<script type="text/javascript">
$(".select-title").on("click",function(){
	$(".select-list").hide();
	$(this).siblings($(".select-list")).show();
	return false;
})
$(".select-list").on("click","li",function(){
	var txt = $(this).text();
	$(this).parent($(".select-list")).siblings($(".select-title")).find("span").text(txt);
	var value = $(this).attr('dataVal');
	$(this).parent().prev().attr('dataVal',value);
})

$('.pagination').pagination(100,{
	callback: function(page){
		alert(page);	
	},
	display_msg: true,
	setPageNo: true
});

$("tbody").find("tr:odd").css("backgroundColor","#eff6fa");

showRemind('input[type=text], textarea','placeholder');

$(".confirm").click(function(){
	var status = $("#statusDiv").attr("dataVal");
	$("input[name='status']").val(status);
	$("#form1").submit();
})
</script>
</html>
