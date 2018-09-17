package com.macys.sdt.shared.utils;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class FileUtil {

    private static FileUtil inst = new FileUtil();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static FileUtil getInstance() {
        return inst;
    }

    private FileUtil() {
    }

    private String readFile(String qualifiedFilename) throws IOException {

        logger.debug(String.format(">>>>>>>>>>>> Qualified full name to read=%1$s", qualifiedFilename));

        try (InputStream is = new DefaultResourceLoader().getResource(qualifiedFilename).getInputStream()) {

            logger.debug("<<<<<<< Qualified full name read");
            return IOUtils.toString(is);
        }
    }

    public LinkedHashMap getJsonKeyValue(String param, String fileName) throws IOException, ParseException {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        JSONObject json = new JSONObject(readFile(fileName));

        map = toMap(json.getJSONObject(param));

        return map;
    }

    private LinkedHashMap<String, String> toMap(JSONObject object) throws JSONException {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        Iterator<String> keysItr = object.keys();

        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value.toString());
        }

        return map;
    }
}
