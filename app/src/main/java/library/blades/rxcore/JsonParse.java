package library.blades.rxcore;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ClassName: JsonParse<p>
 * Author: blades<p>
 * Des: JsonParse<p>
 * CreateTime: 2016/10/14 20:02<p>
 * UpdateTime: 2016/10/14 20:02<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class JsonParse {
    private JSONObject mObject;

    public JsonParse(JSONObject object) {
        mObject = object;
    }

    public <T> T get(String name) {
        try {
            return (T) mObject.get(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return mObject.toString();
    }
}
