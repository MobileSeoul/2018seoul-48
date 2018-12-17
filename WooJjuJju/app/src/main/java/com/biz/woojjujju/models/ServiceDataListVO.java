package com.biz.woojjujju.models;

import java.io.Serializable;

public class ServiceDataListVO implements Serializable {
  /*
    servNm 서비스명
    tgtrDtlCn 대상자 상세내용
    slctCritCn 선정기준 내용
    alwServCn   급여서비스 내용
    servSeDetailNm 서비스 이용
    inqplCtadrList
    servSeDetailLink 사이트링크
    servSeDetailNm 문의처명
    servSeDetailLink 연락처

    */

    private String  servNm;
    private String servDgst;
    private String tgtrDtlCn;// 대상자 상세내용
    private String slctCritCn;// 선정기준 내용
    private String alwServCn;
    private String servSeDetailNm ;//서비스 이용
    private String inqpDatailNm;
    private String inqpDatailLink;
    private String hmpgDatailLink;

    //    servSeDetailLink 연락처
//    servSeDetailNm; 문의처명


    public String getHmpgDatailLink() {
        return hmpgDatailLink;
    }

    public ServiceDataListVO(String servNm, String servDgst, String tgtrDtlCn, String slctCritCn, String alwServCn, String servSeDetailNm, String inqpDatailNm, String inqpDatailLink, String hmpgDatailLink) {
        this.servNm = servNm;
        this.servDgst = servDgst;
        this.tgtrDtlCn = tgtrDtlCn;
        this.slctCritCn = slctCritCn;
        this.alwServCn = alwServCn;
        this.servSeDetailNm = servSeDetailNm;
        this.inqpDatailNm = inqpDatailNm;
        this.inqpDatailLink = inqpDatailLink;
        this.hmpgDatailLink = hmpgDatailLink;
    }

    public ServiceDataListVO(String servNm, String servDgst, String tgtrDtlCn, String slctCritCn, String alwServCn, String servSeDetailNm) {
        this.servNm = servNm;
        this.servDgst = servDgst;
        this.tgtrDtlCn = tgtrDtlCn;
        this.slctCritCn = slctCritCn;
        this.alwServCn = alwServCn;
        this.servSeDetailNm = servSeDetailNm;


    }

    public ServiceDataListVO(String servNm, String servDgst, String tgtrDtlCn, String slctCritCn, String alwServCn) {
        this.servNm = servNm;
        this.servDgst = servDgst;
        this.tgtrDtlCn = tgtrDtlCn;
        this.slctCritCn = slctCritCn;
        this.alwServCn = alwServCn;
    }

    public void setHmpgDatailLink(String hmpgDatailLink) {
        this.hmpgDatailLink = hmpgDatailLink;
    }

    public String getInqpDatailNm() {
        return inqpDatailNm;
    }

    public void setInqpDatailNm(String inqpDatailNm) {
        this.inqpDatailNm = inqpDatailNm;
    }

    public String getInqpDatailLink() {
        return inqpDatailLink;
    }

    public void setInqpDatailLink(String inqpDatailLink) {
        this.inqpDatailLink = inqpDatailLink;
    }

    public ServiceDataListVO() {
    }



    public String getServDgst() {
        return servDgst;
    }

    public void setServDgst(String servDgst) {
        this.servDgst = servDgst;
    }

    public String getServNm() {
        return servNm;
    }

    public void setServNm(String servNm) {
        this.servNm = servNm;
    }

    public String getTgtrDtlCn() {
        return tgtrDtlCn;
    }

    public void setTgtrDtlCn(String tgtrDtlCn) {
        this.tgtrDtlCn = tgtrDtlCn;
    }

    public String getSlctCritCn() {
        return slctCritCn;
    }

    public void setSlctCritCn(String slctCritCn) {
        this.slctCritCn = slctCritCn;
    }

    public String getAlwServCn() {
        return alwServCn;
    }

    public void setAlwServCn(String alwServCn) {
        this.alwServCn = alwServCn;
    }

    public String getServSeDetailNm() {
        return servSeDetailNm;
    }

    public void setServSeDetailNm(String servSeDetailNm) {
        this.servSeDetailNm = servSeDetailNm;
    }


    @Override
    public String toString() {
        return "ServiceDataListVO{" +
                "servNm='" + servNm + '\'' +
                ", servDgst='" + servDgst + '\'' +
                ", tgtrDtlCn='" + tgtrDtlCn + '\'' +
                ", slctCritCn='" + slctCritCn + '\'' +
                ", alwServCn='" + alwServCn + '\'' +
                ", servSeDetailNm='" + servSeDetailNm + '\'' +
                ", inqpDatailNm='" + inqpDatailNm + '\'' +
                ", inqpDatailLink='" + inqpDatailLink + '\'' +
                ", hmpgDatailLink='" + hmpgDatailLink + '\'' +
                '}';
    }
}
