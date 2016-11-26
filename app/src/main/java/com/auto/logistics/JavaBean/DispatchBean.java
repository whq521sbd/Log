package com.auto.logistics.JavaBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class DispatchBean implements Serializable {

    /**
     * Suc : true
     * Msg :
     * Data : {"PageSize":1,"CurPage":"1","Logs":[{"TaskNum":"W20161125155925142XT4","Serial":"S20161125155725194R2J","TransNum":"20161125155725209N28","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"新疆核桃","Weight":"20.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-25 03:59:52","AccUser":"370101000002","PackTime":"2016-11-25 04:00:00","PackUser":"370101000002","PackPic":"/UImage/Logistics/201611/20161125040000062.PNG","DepTime":"2016-11-25 04:00:14","DepUser":"370101000002","DepPic":"/UImage/Logistics/201611/2016112504001488H.PNG","SendTime":"2016-11-25 04:00:25","SendUser":"370101000002","SendPic":"/UImage/Logistics/201611/20161125040025PT8.PNG","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"买方收货纠纷","Booth":"匡山C区002"},{"TaskNum":"W201611251559251220P8","Serial":"S20161125155725194R2J","TransNum":"20161125155725209HTF","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"承德杏仁","Weight":"10.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-26 10:01:04","AccUser":"370101000002","PackTime":"2016-11-26 10:01:33","PackUser":"370101000002","PackPic":"/UImage/Logistics/201611/20161126100133L6V.PNG,,/UImage/Logistics/201611/20161126100133N2F.PNG,,/UImage/Logistics/201611/20161126100133TV2.PNG,","DepTime":"2016-11-26 10:01:54","DepUser":"370101000002","DepPic":"/UImage/Logistics/201611/20161126100154TR2.PNG,,/UImage/Logistics/201611/20161126100154B62.PNG,,/UImage/Logistics/201611/20161126100154HJ8.PNG,","SendTime":"2016-11-26 10:02:30","SendUser":"370101000002","SendPic":"/UImage/Logistics/201611/20161126100230402.PNG,,/UImage/Logistics/201611/20161126100230824.PNG,,/UImage/Logistics/201611/201611261002304J8.PNG,","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"买方收货纠纷","Booth":"匡山C区002"}]}
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
         * Logs : [{"TaskNum":"W20161125155925142XT4","Serial":"S20161125155725194R2J","TransNum":"20161125155725209N28","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"新疆核桃","Weight":"20.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-25 03:59:52","AccUser":"370101000002","PackTime":"2016-11-25 04:00:00","PackUser":"370101000002","PackPic":"/UImage/Logistics/201611/20161125040000062.PNG","DepTime":"2016-11-25 04:00:14","DepUser":"370101000002","DepPic":"/UImage/Logistics/201611/2016112504001488H.PNG","SendTime":"2016-11-25 04:00:25","SendUser":"370101000002","SendPic":"/UImage/Logistics/201611/20161125040025PT8.PNG","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"买方收货纠纷","Booth":"匡山C区002"},{"TaskNum":"W201611251559251220P8","Serial":"S20161125155725194R2J","TransNum":"20161125155725209HTF","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"承德杏仁","Weight":"10.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-26 10:01:04","AccUser":"370101000002","PackTime":"2016-11-26 10:01:33","PackUser":"370101000002","PackPic":"/UImage/Logistics/201611/20161126100133L6V.PNG,,/UImage/Logistics/201611/20161126100133N2F.PNG,,/UImage/Logistics/201611/20161126100133TV2.PNG,","DepTime":"2016-11-26 10:01:54","DepUser":"370101000002","DepPic":"/UImage/Logistics/201611/20161126100154TR2.PNG,,/UImage/Logistics/201611/20161126100154B62.PNG,,/UImage/Logistics/201611/20161126100154HJ8.PNG,","SendTime":"2016-11-26 10:02:30","SendUser":"370101000002","SendPic":"/UImage/Logistics/201611/20161126100230402.PNG,,/UImage/Logistics/201611/20161126100230824.PNG,,/UImage/Logistics/201611/201611261002304J8.PNG,","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"买方收货纠纷","Booth":"匡山C区002"}]
         */

        private int PageSize;
        private String CurPage;
        private List<LogsListBean> Logs;

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

        public List<LogsListBean> getLogs() {
            return Logs;
        }

        public void setLogs(List<LogsListBean> Logs) {
            this.Logs = Logs;
        }

        public static class LogsListBean implements Serializable{
            /**
             * TaskNum : W20161125155925142XT4
             * Serial : S20161125155725194R2J
             * TransNum : 20161125155725209N28
             * Area : 历下区
             * Street : 千佛山街道办事处
             * GoodsTitle : 新疆核桃
             * Weight : 20.00
             * RecPerson : 济南翰林大酒店采购
             * RecTel : 18805310230
             * RecAddr : 济南市经十路221号
             * AccTime : 2016-11-25 03:59:52
             * AccUser : 370101000002
             * PackTime : 2016-11-25 04:00:00
             * PackUser : 370101000002
             * PackPic : /UImage/Logistics/201611/20161125040000062.PNG
             * DepTime : 2016-11-25 04:00:14
             * DepUser : 370101000002
             * DepPic : /UImage/Logistics/201611/2016112504001488H.PNG
             * SendTime : 2016-11-25 04:00:25
             * SendUser : 370101000002
             * SendPic : /UImage/Logistics/201611/20161125040025PT8.PNG
             * DeliTime : 1753-01-01 12:00:00
             * DeliUser :
             * State : 买方收货纠纷
             * Booth : 匡山C区002
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
            private String PackPic;
            private String DepTime;
            private String DepUser;
            private String DepPic;
            private String SendTime;
            private String SendUser;
            private String SendPic;
            private String DeliTime;
            private String DeliUser;
            private String State;
            private String Booth;

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

            public String getPackPic() {
                return PackPic;
            }

            public void setPackPic(String PackPic) {
                this.PackPic = PackPic;
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

            public String getDepPic() {
                return DepPic;
            }

            public void setDepPic(String DepPic) {
                this.DepPic = DepPic;
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

            public String getSendPic() {
                return SendPic;
            }

            public void setSendPic(String SendPic) {
                this.SendPic = SendPic;
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

            public String getBooth() {
                return Booth;
            }

            public void setBooth(String Booth) {
                this.Booth = Booth;
            }
        }
    }
}
