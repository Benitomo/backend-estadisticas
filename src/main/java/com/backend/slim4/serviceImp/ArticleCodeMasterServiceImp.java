
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ArticleCodeMaster;
import com.backend.slim4.service.ArticleCodeMasterService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArticleCodeMasterServiceImp implements ArticleCodeMasterService{
    @Override
    public ResponseEntity articleCodeMasterSelect() {
        String tituloResp  = "";
        String mensajeResp = "";
        ArrayList<ArticleCodeMaster> master = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 1000"
                    + "controlid,"
                    + "TRIM(warehouse) as warehousecode,"
                    + "TRIM(articlecode) as articlecode,"
                    + "creationdate,"
                    + "description,"
                    + "unitprice,"
                    + "salesprice,"
                    + "criterium1,"
                    + "criterium2,"
                    + "criterium3,"
                    + "criterium4,"
                    + "groupcode1,"
                    + "groupcode2,"
                    + "groupcode3,"
                    + "groupcode4,"
                    + "groupcode5,"
                    + "groupcode6,"
                    + "ud1,"
                    + "ud2,"
                    + "ud3,"
                    + "ud4,"
                    + "ud5,"
                    + "ud6,"
                    + "ud7,"
                    + "ud8,"
                    + "ud9,"
                    + "ud10,"
                    + "ud11,"
                    + "ud12,"
                    + "ud13,"
                    + "ud14,"
                    + "ud15,"
                    + "audfield1"
                    + "audfield2"
                    + "audfield3"
                    + "audfield4"
                    + "audfield5"
                    + "audfield6"
                    + "audfield7"
                    + "audfield8"
                    + "audfield9"
                    + "audfield10"
                    + "audfield11"
                    + "audfield12"
                    + "audfield13"
                    + "audfield14"
                    + "audfield15"
                    + "audfield16"
                    + "audfield17"
                    + "audfield18"
                    + "audfield19"
                    + "audfield20"
                    + "audfield21"
                    + "audfield22"
                    + "from stockdetails";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArticleCodeMaster m = new ArticleCodeMaster();
                m.setControlId(rs.getInt("controlid"));
                m.setTransactionNumber(rs.getString("transactionnumber"));
                m.setTransactionType(rs.getString("transactiontype"));
                m.setTransactionName(rs.getString("transactionname"));
                m.setTransactionName(rs.getString("transactionstatus"));
                m.setWarehouse(rs.getString("warehouse"));
                m.setCode(rs.getString("articlecode"));
                m.setIssueDate(rs.getDate("issuedate"));
                m.setConfirmedDate(rs.getDate("confirmeddate"));
                m.setRequestedDate(rs.getDate("requesteddate"));
                m.setIssueQuantity(rs.getBigDecimal("issuequantity"));
                m.setLineNumber(rs.getString("linenumber"));
                m.setConfirmedQuantity(rs.getBigDecimal("confirmedquantity"));
                m.setRequestedQuantity(rs.getBigDecimal("requestedquantity"));
                m.setCustomerNumber(rs.getString("customernumber"));
                m.setCustomerType(rs.getString("customertype"));
                m.setCustomerName(rs.getString("customername"));
                m.setSalesPrice(rs.getBigDecimal("salesprice"));
                m.setDeliveryLocation(rs.getString("deliverylocation"));
                m.setSupplierNumber(rs.getString("suppliernumber"));
                m.setSupplierType(rs.getString("suppliertype"));
                m.setSupplierName(rs.getString("suppliername"));
                m.setBuyingPrice(rs.getBigDecimal("buyingprice"));
                m.setSupplyingLocation(rs.getString("supplyinglocation"));
                m.setConversionFactor(rs.getBigDecimal("conversionfactor"));
                m.setuD1(rs.getString("ud1"));
                m.setuD1(rs.getString("ud2"));
                m.setuD1(rs.getString("ud3"));
                m.setuD1(rs.getString("ud4"));
                m.setTransactionIssueTime(rs.getString("transactionissuetime"));
                master.add(m);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    } 
}
