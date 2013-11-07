package whogoestoevent


class SearchController {
    def vkApiService;
    def grailsApplication;

    def index() {

    }

    def search() {
        Filter filter = new Filter(params?.filter);
        List<VkUser> vkUserList = grailsApplication.mainContext.getBean('searchService').searchUsers(filter);

        render(template:"layouts/vkUserListTmpl", model: [vkUserList: vkUserList, page: 2]) ;
    }

    def loadPage() {
        Integer p = params.page as Integer;
        Integer startRange = p * 15;
        Integer endRange = startRange+14;

        List<VkUser> result = grailsApplication.mainContext.getBean('searchService').getNextPage();
        render(template:"layouts/vkUserListTmpl", model: [vkUserList: result, page: p+1]) ;
    }
}
