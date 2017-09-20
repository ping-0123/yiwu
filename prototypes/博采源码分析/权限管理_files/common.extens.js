/**
 * 对字符串对象的扩展
 * @class String
 */
$.extend(String, {

    /**
     * 对字符串中的'和\做编码处理
     * @static
     * @param {String} string 要做编码处理的字符串
     * @return {String} 编码后的字符串
     */
    escape: function (string) {
        return string.replace(/('|\\)/g, "\\$1");
    },

    /**
     * 让字符串通过指定字符做补齐的函数
     *
     *      var s = String.leftPad('123', 5, '0');//s的值为：'00123'
     *
     * @static
     * @param {String} val 原始值
     * @param {Number} size 总共需要的位数
     * @param {String} ch 用于补齐的字符
     * @return {String}  补齐后的字符串
     */
    leftPad: function (val, size, ch) {
        var result = String(val);
        if (!ch) {
            ch = " ";
        }
        while (result.length < size) {
            result = ch + result;
        }
        return result.toString();
    },

    /**
     * 对字符串做替换的函数
     *
     *      var cls = 'my-class', text = 'Some text';
     *      var res = String.format('<div class="{0}>{1}</div>"', cls, text);
     *      //res的值为：'<div class="my-class">Some text</div>';
     *
     * @static
     * @param {String} format 要做替换的字符串，替换字符串1，替换字符串2...
     * @return {String} 做了替换后的字符串
     */
    format: function (format) {
        var args = Array.prototype.slice.call(arguments, 1);
        return format.replace(/\{(\d+)\}/g, function (m, i) {
            return args[i];
        });
    },
    /**
     * 判断字符是否为空
     *
     *      var str = ""
     *      var res = String.isEmpty(str);
     *      //res的值为：true;
     *
     * @static
     * @param {String} str 要判断为空的字符串
     * @return {boolean} 是否为空
     */
    isEmpty : function (str){
		if(typeof(str) ==  "undefined" || str == null ||str == "null" ||str.toString().trim()==""){     
			return true;
	   	}
	    return false;     
	},
	isNotEmpty : function (str){
		if(typeof(str) ==  "undefined" || str == null ||str == "null" ||str.toString().trim()==""){     
			return false;
	   	}
	    return true;     
	},
	  /**
     * 截取字符串
     * @param {int} num 需要截取的位数
     * @param {String} str 要判断为空的字符串
     * @return {string} 截取完毕的字符串
     */
	substring : function (num,str){
		if(String.isNotEmpty(num) && !isNaN( num ) ){
			return str.substring(0,num);
		}
		return str;
	}
});

/**
 * 对数组对象的扩展
 * @class Array
 */
$.extend(Array.prototype, {
    /**
     * 检查指定的值是否在数组中
     * @param {Object} o 要检查的值
     * @return {Number}  o在数组中的索引（如果不在数组中则返回-1）
     */
/*    indexOf: function (o) {
        for (var i = 0, len = this.length; i < len; i++) {
            if (FR.equals(o, this[i])) {
                return i;
            }
        }
        return -1;
    },*/

    /**
     * 检查指定的值是否在数组中
     * ie67不支持数组的这个方法
     * @param {Object} o 要检查的值
     * @return {Number}  o在数组中的索引（如果不在数组中则返回-1）
     */
/*    lastIndexOf: function (o) {
        for (var len = this.length, i = len - 1; i >= 0; i--) {
            if (FR.equals(o, this[i])) {
                return i;
            }
        }
        return -1;
    },*/

    /**
     * 从数组中移除指定的值，如果值不在数组中，则不产生任何效果
     * @param {Object} o 要移除的值
     * @return {Array} 移除制定值后的数组
     */
    remove: function (o) {
        var index = this.indexOf(o);
        if (index != -1) {
            this.splice(index, 1);
        }
        return this;
    },
    /**
     * 移除数组中的所有元素
     */
    clear: function () {
        while (this.length > 0) {
            this.pop();
        }
    },
    contains : function(e){  
		for(i=0;i<this.length;i++){  
			if(this[i] == e)  
				return true;  
		}
		return false;  
	}
});

/**
 * 对字符串对象的扩展
 * @class String
 */
$.extend(String.prototype, {

    /**
     * 判断字符串是否已指定的字符串开始
     * @param {String} startTag   指定的开始字符串
     * @return {Boolean}  如果字符串以指定字符串开始则返回true，否则返回false
     */
    startWith: function (startTag) {
        if (startTag == null || startTag == "" || this.length === 0 || startTag.length > this.length) {
            return false;
        }
        return this.substr(0, startTag.length) == startTag;
    },
    /**
     * 判断字符串是否以指定的字符串结束
     * @param {String} endTag 指定的字符串
     * @return {Boolean}  如果字符串以指定字符串结束则返回true，否则返回false
     */
    endWith: function (endTag) {
        if (endTag == null || endTag == "" || this.length === 0 || endTag.length > this.length) {
            return false;
        }
        return this.substring(this.length - endTag.length) == endTag;
    },

    /**
     * 获取url中指定名字的参数
     * @param {String} name 参数的名字
     * @return {String} 参数的值
     */
    getQuery: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = this.substr(this.indexOf("?") + 1).match(reg);
        if (r) {
            return unescape(r[2]);
        }
        return null;
    },

    /**
     * 给url加上给定的参数
     * @param {Object} paras 参数对象，是一个键值对对象
     * @return {String} 添加了给定参数的url
     */
    appendQuery: function (paras) {
        if (!paras) {
            return this;
        }
        var src = this;
        // 没有问号说明还没有参数
        if (src.indexOf("?") === -1) {
            src += "?";
        }
        // 如果以问号结尾，说明没有其他参数
        if (src.endWith("?") !== false) {
        } else {
            src += "&";
        }
        $.each(paras, function (name, value) {
            if (typeof(name) === 'string') {
                src += name + "=" + value + "&";
            }
        });
        src = src.substr(0, src.length - 1);
        return src;
    },
    /**
     * 将所有符合第一个字符串所表示的字符串替换成为第二个字符串
     * @param {String} s1 要替换的字符串的正则表达式
     * @param {String} s2 替换的结果字符串
     * @returns {String} 替换后的字符串
     */
    replaceAll: function (s1, s2) {
        return this.replace(new RegExp(s1, "gm"), s2);
    },
    /**
     * 总是让字符串以指定的字符开头
     * @param {String} start 指定的字符
     * @returns {String} 以指定字符开头的字符串
     */
    perfectStart: function (start) {
        if (this.startWith(start)) {
            return this;
        } else {
            return start + this;
        }
    },

    /**
     * 获取字符串中某字符串的所有项位置数组
     * @param {String} sub 子字符串
     * @return {Number[]} 子字符串在父字符串中出现的所有位置组成的数组
     */
    allIndexOf: function (sub) {
        if (typeof sub != 'string') {
            return [];
        }
        var str = this;
        var location = [];
        var offset = 0;
        while (str.length > 0) {
            var loc = str.indexOf(sub);
            if (loc === -1) {
                break;
            }
            location.push(offset + loc);
            str = str.substring(loc + sub.length, str.length);
            offset += loc + sub.length;
        }
        return location;
    },
    
    /**
	 * js 简单字符串格式化
	 * @param {} args
	 * @return {}
	 * @example	var template="我是{0}，今年{1}了";
	 * 			var result=template.format("loogn",22);
	 */
    format : function(args) {
	    var result = this;
	    if (arguments.length > 0) {    
	        if (arguments.length == 1 && typeof (args) == "object") {
	            for (var key in args) {
	                if(args[key]!=undefined){
	                    var reg = new RegExp("({" + key + "})", "g");
	                    result = result.replace(reg, args[key]);
	                }
	            }
	        }
	        else {
	            for (var i = 0; i < arguments.length; i++) {
	                if (arguments[i] != undefined) {
	                    var reg = new RegExp("({[" + i + "]})", "g");
	                    result = result.replace(reg, arguments[i]);
	                }
	            }
	        }
	    }
	    return result;
	}
});


/**
 * 对函数对象的扩展
 * @class Function
 */
$.extend(Function.prototype, {

    /**
     * 将函数绑定到全局域上
     *
     *      @example
     *      function t(a) {
     *          alert(a);
     *      }
     *      var x = t.createCallback("abc");
     *      window.x();
     * @returns {Function}
     */
    createCallback: function (/*args...*/) {
        // make args available, in function below
        var args = arguments;
        var method = this;
        return function () {
            return method.apply(window, args);
        };
    },

    /**
     * 函数的代理，更改原函数的参数和this作用域
     *
     *      @example
     *      var $div = $("#test1");
     *      var $div2 = $("#test2")
     *      $div.bind("click", function(){
     *          alert($(this) == $div2);  // 这一句将会输出true
     *      }.createDelegate($div2));
     *
     * @param {Object} obj 函数内部this作用域的范围
     * @param {Array} args 参数数组
     * @param {Array} appendArgs appendArgs是"Boolean或Number",
     * 如果appendArgs是 Boolean型的且值为true,那么args参数将跟在调用代理方法时传入的参数后面组成数组一起传入当前方法,
     * 否则只传入args,如果 appendArgs为Number型,那么args将插入到appendArgs指定的位置。
     * @returns {Function}
     */
    createDelegate: function (obj) {
        var method = this;
        var args = arguments[1];
        var appendArgs = arguments[2];
        return function () {
            var callArgs = args || arguments;
            if (appendArgs === true) {
                callArgs = Array.prototype.slice.call(arguments, 0);
                callArgs = callArgs.concat(args);
            } else if (typeof appendArgs == "number") {
                callArgs = Array.prototype.slice.call(arguments, 0);
                // copy arguments first
                var applyArgs = [appendArgs, 0].concat(args);
                // create method call params
                Array.prototype.splice.apply(callArgs, applyArgs);
                // splice them in
            }
            return method.apply(obj || window, callArgs);
        };
    },

    /**
     * 创建阻断方法,如果fcn返回false,原方法将不会被执行
     * @param {Function} fcn 阻断函数
     * @param {Object} scope 作用域
     * @returns {*}
     */
    createInterceptor: function (fcn, scope) {
        if (typeof fcn != "function") {
            return this;
        }
        var method = this;
        return function () {
            fcn.target = this;
            fcn.method = method;
            if (fcn.apply(scope || this || window, arguments) === false) {
                return;
            }
            return method.apply(this || window, arguments);
        };
    },

    /**
     * 让函数延迟执行
     * @param {Number} millis 延迟的毫秒数
     * @param {Object} obj 函数的this作用域
     * @param {Array} args 参数
     * @param {Array} appendArgs 同createDelegate函数的最后一个参数说明
     * @returns {Number}
     */
    defer: function (millis, obj, args, appendArgs) {
        var fn = this.createDelegate(obj, args, appendArgs);
        if (millis || millis === 0) {
            return setTimeout(fn, millis);
        }
        fn();
        return 0;
    },

    /**
     * 创建组合函数，将执行原函数以及fcn函数
     * @param {Function} fcn  组合添加的fcn函数
     * @param {Object} scope 函数作用域
     * @returns {*}
     */
    createSequence: function (fcn, scope) {
        if (typeof fcn != "function") {
            return this;
        }
        var method = this;
        return function () {
            var retval = method.apply(this || window, arguments);
            fcn.apply(scope || this || window, arguments);
            return retval;
        };
    },

    /**
     * 通过函数名获取函数的参数列表
     * @returns {String[]} 函数的参数列表组成的数组
     */
    getNameArguments: function () {
        var s = this.toString();
        /*
         * alex:通过正则表达式取到参数列表
         * function(a, b) -> a, b
         * function fn(a, b, c) -> a, b, c
         */
        var match = /function[^\(]*\(([^\)]*)\)/.exec(s);
        var a = [];
        if (match != null) {
            a = match[1].split(",");
        }

        return $.map(a, function (item) {
            return $.trim(item)
        });
    },

    /**
     * @private
     */
    arguments2Json: function () {
        var args = arguments;
        var nameArgs = this.getNameArguments();
        var retJo = {};
        $.each(nameArgs, function (idx, item) {
            if (args.length > idx) {
                retJo[item] = args[idx];
            }
        });

        return retJo;
    }
});

/**
 * 对日期对象的扩展
 * @class Date
 */
$.extend(Date.prototype, {
	/**
	 * 将日期转为fmt类型的字符串日期
	 * @param {String} fmt	日期格式,eg: yyyy-MM-dd
	 * @return {String} 返回String型日期值
	 */
	format: function(fmt){
		var o = {
			"M+" : this.getMonth() + 1, // 月份	
			"d+" : this.getDate(), // 日	
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时	
			"H+" : this.getHours(), // 小时	
			"m+" : this.getMinutes(), // 分	
			"s+" : this.getSeconds(), // 秒	
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度	
			"S" : this.getMilliseconds()// 毫秒	
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
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));	
		}	
		if (/(E+)/.test(fmt)) {	
			fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "")
							+ week[this.getDay() + ""]);
		}	
		for (var k in o) {	
			if (new RegExp("(" + k + ")").test(fmt)) {	
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			}	
		}
		return fmt;
	}
});