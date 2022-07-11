<%@page import="utils.DefineUtil"%>
<%@page import="Model.Dao.CategoryDAO"%>
<%@page import="Model.bean.category"%>
<%@page import="Model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<%
					if (request.getAttribute("err") != null) {
				%>
				<div class="col-md-12">
					<h2 style="color: blue;">
						<span>Không tồn tại bài hát cần tìm kiếm</span>
					</h2>
				</div>
				<%
					}
				%>
				<h2>Quản lý bài hát</h2>
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
									<a href="<%=request.getContextPath()%>/Admin/song/insert" class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="get"
										action="<%=request.getContextPath()%>/Admin/song/search?trang=1">
										<input type="submit" name="search" value="Tìm kiếm" id = "sm-s"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" name="song" class="form-control input-sm" id = "keyword"
											value="<%if(request.getAttribute("song")!=null){String song = (String)request.getAttribute("song");%> <%=song %> <% } %>"
											placeholder="Nhập tên bài hát"
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
										<th>Tên bài hát</th>
										<th>Danh mục</th>
										<th>Lượt đọc</th>
										<th>Hình ảnh</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
                                	if(request.getAttribute("listsong") != null){
                                		ArrayList<Song> list = (ArrayList<Song>)request.getAttribute("listsong");
                                		for(Song song : list){ 
                                			category cate = new CategoryDAO().getCateGoryByID(song.getId_cat());
                                		%>
									<tr>
										<td><%=song.getId() %></td>
										<td class="center"><%=song.getName() %></td>
										<td class="center"><%=cate.getName() %></td>
										<td class="center"><%=song.getCounter() %></td>
										<td class="center"><img width="200px" height="200px"
											src="<%=request.getContextPath()%>/template/public/images/<%=song.getPicutre()%>"
											alt="<%=song.getName() %>" /></td>
										<td class="center"><a
											href="<%=request.getContextPath()%>/Admin/song/edit?idsong=<%=song.getId()%>"
											title="" class="btn btn-primary"><i class="fa fa-edit "></i>
												Sửa</a> <a
											href="<%=request.getContextPath()%>/Admin/song/delete?idsong=<%=song.getId()%>"
											title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?');"><i class="fa fa-pencil"></i>
												Xóa</a></td>
									</tr>

									<%		}
                                	}
                                %>

								</tbody>
							</table>
							<div class="row">
								<div class="col-sm-6">
									<%
                                		if(request.getAttribute("sotrang") != null){
                                			int sotrang = (Integer)request.getAttribute("sotrang");
                                    		int trang = (Integer)request.getAttribute("trang");
                                    		if(sotrang > 0 ){ %>
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">
										Hiển thị trang
										<%=trang %>
										của
										<%=sotrang %>
										trang
									</div>
									<%		}
                                		}
                                	%>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											<%
                                        	if(request.getAttribute("sotrang") != null){
                                        		int sotrang = (Integer)request.getAttribute("sotrang");
                                        		int trang = (Integer)request.getAttribute("trang");
                                        		int sd = trang%DefineUtil.NUMBER_PER_PAGE2;
                                        		String song = (String)request.getAttribute("song");
                                        		if(sd==0){
                                    				sd = DefineUtil.NUMBER_PER_PAGE2;
                                    			}
                                        		if((float)trang/DefineUtil.NUMBER_PER_PAGE2 > 1){ %>
											<li class="paginate_button previous "
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/Admin/song/search?trang=<%=trang-sd%>&song=<%=song%>">Trang
													trước</a></li>
											<%		}
                                        		if(sotrang > 0){
                                        			for(int i = trang - (sd-1) ; i <= trang + (DefineUtil.NUMBER_PER_PAGE2-sd) ; i++ ){
                                    					if(i > sotrang){
                                    						break;
                                    					}
                                    					else{
                                    						if(i == trang){ %>
											<li class="paginate_button active"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/Admin/song/search?trang=<%=i%>&song=<%=song%>"><%=i %></a></li>
											<%						}
                                    						else{ %>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/Admin/song/search?trang=<%=i%>&song=<%=song%>"><%=i %></a></li>
											<%							}
                                    					}
                                        			}
                                        			if((trang + DefineUtil.NUMBER_PER_PAGE2 - sd) < sotrang){ %>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/Admin/song/search?trang=<%=(trang+DefineUtil.NUMBER_PER_PAGE2+1-sd)%>&song=<%=song%>">Trang
													tiếp</a></li>
											<%			}
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/template/admin/inc/footer.jsp"%>