<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle?has_content>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#elseif user_id?has_content && user_id?is_number>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>
    <#if u?has_content>
        <#assign nom = c.findIndex(users, "handle", u.handle)
                leftArrow = "⟵"
                rightArrow = "⟶"/>
        <div class="userInfo">
            <img class="userAvatar" alt="userAvatar" src="/img/user-avatar.png">
            <@c.userlink u/>
            <ul>
                <li>id: ${u.id}</li>
                <li>handle: ${u.handle}</li>
                <li>name: ${u.name}</li>
            </ul>
        </div>
        <#if 0 < nom>
            <@c.activeArrowLink users[nom-1] leftArrow/>
        <#else>
            <@c.arrow leftArrow/>
        </#if>
        <#if nom < users?size-1>
            <@c.activeArrowLink users[nom+1] rightArrow/>
        <#else>
            <@c.arrow rightArrow/>
        </#if>
    <#else>
        No such user
    </#if>

<#--    -->
<#--    <#assign u = c.findBy(users, "handle", handle)!/>-->

<#--    <#if u?has_content>-->
<#--        ${u.handle} / ${u.name} (${u.id})-->
<#--    <#else>-->
<#--        No such user-->
<#--    </#if>-->

</@c.page>
