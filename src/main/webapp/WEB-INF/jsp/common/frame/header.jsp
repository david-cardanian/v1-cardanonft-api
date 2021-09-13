<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script>
    "use strict";
    function changeLang(lang){
        if(lang == "kr"){
            // $('#contentsTitle').html("프로젝트 정보");
            $('#contentsText').html("동아시아에는 많은 신화, 설화, 동화가 있습니다.<br>"+
                "이 이야기 속에는 많은 신과 요괴들이 등장하고 사람들과 함께하며 새로운 이야기들을 창조하였습니다.<br>"+
                "이 이야기들이 Cardano와 만나 Cardanian과 함께 새로운 동화를 써내려 갑니다.<br>"+
                "K-MONSTERZ를 통해 신, 요정, 요괴들이 당신을 만나 새로운 가치를 만들어 낼 것 입니다.<br>"+
                "당신만을 지키는 신을 가지세요.<br>"+
                "당신만을 위하는 요정을 가지세요.<br>"+
                "당신과 함께 놀고 싶은 요괴가 있습니다.<br>"+
                "K-MONSTERZ의 CNFT는 성장하는 스토리를 가지고 있습니다.<br>"+
                "기대하세요. 이제 새로운 세계가 Cardano로 들어옵니다.");
        }else if(lang == "jp"){
            // $('#contentsTitle').html("プロジェクトについて");
            $('#contentsText').html("東アジアには多くの神話、物語、おとぎ話が伝わってきます。<br>" +
                "この物語では、多くの神々や妖怪が登場し、人々との新しい物語を創造しました。<br>" +
                "この話々とカルダノが出会って<br>" +
                "新しい話を作るカルダニアンと緒に新しい童話を書き下ろします。<br>" +
                "K-MONSTERZを通じて、神々、妖精、モンスターがあなたに出会い、新しい価値を生み出すでしょう。<br>" +
                "あなたのためだけの神を所有してください。<br>" +
                "あなただけを守る妖精を所有してください。<br>" +
                "あなたと一緒に遊びたい妖怪がいます。<br>" +
                "K-MONSTERZのCNFTには成長するストーリーを持っています。<br>" +
                "待しててください。今、新しい世界がカルダノにやってきます。");
        }else{
            $('#contentsTitle').html("About the project");
            $('#contentsText').html("There are many myths, tales and fairy tales in East Asia.<br>" +
                "In this story, many gods and youkai appeared and created new stories with people.<br>" +
                "These stories meet Cardano and work with Cardanian to write a new fairy tale.<br>" +
                "Through K-MONSTERZ, gods, fairies, and monsters will meet you and create new values.<br>" +
                "Have a god who protects only you.<br>" +
                "Have a fairy just for you.<br>" +
                "There are youkai who want to play with you.<br>" +
                "K-MONSTERZ's CNFT has a growing story.<br>" +
                "To expect. Now a new world is coming to Cardano.");
        }
    }
    $(document).ready(function () {
        //메뉴 누르면 서브메뉴 보이기
        $('.hamburger').click(function () {
            $('.submenu').show();
        });
        //X 누르면 메뉴닫기
        $('.close').click(function () {
            $('.submenu').hide();
            return false;
        });
        $('#headerLangMo').change(function() {
            changeLang($('#headerLangMo').val())
        });
        $('#headerLang').change(function(){
            changeLang($('#headerLang').val())
        });
    });
</script>
<header class="headerWrap">
    <a class="logoTop" href="/"></a>
    <div class="headerRight">
        <div class="sns">
            <a href="https://twitter.com/KmonsterCNFT" target="_blank" class="twitter"></a>
            <a href="mailto:cardanocomm@gmail.com" target="_blank" class="gmail"></a>
        </div>
        <div class="nav">
            <a href="/guide" class="guide">
                <div class="navIcon"></div>
                <div class="navText">Guide</div>
            </a>
            <div class="lang">
                <div class="navIcon"></div>
                <div class="navText">
                    <select name="language" id="headerLang">
                        <option value="en">English</option>
                        <option value="kr">한국어</option>
                        <option value="jp">日本語</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="hamburger">
            <div class="submenu">
                <div class="close"></div>
                <div class="moNav">
                    <a href="/guide" class="guide">
                        <div class="navIcon"></div>
                        <div class="navText">Guide</div>
                    </a>
                    <div class="lang">
                        <div class="navIcon"></div>
                        <div class="navText">
                            <select name="language" id="headerLangMo">
                                <option value="en">English</option>
                                <option value="kr">한국어</option>
                                <option value="jp">日本語</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="moSns">
                    <a href="https://twitter.com/KmonsterCNFT" target="_blank" class="twitter"></a>
                    <a href="" target="_blank" class="gmail"></a>
                </div>
            </div>
        </div>
    </div>
</header>



