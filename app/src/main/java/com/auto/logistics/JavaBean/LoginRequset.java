package com.auto.logistics.JavaBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public  class LoginRequset implements Serializable {
    // { Token = "tokenValue",MarketNum="",MarketName="" }


//    "Suc": false,
//            "Msg": "用户名或密码不正确",
//            "Data": {
//
//    }

    private boolean Suc;
    private String Msg;
    private Data Data;

    //{Data":{"Token":"34471ece855d4ed49b756bda24d3c0f3","MarketNum":"370101","MarketName":"匡山蔬菜批发市场"}}
    public class Data {
        private String Token;
        private String MarketNum;
        private String MarketName;

        public String getToken() {
            return Token;
        }

        public void setToken(String token) {
            Token = token;
        }

        public String getMarketNum() {
            return MarketNum;
        }

        public void setMarketNum(String marketNum) {
            MarketNum = marketNum;
        }

        public String getMarketName() {
            return MarketName;
        }

        public void setMarketName(String marketName) {
            MarketName = marketName;
        }
    }


    public Data getData() {
        return Data;
    }

    public void setData(Data data) {
        this.Data = data;
    }

    public boolean isSuc() {
        return Suc;
    }

    public void setSuc(boolean suc) {
        Suc = suc;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }


}
