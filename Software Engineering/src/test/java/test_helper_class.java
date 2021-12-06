import org.hamcrest.Matcher;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static org.hamcrest.Matchers.hasProperty;

public class test_helper_class {
    // https://stackoverflow.com/questions/36363372/is-there-a-way-to-do-deep-comparison-on-a-nested-property-with-hamcrest/36371366#36371366
    public static <T> Matcher<T> hasGraph(String graphPath, Matcher<T> matcher) {
        List<String> properties = Arrays.asList(graphPath.split("\\."));
        ListIterator<String> iterator =
                properties.listIterator(properties.size());

        Matcher<T> ret = matcher;
        while (iterator.hasPrevious()) {
            ret = hasProperty(iterator.previous(), ret);
        }
        return ret;
    }

    public static boolean isNumeric(String str) {

        return false;
    }


}
