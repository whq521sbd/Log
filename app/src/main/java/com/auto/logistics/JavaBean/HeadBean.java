package com.auto.logistics.JavaBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/18.
 */
public class HeadBean implements Serializable {

    /**
     * Suc : true
     * Msg :
     * Data : {"Avatar":"/UImage/BudLic/201611/2016111811441806F.PNG"}
     */

    private boolean Suc;
    private String Msg;
    private DataBean Data;

    public boolean isSuc() {
        return Suc;
    }

    public void setSuc(boolean Suc) {
        this.Suc = Suc;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Avatar : /UImage/BudLic/201611/2016111811441806F.PNG
         */

        private String Avatar;

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }
    }
}
