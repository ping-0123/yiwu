if (App == null) {
    App = {};
}

/**
 * 消息对话框定义，需要layer-2.1支持
 * 用法：
 * 		MsgBox.msg(msg);
 * 		MsgBox.msgHide(msg);//Hide表示会自动关闭，默认3秒后关闭
 * 		……
 * 		MsgBox.Dialog(config);//通过config配置参数弹出提示框，支持callback回调函数
 * 
 * 更多用法请参考layer组件
 * 
 * @author tongpuxin
 */
App.MsgBox = {
	_defConfig: {
	    title		: '消息',//标题内容
		content		: '', 	//消息内容
	    width		: 260, 	//内容宽度
		lock		: true, //开启锁屏
		opacity		: 0.4, //锁屏遮罩透明度
		lockiframe	: false,
		autoclose	: false//自动关闭
	},
	/**
	 * 消息内容常量定义
	 */
	Constant : {
		MSG_TIME 	: 3000,
		Succ 		: '操作成功！',
		Error		: '操作失败！',
		Save_Succ 	: '保存成功！',
		Save_Error 	: '保存失败！',
		Del_Succ 	: '删除成功！',
		Del_Error 	: '删除失败！',
		Edit_Succ 	: '修改成功！',
		Edit_Error 	: '修改失败！'
	},
	/**
	 * 普通对话框
	 * 支持callback回调函数
	 */
	Dialog : function (config){
		config = $.extend({}, this._defConfig, config || {});//不能破坏已有的对象_defConfig
		var cb = config.callback;
		if($.isFunction(cb)){
			config.yes = function(index, layero){//该回调携带两个参数，分别为当前层索引、当前层DOM对象
				layer.close(index);//如果设定了yes回调，需进行手工关闭
				cb();
			}
		}
		var title = config.title, i = config.time;
		var index = layer.open(config);
    	if(config.autoclose==true || i > 0){
    		i = i || this.Constant.MSG_TIME, m = i/1000;
			var fn = function () {
				if(m <= 1){
					clearInterval(timer);
				}
	            layer.title(title +"：（将在 "+ m + ' 秒后关闭）', index)
	            m --;
	        };
	        timer = setInterval(fn, 1000);
	        fn();
    	}
    	return index;
	},
	/**
	 * 消息提示框
	 * @param msg  提示内容
	 */
	msg : function(msg) {
		layer.alert(msg);
	},
	/**
	 * 消息提示框,自动消失
	 * @param msg  提示内容
	 */
	msgHide : function(msg) {
		layer.alert(msg, {time: this.Constant.MSG_TIME});
	},
	/**
	 * 警告提示框
	 * @param msg  提示内容
	 */
	warn : function(msg){
		layer.alert(msg, {icon: 0});
	},
	/**
	 * 警告提示框,自动消失
	 * @param msg  提示内容
	 */
	warnHide : function(msg){
		layer.alert(msg, {icon: 0, time: this.Constant.MSG_TIME});
	},
	/**
	 * 修改操作时没有选中要修改的信息 可以使用此提示框
	 */
	warnUpdate : function(){
		var msg = '请选择要修改的记录';
		layer.alert(msg, {icon: 0});
	},
	/**
	 * 删除操作时没有选中要删除的信息 可以使用此提示框
	 */
	warnDelete : function(){
		var msg = '请选择要删除的记录';
		layer.alert(msg, {icon: 0});
	},
	/**
	 * 操作成功后的消息提示
	 * @param msg  提示内容
	 */
	succ : function(msg){
		layer.alert(msg, {icon: 1});
	},
	/**
	 * 操作成功后的消息提示,自动消失
	 * @param msg  提示内容
	 */
	succHide : function(msg){
		layer.alert(msg, {icon: 1, time: this.Constant.MSG_TIME});
	},
	/**
	 * 操作失败后的消息提示
	 * @param msg  提示内容
	 */
	error : function(msg){
		layer.alert(msg, {icon: 2});
	},
	/**
	 * 操作失败后的消息提示,自动消失
	 * @param msg  提示内容
	 */
	errorHide : function(msg){
		layer.alert(msg, {icon: 2, time: this.Constant.MSG_TIME});
	},
	/**
	 * 确认对话框
	 * @param msg  提示内容
	 * @param yesFn  确定按钮回调函数
	 * @param noFn   取消按钮回调函数
	 */
	confirm : function(msg, yesFn, noFn){
		layer.confirm(msg, {icon: 3, title: '提示'}, function(index){
		  if($.isFunction(yesFn)){yesFn();}
		  layer.close(index);
		}, function(index){
			if($.isFunction(noFn)){noFn();}
		  	layer.close(index);
		});
	},
	/**
	 * 确认删除的对话框
	 * @param yesFn  确定按钮回调函数
	 * @param noFn   取消按钮回调函数
	 */
	confirmDelete : function(yesFn, noFn) {
		this.confirm('确定要删除选择的记录', yesFn, noFn);
	},
	/**
	 * 带有回调函数的消息提示框
	 * @param msg  消息内容
	 * @param callBack 回调函数
	 */
	alert : function(msg, callBack){
		layer.alert(msg, function(index){
  			if($.isFunction(callBack)){callBack();}
			layer.close(index);
		});
	},
	/**
	 * 输入框
	 * @param msg  消息内容
	 * @param callBack 回调函数
	 * @param value 默认值
	 */
	prompt : function(msg, callBack, value){
		layer.prompt({
  			formType: 0,
		  	value: value,
		  	title: msg
		}, function(value, index, elem){
  			if($.isFunction(callBack)){callBack(value);}; //得到value
  			layer.close(index);
		});
	},
	/**
	 * 多行输入框
	 * @param msg  消息内容
	 * @param callBack 回调函数
	 * @param value 默认值
	 */
	promptMulti : function(msg, callBack, value){
		layer.prompt({
  			formType: 2,
		  	title: msg,
		  	value: value,//初始时的值，默认空字符
		  	maxlength: 4000//可输入文本的最大长度，默认500
		}, function(value, index, elem){
  			if($.isFunction(callBack)){callBack(value);}; //得到value
  			layer.close(index);
		});
	}
};
MsgBox = App.MsgBox;

/**
 * 弹出窗口定义，需要layer-2.1支持
 * 用法：
 * 		App.Window.open({
 *			url		: ,//url地址
 *			params	: {test1: 'tt1', test2: 'tt2'},//参数，可选
 *			width	: ,//窗口宽，可选
 *			height	: ,//窗口高，可选
 *			fullScreen: ,//是否全屏，可选
 *			callback: function(){}//回调函数，可选
 *		});
 * 其他常用可选参数：
 * 		title	: 窗口标题
 * 		width	: 窗口高度
 * 		height	: 窗口宽度
 * 
 * 更多用法请参考layer-2.1组件
 * 
 * @author tongpuxin
 */
App.Window = {
	_defConfig : {
		 skin: 'layui-layer-molv',
		title 	: '窗口',
		fix		: false, //不固定
		maxmin	: true,
		shadeClose	: 0.4,
		width	: 800, 
		height	: 580,
		through : true//是否iframe穿透
	},
	/**
	 * 打开窗口
	 * @param {} config	窗口配置
	 */
	open : function(config){
		config = $.extend({}, this._defConfig, config);//不能破坏已有的对象_defConfig
//		var cb = config.callback;
//		if($.isFunction(cb)){
//			config.end = function(index, layero){//该回调携带两个参数，分别为当前层索引、当前层DOM对象
//				cb();
//			}
//		}
		config = $.extend(config, {
			type	: 2,
			area	: [config.width+'px', config.height +'px'],
			title	: config.title,
			content	: config.url
		});
		if(config.through==true && window.parent){
			layer = parent.layer;
		}
		if(config.fullScreen == true){//全屏窗口
			config = $.extend(config, {
				maxmin	: false,//不显示最大小化按钮
				move	: false,//禁止拖拽
				area	: ['99%', '98%']
			});
		}
		var index = layer.open(config);
		if(!layer.WindowCallback){
			layer.WindowCallback = new HashMap();
		}
		layer.WindowCallback.put(index, config.callback);
		return index;
    },
    /**
     * 关闭窗口
     * @param {} index
     */
	close : function(index){
		if(layer.WindowCallback){
    		var cb = layer.WindowCallback.get(index);
    		if($.isFunction(cb)){
    			cb();
    			layer.WindowCallback.remove(index);
    		}
    	}
		layer.close(index);
    },
    /**
     * 在Frame中关闭窗口
     * @param {} windowName
     */
    shut : function(windowName){
    	var index = parent.layer.getFrameIndex(windowName);
    	
    	if(parent.layer.WindowCallback){
    		var cb = parent.layer.WindowCallback.get(index);
    		if($.isFunction(cb)){
    			cb();
    			parent.layer.WindowCallback.remove(index);
    		}
    	}
    	
		parent.layer.close(index);
    }
};