package com.macys.sdt.projects.Discovery.DeploymentValidator.util;

/**
 * Created by m125779 on 8/7/2017.
 */
import com.splunk.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.security.krb5.internal.HostAddress;


public class SplunkUtil {

    public static void main(String[] args) {



      //  String basicAuthHeader = Base64.encode(credentials.getBytes());
      //  service.setToken("Basic " + basicAuthHeader);
      //  HttpService.setSslSecurityProtocol( SSLSecurityProtocol.TLSv1_2 );
        ServiceArgs loginArgs = new ServiceArgs();
        loginArgs.setUsername("mcomqe");
        loginArgs.setPassword("changeme");
        loginArgs.setHost("https://rtp-splunk/en-us/app/services/search");
       loginArgs.setPort(8089);
       // loginArgs.setCookie("session_id_8000=365f117c56c46bdbd0ce95c7e21d2b2d1811830d; splunkd_8000=PPlkw^0Az9Yt33_96761KpI54YFSS\n" +
                //"DzpUYpetxM9p1oFSwQefzZDCxltMEMWSctxo3FLSjfmTC6D1pExyeTYtkLGNVZUJvN82IkqIW3pGWygRxe6oYTy0KwT7Q6yFGnZfrN\n" +
         //       "; splunkweb_csrf_token_8000=9431577942699198849");
        loginArgs.setSSLSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
        System.out.println("URI IS ::"+loginArgs.toString());

        Service service = Service.connect(loginArgs);
        // Print the session token
        System.out.println("Your session token: " + service.getToken());

        // Print installed apps to the console to verify login
        for (Application app : service.getApplications().values()) {
            System.out.println(app.getName());
        }
    }

}
