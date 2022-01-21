<#import "parts/common.ftl" as c>
<@c.page>
    <form method="post" action="/parse">

            <div class="form-group-row">
                <label for="html" class="col-sm-2 col-form-label">Page URL:</label>
                <div class="col-sm-10">
                    <input type="text" id="html" name="html" class="form-control" placeholder="Page URL">
                </div>
            </div>
            <div class="form-group-row">
                <label for="html" class="col-sm-2 col-form-label">Number of words to show:</label>
                <div class="col-sm-10">
                    <input type="text" id="number" name="number" class="form-control" placeholder="Number">
                </div>
            </div>
            <div class="form-group-row">
                <button type="submit" class="btn btn-primary ml-2 mb-3 mt-3">Parse</button>
            </div>

    </form>

    <#if page??>
        <h3>Stats for: ${page}</h3>
    </#if>

    <#if words??>
        <table class="table table-hover" >
            <thead>
            <tr>
                <th scope="col">Word</th>
                <th scope="col">Number</th>
            </tr>
            </thead>
            <tbody>
            <#list words as word>
                <#list word as key, value>
                    <tr>
                        <td>${key}</td>
                        <td>${value}</td>
                    </tr>
                </#list>
            </#list>
            </tbody>
        </table>
    </#if>
</@c.page>