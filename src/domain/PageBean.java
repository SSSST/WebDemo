package domain;

import java.util.List;

public class PageBean<Object> {
    private String url;
    private int tr; // total records;
    private int pr; // page records;
    private int pc; // page code;
    private List<Object> beanList; // 当前页记录

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTr() {
        return this.tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getPr(int pr) {
        return this.pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

    public int getPc() {
        return this.pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getTp() { // get total page
        int tp = tr / pr;
        return tr % pr == 0 ? tp : tp + 1;
    }

    public List<Object> getBeanList() {
        return this.beanList;
    }

    public void setBeanList(List<Object> beanList) {
        this.beanList = beanList;
    }
}
