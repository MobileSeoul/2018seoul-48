package com.biz.woojjujju.models;

import java.io.Serializable;

public class GoDataListVO implements Serializable {

    private String servId;       //	서비스ID
    private String servNm;       //	서비스명
    private String jurMnofNm;    //	소관부처명
    private String jurOrgNm;     //	소관조직명
    private String inqNum;       //	조회수
    private String servDgst;     //	서비스 요약
    private String servDtlLink;  //	서비스상세링크
    private String svcfrstRegTs; //	서비스등록일

    public GoDataListVO() {
    }

    public GoDataListVO(String servId, String servNm, String jurMnofNm, String jurOrgNm, String inqNum, String servDgst, String servDtlLink, String svcfrstRegTs) {
        this.servId = servId;
        this.servNm = servNm;
        this.jurMnofNm = jurMnofNm;
        this.jurOrgNm = jurOrgNm;
        this.inqNum = inqNum;
        this.servDgst = servDgst;
        this.servDtlLink = servDtlLink;
        this.svcfrstRegTs = svcfrstRegTs;
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    public String getServNm() {
        return servNm;
    }

    public void setServNm(String servNm) {
        this.servNm = servNm;
    }

    public String getJurMnofNm() {
        return jurMnofNm;
    }

    public void setJurMnofNm(String jurMnofNm) {
        this.jurMnofNm = jurMnofNm;
    }

    public String getJurOrgNm() {
        return jurOrgNm;
    }

    public void setJurOrgNm(String jurOrgNm) {
        this.jurOrgNm = jurOrgNm;
    }

    public String getInqNum() {
        return inqNum;
    }

    public void setInqNum(String inqNum) {
        this.inqNum = inqNum;
    }

    public String getServDgst() {
        return servDgst;
    }

    public void setServDgst(String servDgst) {
        this.servDgst = servDgst;
    }

    public String getServDtlLink() {
        return servDtlLink;
    }

    public void setServDtlLink(String servDtlLink) {
        this.servDtlLink = servDtlLink;
    }

    public String getSvcfrstRegTs() {
        return svcfrstRegTs;
    }

    public void setSvcfrstRegTs(String svcfrstRegTs) {
        this.svcfrstRegTs = svcfrstRegTs;
    }
}
