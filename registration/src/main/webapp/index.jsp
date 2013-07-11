<%@page import="com.ntrphanikumar.emanager.dtos.Employee"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.ntrphanikumar.emanager.registration.services.EmployeeService"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="headsection.jsp" />

<body>
	<div class="container">
        <legend style="margin-bottom:0px !important">Employees</legend>
		<button class="btn btn-primary" style="float: right;margin: -53px 3px 0 0" onclick="window.location.href = '<%=request.getContextPath() %>/create.jsp'">New</button>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
                    <th align="left">Id</th>
					<th align="left">Name</th>
					<th align="left">Email</th>
					<th align="left">Date of birth</th>
					<th align="left">Extension</th>
					<th width="25px;">&nbsp;</th>
				</tr>
			</thead>
            <tbody>
			<%
	        EmployeeService employeeService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(EmployeeService.class);
	        for(Employee employee:employeeService.getEmployees()){
			%>
				<tr>
                    <td style="vertical-align: top"><%=employee.getId() %></td>
					<td style="vertical-align: top"><a
						href="<%=request.getContextPath() %>/edit.jsp?id=<%=employee.getId()%>"><%=employee.getName()%></a></td>
					<td style="vertical-align: top"><%=employee.getEmail()%></td>
					<td style="vertical-align: top"><%=employee.getDob()%></td>
                    <td style="vertical-align: top"><%=employee.getExtn()%></td>
					<td style="vertical-align: top;">
                        <button style="padding-top: 3px;" class="btn btn-mini btn-danger" onclick="deleteField('<%=employee.getId()%>','<%=employee.getName()%>')"><i class="icon-trash"></i></button>
                    </td>
				</tr>
			<%
			    }
			%>
            </tbody>
		</table>
	</div>
	<script type="text/javascript">
		function deleteField(id,name){
			bootbox.confirm('Are you sure you want to delete '+name, function(r){
			    if(r){
			        $.ajax({
				           type: "DELETE",
				           url: '<%=request.getContextPath()%>/employees?id='+id,
				           success: function(data) {
				               window.location.href = '<%=request.getContextPath()%>/index.jsp';
				           }
			        });   
				}
			});
		}
	</script>
</body>
</html>