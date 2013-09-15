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


    public static String getFacebookMeRest(SDKServiceProvider sdkServiceProvider){

        return null;
    }

    public static String getFacebookMe(SDKServiceProvider sdkServiceProvider){
        String url = "https://graph.facebook.com/me?access_token=CAADJZA5LFsBMBACNbStuZAdyAZCjaHylp89gjo9iZBzm28hMDGmHb157ZCYUA8jZBhiQnDJgWkLZCOmtp2xFGlZCf2KFE1vqEZBzXU4KO0ZCtu2LvrQvad0cJI5q9jIUqurgry00x5nnyX1wJhBDX7uXJUAXJPmQZBjZBeQeshyYWZBIpuvZA6svPEJVmuzKsOYRRXdIsZD";

        // Formulate request headers
        Header accept = new Header("Accept-Charset", "utf-8");
        Header content = new Header("Content-Type", "application/x-www-form-urlencoded");

        Set<Header> set = new HashSet();
        set.add(accept);
        set.add(content);


        try {
            System.out.println("kokooooooooooooooooooooo111111");
            HttpService http = sdkServiceProvider.getHttpService();

      /* In this Example we are going to be making a GET request
       * but PUT/POST/DELETE requests are also possible.
       */
            GetRequest req = new GetRequest(url,set);
            HttpResponse resp = http.get(req);

            System.out.println("kokooooooooooooooooooooo");
            System.out.println(resp.getBody());
            return resp.getBody();
        } catch (Exception e) {
            System.out.println("eeeeeeeeeeeee");
            System.out.println(e.getMessage());
            return "Exception.";
        }



    }
}
