package com.auto.logistics.JavaBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/31.
 */

public class CarSata implements Serializable {

    /**
     * Suc : true
     * Msg :
     * Data : 370101001
     */

    private boolean Suc;
    private String Msg;
    private String Data;

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

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
