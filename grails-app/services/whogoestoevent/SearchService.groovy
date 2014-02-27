package whogoestoevent

import jprogress.ProgressService

class SearchService implements Serializable{
    static scope = 'session'
    public static final int SLEEP_TIME = 350 //VK doesn't allow more than 3 request per second.

    def vkApiService;
    def cityService;
    ProgressService progressService;

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
        println count;
        def i;
        Long start;
        Long timer;
        HashSet<String> unknownCities = new HashSet<>();
        while (vkUserList.size() < count) {
            start = System.currentTimeMillis();
            i = count - vkUserList.size();
            print vkUserList.size()
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

            timer = System.currentTimeMillis() - start
            if (timer < SLEEP_TIME) {
                Thread.sleep(SLEEP_TIME - timer);
            }
            progressService.
            progressService.setProgressBarValue("123", (vkUserList.size()*100)/count);

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
        int i = 0;
        Long start;
        Long timer;
        for (String id in unknownCities) {
            start = System.currentTimeMillis();
            cids.append(id);
            cids.append(",");
            i++;
            print i;
            if (i>=100) {
                cityService.addCities(cids.toString());
                cids = new StringBuilder();
                i=0;
                timer = System.currentTimeMillis() - start
                if (timer < SLEEP_TIME) {
                    Thread.sleep(SLEEP_TIME - timer);
                }
            }
        }

        if (cids.size() > 0) {
            cityService.addCities(cids.toString());
        }

        for (VkUser user : filteredList) {
            if (user.city == null) {
                user.city = cityService.getCityById(user.cityId);
            }
        }
    }
}
