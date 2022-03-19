<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    Welcome on Codeforces!
    <div class="articles">
        <template>
            <div class="article">
                <div class="title"></div>
                <div class="information">
                    <div class="author"></div>
                    <div class="creationTime"></div>
                </div>
                <div class="body"></div>
                <div class="footer">
                    <div class="articleId"></div>
                </div>
            </div>
        </template>
        <article class="noData">
            No articles
        </article>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Loading...");
            ajax({
                data: {
                    action: "findAll"
                },
                success: function (response) {
                    response["articles"].forEach(function (article) {
                        $noData.hide();
                        const $article = $($(".articles template").prop("content")).clone();
                        $article.find(".title").text(article["title"]);
                        $article.find(".information .author").text("Author: " +
                            response["users"].find(x => x.id === article["userId"]).login);
                        $article.find(".information .creationTime").text(article["creationTime"]);
                        $article.find(".body").text(article["text"]);
                        $article.find(".footer .articleId").text("Article ID: " + article["id"]);
                        $(".articles").append($article);
                    })
                }
            })
        })
    </script>
</@c.page>

