<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main">
    <title>Тест</title>

</head>

<body>
<g:set var="relationService" bean="relationService"/>
<g:set var="tokenService" bean="tokenService"/>

<div class="row">
    <div class="col-md-4">
        <div class="bs-sidebar hidden-print affix" id="sidebar">
            <g:if test="${!tokenService?.isToken()}">
            <div class="need-auth" style="z-index: 1044;
    background: none repeat scroll 0% 0% rgba(53, 126, 189, 0.62);
    width: 110%;
    height: 105%;
    position: absolute;
    top: 0;
    left: -5%;
    border-bottom-right-radius: 15px;
    border-bottom-left-radius: 15px;
    border: 1px solid #5C7C98;
    border-top: 1px solid #82AFD6 !important;">


                <div class="text-center " style="width:100%; padding-top: 33%;
               ">
                    <h1 style="color: #f5f5f5; text-shadow: -1px -1px 2px grey; font-weight: bold;">Сначала нужно войти</h1>

                    <g:link controller="authentication"> <g:img uri="/images/vk_logo.png" style="width: 90%;
border-radius: 5px;"/></g:link>

                </div>

            </div>
            </g:if>
            <g:formRemote name="search"
                          url="[action: 'search']"
                          update="userlist"
                          >

                <h2>Параметры</h2>

                <div class="form-group">
                    <label for="eventLink">ID группы или события ВКонтакте</label>
                    <g:textField name="filter.eventID" id="eventLink" class="form-control" required="true"
                                 placeholder="Например: insane_tea"/>
                </div>

                <div class="form-group">
                    <label for="filter.sex">Пол</label> <br/>
                    <g:radio name="filter.sex" value="1" id="female-sex-radio"/>
                    <label for="female-sex-radio">Ж</label>
                    <g:radio name="filter.sex" value="2" id="male-sex-radio"/>
                    <label for="male-sex-radio">М</label>
                    <g:radio name="filter.sex" value="" id="all-sex-radio" checked="true"/>
                    <label for="all-sex-radio">Все</label>
                </div>

                <div class="form-group">
                    <label for="filter.age">Возраст</label> <br/>

                    <b>0</b>
                    <g:textField name="filter.age" type="text" class="slider form-control"
                                 data-slider-min="0" data-slider-max="99"
                                 data-slider-step="1" data-slider-value="[0, 99]"
                                 data-slider-orientation="horizontal" data-slider-selection="before"
                                 data-slider-tooltip="show"/>
                    <b>99</b>

                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="filter.showWithoutAge" value="true"/>
                        Показывать если возраста нет?
                    </label>
                </div>

                <div class="form-group">
                    <label for="relation">Семейное положение</label>
                    <g:select class="form-control" id="relation" name="filter.relation" from="${relationService?.relations}" optionKey="code" optionValue="description"/>
                </div>
                
                <g:submitButton name="find"
                                value="Искать"
                                class="btn btn-primary"/>

            </g:formRemote>
        </div>
    </div>

    <div class="col-md-8">
        <style>
            #progress_123 .ui-progressbar-value {
                background: #357EBD;
            }

            #info_123 .ui-progressbar-value {
                background: #357EBD;
            }
        </style>
        <h1 class="page-header">About Us <small>It's Nice to Meet You!</small></h1>

        <p>This is a great place to start off with a short and sweet description of your company, organization, or whatever purpose your website is serving. Keep it friendly, engaging, but short enough to where you won't lose your reader!</p>

        <p>If you need a bit more space to describe what is going on, we recommend putting a picture in this section. Use the <code>pull-right</code> class on the image to make it look good!
        </p>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <g:jprogress progressId="123" trigger="find"/>
        <h2 class="page-header">Название события</h2>

        <div class="row" id="userlist">
        </div>
    </div>
</div>

</body>
</html>
