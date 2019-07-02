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
    <link rel="stylesheet" type="text/css" href="../admin/easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../admin/easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../admin/easyUI/css/demo.css">
    <script src="../admin/js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="../admin/js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function () {
            $('#tb').datagrid({
                url:'/getHouseNoPass',
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
                        return "未审核";
                    }
                    },
                    {field:'opt',title:'审核',width:50,align:'center',
                        formatter: function(value,row,index){
                            return "<a href='javascript:selectStreet()' >审核</a>";
                        }
                    }
                ]]
            });
        })
    </script>
</head>
<body>
<table border="1" id="tb"></table>
</body>
</html>
