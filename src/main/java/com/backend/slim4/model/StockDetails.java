
package com.backend.slim4.model;

import java.math.BigDecimal;
import java.sql.Date;


public class StockDetails {
    int controlId;
    String warehouse;
    String code;
    int stockOnHand;
    String stockID;
    String stockType;
    int excludeSetting;
    String excludeTillDate;
    String excludeFromDate;
    BigDecimal initialShelfLife;
    BigDecimal remainingShelfLife;
    String uD1;
    String uD2;
    String uD3;
    String uD4;

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlId) {
        this.controlId = controlId;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStockOnHand() {
        return stockOnHand;
    }

    public void setStockOnHand(int stockOnHand) {
        this.stockOnHand = stockOnHand;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public int getExcludeSetting() {
        return excludeSetting;
    }

    public void setExcludeSetting(int excludeSetting) {
        this.excludeSetting = excludeSetting;
    }

    public String getExcludeTillDate() {
        return excludeTillDate;
    }

    public void setExcludeTillDate(String excludeTillDate) {
        this.excludeTillDate = excludeTillDate;
    }

    public String getExcludeFromDate() {
        return excludeFromDate;
    }

    public void setExcludeFromDate(String excludeFromDate) {
        this.excludeFromDate = excludeFromDate;
    }

    public BigDecimal getInitialShelfLife() {
        return initialShelfLife;
    }

    public void setInitialShelfLife(BigDecimal initialShelfLife) {
        this.initialShelfLife = initialShelfLife;
    }

    public BigDecimal getRemainingShelfLife() {
        return remainingShelfLife;
    }

    public void setRemainingShelfLife(BigDecimal remainingShelfLife) {
        this.remainingShelfLife = remainingShelfLife;
    }

    public String getuD1() {
        return uD1;
    }

    public void setuD1(String uD1) {
        this.uD1 = uD1;
    }

    public String getuD2() {
        return uD2;
    }

    public void setuD2(String uD2) {
        this.uD2 = uD2;
    }

    public String getuD3() {
        return uD3;
    }

    public void setuD3(String uD3) {
        this.uD3 = uD3;
    }

    public String getuD4() {
        return uD4;
    }

    public void setuD4(String uD4) {
        this.uD4 = uD4;
    }
    
    
    
}
