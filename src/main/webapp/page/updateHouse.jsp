<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
    <script language="JavaScript">
        $(function () {
            showStreet($("#District").val())
            $("#District").change(function () {
                var did=$(this).val();
                showStreet(did)
            });
        });
        function showStreet(did) {
            $.post('/showStreet',{"did":did},function (data) {
                $("#Street>option").remove();
                for (var i = 0; i < data.length; i++) {
                    var option=$("<OPTION selected value="+data[i].id+">"+data[i].name+"</OPTION>")
                    $("#Street").append(option);
                }
            },"json");
        }
    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>

<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新房屋信息发布</DT>
            <DD class=past>填写房屋信息</DD></DL>
        <DIV class=box>

            <FORM id=add_action method=post name=add.action action="../updateHouse2" enctype="multipart/form-data">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>

                            <TD class=field>标　　题：</TD>

                            <TD>
                                <input type="hidden" name="id" value="${house.id}">
                                <INPUT id=add_action_title class=text type=text name=title value="${house.title}">
                            </TD>
                        </TR>

                        <TR>
                            <TD class=field>户　　型：</TD>
                            <TD><SELECT class=text name="typeId" id="type">
                                <c:forEach items="${types}" var="t">
                                    <OPTION <c:if test="${t.id==house.typeId}">selected="selected"</c:if> value=${t.id}>${t.name}</OPTION>
                                </c:forEach>
                            </SELECT>
                            </TD>
                        </TR>

                        <TR>
                            <TD class=field>面　　积：</TD>
                            <TD><INPUT id=add_action_floorage class=text type=text name=floorage value="${house.floorage}"></TD></TR>
                        <TR>
                            <TD class=field>价　　格：</TD>
                            <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
                        <TR>
                            <TD class=field>房产证日期：</TD>
                            <TD><INPUT class=text type=date name="pubdate" value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></f:formatDate>"></TD>
                        </TR>

                        <TR>
                            <TD class=field>位　　置：</TD>

                            <TD>
                                区：
                                <SELECT class=text name="districtId" id="District">
                                    <c:forEach items="${districts}" var="d">
                                        <OPTION <c:if test="${d.id==house.distrctid}">selected="selected"</c:if> value=${d.id}>${d.name}</OPTION>
                                    </c:forEach>
                                </SELECT>

                                街：
                                <SELECT class=text name="streetId" id="Street">
                                </SELECT>
                            </TD>
                        </TR>

                        <tr>
                            <TD class=field>上传图片：</TD>
                            <TD>
                                <input type="hidden" value="${house.path}" name="oldImg">
                                <img src="http://localhost:80/${house.path}" width="100" height="75">
                                <INPUT class=text type="file" name="image" value="${house.path}" >
                            </TD>

                        </tr>
                        <!--<tr>

                        <td class="field">坐  标：</td>
                        <td><input type="text" class="text" name="point" /></td>

                        </tr>-->
                        <!--  <tr><td class="field">Y 坐  标：</td>
                        <td><input type="text" class="text" name="point.y" /></td>

                        </tr>-->

                        <TR>
                            <TD class=field>联系方式：</TD>
                            <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
                        <TR>
                            <TD class=field>详细信息：</TD>
                            <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR></TBODY></TABLE>
                    <DIV class=buttons>
                        <INPUT  value="提交修改" type="submit">
                        <a href="/showUserHouse">取消</a>
                    </DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>

<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>

