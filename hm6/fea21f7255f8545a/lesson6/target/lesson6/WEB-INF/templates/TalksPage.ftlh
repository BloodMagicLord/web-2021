<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="currUser" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="message-box">
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="send"/>
                <div class="select-user-field">
                    <select id="targetUserId" name="targetUserId">
                        <#list users as user>
                            <#if currUser.login != user.login>
                            <option value="${user.id!}">${user.login!}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <div class="message-field">
                    <div class="value">
                        <textarea id="text" name="text" rows="10" cols="50"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
        <#if error??>
            <div class="error">
                ${error!}
            </div>
        </#if>
    </div>
    <div class="datatable">
        <div class="caption">Talk</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Text</th>
                <th>Time</th>
            </tr>
            </thead>
            <tbody>
            <#list talks as talk>
            <tr>
                <td>${talk.id}</td>
                <#assign sourceUserLogin = c.findBy(users, talk.sourceUserId)>
                <#if sourceUserLogin == currUser.login>
                    <td>You</td>
                <#else>
                    <td>${sourceUserLogin}</td>
                </#if>
                <#assign targetUserLogin = c.findBy(users, talk.targetUserId)>
                <#if targetUserLogin == currUser.login>
                    <td>You</td>
                <#else>
                    <td>${targetUserLogin}</td>
                </#if>
                <td>${talk.text}</td>
                <td>${talk.creationTime}</td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
