
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
            Logger.getLogger(TransactionsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    } 
}
