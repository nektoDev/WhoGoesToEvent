import org.joda.time.DateTime
import org.joda.time.Years

class BootStrap {

    def init = { servletContext ->
        Date.metaClass.getAge = {
            def yob = new DateTime(delegate).toDateMidnight()
            def now = DateTime.now().toDateMidnight()

            return Years.yearsBetween(yob, now).getYears()
        };
    }
    def destroy = {
    }
}
