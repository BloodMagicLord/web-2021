<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <#if userInfo??>
        <div class="userInfo">
            <ul>
                <li>ID: ${userInfo.id}</li>
                <li>Login: ${userInfo.login}</li>
                <li>Creation time: ${userInfo.creationTime}</li>
                <li>Disabled:
                    <#if userInfo.disabled>
                        Yes :(
                    <#else>
                        No :)
                    </#if>
                </li>
            </ul>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
