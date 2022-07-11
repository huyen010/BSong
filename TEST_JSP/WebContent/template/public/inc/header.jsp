<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>BSong</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%= request.getContextPath()%>/template/public/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/template/public/css/coin-slider.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/template/public/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/template/public/js/script.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/template/public/js/coin-slider.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/template/public/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/template/public/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/template/public/js/checksearch.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/template/lib/ckeditor/ckeditor.js"></script>

</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="<%=request.getContextPath()%>/Trang-chu">BSong <small>Một dự án khóa JAVA tại VinaEnter Edu</small></a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li <%if(request.getAttribute("lienhe")==null){%> class="active" <% } %>><a href="<%=request.getContextPath()%>/Trang-chu"><span>Trang chủ</span></a></li>
          <li <%if(request.getAttribute("lienhe")!=null){%> class="active" <% } %>><a href="<%=request.getContextPath()%>/lien-he"><span>Liên hệ</span></a></li>
        </ul>
      </div>
      <div class="clr"></div>
      <div class="slider">
        <div id="coin-slider"><a href="#"><img src="<%=request.getContextPath()%>/template/public/images/slide1.jpg" width="935" height="307" alt="" /></a></div>
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">