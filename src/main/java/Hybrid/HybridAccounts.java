package Hybrid;

import java.sql.*;

/*
prramu - 2/12/2018
 */
import oracle.cloud.paas.logger.ProvisioningLogger;
import org.apache.logging.log4j.Logger;

public class HybridAccounts {
    private static Logger log = ProvisioningLogger.getLogger(HybridAccounts.class);
   static  String DBConnectionString = "jdbc:oracle:thin:@slcs23-scan1.c9dev1.oraclecorp.com:1521/centralDB";
    static String DBConnectionUsername = "tas";
    static String DBConnectionPasswd = "Welcome1";

    public static void main(String[] args) throws Exception {

        createShellAccount("praveen.ramu@oracle.com", "PraveenRamu", "Testprvdsdell02", "29999109992", "RG001");
        return;
    }


    public static String createShellAccount(String buyer_emailid ,String buyer_name ,String accountName ,String acctNoUnique11,String datacenter  ) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        final Connection c = DriverManager.getConnection(DBConnectionString,DBConnectionUsername,DBConnectionPasswd);
        String out = "";
        String accountCreate = "DECLARE\n" +
                "  l_tas_order              tas.tas_order_t;\n" +
                "  l_order_id               VARCHAR2(1000);\n" +
                "  l_e                      tas.tas_e_t;\n" +
                "  l_order_item_list        tas.tas_order_item_list_t;\n" +
                "  l_order_item             tas.tas_order_item_t;\n" +
                "  l_order                  tas.tas_order_t;\n" +
                "  l_buyer_contact          tas.tas_person_info_t;\n" +
                "  l_aa_contact             tas.tas_person_info_t;\n" +
                "  l_properties             tas.tas_key_value_list_t ;\n" +
                "  l_prop_t                 tas.tas_key_value_t ;\n" +
                "  l_buyer_email            VARCHAR2(100 char);\n" +
                "  l_account_admin_email    VARCHAR2(100 char);\n" +
                "  l_customer_name          VARCHAR2(1000 char);\n" +
                "  l_customer_acct_number   VARCHAR2(1000 char);\n" +
                "  l_data_center_id         VARCHAR2(100 CHAR);\n" +
                "  l_data_center_region_id  VARCHAR2(256 CHAR);\n" +
                "BEGIN\n" +
                "  l_buyer_email             := '"+buyer_emailid+"';\n" +
                "  l_account_admin_email     := 'praveen.ramu@oracle.com';\n" +
                "  l_buyer_contact           := tas.tas_person_info_t(given_name => '"+buyer_name+"',\n" +
                "                                         family_name => 'ks',\n" +
                "                                         email => l_buyer_email,\n" +
                "                                         name => l_buyer_email);\n" +
                "  l_aa_contact              := tas.tas_person_info_t(given_name => '"+buyer_name+"',\n" +
                "                                         family_name => 'ks',\n" +
                "                                         email => l_account_admin_email,\n" +
                "                                         name => l_account_admin_email);                                        \n" +
                " \n" +
                "  l_customer_name           := '"+buyer_name+"';\n" +
                "  l_customer_acct_number    := '"+acctNoUnique11+"';\n" +
                "  --l_data_center_id          :=  'US001';  --mandatory for island based orders\n" +
                "  l_data_center_region_id   :=  '"+datacenter+"';\n" +
                "  \n" +
                "  l_prop_t                  := tas_key_value_t ( KEY => tas_property_sets_pkg.PROP_NAME_CUST_COUNTRY_CODE, value => 'US' ); --mandatory\n" +
                "  l_properties              := tas_key_value_list_t();\n" +
                "  l_properties.extend();\n" +
                "  l_properties(1)           := l_prop_t;\n" +
                " \n" +
                "  -- Build the item objects first.\n" +
                "  l_order_item_list         := tas.tas_order_item_list_t();\n" +
                "  l_order_item              := tas.tas_order_item_t\n" +
                "                            (\n" +
                "                              OPERATION_TYPE => tas.tas_oq_order_pkg.CREATE_CLOUD_ACCOUNT,\n" +
                "                              SYSTEM_NAME => NULL\n" +
                "                            );\n" +
                "  l_order_item.opc_account_name    := null;\n" +
                "  l_order_item.system_id           := null;\n" +
                "  l_order_item.cloud_account_name  := '"+accountName+"';\n" +
                "  l_order_item_list.extend();\n" +
                "  l_order_item_list(1)             := l_order_item;\n" +
                " \n" +
                "  \n" +
                "  l_tas_order       := tas.tas_order_t\n" +
                "                      (\n" +
                "                        user_id                     => l_buyer_email,\n" +
                "                        system_name                 => NULL,\n" +
                "                        customer_name               => l_customer_name,\n" +
                "                        services                    => l_order_item_list,\n" +
                "                        user_contact_info           => l_buyer_contact,\n" +
                "                        order_source                => tas.tas_oq_order_pkg.ORDER_SOURCE_INTERNAL,\n" +
                "                        data_center_id              => l_data_center_id,\n" +
                "                        data_center_region_id       => l_data_center_region_id\n" +
                "                        );\n" +
                "  l_tas_order.account_admin_username          := l_account_admin_email;\n" +
                "  l_tas_order.account_admin_contact_info      := l_aa_contact;\n" +
                "  l_tas_order.tca_party_id                    := l_customer_acct_number;\n" +
                "  l_tas_order.system_id                       := NULL;\n" +
                " \n" +
                "  l_tas_order.order_type                      := tas_constants_pkg.REAL_ORDER_TYPE;\n" +
                "  l_tas_order.status                          := tas_oq_order_pkg.STATE_TO_BE_PROCESSED;\n" +
                "  l_tas_order.system_admin_username           := null;\n" +
                "  l_tas_order.system_admin_contact_info       := null;\n" +
                "  l_tas_order.order_properties                := l_properties;\n" +
                " \n" +
                "  tas_oq_order_pkg.submit_order_request(o_e => l_e, o_order_id => l_order_id, i_order => l_tas_order);\n" +
                " \n" +
                "  dbms_output.put_line('New order id is ' || l_order_id);\n" +
                "? := 'New order id is ' || l_order_id;"+
                "  IF(l_e IS NOT NULL) THEN\n" +
                "    dbms_output.put_line('Error ----- ' || l_e.e_errm||'. '||l_e.sql_errm);\n" +
                "? := 'New order Failed ';"+
                "  END IF;\n" +
                "  COMMIT;\n" +
                "END;\n";


        CallableStatement cs = c.prepareCall(accountCreate);
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.execute();
        String outpass = "Result = " + cs.getObject(1);
        out = "Fail Result = " + cs.getObject(2);
        log.info(outpass);
        log.info(out);

        cs.close();
        c.close();
        return out;
    }

}
