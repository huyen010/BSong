<%@page import="Model.bean.Song"%>
<%@page import="Model.Dao.SongDAO"%>
<%@page import="Model.Dao.CategoryDAO"%>
<%@page import="Model.bean.category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
	<%
			int idCat = 0;
			String name = "";
			Song songdetail = new Song(0,"","","",null,0,"",0);
			if(request.getAttribute("name")!=null ){ 
				name = (String)request.getAttribute("name") ;
			}
			if(request.getAttribute("idcate")!=null ){ 
				idCat = (int) request.getAttribute("idcate") ;
			}
			if(request.getAttribute("song")!=null ){ 
				songdetail = (Song) request.getAttribute("song") ;
			}
	%>
			 <form id="formsearch" name="formsearch" method="get" action="">
			    <span>
			    	<input name="name" class="editbox_search" id="editbox_search" maxlength="80" value="<%=name%>" type="search" />
			    	<input name="idcate" value="<%=idCat%>" id = "keyword" type="text" style="display: none"/>
			    </span>
			    <input name="button_search" src="<%= request.getContextPath()%>/template/public/images/search.jpg" class="button_search" type="image" />
		  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  	<%
  		ArrayList<category> list = new ArrayList<category>();
  		CategoryDAO ctgdao = new CategoryDAO();
  		list = ctgdao.getListCateGory();
  		for(category ctg : list){ 
  			if(ctg.getId() == idCat){%>	
  	    		<li><a href="<%=request.getContextPath() %>/danh-muc?idcate=<%=ctg.getId()%>" style="color: orange;"><%=ctg.getName() %></a></li>
  		<% 	}else{ %>
  			  	<li><a href="<%=request.getContextPath() %>/danh-muc?idcate=<%=ctg.getId()%>"><%=ctg.getName() %></a></li>
  		<%	}
  		}
  	%>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  	<%
  		SongDAO songdao = new SongDAO();
  		ArrayList<Song> listsong = new ArrayList<Song>();
  		listsong = songdao.getListSongNew();
  		for(Song song : listsong){
  			if(song.getId()==songdetail.getId()){ %>
  				<li><a href="<%=request.getContextPath()%>/chi-tiet?idsong=<%=song.getId()%>"  style="color: orange;"><%=song.getName()%></a></li>
  	<%	}else{ %>
  		  		<li><a href="<%=request.getContextPath()%>/chi-tiet?idsong=<%=song.getId()%>"><%=song.getName()%></a></li>
  	<% 		}
  		}
  	%>
  </ul>
</div>