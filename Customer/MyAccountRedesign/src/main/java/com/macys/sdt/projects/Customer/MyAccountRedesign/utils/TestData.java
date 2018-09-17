package com.macys.sdt.projects.Customer.MyAccountRedesign.utils;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

/**
 * Created by m509575 on 16/03/17.
 */
public class TestData {
    final String plentiTestDataPath = "plenti_cards.json";
    private PlentiTestData plentiTestData;
    private ObjectMapper mapper;
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());

    public void setPlentiTestData(PlentiTestData plentiData){
        this.plentiTestData = plentiData;
    }

    private PlentiTestData loadPlentiData(){
        try{
            mapper = new ObjectMapper();
            return mapper.readValue(getResourceFile("plenti_cards.json"),PlentiTestData.class);
        }
        catch(IOException e){
            logger.info("unable to load plenti test data file plenti_card.json");
        }
        return null;
    }

    public PlentiTestData getPlentiTestData(){
        if(plentiTestData==null){
            this.setPlentiTestData(this.loadPlentiData());
        }
        return this.plentiTestData;
    }
}
