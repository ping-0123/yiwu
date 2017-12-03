<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create post</title>

</head>
<body>

	<!-- modal header -->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">×</span>
		</button>
		<h4 class="modal-title" >新增岗位</h4>
	</div>
	<!-- /modal header -->

	<!-- modal body -->
	<div class="modal-body">
		<form id="form-create"  method="POST" action="./"  class="form-horizontal form-label-left">

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">产品名称<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<!--  <input type="text" class="form-control" placeholder="设置之后不可修改"  name="name" value="${post.name }"> -->
					<input  name="name" class="form-control col-md-7 col-xs-12"  required="required" type="text">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">产品卡类型<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="cardType" class="form-control">
						<c:forEach items="${cardTypes}" var="type">
							<option value="${type }" >${type.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">少儿Or成人卡<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="customerType" class="form-control">
						<c:forEach items="${customerAgeTypes}" var="type">
							<option value="${type }" >${type.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">业绩类型<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="performanceType" class="form-control">
						<c:forEach items="${performanceTypes}" var="type">
							<option value="${type }" >${type.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">产品标价 <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control"  name="markedPrice" required=true, type="text">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">品类</label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control"  name="dyRCP" required=true>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">有效期(月)<span class="required">*</span> </label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control"  name="usefulLife" required=true, type="number">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">有效次数(次) <span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control"  name="usefulTimes" required=true>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">有效课时数<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control"  name="usefulHours" required=true>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">上架日期<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control"  name="startDate" required=true, type="date">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">下架日期<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<input class="form-control"  name="endDate" required=true, type="date">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">舞种<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="dance.id" class="form-control">
						<c:forEach items="${dances}" var="dance">
							<option value="${dance.id }" >${dance.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
						
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">舞种等级<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="danceGrade.id" class="form-control">
						<c:forEach items="${danceGrades}" var="grade">
							<option value="${grade.id }" >${grade.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">合同类型<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="contractType.id" id="contractTypeId" class="form-control">
						<option >--请选择--</option>
						<c:forEach items="${contractTypes}" var="type">
							<option value="${type.id}" >${type.contractType.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			
 			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">合约类型</label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="courseType" id="courseType" class="form-control">
		<%-- 				<option >--请选择--</option>
						<c:forEach items="${courseTypes}" var="type">
							<option value="${type}" >${type.name }</option>
						</c:forEach> --%>
					</select>
				</div>
			</div>
			 
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">合约中类</label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="subCourseType"  id="subCourseType" class="form-control">
						<%-- <c:forEach items="${subCourseTypes}" var="type">
							<option value="${type}" >${type.name }</option>
						</c:forEach> --%>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">可使用范围<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="usableRangeType" class="form-control">
						<c:forEach items="${usableRangeTypes}" var="type">
							<option value="${type }" >${type.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">指定使用门店</label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="usableDepartments" class="form-control">
						<option></option>
						<c:forEach items="${stores}" var="store">
							<option value="${store.id }" >${store.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3 col-sm-3 col-xs-12">所属公司<span class="required">*</span></label>
				<div class="col-md-9 col-sm-9 col-xs-12">
					<select name="company.id" class="form-control">
						<c:forEach items="${companies}" var="company">
							<option value="${company.id }" >${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
					<button type="submit" class="btn btn-success">提交</button>
				</div>
			</div>

		</form>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			$('#form-create').submit(function(){
				$.ajax({
					url: $(this).attr("action"),
					type: $(this).attr("method"),
					data: $(this).serialize(),
					success:function(data){
						$('.modal-create').modal('hide');
						TABLE.order([CLOUMN_CREATE_TIME,'desc']).draw();
					}
				});
				return false;
			});
			
			$('#contractTypeId').change(function(){
				loadHtmlForCourseType($(this).val());
				$('#subCourseType').html('');
			});
			
			$('#courseType').change(function(){
				loadHtmlForSubCourseType($(this).val());
			});
		});

		
		function loadHtmlForSubCourseType(v_courseType){
			var html;
			$.ajax({
				url:"../courseTemplates/subCourseTypes?courseType=" + v_courseType,
				type:"GET",
				success:function(response){
					if(response.result){
						var types = response.data;
						for(var i=0; i<types.length;i++){
							html = html + '<option value="' +types[i] + '">' + translateSubCourseType(types[i]) + '</option>';
						}
					}else{
						html='';
					}
					$('#subCourseType').html(html);
				}
			});
		}
		
		function loadHtmlForCourseType(v_contractTypeId){
			var html = '<option >--请选择--</option>';
			$.ajax({
				url : '../eContractTypes/' + v_contractTypeId + '/contractType',
				type:'GET',
				success:function(res){
					if(res.result){
						var type = res.data;
						if(type == 'CLOSED' || type=='OPENED' || type=='PRIVATE'){
							html = html + '<option value="' +type + '">' + translateCourseType(type) + '</option>';
						}else{
							html='';
						}
					}else{
						html='';
					}
					
					$('#courseType').html(html);
				}
			});
		}
	</script>
</body>
</html>