<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="create form-box">
        <div class="header">Create Notice</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="createNotice"/>
                <div class="field">
                    <div class="value">
                        <label for="text"></label>
                        <textarea id="text" name="content" rows="10" cols="40" value="${createForm.content!}"></textarea>
                    </div>
                    <@c.error "createForm.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>
</@c.page>
