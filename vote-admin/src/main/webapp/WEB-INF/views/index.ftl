<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/login/css/base.css" />
<link rel="stylesheet" type="text/css" href="/login/css/jquery.dialog.css" />
<link rel="stylesheet" href="/login/css/index.css" />

<title>基础结构平台</title>
</head>

<body>
<div id="container">
	<div id="hd">
    	<div class="hd-wrap ue-clear">
        	<div class="top-light"></div>
            <h1 class="logo"></h1>
            <div class="login-info ue-clear">
                <div class="welcome ue-clear"><span>欢迎您,</span><a href="javascript:;" class="user-name">Admin</a></div>
                <div class="login-msg ue-clear">
                    <a href="javascript:;" class="msg-txt">消息</a>
                    <a href="javascript:;" class="msg-num">10</a>
                </div>
            </div>
            <div class="toolbar ue-clear">
                <a href="javascript:;" class="home-btn">首页</a>
                <a href="javascript:;" class="quit-btn exit"></a>
            </div>
        </div>
    </div>
    <div id="bd">
    	<div class="wrap ue-clear">
        	<div class="sidebar">
            	<h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav">
                	<li class="office current"><div class="nav-header"><a href="javascript:;" date-src="helloworld?page=home" class="ue-clear"><span>日常办公</span><i class="icon"></i></a></div></li>
                    <li class="gongwen"><div class="nav-header"><a href="javascript:;" class="ue-clear"><span>公文起草</span><i class="icon"></i></a></div></li>
                    <li class="nav-info">
                    	<div class="nav-header"><a href="javascript:;" class="ue-clear"><span>导航信息管理</span><i class="icon"></i></a></div>
                        <ul class="subnav">
                        	<li><a href="javascript:;" date-src="/helloworld?page=info-reg">信息录入</a></li>
                            <li><a href="javascript:;" date-src="/helloworld?page=info-mgt">信息管理</a></li>
                            <li><a href="javascript:;" date-src="/helloworld?page=info-det">领导值岗管理</a></li>
                            <li><a href="javascript:;">中层领导管理</a></li>
                            <li><a href="javascript:;">领导值班记录</a></li>
                            <li><a href="javascript:;" date-src="/admin/voteList">投票列表</a></li>
                            <li><a href="javascript:;" date-src="/admin/voteTypeList">投票类型</a></li>
                        </ul>
                    </li>
                    <li class="system">
                    	<div class="nav-header"><a href="javascript:;" class="ue-clear"><span>系统管理</span><i class="icon"></i></a></div>
                    	<ul class="subnav">
                        	<li><a href="javascript:;" date-src="info-reg.html">角色管理</a></li>
                            <li><a href="javascript:;" date-src="info-mgt.html">权限管理</a></li>
                            <li><a href="javascript:;" date-src="info-det.html">用户管理</a></li>
                            <li><a href="javascript:;" date-src="info-det.html">用户日志<2期></a></li>
                            <li><a href="javascript:;" date-src="info-det.html">修改密码<2期></a></li>
                            <li><a href="javascript:;" date-src="info-det.html">权限申请<2期></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="content">
            	<iframe src="/helloworld?page=home" id="iframe" width="100%" height="100%" frameborder="0"></iframe>
            </div>
        </div>
    </div>
    <div id="ft" class="ue-clear">
    	<div class="ft-left">
            <span>中国移动</span>
            <em>Office&nbsp;System</em>
        </div>
        <div class="ft-right">
            <span>Automation</span>
            <em>V2.0&nbsp;2014</em>
        </div>
    </div>
</div>
<div class="exitDialog">
	<div class="dialog-content">
    	<div class="ui-dialog-icon"></div>
        <div class="ui-dialog-text">
        	<p class="dialog-content">你确定要退出系统？</p>
            <p class="tips">如果是请点击“确定”，否则点“取消”</p>
            
            <div class="buttons">
                <input type="button" class="button long2 ok" value="确定" />
                <input type="button" class="button long2 normal" value="取消" />
            </div>
        </div>
        
    </div>
</div>
</body>
<script type="text/javascript" src="/login/js/jquery.js"></script>
<script type="text/javascript" src="/login/js/common.js"></script>
<script type="text/javascript" src="/login/js/core.js"></script>
<script type="text/javascript" src="/login/js/jquery.dialog.js"></script>
<script type="text/javascript" src="/login/js/index.js"></script>

</html>
