<%@page import="Model.bean.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <h2><span>Liên hệ</span></h2>
      <div class="clr"></div>
      <p>Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>
    </div>
    	<div>
    		
    	</div>
    <div class="article">
      <h2>Gửi liên hệ đến chúng tôi</h2>
      <div class="clr"></div>
      		<% 
    			if(request.getAttribute("msg") != null){ 
    				String msg = (String)request.getAttribute("msg");
    				if(msg.equals("1")){ %>
    					<h2 style="background-color: yellow; display: inline-block;">Đã có lỗi xảy ra</h2> 
    		<% 		}
    			}
    		%>
    		
    		<% 
    	    	Contact contact = new Contact(0,"","","","");
    			if(request.getAttribute("contact") != null){
    				contact = (Contact)request.getAttribute("contact");
    			}
    		%>
      <form action="<%=request.getContextPath()%>/lien-he" method="post" id="sendemail">
        <ol>
		  <li>
            <label for="name">Họ tên</label>
            <input id="name" value="<%=contact.getName()%>" required="required" name="name" class="text" />
          	<%
	          	if(contact.getName().equals("") && request.getAttribute("contact") != null){ %>
					<h2 style="color: red;">Vui lòng nhập tên liên hệ</h2>
			<% 	}
          	%>
          </li>
          <li>
            <label for="email">Email</label>
            <input id="email" value="<%=contact.getEmail()%>" required="required" name="email" class="text" />
            <%
	          	if(contact.getEmail().equals("") && request.getAttribute("contact") != null){ %>
					<h2 style="color: red;">Vui lòng nhập tên liên hệ</h2>
			<% 	}
          	%>
          </li>
          <li>
            <label for="website">Website</label>
            <input id="website" required="required" value="<%=contact.getWebsite()%>" name="website" class="text" />
          </li>
           	<%
	          	if(contact.getWebsite().equals("") && request.getAttribute("contact") != null){ %>
					<h2 style="color: red;">Vui lòng nhập tên liên hệ</h2>
			<% 	}
          	%>
          <li>
            <label for="message">Nội dung</label>
            <textarea id="message" required="required" name="message" rows="8" cols="50"><%=contact.getMessage()%> </textarea>
          </li>
           	<%
	          	if(contact.getMessage().equals("") && request.getAttribute("contact") != null){ %>
					<h2 style="color: red;">Vui lòng nhập tên liên hệ</h2>
			<% 	}
          	%>
          <li>
            <input type="image" name="imageField" id="imageField" src="<%=request.getContextPath() %>/template/public/images/submit.gif" class="send" />
            <div class="clr"></div>
          </li> 
        </ol>
      </form>
  <script type="text/javascript">
		$(document).ready(function (){
			$('#sendemail').validate({
				rules:{
					"email":{
						email: true
					},
					"website":{
						url: true
					}
				},
				messages:{
					"id":{
						email: "Nhập định dạng email",
					}
				}
			})
		})
	</script>
 <style>
	.error{
		color: red;
	}
</style>
<script>
    var editor1 = CKEDITOR.replace('message');
</script>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/template/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/template/public/inc/footer.jsp" %>
