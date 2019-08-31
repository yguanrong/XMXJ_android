/**
 * jssdk 2.0
 */
( function( global, factory ) {

  "use strict";

  if ( typeof module === "object" && typeof module.exports === "object" ) {
    module.exports = global.document ?
      factory( global, true ) :
      function( w ) {
        if ( !w.document ) {
          throw new Error( "EZUIPlayer requires a window with a document" );
        }
        return factory( w );
      };
  } else {
    console.log("global",global);
    factory( global );
  }

  // Pass this if window is not defined yet
} )( typeof window !== "undefined" ? window : this, function( window, noGlobal ){

  /**
   * @preserve HTML5 Shiv 3.7.3 | @afarkas @jdalton @jon_neal @rem | MIT/GPL2 Licensed
   */
  !function(a,b){function c(a,b){var c=a.createElement("p"),d=a.getElementsByTagName("head")[0]||a.documentElement;return c.innerHTML="x<style>"+b+"</style>",d.insertBefore(c.lastChild,d.firstChild)}function d(){var a=t.elements;return"string"==typeof a?a.split(" "):a}function e(a,b){var c=t.elements;"string"!=typeof c&&(c=c.join(" ")),"string"!=typeof a&&(a=a.join(" ")),t.elements=c+" "+a,j(b)}function f(a){var b=s[a[q]];return b||(b={},r++,a[q]=r,s[r]=b),b}function g(a,c,d){if(c||(c=b),l)return c.createElement(a);d||(d=f(c));var e;return e=d.cache[a]?d.cache[a].cloneNode():p.test(a)?(d.cache[a]=d.createElem(a)).cloneNode():d.createElem(a),!e.canHaveChildren||o.test(a)||e.tagUrn?e:d.frag.appendChild(e)}function h(a,c){if(a||(a=b),l)return a.createDocumentFragment();c=c||f(a);for(var e=c.frag.cloneNode(),g=0,h=d(),i=h.length;i>g;g++)e.createElement(h[g]);return e}function i(a,b){b.cache||(b.cache={},b.createElem=a.createElement,b.createFrag=a.createDocumentFragment,b.frag=b.createFrag()),a.createElement=function(c){return t.shivMethods?g(c,a,b):b.createElem(c)},a.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+d().join().replace(/[\w\-:]+/g,function(a){return b.createElem(a),b.frag.createElement(a),'c("'+a+'")'})+");return n}")(t,b.frag)}function j(a){a||(a=b);var d=f(a);return!t.shivCSS||k||d.hasCSS||(d.hasCSS=!!c(a,"article,aside,dialog,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}mark{background:#FF0;color:#000}template{display:none}")),l||i(a,d),a}var k,l,m="3.7.3",n=a.html5||{},o=/^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,p=/^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,q="_html5shiv",r=0,s={};!function(){try{var a=b.createElement("a");a.innerHTML="<xyz></xyz>",k="hidden"in a,l=1==a.childNodes.length||function(){b.createElement("a");var a=b.createDocumentFragment();return"undefined"==typeof a.cloneNode||"undefined"==typeof a.createDocumentFragment||"undefined"==typeof a.createElement}()}catch(c){k=!0,l=!0}}();var t={elements:n.elements||"abbr article aside audio bdi canvas data datalist details dialog figcaption figure footer header hgroup main mark meter nav output picture progress section summary template time video",version:m,shivCSS:n.shivCSS!==!1,supportsUnknownElements:l,shivMethods:n.shivMethods!==!1,type:"default",shivDocument:j,createElement:g,createDocumentFragment:h,addElements:e};a.html5=t,j(b),"object"==typeof module&&module.exports&&(module.exports=t)}("undefined"!=typeof window?window:this,document);

  /*! @source http://purl.eligrey.com/github/classList.js/blob/master/classList.js */
  if("document" in self){if(!("classList" in document.createElement("_"))){(function(j){"use strict";if(!("Element" in j)){return}var a="classList",f="prototype",m=j.Element[f],b=Object,k=String[f].trim||function(){return this.replace(/^\s+|\s+$/g,"")},c=Array[f].indexOf||function(q){var p=0,o=this.length;for(;p<o;p++){if(p in this&&this[p]===q){return p}}return -1},n=function(o,p){this.name=o;this.code=DOMException[o];this.message=p},g=function(p,o){if(o===""){throw new n("SYNTAX_ERR","An invalid or illegal string was specified")}if(/\s/.test(o)){throw new n("INVALID_CHARACTER_ERR","String contains an invalid character")}return c.call(p,o)},d=function(s){var r=k.call(s.getAttribute("class")||""),q=r?r.split(/\s+/):[],p=0,o=q.length;for(;p<o;p++){this.push(q[p])}this._updateClassName=function(){s.setAttribute("class",this.toString())}},e=d[f]=[],i=function(){return new d(this)};n[f]=Error[f];e.item=function(o){return this[o]||null};e.contains=function(o){o+="";return g(this,o)!==-1};e.add=function(){var s=arguments,r=0,p=s.length,q,o=false;do{q=s[r]+"";if(g(this,q)===-1){this.push(q);o=true}}while(++r<p);if(o){this._updateClassName()}};e.remove=function(){var t=arguments,s=0,p=t.length,r,o=false,q;do{r=t[s]+"";q=g(this,r);while(q!==-1){this.splice(q,1);o=true;q=g(this,r)}}while(++s<p);if(o){this._updateClassName()}};e.toggle=function(p,q){p+="";var o=this.contains(p),r=o?q!==true&&"remove":q!==false&&"add";if(r){this[r](p)}if(q===true||q===false){return q}else{return !o}};e.toString=function(){return this.join(" ")};if(b.defineProperty){var l={get:i,enumerable:true,configurable:true};try{b.defineProperty(m,a,l)}catch(h){if(h.number===-2146823252){l.enumerable=false;b.defineProperty(m,a,l)}}}else{if(b[f].__defineGetter__){m.__defineGetter__(a,i)}}}(self))}else{(function(){var b=document.createElement("_");b.classList.add("c1","c2");if(!b.classList.contains("c2")){var c=function(e){var d=DOMTokenList.prototype[e];DOMTokenList.prototype[e]=function(h){var g,f=arguments.length;for(g=0;g<f;g++){h=arguments[g];d.call(this,h)}}};c("add");c("remove")}b.classList.toggle("c3",false);if(b.classList.contains("c3")){var a=DOMTokenList.prototype.toggle;DOMTokenList.prototype.toggle=function(d,e){if(1 in arguments&&!this.contains(d)===!e){return e}else{return a.call(this,d)}}}b=null}())}};


  Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
      "M+": this.getMonth() + 1, //月份
      "d+": this.getDate(), //日
      "h+": this.getHours(), //小时
      "m+": this.getMinutes(), //分
      "s+": this.getSeconds(), //秒
      "q+": Math.floor((this.getMonth() + 3) / 3), //季度
      "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  };


  var Domain = 'https://open.ys7.com';
  var logDomain = 'https://log.ys7.com/statistics.do';

  var jqueryJS =    Domain + '/sdk/js/2.0/js/jquery.min.js';
  var ckplayerJS =  Domain + '/sdk/js/2.0/js/ckplayer/ckplayer.js';
  var ckplayerSWF = Domain + '/sdk/js/2.0/js/ckplayer/ckplayer.swf';
  var m3u8SWF =     Domain + '/sdk/js/2.0/js/ckplayer/m3u8.swf';
  var flv_js =      Domain + '/sdk/js/2.0/js/flv.min.js';
  var hlsJS =       Domain + '/sdk/js/2.0/js/hls.min.js';
  var mpegJS =      Domain + '/sdk/js/2.0/js/jsmpeg.min.js';
  var wav =         Domain + '/sdk/js/2.0/js/wav-audio-encoder.js';

  // 当前页面是否是https协议
  var isHttps = window.location.protocol === 'https:' ? true : false;
  // 是否为移动端
  var isMobile = !!navigator.userAgent.match(/(iPhone|iPod|iPad|Android|ios|SymbianOS)/i);
  var testVideo = document.createElement('video');
  // 是否支持video标签和addEventListener方法（主要为了区别ie8）
  var isModernBrowser = !!testVideo.canPlayType && !!window.addEventListener;
  // 是否能使用video原生播放hls，目前只有safari可以支持原生video播放。
  var isNativeSupportHls = isModernBrowser && testVideo.canPlayType('application/vnd.apple.mpegURL');
  // 是否能使用hls.js播放
  var isSupportHls = false;
  // 是否使用flash
  var useFlash = false;

  // 本地信息上报
  var LOCALINFO = 'open_netstream_localinfo';
  // 预览主表上报
  var PLAY_MAIN = 'open_netstream_play_main';

  function dclog(obj){
    var logObj = {
      Ver: 'v.2.0.0',
      PlatAddr: 'open.ys7.com',
      ExterVer: 'Ez.2.0.0',
      CltType: 102,
      StartTime: (new Date()).Format('yyyy-MM-dd hh:mm:ss.S'),  // 每个日志包含当前的时间
      OS: navigator.platform
    }
    for(var i in obj){
      logObj[i] = obj[i];
    }

    var tempArray = [];
    for(var j in logObj){
      tempArray.push(j + '=' + logObj[j]);
    }
    var params = '?' + tempArray.join('&');
    // 上报一次本地统计信息
    var img = new Image();
    img.src = logDomain + params;
  }

  // 上报一次本地信息
  dclog({
    systemName: LOCALINFO
  });

  var RTMP_REG = /^rtmp/;
  var HLS_REG = /\.m3u8/;

  // 获取元素样式
  function getStyle(el){
    return window.getComputedStyle
      ? window.getComputedStyle(el, null)
      : el.currentStyle;
  }

  // 加载js
  function addJs(filepath, callback){
    var oJs = document.createElement("script");
    oJs.setAttribute("src", filepath);
    oJs.onload = callback;
    document.getElementsByTagName("head")[0].appendChild(oJs);
  }


  var EZUIPlayer = function(videoId){
    console.log("定义EZUIPlayer,参数：videoId",videoId)
    if(!isModernBrowser){
      throw new Error('不支持ie8等低版本浏览器');
      return;
    }
    if(typeof videoId !== 'string'){
      throw new Error('EZUIPlayer requires parameter videoId');
    }
    this.videoId = videoId;
    console.log("this",this)
    this.video = document.getElementById(videoId);
    if(!this.video){
      throw new Error('EZUIPlayer requires parameter videoId');
    }
    this.opt = {};
    this.opt.sources = [];
    var sources = this.video.getElementsByTagName('source');
    console.log("sources",this.video.getElementsByTagName('source'))
    // 转为数组对象，不受removeChild影响
    sources = Array.prototype.slice.call(sources, 0);

    if(this.video.src){
      // 移动端删除rtmp地址
      if(isMobile && RTMP_REG.test(this.video.src)){
        console.log("isMobile",isMobile);
        this.video.removeAttribute('src');
        this.video.load();
      }else{
        this.opt.sources.push(this.video.src);
      }
    }

    var l = sources.length;
    console.log("l",l);
    if(l > 0){
      for(var i = 0; i < l; i++){
        // 移动端删除rtmp地址
        if(isMobile && RTMP_REG.test(sources[i].src)){
          this.video.removeChild(sources[i]);
        }else{
          this.opt.sources.push(sources[i].src);
        }
      }
    }
    if(this.opt.sources.length < 1){
      throw new Error('no source found in video tag.');
    }
    this.opt.cur = 0;


    // 事件存储
    this.handlers = {};

    this.opt.poster = this.video.poster;
    var videoStyle = getStyle(this.video);
    var width = this.video.width;
    var height = this.video.height;
    if(width){
      this.opt.width = width;
      if(height){
        this.opt.height = height;
      }else{
        this.opt.height = 'auto';
      }
      this.log('video width:' + this.opt.width + ' height:' + this.opt.height);
    }else{
      this.opt.width = videoStyle.width;
      this.opt.height = videoStyle.height;
      this.log('videoStyle.width:' + videoStyle.width + ' wideoStyle.height:' + videoStyle.height);
    }
    this.opt.parentId = videoId;
    this.opt.autoplay = this.video.autoplay ? true : false;
    this.log('autoplay:' + this.video.autoplay);

    this.opt.currentSource = this.opt.sources[this.opt.cur];

    this.getRealUrl();
    this.initTime = (new Date()).getTime();

    this.on('play', function(){
      // 上报播放成功信息
      dclog({
        systemName: PLAY_MAIN,
        playurl: this.opt.currentSource,
        Time: (new Date()).Format('yyyy-MM-dd hh:mm:ss.S'),
        Enc: 0,  // 0 不加密 1 加密
        PlTp: 1,  // 1 直播 2 回放
        Via: 2,  // 2 服务端取流
        ErrCd: 0,
        Cost: (new Date()).getTime() - this.initTime  // 毫秒数
      });
    });
    this.retry = 2;
    this.on('error', function(){
      dclog({
        systemName: PLAY_MAIN,
        playurl: this.opt.currentSource,
        cost: -1,
        ErrCd: -1
      });
    });

  };

  // 事件监听
  EZUIPlayer.prototype.on = function(eventName, callback){
    if(typeof eventName !== 'string' || typeof callback !== 'function'){
      return;
    }
    if(typeof this.handlers[eventName] === 'undefined'){
      this.handlers[eventName] = [];
    }
    this.handlers[eventName].push(callback);
  };

  // 事件触发
  EZUIPlayer.prototype.emit = function(){
    if(this.handlers[arguments[0]] instanceof Array){
      var handlers = this.handlers[arguments[0]];
      var l = handlers.length;
      for(var i = 0; i < l; i++){
        handlers[i].apply(this, Array.prototype.slice.call(arguments, 1));
      }
    }
  };

  EZUIPlayer.prototype.getRealUrl = function(){

    if(!this.opt.currentSource){
      this.log('未找到合适的播放URL');
      return;
    }
    var me = this;
    // 如果不是ezopen打头的，走原来的播放模式
    if(!/^ezopen:\/\//.test(this.opt.currentSource)){
      this.tryPlay();
    } else {
      // 如果是ezopen协议地址，先校验一下地址的合法性
      if(!/^ezopen:\/\//.test(this.opt.currentSource)){
        throw new Error('EZOPEN地址必须要以ezopen://开头');
        return;
      } else if(this.opt.currentSource.indexOf('.com/') === -1){
        throw new Error('EZOPEN地址格式不正确');
        return;
      } else if(!/[a-z\d]{32}(\.hd)?\.live/.test(this.opt.currentSource)){
        throw new Error('EZOPEN地址格式uuid格式不正确');
        return;
      } else if(/(.*.hls.*|.*.m3u8.*|.*.wss.*|.*.flv.*|.*.rtmp.*){2,}/.test(this.opt.currentSource)){
        throw new Error('EZOPEN地址多于两个播放协议');
        return;
      } else if(this.opt.currentSource.search(/(.hls|.m3u8|.wss|.flv|.rtmp)/) !== -1 && !/.live(.hls|.m3u8|.wss|.flv|.rtmp)/.test(this.opt.currentSource)){
        throw new Error('请指定正确的播放协议');
        return;
      } else if(this.opt.currentSource.search(/(.hls|.m3u8|.wss|.flv|.rtmp)/) === -1 && !/[a-z\d]{32}(\.hd)?\.live$/.test(this.opt.currentSource)){
        throw new Error('EZOPEN地址结尾不正确');
        return;
      } else {
        var that = this;
        addJs(flv_js,function () {

          var para = {
            "ezopen": that.opt.currentSource,
            "userAgent": window.navigator.userAgent,
            "isFlv": flvjs && flvjs.isSupported() ? flvjs.isSupported() : false,
            "addressTypes": "HLS,RTMP,WS,FLV",
            "isHttp": window.location.protocol.indexOf('s') > 0 ? false : true,
          };

          dclog({
            "ezopen": that.opt.currentSource,
            "userAgent": window.navigator.userAgent,
            "isFlv": flvjs && flvjs.isSupported() ? flvjs.isSupported() : false,
            "addressTypes": "HLS,RTMP,WS,FLV",
            "isHttp": window.location.protocol.indexOf('s') > 0 ? false : true,
            'systemName': 'EZOPEN',
          });

          that.log('---------------------------------------');
          that.log('入参(ezopen)是：   '+ para.ezopen);
          that.log('---------------------------------------');
          that.log('入参(userAgent)是：   '+ para.userAgent);
          that.log('---------------------------------------');
          that.log('入参(isFlv)是：   '+ para.isFlv);
          that.log('---------------------------------------');
          that.log('入参(addressTypes)是：   '+ para.addressTypes);
          that.log('---------------------------------------');
          that.log('入参(isHttp)是：   '+ para.isHttp);
          that.log('---------------------------------------');

          addJs(jqueryJS,function () {
            $.ajax({
              url: 'https://open.ys7.com/api/lapp/live/url/ezopen',
              type: 'get',
              data: para,
              dataType: 'jsonp',
              success: function (data) {
                if(data.code === '200'){
                  that.log('播放地址是：   '+data.data);
                  that.video.src = data.data;
                  that.video.load();
                  that.tryPlay(data.data);
                } else {
                  that.log('data：   '+ JSON.stringify(data));
                  throw new Error(data.msg);
                  return;
                }
              },
              error: function (err) {
                that.log('获取播放地址错误：   '+JSON.stringify(err));
              }
            })
          });

          // var jsonUrl = 'https://test2.ys7.com:65/api/lapp/live/url/ezopen';
          // jsonp(jsonUrl,para,function (data) {
          //   if(data.code === '200'){
          //     that.log('播放地址是：   '+data.data);
          //     that.video.src = data.data;
          //     that.video.load();
          //     that.tryPlay(data.data);
          //   } else {
          //     that.log('data：   '+ JSON.stringify(data));
          //     throw new Error(data.msg);
          //     return;
          //   }
          // })
        });
      }
    }
  };

  // 尝试播放
  EZUIPlayer.prototype.tryPlay = function(url){
    // this.opt.currentSource = this.opt.sources[this.opt.cur];
    // if(!this.opt.currentSource){
    //   this.log('未找到合适的播放URL');
    //   return;
    // }

    // 如果是ezopen协议的地址，将ezopen协议地址，转换后的真实地址赋值
    if(url && typeof url !== 'undefined'){
      this.opt.currentSource = url;
    }


    var me = this;
    // 如果是HLS地址
    if(/\.m3u8/.test(this.opt.currentSource)){
      // 如果是手机浏览器环境,或者原生支持HLS播放的,直接使用video标签播放
      // 否则尝试使用hls.js播放，
      // 最后使用flash
      if(isMobile || isNativeSupportHls){
        this.log('使用原生video');
        this.video.style.heght = this.opt.height = Number(this.opt.width.replace(/px$/g, '')) * 9 / 16 + 'px';
        this.initVideoEvent();
      }else{
        if(isHttps){
          addJs(ckplayerJS, function(){
            me.initCKPlayer();
          });
        }else{
          addJs(hlsJS, function(){
            isSupportHls = Hls.isSupported();
            if(isSupportHls){
              me.log('使用hls.js');
              me.initHLS();
            }else{
              useFlash = true;
              me.log('2 使用flash');
              addJs(ckplayerJS, function(){
                me.initCKPlayer();
              });
            }
          });
        }
      }
    }else if(/^rtmp:/.test(this.opt.currentSource)){
      if(isMobile){
        this.opt.cur++;
        console.log("移动端播放rtmp");
        this.tryPlay();
        return;
      }else{
        console.log("PC端播放rtmp");
        addJs(ckplayerJS, function(){ console.log("initCKPlayer")
          me.initCKPlayer();
        });
      }
    }else if(/^wss:|^ws:/.test(this.opt.currentSource)){
      /*
      *   WS协议的JSMpeg的不支持IE11以下的版本
      *   开放平台官网不支持IE8打开，所以官网上面不兼容两个人版本IE9 ，和IE10
      *
      * */
      if (!ltIE11()) {
        addJs(mpegJS, function(){
          me.initJSmpeg();
        });
      } else {
        alert('WS协议不支持Ie11以下的浏览器！请使用IE11，或者更高版本的浏览器');
        return;
      }
    }else if (/\.flv/.test(this.opt.currentSource)){
      addJs(flv_js, function(){
        me.log("使用flv.js播放");
        me.initflv();
      });
    }
  };

  // 初始化hls.js
  EZUIPlayer.prototype.initHLS = function(){
    var me = this;
    var hls = new Hls({defaultAudioCodec: 'mp4a.40.2'}); // 萤石设备默认使用 AAC LC 音频编码
    hls.loadSource(this.opt.currentSource);
    hls.attachMedia(this.video);
    hls.on(Hls.Events.MANIFEST_PARSED, function(){
      if(me.opt.autoplay){
        me.video.play();
      }
      me.initVideoEvent();
    });
    hls.on(Hls.Events.ERROR, function (event, data) {
      if (data.fatal) {
        switch(data.type) {
          case Hls.ErrorTypes.NETWORK_ERROR:
            // try to recover network error
            console.log("fatal network error encountered, try to recover");
            hls.startLoad();
            break;
          case Hls.ErrorTypes.MEDIA_ERROR:
            console.log("fatal media error encountered, try to recover");
            hls.recoverMediaError();
            break;
          default:
            // cannot recover
            hls.destroy();
            break;
        }
      }
    });

    this.hls = hls;
  };

  // 日志
  EZUIPlayer.prototype.log = function(msg){
    this.emit('log', msg);
  };

  // 初始化ckplayer
  EZUIPlayer.prototype.initCKPlayer = function(url){
    this.log('ckplayer初始化');
    var me = this;
    var events = {
      'play': function(){me.emit('play')},
      'pause': function(){me.emit('pause')},
      'error': function(){me.emit('error')}
    };
    window.ckplayer_status = function(){
      me.log(arguments);
      events[arguments[0]] && events[arguments[0]]();
    };

    // 新增相同id的div标签，然后删除video标签
    this.videoFlash = document.createElement('DIV');
    this.video.parentNode.replaceChild(this.videoFlash, this.video);
    this.video = this.videoFlash;
    this.videoFlash.id = this.opt.parentId;
    var flashvars = null;
    // 如果rtmp服务器环境设置了视频暂停则断开链接
    // 需要修改ckplayer.js  setup参数第30个值
    // 在播放暂停后点击播放是否采用重新链接的方式
    if(/^rtmp/.test(this.opt.currentSource)){
      flashvars = {
        f: this.opt.currentSource,
        c: 0,
        p: this.opt.autoplay ? 1 : 0,
        i: this.opt.poster,
        lv: 1,
        loaded: 'loadHandler'
      };
    }else if(/\.m3u8/.test(this.opt.currentSource)){
      flashvars = {
        s:4,  // 4-使用swf视频流插件播放
        f:m3u8SWF,
        a: this.opt.currentSource,
        c: 0,  // 0-使用ckplayer.js的配置 1-使用ckplayer.xml的配置
        lv:1, // 1-直播 0-普通方式
        p: this.opt.autoplay ? 1 : 0,   // 1-默认播放 0-默认暂停
        i: this.opt.poster,
        loaded: 'loadHandler'
      };
    }else{
      flashvars = {
        f: this.opt.currentSource,
        c: 0,
        p: 1,
        loaded: 'loadHandler'
      };
    }
    var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    this.flashId = this.opt.parentId + 'flashId';
    window.CKobject.embedSWF(ckplayerSWF,this.opt.parentId,this.flashId,this.opt.width,this.opt.height,flashvars,params);
  };

  EZUIPlayer.prototype.initVideoEvent = function(){
    var me = this;
    var EVENT = {
      'loadstart': function(e){
        me.log('loadstart...当浏览器开始查找音频/视频时...');
        me.emit('loadstart', e);
      },
      'durationchange': function(e){
        me.log('durationchange...当音频/视频的时长已更改时...');
        me.emit('durationchange', e);
      },
      'loadedmetadata': function(e){
        me.log('loadedmetadata...当浏览器已加载音频/视频的元数据时...');
        me.emit('loadedmetadata', e);
      },
      'loadeddata': function(e){
        me.log('loadeddata...当浏览器已加载音频/视频的当前帧时...');
        me.emit('loadeddata', e);
      },
      'progress': function(e){
        me.log('progress...当浏览器正在下载音频/视频时...');
        me.emit('progress', e);
      },
      'canplay': function(e){
        me.log('canplay...当浏览器可以播放音频/视频时...');
        me.emit('canplay', e);
      },
      'canplaythrough': function(e){
        me.log('canplaythrough...当浏览器可在不因缓冲而停顿的情况下进行播放时...');
        me.emit('canplaythrough', e);
      },
      'abort': function(e){
        me.log('abort...当音频/视频的加载已放弃时...');
        me.emit('abort', e);
      },
      'emptied': function(e){
        me.log('emptied...当目前的播放列表为空时...');
        me.emit('emptied', e);
      },
      'ended': function(e){
        me.log('ended...当目前的播放列表已结束时...');
        me.emit('ended', e);
      },
      'pause': function(e){
        me.log('pause...当音频/视频已暂停时...');
        me.emit('pause', e);
      },
      'play': function(e){
        me.log('play...当音频/视频已开始或不再暂停时...');
        me.emit('play', e);
      },
      'playing': function(e){
        me.log('playing...当音频/视频在已因缓冲而暂停或停止后已就绪时...');
        me.emit('playing', e);
      },
      'ratechange': function(e){
        me.log('ratechange...当音频/视频的播放速度已更改时...');
        me.emit('ratechange', e);
      },
      'seeked': function(e){
        me.log('seeked...当用户已移动/跳跃到音频/视频中的新位置时...');
        me.emit('seeked', e);
      },
      'seeking': function(e){
        me.log('seeking...当用户开始移动/跳跃到音频/视频中的新位置时...');
        me.emit('seeking', e);
      },
      'stalled': function(e){
        me.log('stalled...当浏览器尝试获取媒体数据，但数据不可用时...');
        me.emit('stalled', e);
      },
      'suspend': function(e){
        me.log('suspend...当浏览器刻意不获取媒体数据时...');
        me.emit('suspend', e);
        if(me.opt.autoplay){
          me.video.play();
        }
      },
      'timeupdate': function(e){
        //me.log('timeupdate...当目前的播放位置已更改时...');
        me.emit('timeupdate', e);
      },
      'volumechange': function(e){
        me.log('volumechange...当音量已更改时...');
        me.emit('volumechange', e);
      },
      'waiting': function(e){
        me.log('waiting...当视频由于需要缓冲下一帧而停止...');
        me.emit('waiting', e);
      },
      'error': function(e){
        me.log('error...当在音频/视频加载期间发生错误时...');
        me.emit('error', e);
      }

    };
    for(var i in EVENT){
      this.video.addEventListener( i, EVENT[i], false);
    }

    ios11Hack(this.video);

  };

  EZUIPlayer.prototype.initJSmpeg = function () {
    this.canvasEle = document.createElement('canvas');
    this.canvasEle.style.width = this.opt.width;
    this.canvasEle.style.height = this.opt.height;
    this.video.parentNode.replaceChild(this.canvasEle,this.video);
    this.canvasEle.id = this.opt.parentId;
    var player;
    if(player && player.destroy){
      player.destroy();
    }
    player = new JSMpeg.Player(this.opt.currentSource, {canvas: this.canvasEle});
  };

  EZUIPlayer.prototype.initflv = function () {
    if (flvjs.isSupported()) {
      var player = this.video;
      var hasControls = player.getAttribute('controls');
      if(!hasControls){
        player.setAttribute('controls',true);
      }
      var flvPlayer = flvjs.createPlayer({
        type: 'flv',
        url: this.opt.currentSource,
        isLive: true,
      }, {
        enableStashBuffer: true,
        stashInitialSize: 128,
        enableWorker: true
      });

      flvPlayer.attachMediaElement(player);
      flvPlayer.load();
      flvPlayer.play();
    } else{
      this.log("浏览器不支持flv播放");
      throw new Error('浏览器不支持flv播放');
      return;
    }
  };


  EZUIPlayer.prototype.play = function(){
    this.opt.autoplay = true;
    if(!!window['CKobject']){
      CKobject.getObjectById(this.flashId).videoPlay();
    }else if(!!this.video){
      this.video.play();
    }

  };

  EZUIPlayer.prototype.pause = function(){
    this.opt.autoplay = false;
    if(!!window['CKobject']){
      CKobject.getObjectById(this.flashId).videoPause();
    }else if(!!this.video){
      this.video.pause();
    }
  };
  EZUIPlayer.prototype.stop = function () {
    // 执行停止
    this.opt.autoplay = false;
    if (!!window['CKobject']) {
      CKobject.getObjectById(this.flashId).videoClear();
    } else if (!!this.video) {
      if (!!this.hls) {   // hls停止依赖this.hls
        // 通过暂停停止播放
        this.video.pause();
        // 停止取流
        this.hls.stopLoad();
      } else if (!!this.flv) {
        this.flv.pause();
        this.flv.unload();
        this.flv.detachMediaElement();
        this.flv.destroy();
        this.flv = null;
      } else if(!!this.JSmpeg) {
        this.JSmpeg.stop();
        // this.JSmpeg.destroy();
      }
    }
  };

  EZUIPlayer.prototype.load = function(){
    if(!!window['CKobject']){
      // flash load
    }else if(!!this.video){
      this.video.load();
    }
  };

  //格式化参数
  function formatParams(data) {
    var arr_params = [];
    for (var name in data) {
      arr_params.push(encodeURIComponent(name) + "=" + encodeURIComponent(data[name]));
    }
    arr_params.push(("v=" + Math.random()).replace("."));
    return arr_params.join("&");
  }


  // iOS11手机HLS直播在m3u8响应时间过长后不继续请求的hack
  function ios11Hack(video){
    var isloadeddata = false;
    var isPlaying = false;
    var stalledCount = 0;
    video.addEventListener('loadeddata', function(){
      isloadeddata = true;
    }, false);
    video.addEventListener('stalled', function(){
      stalledCount++;
      if(!isPlaying){
        if(stalledCount >= 2 && !isloadeddata){
          video.load();
          video.play();
          isloadeddata = false;
          isPlaying = false;
          stalledCount = 0;
        }
      }
    }, false);
    video.addEventListener('playing', function(){
      isPlaying = true;
    });
  }

  /*
  *
  * 值	    值类型	        值说明
    -1	  Number	    不是ie浏览器
    6	    Number	    ie版本<=6
    7	    Number	      ie7
    8	    Number	      ie8
    9	    Number	      ie9
    10	  Number	      ie10
    11	  Number	      ie11
    'edge'	String	    ie的edge浏览器
  * */
  function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
      var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
      reIE.test(userAgent);
      var fIEVersion = parseFloat(RegExp["$1"]);
      if(fIEVersion == 7) {
        return 7;
      } else if(fIEVersion == 8) {
        return 8;
      } else if(fIEVersion == 9) {
        return 9;
      } else if(fIEVersion == 10) {
        return 10;
      } else {
        return 6;//IE版本<=7
      }
    } else if(isEdge) {
      return 'edge';//edge
    } else if(isIE11) {
      return 11; //IE11
    }else{
      return -1;//不是ie浏览器
    }
  }

  function ltIE11() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
    if (isIE) {
      return true;
    } else {
      return false;
    }
  }

  // 判断浏览器终端类型
  function browserTerminal(){
    var u = window.navigator.userAgent;
    var browserClass = {
      trident: u.indexOf('Trident') > -1, //IE内核
      presto: u.indexOf('Presto') > -1, //opera内核
      webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
      gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') === -1, //火狐内核
      mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
      ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
      android: u.indexOf('Android') > -1 || u.indexOf('Adr') > -1, //android终端
      iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
      iPad: u.indexOf('iPad') > -1, //是否iPad
      webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
      weixin: u.indexOf('MicroMessenger') > -1, //是否微信
      qq: u.match(/\sQQ/i) == " qq", //是否QQ
      ie: u.indexOf('Trident') > -1 || u.indexOf('MSIE') > -1 || u.indexOf('compatible') > -1,
    };
    return browserClass;
  }

  // 根据uuid生成相应的地址
  // function generateAddress(uuid){
  //   var indexPto = window.location.protocol.indexOf('s');
  //   var addressClass = {
  //     rtmpAddress:    `rtmp://rtmp.open.ys7.com/openlive/${uuid}`,
  //     rtmpAddressHd:  `rtmp://rtmp.open.ys7.com/openlive/${uuid}.hd`,
  //     hlsAddress:     `http://hls.open.ys7.com/openlive/${uuid}.m3u8`,
  //     hlsAddressHd:   `http://hls.open.ys7.com/openlive/${uuid}.hd.m3u8`,
  //     wssAddress:     `wss://wsopen.ys7.com/${uuid}`,
  //     flvAddress:     indexPto > 0 ?  `https://flvopen.ys7.com:9188/openlive/${uuid}.flv` : `http://rtmp.open.ys7.com:9088/openlive/${uuid}.flv`,
  //     flvAddressHd:   indexPto > 0 ?  `https://flvopen.ys7.com:9188/openlive/${uuid}.hd.flv` : `http://rtmp.open.ys7.com:9088/openlive/${uuid}.hd.flv`,
  //   };
  //   return addressClass;
  // }
  
  //判断指定了哪种播放协议
  // function getAgreement(url) {
  //   var uuid = url.match(/[a-z\d]{32}/)[0];
  //   var address = generateAddress(uuid);
  //   var agreementObj = {};
  //   // 判断是否指定了高清协议
  //   if (url.indexOf('.hd.live') > -1 ){
  //     if (url.indexOf('hls') > -1 || url.indexOf('m3u8') > -1) {
  //       agreementObj.agreement = 'hls';
  //       agreementObj.url = address['hlsAddressHd'];
  //     } else if (url.indexOf('rtmp') > -1) {
  //       agreementObj.agreement = 'rtmp';
  //       agreementObj.url = address['rtmpAddressHd'];
  //     } else if (url.indexOf('wss') > -1) {
  //       agreementObj.agreement = 'wss';
  //       agreementObj.url = address['wssAddress'];
  //     } else if (url.indexOf('flv') > -1) {
  //       agreementObj.agreement = 'flv';
  //       agreementObj.url = address['flvAddressHd'];
  //     } else {
  //       agreementObj.agreement = 'null';
  //       agreementObj.hls = address['hlsAddressHd'];
  //       agreementObj.flv = address['flvAddressHd'];
  //       agreementObj.rtmp = address['rtmpAddressHd'];
  //     }
  //   } else {
  //     if (url.indexOf('hls') > -1 || url.indexOf('m3u8') > -1) {
  //       agreementObj.agreement = 'hls';
  //       agreementObj.url = address['hlsAddress'];
  //     } else if (url.indexOf('rtmp') > -1) {
  //       agreementObj.agreement = 'rtmp';
  //       agreementObj.url = address['rtmpAddress'];
  //     } else if (url.indexOf('wss') > -1) {
  //       agreementObj.agreement = 'wss';
  //       agreementObj.url = address['wssAddress'];
  //     } else if (url.indexOf('flv') > -1) {
  //       agreementObj.agreement = 'flv';
  //       agreementObj.url = address['flvAddress'];
  //     } else {
  //       agreementObj.agreement = 'null';
  //       agreementObj.hls = address['hlsAddress'];
  //       agreementObj.flv = address['flvAddress'];
  //       agreementObj.rtmp = address['rtmpAddress'];
  //     }
  //   }
  //   return agreementObj;
  // }


  function jsonp(url,data,callback) {
    // 回调函数+时间戳
    var cbName = 'callback_' + new Date().getTime();
    // 暴露全局函数给window
    // 判读查询字符串最后一位是否为?或者是&
    var queryString = url.indexOf('?') == -1 ? '?' : '&';
    // 遍历传进来的data实参赋值给查询字符串
    for (var k in data) {
      queryString += k + '=' + data[k] + '&';
    }
    // 查询字符串加上回调函数
    queryString += 'callback=' + cbName;
    // 创建script标签
    var ele = document.createElement('script');
    // 给script标签添加src属性值
    ele.src = url + queryString;

    var head = document.getElementsByTagName('head')[0]
    window[cbName] = function (data) {
      callback(data);
      head.removeChild(ele);
    };
    // 添加到body尾部
    head.appendChild(ele);
  };

    //公共方法
  var Util = {
    //初始化
    init: function(){
      navigator.getUserMedia = navigator.getUserMedia ||
        navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia ||
        navigator.msGetUserMedia;

      window.AudioContext = window.AudioContext ||
        window.webkitAudioContext;
    },
    //日志
    log: function(str){
      console.log.apply(console,arguments);
    }
  };


  var EZUITalk = function(bizParams){
    if(bizParams && typeof bizParams !== 'object'){
      throw new Error("EZUITalk requires parameter it doesn't right");
      return;
    }
    var audioId = bizParams.id;
    var Token = bizParams.token;
    var uuid = bizParams.uuid;
    if(typeof audioId !== 'string' || typeof audioId === 'undefined'){
      throw new Error('EZUITalk requires parameter audioId');
      return;
    }
    this.audio = document.getElementById(audioId);
    if(!this.audio){
      throw new Error('EZUITalk requires parameter audioElement');
      return;
    }
    if(!Token){
      throw new Error('EZUITalk requires parameter accessToken');
      return
    }
    if(!uuid){
      throw new Error('EZUITalk requires parameter uuid');
      return;
    }
    Util.init();
    if(!navigator.getUserMedia){
      throw new Error('当前浏览器不支持录音功能');
      return;
    }
    var browserC = browserTerminal();
    // if (!browserC.mobile) {
    //   throw new Error('对讲功能仅在手机中支持进行对讲');
    //   return;
    // }

    // 事件存储
    this.handlers = {};
    var _this = this;
    this.BUFFER = [];
    this.encoder = undefined;


    this.isEnded = true;
    this.LENGTH = 20;
    this.audio.addEventListener('ended', function(){
      _this.isEnded = true;
    });

    // 对讲的播放功能
    this.audioPlay = function(){
      if(this.isEnded){
        this.isEnded = false;
        if(this.BUFFER.length > this.LENGTH){
          this.BUFFER = this.BUFFER.slice(0, this.LENGTH)
        }
        this.encoder = new WavAudioEncoder(16000, 1);
        var buf = this.BUFFER.shift();
        while(buf){
          this.encoder.encode(buf);
          buf = this.BUFFER.shift();
        }
        this.audio.src = URL.createObjectURL(this.encoder.finish());
      }
    };

    // 按钮触发对讲功能
    this.start = function(){
      this.wsUrl = 'wss://h5talk.ys7.com:20006/opentalk/' + uuid;
      this.socket = new WebSocket(this.wsUrl);
      this.talk();
    };

    this.talk = function(){
      addJs(wav, function () {
        _this.socket.onopen = function() {
          _this.log('websocket已连接');
          var sendData = {
            'accessToken': Token,
            'uuid': uuid,
          };
          var messageJson = JSON.stringify(sendData);
          _this.socket.send(messageJson);
          _this.log('websocket 发送文本数据');
          _this.startRecord();
        };
        _this.socket.onclose = function() {
          _this.log('websocket已关闭');
        };
        _this.socket.onerror = function(e) {
          _this.log('websocket出错->' + JSON.stringify(e));
        };
        _this.socket.onmessage = function(e) {
          _this.log('websocket 接收数据');
          _this.BUFFER.push(e.data);
          _this.audioPlay();
        };

        _this.audio.setAttribute('autoplay', true);
        _this.audio.play();

      });
    };

    // 对讲中的说话功能
    this.startRecord = function(){
      navigator.mediaDevices.getUserMedia({audio : true})
        .then(function (stream) {
          var context = new AudioContext(),
            microphone = context.createMediaStreamSource(stream), //媒体流音频源
            processor = context.createScriptProcessor(16384,1,1); //js音频处理器

          _this.log(`当前浏览器采样率为： ${context.sampleRate}`);

          processor.onaudioprocess = function(event){
            //监听音频录制过程
            var array = event.inputBuffer.getChannelData(0);
            // _this.log('监听声音录制');
            realTimeWorker.postMessage({ cmd: 'encode', buf: array });
          };

          var realTimeWorker = new Worker('../js/worker.js'); //开启后台线程

          realTimeWorker.onmessage = function(e) {
            //主线程监听后台线程，实时通信
            switch (e.data.cmd) {
              case 'pcm':
                _this.socket.send(e.data.buf);
                console.log(e.data.buf);
                _this.log('websocket 发送数据');
                break;
              default:
                _this.log('未知信息：' + e.data);
            }
          };

          //开始对讲
          if(processor && microphone){
            microphone.connect(processor);
            processor.connect(context.destination);
          }
          _this.log('开始对讲');

          //结束对讲
          _this.stop = function() {
            if(processor && microphone){
              microphone.disconnect();
              processor.disconnect();
            }
            _this.socket.close();
            _this.log('结束对讲');
          };

        }).catch(function (error) {
        var msg;
        switch(error.code || error.name){
          case 'PermissionDeniedError':
          case 'PERMISSION_DENIED':
          case 'NotAllowedError':
            msg = '用户拒绝访问麦克风';
            break;
          case 'NOT_SUPPORTED_ERROR':
          case 'NotSupportedError':
            msg = '浏览器不支持麦克风';
            break;
          case 'MANDATORY_UNSATISFIED_ERROR':
          case 'MandatoryUnsatisfiedError':
            msg = '找不到麦克风设备';
            break;
          default:
            msg = '无法打开麦克风，异常信息:' + (error.code || error.name);
            break;
        }
        _this.log(msg);
        alert(msg);
      })
    }
  };

  // 事件监听
  EZUITalk.prototype.on = function(eventName, callback){
    if(typeof eventName !== 'string' || typeof callback !== 'function'){
      return;
    }
    if(typeof this.handlers[eventName] === 'undefined'){
      this.handlers[eventName] = [];
    }
    this.handlers[eventName].push(callback);
  };

  // 事件触发
  EZUITalk.prototype.emit = function(){
    if(this.handlers[arguments[0]] instanceof Array){
      var handlers = this.handlers[arguments[0]];
      var l = handlers.length;
      for(var i = 0; i < l; i++){
        handlers[i].apply(this, Array.prototype.slice.call(arguments, 1));
      }
    }
  };

  EZUITalk.prototype.log = function (msg) {
    this.emit('log', msg);
  };

  var EZuikit = {
    'EZUIPlayer': EZUIPlayer,
    'EZUITalk': EZUITalk
  };

  if ( !noGlobal ) {
    window.EZuikit = EZuikit;
  }
  return EZuikit;
});
