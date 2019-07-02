<%--
  Created by IntelliJ IDEA.
  User: singe
  Date: 2019/6/18
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../admin/easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../admin/easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../admin/easyUI/css/demo.css">
    <script src="../admin/js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="../admin/js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function () {
            $('#tb').datagrid({
                url:'/UsersList',
                fitColumns:true,
                pagination:true,
                toolbar:'#toolbar',
                pageSize:4,
                pageList:[2,4,6,8],
                columns:[[
                    {field:'ck',checkbox:'true'},
                    {field:'id',title:'用户编号',width:50,align:'center'},
                    {field:'name',title:'用户名',width:50,align:'center'},
                    {field:'telephone',title:'电话号码',width:50,align:'center'},
                    {field:'isadmin',title:'是否为用户',width:50,align:'center'},
                    {title:'操作',width:50,align:'center'}
                ]]
            });
        })
    </script>
</head>
<body>
<table border="1" id="tb"></table>

<div id="toolbar" style="height: 40px">
    <a href="javascript:add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
    <a href="javascript:del()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>


</body>
</html>