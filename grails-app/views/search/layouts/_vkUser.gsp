<div class="col-lg-4 col-sm-6 ${user?.sex == 1 ? 'female' : 'male'}">
    <a href="http://vk.com/id${user?.id}">
        <g:img class="img-circle img-responsive user-photo" uri="${user?.photo200 ?: user?.sex == 1 ? '/images/nophoto-female.png' : '/images/nophoto-male.png'}"/>
    </a>
    <h3 class="fio"><g:img uri="${user?.sex == 1 ? '/images/female.png' : '/images/male.png'}" style="height: 20px"/> ${user?.firstName} ${user?.lastName}</h3>
    <p></p>
</div>