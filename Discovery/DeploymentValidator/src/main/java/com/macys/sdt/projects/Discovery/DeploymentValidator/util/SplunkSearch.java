package com.macys.sdt.projects.Discovery.DeploymentValidator.util;

/**
 * Created by m125779 on 8/7/2017.
 */
import java.io.*;
import com.splunk.*;

public class SplunkSearch {
    String host = "localhost";
    public static void main(String[] args) throws IOException {

         /* Overriding the static method setSslSecurityProtocol to implement the security protocol of choice */
      //  HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
         /* end comment for overriding the method setSslSecurityProtocol */

        ServiceArgs loginArgs = new ServiceArgs();
        loginArgs.setUsername("mcomqe");
        loginArgs.setPassword("changeme");
        //loginArgs.setHost("https://rtp-splunk/en-us/app/services/search");
       // loginArgs.setPort(80);
        loginArgs.setHost("rtp-splunk");
        loginArgs.setScheme("http");
        loginArgs.setToken("cval=1605064302");
       // loginArgs.setToken("allowRemoteLogin = always");

      // loginArgs.setPort(80);

        SplunkHelper svc = (SplunkHelper) SplunkHelper.connect(loginArgs);
        svc.login();
        JobExportArgs exportArgs = new JobExportArgs();
        exportArgs.setSearchMode(JobExportArgs.SearchMode.NORMAL);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a search string then press <ENTER>: ");
        String mySearch = br.readLine();

        if(!(mySearch.trim().startsWith("|")) && !(mySearch.substring(0, 6).equalsIgnoreCase("search"))) {
            mySearch = "search " + mySearch;
        }
        InputStream exportSearch = svc.export(mySearch, exportArgs);
        MultiResultsReaderXml resultsReader = new MultiResultsReaderXml(exportSearch);

        long counter = 0;
        for(SearchResults searchResults : resultsReader) {
            for(Event event : searchResults) {
                System.out.println("**** Event " + ++counter + " ****");
                for(String key : event.keySet()) {
                    System.out.println("\t" + key + ": " + event.get(key));
                }
            }
        }
        resultsReader.close();
    }
}

