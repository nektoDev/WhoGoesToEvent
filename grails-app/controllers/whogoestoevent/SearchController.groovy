package whogoestoevent


class SearchController {
    def vkApiService;
    def grailsApplication;

    def index() {
        [user: vkApiService.usersGet("3388135"), page: 2];

    }

    def search() {
        Filter filter = new Filter(params?.filter);
        List<VkUser> vkUserList = grailsApplication.mainContext.getBean('searchService').searchUsers(filter);

        render(template:"layouts/vkUser", collection: vkUserList, var: 'user') ;
    }

    def search2() {
        Filter filter = new Filter(params?.filter);
        List<VkUser> vkUserList = grailsApplication.mainContext.getBean('searchService').searchUsers(filter);

        render(template:"layouts/vkUser", collection: vkUserList, var: 'user') ;
    }

    def getPage() {
        Integer p = params.page as Integer;
        Integer startRange = p * 15;
        Integer endRange = startRange+14;

        List<VkUser> result = grailsApplication.mainContext.getBean('searchService').getUsersAt([startRange..endRange]);
        render(template:"layouts/vkUser", collection: result, var: 'user') ;
    }
}
