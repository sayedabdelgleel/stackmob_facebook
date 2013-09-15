package com.trivayh.server;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.rest.ProcessedAPIRequest;
import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;
import com.stackmob.sdkapi.http.Header;
import com.stackmob.sdkapi.http.HttpService;
import com.stackmob.sdkapi.http.request.GetRequest;
import com.stackmob.sdkapi.http.response.HttpResponse;
import com.trivayh.facebook.FacebookUtil;

import java.lang.reflect.Array;
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
        return Arrays.asList();
    }

    @Override
    public ResponseToProcess execute(ProcessedAPIRequest processedAPIRequest, SDKServiceProvider sdkServiceProvider) {
        Map<String, String> args = new HashMap<String, String>();
        args.put("msg", FacebookUtil.getFacebookMeRest(sdkServiceProvider));


        return new ResponseToProcess(HttpURLConnection.HTTP_OK, args);

    }
}
