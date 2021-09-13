//공통 페이지 로드
function loadHTML(myDivId, url) {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
            if (xmlhttp.status == 200) {
                document.getElementById(myDivId).innerHTML = xmlhttp.responseText;
                var allScripts = document.getElementById(myDivId).getElementsByTagName('script');
                for (var n = 0; n < allScripts.length; n++) {
                    new Function(allScripts[n].innerHTML)();
                }
            } else {

            }
        }
    }
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
function LoadingWithMask(imgUrl) {
    //화면의 높이와 너비를 구합니다.
    var maskHeight = $(document).height();
    var maskWidth = window.document.body.clientWidth; //화면에 출력할 마스크를 설정해줍니다.
    var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg = '';
    loadingImg += "<div id='loadingImg'>";
    loadingImg += " <img src='"+imgUrl+"' style='position: absolute; left: "+(maskWidth/2-100)+"px; top: "+(maskHeight/2-100)+"px ;display: block; margin: 0px auto;'/>";
    loadingImg += "</div>"; //화면에 레이어 추가
    $('body') .append(mask) .append(loadingImg) //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
    $('#mask').css({ 'width' : maskWidth , 'height': maskHeight , 'opacity' : '0.3' }); //마스크 표시
    $('#mask').show(); //로딩중 이미지 표시
    $('#loadingImg ').show();
}
function closeLoadingWithMask() {
    $('#mask, #loadingImg').hide();
    $('#mask, #loadingImg').remove();
}
// loadHTML("header", "/view/v1/system/header");
// loadHTML("footer", "/view/v1/system/footer");

//polyfill
//ie11대응을 위한 polyfill입니다.

//1. foreach
if ('NodeList' in window && !NodeList.prototype.forEach) {
    NodeList.prototype.forEach = function (callback, thisArg) {
        thisArg = thisArg || window;
        for (var i = 0; i < this.length; i++) {
            callback.call(thisArg, this[i], i, this);
        }
    };
}
// 2. from
if (!Array.from) {
    Array.from = function (object) {
        'use strict';
        return [].slice.call(object);
    };
}
// 3. remove
(function (arr) {
    arr.forEach(function (item) {
        if (item.hasOwnProperty('remove')) {
            return;
        }
        Object.defineProperty(item, 'remove', {
            configurable: true,
            enumerable: true,
            writable: true,
            value: function remove() {
                if (this.parentNode !== null)
                    this.parentNode.removeChild(this);
            }
        });
    });
})([Element.prototype, CharacterData.prototype, DocumentType.prototype]);