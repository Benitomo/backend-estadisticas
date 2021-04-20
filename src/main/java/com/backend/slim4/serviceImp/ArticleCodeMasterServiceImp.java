
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ArticleCodeMaster;
import com.backend.slim4.service.ArticleCodeMasterService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
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
                    + "audfield23"
                    + "audfield24"
                    + "audfield25"
                    + "audfield26"
                    + "audfield27"
                    + "audfield28"
                    + "audfield29"
                    + "audfield30"
                    + "audfield31"
                    + "audfield32"
                    + "audfield33"
                    + "audfield34"
                    + "audfield35"
                    + "audfield36"
                    + "audfield37"
                    + "audfield38"
                    + "audfield38"
                    + "audfield39"
                    + "audfield40"
                    + "audfield41"
                    + "audfield42"
                    + "audfield43"
                    + "audfield44"
                    + "audfield45"
                    + "audfield46"
                    + "audfield47"
                    + "audfield48"
                    + "audfield49"
                    + "audfield50"
                    + "audfield51"
                    + "audfield52"
                    + "audfield53"
                    + "audfield54"
                    + "audfield55"
                    + "audfield56"
                    + "audfield57"
                    + "audfield58"
                    + "audfield59"
                    + "audfield60"
                    + "audfield61"
                    + "audfield62"
                    + "audfield63"
                    + "audfield64"
                    + "audfield65"
                    + "audfield66"
                    + "audfield67"
                    + "audfield68"
                    + "audfield69"
                    + "audfield70"
                    + "audfield71"
                    + "audfield72"
                    + "audfield73"
                    + "audfield74"
                    + "audfield75"
                    + "audfield76"
                    + "audfield77"
                    + "audfield78"
                    + "audfield79"
                    + "audfield80"
                    + "audfield81"
                    + "audfield82"
                    + "audfield83"
                    + "audfield84"
                    + "audfield85"
                    + "audfield86"
                    + "audfield87"
                    + "audfield88"
                    + "audfield89"
                    + "audfield90"
                    + "audfield91"
                    + "audfield92"
                    + "audfield93"
                    + "audfield94"
                    + "audfield95"
                    + "audfield96"
                    + "audfield97"
                    + "audfield98"
                    + "audfield99"
                    + "audfield100"
                    + "from articlecodemaster";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArticleCodeMaster m = new ArticleCodeMaster();
                m.setControlId(rs.getInt("controlid"));
                m.setWarehousecode(rs.getString("warehouse"));
                m.setCode(rs.getString("articlecode"));
                m.setCreationDate(rs.getDate("creationdate"));
                m.setDescription(rs.getString("description"));
                m.setUnitPrice(rs.getBigDecimal("unitprice"));
                m.setSalesPrice(rs.getBigDecimal("salesprice"));
                m.setCriterium1(rs.getBigDecimal("criterium1"));
                m.setCriterium1(rs.getBigDecimal("criterium2"));
                m.setCriterium1(rs.getBigDecimal("criterium3"));
                m.setCriterium1(rs.getBigDecimal("criterium4"));
                m.setGroupCode1(rs.getString("groupcode1"));
                m.setGroupCode1(rs.getString("groupcode2"));
                m.setGroupCode1(rs.getString("groupcode3"));
                m.setGroupCode1(rs.getString("groupcode4"));
                m.setGroupCode1(rs.getString("groupcode5"));
                m.setGroupCode1(rs.getString("groupcode6"));
                m.setuD1(rs.getString("ud1"));
                m.setuD1(rs.getString("ud2"));
                m.setuD1(rs.getString("ud3"));
                m.setuD1(rs.getString("ud4"));
                m.setuD1(rs.getString("ud5"));
                m.setuD1(rs.getString("ud6"));
                m.setuD1(rs.getString("ud7"));
                m.setuD1(rs.getString("ud8"));
                m.setuD1(rs.getString("ud9"));
                m.setuD1(rs.getString("ud10"));
                m.setuD1(rs.getString("ud11"));
                m.setuD1(rs.getString("ud12"));
                m.setuD1(rs.getString("ud13"));
                m.setuD1(rs.getString("ud14"));
                m.setuD1(rs.getString("ud15"));
                m.setuD1(rs.getString("audfield1"));
                m.setuD1(rs.getString("audfield2"));
                m.setuD1(rs.getString("audfield3"));
                m.setuD1(rs.getString("audfield4"));
                m.setuD1(rs.getString("audfield5"));
                m.setuD1(rs.getString("audfield6"));
                m.setuD1(rs.getString("audfield7"));
                m.setuD1(rs.getString("audfield8"));
                m.setuD1(rs.getString("audfield9"));
                m.setuD1(rs.getString("audfield10"));
                m.setuD1(rs.getString("audfield11"));
                m.setuD1(rs.getString("audfield12"));
                m.setuD1(rs.getString("audfield13"));
                m.setuD1(rs.getString("audfield14"));
                m.setuD1(rs.getString("audfield15"));
                m.setuD1(rs.getString("audfield16"));
                m.setuD1(rs.getString("audfield17"));
                m.setuD1(rs.getString("audfield18"));
                m.setuD1(rs.getString("audfield19"));
                m.setuD1(rs.getString("audfield20"));
                m.setuD1(rs.getString("audfield21"));
                m.setuD1(rs.getString("audfield22"));
                m.setuD1(rs.getString("audfield23"));
                m.setuD1(rs.getString("audfield23"));
                m.setuD1(rs.getString("audfield24"));
                m.setuD1(rs.getString("audfield25"));
                m.setuD1(rs.getString("audfield26"));
                m.setuD1(rs.getString("audfield27"));
                m.setuD1(rs.getString("audfield28"));
                m.setuD1(rs.getString("audfield29"));
                m.setuD1(rs.getString("audfield30"));
                m.setuD1(rs.getString("audfield31"));
                m.setuD1(rs.getString("audfield32"));
                m.setuD1(rs.getString("audfield33"));
                m.setuD1(rs.getString("audfield34"));
                m.setuD1(rs.getString("audfield35"));
                m.setuD1(rs.getString("audfield36"));
                m.setuD1(rs.getString("audfield37"));
                m.setuD1(rs.getString("audfield38"));
                m.setuD1(rs.getString("audfield39"));
                m.setuD1(rs.getString("audfield40"));
                m.setuD1(rs.getString("audfield41"));
                m.setuD1(rs.getString("audfield42"));
                m.setuD1(rs.getString("audfield43"));
                m.setuD1(rs.getString("audfield44"));
                m.setuD1(rs.getString("audfield45"));
                m.setuD1(rs.getString("audfield46"));
                m.setuD1(rs.getString("audfield47"));
                m.setuD1(rs.getString("audfield48"));
                m.setuD1(rs.getString("audfield49"));
                m.setuD1(rs.getString("audfield50"));
                m.setuD1(rs.getString("audfield51"));
                m.setuD1(rs.getString("audfield52"));
                m.setuD1(rs.getString("audfield53"));
                m.setuD1(rs.getString("audfield54"));
                m.setuD1(rs.getString("audfield55"));
                m.setuD1(rs.getString("audfield56"));
                m.setuD1(rs.getString("audfield57"));
                m.setuD1(rs.getString("audfield58"));
                m.setuD1(rs.getString("audfield59"));
                m.setuD1(rs.getString("audfield60"));
                m.setuD1(rs.getString("audfield61"));
                m.setuD1(rs.getString("audfield62"));
                m.setuD1(rs.getString("audfield63"));
                m.setuD1(rs.getString("audfield64"));
                m.setuD1(rs.getString("audfield65"));
                m.setuD1(rs.getString("audfield66"));
                m.setuD1(rs.getString("audfield67"));
                m.setuD1(rs.getString("audfield68"));
                m.setuD1(rs.getString("audfield69"));
                m.setuD1(rs.getString("audfield70"));
                m.setuD1(rs.getString("audfield71"));
                m.setuD1(rs.getString("audfield72"));
                m.setuD1(rs.getString("audfield73"));
                m.setuD1(rs.getString("audfield74"));
                m.setuD1(rs.getString("audfield75"));
                m.setuD1(rs.getString("audfield76"));
                m.setuD1(rs.getString("audfield77"));
                m.setuD1(rs.getString("audfield78"));
                m.setuD1(rs.getString("audfield79"));
                m.setuD1(rs.getString("audfield80"));
                m.setuD1(rs.getString("audfield81"));
                m.setuD1(rs.getString("audfield82"));
                m.setuD1(rs.getString("audfield83"));
                m.setuD1(rs.getString("audfield84"));
                m.setuD1(rs.getString("audfield85"));
                m.setuD1(rs.getString("audfield86"));
                m.setuD1(rs.getString("audfield87"));
                m.setuD1(rs.getString("audfield88"));
                m.setuD1(rs.getString("audfield89"));
                m.setuD1(rs.getString("audfield90"));
                m.setuD1(rs.getString("audfield91"));
                m.setuD1(rs.getString("audfield92"));
                m.setuD1(rs.getString("audfield93"));
                m.setuD1(rs.getString("audfield94"));
                m.setuD1(rs.getString("audfield95"));
                m.setuD1(rs.getString("audfield96"));
                m.setuD1(rs.getString("audfield97"));
                m.setuD1(rs.getString("audfield98"));
                m.setuD1(rs.getString("audfield99"));
                m.setuD1(rs.getString("audfield100"));
                master.add(m);
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleCodeMasterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(master.size()>0){
            try {
                return transactionsInsert(master);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ArticleCodeMasterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            tituloResp = "Error";
            mensajeResp = "La tabla transactions está vacía o hay inconvenientes de columnas";
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    } 
    
    public ResponseEntity transactionsInsert(ArrayList<ArticleCodeMaster> t) throws ClassNotFoundException {
        String tituloResp = "";
        String mensajeResp = "";
        try {
            Connection cnt = GetConnection.sqlServer();
            Statement stmt = cnt.createStatement();
            int r = emptyTable(stmt);
            if(r>=0){
            insertDataTable(stmt,t);
            tituloResp = "Éxito";
            mensajeResp = "se ejecutó la interface ArticleCodeMaster correctamente!";
            }
            else{
            tituloResp = "Error";
            mensajeResp = "No se pudo eliminar los registros de la tabla S4Import_ArticleCodeMaster previo a realizar la inserción.";
            }
            cnt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ArticleCodeMasterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject item = new JSONObject();
        item.put(tituloResp, mensajeResp);
        return new ResponseEntity(item, HttpStatus.OK);
        
    }
    
    public int emptyTable(Statement stmt){
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_ArticleCodeMaster]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleCodeMasterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int insertDataTable(Statement stmt, ArrayList<ArticleCodeMaster> t) throws SQLException{
        
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleCodeMaster]"
                + "("
                + "controlId,"
                + "transactionNumber,"
                + "transactionType,"
                + "transactionName,"
                + "transactionStatus,"
                + "warehouse,"
                + "code,"
                + "issueDate,"
                + "confirmedDate,"
                + "requestedDate,"
                + "issueQuantity,"
                + "lineNumber,"
                + "confirmedQuantity,"
                + "requestedQuantity,"
                + "customerNumber,"
                + "customerType,"
                + "customerName, "
                + "salesPrice, "
                + "deliveryLocation, "
                + "supplierNumber, "
                + "supplierType,"
                + "supplierName,"
                + "buyingPrice,"
                + "supplyingLocation,"
                + "conversionFactor,"
                + "uD1,"
                + "uD2,"
                + "uD3,"
                + "uD4,"
                + "issueTime"
                + ")"
                + "SELECT * FROM  (VALUES";
            for (int i = 0; i < t.size(); i++) {
                sql =  (i==t.size()-1)? 
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'" + t.get(i).getWarehousecode()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getCreationDate()+ "', "
                        + "'" + t.get(i).getDescription()+ "', "
                        + ""  + t.get(i).getUnitPrice()+ ", "
                        + ""  + t.get(i).getSalesPrice()+ ", "
                        + ""  + t.get(i).getCriterium1()+ ", "
                        + ""  + t.get(i).getCriterium2()+ ", "
                        + ""  + t.get(i).getCriterium3()+ ", "
                        + ""  + t.get(i).getCriterium4()+ ", "
                        + "'" + t.get(i).getGroupCode1()+ "', "
                        + "'" + t.get(i).getGroupCode2()+ "', "
                        + "'" + t.get(i).getGroupCode3()+ "', "
                        + "'" + t.get(i).getGroupCode4()+ "', "
                        + "'" + t.get(i).getGroupCode5()+ "', "
                        + "'" + t.get(i).getGroupCode6()+ "', "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getuD5()+ "', "
                        + "'" + t.get(i).getuD6()+ "', "
                        + "'" + t.get(i).getuD7()+ "', "
                        + "'" + t.get(i).getuD8()+ "', "
                        + "'" + t.get(i).getuD9()+ "', "
                        + "'" + t.get(i).getuD10()+ "', "
                        + "'" + t.get(i).getuD11()+ "', "
                        + "'" + t.get(i).getuD12()+ "', "
                        + "'" + t.get(i).getuD13()+ "', "
                        + "'" + t.get(i).getuD14()+ "', "
                        + "'" + t.get(i).getuD15()+ "', "
                        + "'" + t.get(i).getaUDField1()+ "', "
                        + "'" + t.get(i).getaUDField2()+ "', "
                        + "'" + t.get(i).getaUDField3()+ "', "
                        + "'" + t.get(i).getaUDField4()+ "', "
                        + "'" + t.get(i).getaUDField5()+ "', "
                        + "'" + t.get(i).getaUDField6()+ "', "
                        + "'" + t.get(i).getaUDField7()+ "', "
                        + "'" + t.get(i).getaUDField8()+ "', "
                        + "'" + t.get(i).getaUDField9()+ "', "
                        + "'" + t.get(i).getaUDField10()+ "', "
                        + "'" + t.get(i).getaUDField11()+ "', "
                        + "'" + t.get(i).getaUDField12()+ "', "
                        + "'" + t.get(i).getaUDField13()+ "', "
                        + "'" + t.get(i).getaUDField14()+ "', "
                        + "'" + t.get(i).getaUDField15()+ "', "
                        + "'" + t.get(i).getaUDField16()+ "', "
                        + "'" + t.get(i).getaUDField17()+ "', "
                        + "'" + t.get(i).getaUDField18()+ "', "
                        + "'" + t.get(i).getaUDField19()+ "', "
                        + "'" + t.get(i).getaUDField20()+ "', "
                        + "'" + t.get(i).getaUDField21()+ "', "
                        + "'" + t.get(i).getaUDField22()+ "', "
                        + "'" + t.get(i).getaUDField23()+ "', "
                        + "'" + t.get(i).getaUDField24()+ "', "
                        + "'" + t.get(i).getaUDField25()+ "', "
                        + "'" + t.get(i).getaUDField26()+ "', "
                        + "'" + t.get(i).getaUDField27()+ "', "
                        + "'" + t.get(i).getaUDField28()+ "', "
                        + "'" + t.get(i).getaUDField29()+ "', "
                        + "'" + t.get(i).getaUDField30()+ "', "
                        + "'" + t.get(i).getaUDField31()+ "', "
                        + "'" + t.get(i).getaUDField32()+ "', "
                        + "'" + t.get(i).getaUDField33()+ "', "
                        + "'" + t.get(i).getaUDField34()+ "', "
                        + "'" + t.get(i).getaUDField35()+ "', "
                        + "'" + t.get(i).getaUDField36()+ "', "
                        + "'" + t.get(i).getaUDField37()+ "', "
                        + "'" + t.get(i).getaUDField38()+ "', "
                        + "'" + t.get(i).getaUDField39()+ "', "
                        + "'" + t.get(i).getaUDField40()+ "', "
                        + "'" + t.get(i).getaUDField41()+ "', "
                        + "'" + t.get(i).getaUDField42()+ "', "
                        + "'" + t.get(i).getaUDField43()+ "', "
                        + "'" + t.get(i).getaUDField44()+ "', "
                        + "'" + t.get(i).getaUDField45()+ "', "
                        + "'" + t.get(i).getaUDField46()+ "', "
                        + "'" + t.get(i).getaUDField47()+ "', "
                        + "'" + t.get(i).getaUDField48()+ "', "
                        + "'" + t.get(i).getaUDField49()+ "', "
                        + "'" + t.get(i).getaUDField50()+ "', "
                        + "'" + t.get(i).getaUDField51()+ "', "
                        + "'" + t.get(i).getaUDField52()+ "', "
                        + "'" + t.get(i).getaUDField53()+ "', "
                        + "'" + t.get(i).getaUDField54()+ "', "
                        + "'" + t.get(i).getaUDField55()+ "', "
                        + "'" + t.get(i).getaUDField56()+ "', "
                        + "'" + t.get(i).getaUDField57()+ "', "
                        + "'" + t.get(i).getaUDField58()+ "', "
                        + "'" + t.get(i).getaUDField59()+ "', "
                        + "'" + t.get(i).getaUDField60()+ "', "
                        + "'" + t.get(i).getaUDField61()+ "', "
                        + "'" + t.get(i).getaUDField62()+ "', "
                        + "'" + t.get(i).getaUDField63()+ "', "
                        + "'" + t.get(i).getaUDField64()+ "', "
                        + "'" + t.get(i).getaUDField65()+ "', "
                        + "'" + t.get(i).getaUDField66()+ "', "
                        + "'" + t.get(i).getaUDField67()+ "', "
                        + "'" + t.get(i).getaUDField68()+ "', "
                        + "'" + t.get(i).getaUDField69()+ "', "
                        + "'" + t.get(i).getaUDField70()+ "', "
                        + "'" + t.get(i).getaUDField71()+ "', "
                        + "'" + t.get(i).getaUDField72()+ "', "
                        + "'" + t.get(i).getaUDField73()+ "', "
                        + "'" + t.get(i).getaUDField74()+ "', "
                        + "'" + t.get(i).getaUDField75()+ "', "
                        + "'" + t.get(i).getaUDField76()+ "', "
                        + "'" + t.get(i).getaUDField77()+ "', "
                        + "'" + t.get(i).getaUDField78()+ "', "
                        + "'" + t.get(i).getaUDField79()+ "', "
                        + "'" + t.get(i).getaUDField80()+ "', "
                        + "'" + t.get(i).getaUDField81()+ "', "
                        + "'" + t.get(i).getaUDField82()+ "', "
                        + "'" + t.get(i).getaUDField83()+ "', "
                        + "'" + t.get(i).getaUDField84()+ "', "
                        + "'" + t.get(i).getaUDField85()+ "', "
                        + "'" + t.get(i).getaUDField86()+ "', "
                        + "'" + t.get(i).getaUDField87()+ "', "
                        + "'" + t.get(i).getaUDField88()+ "', "
                        + "'" + t.get(i).getaUDField89()+ "', "
                        + "'" + t.get(i).getaUDField90()+ "', "
                        + "'" + t.get(i).getaUDField91()+ "', "
                        + "'" + t.get(i).getaUDField92()+ "', "
                        + "'" + t.get(i).getaUDField93()+ "', "
                        + "'" + t.get(i).getaUDField94()+ "', "
                        + "'" + t.get(i).getaUDField95()+ "', "
                        + "'" + t.get(i).getaUDField96()+ "', "
                        + "'" + t.get(i).getaUDField97()+ "', "
                        + "'" + t.get(i).getaUDField98()+ "', "
                        + "'" + t.get(i).getaUDField99()+ "', "
                        + "'" + t.get(i).getaUDField100()+ "', "
                        + ")) as temporal"
                        + "("
                            + "controlId,"
                            + "transactionNumber,"
                            + "transactionType,"
                            + "transactionName,"
                            + "transactionStatus,"
                            + "warehouse,"
                            + "code,"
                            + "issueDate,"
                            + "confirmedDate,"
                            + "requestedDate,"
                            + "issueQuantity,"
                            + "lineNumber,"
                            + "confirmedQuantity,"
                            + "requestedQuantity,"
                            + "customerNumber,"
                            + "customerType,"
                            + "customerName, "
                            + "salesPrice, "
                            + "deliveryLocation, "
                            + "supplierNumber, "
                            + "supplierType,"
                            + "supplierName,"
                            + "buyingPrice,"
                            + "supplyingLocation,"
                            + "conversionFactor,"
                            + "uD1,"
                            + "uD2,"
                            + "uD3,"
                            + "uD4,"
                            + "issueTime"
                            + ")"
                :
                        sql + "(" + t.get(i).getControlId() + ", "
                        + "'"+  t.get(i).getTransactionNumber()+"', "
                        + "'" + t.get(i).getTransactionType() + "', "
                        + "'" + t.get(i).getTransactionName()+ "', "
                        + "'" + t.get(i).getTransactionStatus()+ "', "
                        + "'" + t.get(i).getWarehouse()+ "', "
                        + "'" + t.get(i).getCode()+ "', "
                        + "'" + t.get(i).getIssueDate()+ "', "
                        + "'" + t.get(i).getConfirmedDate()+ "', "
                        + "'" + t.get(i).getRequestedDate()+ "', "
                        + ""  + t.get(i).getIssueQuantity()+ ", "
                        + "'" + t.get(i).getLineNumber()+ "', "
                        + ""  + t.get(i).getConfirmedQuantity()+ ", "
                        + ""  + t.get(i).getRequestedQuantity()+ ", "
                        + "'" + t.get(i).getCustomerNumber()+ "', "
                        + "'" + t.get(i).getCustomerType() + "', "
                        + "'" + t.get(i).getCustomerName()+ "', "
                        + ""  + t.get(i).getSalesPrice()+ ", "
                        + "'" + t.get(i).getDeliveryLocation()+ "', "
                        + "'" + t.get(i).getSupplierNumber()+ "', "
                        + "'" + t.get(i).getSupplierType()+ "', "
                        + "'" + t.get(i).getSupplierName()+ "',"
                        + ""  + t.get(i).getBuyingPrice()+ ", "
                        + "'" + t.get(i).getSupplyingLocation()+ "', "
                        + "" +  t.get(i).getConversionFactor()+ ", "
                        + "'" + t.get(i).getuD1()+ "', "
                        + "'" + t.get(i).getuD2()+ "', "
                        + "'" + t.get(i).getuD3()+ "', "
                        + "'" + t.get(i).getuD4()+ "', "
                        + "'" + t.get(i).getTransactionIssueTime()+ "'), ";
                        
            }
        return stmt.executeUpdate(sql);
    } 
}
