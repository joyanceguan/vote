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
<div class="title"><h2>投票类型管理</h2></div>
<div class="table-operate ue-clear">
	<a href="/admin/voteType" class="add">添加</a>
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
                <th class="name">类型</th>
                <th class="operate">操作</th>
            </tr>
        </thead>
        <tbody>
            <#list list as voteType>
        	<tr>
            	<td class="num">${voteType.id}</td>
                <td class="name">${voteType.type_name}</td>
                <td class="operate">
                   <a href="/admin/deleteVoteType?id=${voteType.id}">删除</a>
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
</script>
</html>
