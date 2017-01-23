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
     * Data : {"PageSize":3,"CurPage":"1","Logs":[{"TaskNum":"W20170104143604788T2L","Serial":"S201701040914040738LF","TransNum":"20170104091404150R66","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"12.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604750NZJ","Serial":"S20161227091627339P26","TransNum":"20161227091627355LHD","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光杏鲍菇","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604736LH4","Serial":"S20161227091627339P26","TransNum":"2016122709162735544X","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光蘑菇","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604706FL2","Serial":"S20161228091028728H6Z","TransNum":"20161228091028700R6R","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"12.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W2017010414360464860V","Serial":"S2016122709302709560X","TransNum":"201612270930271110P6","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604625064","Serial":"S201701040914040738LF","TransNum":"2017010409140412006Z","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"山东瓜子","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山C区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604613V8H","Serial":"S20161228091028728H6Z","TransNum":"201612280910282532P4","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W201701041436045552Z0","Serial":"S20161227091627339P26","TransNum":"2016122709162735544X","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光蘑菇","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W201701041436045290V2","Serial":"S20161228091028728H6Z","TransNum":"2016122809102848342H","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604492RDX","Serial":"S2016123017263050184V","TransNum":"20161230172630562XTF","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光蘑菇","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604489806","Serial":"S2016122709302709560X","TransNum":"201612270930271110P6","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604480N2Z","Serial":"S2016122709302709560X","TransNum":"2016122709302709568P","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604467D02","Serial":"S201701040914040738LF","TransNum":"20170104091404150R66","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"12.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604416H6N","Serial":"S20161228091028728H6Z","TransNum":"201612280910280156Z8","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"1.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W201701041436044098R0","Serial":"S201701040914040738LF","TransNum":"201701040914040816J8","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"内蒙白瓜子","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山C区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W2017010414360434880T","Serial":"S20161228091028728H6Z","TransNum":"201612280910282532P4","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604314R22","Serial":"S2016123017263050184V","TransNum":"20161230172630511848","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604313N64","Serial":"S201701040914040738LF","TransNum":"2017010409140412006Z","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"山东瓜子","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山C区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604307426","Serial":"S20161227091627339P26","TransNum":"20161227091627370Z64","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604283068","Serial":"S2016122709302709560X","TransNum":"2016122709302709568P","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"}]}
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
         * PageSize : 3
         * CurPage : 1
         * Logs : [{"TaskNum":"W20170104143604788T2L","Serial":"S201701040914040738LF","TransNum":"20170104091404150R66","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"12.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604750NZJ","Serial":"S20161227091627339P26","TransNum":"20161227091627355LHD","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光杏鲍菇","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604736LH4","Serial":"S20161227091627339P26","TransNum":"2016122709162735544X","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光蘑菇","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604706FL2","Serial":"S20161228091028728H6Z","TransNum":"20161228091028700R6R","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"12.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W2017010414360464860V","Serial":"S2016122709302709560X","TransNum":"201612270930271110P6","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604625064","Serial":"S201701040914040738LF","TransNum":"2017010409140412006Z","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"山东瓜子","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山C区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604613V8H","Serial":"S20161228091028728H6Z","TransNum":"201612280910282532P4","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W201701041436045552Z0","Serial":"S20161227091627339P26","TransNum":"2016122709162735544X","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光蘑菇","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W201701041436045290V2","Serial":"S20161228091028728H6Z","TransNum":"2016122809102848342H","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604492RDX","Serial":"S2016123017263050184V","TransNum":"20161230172630562XTF","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"寿光蘑菇","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604489806","Serial":"S2016122709302709560X","TransNum":"201612270930271110P6","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"3.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604480N2Z","Serial":"S2016122709302709560X","TransNum":"2016122709302709568P","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604467D02","Serial":"S201701040914040738LF","TransNum":"20170104091404150R66","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"12.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604416H6N","Serial":"S20161228091028728H6Z","TransNum":"201612280910280156Z8","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"1.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W201701041436044098R0","Serial":"S201701040914040738LF","TransNum":"201701040914040816J8","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"内蒙白瓜子","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山C区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W2017010414360434880T","Serial":"S20161228091028728H6Z","TransNum":"201612280910282532P4","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"马踏湖莲藕","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604314R22","Serial":"S2016123017263050184V","TransNum":"20161230172630511848","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604313N64","Serial":"S201701040914040738LF","TransNum":"2017010409140412006Z","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"山东瓜子","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山C区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604307426","Serial":"S20161227091627339P26","TransNum":"20161227091627370Z64","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"宁阳鸡腿菇","Weight":"2.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山B区002","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"},{"TaskNum":"W20170104143604283068","Serial":"S2016122709302709560X","TransNum":"2016122709302709568P","Area":"历下区","Street":"千佛山街道办事处","GoodsTitle":"芥蓝","Weight":"4.00","BuyerID":"370101000010","RecPerson":"济南大学舜耕校区采购","RecTel":"15264118740","RecAddr":"济南市历下区舜耕路12号","AccTime":"1753-01-01 00:00:00","AccUser":"","PackTime":"1753-01-01 00:00:00","PackUser":"","PackPic":"","DepTime":"1753-01-01 00:00:00","DepUser":"","DepPic":"","SendTime":"1753-01-01 00:00:00","SendUser":"","SendPic":"","DeliTime":"1753-01-01 00:00:00","DeliUser":"","State":"已派单","Booth":"匡山A区001","ComName":"济南大学舜耕校区食堂","Distance":"26.00","LogUrg":"0"}]
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
             * TaskNum : W20170104143604788T2L
             * Serial : S201701040914040738LF
             * TransNum : 20170104091404150R66
             * Area : 历下区
             * Street : 千佛山街道办事处
             * GoodsTitle : 宁阳鸡腿菇
             * Weight : 12.00
             * BuyerID : 370101000010
             * RecPerson : 济南大学舜耕校区采购
             * RecTel : 15264118740
             * RecAddr : 济南市历下区舜耕路12号
             * AccTime : 1753-01-01 00:00:00
             * AccUser :
             * PackTime : 1753-01-01 00:00:00
             * PackUser :
             * PackPic :
             * DepTime : 1753-01-01 00:00:00
             * DepUser :
             * DepPic :
             * SendTime : 1753-01-01 00:00:00
             * SendUser :
             * SendPic :
             * DeliTime : 1753-01-01 00:00:00
             * DeliUser :
             * State : 已派单
             * Booth : 匡山B区002
             * ComName : 济南大学舜耕校区食堂
             * Distance : 26.00
             * LogUrg : 0
             */

            private String TaskNum;
            private String Serial;
            private String TransNum;
            private String Area;
            private String Street;
            private String GoodsTitle;
            private String Weight;
            private String BuyerID;
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
            private String ComName;
            private String Distance;
            private String LogUrg;




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

            public String getBuyerID() {
                return BuyerID;
            }

            public void setBuyerID(String BuyerID) {
                this.BuyerID = BuyerID;
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

            public String getComName() {
                return ComName;
            }

            public void setComName(String ComName) {
                this.ComName = ComName;
            }

            public String getDistance() {
                return Distance;
            }

            public void setDistance(String Distance) {
                this.Distance = Distance;
            }

            public String getLogUrg() {
                return LogUrg;
            }

            public void setLogUrg(String LogUrg) {
                this.LogUrg = LogUrg;
            }
        }
    }
}
