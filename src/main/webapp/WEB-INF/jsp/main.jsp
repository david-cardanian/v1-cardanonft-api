<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/jsp/common/include/head.jspf"%>
    <script type="text/javascript" src="/static/js/qrcode.min.js"></script>
</head>
<script>
    "use strict";
    let globalNftId = 0;
    $(document).ready(function () {
        function counter(){
            var dday = new Date("Sep 6,2021,21:00:00").getTime(); //디데이
            setInterval(function(){
                var now = new Date(); //현재 날짜 가져오기
                var distance = dday - now;
                var d = Math.floor(distance / (1000 * 60 * 60 * 24));
                var h = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var m = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var s = Math.floor((distance % (1000 * 60)) / 1000);
                if(s < 10){
                    s = '0'+s;
                }
                $('.countDown').html(d+'Days '+h+':'+m+':'+s)
            }, 1000);
        }
        counter();
        //썸네일 누르면 팝업보이기
        // $('.collection').click(function () {
        //     $('.popup1').show();
        //     $('.popupWrap').show();
        //     return false;
        // });
        //buy 버튼 누르면 두번쨰 팝업뜨기
        $('.btnBuy').click(function () {
            if($('#popupBuyNum').val() > $('#popupRemain').html()){
                alert("Sold Out!!");
                return false;
            }
            $('.popup1').hide();
            $('.popup2').show();
            makeCode();
            $('#payAmt').val(($('#popupPrice').html().replace(" ADA","") * $('#popupBuyNum').val()));
            return false;
        });
        //X 누르면 팝업닫기
        $('.popupClose').click(function () {
            $('.popup1').hide();
            $('.popup2').hide();
            $('.popupWrap').hide();
            globalNftId = 0;
            return false;
        });
        //배경누르면 팝업닫기
        $('.popupBg').click(function () {
            $('.popup1').hide();
            $('.popup2').hide();
            $('.popupWrap').hide();
            globalNftId = 0;
            return false;
        });
        //구매수량 인풋버튼 제이쿼리
        $('<div class="quantity-nav"><div class="quantity-button quantity-up">+</div><div class="quantity-button quantity-down">-</div></div>').insertAfter('.quantity input');
        // $('.quantity').each(function() {
        //     var spinner = $(this),
        //         input = spinner.find('input[type="number"]'),
        //         btnUp = spinner.find('.quantity-up'),
        //         btnDown = spinner.find('.quantity-down'),
        //         min = input.attr('min'),
        //         max = input.attr('max');
        //
        //     btnUp.click(function() {
        //         var oldValue = parseFloat(input.val());
        //         if (oldValue >= max) {
        //             var newVal = oldValue;
        //         } else {
        //             var newVal = oldValue + 1;
        //         }
        //         spinner.find("input").val(newVal);
        //         spinner.find("input").trigger("change");
        //     });
        //
        //     btnDown.click(function() {
        //         var oldValue = parseFloat(input.val());
        //         if (oldValue <= min) {
        //             var newVal = oldValue;
        //         } else {
        //             var newVal = oldValue - 1;
        //         }
        //         spinner.find("input").val(newVal);
        //         spinner.find("input").trigger("change");
        //     });
        //
        // });
        //qr코드 생성
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            //가로, 세로 높이 조절
            width : 150,
            height : 150 });
        //input:text에 들어있는 value를 qr코드로 바꿔주는 function
        function makeCode (addr) {
            qrcode.clear();
            qrcode.makeCode($('#payAddr').val());
            setTimeout(function() {
                $('#qrcode > img').css("display","");
            }, 500);
        }
        search();
    });

    function copyText (div) {
        var input = document.getElementById(div);
        try {
            input.select();
            // returnValue: A Boolean that is false if the command is not supported or enabled.
            var returnValue = document.execCommand('copy');
            console.debug(returnValue);
            if (!returnValue) {
                throw new Error('copied nothing');
            }else{
                alert('Copied.');
            }

        } catch (e) {
        }
    }
    let intervalVal;
    function collectClick(nftId){
        const json = {
            nftId : nftId
        };

        $.ajax({
            url : "/collection/"+nftId
            , type : "POST"
            , dataType:'json'
            , contentType:"application/json;charset=UTF-8"
            , data : JSON.stringify(json)
            , success:function(data) {
                if(data.response.targetQuantity - data.response.mintCount <= 0){
                    $('#popupSoldOut').css("display","block");
                    $('#soldOutDiv'+nftId).css("display","block");
                    globalNftId=0;
                }else{
                    $('#popupSoldOut').css("display","");
                    $('#soldOutDiv'+nftId).css("display","");
                    globalNftId= nftId;
                }
                $('#popupTitle').html(data.response.nftNameKor);
                $('#popupRemain').html(data.response.targetQuantity - data.response.mintCount);
                $('#popupName').html(data.response.nftNameKor);
                $('#popupAge').html(data.response.age);
                $('#payAddr').val(data.response.address);
                $('#popupDescription').html(data.response.description);
                $('#popupPrice').html((data.response.nftPrice / 1000000) + " ADA");
                $('#popupImg').attr("src",data.response.imgUrl);
                $('#popupImg').attr("alt",data.response.nftNameKor);
                $('#popupImg').attr("name",data.response.nftNameKor);
                $('.popup1').show();
                $('.popupWrap').show();
            }
            , error:function(request,status,error) {
            }
        });
        clearInterval(intervalVal);
        intervalVal = setInterval(checkSoldOut, 10000);
        return false;
    }
    function checkSoldOut(){
        if(globalNftId == 0) return false;
        const json = {
            nftId : globalNftId
        };
        $.ajax({
            url : "/collection/"+globalNftId
            , type : "POST"
            , dataType:'json'
            , contentType:"application/json;charset=UTF-8"
            , data : JSON.stringify(json)
            , success:function(data) {
                $('#popupTitle').html(data.response.nftNameKor);
                if(data.response.targetQuantity - data.response.mintCount <= 0){
                    $('#popupSoldOut').css("display","block");
                    $('#soldOutDiv'+globalNftId).css("display","block");
                    if($('.popup2').css("display") == "block"){
                        alert("Sorry, Sold out!\nIf you have sent Ada,\nIt will mint or automatically refunded to your wallet.");
                        $('.popup1').hide();
                        $('.popup2').hide();
                        $('.popupWrap').hide();
                    }
                    globalNftId=0;
                }else{
                    $('#popupSoldOut').css("display","");
                    $('#soldOutDiv'+globalNftId).css("display","");
                }
                $('#popupRemain').html(data.response.targetQuantity - data.response.mintCount);
                $('#popupName').html(data.response.nftNameKor);
                $('#popupAge').html(data.response.age);
                $('#payAddr').val(data.response.address);
                $('#popupDescription').html(data.response.description);
                $('#popupPrice').html((data.response.nftPrice / 1000000) + " ADA");
            }
            , error:function(request,status,error) {
            }
        });
    }
    // 검색
    function search(){
        const json = {
            listSize : 30
        };

        $.ajax({
            url : "/collection/list"
            , type : "POST"
            , dataType:'json'
            , contentType:"application/json;charset=UTF-8"
            , data : JSON.stringify(json)
            , success:function(data) {
                let soldOut = true;
                $.each(data.response, function(k, v) {
                    let newDiv = "<div class=\"collection\" onclick='collectClick("+v.nftId+");'>\n" +
                        "                <div class=\"collectionImg\">\n"
                    if(v.targetQuantity <= v.mintCount){
                        newDiv = newDiv + "                   <div class=\"soldOut\" style=\"display: block\" id=\"soldOutDiv"+v.nftId+"\"></div>\n";
                    }else{
                        newDiv = newDiv + "                   <div class=\"soldOut\" style=\"display: none\" id=\"soldOutDiv"+v.nftId+"\"></div>\n";
                    }

                    newDiv = newDiv + "                    <div class=\"imgArea\"><img src=\""+v.imgUrl+"\" alt=\""+v.nftName+"\" name=\""+v.nftName+"\"></div>\n" +
                        "                </div>\n" +
                        "                <div class=\"collectionText\">"+v.nftNameKor+"</div>\n" +
                        "            </div>";
                    $('#collectionList').append(newDiv);
                    if(v.targetQuantity - v.mintCount <= 0){
                        soldOut = true;
                    }else {
                        soldOut = false;
                    }
                });
                if(soldOut){
                    // 메인 soldOut image로 교체
                    $('.mainTitle').css("display","none");
                    $('.mainTitle2').css("display","block");
                }else{
                    $('.mainTitle2').css("display","none");
                }
                if (data.response.length < 10) {
                    var step;
                    for (step = 0; step < 10-data.response.length; step++) {
                        var newDiv = "<div class=\"collection\">\n" +
                            "                <div class=\"collectionImg\">\n" +
                            "                    <div class=\"imgArea\"></div>\n" +
                            "                </div>\n" +
                            "                <div class=\"collectionText\">???</div>\n" +
                            "            </div>";
                        $('#collectionList').append(newDiv);
                    }
                }
            }
            , error:function(request,status,error) {
            }
        });
        return false;
    }
</script>
<body>
<!-- 공통컴포넌트 1.header -->
<div id="header" class="header">
    <jsp:include page="/WEB-INF/jsp/common/frame/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="/static/css/main.css">
</div>
<div class="contentsWrap">
    <div class="mainImg">
        <div class="characterWrap">
            <div class="mainTitle">
                COMING SOON<br>
                <span>September 6th 12PM (UTC)</span>
                <p class="countDown"></p>
            </div>
            <div class="mainTitle2">
                Sold out<br>
                 <span>Thank you so much.</span>
            </div>
            <div class="character"></div>
        </div>
        <div class="mainBottom"></div>
    </div>
    <div class="contents">
        <div class="contentsTitle" id="contentsTitle">About the project</div>
        <div class="contentsText" id="contentsText">There are many myths, tales and fairy tales in East Asia.<br>
            In this story, many gods and youkai appeared and created new stories with people.<br>
            These stories meet Cardano and work with Cardanian to write a new fairy tale.<br>
            Through K-MONSTERZ, gods, fairies, and monsters will meet you and create new values.<br>
            Have a god who protects only you.<br>
            Have a fairy just for you.<br>
            There are youkai who want to play with you.<br>
            K-MONSTERZ's CNFT has a growing story.<br>
            To expect. Now a new world is coming to Cardano.
        </div>
        <div class="blackArea"></div>
        <div class="contentsTitle">Collection</div>
        <div class="contentsBox" id="collectionList">


        </div>
        <div class="popupWrap">
            <div class="popup1">
                <button class="popupClose"></button>
                <div class="popupLeft">
                    <div class="soldOut" style="display: block" id="popupSoldOut">
                        <div>Sold<br>Out</div>
                    </div>
                    <div ></div>
                    <div class="popupImg">
                        <img src="/static/img/gumi(N).png" alt="gumi" name="gumi" id="popupImg">
                    </div>
                </div>
                <div class="popupRight">
                    <div class="popupTitle" id="popupTitle"></div>
                    <div class="popupText">
                        <%--                        <div class="row">--%>
                        <%--                            <div class="popupSubtitle">이름</div>--%>
                        <%--                            <span id="popupName"></span>--%>
                        <%--                        </div>--%>
                        <div class="row">
                            <div class="popupSubtitle">Age</div>
                            <span id="popupAge"></span>
                        </div>
                        <div class="row">
                            <div class="popupSubtitle">Story</div>
                        </div>
                        <div class="row">
                            <span id="popupDescription"></span>
                        </div>
                        <div class="row">
                            <br>
                        </div>
                        <div class="row">
                            <div class="popupSubtitle">Price</div>
                            <span class="blue"  id="popupPrice"></span>
                        </div>
                        <div class="row">
                            <div class="popupSubtitle">Remain</div>
                            <span class="blue"  id="popupRemain"></span>
                        </div>
                        <div class="buyNumber">
                            <div class="popupSubtitle">Quantity</div>
                            <div class="quantity">
                                <input readonly type="number" min="1" max="9" step="1" value="1"  id="popupBuyNum">
                            </div>
                            <button class="btnBuy">BUY</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="popup2">
                <button class="popupClose"></button>
                <div class="popupTitle">Pending payment confirmation</div><br>
                <div class="popupText">Your order has been approved. <br>
                    Send Ada using the information below.</div>
                <div class="popupCode" id="qrcode" style="display: inline-block;">
                </div>
                <div class="popupText">
                    <div class="popupSubtitle">Address</div>
                    <div class="popupBox">
                        <input type="text" id="payAddr" readonly>
                        <button class="btnCopy" onclick="copyText('payAddr');"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M22 6v16h-16v-16h16zm2-2h-20v20h20v-20zm-24 17v-21h21v2h-19v19h-2z"></path></svg></button>
                    </div>
                    <div class="popupSubtitle">Amount</div>
                    <div class="popupBox">
                        <input type="text" id="payAmt" readonly> ADA
                        <button class="btnCopy" onclick="copyText('payAmt');"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M22 6v16h-16v-16h16zm2-2h-20v20h20v-20zm-24 17v-21h21v2h-19v19h-2z"></path></svg></button>
                    </div>
                    <div class="popupSubtitle">Important</div>
                    <div class="popupBox red" style="text-align: left">* You must use a Cardano Shelley-Era wallet such as <a href="https://daedaluswallet.io/" target="_blank">Daedalus</a>,<a href="https://yoroi-wallet.com/" target="_blank">Yoroi</a><br>
                        * It might take a minute your addressTransaction to be reflected after payment.<br>
                        * It is based on the order in which Ada is transferred to the server wallet, <br>
                        &nbsp;&nbsp;&nbsp;&nbsp; not the order in which the purchase button was pressed.<br>
                        * If the purchase fails or the amount does not match, <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;The amount transferred will be automatically refunded excluding fees.<br>
                    </div>
                </div>

            </div>
            <div class="popupBg"></div>
        </div>
    </div>
    <div class="blackArea"></div><div class="blackArea"></div>
</div>
<!-- 공통컴포넌트 2.footer -->
<div id="footer" class="footer"> <jsp:include page="/WEB-INF/jsp/common/frame/footer.jsp"/></div>
</body>
</html>
