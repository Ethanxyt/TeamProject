package uk.ncl.cs.teamproject.common;


import net.sf.json.JSONObject;
import uk.ncl.cs.teamproject.util.ConditionUtil;

public class JSONResult extends Result<JSONObject> {

    public static JSONResult isOk() {
        return new JSONResult();
    }

    public static JSONResult isFail() {
        return (JSONResult) new JSONResult().status(FAIL);
    }

    public static JSONResult isFail(String e) {
        return (JSONResult) isFail().msg(e);
    }

    public JSONResult put(Object key, Object value) {
        if (ConditionUtil.isNull(data)) {
            data = new JSONObject();
        }
        data.put(key, value);
        return this;
    }
}
