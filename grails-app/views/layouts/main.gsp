<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Кто еще идет на событие? Найди с кем пойти!">
    <meta name="author" content="NektoDev">

    <title><g:layoutTitle default="Grails"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'slider.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'round-about.css')}" type="text/css">
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>
<g:render template="/layouts/header"/>

<div class="container">

    <g:layoutBody/>

    <hr>

    <g:render template="/layouts/footer"/>
</div> <!-- /container -->

<div class="modal fade" id="spinner" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" data-backdrop="static" data-keyboard="false" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Загружаем данные</h4>
            </div>

            <div class="modal-body">
                Пожалуйста подождите...
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<g:javascript>
    if (jQuery('#load-status').val() == 'fail') {
        jQuery('#spinner').modal('show');
    }

</g:javascript>
<g:javascript library="application"/>
<r:layoutResources/>
</body>
</html>
