<%@page import="Model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm liên hệ</h2>
            </div>
            <% 	if(request.getAttribute("error") != null){
    			int a = (int)request.getAttribute("err");
    			if(a==1){ %>
    				<div class="col-md-12">
		                <h2 style="background-color: yellow; display: inline-block;">Đã có lỗi xảy ra</h2>
		            </div>
    		<% 		}
    			}
        	%>
        	<%
        		User user = new User(0,"","","",0);
        		if(request.getAttribute("user") != null ){
        			user = (User)request.getAttribute("user");
        		}
        	%>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" action="<%=request.getContextPath()%>/Admin/user/insert" method="post" id="form">
                                    <div class="form-group">
                                        <label for="name">Username</label>
                                        <input type="text" required="required" id="name" value="" name="name" class="form-control" />
                                        <%
                                        if(user.getUsername().equals("") && request.getAttribute("user") != null){ %>
	                            			<div class="col-md-12">
	                    		                <p style="color: red;">Vui lòng nhập tên đăng nhập</p>
	                    		            </div>
	                            			<% 	}
                                        %>
                                        <label for="name">Password</label>
                                        <input type="password" required="required" id="password" value="" name="password" class="form-control" />
                                        <%
	                                        if(user.getPassword().equals("") && request.getAttribute("user") != null){ %>
		                            			<div class="col-md-12">
		                    		                <p style="color: red;">Vui lòng nhập password</p>
		                    		            </div>
		                            		<% 	}
                                        %>
                                        <label for="name">Fullname</label>
                                        <input type="text" required="required" id="fullname" value="" name="fullname" class="form-control" />
                                    	<%
	                                    	if(user.getFullname().equals("") && request.getAttribute("user") != null){ %>
		                            			<div class="col-md-12">
		                    		                <p style="color: red;">Vui lòng nhập tên người dùng</p>
		                    		            </div>
	                            			<% 	}
                                    	%>
                                    	<label for="name">Role</label>
                                    	<select name = "role" id = "role" class="form-control" >	
                                    		<option value = "3" <%if(user.getRole()==3){ %> selected="selected" <% } %>>Mod</option>
                                    		<option value = "2" <%if(user.getRole()==2){ %> selected="selected" <% } %>>User</option>
                                    	</select>
                                    	<%

                                		if(user.getRole()==0 && request.getAttribute("user") != null){ %>
                                			<div class="col-md-12">
                        		                <p style="color: red;">Vui lòng chọn chức vụ phù hợp</p>
                        		            </div>
                                			<% 	}
                                    	%>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>