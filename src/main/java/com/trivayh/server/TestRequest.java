package com.trivayh.server;


import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.rest.ProcessedAPIRequest;
import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;

import com.trivayh.facebook.FacebookUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sayed
 * Date: 9/9/13
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestRequest implements CustomCodeMethod {
    @Override
    public String getMethodName() {
        return "test_request";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getParams() {
        return Arrays.asList("accessToken");
    }

    @Override
    public ResponseToProcess execute(ProcessedAPIRequest processedAPIRequest, SDKServiceProvider sdkServiceProvider) {

        String accessToken = "";
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(processedAPIRequest.getBody());
            JSONObject jsonObject = (JSONObject) obj;
            accessToken = (String) jsonObject.get("accessToken");
        }catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Map<String, String> args = new HashMap<String, String>();
        FacebookUtil facebook = new FacebookUtil(accessToken, sdkServiceProvider);
        args.put("msg", facebook.getMe());


        return new ResponseToProcess(HttpURLConnection.HTTP_OK, args);

    }
}
