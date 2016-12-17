package com.auto.logistics.JavaBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/14.
 */
public class TaskInfo implements Serializable {


    /**
     * Suc : true
     * Msg : 操作已成功
     * Data : {"LogTask_TaskNum":"W20161214100114826880","LogTask_CarNum":"370101001","LogTask_Serial":"S201612140930143432T4","LogTask_TransNum":"20161214093014350J4Z","LogTask_Market":"370101","LogTask_SellerID":"370101000103","LogTask_Booth":"匡山D区004","LogTask_BuyerID":"370101000010","LogTask_Area":"370102","LogTask_Street":"370102002","LogTask_GoodsId":34,"LogTask_GoodsTitle":"山东扇贝丁","LogTask_Weight":21,"LogTask_RecPerson":"济南大学舜耕校区采购","LogTask_RecTel":"15264118740","LogTask_RecAddr":"济南市历下区舜耕路12号","LogTask_DispTime":"2016-12-14 10:01:02","LogTask_DispUser":"370101000001","LogTask_AccTime":"1753-01-01 12:00:00","LogTask_AccUser":"","LogTask_PackTime":"1753-01-01 12:00:00","LogTask_PackUser":"","LogTask_PackImg":"","LogTask_DepTime":"1753-01-01 12:00:00","LogTask_DepUser":"","LogTask_DepImg":"","LogTask_SendTime":"1753-01-01 12:00:00","LogTask_SendUser":"","LogTask_SendImg":"","LogTask_DeliTime":"1753-01-01 12:00:00","LogTask_DeliUser":"","LogTask_DeliImg":"","LogTask_State":"2"}
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

    public static class DataBean implements Serializable {
        /**
         * LogTask_TaskNum : W20161214100114826880
         * LogTask_CarNum : 370101001
         * LogTask_Serial : S201612140930143432T4
         * LogTask_TransNum : 20161214093014350J4Z
         * LogTask_Market : 370101
         * LogTask_SellerID : 370101000103
         * LogTask_Booth : 匡山D区004
         * LogTask_BuyerID : 370101000010
         * LogTask_Area : 370102
         * LogTask_Street : 370102002
         * LogTask_GoodsId : 34
         * LogTask_GoodsTitle : 山东扇贝丁
         * LogTask_Weight : 21
         * LogTask_RecPerson : 济南大学舜耕校区采购
         * LogTask_RecTel : 15264118740
         * LogTask_RecAddr : 济南市历下区舜耕路12号
         * LogTask_DispTime : 2016-12-14 10:01:02
         * LogTask_DispUser : 370101000001
         * LogTask_AccTime : 1753-01-01 12:00:00
         * LogTask_AccUser :
         * LogTask_PackTime : 1753-01-01 12:00:00
         * LogTask_PackUser :
         * LogTask_PackImg :
         * LogTask_DepTime : 1753-01-01 12:00:00
         * LogTask_DepUser :
         * LogTask_DepImg :
         * LogTask_SendTime : 1753-01-01 12:00:00
         * LogTask_SendUser :
         * LogTask_SendImg :
         * LogTask_DeliTime : 1753-01-01 12:00:00
         * LogTask_DeliUser :
         * LogTask_DeliImg :
         * LogTask_State : 2
         */

        private String LogTask_TaskNum;
        private String LogTask_CarNum;
        private String LogTask_Serial;
        private String LogTask_TransNum;
        private String LogTask_Market;
        private String LogTask_SellerID;
        private String LogTask_Booth;
        private String LogTask_BuyerID;
        private String LogTask_Area;
        private String LogTask_Street;
        private int LogTask_GoodsId;
        private String LogTask_GoodsTitle;
        private int LogTask_Weight;
        private String LogTask_RecPerson;
        private String LogTask_RecTel;
        private String LogTask_RecAddr;
        private String LogTask_DispTime;
        private String LogTask_DispUser;
        private String LogTask_AccTime;
        private String LogTask_AccUser;
        private String LogTask_PackTime;
        private String LogTask_PackUser;
        private String LogTask_PackImg;
        private String LogTask_DepTime;
        private String LogTask_DepUser;
        private String LogTask_DepImg;
        private String LogTask_SendTime;
        private String LogTask_SendUser;
        private String LogTask_SendImg;
        private String LogTask_DeliTime;
        private String LogTask_DeliUser;
        private String LogTask_DeliImg;
        private String LogTask_State;

        public String getLogTask_TaskNum() {
            return LogTask_TaskNum;
        }

        public void setLogTask_TaskNum(String LogTask_TaskNum) {
            this.LogTask_TaskNum = LogTask_TaskNum;
        }

        public String getLogTask_CarNum() {
            return LogTask_CarNum;
        }

        public void setLogTask_CarNum(String LogTask_CarNum) {
            this.LogTask_CarNum = LogTask_CarNum;
        }

        public String getLogTask_Serial() {
            return LogTask_Serial;
        }

        public void setLogTask_Serial(String LogTask_Serial) {
            this.LogTask_Serial = LogTask_Serial;
        }

        public String getLogTask_TransNum() {
            return LogTask_TransNum;
        }

        public void setLogTask_TransNum(String LogTask_TransNum) {
            this.LogTask_TransNum = LogTask_TransNum;
        }

        public String getLogTask_Market() {
            return LogTask_Market;
        }

        public void setLogTask_Market(String LogTask_Market) {
            this.LogTask_Market = LogTask_Market;
        }

        public String getLogTask_SellerID() {
            return LogTask_SellerID;
        }

        public void setLogTask_SellerID(String LogTask_SellerID) {
            this.LogTask_SellerID = LogTask_SellerID;
        }

        public String getLogTask_Booth() {
            return LogTask_Booth;
        }

        public void setLogTask_Booth(String LogTask_Booth) {
            this.LogTask_Booth = LogTask_Booth;
        }

        public String getLogTask_BuyerID() {
            return LogTask_BuyerID;
        }

        public void setLogTask_BuyerID(String LogTask_BuyerID) {
            this.LogTask_BuyerID = LogTask_BuyerID;
        }

        public String getLogTask_Area() {
            return LogTask_Area;
        }

        public void setLogTask_Area(String LogTask_Area) {
            this.LogTask_Area = LogTask_Area;
        }

        public String getLogTask_Street() {
            return LogTask_Street;
        }

        public void setLogTask_Street(String LogTask_Street) {
            this.LogTask_Street = LogTask_Street;
        }

        public int getLogTask_GoodsId() {
            return LogTask_GoodsId;
        }

        public void setLogTask_GoodsId(int LogTask_GoodsId) {
            this.LogTask_GoodsId = LogTask_GoodsId;
        }

        public String getLogTask_GoodsTitle() {
            return LogTask_GoodsTitle;
        }

        public void setLogTask_GoodsTitle(String LogTask_GoodsTitle) {
            this.LogTask_GoodsTitle = LogTask_GoodsTitle;
        }

        public int getLogTask_Weight() {
            return LogTask_Weight;
        }

        public void setLogTask_Weight(int LogTask_Weight) {
            this.LogTask_Weight = LogTask_Weight;
        }

        public String getLogTask_RecPerson() {
            return LogTask_RecPerson;
        }

        public void setLogTask_RecPerson(String LogTask_RecPerson) {
            this.LogTask_RecPerson = LogTask_RecPerson;
        }

        public String getLogTask_RecTel() {
            return LogTask_RecTel;
        }

        public void setLogTask_RecTel(String LogTask_RecTel) {
            this.LogTask_RecTel = LogTask_RecTel;
        }

        public String getLogTask_RecAddr() {
            return LogTask_RecAddr;
        }

        public void setLogTask_RecAddr(String LogTask_RecAddr) {
            this.LogTask_RecAddr = LogTask_RecAddr;
        }

        public String getLogTask_DispTime() {
            return LogTask_DispTime;
        }

        public void setLogTask_DispTime(String LogTask_DispTime) {
            this.LogTask_DispTime = LogTask_DispTime;
        }

        public String getLogTask_DispUser() {
            return LogTask_DispUser;
        }

        public void setLogTask_DispUser(String LogTask_DispUser) {
            this.LogTask_DispUser = LogTask_DispUser;
        }

        public String getLogTask_AccTime() {
            return LogTask_AccTime;
        }

        public void setLogTask_AccTime(String LogTask_AccTime) {
            this.LogTask_AccTime = LogTask_AccTime;
        }

        public String getLogTask_AccUser() {
            return LogTask_AccUser;
        }

        public void setLogTask_AccUser(String LogTask_AccUser) {
            this.LogTask_AccUser = LogTask_AccUser;
        }

        public String getLogTask_PackTime() {
            return LogTask_PackTime;
        }

        public void setLogTask_PackTime(String LogTask_PackTime) {
            this.LogTask_PackTime = LogTask_PackTime;
        }

        public String getLogTask_PackUser() {
            return LogTask_PackUser;
        }

        public void setLogTask_PackUser(String LogTask_PackUser) {
            this.LogTask_PackUser = LogTask_PackUser;
        }

        public String getLogTask_PackImg() {
            return LogTask_PackImg;
        }

        public void setLogTask_PackImg(String LogTask_PackImg) {
            this.LogTask_PackImg = LogTask_PackImg;
        }

        public String getLogTask_DepTime() {
            return LogTask_DepTime;
        }

        public void setLogTask_DepTime(String LogTask_DepTime) {
            this.LogTask_DepTime = LogTask_DepTime;
        }

        public String getLogTask_DepUser() {
            return LogTask_DepUser;
        }

        public void setLogTask_DepUser(String LogTask_DepUser) {
            this.LogTask_DepUser = LogTask_DepUser;
        }

        public String getLogTask_DepImg() {
            return LogTask_DepImg;
        }

        public void setLogTask_DepImg(String LogTask_DepImg) {
            this.LogTask_DepImg = LogTask_DepImg;
        }

        public String getLogTask_SendTime() {
            return LogTask_SendTime;
        }

        public void setLogTask_SendTime(String LogTask_SendTime) {
            this.LogTask_SendTime = LogTask_SendTime;
        }

        public String getLogTask_SendUser() {
            return LogTask_SendUser;
        }

        public void setLogTask_SendUser(String LogTask_SendUser) {
            this.LogTask_SendUser = LogTask_SendUser;
        }

        public String getLogTask_SendImg() {
            return LogTask_SendImg;
        }

        public void setLogTask_SendImg(String LogTask_SendImg) {
            this.LogTask_SendImg = LogTask_SendImg;
        }

        public String getLogTask_DeliTime() {
            return LogTask_DeliTime;
        }

        public void setLogTask_DeliTime(String LogTask_DeliTime) {
            this.LogTask_DeliTime = LogTask_DeliTime;
        }

        public String getLogTask_DeliUser() {
            return LogTask_DeliUser;
        }

        public void setLogTask_DeliUser(String LogTask_DeliUser) {
            this.LogTask_DeliUser = LogTask_DeliUser;
        }

        public String getLogTask_DeliImg() {
            return LogTask_DeliImg;
        }

        public void setLogTask_DeliImg(String LogTask_DeliImg) {
            this.LogTask_DeliImg = LogTask_DeliImg;
        }

        public String getLogTask_State() {
            return LogTask_State;
        }

        public void setLogTask_State(String LogTask_State) {
            this.LogTask_State = LogTask_State;
        }
    }
}
