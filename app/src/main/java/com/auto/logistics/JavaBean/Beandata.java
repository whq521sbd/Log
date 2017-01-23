package com.auto.logistics.JavaBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by toplion on 2017/1/22.
 */
public class Beandata implements Serializable {
    private String title;
    private List<LogTaskBean.DataBean.LogsBean> logs;
    private boolean checked=false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LogTaskBean.DataBean.LogsBean> getLogs() {
        return logs;
    }

    public void setLogs(List<LogTaskBean.DataBean.LogsBean> logs) {
        this.logs = logs;
    }
}
