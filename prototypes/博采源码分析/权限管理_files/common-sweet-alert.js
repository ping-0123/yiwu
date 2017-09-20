    if (App == null) {
    	App = {};
    }
    App.sweet = {
    	_defConfig : {
    		title : '',// 标题内容
    		text : '', // 消息内容
    		type : "warning", // 提示分类
    		showCancelButton : false, // 是否显示取消按钮
    		confirmButtonColor : "#DD6B55", // 提示按钮颜色
    		confirmButtonText : "确认", // 提示按钮内容
    		closeOnConfirm : false
    	// true不显示操作完成alert
    	},
    	// 未选中删除提示
    	alert : function(config) {
    		config = $.extend({}, this._defConfig, config || {});// 不能破坏已有的对象_defConfig
    		// 提示框
    		swal({
    			title : config.title,// 标题内容
    			text : config.text, // 消息内容
    			type : config.type, // 提示分类
    			showCancelButton : config.showCancelButton, // 是否显示取消安丘
    			confirmButtonColor : config.confirmButtonColor, // 提示按钮颜色
    			confirmButtonText : config.confirmButtonText, // 提示按钮内容
    			closeOnConfirm : config.closeOnConfirm
    		})
    	},
    //确认提示
    	confirmDel : function(title, text, yesFn) {
    		var config = $.extend({}, this._defConfig, config || {});
    		config.title = title;
    		config.text = text;
    		swal({
    			title : config.title,// 标题内容
    			text : config.text, // 消息内容
    			type : config.type, // 提示分类
    			showCancelButton : true, // 是否显示取消安丘
    			confirmButtonColor : config.confirmButtonColor, // 提示按钮颜色
    			confirmButtonText : config.confirmButtonText, // 提示按钮内容
    			closeOnConfirm : config.closeOnConfirm
    		}, function() {
    			if ($.isFunction(yesFn)) {
    				yesFn();
    			}
    		})
    	},
    	confirmSave : function(title,text,yesFn){
    		var config = $.extend({}, this._defConfig, config || {});
    		config.title = title;
    		config.text = text;
    		swal({
    			title : config.title,// 标题内容
    			text : config.text, // 消息内容
    			type : "success", // 提示分类
    			showCancelButton : false, // 是否显示取消安丘
    			confirmButtonColor : "#AEDEF4", // 提示按钮颜色
    			confirmButtonText : config.confirmButtonText, // 提示按钮内容
    			closeOnConfirm : false
    		}, function() {
    			if ($.isFunction(yesFn)) {
    				yesFn();
    			}
    		})
    	}
    }