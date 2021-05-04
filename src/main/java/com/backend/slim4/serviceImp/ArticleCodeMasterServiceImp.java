
package com.backend.slim4.serviceImp;

import com.backend.slim4.GetConnection;
import com.backend.slim4.model.ArticleCodeMaster;
import com.backend.slim4.model.ImportControl;
import com.backend.slim4.service.ArticleCodeMasterService;
import com.backend.slim4.service.ImportControlService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArticleCodeMasterServiceImp implements ArticleCodeMasterService{
    @Autowired
    ImportControlService control_service;
    // Variable límite de registros
    private static final int REGISTROS_BATCH = 1000;
    // ID de la interface
    private static final int importType = 4;
    @Override
    public ResponseEntity articleCodeMasterSelect() {
        // Mensaje de respuesta
        String tituloResp  = "";
        String mensajeResp = "";
        // Prepare Stament para inserción en Sql Server, se insertará por bloques.
        String sqlPrepare = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleCodeMaster]"
                + "("
                + "controlId,"
                    + "warehouse,"
                    + "code,"
                    + "creationDate,"
                    + "description,"
                    + "unitPrice,"
                    + "salesPrice,"
                    + "criterium1,"
                    + "criterium2,"
                    + "criterium3,"
                    + "criterium4,"
                    + "groupCode1,"
                    + "groupCode2,"
                    + "groupCode3,"
                    + "groupCode4,"
                    + "groupCode5,"
                    + "groupCode6,"
                    + "uD1,"
                    + "uD2,"
                    + "uD3,"
                    + "uD4,"
                    + "uD5,"
                    + "uD6,"
                    + "uD7,"
                    + "uD8,"
                    + "uD9,"
                    + "uD10,"
                    + "uD11,"
                    + "uD12,"
                    + "uD13,"
                    + "uD14,"
                    + "uD15,"
                    + "aUDField1,"
                    + "aUDField2,"
                    + "aUDField3,"
                    + "aUDField4,"
                    + "aUDField5,"
                    + "aUDField6,"
                    + "aUDField7,"
                    + "aUDField8,"
                    + "aUDField9,"
                    + "aUDField10,"
                    + "aUDField11,"
                    + "aUDField12,"
                    + "aUDField13,"
                    + "aUDField14,"
                    + "aUDField15,"
                    + "aUDField16,"
                    + "aUDField17,"
                    + "aUDField18,"
                    + "aUDField19,"
                    + "aUDField20,"
                    + "aUDField21,"
                    + "aUDField22,"
                    + "aUDField23,"
                    + "aUDField24,"
                    + "aUDField25,"
                    + "aUDField26,"
                    + "aUDField27,"
                    + "aUDField28,"
                    + "aUDField29,"
                    + "aUDField30,"
                    + "aUDField31,"
                    + "aUDField32,"
                    + "aUDField33,"
                    + "aUDField34,"
                    + "aUDField35,"
                    + "aUDField36,"
                    + "aUDField37,"
                    + "aUDField38,"
                    + "aUDField39,"
                    + "aUDField40,"
                    + "aUDField41,"
                    + "aUDField42,"
                    + "aUDField43,"
                    + "aUDField44,"
                    + "aUDField45,"
                    + "aUDField46,"
                    + "aUDField47,"
                    + "aUDField48,"
                    + "aUDField49,"
                    + "aUDField50,"
                    + "aUDField51,"
                    + "aUDField52,"
                    + "aUDField53,"
                    + "aUDField54,"
                    + "aUDField55,"
                    + "aUDField56,"
                    + "aUDField57,"
                    + "aUDField58,"
                    + "aUDField59,"
                    + "aUDField60,"
                    + "aUDField61,"
                    + "aUDField62,"
                    + "aUDField63,"
                    + "aUDField64,"
                    + "aUDField65,"
                    + "aUDField66,"
                    + "aUDField67,"
                    + "aUDField68,"
                    + "aUDField69,"
                    + "aUDField70,"
                    + "aUDField71,"
                    + "aUDField72,"
                    + "aUDField73,"
                    + "aUDField74,"
                    + "aUDField75,"
                    + "aUDField76,"
                    + "aUDField77,"
                    + "aUDField78,"
                    + "aUDField79,"
                    + "aUDField80,"
                    + "aUDField81,"
                    + "aUDField82,"
                    + "aUDField83,"
                    + "aUDField84,"
                    + "aUDField85,"
                    + "aUDField86,"
                    + "aUDField87,"
                    + "aUDField88,"
                    + "aUDField89,"
                    + "aUDField90,"
                    + "aUDField91,"
                    + "aUDField92,"
                    + "aUDField93,"
                    + "aUDField94,"
                    + "aUDField95,"
                    + "aUDField96,"
                    + "aUDField97,"
                    + "aUDField98,"
                    + "aUDField99,"
                    + "aUDField100"
                    + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
             // Informix
            Connection cnt = GetConnection.informix("slim4");
            Statement stmt = cnt.createStatement();
            // Sql Server
            Connection cnt2 = GetConnection.sqlServer();
            Statement stmt2 = cnt2.createStatement();
            // Query que trae la información de Informix
            String sql = "SELECT "
                    + "controlid,"
                    + "TRIM(warehousecode) as warehousecode,"
                    + "TRIM(articlecode) as articlecode,"
                    + "creationdate,"
                    + "TRIM(description) as description,"
                    + "unitprice,"
                    + "salesprice,"
                    + "criterium1,"
                    + "criterium2,"
                    + "criterium3,"
                    + "criterium4,"
                    + "TRIM(groupcode1) as groupcode1,"
                    + "TRIM(groupcode2) as groupcode2,"
                    + "TRIM(groupcode3) as groupcode3,"
                    + "TRIM(groupcode4) as groupcode4,"
                    + "TRIM(groupcode5) as groupcode5,"
                    + "TRIM(groupcode6) as groupcode6,"
                    + "TRIM(ud1) as ud1,"
                    + "TRIM(ud2) as ud2,"
                    + "TRIM(ud3) as ud3,"
                    + "TRIM(ud4) as ud4,"
                    + "TRIM(ud5) as ud5,"
                    + "TRIM(ud6) as ud6,"
                    + "TRIM(ud7) as ud7,"
                    + "TRIM(ud8) as ud8,"
                    + "TRIM(ud9) as ud9,"
                    + "TRIM(ud10) as ud10,"
                    + "TRIM(ud11) as ud11,"
                    + "TRIM(ud12) as ud12,"
                    + "TRIM(ud13) as ud13,"
                    + "TRIM(ud14) as ud14,"
                    + "TRIM(ud15) as ud15,"
                    + "TRIM(audfield1) as audfield1,"
                    + "TRIM(audfield2) as audfield2,"
                    + "TRIM(audfield3) as audfield3,"
                    + "TRIM(audfield4) as audfield4,"
                    + "TRIM(audfield5) as audfield5,"
                    + "TRIM(audfield6) as audfield6,"
                    + "TRIM(audfield7) as audfield7,"
                    + "TRIM(audfield8) as audfield8,"
                    + "TRIM(audfield9) as audfield9,"
                    + "TRIM(audfield10) as audfield10,"
                    + "TRIM(audfield11) as audfield11,"
                    + "TRIM(audfield12) as audfield12,"
                    + "TRIM(audfield13) as audfield13,"
                    + "TRIM(audfield14) as audfield14,"
                    + "TRIM(audfield15) as audfield15,"
                    + "TRIM(audfield16) as audfield16,"
                    + "TRIM(audfield17) as audfield17,"
                    + "TRIM(audfield18) as audfield18,"
                    + "TRIM(audfield19) as audfield19,"
                    + "TRIM(audfield20) as audfield20,"
                    + "TRIM(audfield21) as audfield21,"
                    + "TRIM(audfield22) as audfield22,"
                    + "TRIM(audfield23) as audfield23,"
                    + "TRIM(audfield24) as audfield24,"
                    + "TRIM(audfield25) as audfield25,"
                    + "TRIM(audfield26) as audfield26,"
                    + "TRIM(audfield27) as audfield27,"
                    + "TRIM(audfield28) as audfield28,"
                    + "TRIM(audfield29) as audfield29,"
                    + "TRIM(audfield30) as audfield30,"
                    + "TRIM(audfield31) as audfield31,"
                    + "TRIM(audfield32) as audfield32,"
                    + "TRIM(audfield33) as audfield33,"
                    + "TRIM(audfield34) as audfield34,"
                    + "TRIM(audfield35) as audfield35,"
                    + "TRIM(audfield36) as audfield36,"
                    + "TRIM(audfield37) as audfield37,"
                    + "TRIM(audfield38) as audfield38,"
                    + "TRIM(audfield39) as audfield39,"
                    + "TRIM(audfield40) as audfield40,"
                    + "TRIM(audfield41) as audfield41,"
                    + "TRIM(audfield42) as audfield42,"
                    + "TRIM(audfield43) as audfield43,"
                    + "TRIM(audfield44) as audfield44,"
                    + "TRIM(audfield45) as audfield45,"
                    + "TRIM(audfield46) as audfield46,"
                    + "TRIM(audfield47) as audfield47,"
                    + "TRIM(audfield48) as audfield48,"
                    + "TRIM(audfield49) as audfield49,"
                    + "TRIM(audfield50) as audfield50,"
                    + "TRIM(audfield51) as audfield51,"
                    + "TRIM(audfield52) as audfield52,"
                    + "TRIM(audfield53) as audfield53,"
                    + "TRIM(audfield54) as audfield54,"
                    + "TRIM(audfield55) as audfield55,"
                    + "TRIM(audfield56) as audfield56,"
                    + "TRIM(audfield57) as audfield57,"
                    + "TRIM(audfield58) as audfield58,"
                    + "TRIM(audfield59) as audfield59,"
                    + "TRIM(audfield60) as audfield60,"
                    + "TRIM(audfield61) as audfield61,"
                    + "TRIM(audfield62) as audfield62,"
                    + "TRIM(audfield63) as audfield63,"
                    + "TRIM(audfield64) as audfield64,"
                    + "TRIM(audfield65) as audfield65,"
                    + "TRIM(audfield66) as audfield66,"
                    + "TRIM(audfield67) as audfield67,"
                    + "TRIM(audfield68) as audfield68,"
                    + "TRIM(audfield69) as audfield69,"
                    + "TRIM(audfield70) as audfield70,"
                    + "TRIM(audfield71) as audfield71,"
                    + "TRIM(audfield72) as audfield72,"
                    + "TRIM(audfield73) as audfield73,"
                    + "TRIM(audfield74) as audfield74,"
                    + "TRIM(audfield75) as audfield75,"
                    + "TRIM(audfield76) as audfield76,"
                    + "TRIM(audfield77) as audfield77,"
                    + "TRIM(audfield78) as audfield78,"
                    + "TRIM(audfield79) as audfield79,"
                    + "TRIM(audfield80) as audfield80,"
                    + "TRIM(audfield81) as audfield81,"
                    + "TRIM(audfield82) as audfield82,"
                    + "TRIM(audfield83) as audfield83,"
                    + "TRIM(audfield84) as audfield84,"
                    + "TRIM(audfield85) as audfield85,"
                    + "TRIM(audfield86) as audfield86,"
                    + "TRIM(audfield87) as audfield87,"
                    + "TRIM(audfield88) as audfield88,"
                    + "TRIM(audfield89) as audfield89,"
                    + "TRIM(audfield90) as audfield90,"
                    + "TRIM(audfield91) as audfield91,"
                    + "TRIM(audfield92) as audfield92,"
                    + "TRIM(audfield93) as audfield93,"
                    + "TRIM(audfield94) as audfield94,"
                    + "TRIM(audfield95) as audfield95,"
                    + "TRIM(audfield96) as audfield96,"
                    + "TRIM(audfield97) as audfield97,"
                    + "TRIM(audfield98) as audfield98,"
                    + "TRIM(audfield99) as audfield99,"
                    + "TRIM(audfield100) as audfield100 "
                    + "from articlecodemaster";
            System.out.print("\n ------------------------------ARTICLECODEMASTER------------------------------ \n");
            System.out.print("\n Entré a ejecutar query select en informix \n");
            
            try (PreparedStatement pstmt = cnt2.prepareStatement(sqlPrepare)) {
               // Ejecutamos el query que trae la información de Informix    
               ResultSet rs = stmt.executeQuery(sql);
               // Contador que nos permite saber cuando llegamos al límite de inserción
               int counter = 0;
               int r = emptyTable(stmt2);
               
               System.out.print("\n Resultado del Delete: " + r + "\n");
               if(r>=0){
                   System.out.print("\n Entré al while next \n");
                    while (rs.next()) {
                        pstmt.setInt(1, rs.getInt("controlid"));
                        pstmt.setString(2, rs.getString("warehousecode"));
                        pstmt.setString(3, rs.getString("articlecode"));
                        pstmt.setDate(4, rs.getDate("creationdate"));
                        pstmt.setString(5, rs.getString("description"));
                        pstmt.setBigDecimal(6, rs.getBigDecimal("unitprice"));
                        pstmt.setBigDecimal(7, rs.getBigDecimal("salesprice"));
                        pstmt.setBigDecimal(8, rs.getBigDecimal("criterium1"));
                        pstmt.setBigDecimal(9, rs.getBigDecimal("criterium2"));
                        pstmt.setBigDecimal(10, rs.getBigDecimal("criterium3"));
                        pstmt.setBigDecimal(11, rs.getBigDecimal("criterium4"));
                        pstmt.setString(12, rs.getString("groupcode1"));
                        pstmt.setString(13, rs.getString("groupcode2"));
                        pstmt.setString(14, rs.getString("groupcode3"));
                        pstmt.setString(15, rs.getString("groupcode4"));
                        pstmt.setString(16, rs.getString("groupcode5"));
                        pstmt.setString(17, rs.getString("groupcode6"));
                        pstmt.setString(18, rs.getString("ud1"));
                        pstmt.setString(19, rs.getString("ud2"));
                        pstmt.setString(20, rs.getString("ud3"));
                        pstmt.setString(21, rs.getString("ud4"));
                        pstmt.setString(22, rs.getString("ud5"));
                        pstmt.setString(23, rs.getString("ud6"));
                        pstmt.setString(24, rs.getString("ud7"));
                        pstmt.setString(25, rs.getString("ud8"));
                        pstmt.setString(26, rs.getString("ud9"));
                        pstmt.setString(27, rs.getString("ud10"));
                        pstmt.setString(28, rs.getString("ud11"));
                        pstmt.setString(29, rs.getString("ud12"));
                        pstmt.setString(30, rs.getString("ud13"));
                        pstmt.setString(31, rs.getString("ud14"));
                        pstmt.setString(32, rs.getString("ud15"));
                        pstmt.setString(33, rs.getString("audfield1"));
                        pstmt.setString(34, rs.getString("audfield2"));
                        pstmt.setString(35, rs.getString("audfield3"));
                        pstmt.setString(36, rs.getString("audfield4"));
                        pstmt.setString(37, rs.getString("audfield5"));
                        pstmt.setString(38, rs.getString("audfield6"));
                        pstmt.setString(39, rs.getString("audfield7"));
                        pstmt.setString(40, rs.getString("audfield8"));
                        pstmt.setString(41, rs.getString("audfield9"));
                        pstmt.setString(42, rs.getString("audfield10"));
                        pstmt.setString(43, rs.getString("audfield11"));
                        pstmt.setString(44, rs.getString("audfield12"));
                        pstmt.setString(45, rs.getString("audfield13"));
                        pstmt.setString(46, rs.getString("audfield14"));
                        pstmt.setString(47, rs.getString("audfield15"));
                        pstmt.setString(48, rs.getString("audfield16"));
                        pstmt.setString(49, rs.getString("audfield17"));
                        pstmt.setString(50, rs.getString("audfield18"));
                        pstmt.setString(51, rs.getString("audfield19"));
                        pstmt.setString(52, rs.getString("audfield20"));
                        pstmt.setString(53, rs.getString("audfield21"));
                        pstmt.setString(54, rs.getString("audfield22"));
                        pstmt.setString(55, rs.getString("audfield23"));
                        pstmt.setString(56, rs.getString("audfield24"));
                        pstmt.setString(57, rs.getString("audfield25"));
                        pstmt.setString(58, rs.getString("audfield26"));
                        pstmt.setString(59, rs.getString("audfield27"));
                        pstmt.setString(60, rs.getString("audfield28"));
                        pstmt.setString(61, rs.getString("audfield29"));
                        pstmt.setString(62, rs.getString("audfield30"));
                        pstmt.setString(63, rs.getString("audfield31"));
                        pstmt.setString(64, rs.getString("audfield32"));
                        pstmt.setString(65, rs.getString("audfield33"));
                        pstmt.setString(66, rs.getString("audfield34"));
                        pstmt.setString(67, rs.getString("audfield35"));
                        pstmt.setString(68, rs.getString("audfield36"));
                        pstmt.setString(69, rs.getString("audfield37"));
                        pstmt.setString(70, rs.getString("audfield38"));
                        pstmt.setString(71, rs.getString("audfield39"));
                        pstmt.setString(72, rs.getString("audfield40"));
                        pstmt.setString(73, rs.getString("audfield41"));
                        pstmt.setString(74, rs.getString("audfield42"));
                        pstmt.setString(75, rs.getString("audfield43"));
                        pstmt.setString(76, rs.getString("audfield44"));
                        pstmt.setString(77, rs.getString("audfield45"));
                        pstmt.setString(78, rs.getString("audfield46"));
                        pstmt.setString(79, rs.getString("audfield47"));
                        pstmt.setString(80, rs.getString("audfield48"));
                        pstmt.setString(81, rs.getString("audfield49"));
                        pstmt.setString(82, rs.getString("audfield50"));
                        pstmt.setString(83, rs.getString("audfield51"));
                        pstmt.setString(84, rs.getString("audfield52"));
                        pstmt.setString(85, rs.getString("audfield53"));
                        pstmt.setString(86, rs.getString("audfield54"));
                        pstmt.setString(87, rs.getString("audfield55"));
                        pstmt.setString(88, rs.getString("audfield56"));
                        pstmt.setString(89, rs.getString("audfield57"));
                        pstmt.setString(90, rs.getString("audfield58"));
                        pstmt.setString(91, rs.getString("audfield59"));
                        pstmt.setString(92, rs.getString("audfield60"));
                        pstmt.setString(93, rs.getString("audfield61"));
                        pstmt.setString(94, rs.getString("audfield62"));
                        pstmt.setString(95, rs.getString("audfield63"));
                        pstmt.setString(96, rs.getString("audfield64"));
                        pstmt.setString(97, rs.getString("audfield65"));
                        pstmt.setString(98, rs.getString("audfield66"));
                        pstmt.setString(99, rs.getString("audfield67"));
                        pstmt.setString(100, rs.getString("audfield68"));
                        pstmt.setString(101, rs.getString("audfield69"));
                        pstmt.setString(102, rs.getString("audfield70"));
                        pstmt.setString(103, rs.getString("audfield71"));
                        pstmt.setString(104, rs.getString("audfield72"));
                        pstmt.setString(105, rs.getString("audfield73"));
                        pstmt.setString(106, rs.getString("audfield74"));
                        pstmt.setString(107, rs.getString("audfield75"));
                        pstmt.setString(108, rs.getString("audfield76"));
                        pstmt.setString(109, rs.getString("audfield77"));
                        pstmt.setString(110, rs.getString("audfield78"));
                        pstmt.setString(111, rs.getString("audfield79"));
                        pstmt.setString(112, rs.getString("audfield80"));
                        pstmt.setString(113, rs.getString("audfield81"));
                        pstmt.setString(114, rs.getString("audfield82"));
                        pstmt.setString(115, rs.getString("audfield83"));
                        pstmt.setString(116, rs.getString("audfield84"));
                        pstmt.setString(117, rs.getString("audfield85"));
                        pstmt.setString(118, rs.getString("audfield86"));
                        pstmt.setString(119, rs.getString("audfield87"));
                        pstmt.setString(120, rs.getString("audfield88"));
                        pstmt.setString(121, rs.getString("audfield89"));
                        pstmt.setString(122, rs.getString("audfield90"));
                        pstmt.setString(123, rs.getString("audfield91"));
                        pstmt.setString(124, rs.getString("audfield92"));
                        pstmt.setString(125, rs.getString("audfield93"));
                        pstmt.setString(126, rs.getString("audfield94"));
                        pstmt.setString(127, rs.getString("audfield95"));
                        pstmt.setString(128, rs.getString("audfield96"));
                        pstmt.setString(129, rs.getString("audfield97"));
                        pstmt.setString(130, rs.getString("audfield98"));
                        pstmt.setString(131, rs.getString("audfield99"));
                        pstmt.setString(132, rs.getString("audfield100"));
                        
                       
                        pstmt.addBatch();
                        counter++;
                         if (counter == REGISTROS_BATCH) {
                             pstmt.executeBatch();
                             counter = 0;
                         }

                         }
                         //revisamos si todavía hay sentencias pendientes de ejecutar
                         if (counter > 0) {
                             pstmt.executeBatch();
                         }
                        ImportControl control = new ImportControl();
                        control.setControlID(1);
                        control.setImportType(importType);
                        control.setControlTimestamp("");
                        control.setControlStatus(4);
                        control_service.insert(stmt2, importType, control);
                         System.out.print("\n Proceso finalizado! \n");
                         System.out.print("\n ------------------------------ARTICLECODEMASTER------------------------------ \n");
                         tituloResp = "Éxito";
                         mensajeResp = "se ejecutó la interface ArticleCodeMaster correctamente!";
                }else{
                    tituloResp = "Error";
                    mensajeResp = "Hubo problemas al eliminar la información de Sql Server previo a la inserción";
                   }
               
            }
            cnt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleCodeMasterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArticleCodeMasterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HashMap<String, String> map = new HashMap<>();
        map.put(tituloResp, mensajeResp);
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    } 
    
    public int emptyTable(Statement stmt){
        System.out.print("\n Entré a eliminar los registros en Sql Server: \n");
        String sql = "delete from [slim4interface_test].[dbo].[S4Import_ArticleCodeMaster]";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleCodeMasterServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ResponseEntity articleCodeMasterInsert(ArrayList<ArticleCodeMaster> t) throws ClassNotFoundException {
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
    
    
    
    public int insertDataTable(Statement stmt, ArrayList<ArticleCodeMaster> t) throws SQLException{
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[S4Import_ArticleCodeMaster]"
                + "("
                + "controlId,"
                    + "warehouse,"
                    + "code,"
                    + "creationDate,"
                    + "description,"
                    + "unitPrice,"
                    + "salesPrice,"
                    + "criterium1,"
                    + "criterium2,"
                    + "criterium3,"
                    + "criterium4,"
                    + "groupCode1,"
                    + "groupCode2,"
                    + "groupCode3,"
                    + "groupCode4,"
                    + "groupCode5,"
                    + "groupCode6,"
                    + "uD1,"
                    + "uD2,"
                    + "uD3,"
                    + "uD4,"
                    + "uD5,"
                    + "uD6,"
                    + "uD7,"
                    + "uD8,"
                    + "uD9,"
                    + "uD10,"
                    + "uD11,"
                    + "uD12,"
                    + "uD13,"
                    + "uD14,"
                    + "uD15,"
                    + "aUDField1,"
                    + "aUDField2,"
                    + "aUDField3,"
                    + "aUDField4,"
                    + "aUDField5,"
                    + "aUDField6,"
                    + "aUDField7,"
                    + "aUDField8,"
                    + "aUDField9,"
                    + "aUDField10,"
                    + "aUDField11,"
                    + "aUDField12,"
                    + "aUDField13,"
                    + "aUDField14,"
                    + "aUDField15,"
                    + "aUDField16,"
                    + "aUDField17,"
                    + "aUDField18,"
                    + "aUDField19,"
                    + "aUDField20,"
                    + "aUDField21,"
                    + "aUDField22,"
                    + "aUDField23,"
                    + "aUDField24,"
                    + "aUDField25,"
                    + "aUDField26,"
                    + "aUDField27,"
                    + "aUDField28,"
                    + "aUDField29,"
                    + "aUDField30,"
                    + "aUDField31,"
                    + "aUDField32,"
                    + "aUDField33,"
                    + "aUDField34,"
                    + "aUDField35,"
                    + "aUDField36,"
                    + "aUDField37,"
                    + "aUDField38,"
                    + "aUDField39,"
                    + "aUDField40,"
                    + "aUDField41,"
                    + "aUDField42,"
                    + "aUDField43,"
                    + "aUDField44,"
                    + "aUDField45,"
                    + "aUDField46,"
                    + "aUDField47,"
                    + "aUDField48,"
                    + "aUDField49,"
                    + "aUDField50,"
                    + "aUDField51,"
                    + "aUDField52,"
                    + "aUDField53,"
                    + "aUDField54,"
                    + "aUDField55,"
                    + "aUDField56,"
                    + "aUDField57,"
                    + "aUDField58,"
                    + "aUDField59,"
                    + "aUDField60,"
                    + "aUDField61,"
                    + "aUDField62,"
                    + "aUDField63,"
                    + "aUDField64,"
                    + "aUDField65,"
                    + "aUDField66,"
                    + "aUDField67,"
                    + "aUDField68,"
                    + "aUDField69,"
                    + "aUDField70,"
                    + "aUDField71,"
                    + "aUDField72,"
                    + "aUDField73,"
                    + "aUDField74,"
                    + "aUDField75,"
                    + "aUDField76,"
                    + "aUDField77,"
                    + "aUDField78,"
                    + "aUDField79,"
                    + "aUDField80,"
                    + "aUDField81,"
                    + "aUDField82,"
                    + "aUDField83,"
                    + "aUDField84,"
                    + "aUDField85,"
                    + "aUDField86,"
                    + "aUDField87,"
                    + "aUDField88,"
                    + "aUDField89,"
                    + "aUDField90,"
                    + "aUDField91,"
                    + "aUDField92,"
                    + "aUDField93,"
                    + "aUDField94,"
                    + "aUDField95,"
                    + "aUDField96,"
                    + "aUDField97,"
                    + "aUDField98,"
                    + "aUDField99,"
                    + "aUDField100"
                + ") "
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
                        + "'" + t.get(i).getaUDField100()+ "' "
                        + ")) as temporal"
                        + "("
                             + "controlId,"
                    + "warehouse,"
                    + "code,"
                    + "creationDate,"
                    + "description,"
                    + "unitPrice,"
                    + "salesPrice,"
                    + "criterium1,"
                    + "criterium2,"
                    + "criterium3,"
                    + "criterium4,"
                    + "groupCode1,"
                    + "groupCode2,"
                    + "groupCode3,"
                    + "groupCode4,"
                    + "groupCode5,"
                    + "groupCode6,"
                    + "uD1,"
                    + "uD2,"
                    + "uD3,"
                    + "uD4,"
                    + "uD5,"
                    + "uD6,"
                    + "uD7,"
                    + "uD8,"
                    + "uD9,"
                    + "uD10,"
                    + "uD11,"
                    + "uD12,"
                    + "uD13,"
                    + "uD14,"
                    + "uD15,"
                    + "aUDField1,"
                    + "aUDField2,"
                    + "aUDField3,"
                    + "aUDField4,"
                    + "aUDFfield5,"
                    + "aUDField6,"
                    + "aUDField7,"
                    + "aUDFfield8,"
                    + "aUDField9,"
                    + "aUDField10,"
                    + "aUDField11,"
                    + "aUDField12,"
                    + "aUDField13,"
                    + "aUDField14,"
                    + "aUDField15,"
                    + "aUDField16,"
                    + "aUDField17,"
                    + "aUDField18,"
                    + "aUDField19,"
                    + "aUDField20,"
                    + "aUDField21,"
                    + "aUDField22,"
                    + "aUDField23,"
                    + "aUDField24,"
                    + "aUDField25,"
                    + "aUDField26,"
                    + "aUDField27,"
                    + "aUDField28,"
                    + "aUDField29,"
                    + "aUDField30,"
                    + "aUDField31,"
                    + "aUDField32,"
                    + "aUDField33,"
                    + "aUDField34,"
                    + "aUDField35,"
                    + "aUDField36,"
                    + "aUDField37,"
                    + "aUDField38,"
                    + "aUDField39,"
                    + "aUDField40,"
                    + "aUDField41,"
                    + "aUDField42,"
                    + "aUDField43,"
                    + "aUDField44,"
                    + "aUDField45,"
                    + "aUDField46,"
                    + "aUDField47,"
                    + "aUDField48,"
                    + "aUDField49,"
                    + "aUDField50,"
                    + "aUDField51,"
                    + "aUDField52,"
                    + "aUDField53,"
                    + "aUDField54,"
                    + "aUDField55,"
                    + "aUDField56,"
                    + "aUDField57,"
                    + "aUDField58,"
                    + "aUDField59,"
                    + "aUDField60,"
                    + "aUDField61,"
                    + "aUDField62,"
                    + "aUDField63,"
                    + "aUDField64,"
                    + "aUDField65,"
                    + "aUDField66,"
                    + "aUDField67,"
                    + "aUDField68,"
                    + "aUDField69,"
                    + "aUDField70,"
                    + "aUDField71,"
                    + "aUDField72,"
                    + "aUDField73,"
                    + "aUDField74,"
                    + "aUDField75,"
                    + "aUDField76,"
                    + "aUDField77,"
                    + "aUDField78,"
                    + "aUDField79,"
                    + "aUDField80,"
                    + "aUDField81,"
                    + "aUDField82,"
                    + "aUDField83,"
                    + "aUDField84,"
                    + "aUDField85,"
                    + "aUDField86,"
                    + "aUDField87,"
                    + "aUDField88,"
                    + "aUDField89,"
                    + "aUDField90,"
                    + "aUDField91,"
                    + "aUDField92,"
                    + "aUDField93,"
                    + "aUDField94,"
                    + "aUDField95,"
                    + "aUDField96,"
                    + "aUDField97,"
                    + "aUDField98,"
                    + "aUDField99,"
                    + "aUDField100"
                            + ")"
                :
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
                        + "'" + t.get(i).getaUDField100()+ "'), ";
                        
            }  
        return stmt.executeUpdate(sql);
    } 
}
