package com.auto.logistics.JavaBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class DispatchBean implements Serializable {

    /**
     * Suc : true
     * Msg :
     * Data : {"PageSize":1,"CurPage":"1","Logs":[{"TaskNum":"W201611070943074554L2","Serial":"S20161023112223813BNT","TransNum":"201610231122238292BN","Area":"市中区","Street":"四里村街道办事处","GoodsTitle":"惠民韭菜","Weight":"38.00","RecPerson":"济南华能大厦采购","RecTel":"18678808192","RecAddr":"济南市泉城路23号","AccTime":"2016-11-07 09:43:08","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"买方收货纠纷"}]}
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
         * PageSize : 1
         * CurPage : 1
         * Logs : [{"TaskNum":"W201611070943074554L2","Serial":"S20161023112223813BNT","TransNum":"201610231122238292BN","Area":"市中区","Street":"四里村街道办事处","GoodsTitle":"惠民韭菜","Weight":"38.00","RecPerson":"济南华能大厦采购","RecTel":"18678808192","RecAddr":"济南市泉城路23号","AccTime":"2016-11-07 09:43:08","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"买方收货纠纷"}]
         */

        private int PageSize;
        private String CurPage;
        private ArrayList<LogsListBean> Logs;

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public String getCurPage() {
            return CurPage;
        }

        public void setCurPage(String CurPage) {
            this.CurPage = CurPage;
        }

        public ArrayList<LogsListBean> getLogs() {
            return Logs;
        }

        public void setLogs(ArrayList<LogsListBean> Logs) {
            this.Logs = Logs;
        }

        public static class LogsListBean implements Serializable{
            /**
             * TaskNum : W201611070943074554L2
             * Serial : S20161023112223813BNT
             * TransNum : 201610231122238292BN
             * Area : 市中区
             * Street : 四里村街道办事处
             * GoodsTitle : 惠民韭菜
             * Weight : 38.00
             * RecPerson : 济南华能大厦采购
             * RecTel : 18678808192
             * RecAddr : 济南市泉城路23号
             * AccTime : 2016-11-07 09:43:08
             * AccUser : jnksdd
             * PackTime : 1753-01-01 12:00:00
             * PackUser :
             * DepTime : 1753-01-01 12:00:00
             * DepUser :
             * SendTime : 1753-01-01 12:00:00
             * SendUser :
             * DeliTime : 1753-01-01 12:00:00
             * DeliUser :
             * State : 买方收货纠纷
             */

            private String TaskNum;
            private String Serial;
            private String TransNum;
            private String Area;
            private String Street;
            private String GoodsTitle;
            private String Weight;
            private String RecPerson;
            private String RecTel;
            private String RecAddr;
            private String AccTime;
            private String AccUser;
            private String PackTime;
            private String PackUser;
            private String DepTime;
            private String DepUser;
            private String SendTime;
            private String SendUser;
            private String DeliTime;
            private String DeliUser;
            private String State;
            private String Booth;

            public String getBooth() {
                return Booth;
            }

            public void setBooth(String booth) {
                Booth = booth;
            }

            public String getTaskNum() {
                return TaskNum;
            }

            public void setTaskNum(String TaskNum) {
                this.TaskNum = TaskNum;
            }

            public String getSerial() {
                return Serial;
            }

            public void setSerial(String Serial) {
                this.Serial = Serial;
            }

            public String getTransNum() {
                return TransNum;
            }

            public void setTransNum(String TransNum) {
                this.TransNum = TransNum;
            }

            public String getArea() {
                return Area;
            }

            public void setArea(String Area) {
                this.Area = Area;
            }

            public String getStreet() {
                return Street;
            }

            public void setStreet(String Street) {
                this.Street = Street;
            }

            public String getGoodsTitle() {
                return GoodsTitle;
            }

            public void setGoodsTitle(String GoodsTitle) {
                this.GoodsTitle = GoodsTitle;
            }

            public String getWeight() {
                return Weight;
            }

            public void setWeight(String Weight) {
                this.Weight = Weight;
            }

            public String getRecPerson() {
                return RecPerson;
            }

            public void setRecPerson(String RecPerson) {
                this.RecPerson = RecPerson;
            }

            public String getRecTel() {
                return RecTel;
            }

            public void setRecTel(String RecTel) {
                this.RecTel = RecTel;
            }

            public String getRecAddr() {
                return RecAddr;
            }

            public void setRecAddr(String RecAddr) {
                this.RecAddr = RecAddr;
            }

            public String getAccTime() {
                return AccTime;
            }

            public void setAccTime(String AccTime) {
                this.AccTime = AccTime;
            }

            public String getAccUser() {
                return AccUser;
            }

            public void setAccUser(String AccUser) {
                this.AccUser = AccUser;
            }

            public String getPackTime() {
                return PackTime;
            }

            public void setPackTime(String PackTime) {
                this.PackTime = PackTime;
            }

            public String getPackUser() {
                return PackUser;
            }

            public void setPackUser(String PackUser) {
                this.PackUser = PackUser;
            }

            public String getDepTime() {
                return DepTime;
            }

            public void setDepTime(String DepTime) {
                this.DepTime = DepTime;
            }

            public String getDepUser() {
                return DepUser;
            }

            public void setDepUser(String DepUser) {
                this.DepUser = DepUser;
            }

            public String getSendTime() {
                return SendTime;
            }

            public void setSendTime(String SendTime) {
                this.SendTime = SendTime;
            }

            public String getSendUser() {
                return SendUser;
            }

            public void setSendUser(String SendUser) {
                this.SendUser = SendUser;
            }

            public String getDeliTime() {
                return DeliTime;
            }

            public void setDeliTime(String DeliTime) {
                this.DeliTime = DeliTime;
            }

            public String getDeliUser() {
                return DeliUser;
            }

            public void setDeliUser(String DeliUser) {
                this.DeliUser = DeliUser;
            }

            public String getState() {
                return State;
            }

            public void setState(String State) {
                this.State = State;
            }
        }
    }
}
