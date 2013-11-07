
<g:each in="${vkUserList}" var="u">
    <tmpl:layouts/vkUser user="${u}"/>
</g:each>

<div id="page_${page}">
    <g:form id="more">
        <g:hiddenField name="page" id="page" value="${page}"/>
        <g:submitToRemote value="Еще"
                          class="btn btn-primary col-md-12"
                          url="[action: 'loadPage']"
                          update="page_${page}"
                          onLoading="jQuery('#spinner').modal('show');"
                          onComplete="jQuery('#more').remove();jQuery('#spinner').modal('hide');"
                          id="morebtn"

        />
    </g:form>
</div>

