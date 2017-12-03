<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String path = request.getContextPath();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String date = df.format( new Date());
	
%>

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
		<form id="form-create"  method="POST" action="<%=path%>/system/orders"  class="form-horizontal form-label-left">
			
			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">门店 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<select name="store.id" id="storeId"  class="form-control">
						<c:forEach items="${stores}" var="v">
							<option value="${v.id }" >${v.name }</option>
						</c:forEach>
					</select>
				</div>
		<!-- 	</div>
			
			<div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">产品<span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<select name="product.id" id="productId"  class="form-control">
						<option>--请选择--</option>
						<c:forEach items="${products}" var="v">
							<option value="${v.id }" data-price="${v.markedPrice }" >${v.name }</option>
						</c:forEach>
					</select>
				</div>
		 </div>
			
			<div class="form-group"> 
				<label class="control-label col-md-2 col-sm-2 col-xs-4">数量 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="count" id="count" onchange="setPayedAmount();setContractValidityTimes();" required=true, type="number" value="1">
				</div>
			<!-- </div>
			
			<div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">折扣 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="discount" id="discount" onchange="setPayedAmount();" required=true, type="text" value="1">
				</div>
			</div>
			
			<input class="form-control"  name="distributer.id"  required=true, type="hidden" value="${distributer.id }">
		
			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">金额 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="payedAmount" id="payedAmount" disabled="disabled" required=true, type="text">
				</div>
			<!-- </div> -->
			
			
			<!-- <div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">成交日期 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="payedDate"  disabled="disabled" required=true, type="date" value=<%=date %>>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">合约开始日期 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="contract.start" id="contractStart" onchange="setContractEnd();" required=true, type="date" value=<%=date %>>
				</div>
			<!-- </div>
			
			<div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">合约结束日期 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="contract.end"  id="contractEnd" disabled="disabled" required=true, type="date" >
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">合约有效次数</label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="contract.validityTimes"  id="contractValidityTimes" disabled="disabled" >
				</div>
			<!-- </div>
			
			<div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">合约类型</label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="type"  id="contractType" disabled="disabled" >
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">合约中类</label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<input class="form-control"  name="subType"   id="contractSubType" disabled="disabled" >
				</div>
			<!-- </div>
			
			<div class="form-group"> -->
				<label class="control-label col-md-2 col-sm-2 col-xs-4">合约可使用门店 <span class="required">*</span></label>
				<div class="col-md-4 col-sm-4 col-xs-6">
					<select name="contract.validStoreIds" id="contractValidStoreIds"  class="form-control">
						<c:forEach items="${allStores}" var="v">
							<option value="${v.id }" >${v.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-2 col-sm-2 col-xs-4">备注</label>
				<div class="col-md-10 col-sm-10 col-xs-8">
					<textarea class="form-control" rows="2" name="comments">  </textarea>
				</div>
			</div>
			
						<!-- 添加支付方式 -->
			<div>
				<div class="form-group">
					<label class="control-label col-md-2 col-sm-2 col-xs-4">支付方式<span class="required">*</span></label>
					<div class="col-md-4 col-sm-4 col-xs-8">
						<select name="payes[0].payedMethod.id"  class="form-control">
							<c:forEach items="${payedMethods}" var="v">
								<option value="${v.id }" >${v.name }</option>
							</c:forEach>
						</select>
					</div>
					<label class="control-label col-md-2 col-sm-2 col-xs-4">金额<span class="required">*</span></label>
					<div class="col-md-4 col-sm-4 col-xs-8">
						<input class="form-control"  name="payes[0].amount" value="0" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2 col-sm-2 col-xs-4">支付方式<span class="required">*</span></label>
					<div class="col-md-4 col-sm-4 col-xs-8">
						<select name="payes[1].payedMethod.id"  class="form-control">
							<c:forEach items="${payedMethods}" var="v">
								<option value="${v.id }" >${v.name }</option>
							</c:forEach>
						</select>
					</div>
					<label class="control-label col-md-2 col-sm-2 col-xs-4">金额<span class="required">*</span></label>
					<div class="col-md-4 col-sm-4 col-xs-8">
						<input class="form-control"  name="payes[1].amount" value="0" >
					</div>
				</div>
								<div class="form-group">
					<label class="control-label col-md-2 col-sm-2 col-xs-4">支付方式<span class="required">*</span></label>
					<div class="col-md-4 col-sm-4 col-xs-8">
						<select name="payes[2].payedMethod.id"  class="form-control">
							<c:forEach items="${payedMethods}" var="v">
								<option value="${v.id }" >${v.name }</option>
							</c:forEach>
						</select>
					</div>
					<label class="control-label col-md-2 col-sm-2 col-xs-4">金额<span class="required">*</span></label>
					<div class="col-md-4 col-sm-4 col-xs-8">
						<input class="form-control"  name="payes[2].amount" value="0">
					</div>
				</div>
			</div>
			
			<div class="ln_solid"></div>
			<div class="form-group">
				<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-2">
					<button type="submit" class="btn btn-success">提交</button>
				</div>
			</div>

		</form>
	</div>

	<script type="text/javascript">
		var g_product;
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
			
			$('#contractValidStoreIds').val(${stores[0].id});
			
			$('#productId').change(function(){
				$.ajax({
					url: '<%=path%>'+ '/system/products/' + $(this).val(),
					type:'GET',
					success:function(res){
						if(res.result){
							console.log(JSON.stringify(res.data));
							g_product = res.data;
							g_price = g_product.markedPrice;
							
							/** 设置订单金额  **/
							setPayedAmount();
							
							/** 设置结束日期 **/
							setContractEnd();
							setContractValidityTimes();
							setContractType();
							setContractSubType();
						}
					}
					
				});
		 		
			});
			
		});
		
		/** 设置合约结束日期 **/
		function setContractEnd(){
			var date = new Date($('#contractStart').val());
			date.setMonth(date.getMonth() + g_product.usefulLife);
			$('#contractEnd').val(formatDate(date,'yyyy-MM-dd'));
		}
		
		/** 设置订单金额 **/
		function setPayedAmount(){
			var amount =g_product.markedPrice * $('#count').val() * $('#discount').val();
			console.log("payedAmount is " + amount);
			$('#payedAmount').val(amount);
		}
		
		/** 设置合约有效次数  **/
		function setContractValidityTimes(){
			var times = g_product.usefulTimes * $('#count').val();
			$('#contractValidityTimes').val(times);
		}
		
		/** 设置合约类型  **/
		function setContractType(){
			$('#contractType').val(translateCourseType(g_product.courseType));
		}
		
		/** 设置合约中类 **/
		function setContractSubType(){
			$('#contractSubType').val(translateSubCourseType(g_product.subCourseType));
		}
		
		/** 设置合约可使用门店 **/
		function setContractValidStoreIds(){
			/* $('#contractValidStoreIds').val($('storeId'.val())); */
		}
	</script>
</body>
</html>