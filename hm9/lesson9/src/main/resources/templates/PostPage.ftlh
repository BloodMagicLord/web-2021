<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <@c.post post=post/>
    <#if user??>
        <form method="post" action="">
            <input type="hidden" name="action" value="writeComment"/>

            <div class="field">
                <div class="name">
                    <label for="text">Your comment</label>
                </div>

                <div class="value">
                    <textarea autofocus id="text" name="text">${commentForm.text!}</textarea>
                </div>
                <@spring.bind "commentForm.text">
                <#if spring.status.errorMessage??>
                    <div class="error">${spring.status.errorMessage}</div>
                </#if>
<#--                <@c.error "commentForm.text"/>-->
            </div>

            <div class="button-field">
                <input type="submit" value="Comment">
            </div>
        </form>
    </#if>

    <#if post.comments??>
        <ul>
            <#list post.comments as comment>
                <li>
                    <@c.comment comment/>
                </li>
            </#list>
        </ul>
    <#else>
        <a>No comments XD. You can be first.</a>
    </#if>
</@c.page>
