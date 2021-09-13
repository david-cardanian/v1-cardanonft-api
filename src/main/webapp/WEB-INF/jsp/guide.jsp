<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/jsp/common/include/head.jspf"%>
</head>

<body>
<!-- 공통컴포넌트 1.header -->
<div id="header" class="header">
    <jsp:include page="/WEB-INF/jsp/common/frame/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="/static/css/guide.css">
</div>
<div class="contentsWrap">
    <div class="contents">
        <div class="blackArea"></div>
        <div class="blackArea"></div>
        <div class="contentsTitle">Guide</div>
        <div class="contentsText">
            1. Click on the character you want to purchase.
        </div>
        <div class="guideImg">
            <img src="/static/img/guide1.png" alt="guide1">
        </div>
        <div class="contentsText">
            2. Click the buy button.
        </div>
        <div class="guideImg">
            <img src="/static/img/guide2.png" alt="guide2">
        </div>
        <div class="contentsText">
            3. Copy both the Amount and the Wallet Address.<br> Send the Amount to the Wallet Address from your Yoroi or Daedalus wallet.
        </div>
        <div class="guideImg">
            <img src="/static/img/guide3.png" alt="guide3">
        </div>
        <div class="contentsText">
            4. Wait for your transaction to process.
        </div>
        <div class="contentsText">
            5. If the purchase was successful, NFT will be sent to the wallet address that payment was sent from.
        </div>
        <div class="contentsText">
            6. If the purchase fails or the amount does not match, The amount transferred will be automatically refunded excluding fees.
        </div>
        <div class="contentsText">
            <span>* It is based on the order in which Ada is transferred to the server wallet, not the order in which the purchase button was pressed.</span>
        </div>
    </div>
        <div class="blackArea"></div>
    </div>
</div>
<!-- 공통컴포넌트 2.footer -->
<div id="footer" class="footer"> <jsp:include page="/WEB-INF/jsp/common/frame/footer.jsp"/></div>
</body>
</html>
