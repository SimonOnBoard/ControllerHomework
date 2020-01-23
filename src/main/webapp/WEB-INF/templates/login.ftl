<#ftl encoding='UTF-8'>
<#include "base.ftl"/>

<#macro content>

    <script type="text/javascript">
        function f() {

            var formData = {
                'username': $('#username').val(),
                'password': $('#password').val()
            };
            console.log(formData);

            $.ajax({
                url: "/login",
                type: "post",
                data: formData,
                success: function (data) {
                    var JSONObject = JSON.parse(data);
                    if (JSONObject.redirect) {
                        window.location.href = JSONObject.redirect;
                    } else {
                        var lst = $('#error_list');
                        lst.html('');
                        for (var i = 0; i < JSONObject.errors.length; i++) {
                            lst.append("<p>" + JSONObject.errors[i] + "</p>")
                        }
                    }
                },
                error: function (err) {
                    alert("error msg")
                }
            });
        }
    </script>

    <script>
        $("#namer input").on("change keyup paste", function () {
            var inputValue = $(this).val();

            if (inputValue) {
                $(".namer-controls").addClass("active");
                $("#namer").addClass("active");
            } else {
                $(".namer-controls").removeClass("active");
                $("#namer").removeClass("active");
            }
        });

        $(document).on("click", ".namer-controls.active span", function () {
            if ($(this).hasClass("active")) {
                $(".namer-controls span").removeClass("active");
                $("#namer-input input").addClass("shake");
                setTimeout(function () {
                    $("#namer-input input").removeClass("shake");
                }, 400);
                $("#namer-input").removeClass();
            } else {
                $(".namer-controls span").removeClass("active");
                $(this).addClass("active");
                var styleClass = $(this).text();

                $("#namer-input input").addClass("shake");
                setTimeout(function () {
                    $("#namer-input input").removeClass("shake");
                }, 400);

                $("#namer-input").removeClass();
                $("#namer-input").addClass(styleClass);
            }
        });

        $(document).ready(function () {
            $("#namer-input input").focus();
        });
    </script>

    <div class="my-container">
        <div class="reg-container">

            <form name="log" method="post">
                <h1>Sign in</h1>
                <small class="form-text text-white" style="margin-bottom: 1.7rem;"><a href="/registration">Sign
                        up</a></small>

                <div id="error_list">

                </div>


                <div id="namer">
                    <div id="namer-input">
                        <input type="text" id="username" name="username" placeholder="Type your login" required>
                    </div>
                </div>

                <div id="namer">
                    <div id="namer-input">
                        <input type="password" id="password" name="password" placeholder="Type your password" required>
                    </div>
                </div>


                <button type="button" class="btn btn-danger btn-lg" id="test_ajax" onclick="f()" value="Sign Up">Sign
                    in
                </button>
            </form>
        </div>
    </div>


</#macro>
<#macro title>
    <title>Login</title>
</#macro>
<@display_page />