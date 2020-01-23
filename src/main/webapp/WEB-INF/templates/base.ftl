<#ftl encoding='UTF-8'>
<#macro content></#macro>
<#macro title></#macro>

<#macro display_page>
    <html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <meta charset="utf-8">
        <@title />

        <script>
            $(function () {
                var $height = $('#menu').height();
                var $size = $('#menu_item1').height();
                var $margin = "" + ($height - 5 * $size) / 6 - 10 + "";
                $("#menu_item1").css("margin-top", $margin);
                $("#menu_item2").css("margin-top", $margin);
                $("#menu_item3").css("margin-top", $margin);
                $("#menu_item4").css("margin-top", $margin);
                $("#menu_item5").css("margin-top", $margin);
            });
        </script>


    </head>

    <body>
    <div class="menu" id="menu">
        <a href="/main">
            <div class="menu-item" id="menu_item1" title="Main page"><p><i class="fa fa-home" style="font-size:2.8rem;"
                                                                           aria-hidden="true"></i></p></div>
        </a>
        <a href="/mainSearch">
            <div class="menu-item" id="menu_item2" title="Feed"><p><i class="fa fa-newspaper-o"
                                                                      style="font-size:2.8rem;"
                                                                      aria-hidden="true"></i></p></div>
        </a>
        <a href="/posts">
            <div class="menu-item" id="menu_item3" title="My posts"><p><i class="fa fa-clone" style="font-size:2.8rem;"
                                                                          aria-hidden="true"></i></p></div>
        </a>
        <a href="/comment">
            <div class="menu-item" id="menu_item4" title="My comments"><p><i class="fa fa-commenting-o"
                                                                             style="font-size:2.8rem;"
                                                                             aria-hidden="true"></i></p></div>
        </a>
        <a href="/home">
            <div class="menu-item" id="menu_item5" title="Log out"><p><i class="fa fa-user" style="font-size:2.8rem;" aria-hidden="true"></i></p></div>
        </a>
    </div>
    <@content />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    </body>
    </html>
</#macro>