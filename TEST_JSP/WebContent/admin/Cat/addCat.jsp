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
            <% 	if(request.getAttribute("error") != null){
    			int a = (int)request.getAttribute("error");
    			if(a==2){ %>
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
                                <form role="form" action="<%=request.getContextPath()%>/Admin/cat/insert" method="post" id="formCat">
                                    <div class="form-group">
                                        <label for="name">Tên danh mục</label>
                                        <input type="text" required="required" id="name" value="" name="name" class="form-control" />
                                    	<%
                                    		if(request.getAttribute("name") != null){
                                    			String name = (String)request.getAttribute("name");
                                    			if(name.equals("")){ %>
                                    				<div class="col-md-12">
			                    		                <h3 style="color: red;">Vui lòng nhập tên danh mục</h3>
			                    		            </div>
                                    	<%		}
                                    		}
                                    	%>
                                    </div>
                                    <input type="submit" name="submit" class="btn btn-success btn-md" value="Thêm" />
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
<script type="text/javascript">
	$(document).ready(function () {
		$('#formCat').validate({
			rules: {
				"name": {
					required: true,
				}
			},
			messages: {
				"name": {
					required: "Nhập vô bạn ê",
				}
			}
		})
	})
</script>
<script>
    document.getElementById("category").classList.add('active-menu');
</script>

<!-- /. PAGE WRAPPER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>