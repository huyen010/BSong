<%@page import="utils.DefineUtil"%>
<%@page import="Model.bean.Contact"%>
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
            		if(request.getParameter("error") != null){
            			String error = request.getParameter("error");
            			if(error.equals(1)){ %>
				<h2 style="color: yellow;">Đã có lỗi xảy ra</h2>
				<%		}
            			if(error.equals(2)){ %>
				<h2 style="color: yellow;">Id không tồn tại</h2>
				<%		}
            		}
            		if(request.getParameter("msg") != null){
            			int msg = Integer.parseInt(request.getParameter("msg"));
            			if(msg==1){ %>
				<h2 style="color: yellow;">Xóa liên hệ thành công</h2>
				<%		}
            		}
            		if(request.getAttribute("err") != null){%>
				<h2 style="color: yellow;">Liên hệ không tồn tại</h2>
				<% 	}
            	%>
				<h2>Quản lý liên hệ</h2>
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
								<div class="col-sm-6"></div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="get"
										action="<%=request.getContextPath()%>/Admin/contact/search?trang=1">
										<input type="submit" name="search" value="Tìm kiếm" id = "sm-s"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" name="contact" id = "keyword" class="form-control input-sm"
											placeholder="Nhập tên liên hệ"
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
										<th>Tên liên hệ</th>
										<th>Email</th>
										<th>Website</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
                                	if(request.getAttribute("listcontact")!=null){
                                		ArrayList<Contact> list = (ArrayList<Contact>)request.getAttribute("listcontact");
                                		for(Contact ct : list){ %>
									<tr>
										<td><%=ct.getId() %></td>
										<td class="center"><%=ct.getName() %></td>
										<td class="center"><%=ct.getEmail() %></td>
										<td class="center"><%=ct.getWebsite() %></td>
										<td class="center"><a
											href="<%=request.getContextPath()%>/Admin/contact/delete?idcontact=<%=ct.getId()%>"
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
                                        		int sd = trang%DefineUtil.NUMBER_PER_PAGE1;
                                        		if(sd==0){
                                    				sd = DefineUtil.NUMBER_PER_PAGE1;
                                    			}
                                        		if((float)trang/DefineUtil.NUMBER_PER_PAGE1 > 1){ %>
											<li class="paginate_button previous "
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/Admin/contact/index?trang=<%=trang-sd%>">Trang
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
												href="<%=request.getContextPath()%>/Admin/contact/index?trang=<%=i%>"><%=i %></a></li>
											<%						}
                                    						else{ %>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/Admin/contact/index?trang=<%=i%>"><%=i %></a></li>
											<%							}
                                    					}
                                        			}
                                        			if((trang + DefineUtil.NUMBER_PER_PAGE1 - sd) < sotrang){ %>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/Admin/contact/index?trang=<%=(trang+DefineUtil.NUMBER_PER_PAGE1+1-sd)%>">Trang
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/template/admin/inc/footer.jsp"%>