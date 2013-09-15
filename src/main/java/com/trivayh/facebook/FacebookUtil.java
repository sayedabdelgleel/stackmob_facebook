package com.trivayh.facebook;


import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;
import com.stackmob.sdkapi.http.Header;
import com.stackmob.sdkapi.http.HttpService;
import com.stackmob.sdkapi.http.request.GetRequest;
import com.stackmob.sdkapi.http.response.HttpResponse;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sayed
 * Date: 9/9/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookUtil {

    private static final String FACEBOOk_GRAPH_URL = "https://graph.facebook.com/";
    private SDKServiceProvider serviceProvider;
    private String accessToken;

    public FacebookUtil (String token, SDKServiceProvider sdkServiceProvider)
    {
         serviceProvider = sdkServiceProvider;
         accessToken = "?access_token=" + token;
    }


    public String getMe(){
        String url = FACEBOOk_GRAPH_URL+ "/me" +accessToken;
        HttpResponse resp = makeGETRequest(url);
        return resp.getBody();
    }

    public String getFriends(){
        String url = FACEBOOk_GRAPH_URL+ "/me/friends" +accessToken;
        HttpResponse resp = makeGETRequest(url);
        return resp.getBody();
    }

    public String getUser(String id){
        String url = FACEBOOk_GRAPH_URL+ "/"+id+accessToken;
        HttpResponse resp = makeGETRequest(url);
        return resp.getBody();
    }

    public HttpResponse makeGETRequest(String url){

        Header accept = new Header("Accept-Charset", "utf-8");
        Header content = new Header("Content-Type", "application/x-www-form-urlencoded");

        Set<Header> set = new HashSet();
        set.add(accept);
        set.add(content);

        try {
            HttpService http = serviceProvider.getHttpService();
            GetRequest req = new GetRequest(url,set);
            HttpResponse resp = http.get(req);
            System.out.println(resp.getBody());
            return resp;
        } catch (Exception e) {
            return null;
        }

    }
}
