
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ConfirmedDemand;
import com.backend.slim4.service.ConfirmedDemandService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConfirmedDemandServiceImp implements ConfirmedDemandService{
    @Override
    public ResponseEntity confirmedDemandedSelect() {
    String tituloResp  = "";
    String mensajeResp = "";
    ArrayList<ConfirmedDemand> confirmed = new ArrayList<>();
        try {
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            String sql = "SELECT first 70000 * from confirmeddemand";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ConfirmedDemand po = new ConfirmedDemand();
                po.setControlId(rs.getInt("controlid"));
                po.setWarehousecode(rs.getString("warehousecode"));
                po.setCode(rs.getString("articlecode"));
                po.setNumber(rs.getString("numbers"));
                po.setComment(rs.getString("comments"));
                po.setOriginalQuantity(rs.getInt("originalquantity"));
                po.setSuppliedQuantity(rs.getInt("suppliedquantity"));
                po.setFreeText1(rs.getString("freetext1"));
                po.setFreeText2(rs.getString("freetext2"));
                po.setFreeNumber1(rs.getBigDecimal("freenumber1"));
                po.setFreeNumber2(rs.getBigDecimal("freenumber2"));
                confirmed.add(po);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }
}
