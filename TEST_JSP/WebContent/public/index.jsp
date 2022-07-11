<%@page import="utils.DefineUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
    		if(request.getAttribute("msg") != null){ %>
    			<h2>Bài hát không tồn tại</h2>
    	<%	}
  %>
  	<%
  		if(request.getAttribute("listsong1")!= null){
  			ArrayList<Song> listsong1 = (ArrayList<Song>)request.getAttribute("listsong1");
  			for(Song song : listsong1){ %>
  			  <div class="article">
			      <h2><a href="<%=request.getContextPath()%>/chi-tiet?idsong=<%=song.getId()%>" title="<%=song.getName() %>"><%=song.getName() %></a></h2>
			      <p class="infopost">Ngày đăng: <%=song.getDatecreate()%>. Lượt xem: <%=song.getCounter()%> <a href="<%=request.getContextPath()%>/chi-tiet??idsong=<%=song.getId()%>" class="com"><span><%=song.getId() %></span></a></p>
			      <div class="clr"></div>
			      <div class="img"><img src="<%=request.getContextPath()%>/template/public/images/<%=song.getPicutre()%>" width="177" height="213" alt="<%=song.getName() %>" class="fl" /></div>
			      <div class="post_content">
			        <p>“<%=song.getPreview() %></p>
			        <p class="spec"><a href="<%=request.getContextPath()%>/chi-tiet?idsong=<%=song.getId()%>" class="rm">Chi tiết &raquo;</a></p>
			      </div>
		      <div class="clr"></div>
    </div>
  	<%		}
  		}
  	%>
  	<%
  		if(request.getAttribute("sotrang")!= null){
  			String name = "";
  			if(request.getAttribute("name") != null){
  				name =(String)request.getAttribute("name");
  			}
  			int sotrang = (int)request.getAttribute("sotrang");
  			int trang = (int)request.getAttribute("trang");
  			int sd = trang%DefineUtil.NUMBER_PER_PAGE2;
			if(sd==0){
  				sd = DefineUtil.NUMBER_PER_PAGE2;
  			}
  		
			if((float)trang/DefineUtil.NUMBER_PER_PAGE2 > 1){ %>
				<a href="<%=request.getContextPath()%>/Trang-chu?trang=<%=(trang-sd) %>&name=<%=name%>"><<</a>
	<%		}
  			if(sotrang > 0){
  			%>
  				<p class="pages"><small>Trang <%=trang %> của <%=sotrang %></small>
  			<%  for(int i = trang - (sd-1) ; i <= trang + (DefineUtil.NUMBER_PER_PAGE2-sd) ; i++){
  					if(i > sotrang){
  						break;
  					}
  					else{
  						if(i == trang){ %>
  						<span><%=i %></span>
  				<%		}
	  					else{ %>
	  						<a href="<%=request.getContextPath()%>/Trang-chu?trang=<%=i %>&name=<%=name%>"><%=i %></a>
	  			<%		}
  					}
  				}
	  			if((trang + DefineUtil.NUMBER_PER_PAGE2 - sd) < sotrang){ %>
	  				<a href="<%=request.getContextPath()%>/Trang-chu?trang=<%=(trang+DefineUtil.NUMBER_PER_PAGE2+1-sd) %>&name=<%=name%>">>></a>	
		<%		}
  			}
  		}
  	%>
  </div>
  <div class="sidebar">
      <%@ include file="/template/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/template/public/inc/footer.jsp" %>
