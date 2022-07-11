<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    	<%
    		if(request.getAttribute("msg") != null){ %>
    			<h2>Bài hát không tồn tại</h2>
    	<%	}
    	%>
    	<%
    		if(request.getAttribute("song")!=null){
    			Song song = (Song)request.getAttribute("song");
    			category cate = new CategoryDAO().getCateGoryByID(song.getId_cat()); %>
    			   <h1 style="color: green;"><%=cate.getName() %></h1>
			      <div class="clr"></div>
			      <p>Ngày đăng: <%=song.getDatecreate() %>. Lượt xem: <%=song.getCounter() %></p>
			      <img src="<%=request.getContextPath()%>/template/public/images/<%=song.getPicutre()%>" width="40" height="40" alt="" class="userpic" />
			      <h2 style="color: red;"><%=song.getName() %></h2>
			      <div class="vnecontent">
			      	<%=song.getPreview() %> <br>
			      	<%=song.getDetail() %>
			      </div>
    	<%	}
    	%>
    </div>
    <div class="article">
        <%
    		if(request.getAttribute("songrd")!=null){ %>
    			<h2>Bài viết liên quan</h2>
    			<div class="clr"></div>
    	<%		ArrayList<Song> listsong = (ArrayList)request.getAttribute("songrd");
    			for(Song song : listsong){
    				category cate = new CategoryDAO().getCateGoryByID(song.getId_cat()); %>
    				<div class="comment"> <a href="<%=request.getContextPath()%>/chi-tiet?idsong=<%=song.getId()%>"><img src="<%=request.getContextPath()%>/template/public/images/<%=song.getPicutre()%>" width="40" height="40" alt="" class="userpic" /></a>
			        <h2><a href="<%=request.getContextPath()%>/chi-tiet?idsong=<%=song.getId()%>"><%=song.getName() %></a></h2>
			        <p><%=song.getPreview() %></p>
			      </div>
    	 <%		}
			      
    		}
    	%>
  </div>
  </div>
  <div class="sidebar">
  <%@ include file="/template/public/inc//leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/template/public/inc//footer.jsp" %>
