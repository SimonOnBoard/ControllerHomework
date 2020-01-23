<#ftl encoding='UTF-8'>
<#include "base.ftl"/>

<#macro content>

    <div class="feed-container">
        <div class="feed">
            <h1>Products</h1>
            <form name="post_form" id="post_form" method="post" enctype="multipart/form-data">
                <div id="namer">
                    <div id="namer-input">
                        <input type="text" name="name" placeholder="Type your product name" required/>
                    </div>
                </div>

                <div id="namer">
                    <div id="namer-input">
                        <input type="number" step="0.01" name="cost" placeholder="Type your price" required/>
                    </div>
                </div>
                <div id="namer">
                    <div id="namer-input">
                        <input type="number" name="count" placeholder="Type your count" required/>
                    </div>
                </div>
                <div id="namer">
                    <div id="namer-input">
                        <input type="file" name="photo"/>
                    </div>
                </div>
                <button type="button" class="btn btn-danger btn-lg" id="test_ajax" onclick="f()" value="Добавить товар">
                    Add to list
                </button>
            </form>

            <div id="res" class="row">
                <#list products as post>
                    <div class="col-sm-6">
                        <div class="post" style="width:95%; margin-right:5%">
                            <p>
                                <img src="${post.getPhotoPath()}" height="400"> &nbsp; <span
                                        class="big">${post.getName()}</span>
                            </p>

                            <p class="post-text">${post.getPrice()} Рублей</p>
                            <p>${post.getCount()}</p>
                            <p>ended: ${post.getEnded()?string('yes', 'no')}</p>
                        </div>
                    </div>
                </#list>
            </div>

        </div>
    </div>


    <script type="application/javascript">
        function f() {
            // Create an FormData object
            var form = $("#post_form")[0];
            var data = new FormData(form);

            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/products",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (msg) {
                    var msg = JSON.parse(msg);
                    var div_r = document.createElement("div");
                    div_r.setAttribute("id", "div_" + msg.id);
                    div_r.setAttribute("class", "col-sm-6");
                    var div_r_1 = document.createElement("div");
                    div_r_1.setAttribute("class", "post");
                    div_r_1.setAttribute("id", "product_" + msg.id);
                    div_r_1.setAttribute("style", "width:95%; margin-right:5%;");
                    $("#res").append(div_r);
                    $("#div_" + msg.id).append(div_r_1);
                    $("#product_" + msg.id).append("<p><img height=\"400\" width='450' src=\"" + msg.photoPath + "\"> &nbsp; <span class=\"big\">" + msg.name + "</span></p>");
                    $("#product_" + msg.id).append("<p class=\"post-text\">" + msg.price + "рублей" + "</p>");
                    $("#product_" + msg.id).append("<p class=\"post-text\">" + msg.count + "</p>");
                }
            });
        };
    </script>




</#macro>
<#macro title>
    <title>Search</title>
</#macro>
<@display_page />