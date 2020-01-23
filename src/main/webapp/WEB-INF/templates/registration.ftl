<#ftl encoding='UTF-8'>
<#include "base.ftl"/>

<#macro content>
    <div class="my-container">
        <div class="reg-container">


            <form name="reg" action="registration" method="post" enctype="multipart/form-data"><h1>Just a registration form! PUT some information IN)</h1>
                <small class="form-text text-white" style="margin-bottom: 1.7rem;"><a href="/login">Sign in</a></small>
                <#if errors??>
                    <#list errors as g>
                        <p>${g}</p>
                    </#list>
                <#else>
                </#if>

                <div id="namer">
                    <div id="namer-input">
                        <input type="text" id="user_name" name="user_name" placeholder="Type your nick" required>
                    </div>
                </div>

                <div id="namer">
                    <div id="namer-input">
                        <input type="password" id="password" name="password" placeholder="Type your password" required>
                    </div>
                </div>

                <div id="namer">
                    <div id="namer-input">
                        <input type="email" id="email" name="email" placeholder="Type your email" required/>
                    </div>
                </div>

                <div id="namer">
                    <div id="namer-input">
                        <input type="text" id="name" name="name" placeholder="Type your login" required>
                    </div>
                </div>

                <div id="namer">
                    <div id="namer-input">
                        <input type="date" id="date" name="birth"
                               min="1900-01-01" max="2020-01-01" required>
                    </div>
                </div>

                <div id="namer">
                    <div id="namer-input">
                        <input type="file" id="photo" name="photo">
                    </div>
                </div>
                <button type="submit" class="btn btn-danger btn-lg">Sign up</button>
            </form>
        </div>
    </div>

</#macro>
<#macro title>
    <title>Registration</title>
</#macro>
<@display_page />