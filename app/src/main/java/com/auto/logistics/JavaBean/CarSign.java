package com.auto.logistics.JavaBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */

public class CarSign implements Serializable {

    /**
     * Suc : true
     * Msg :
     * Data : {"PageSize":4,"CurPage":"1","SignCars":[{"FullName":"历下区司机张岚山","SignTime":"2016-12-17 11:21:53","State":"修改","SignText":"123645648487676","SignImg":"2016/12/17 11:21:53"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-16 09:30:03","State":"","SignText":"","SignImg":"2016/12/16 9:30:03"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 15:59:01","State":"","SignText":"","SignImg":"2016/12/15 15:59:01"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 15:55:41","State":"","SignText":"","SignImg":"2016/12/15 15:55:41"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 15:51:59","State":"","SignText":"","SignImg":"2016/12/15 15:51:59"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:45","State":"","SignText":"","SignImg":"2016/12/15 10:49:45"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:45","State":"","SignText":"","SignImg":"2016/12/15 10:49:45"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:40","State":"","SignText":"","SignImg":"2016/12/15 10:49:40"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:40","State":"","SignText":"","SignImg":"2016/12/15 10:49:40"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:53","State":"","SignText":"","SignImg":"2016/12/15 10:48:53"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:53","State":"","SignText":"","SignImg":"2016/12/15 10:48:53"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:46","State":"","SignText":"","SignImg":"2016/12/15 10:48:46"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:45","State":"","SignText":"","SignImg":"2016/12/15 10:48:45"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:28","State":"","SignText":"","SignImg":"2016/12/15 10:48:28"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:28","State":"","SignText":"","SignImg":"2016/12/15 10:48:28"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:22","State":"","SignText":"","SignImg":"2016/12/15 10:48:22"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:22","State":"","SignText":"","SignImg":"2016/12/15 10:48:22"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:17","State":"","SignText":"","SignImg":"2016/12/15 10:48:17"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:16","State":"","SignText":"","SignImg":"2016/12/15 10:48:16"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:11","State":"","SignText":"","SignImg":"2016/12/15 10:48:11"}]}
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

    public static class DataBean  {
        /**
         * PageSize : 4
         * CurPage : 1
         * SignCars : [{"FullName":"历下区司机张岚山","SignTime":"2016-12-17 11:21:53","State":"修改","SignText":"123645648487676","SignImg":"2016/12/17 11:21:53"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-16 09:30:03","State":"","SignText":"","SignImg":"2016/12/16 9:30:03"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 15:59:01","State":"","SignText":"","SignImg":"2016/12/15 15:59:01"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 15:55:41","State":"","SignText":"","SignImg":"2016/12/15 15:55:41"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 15:51:59","State":"","SignText":"","SignImg":"2016/12/15 15:51:59"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:45","State":"","SignText":"","SignImg":"2016/12/15 10:49:45"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:45","State":"","SignText":"","SignImg":"2016/12/15 10:49:45"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:40","State":"","SignText":"","SignImg":"2016/12/15 10:49:40"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:49:40","State":"","SignText":"","SignImg":"2016/12/15 10:49:40"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:53","State":"","SignText":"","SignImg":"2016/12/15 10:48:53"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:53","State":"","SignText":"","SignImg":"2016/12/15 10:48:53"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:46","State":"","SignText":"","SignImg":"2016/12/15 10:48:46"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:45","State":"","SignText":"","SignImg":"2016/12/15 10:48:45"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:28","State":"","SignText":"","SignImg":"2016/12/15 10:48:28"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:28","State":"","SignText":"","SignImg":"2016/12/15 10:48:28"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:22","State":"","SignText":"","SignImg":"2016/12/15 10:48:22"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:22","State":"","SignText":"","SignImg":"2016/12/15 10:48:22"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:17","State":"","SignText":"","SignImg":"2016/12/15 10:48:17"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:16","State":"","SignText":"","SignImg":"2016/12/15 10:48:16"},{"FullName":"历下区司机张岚山","SignTime":"2016-12-15 10:48:11","State":"","SignText":"","SignImg":"2016/12/15 10:48:11"}]
         */

        private int PageSize;
        private String CurPage;
        private List<SignCarsBean> SignCars;

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

        public List<SignCarsBean> getSignCars() {
            return SignCars;
        }

        public void setSignCars(List<SignCarsBean> SignCars) {
            this.SignCars = SignCars;
        }

        public static class SignCarsBean  implements Serializable{
            /**
             * FullName : 历下区司机张岚山
             * SignTime : 2016-12-17 11:21:53
             * State : 修改
             * SignText : 123645648487676
             * SignImg : 2016/12/17 11:21:53
             */

            private String FullName;
            private String SignTime;
            private String State;
            private String SignText;
            private String SignImg;

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String FullName) {
                this.FullName = FullName;
            }

            public String getSignTime() {
                return SignTime;
            }

            public void setSignTime(String SignTime) {
                this.SignTime = SignTime;
            }

            public String getState() {
                return State;
            }

            public void setState(String State) {
                this.State = State;
            }

            public String getSignText() {
                return SignText;
            }

            public void setSignText(String SignText) {
                this.SignText = SignText;
            }

            public String getSignImg() {
                return SignImg;
            }

            public void setSignImg(String SignImg) {
                this.SignImg = SignImg;
            }
        }
    }
}
