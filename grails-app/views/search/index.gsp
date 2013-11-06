<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main">
    <title>Тест</title>

</head>

<body>

    <div class="row">
        <div class="col-md-4">
            <div class="bs-sidebar hidden-print affix" id="sidebar" >
                <g:form url="[action: 'search']" >
                    <h2>Параметры</h2>
                    <div class="form-group">
                        <label for="eventLink">ID группы или события ВКонтакте</label>
                        <g:textField name="filter.eventID" id="eventLink" class="form-control" placeholder="Например: insane_tea"/>
                    </div>

                    <g:submitToRemote value="Искать2"
                                      class="btn btn-primary"
                                      url="[action: 'search2']"
                                      update="userlist"
                                      onLoading="jQuery('#spinner').modal('show');"
                                      onComplete="jQuery('#spinner').modal('hide');"
                    />
                    <g:submitToRemote value="Искать"
                                      class="btn btn-primary"
                                      url="[action: 'search']"
                                      update="userlist"
                                      onLoading="jQuery('#spinner').modal('show');"
                                      onComplete="jQuery('#spinner').modal('hide');"
                    />
                </g:form>
            </div>
        </div>

        <div class="col-md-8">

            <h1 class="page-header">About Us <small>It's Nice to Meet You!</small></h1>
            <p>This is a great place to start off with a short and sweet description of your company, organization, or whatever purpose your website is serving. Keep it friendly, engaging, but short enough to where you won't lose your reader!</p>
            <p>If you need a bit more space to describe what is going on, we recommend putting a picture in this section. Use the <code>pull-right</code> class on the image to make it look good!</p>

            <h2 class="page-header">Название события</h2>

            <div class="row" id="userlist">
                <tmpl:layouts/vkUser user="${user}"/>

            </div>
            <div class="row" id="page_${page}">
                <tmpl:layouts/vkUser user="${user}"/>

            </div>

            <g:form>
                <g:hiddenField name="page" id="page" value="${page}"/>
                <g:submitToRemote value="Еще"
                                  class="btn btn-primary"
                                  url="[action: 'getPage']"
                                  update="page_${page}"
                                  onLoading="jQuery('#spinner').modal('show');"
                                  onComplete="jQuery('#page').value(jQuery('#page').value() + 1);jQuery('#spinner').modal('hide');"
                />
            </g:form>
        </div>
    </div>
</body>
</html>
