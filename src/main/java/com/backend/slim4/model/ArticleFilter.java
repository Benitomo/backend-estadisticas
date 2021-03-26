
package com.backend.slim4.model;

public class ArticleFilter {
    
  int controlid;
  String warehousecode;
  String articlecode;

    public int getControlid() {
        return controlid;
    }

    public void setControlid(int controlID) {
        this.controlid = controlID;
    }

    public String getWarehousecode() {
        return warehousecode;
    }

    public void setWarehousecode(String warehouse_code) {
        this.warehousecode = warehouse_code;
    }

    public String getArticlecode() {
        return articlecode;
    }

    public void setArticlecode(String article_code) {
        this.articlecode = article_code;
    }
  
  
    
}
