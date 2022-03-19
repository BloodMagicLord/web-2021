<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as u>
                <tr>
                    <td class="id">${u.id}</td>
                    <td class="login">${u.login}</td>
                    <td class="creationTime">${u.creationTime}</td>
                    <td class="status">
                            <#if u.disabled>
                                <#assign status = "Enable">
                                <#assign value = 0>
                            <#else>
                                <#assign status = "Disable">
                                <#assign value = 1>
                            </#if>
                            <form method="post" action="">
                                <input type="hidden" name="action" value="setDisabled"/>
                                <label>
                                    <input name="userId" value="${u.id}" hidden/>
                                </label>
                                <label>
                                    <input name="disabled" value="${value}" hidden/>
                                </label>
                                <div class="button-field">
                                    <input type="submit" value="${status}">
                                </div>
                            </form>
                    </td>
                </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
