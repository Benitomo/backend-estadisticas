
package com.backend.slim4.model;

import java.math.BigDecimal;
import java.sql.Date;

public class BillOfMaterial {
    int controlId;
    String code;
    String componentArticleCode;
    BigDecimal quantity;
    String bomID;
    String lineNumber;
    BigDecimal leadTime;
    Date fromDate;
    Date toDate;
    String bomType;
    int exceptionLevel;

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlId) {
        this.controlId = controlId;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComponentArticleCode() {
        return componentArticleCode;
    }

    public void setComponentArticleCode(String componentArticleCode) {
        this.componentArticleCode = componentArticleCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getBomID() {
        return bomID;
    }

    public void setBomID(String bomID) {
        this.bomID = bomID;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public BigDecimal getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(BigDecimal leadTime) {
        this.leadTime = leadTime;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getBomType() {
        return bomType;
    }

    public void setBomType(String bomType) {
        this.bomType = bomType;
    }

    public int getExceptionLevel() {
        return exceptionLevel;
    }

    public void setExceptionLevel(int exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }
    
    
    
}
