<!DOCTYPE html>
<html lang="en">
<jsp:include page="headsection.jsp" />

<body>
	<div class="container">
		<form class="form-horizontal" id="add-form">
			<input type="hidden" name="action" value="create" />
			<fieldset>
				<legend style="margin-bottom:0px !important">New Employee</legend>
				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<input type="text" class="input" name="name" id="name" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="email">Email</label>
					<div class="controls">
						<input type="text" class="input" name="email" id="email" />
					</div>
				</div>
                <div class="control-group">
                    <label class="control-label" for="extn">Extension</label>
                    <div class="controls">
                        <input type="text" class="input" name="extn" id="extn" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="dob">Date of Birth</label>
                    <div class="controls">
                        <input type="text" class="input datepicker" name="dob" id="dob" />
                    </div>
                </div>            
				<div class="form-actions">  
            		<button class="btn btn-primary" onclick="createEmployee();return false;">Create</button>  
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
    
    function createEmployee(){
	    $.ajax({
	           type: "POST",
	           url: '<%=request.getContextPath()%>/employees',
	           data: $("#add-form").serialize(),
	           success: function(data) {
	               window.location.href = '<%=request.getContextPath()%>/index.jsp';
	           }
        });   
    }
    </script>
</body>
</html>
