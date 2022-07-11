<%@page import="Model.bean.User"%>
<%@page import="Model.bean.category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa người dùng</h2>
            </div>
            <% 	if(request.getAttribute("err") != null){
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
        		if(request.getAttribute("user") != null){
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
                             <form role="form" action="" method="post" id="form">
                                    <div class="form-group">
                             
                                		<label for="name">Username</label>
                                        <input type="text" required="required" id="name" value="<%=user.getUsername() %>" disabled="disabled" name="name" class="form-control" />
                                        <%
	                                   		if (user.getUsername().equals("")) {
	                    						%>
	                    						<div class="col-md-12">
	                    							<p style="color: red;">Vui lòng nhập tên đăng nhập</p>
	                    						</div>
	                    						<%
	                    					}
                                   			%>
                                        <label for="name">Password</label>
                                        <input type="password" required="required" id="password" value="" name="password" class="form-control" />
	                    						<div class="col-md-12">
	                    							<p style="color: red;"></p>
	                    						</div>
                                        <label for="name">Fullname</label>
                                        <input type="text" required="required" id="fullname" value="<%=user.getFullname() %>" name="fullname" class="form-control" />
                                          <%
	                                   		if (user.getFullname().equals("")) {
	                    						%>
	                    						<div class="col-md-12">
	                    							<p style="color: red;">Vui lòng nhập tên người dùng</p>
	                    						</div>
	                    						<%
	                    					}
                                   			%>
                                    	<label for="name">Role</label>
                                    	<select name = "role" id = "role" class="form-control" >	
                                    		<option <%if(user.getRole()==3){%> selected="selected" <% } %> value = "3">Mod</option>
                                    		<option <%if(user.getRole()==2){%> selected="selected" <% } %> value = "2">User</option>
                                    	</select>
                                    	  <%
	                                   		if (user.getRole()==0) {
	                    						%>
	                    						<div class="col-md-12">
	                    							<p style="color: red;">Vui lòng nhập chức năng phù hợp</p>
	                    						</div>
	                    						<%
	                    					}
                                   			%>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>