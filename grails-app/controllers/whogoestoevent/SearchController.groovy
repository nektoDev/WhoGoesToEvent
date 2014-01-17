package whogoestoevent


class SearchController {
    def grailsApplication;

    def index() {

    }

    def search() {
        Filter filter = new Filter(params?.filter);
        grailsApplication.mainContext.getBean('searchService').initUserList(filter)
        List<VkUser> vkUserList = grailsApplication.mainContext.getBean('searchService').getNextPage();

        render(template:"layouts/vkUserListTmpl", model: [vkUserList: vkUserList, page: 2]) ;
    }

 def loadPage() {
        Integer p = params.page as Integer;

        List<VkUser> result = grailsApplication.mainContext.getBean('searchService').getNextPage();
        render(template:"layouts/vkUserListTmpl", model: [vkUserList: result, page: p+1]) ;
    }
}
