package com.auto.logistics.JavaBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/4.
 */
public class Position  implements Serializable{

    /**
     * ID : 1
     * par : {"Pos":"P001"}
     */


    private int ID;
    private ParBean par;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ParBean getPar() {
        return par;
    }

    public void setPar(ParBean par) {
        this.par = par;
    }

    public static class ParBean {
        /**
         * Pos : P001
         */

        private String Pos;

        public String getPos() {
            return Pos;
        }

        public void setPos(String Pos) {
            this.Pos = Pos;
        }
    }
}
