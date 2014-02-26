<g:set var="relationService" bean="relationService"/>

<div class="col-lg-4 col-sm-6" id="user-wrap">
    <div class="img-wrap">
        <a href="http://vk.com/id${user?.id}">
            <g:img class="img-circle img-responsive user-photo"
                   uri="${user?.photo200 ?: user?.sex == 1 ? '/images/no_avatar_F_200x200.png' : '/images/no_avatar_M_200x200.png'}"/>
            <div class="cover img-circle img-responsive ${user?.sex == 1 ? 'female' : 'male'}">
                <h3>${user?.age ? user?.age : '??'}</h3>

                <p>${user?.city == null ? "г. ?? ": "г. " + user?.city}</p>
                <p>${relationService.getRelationById(user?.relation).description}</p>
            </div>
        </a>
    </div>

    <h3 class="fio"><g:img uri="${user?.sex == 1 ? '/images/female.png' : '/images/male.png'}"
                           style="height: 20px"/> ${user?.firstName} ${user?.lastName}</h3>

    <p></p>
</div>