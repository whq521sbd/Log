package com.auto.logistics.JavaBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class LogTaskBean implements Serializable {


    /**
     * Suc : true
     * Msg :
     * Data : {"PageSize":1,"CurPage":"1","Logs":[{"TaskNum":"W201611221528229658TZ","Serial":"S201611221526225244R6","TransNum":"201611221526225718F0","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"惠民韭菜","Weight":"120.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822910Z6F","Serial":"S201611221526225244R6","TransNum":"201611221526225716RX","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"惠民韭菜","Weight":"55.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822829822","Serial":"S201611221526225244R6","TransNum":"20161122152622555FN2","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"红根菠菜","Weight":"53.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822766JD0","Serial":"S201611221526225244R6","TransNum":"2016112215262255584J","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"50.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822690N46","Serial":"S201611221526225244R6","TransNum":"201611221526225550H6","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"章丘鲍芹","Weight":"58.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528226258T2","Serial":"S201611221526225244R6","TransNum":"20161122152622539VRR","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"章丘鲍芹","Weight":"76.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822550R62","Serial":"S201611221526225244R6","TransNum":"201611221526225392N0","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"唐王天津绿白菜","Weight":"89.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822497802","Serial":"S201611221526225244R6","TransNum":"20161122152622617X06","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"唐王天津绿白菜","Weight":"60.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W2016112215282245906P","Serial":"S201611221526225244R6","TransNum":"20161122152622524D8P","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"商河包菜","Weight":"90.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822422480","Serial":"S201611221526225244R6","TransNum":"201611221526226176HF","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"高唐娃娃菜","Weight":"68.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528223638XX","Serial":"S201611221526225244R6","TransNum":"201611221526226174LH","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"50.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528222896ZX","Serial":"S201611221526225244R6","TransNum":"2016112215262260224N","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"唐王天津绿白菜","Weight":"70.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528222268N4","Serial":"S201611221526225244R6","TransNum":"201611221526226020PX","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"高唐娃娃菜","Weight":"80.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822108D0J","Serial":"S201611221526225244R6","TransNum":"20161122152622586V2J","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"50.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822042J6Z","Serial":"S201611221526225244R6","TransNum":"20161122152622586P48","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"红根菠菜","Weight":"54.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"}]}
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
         * Logs : [{"TaskNum":"W201611221528229658TZ","Serial":"S201611221526225244R6","TransNum":"201611221526225718F0","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"惠民韭菜","Weight":"120.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822910Z6F","Serial":"S201611221526225244R6","TransNum":"201611221526225716RX","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"惠民韭菜","Weight":"55.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822829822","Serial":"S201611221526225244R6","TransNum":"20161122152622555FN2","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"红根菠菜","Weight":"53.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822766JD0","Serial":"S201611221526225244R6","TransNum":"2016112215262255584J","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"50.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822690N46","Serial":"S201611221526225244R6","TransNum":"201611221526225550H6","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"章丘鲍芹","Weight":"58.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528226258T2","Serial":"S201611221526225244R6","TransNum":"20161122152622539VRR","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"章丘鲍芹","Weight":"76.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822550R62","Serial":"S201611221526225244R6","TransNum":"201611221526225392N0","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"唐王天津绿白菜","Weight":"89.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822497802","Serial":"S201611221526225244R6","TransNum":"20161122152622617X06","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"唐王天津绿白菜","Weight":"60.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W2016112215282245906P","Serial":"S201611221526225244R6","TransNum":"20161122152622524D8P","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"商河包菜","Weight":"90.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:35","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822422480","Serial":"S201611221526225244R6","TransNum":"201611221526226176HF","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"高唐娃娃菜","Weight":"68.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528223638XX","Serial":"S201611221526225244R6","TransNum":"201611221526226174LH","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"50.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528222896ZX","Serial":"S201611221526225244R6","TransNum":"2016112215262260224N","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"唐王天津绿白菜","Weight":"70.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W201611221528222268N4","Serial":"S201611221526225244R6","TransNum":"201611221526226020PX","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"高唐娃娃菜","Weight":"80.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822108D0J","Serial":"S201611221526225244R6","TransNum":"20161122152622586V2J","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"50.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"},{"TaskNum":"W20161122152822042J6Z","Serial":"S201611221526225244R6","TransNum":"20161122152622586P48","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"红根菠菜","Weight":"54.00","RecPerson":"济南翰林大酒店采购","RecTel":"18805310230","RecAddr":"济南市经十路221号","AccTime":"2016-11-22 03:28:36","AccUser":"jnksdd","PackTime":"1753-01-01 12:00:00","PackUser":"","DepTime":"1753-01-01 12:00:00","DepUser":"","SendTime":"1753-01-01 12:00:00","SendUser":"","DeliTime":"1753-01-01 12:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001"}]
         */

        private int PageSize;
        private String CurPage;
        private List<LogsBean> Logs;

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

        public List<LogsBean> getLogs() {
            return Logs;
        }

        public void setLogs(List<LogsBean> Logs) {
            this.Logs = Logs;
        }

        public static class LogsBean implements Serializable {
            /**
             * TaskNum : W201611221528229658TZ
             * Serial : S201611221526225244R6
             * TransNum : 201611221526225718F0
             * Area : 历下区
             * Street : 千佛山街道办事处
             * GoodsTitle : 惠民韭菜
             * Weight : 120.00
             * RecPerson : 济南翰林大酒店采购
             * RecTel : 18805310230
             * RecAddr : 济南市经十路221号
             * AccTime : 2016-11-22 03:28:35
             * AccUser : jnksdd
             * PackTime : 1753-01-01 12:00:00
             * PackUser :
             * DepTime : 1753-01-01 12:00:00
             * DepUser :
             * SendTime : 1753-01-01 12:00:00
             * SendUser :
             * DeliTime : 1753-01-01 12:00:00
             * DeliUser :
             * State : 已派单
             * Booth : 匡山A区001
             */
            private boolean ischecked = false;

            public boolean ischecked() {
                return ischecked;
            }

            public void setIschecked(boolean ischecked) {
                this.ischecked = ischecked;
            }

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
            private String PackPic;
            private String DepPic;
            private String SendPic;

            public String getPackPic() {
                return PackPic;
            }

            public void setPackPic(String packPic) {
                PackPic = packPic;
            }

            public String getDepPic() {
                return DepPic;
            }

            public void setDepPic(String depPic) {
                DepPic = depPic;
            }

            public String getSendPic() {
                return SendPic;
            }

            public void setSendPic(String sendPic) {
                SendPic = sendPic;
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

            public String getBooth() {
                return Booth;
            }

            public void setBooth(String Booth) {
                this.Booth = Booth;
            }
        }
    }
}
