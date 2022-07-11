<%@page import="utils.DefineUtil"%>
<%@page import="Model.Dao.SongDAO"%>
<%@page import="Model.bean.category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp"%>
<%@ include file="/template/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<%
				if (request.getAttribute("err") != null) {
			%>
			<div class="col-md-12">
				<h2 style="color: blue;">
					<span>Không tồn tại danh mục cần tìm kiếm</span>
				</h2>
			</div>
			<%
				}
			%>
			<div class="col-md-12">
				<h2>Quản lý danh mục</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="col-sm-6">
									<a href="<%=request.getContextPath()%>/Admin/cat/insert"
										class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="get"
										action="<%=request.getContextPath()%>/Admin/cat/search?trang=1">
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" name="cate"
											value="<%if (request.getAttribute("cate") != null) {%><%=(String) request.getAttribute("cate")%> <%}%>"
											class="form-control input-sm" placeholder="Nhập tên danh mục"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên danh mục</th>
										<th>Số bài</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										if (request.getAttribute("listcate") != null) {
										SongDAO songdao = new SongDAO();
										ArrayList<category> list = (ArrayList<category>) request.getAttribute("listcate");
										for (category cate : list) {
									%>
									<tr>
										<td><%=cate.getId()%></td>
										<td class="center"><%=cate.getName()%></td>
										<td class="center"><%=songdao.getListSongById_Cat(cate.getId()).size()%></td>
										<td class="center"><a
											href="<%=request.getContextPath()%>/Admin/cat/edit?idcate=<%=cate.getId()%>"
											title="" class="btn btn-primary"><i class="fa fa-edit "></i>
												Sửa</a> <a
											href="<%=request.getContextPath()%>/Admin/cat/delete?idcate=<%=cate.getId()%>"
											title="" class="btn btn-danger"
											onclick="return confirm('Bạn có chắc chắn muốn xóa?');"><i
												class="fa fa-pencil"></i> Xóa</a></td>
									</tr>
									<%
										}
									}
									%>
								</tbody>
							</table>
							<div class="row">
								<div class="col-sm-6">
									<%
										if (request.getAttribute("sotrang") != null) {
										int sotrang = (Integer) request.getAttribute("sotrang");
										int trang = (Integer) request.getAttribute("trang");
										if (sotrang > 0) {
									%>
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">
										Hiển thị trang
										<%=trang%>
										của
										<%=sotrang%>
										trang
									</div>
									<%
										}
									}
									%>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											<%
												if (request.getAttribute("sotrang") != null) {
												String cate = "";
												if (request.getAttribute("cate") != null) {
													cate = (String) request.getAttribute("cate");
												}
												int sotrang = (Integer) request.getAttribute("sotrang");
												int trang = (Integer) request.getAttribute("trang");
												int sd = trang % DefineUtil.NUMBER_PER_PAGE1;
												if (sd == 0) {
													sd = DefineUtil.NUMBER_PER_PAGE1;
												}
												if ((float) trang / DefineUtil.NUMBER_PER_PAGE1 > 1) {
											%>
											<li class="paginate_button previous "
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/Admin/cat/search?trang=<%=trang - sd%>&cate=<%=cate%>">Trang
													trước</a></li>
											<%
												}
											if (sotrang > 0) {

											for (int i = trang - (sd - 1); i <= trang + (DefineUtil.NUMBER_PER_PAGE1 - sd); i++) {
												if (i > sotrang) {
													break;
												} else {
													if (i == trang) {
											%>
											<li class="paginate_button active"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/Admin/cat/search?trang=<%=i%>&cate=<%=cate%>"><%=i%></a></li>
											<%
												} else {
											%>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/Admin/cat/search?trang=<%=i%>&cate=<%=cate%>"><%=i%></a></li>
											<%
												}
											}
											}
											if ((trang + DefineUtil.NUMBER_PER_PAGE1 - sd) < sotrang) {
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/Admin/cat/search?trang=<%=(trang + DefineUtil.NUMBER_PER_PAGE1 + 1 - sd)%>&cate=<%=cate%>">Trang
													tiếp</a></li>
											<%
												}
											}
											}
											%>
										</ul>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>
<script>
	document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/template/admin/inc/footer.jsp"%>