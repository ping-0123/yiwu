var App = {};

/**
 * ajax请求定义
 * 用法：
 *    常规请求：
 * 		App.Ajax.request({
 *			url 	: ,//请求url，必须
 *			params 	: {},//请求参数
 *			success	: function(result){},//请求成功的回调函数,可选
 *			error	: function(result){}//请求失败的回调函数,可选
 *		});
 *	  跨域请求：
 *		App.Ajax.jsonp({
 *			url 	: //请求url，必须
 *		});
 * 其他常用可选参数：
 *		dataType	: 从服务器端返回的数据类型，默认json
 *		type		: ajax提交方式。POST或者GET，默认POST
 *		isAutoTip	: 是否自动提示请求结果，默认true
 *		async		: 默认设置为true，即所有请求均为异步请求。如需发送同步请求，将此项设置为false。注意，同步请求将锁住浏览器，用户其他操作必须等待请求完成才可以执行。
 * 
 * 更多用法请参考jquery的ajax请求
 * 
 * @author tongpuxin
 */
App.Ajax = {
	_defConfig : {
		url 		: '',
		dataType	: "json",
		async 		: true,//默认设置为true，所有请求均为异步请求
		isAutoTip	: true
	},
	/**
	 * 常规请求
	 * @param {} config	请求参数
	 */
	request: function(config){
		config = $.extend({}, this._defConfig, config || {});//不能破坏已有的对象_defConfig
		var succFn 	  = config.success;
		var failFn 	  = config.failure || config.error;
		var isAutoTip = config.isAutoTip;
		$.ajax({
			url  	: config.url,
			type 	: config.type || config.method || 'post',
			data 	: config.params || config.data || {},
			dataType: config.dataType,
			async 	: config.async,
			success : function(resp) {
				if(resp.success == null)resp.success = true;
				App.Ajax.succOper(resp, succFn, failFn, isAutoTip);
			},
			error 	: function(resp, textStatus, errorThrown) {
				if(resp.status == 200){
					App.Ajax.succOper(resp, succFn, failFn, isAutoTip);
				}else{
					App.Ajax.errorHandler(resp, this.url);
				}
			}
		});	
	},
	/**
	 * private
	 * 处理请求结果
	 */
	succOper : function(resp, succFn, failFn, isAutoTip) {
		if(resp){//有响应
			if(resp.success){//请求成功
				if($.isFunction(succFn)){
					succFn(resp);
				}
				App.Ajax.showTip(resp.msg, isAutoTip, true);
			}else{
				if ($.isFunction(failFn)) {
					failFn(resp);
				} else {
					isAutoTip = true;//无回调函数时提示操作失败
				}
				App.Ajax.showTip(resp.error || resp.msg, isAutoTip, false);
			}
		}else{
			App.Ajax.showTip('响应数据为空!!', true, false);
		}
	},
	/**
	 * private
	 * 提示请求结果
	 */
	showTip	: function (msg, isAutoTip, success) {
		if(isAutoTip == true) {
			if(typeof(MsgBox) != 'undefined'){
				if(success){
					MsgBox.msgHide(msg || MsgBox.Constant.Succ);
				}else{
					MsgBox.error(msg || MsgBox.Constant.Error);
				}
			}else{
				alert(msg || (success ? '操作成功!' : '操作失败!'));
			}
		}
	},
	/**
	 * private
	 * 错误处理
	 * @param {} resp
	 */
	errorHandler : function(resp, url){
		var status = resp.status;
		if(status == '0') {
			App.Ajax.showTip('无法与服务器建立连接', true, false);
		} else if(status == '1') {
			App.Ajax.showTip('向服务端发生请求失败', true, false);
		} else if(status == '404') {
			App.Ajax.showTip('url "'+url+'" 不存在', true, false);
		} else if(status == '408') {
			App.Ajax.showTip('服务器等候请求时发生超时', true, false);
		} else if(status == '405') {
			App.Ajax.showTip('请求不被允许', true, false);
		} else if(status == '500') {
			App.Ajax.showTip('服务器内部错误', true, false);
		} else if(status == '503') {//  服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态
			App.Ajax.showTip('服务不可用', true, false);
		} else {
			App.Ajax.showTip('请求失败, 状态代码 : ' + status, true, false);
		}
	},
	/**
	 * private
	 * 将data转为json格式
	 * @param {} data
	 * @return {}
	 */
	parseData : function(data){
		if(typeof data == 'undefined'){
			data = {};
		}else if(typeof data == 'string'){
			data = JSON.parse(data);
		}
		return data;
	},
	/**
	 * private
	 * 执行回调函数
	 * @param resp
	 * @param succFn
	 * @param failFn
	 */
	callback : function(resp, succFn, failFn){
		if(resp.success){
			if($.isFunction(succFn)){
				succFn(resp);
			}
		}else{
			if($.isFunction(failFn)){
				failFn(resp);
			}
		}
	},
	/**
	 * 跨域请求
	 * @param {} config 请求参数
	 */
	jsonp : function(config){
		var url = config.url || "";
		if(url.indexOf('callback') == -1){//url没有callback参数
			if(url.indexOf('?') == -1){
				url += '?callback=?';
			}else{
				url += '&callback=?';
			}
		}
		$.getJSON(url, config.params || {}, function(data){
			if($.isFunction(config.callback)){
				config.callback(data);
			}
		});
	},
	/**
	 * 上传文件
	 * @param {} config
	 */
	upload : function(config){
		if(!config.url && !config.contextPath){
			return alert('请传入url参数或contextPath参数');
		}
		var form = config.form;
		if(typeof form == 'undefined'){
			return alert('请传入form参数');
		}else if(typeof form == 'string'){
			if(form.indexOf('#') == -1)form = '#' + form;
			form = $(form)[0];
		}else if(form.selector){
			form = form[0];
		}
		var formData = new FormData(form);//用form表单直接构造formData对象;就不需要用append方法来为表单进行赋值
		var url = config.url || config.contextPath + '/files/upload.do';
		var succFn 	  = config.success;
		var failFn 	  = config.failure || config.error;
		var isAutoTip = config.isAutoTip == null ? false : config.isAutoTip;
		$.ajax({
	        url			: url,
	        type 		: 'POST',
	        data 		: formData,
	        /**
	         * 必须false才会避开jQuery对 formdata 的默认处理
	         * XMLHttpRequest会对 formdata 进行正确的处理
	         */
	        processData : false,
	        /**
	         *必须false才会自动加上正确的Content-Type
	         */
	        contentType : false,
	        success : function(resp) {
				if(resp.success == null)resp.success = true;
				resp.data = App.Ajax.parseData(resp.data);
				App.Ajax.succOper(resp, succFn, failFn, isAutoTip);
			},
			error 	: function(resp, textStatus, errorThrown) {
				resp.data = App.Ajax.parseData(resp.data);
				if(resp.status == 200){
					App.Ajax.succOper(resp, succFn, failFn, isAutoTip);
				}else{
					App.Ajax.errorHandler(resp, url);
				}
			}
	    });
	},
	/**
	 * 提交form表单(需要jquery.form插件)
	 * @param options 同jquery.form的ajaxSubmit方法的options参数
	 * @returns
	 */
	submit: function(options){
		var form = options.form;
		if(typeof form == 'undefined'){
			return alert('请传入form参数');
		}else if(typeof form == 'string'){
			if(form.indexOf('#') == -1)form = '#' + form;
			form = $(form);
		}else if(form.selector){
		}else{
			form = $(form);
		}
		var succFn 	  = options.success || options.callback;
		var failFn 	  = options.failure || options.error;
		var isAutoTip = options.isAutoTip == null ? true : options.isAutoTip;
		options = $.extend(options, {
			success	: function(resp, statusText){
				if(resp.success == null && resp.returnCode == 200)resp.success = true;
				if(isAutoTip == true) {//提示并回调
					if(typeof(MsgBox) != 'undefined'){
						MsgBox.alert(MsgBox.Constant.Succ, function(){
							App.Ajax.callback(resp, succFn, failFn);
						});
					}else{
						alert('操作成功!');
						App.Ajax.callback(resp, succFn, failFn);
					}
				}else{//回调
					App.Ajax.callback(resp, succFn, failFn);
				}
			}
		});
		form.ajaxSubmit(options);
	}
};

/**
 * 日期格式化
 * 
 * @author tongpuxin
 */
App.Date = {
	/**
	 * 将指定参数date的值转为标准Date类型
	 * @param {Object} date	待转换的值
	 * @return {Date} 返回Date型日期值
	 */
	parse: function(date){
		if(!date)return null;
		if(typeof(date) == "string"){
			var dates = date.split(' ');
			if(dates[0].length > 8){
				date = date.replace('-', '/').replace('-', '/');
				date = date.replace('年', '/').replace('月', '/').replace('日', '');
				date = date.replace('时', ':').replace('分', ':').replace('秒', '');
			}else{//处理yyyyMMdd
				var year  = dates[0].substring(0, 4);
				var month = dates[0].substring(4, 6);
				var day   = dates[0].substring(6, 8);
				date = year + '/' + month + '/' + day + ' ';
				if(dates.length > 1){
					date = date + ' ' + dates[1];
				}
			}
			return new Date(date);
		}else if(typeof(date) == "object"){
			return new Date(date);
		}else{
			alert('日期格式不正确，无法转换！');
		}
	},
	/**
	 * 将指定参数date的值转为fmt类型的字符串日期
	 * @param {Date} date	待转换的日期
	 * @param {String} fmt	日期格式,eg: yyyy-MM-dd
	 * @return {String} 返回String型日期值
	 */
	format : function(date, fmt){
		var d = this.parse(date);
		var o = {
			"M+" : d.getMonth() + 1, // 月份	
			"d+" : d.getDate(), // 日	
			"h+" : d.getHours() % 12 == 0 ? 12 : d.getHours() % 12, // 小时	
			"H+" : d.getHours(), // 小时	
			"m+" : d.getMinutes(), // 分	
			"s+" : d.getSeconds(), // 秒	
			"q+" : Math.floor((d.getMonth() + 3) / 3), // 季度	
			"S" : d.getMilliseconds()// 毫秒	
		};	
		var week = {	
			"0" : "\u65e5",	
			"1" : "\u4e00",	
			"2" : "\u4e8c",	
			"3" : "\u4e09",	
			"4" : "\u56db",	
			"5" : "\u4e94",	
			"6" : "\u516d"	
		};	
		if (/(y+)/.test(fmt)) {	
			fmt = fmt.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));	
		}	
		if (/(E+)/.test(fmt)) {	
			fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "")
							+ week[d.getDay() + ""]);
		}	
		for (var k in o) {	
			if (new RegExp("(" + k + ")").test(fmt)) {	
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			}	
		}
		return fmt;
	},
	/**
	 * 比较date1是否大于date2
	 * @param {Object} date1
	 * @param {Object} date2
	 * @return {TypeName} 返回Boolean值
	 */
	compare: function (date1, date2){
		var d1 = this.parse(date1);
		var d2 = this.parse(date2);
		if(d1 && d2){
			return d1 > d2;
		}
		return null
	},
	/**
	 * 将一个日期增加n天
	 * @param date
	 * @param n	增加的天数
	 */
	addDay: function(date, n){
		var d = this.parse(date);
		d = d.valueOf();
		d = d + n * 24 * 60 * 60 * 1000;
		return new Date(d);
	}
};

/**
 * Cookie操作
 * 
 * @author tongpuxin
 */
App.Cookie = {
	/**
	 * 写入
	 */
	create: function(name, value, days) {
	    if (days) {
	        var date = new Date();
	        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
	        var expires = "; expires=" + date.toGMTString();
	    } else {
	        expires = "";
	    }
	    value = encodeURI(encodeURI(value));
	    document.cookie = name + "=" + value + expires + "; path=/";
	},
	/**
	 * 读取
	 */
	read: function(name) {
	    var nameEQ = name + "=",
	        ca = document.cookie.split(';'),
	        i,
	        c,
	        len = ca.length;
	    for ( i = 0; i < len; i++) {
	        c = ca[i];
	        while (c.charAt(0) == ' ') {
	            c = c.substring(1, c.length);
	        }
	        if (c.indexOf(nameEQ) == 0) {
	        	var value = c.substring(nameEQ.length, c.length);
	        	value = decodeURI(decodeURI(value));
	            return value;
	        }
	    }
	    return null;
	},
	/**
	 * 清除
	 */
	clear:function(name) {
		document.cookie = name + "=" + "; path=/";
	}
};