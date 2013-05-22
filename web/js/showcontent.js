function IncludeJavaScript(jsFile) {
    document.write('<script type="text/javascript" src="' + jsFile
        + '"></script>');
}
function IncludeCSS(cssFile) {
    document.write('<link rel="stylesheet" href="' + cssFile
        + '" type="text/css" media="all">');
}
IncludeJavaScript('js/jquery-1.8.3.min.js');
IncludeJavaScript('js/jquery.mobile-1.3.0.js');
IncludeJavaScript('js/jquery.html5-placeholder.js');

IncludeCSS('css/jquery.mobile-1.3.0.css');
//IncludeCSS('css/jquery-1.3.0-theme-mod.css');
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/style.css');
IncludeCSS('css/showcontent.css');

window.onload = function(){
    getUrlVars();
    sel_content();
}

function sel_content(){
    var content = getUrlVars()["content"];
    var id = getUrlVars()["id"];
    if(content == "news"){
        shownews(id);
    }else if(content == "event"){
        showevent(id);
    }else if(content == "knowledge"){
        showknowledge(id);
    }
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=]*)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function shownews(id){

    $.ajax({
        url : 'content',
        data : {
            'content' : 'news',
            'option'  : 'some',
            'id_new' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            $('.headcontent').css('background-image', 'url("/cs.dusit.ac.th/images/headnews.png")');
            var title = data.title.substr(0,68);
            var date = data.startdate.substr(6, 2)+"/"+data.startdate.substr(4, 2)+"/"+data.startdate.substr(2, 2);
            $('.showcontent').append('<div class="date">วันที่ : '+date+'</div><div class="title">'+title+'dddddddddddddddd dddddddddddddddddddddd ddddddddddddddddd</div><div class="detail">'+data.detail+'<embed width="100%" height="100%" name="plugin" src="http://www.dusit.ac.th/sdu/activities/news/2013-01-10/evt_02126.pdf" type="application/pdf"></div>');   
        }
    });
}

function showevent(id){    
    $.ajax({
        url : 'content',
        data : {
            'content' : 'event',
            'option'  : 'some',
            'id_eve' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            $('.headcontent').css('background-image', 'url("/cs.dusit.ac.th/images/headevent.png")');
            var title = data.title.substr(0,68);
            var date = data.startdate.substr(6, 2)+"/"+data.startdate.substr(4, 2)+"/"+data.startdate.substr(2, 2);
            $('.showcontent').append('<div class="date">วันที่ : '+date+'</div><div class="title">'+title+'</div><div class="detail">'+data.detail+'</div>'); 
            
        }
    });
}

function showknowledge(id){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'knowledge',
            'option'  : 'some',
            'id_kno' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            $('.headcontent').css('background-image', 'url("/cs.dusit.ac.th/images/headknowledge.png")');
            var date = data.startdate.substr(6, 2)+"/"+data.startdate.substr(4, 2)+"/"+data.startdate.substr(2, 2);
            $('.showcontent').append('<div class="date">วันที่ : '+date+'</div><div class="title">'+data.title+'</div><div class="detail">'+data.detail+'</div>'); 
            
        }
    });
}