package com.trivayh.server;


import com.stackmob.core.DatastoreException;
import com.stackmob.core.InvalidSchemaException;
import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.rest.ProcessedAPIRequest;
import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.DataService;
import com.stackmob.sdkapi.SDKServiceProvider;
import com.stackmob.sdkapi.*;
import com.trivayh.facebook.FacebookUtil;
import org.json.simple.JSONArray;
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

        String accessToken = processedAPIRequest.getParams().get("accessToken");

        Map<String, String> args = new HashMap<String, String>();
        FacebookUtil facebook = new FacebookUtil(accessToken, sdkServiceProvider);
        DataService ds = sdkServiceProvider.getDataService();
        JSONParser parser = new JSONParser();
        String myId = "";
        JSONArray friendsJson = new JSONArray();
        try {
            Object obj = parser.parse(facebook.getFriends());
            JSONObject jsonObject = (JSONObject) obj;
             friendsJson = (JSONArray) jsonObject.get("data");
            // Fetch the values passed in by the user from the body of JSON

        } catch (ParseException pe) {

        }
        HashMap<String, SMValue> friends = new HashMap<String, SMValue>();

//        friends.put("friends_id", new SMString("d")); //string
//        friends.put("player_id", new SMString("f")); //string
//
//
//        try {
//            // This is how you create an object in the `car` schema
//            ds.createObject("friends", new SMObject(friends));
//        } catch (InvalidSchemaException ise) {
//        } catch (DatastoreException dse) {}
        args.put("msg", myId);

        return new ResponseToProcess(HttpURLConnection.HTTP_OK, args);

    }
}
