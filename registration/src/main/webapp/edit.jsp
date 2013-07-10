<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.commons.lang.time.DateUtils"%>
<%@page import="com.akrantha.emanager.dtos.Employee"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.akrantha.emanager.registration.services.EmployeeService"%>
<html lang="en">
<jsp:include page="headsection.jsp" />

<body>
	<div class="container">		
		<%
        EmployeeService employeeService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(EmployeeService.class);
		Employee employee =employeeService.getEmployeeById(Integer.parseInt(request.getParameter("id")));
		%>
		<form class="form-horizontal" id="edit-form">
			<input type="hidden" name="id" value="<%=employee.getId()%>" /> 
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<input type="text" class="input" name="name" id="name" value="<%=employee.getName() %>" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="email">Email</label>
					<div class="controls">
						<input type="text" class="input" name="email" id="email" value="<%=employee.getEmail() %>" />
					</div>
				</div>
                <div class="control-group">
                    <label class="control-label" for="extn">Extension</label>
                    <div class="controls">
                        <input type="text" class="input" name="extn" id="extn" value="<%=employee.getExtn() %>" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="dob">Date of Birth</label>
                    <div class="controls">
                        <input type="text" class="input datepicker" name="dob" id="dob" value="<%=new SimpleDateFormat("MM/dd/yyyy").format(employee.getDob())%>" />
                    </div>
                </div>            
				<div class="form-actions">  
            		<button class="btn btn-primary" onclick="editEmployee();return false;">Update</button>  
            		<button class="btn" onclick="window.location.href = '<%=request.getContextPath()%>/index.jsp'; return false;">Cancel</button>  
          		</div>
			</fieldset>
		</form>
	</div>
    <script type="text/javascript">
    $(function(){
        var nowTemp = new Date();
        var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
         
        var checkin = $('#dob').datepicker({
          onRender: function(date) {
            return date.valueOf() > now.valueOf() ? 'disabled' : '';
          }
        }).on('changeDate', function(ev) {
            checkin.hide();
        }).data('datepicker');
    });
    
    function editEmployee(){
	    $.ajax({
	           type: "PUT",
	           url: '<%=request.getContextPath()%>/employees',
	           data: $("#edit-form").serialize(),
	           success: function(data) {
	               window.location.href = '<%=request.getContextPath()%>/index.jsp';
	           }
        });   
    }

    </script>
</body>
</html>
