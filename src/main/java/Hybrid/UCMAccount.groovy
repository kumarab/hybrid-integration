package Hybrid

import oracle.cloud.core.tas.OrderUtil

class UCMAccount {

    public static void main(String[] args){
          UCMAccountcreate("praveen.ramu@oracle.com" , "Pool2")
    }


        public static String UCMAccountcreate(String emailid , String poolinfo){
            def params = [:]
            params."@ACCOUNT_ADMIN_EMAIL@" = "praveen.ramu@oracle.com"
            params."@ORDER_NUMBER@"="38139994221"
            params."@CUSTOMER_ACCT_NUMBER@"="1999999"
            params."@CSI_NUMBER@"="9199998"
            OrderUtil  orderUtil = new OrderUtil(System.getProperty("user.dir")+"/src/main/java/HybridG/payloads/hybridsaaspayload.xml",emailid,params)

            orderUtil.processPayload()
            String orderId = orderUtil.seedPayload("Pool2",orderUtil.processPayload())
            Random random = new Random();
            int rand = random.nextInt(500);
            println("Creating Identity Domain :" + "mytest"+rand)
             String order  = orderUtil.completeOrder("Pool2",orderId,"mytest"+rand)
             return order

        }


}


