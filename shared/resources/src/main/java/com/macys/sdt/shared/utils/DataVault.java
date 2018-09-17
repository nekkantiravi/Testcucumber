package com.macys.sdt.shared.utils;

import com.google.gson.Gson;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.AesUtil;

import java.util.List;
import java.util.Map;

public class DataVault {
    private static final String URL = "http://sdt.ee.fds.com/vault/dataVault/";
    private String vaultName;
    public DataVault(String vaultName){
        this.vaultName = vaultName;
    }


    private static int KEY_SIZE = 128;
    private static int ITERATION_COUNT = 1000;
    private static String SALT = "4600fe22247b828c621cdac7da11f543";
    private static String IV = "f094e1a709a8bd8a5860dce43be8446f";
    private Map vaultData;
    public Map getVault() {
        try {
            String data = new AesUtil(KEY_SIZE, ITERATION_COUNT).decrypt(SALT, IV, this.vaultName.toUpperCase(), Utils.httpGet(URL + "get/" + this.vaultName, null));
            return this.vaultData = new Gson().fromJson(data, Map.class);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String getParam(String param) {
        if (this.vaultData == null)
            this.getVault();
        try {
            List restrictScenarios = null;
            try {
                restrictScenarios = new Gson().fromJson(this.vaultData.get("scenarios").toString(), List.class);
                String currentScenarioName = Hooks.getCurrentScenario().getName();
                if (!restrictScenarios.contains(currentScenarioName))
                    throw new Exception("Scenario restriction.");
            } catch (Exception ex) {
            }
            List<String> vault_params = (List) this.vaultData.get("vault_params");
            for (int i = 0; i < vault_params.size(); i += 2) {
                if (vault_params.get(i).equals(param)) {
                    return vault_params.get(i + 1);
                }
            }
        }catch(Exception ex1){
            ex1.printStackTrace();
        }
        return null;
    }
}
