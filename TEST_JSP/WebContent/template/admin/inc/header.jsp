<%@page import="Model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>AdminCP | VinaEnter Edu</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="<%= request.getContextPath()%>/template/admin/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="<%= request.getContextPath()%>/template/admin/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="<%= request.getContextPath()%>/template/admin/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <script type="text/javascript" src="<%=request.getContextPath()%>/template/admin/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/template/admin/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/template/admin/js/checksearch.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/template/lib/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/template/admin/js/validate-form.js"></script>
</head>

<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/Admin/index">VinaEnter Edu</a>
            </div>
            <%
            	User userlogin = (User)session.getAttribute("userlogin");
            %>
            <div style="color: white;padding: 15px 50px 5px 50px;float: right;font-size: 16px;"><%if(userlogin != null){%> Xin chào, <b>Admin</b> <% }else{%>Xin chào, <b>Khách</b><%} %> &nbsp; <a href="<%=request.getContextPath() %>/Admin/logout" class="btn btn-danger square-btn-adjust"><%if(userlogin != null){%> Đăng xuất <% }else {%> Login <%} %></a> </div>
        </nav>
        <!-- /. NAV TOP  -->