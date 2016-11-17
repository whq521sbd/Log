package com.auto.logistics.JavaBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/26.
 */

public  class LoginRequset implements Serializable {


    /**
     * Suc : true
     * Msg :
     * Data : {"Token":"6af2f01500e64d288402a9d587e7c51d","Avatar":"/UImage/BudLic/201611/20161117015701R0Z.PNG","MarketNum":"370101","MarketName":"匡山蔬菜批发市场"}
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
         * Token : 6af2f01500e64d288402a9d587e7c51d
         * Avatar : /UImage/BudLic/201611/20161117015701R0Z.PNG
         * MarketNum : 370101
         * MarketName : 匡山蔬菜批发市场
         */

        private String Token;
        private String Avatar;
        private String MarketNum;
        private String MarketName;

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public String getMarketNum() {
            return MarketNum;
        }

        public void setMarketNum(String MarketNum) {
            this.MarketNum = MarketNum;
        }

        public String getMarketName() {
            return MarketName;
        }

        public void setMarketName(String MarketName) {
            this.MarketName = MarketName;
        }
    }
}
