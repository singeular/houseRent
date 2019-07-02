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
                url:'/DistrictList',
                fitColumns:true,
                pagination:true,
                toolbar:'#toolbar',
                //singleSelect:true,
                pageSize:4,
                pageList:[2,4,6,8],

                columns:[[
                    {field:'ck',checkbox:'true'},
                    {field:'id',title:'地区编号',width:50,align:'center'},
                    {field:'name',title:'地区',width:50,align:'center'},
                    {field:'opt',title:'操作',width:50,align:'center',
                        formatter: function(value,row,index){
                        return "<a href='javascript:selectStreet()' >显示街道</a>||<a href='javascript:deleteOne("+row.id+")' >删除</a>";
                        }
                    }
                ]]
            });
        })

        //街道信息


        //打开新增窗口
        function add() {
            $("#add").dialog("open").dialog("setTitle","新增地区")
        }

        //执行新增操作
        function saveD() {
            $('#addForm').form('submit', {
                    url:"/addDistrict",
            success:function(data){
                var obj=$.parseJSON(data);
                if(obj.result>0){
                    $("#add").dialog("close");
                    $.messager.alert('提示框','添加成功');
                }else {
                    $.messager.alert('提示框','添加失败');
                }
            }
        });
        }
        //打开删除对话框


        //打开修改窗口
        function edit() {
            var SelectRows=$("#tb").datagrid('getSelections');
            if(SelectRows.length!==1){
                $.messager.alert('提示框','请至少选择一行或不支持多行修改')
                return;
            }
            var SelectRow=SelectRows[0];
            $("#edit").dialog("open").dialog("setTitle","修改信息")
            $("#edit").form('load',SelectRow);
        }

        //执行修改
        function editD() {
            $('#editForm').form('submit', {
                url:"/editDistrict",
                success:function(data){
                    var obj=$.parseJSON(data);
                    if(obj.result>0){
                        $("#edit").dialog("close");
                        $("#tb").datagrid('reload');
                        $.messager.alert('提示框','修改成功');
                    }else {
                        $.messager.alert('提示框','修改有误');
                    }
                }
            });
        }
        function deleteOne(id) {
            $.messager.confirm('提示框','确认删除',function(result){
                if (result){
                    $.post("/deleteOne",{"id":id},function (data) {
                        if(data.result>0){
                            $("#tb").datagrid('reload');
                            $.messager.alert('提示框',"删除成功")
                        }else {
                            $.messager.alert('提示框',"系统出错")
                        }
                    },"json");
                }
            });

        }

        function selectStreet() {
            $("#street").dialog("open").dialog("setTitle","街道信息")
        }

        //关闭窗口
        function close(v) {
            $("#"+v).dialog("close")
        }

    </script>
</head>
<body>
<table border="1" id="tb"></table>

<div id="toolbar" style="height: 40px">
    <a href="javascript:add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
    <a href="javascript:edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
    <a href="javascript:del()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">批量删除</a>
</div>

<%--新增窗口--%>
<div id="add" class="easyui-dialog" buttons="#aa"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="addForm" method="post">
        <table>
            <tr>
                <td>地区:</td>
                <td><input type="text" name="name" id="name" /></td>
            </tr>
        </table>
    </form>
</div>

<%--修改窗口--%>
<div id="edit" class="easyui-dialog" buttons="#bb"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="editForm" method="post">
        <table>
            <tr>
                <td>编号:</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>地区:</td>
                <td><input type="text" name="name"/></td>
            </tr>
        </table>
    </form>
</div>

<%--确认删除窗口--%>
<div id="street" class="easyui-dialog" buttons="#cc"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <table id="tbStreet">?</table>
</div>


<div id="aa">
    <a href="javascript:saveD()" class="easyui-linkbutton">确认添加</a>
    <a href="javascript:close('Add')" class="easyui-linkbutton">取消</a>
</div>
<div id="bb">
    <a href="javascript:editD()" class="easyui-linkbutton">确认修改</a>
    <a href="javascript:close('edit')" class="easyui-linkbutton">取消</a>
</div>
<div id="cc">
    <a href="javascript:editD()" class="easyui-linkbutton">确认</a>
    <a href="javascript:close('street')" class="easyui-linkbutton">关闭</a>
</div>
</body>
</html>
