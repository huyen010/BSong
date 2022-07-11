<%@page import="Model.bean.category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm danh mục</h2>
            </div>
            <% 	if(request.getAttribute("err") != null){
    			int a = (int)request.getAttribute("err");
    			if(a==1){ %>
    				<div class="col-md-12">
		                <h2>Đã có lỗi xảy ra</h2>
		            </div>
    	<% 		}
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
                             <%if(request.getAttribute("cate")!= null){
                                        	category cate = (category)request.getAttribute("cate"); %>
                                <form role="form" action="<%=request.getContextPath()%>/Admin/cat/edit?idcate=<%=cate.getId()  %>" method="post" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên danh mục</label>
                                        <input type="text" required="required" id="name" value="<%=cate.getName()%>" name="name" class="form-control" />
                               <% }%>
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