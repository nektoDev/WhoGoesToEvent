<div class="col-lg-4 col-sm-6">
    <a href="http://vk.com/id${user?.id}">
        <img class="img-circle img-responsive" src="${user?.photo200}"/>
    </a>
    <h3 style="white-space:nowrap;">${user?.firstName} ${user?.lastName} <small>${user?.sex == 1 ? 'Ж' : 'M'}</small></h3>
    <p></p>
</div>