
//get browser version
    var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
    console.log(userAgent)

    var isFirefox =  navigator.userAgent.indexOf('Firefox') > -1  // 是否是火狐  ，火狐内核Gecko 
    console.log('火狐'+ isFirefox)  // 返回 true  则是 ，false  则不是

    var isWebKit =  navigator.userAgent.indexOf('WebKit') > -1  // 是否是WebKit 内核 
    console.log('谷歌内核'+ isWebKit)  // 返回 true  则是，false  则不是

    var isChrome =  navigator.userAgent.indexOf('Chrome') > -1  // 是否是谷歌 
    console.log('谷歌'+ isChrome)  // 返回 true  则是 ，false  则不是

    var isOpera =  navigator.userAgent.indexOf('Opera') > -1  // 是否是opera ， opera内核 Presto
    console.log('Opera'+ isOpera)  // 返回 true  则是 ，false  则不是

    var isTrident =  navigator.userAgent.indexOf('Trident') > -1  // 是否是IE内核 
    console.log('IE内核'+ isTrident)  // 返回 true  则是 ，false  则不是

    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1  && !isOpera
    console.log('IE '+ isIE)  //判断是否IE浏览器
    //  因为ie10-ie11的版本问题，不再支持document.all判断，所以ie判断函数要重新写
    var isIeL = !!window.ActiveXObject || "ActiveXObject" in window
    console.log('IELLQ '+ isIeL)  //判断是否IE浏览器

    var isIE9 = navigator.userAgent.indexOf("MSIE 9.0")>0
    console.log('IE999 '+ isIE9)  //判断是否IE9  ;如果是其他IE版本，则 MSIE 7.0   MSIE 8.0 
    // 判断是否为移动端
    var browser = {
        versions: function() {
            var u = navigator.userAgent;
            return {
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Firefox') > -1, //火狐内核Gecko
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android
            iPhone: u.indexOf('iPhone') > -1 , //iPhone
            iPad: u.indexOf('iPad') > -1, //iPad
            webApp: u.indexOf('Safari') > -1 //Safari
            };
        }()
    }
var isPhone = browser.versions.mobile || browser.versions.ios || browser.versions.android || browser.versions.iPhone || browser.versions.iPad
console.log('是否为移动端' +isPhone )

// JS判断浏览器是否是IE9以下，处理可能遇到的兼容性问题
if(navigator.appName == "Microsoft Internet Explorer"&&parseInt(navigator.appVersion.split(";")[1].replace(/[ ]/g, "").replace("MSIE",""))<9){
        console.log("您的浏览器版本过低，请使用IE9及以上版本");
}


createMessageBox = function(message,element,level){
    if(message != ""){
        const messageBox = document.createElement('div');
        messageBox.className = 'message';
        messageBox.textContent = message;
        setTimeout(function(){messageBox.remove()},3000);
        element.insertAdjacentElement('beforeend',messageBox);
    }
}

animation_up_and_down = function(){
    if(!($(this).hasClass("up") || $(this).hasClass("down"))){
            $(this).addClass("up");
            setTimeout(()=>{
                $(this).removeClass("up");
                $(this).addClass("down");
            },500);
            setTimeout(()=>{
                $(this).removeClass("down")
            },1000);
        }
}
animation_up = function(){
    if(!$(this).hasClass("up")){
        $(this).addClass("up");
        setTimeout(()=>{
            $(this).removeClass("up");
        },500);
    }
}
animation_down = function(){
    if(!$(this).hasClass("down")){
        $(this).addClass("down");
        setTimeout(()=>{
            $(this).removeClass("down");
        },500);
    }
}
animation_up_and_down_hover = function(){
    if(!$(this).hasClass("up-down")){
        $(this).addClass("up-down");
    }
}
searchbox_shadow1_change = function(){
    const node = this.parentElement;
    if($(node).hasClass("shadow2")){
        $(node).removeClass("shadow2");
    }
    $(node).addClass("shadow1");
}
searchbox_shadow2_change = function(){
    const node = this.parentElement;
    if($(node).hasClass("shadow1")){
        $(node).removeClass("shadow1");
    }
    $(node).addClass("shadow2");
}

nav_set_icon_color = function(e,bgcolor,color){
    $(e).css("background-color",bgcolor);
    e.getElementsByTagName("lord-icon")[0].colors="primary:"+color;
}
animation_nav_expand = function(e){
    $(e).css("animation-name","expand");
    $(e).css("animation-duration","0.2s");
    $(e).css("width","200px");
    const nav_list = e.querySelectorAll("label");
    nav_list.forEach((element)=>{
        $(element).show();
    });
}
animation_nav_collapse = function(e){
    $(e).css("animation-name","collapse");
    $(e).css("animation-duratino","0.2s");
    $(e).css("width","70px");
    const nav_list = e.querySelectorAll("label");
    nav_list.forEach((element)=>{
        $(element).hide();
    });
}
nav_control = function(){
    const nav = this.parentElement.parentElement;
    const control = this;
    if($(nav).width() == 70){
        $(control.getElementsByTagName("lord-icon")[0]).hide();
        $(control.getElementsByTagName("lord-icon")[1]).show();
        animation_nav_expand(nav);
    }else{
        $(control.getElementsByTagName("lord-icon")[0]).show();
        $(control.getElementsByTagName("lord-icon")[1]).hide();
        animation_nav_collapse(nav);
    }
}
function formatTime(value) {
    var nums = (value+"").split(":");
    var length = nums.length;
    var ms = parseInt(nums[length-1] / 10);
    var s = nums[length-2];
    var m = nums[length-3];
    var h = nums[length-4];
    var time = "";
    if (m == 0 || m == undefined) {
        m = "00";
    } else if (m < 10) {
        m = "0" + m;
    }

    if (s == 0 || s == undefined) {
        s = "00";
    }else if (s < 10) {
        s = "0" + s;
    }

    if (ms == 0 || ms == undefined) {
        ms = "000";
    } else if (ms < 10) {
        ms = "00" + ms;
    } else if (ms < 100) {
        ms = "0" + ms;
    }
    if (h = 0 || h == undefined) {
        h = "00";
    } else if (h < 10) {
        h = "0" + h;
    }
    if (h == 0) {
        time = m+":"+s;
    } else {
        time = h + ":" + m + ":" + s;
    }
    return time;
}
function transTime(value) {
    var time = "";
    var h = parseInt(value / 3600);
    value %= 3600;
    var m = parseInt(value / 60);
    value %= 60;
    var s = parseInt(value);
    value %= 1;
    var ms = parseInt(value*1000);
    if (h > 0) {
        time = formatTime(h + ":" + m + ":" + s + ":" + ms);
    } else if (m > 0) {
        time = formatTime(m + ":" + s + ":" + ms);
    } else if (s > 0) {
        time = formatTime(s + ":" + ms);
    } else{
        time = formatTime(ms);
    }
    return time;
}
const pageChangeHTML = {
    "firstPage":'<a href=""><button class="btn btn-primary">首页</button></a>',
    "nextPage":'<a href=""><button class="btn btn-primary">下一页</button></a>',
    "lastPage":'<a href=""><button class="btn btn-primary">上一页</button></a>',
    "endPage":'<a href=""><button class="btn btn-primary">尾页</button></a>'
}
insertStr = function(soure, start, newStr){
   return soure.slice(0, start) + newStr + soure.slice(start);
}
pageChangeUrlGet = function(url,index){
    const index1 = url.indexOf('#');
    const url1 = url.substring(index1);
    const start = url1.indexOf('{');
    const end = url1.indexOf('}');
    if(start == 1 && end != -1){
        const label = url1.substring(start+1,end)
        if(label == 'page'){
            return url.substring(0,index1)+index+url.substring(index1+end+1);
        }
    }else{
        return -1;
    }
}

initPageControlButton = function(pageInfo,element,url){
    const firstUrl = pageChangeUrlGet(url,0);
    const firstHtml = insertStr(pageChangeHTML["firstPage"],9,firstUrl);
    const endUrl = pageChangeUrlGet(url,pageInfo['numberOfPage'] - 1);
    const endHtml = insertStr(pageChangeHTML["endPage"],9,endUrl);
    element.insertAdjacentHTML('beforeend',firstHtml);

    if(pageInfo['index'] > 0){
        const lastUrl=pageChangeUrlGet(url,pageInfo['index'] - 1);
        const lastHtml = insertStr(pageChangeHTML["lastPage"],9,lastUrl);
        element.insertAdjacentHTML('beforeend',lastHtml)
    }
    if(pageInfo['index'] < pageInfo['numberOfPage'] - 1){
        const nextUrl=pageChangeUrlGet(url,pageInfo['index']+1);
        const nextHtml = insertStr(pageChangeHTML["nextPage"],9,nextUrl);
        element.insertAdjacentHTML('beforeend',nextHtml);
    }
    element.insertAdjacentHTML('beforeend',endHtml);
}
PageNext = function(pageInfo,url){
    if(pageInfo['numberOfPage']-1 > pageInfo['index']){
        const dUrl = pageChangeUrlGet(url,pageInfo['index']+1);
        if(dUrl != -1){
            document.location.href = dUrl;
        }
    }else{
        createMessageBox("没有下一页了",document.body,"null");
    }
}
PageLast = function(pageInfo,url){
    if(pageInfo['index'] > 0){
        const dUrl = pageChangeUrlGet(url,pageInfo['index']-1);
        if(dUrl != -1){
            document.location.href = dUrl;
        }
    }else{
        createMessageBox("没有上一页了",document.body,"null");
    }
}

open_window = function(URL,width,height,margin_top,margin_left){
    window.open(URL,"","toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width="+width+",height="+height+",left="+margin_left+",top="+margin_top);
}
submitAddTag = function(e){
    console.log(e);
    return false;
}

$(function(){
    const cards = document.querySelectorAll(".card");
    const searchbox = document.querySelector("#searchbox input[type='text']");
    const choose_nav = document.querySelectorAll("#nav .choose");
    const control_nav = document.querySelector("#nav .control");
    cards.forEach((e)=>{
        e.addEventListener("mouseenter",animation_up_and_down_hover,false);
    });
    choose_nav.forEach((e)=>{
        nav_set_icon_color(e,"#cbf3f0","#2ec4b6");
    })
    if(searchbox != null){
        searchbox.addEventListener("focus",searchbox_shadow2_change,false);
        searchbox.addEventListener("blur",searchbox_shadow1_change,false);
    }
    if(control_nav != null){
        control_nav.addEventListener("click",nav_control,false);
    }
})

formatSize = function(size,round){
    var sizeStr = size.toString();
    var tmp = (round > 0?round+1:0);
    if(size < 1024){
        return sizeStr.substring(0,sizeStr.indexOf('.')+tmp) + "B";
    }else if(size < 1024 * 1024){
        sizeStr = convertUnit(size, "KB").toString();
        return sizeStr.substring(0,sizeStr.indexOf('.')+tmp) + "KB";
    }else if(size < 1024 * 1024 * 1024){
        sizeStr = convertUnit(size, "MB").toString();
        return sizeStr.substring(0,sizeStr.indexOf('.')+tmp) + "MB";
    }else if(size < 1024 * 1024 * 1024 * 1024){
        sizeStr = convertUnit(size, "GB").toString();
        return sizeStr.substring(0,sizeStr.indexOf('.')+tmp) + "GB";
    }else{
        sizeStr = convertUnit(size, "TB").toString();
        return sizeStr.substring(0,sizeStr.indexOf('.')+tmp) + "TB";
    }
}

convertUnit = function(size,unit){
    switch(unit){
        case "B":
            return size;
        case "KB":
            return size /(1024);
        case "MB":
            return size /(1024 * 1024);
        case "GB":
            return size /(1024 * 1024 * 1024);
        case "TB":
            return size /(1024 * 1024 * 1024 * 1024);
    }
}
function hashVal(string){
	var hash = 0, i, chr;
	if (string.length === 0) return hash;
	for (i = 0; i < string.length; i++) {
	chr = string.charCodeAt(i);
	hash = ((hash << 5) - hash) + chr;
	hash |= 0; // Convert to 32bit integer
	}
    return hash;
}
