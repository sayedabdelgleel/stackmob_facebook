package com.trivayh.server;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.rest.ProcessedAPIRequest;
import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return Arrays.asList();
    }

    @Override
    public ResponseToProcess execute(ProcessedAPIRequest processedAPIRequest, SDKServiceProvider sdkServiceProvider) {
        Map<String, String> args = new HashMap<String, String>();
        args.put("msg", "hello world!");

        FacebookClient facebookClient = new DefaultFacebookClient("CAAGF19ZAX3kUBABtGWvLZAlfqrNOHqsMNhGBbqEWRwCPHpp3oBDdGl4ZCEOmGbAd4DH0h8NVcMbxV2SNVf9sv4Vn9whZCWWuWbfuHNbNFsPMkbA6FBFiqQ64FgvSuCZCrCgyoUGnRIb7KV0l22xW1Pr7ZCSXiD5JajcUKozB8ZCCAG11OAQkoSZB4UnTHECFAhv0mIBY9WjtAwZDZD");

        User user = facebookClient.fetchObject("me", User.class);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(user.getName());
        System.out.println("-------------------------------------------------------------------------");

        return new ResponseToProcess(HttpURLConnection.HTTP_OK, args);
    }
}
