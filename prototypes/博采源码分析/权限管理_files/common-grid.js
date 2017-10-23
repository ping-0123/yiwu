if (App == null) {
    App = {};
}

/**
 * 表格定义，需要jqgrid支持
 * 用法：
 * 		var grid = new App.GridEx();
 * 		var jqgrid = grid.init({
 *			url			: ,//请求列表数据的url，必须
 *			colNames	:['名称','排序', 'oid'],//表格显示的列名，必须
 *			colModel	:[//显示数据，必须（常用属性：name列显示的名称；index传到服务端用来排序的列名；width列宽度；align对齐方式；sortable是否可排序）
 *				{name:'name',index:'name'},
 *				{name:'order',index:'order',width:60,formatter:function(v, o, rd){return v;}},//formatter格式化显示在表格中的数据
 *				{name:'oid', hidden: true}
 *			]
 *		});
 *		利用init方法返回的对象可以直接执行原生的方法(也可以用grid.getGrid()方法获取后再执行原生API方法)
 *		设置表格宽度：grid.getGrid().setGridWidth(width);或jqgrid.setGridWidth(width);
 *		在tab页中使用的时候,默认显示的tab页grid能自动宽度,但其他tab页就不能自动宽度了.解决方法:在页面中能自动全宽的元素上加上样式gridwidth,如:<div id="tab_demo" class="HuiTab gridwidth">
 * 其他常用可选参数：
 * 		isPage	: 是否分页，默认true
 * 		dataType: 从服务器端返回的数据类型，默认json
 * 		gridDom	: 定义表格用的html元素，默认是一个id为grid-table的html元素
 *		pageDom	: 定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置，默认是一个id为grid-pager的html元素
 * 		mtype	: ajax提交方式。POST或者GET，默认POST
 * 		rowNum	: 每页显示记录数
 * 		height	: 表格高度
 * 		multiselect	: 是否可以多选，默认true
 * 		rownumbers	: 是否显示左侧行号，默认false
 * 		autowidth	: 是否自动列宽，默认true
 * 
 * 更多用法请参考jqgrid组件
 * 
 * @author tongpuxin
 */
App.GridEx = function() {
	this._defConfig = {
		url 		: '',
		dataType	: 'json',//从服务器端返回的数据类型
		gridDom		: '#grid-table',
		pageDom		: '#grid-pager',
		isPage		: true,
		multiselect	: true,
		rownumbers	: false,
		autowidth	: true,
		viewrecords : true,//是否在浏览导航栏显示记录总数
		pagerpos	: 'center',//指定分页栏的位置
		altRows		: true,//设置表格 zebra-striped 值
//		toppager	: true,//是否显示表格顶部分页工具条
	 	multiboxonly: false//只有当multiselect=true起作用，当multiboxonly为ture时只有选择checkbox才会起作用
//		direction	: "rtl",//表格中文字的显示方向，从左向右（ltr）或者从右向左（rtr
	};
	this.config = {};
};
App.GridEx.prototype = {
	/**
	 * 初始化表格
	 * @param {} config
	 * @return {}	config
	 */
	init : function(config){
		$.jgrid.defaults.styleUI = 'Bootstrap';
		config = $.extend({}, this._defConfig, config || {});//不能破坏已有的对象_defConfig
		if(config.gridDom.indexOf('#') == -1)config.gridDom = '#' + config.gridDom;
		if(config.pageDom.indexOf('#') == -1)config.pageDom = '#' + config.pageDom;
		var postData = $.extend(config.postData||{}, {
			isPage	: config.isPage
		});
		var loadCompleteFN = config.loadComplete;
		config = $.extend(config, {
			height		: config.height || 'auto',
			datatype	: config.dataType || 'json',
			mtype		: config.mtype || 'post',//ajax提交方式。POST或者GET，默认GET
			pager 		: config.isPage ? config.pageDom : '',//分页div
			rowNum		: config.isPage ? this.getRowNum(config) : 0,//每页显示记录数
			rowList		: [10,15,20,30,50,100,200],//用于改变显示行数的下拉列表框的元素数组。
		
			loadComplete: function(result) {
				if($.isFunction(loadCompleteFN)){
					loadCompleteFN(result);
				}
			},
			prmNames : {
				page		: "pageNo",    // 表示请求页码的参数名称  
			    rows		: "limit",    // 表示请求行数的参数名称  
			    sort		: "sort", 	// 表示用于排序的列名的参数名称  
			    order		: "dir", 	// 表示采用的排序方式的参数名称
			    totalrows	: "totalCount" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的
			},
			jsonReader : { 
				root	: "result",// json中代表实际模型数据的入口
				page	: "pageNo", //json中代表当前页码的数据
				total	: "totalPage",//json中代表页码总数的数据
				records	: "totalCount"//json中代表数据行总数的数据 
			},
			postData	: postData
		});
		this.setConfig(config);
		//表格
		jQuery(config.gridDom).jqGrid(config);
		//分页工具条
		if(config.pageDom && config.isPage){//分页工具条上的按钮控制
			jQuery(config.gridDom).jqGrid('navGrid', config.pageDom, {
				edit: false,
				add: false,
				del: false,
				search: false,
				refresh: true
			});
		}
		return this.getGrid();
	},
	/**
	 * 重新加载表格数据
	 * @param {} page		要刷新的页码，可为空，也可省略（无值时刷新到第一页）
	 */
	reload: function(page){
		var config = this.getConfig();
		if(!page){//刷新当前页
			$(config.gridDom).trigger("reloadGrid");
		}else{//刷新到指定页码
			$(config.gridDom).setGridParam({page : page || config.page || 1}).trigger("reloadGrid");
		}
	},
	/**
	 * 根据查询条件重新加载表格
	 * @param {} params		查询条件
	 */
	search: function(params){
		var config = this.getConfig();
		params = params || {};
		$(config.gridDom).setGridParam({page: 1, postData: params}).trigger("reloadGrid");
	},
	/**
	 * 获取表格选中行的属性值
	 * @param {} columnName		属性名称，为空时默认是oid
	 * @return {} 数组
	 */
	getColumn: function(columnName){
		var config = this.getConfig();
		var rowids = $(config.gridDom).jqGrid('getGridParam','selarrrow');
		var values = new Array();
		if(rowids.length > 0){
			columnName = columnName || 'oid';
			for(var i=0; i<rowids.length;i++){
				var rowData = $(config.gridDom).jqGrid('getRowData', rowids[i]);
				values.push(rowData[columnName])
			}
		}
		return values.join(',');
	},
	/**
	 * 获取表格中选中的行，单选
	 * @return {}
	 */
	getSelected: function(){
		var config = this.getConfig();
		var rowid = $(config.gridDom).jqGrid('getGridParam','selrow');
		if(rowid){
			return $(config.gridDom).jqGrid('getRowData', rowid);
		}
		return null;
	},
	/**
	 * 获取表格中所有选中的行，多选
	 * @return {}
	 */
	getSelectRows: function(){
		var config = this.getConfig();
		var rowids = $(config.gridDom).jqGrid('getGridParam','selarrrow');
		var values = new Array();
		if(rowids.length > 0){
			for(var i=0; i<rowids.length;i++){
				var rowData = $(config.gridDom).jqGrid('getRowData', rowids[i]);
				values.push(rowData)
			}
		}
		return values;
	},
	/**
	 * 获取当前页码
	 */
	getPage : function(){
		var config = this.getConfig();
		return $(config.gridDom).jqGrid('getGridParam','page');
	},
	/**
	 * 获取表格每页显示的记录数
	 * private
	 */
	getRowNum: function(config){
		var rowNum = 20;
		if(config.rowNum)rowNum = config.rowNum;
		else if(typeof(Config) != 'undefined' && Config.LIMIT > 0)rowNum = Config.LIMIT;
		return rowNum;
	},
	/**
	 * 设置初始化参数
	 * private
	 */
	setConfig: function(config){
		this.config = config;	
	},
	/**
	 * 获取初始化参数
	 */
	getConfig: function(){
		return this.config;
	},
	/**
	 * 返回初始化后的表格对象
	 */
	getGrid: function(){
		var config = this.getConfig();
		return $(config.gridDom);
	}
}

App.Grid = {
	_defConfig : {
		url 	: '',
		dataType: 'json',//从服务器端返回的数据类型
		gridDom	: '#grid-table',
		pageDom	: '#grid-pager',
		isPage	: true,
		multiselect	: true,
		rownumbers	: false,
		autowidth	: true,
		viewrecords : true,//是否在浏览导航栏显示记录总数
		pagerpos	: 'center',//指定分页栏的位置
		altRows		: true,//设置表格 zebra-striped 值
//		toppager	: true,//是否显示表格顶部分页工具条
	 	multiboxonly: false//只有当multiselect=true起作用，当multiboxonly为ture时只有选择checkbox才会起作用
//		direction	: "rtl",//表格中文字的显示方向，从左向右（ltr）或者从右向左（rtr
	},
	/**
	 * 初始化表格，返回表格的初始化参数
	 * @param {} config
	 * @return {}	config
	 */
	init : function(config){
		$.jgrid.defaults.styleUI = 'Bootstrap';
		config = $.extend({}, this._defConfig, config || {});//不能破坏已有的对象_defConfig
		if(config.gridDom.indexOf('#') == -1)config.gridDom = '#' + config.gridDom;
		if(config.pageDom.indexOf('#') == -1)config.pageDom = '#' + config.pageDom;
		var postData = $.extend(config.postData||{}, {
			isPage	: config.isPage
		});
		var loadCompleteFN = config.loadComplete;
		config = $.extend(config, {
			height		: config.height || 'auto',
			datatype	: config.dataType || 'json',
			mtype		: config.mtype || 'post',//ajax提交方式。POST或者GET，默认GET
			pager 		: config.isPage ? config.pageDom : '',//分页div
			rowNum		: config.isPage ? this.getRowNum(config) : 0,//每页显示记录数
			rowList		: [10,20,30,50,100,200],//用于改变显示行数的下拉列表框的元素数组。
		
			loadComplete: function(result) {
				if($.isFunction(loadCompleteFN)){
					loadCompleteFN(result);
				}
			},
			prmNames : {
				page		: "pageNo",    // 表示请求页码的参数名称  
			    rows		: "limit",    // 表示请求行数的参数名称  
			    sort		: "sort", 	// 表示用于排序的列名的参数名称  
			    order		: "dir", 	// 表示采用的排序方式的参数名称
			    totalrows	: "totalCount" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的
			},
			jsonReader : { 
				root	: "result",// json中代表实际模型数据的入口
				page	: "pageNo", //json中代表当前页码的数据
				total	: "totalPage",//json中代表页码总数的数据
				records	: "totalCount"//json中代表数据行总数的数据 
			},
			postData	: postData
		});
		this.setConfig(config);
		//表格
		jQuery(config.gridDom).jqGrid(config);
		//分页工具条
		if(config.pageDom && config.isPage){//分页工具条上的按钮控制
			jQuery(config.gridDom).jqGrid('navGrid', config.pageDom, {
				edit: false,
				add: false,
				del: false,
				search: false,
				refresh: true
			});
		}
		return this;
	},
	/**
	 * 重新加载表格数据
	 * @param {} page		要刷新的页码，可为空，也可省略（无值时刷新到第一页）
	 */
	reload: function(page){
		var config = this.getConfig();
		if(!page){//刷新当前页
			$(config.gridDom).trigger("reloadGrid");
		}else{//刷新到指定页码
			$(config.gridDom).setGridParam({page : page || config.page || 1}).trigger("reloadGrid");
		}
	},
	/**
	 * 根据查询条件重新加载表格
	 * @param {} params		查询条件
	 */
	search: function(params){
		var config = this.getConfig();
		params = params || {};
		$(config.gridDom).setGridParam({page: 1, postData: params}).trigger("reloadGrid");
	},
	/**
	 * 获取表格选中行的属性值
	 * @param {} column		属性名称，为空时默认是oid
	 * @return {} 数组
	 */
	getColumn: function(column){
		var config = this.getConfig();
		var rowids = $(config.gridDom).jqGrid('getGridParam','selarrrow');
		var values = new Array();
		if(rowids.length > 0){
			column = column || 'oid';
			for(var i=0; i<rowids.length;i++){
				var rowData = $(config.gridDom).jqGrid('getRowData', rowids[i]);
				values.push(rowData[column])
			}
		}
		return values.join(',');
	},
	/**
	 * 获取表格中选中的行，单选
	 * @return {}
	 */
	getSelected: function(){
		var config = this.getConfig();
		var rowid = $(config.gridDom).jqGrid('getGridParam','selrow');
		if(rowid){
			return $(config.gridDom).jqGrid('getRowData', rowid);
		}
		return null;
	},
	/**
	 * 获取表格中所有选中的行，多选
	 * @return {}
	 */
	getSelectRows: function(){
		var config = this.getConfig();
		var rowids = $(config.gridDom).jqGrid('getGridParam','selarrrow');
		var values = new Array();
		if(rowids.length > 0){
			for(var i=0; i<rowids.length;i++){
				var rowData = $(config.gridDom).jqGrid('getRowData', rowids[i]);
				values.push(rowData)
			}
		}
		return values;
	},
	/**
	 * 获取当前页码
	 */
	getPage : function(){
		var config = this.getConfig();
		return $(config.gridDom).jqGrid('getGridParam','page');
	},
	/**
	 * 获取表格每页显示的记录数
	 * private
	 */
	getRowNum: function(config){
		var rowNum = 20;
		if(config.rowNum)rowNum = config.rowNum;
		else if(typeof(Config) != 'undefined' && Config.LIMIT > 0)rowNum = Config.LIMIT;
		return rowNum;
	},
	/**
	 * 设置初始化参数
	 * private
	 */
	setConfig: function(config){
		this.config = config;	
	},
	/**
	 * 获取初始化参数
	 */
	getConfig: function(){
		return this.config;
	},
	/**
	 * 返回初始化后的表格对象
	 */
	getGrid: function(){
		var config = this.getConfig();
		return $(config.gridDom);
	}
};