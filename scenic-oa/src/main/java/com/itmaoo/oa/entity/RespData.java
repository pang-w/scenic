package com.itmaoo.oa.entity;

import java.util.HashMap;
import java.util.Map;

public class RespData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private ErrorInfo errorInfo;
    private ActionInfo actionInfo;
    private Map<String, Object> respInfo;

    public RespData() {
        respInfo = new HashMap<String, Object>();
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ActionInfo getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    public Map<String, Object> getRespInfo() {
        return respInfo;
    }

    public void setRespInfo(Map<String, Object> respInfo) {
        this.respInfo = respInfo;
    }

    public void addObject(String key, Object value) {
        respInfo.put(key, value);
    }

    public void removeObject(String key) {
        respInfo.remove(key);
    }
}
