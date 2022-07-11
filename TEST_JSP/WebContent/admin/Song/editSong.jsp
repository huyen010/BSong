<%@page import="java.awt.geom.Path2D"%>
<%@page import="Model.bean.category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp"%>
<%@ include file="/template/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<%
					if (request.getAttribute("error") != null) {
					String error = (String) request.getAttribute("error");
					if (error.equals("1")) {
				%>
				<h2 style="background-color: yellow; display: inline-block;">Đã có lỗi xảy ra</h2>
				<%
					}
				}
				%>
				<%
					Song song = (Song) request.getAttribute("song");
					ArrayList<category> listcate = (ArrayList<category>) request.getAttribute("listcate");
				%>
				<h2>Sửa bài hát</h2>
			</div>
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
								<form role="form" action="" method="post"
									enctype="multipart/form-data" id="form">
									<div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											required="required" id="name" value="<%=song.getName()%>"
											name="name" class="form-control" />
											<%
	                                   		if (song.getName().equals("")) {
	                    						%>
	                    						<div class="col-md-12">
	                    							<p style="color: red;">Vui lòng nhập tên bài hát</p>
	                    						</div>
	                    						<%
	                    					}
                                   			%>
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label> <select
											id="category" name="category" class="form-control">
											<%
												for (category cate : listcate) {
												if (cate.getId() == song.getId_cat()) {
											%>
											<option selected="selected" value="<%=cate.getId()%>"><%=cate.getName()%></option>
											<%
												} else {
											%>
											<option value="<%=cate.getId()%>"><%=cate.getName()%></option>
											<%
												}
											}
											%>
											<%	
	                                        if (song.getId_cat()==0) {
	                    						%>
	                    						<div class="col-md-12">
	                    							<p style="color: red;">Vui lòng chọn danh mục phù hợp</p>
	                    						</div>
	                    						<%
	                    					}
                                       		 %>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <img width="100px"
											height="100px"
											src="<%=request.getContextPath()%>/template/public/images/<%=song.getPicutre()%>"
											alt="<%=song.getName()%>" /> <br> <br> <input
											type="file" value="" name="picture" />
											 <%
		                                    	if (song.getPicutre().equals("")) {
		                    						%>
		                    						<div class="col-md-12">
		                    							<p style="color: red;">Vui lòng chọn hình ảnh</p>
		                    						</div>
		                    						<%
		                    					}
                                        	%>
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" required="required"
											class="form-control" rows="3" name="preview"><%=song.getPreview()%></textarea>
											 <%
		                                        if (song.getPreview().equals("")) {
		                    						%>
		                    						<div class="col-md-12">
		                    							<p style="color: red;">Vui lòng nhập mô tả bài hát</p>
		                    						</div>
		                    						<%
		                    					}
	                                        %>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" required="required" class="form-control"
											id="detail" rows="5" name="detail"><%=song.getDetail()%></textarea>
											 <%
		                                        if (song.getDetail().equals("")) {
		                    						%>
		                    						<div class="col-md-12">
		                    							<p style="color: red;">Vui lòng nhập chi tiết bài hát</p>
		                    						</div>
		                    						<%
		                    					}
		                                        %>
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
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
	document.getElementById("song").classList.add('active-menu');
</script>
<script>
    var editor1 = CKEDITOR.replace('detail');
    var editor2 = CKEDITOR.replace('preview');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/template/admin/inc/footer.jsp"%>