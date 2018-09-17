package com.macys.sdt.projects.Discovery.DeploymentValidator.util;

import com.splunk.*;

import java.net.URLStreamHandler;
import java.util.Map;

/**
 * Created by m125779 on 8/8/2017.
 */
public class SplunkHelper extends Service {



    SplunkHelper(String host){

      super(host);
    }
    public SplunkHelper(Map<String, Object> args) {
        super(args);

    }


    /**
     * Establishes a connection to a Splunk service using a map of arguments.
     * This member creates a new {@code Service} instance and authenticates
     * the session using credentials passed in from the {@code args} map.
     *
     * @param args The {@code args} map.
     * @return A new {@code Service} instance.
     */
    public static Service connect(Map<String, Object> args) {
        SplunkHelper sHelper =new SplunkHelper(args);
        SplunkHelper service = new SplunkHelper(args);
        if (args.containsKey("username")) {
            service= (SplunkHelper) sHelper.login();
        }
        return service;
    }


    /**
     * Authenticates the {@code Service} instance with a specified username and
     * password. Note that these values override any previously-set values for
     * username and password.
     *
     * @param username The Splunk account username.
     * @param password The password for the username.
     * @return The current {@code Service} instance.
     */
    public Service login(String username, String password) {
        this.username = username;
        this.password = password;
        this.port=-1;
        Args args = new Args();
        args.put("username", username);
        args.put("password", password);
        args.put("cookie", "1");
        args.put("cval","1605064302");
        args.put("Content-Type","text/html;charset=UTF-8");
       // ResponseMessage response = post("/en-US/account/login", args);
        ResponseMessage response = get("/en-US/account/login", args);
        String sessionKey = Xml.parse(response.getContent())
                .getElementsByTagName("sessionKey")
                .item(0)
                .getTextContent();
        this.token = "Splunk " + sessionKey;
        this.version = this.getInfo().getVersion();
        if (versionCompare("4.3") >= 0)
            this.passwordEndPoint = "storage/passwords";

        return this;
    }

}
