<%--
  Created by IntelliJ IDEA.
  User: singe
  Date: 2019/7/1
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已审核</title>
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../easyUI/css/demo.css">
    <script src="../js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="../js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function () {
            $('#tb2').datagrid({
                url:'/selectHouseHadPass',
                fitColumns:true,
                pagination:true,
                toolbar:'#toolbar',
                //singleSelect:true,
                pageSize:4,
                pageList:[2,4,6,8],
                columns:[[
                    {field:'ck',checkbox:'true'},
                    {field:'id',title:'编号',width:50,align:'center'},
                    {field:'title',title:'标题',width:50,align:'center'},
                    {field:'price',title:'价格',width:50,align:'center'},
                    {field:'floorage',title:'面积',width:50,align:'center'},
                    {field:'dname',title:'地区',width:50,align:'center'},
                    {field:'sname',title:'街道',width:50,align:'center'},
                    {field:'tname',title:'户型',width:50,align:'center'},
                    {field:'ispass',title:'状态',width:50,align:'center',
                        formatter:function (valve,row,index) {
                        return "已审核";
                    }
                    },
                    {field:'opt',title:'操作',width:50,align:'center',
                        formatter: function(value,row,index){
                            return "<a href='javascript:cancelPass("+row.id+")' >取消审核</a>";
                        }
                    }
                ]]
            });
        })

    //    取消审核
        function cancelPass(id) {
            $.messager.confirm('提示框','确认取消审核',function(result){
                if (result){
                    $.post("/cancelPass",{"id":id},function (data) {
                        if(data.result>0){
                            $("#tb2").datagrid('reload');
                            $.messager.alert('提示框',"取消成功")
                        }else {
                            $.messager.alert('提示框',"系统出错")
                        }
                    },"json");
                }
            });
        }
    </script>
</head>
<body>
<table border="1" id="tb2"></table>
<div id="toolbar" style="height: 40px">
    <a href="javascript:cancelPassAll()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">批量取消审核</a>
</div>
</body>
</html>
