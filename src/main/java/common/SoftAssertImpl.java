package common;

import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.json.JSONArray;
import org.json.JSONObject;
import uk.co.datumedge.hamcrest.json.SameJSONAs;

public class SoftAssertImpl {

    private SoftAssertions softAssert;

    public SoftAssertImpl() {
        this.softAssert = new SoftAssertions();
    }

    public void assertThat(String message, String actual, String expected) {
        softAssert.assertThat(actual).as(message).isEqualTo(expected);
    }

    public void assertThat(String message, int actual, int expected) {
        softAssert.assertThat(actual).as(message).isEqualTo(expected);
    }

    public void assertThat(String message, boolean actual, boolean expected) {
        softAssert.assertThat(actual).as(message).isEqualTo(expected);
    }

    public void assertThat(String message, Double actual, Double expected) {
        softAssert.assertThat(actual).as(message).isEqualTo(expected);
    }

    public void assertThat(String message, JSONObject actual, JSONObject expected) {
        Description desc = new StringDescription();
        SameJSONAs<JSONObject> matcher =
                SameJSONAs.sameJSONObjectAs(expected).allowingAnyArrayOrdering();
        matcher.describeMismatch(actual, desc);

        if (!matcher.matches(actual)) {
            softAssert.fail(message + "\r\n" + desc.toString());
        }
    }

    public void assertThat(String message, JSONArray actual, JSONArray expected) {
        Description desc = new StringDescription();
        SameJSONAs<JSONArray> matcher = SameJSONAs.sameJSONArrayAs(expected).allowingAnyArrayOrdering();
        matcher.describeMismatch(actual, desc);

        if (!matcher.matches(actual)) {
            softAssert.fail(message + "\r\n" + desc.toString());
        }
    }

    public void assertAll() {
        softAssert.assertAll();
    }

    public SoftAssertions getSoftAssert() {
        return softAssert;
    }

}
