package com.auto.logistics.JavaBean;

/**
 * Created by Administrator on 2016/10/27.
 */

public class TestBean {
    /**
     * Suc : true
     * Msg :
     * Data : {"Token":"0b154a4738cb49db8a99f4338eb0ef79","MarketNum":"370101","MarketName":"匡山蔬菜批发市场"}
     */

    private boolean Suc;
    private String Msg;
    /**
     * Token : 0b154a4738cb49db8a99f4338eb0ef79
     * MarketNum : 370101
     * MarketName : 匡山蔬菜批发市场
     */

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
        private String Token;
        private String MarketNum;
        private String MarketName;

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
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

    //{"Suc":true,"Msg":"","Data":{"Token":"0b154a4738cb49db8a99f4338eb0ef79","MarketNum":"370101","MarketName":"匡山蔬菜批发市场"}}


}
