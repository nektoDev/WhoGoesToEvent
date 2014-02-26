package whogoestoevent

class SearchService implements Serializable{
    static scope = 'session'

    def vkApiService;
    def cityService;

    List<VkUser> vkUserList;
    List<VkUser> filteredList;

    Filter filter;

    Integer offset = 0;

    def initUserList(Filter filter) {
        vkUserList = new ArrayList<>();
        filteredList = new ArrayList<>();
        offset = 0;

        def response = vkApiService.groupsGetMembers(filter.eventID, 1);

        Integer count = response.count as Integer;
        def i;
        HashSet<String> unknownCities = new HashSet<>();
        while (vkUserList.size() < count) {
            i = count - vkUserList.size();
            Integer loadingCount = i > 1000 ? 1000 : i;
            List<VkUser> members = vkApiService.getGroupsMembers(filter.eventID, loadingCount, vkUserList.size()) //TODO if null
            for (VkUser member : members) {
                if (member.compareWithFilter(filter)) {
                    filteredList.add(member);

                    member.city = cityService.getCityById(member.cityId)

                    if (member.city == null && member.cityId != null && !"0".equals(member.cityId)) {
                        unknownCities.add(member.cityId);
                    }
                }
            }
            vkUserList.addAll(members);
            Thread.sleep(350);
        }
        if (!unknownCities?.empty) {
            defineUnknownCities(unknownCities);
        }
    }

    public List<VkUser> getNextPage(Integer count = 15) {
        List<VkUser> result = new ArrayList<VkUser>();
        def to = offset + count;
        if (filteredList.size() > offset) {
            to = filteredList.size() < to ? filteredList.size() : to
            result.addAll(filteredList[offset..to-1])
        };
        offset += result.size();
        return result;
    }

    private void defineUnknownCities(HashSet<String> unknownCities) {
        StringBuilder cids = new StringBuilder();
        for (String id : unknownCities) {
            cids.append(id);
            cids.append(",");
        }

        cityService.addCities(cids.toString());

        for (VkUser user : filteredList) {
            if (user.city == null) {
                user.city = cityService.getCityById(user.cityId);
            }
        }
    }
}
