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
public class setFriends implements CustomCodeMethod {
    @Override
    public String getMethodName() {
        return "set_friends";  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public List<String> getParams() {
        return Arrays.asList("accessToken");
    }

    @Override
    public ResponseToProcess execute(ProcessedAPIRequest processedAPIRequest, SDKServiceProvider sdkServiceProvider) {

        DataService ds = sdkServiceProvider.getDataService();
        String accessToken = processedAPIRequest.getParams().get("accessToken");

        FacebookUtil facebook = new FacebookUtil(accessToken, sdkServiceProvider);

        JSONParser parser = new JSONParser();
        JSONArray friendsJSON = new JSONArray();
        String playerID = "";

        try {

            JSONObject jsonFriendsObject = (JSONObject) parser.parse(facebook.getFriends());
            JSONObject jsonPlayerObject = (JSONObject) parser.parse(facebook.getMe());
            friendsJSON = (JSONArray) jsonFriendsObject.get("data");
            playerID = (String) jsonPlayerObject.get("id");

        } catch (ParseException pe) {

        }

        for (int i =0; i<1000; i++){
//            JSONObject friendJSON = (JSONObject) friendsJSON.get(i);
//            String friendID = (String) friendJSON.get("id");
//
            HashMap<String, SMValue> friends = new HashMap<String, SMValue>();
            friends.put("friends_id", new SMString("45735"+i)); //string
            friends.put("player_id", new SMString("86423"+i)); //string

            try {
                ds.createObject("friends", new SMObject(friends));
            } catch (InvalidSchemaException ise) {
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                System.out.println(ise.getMessage());
            } catch (DatastoreException dse) {
                System.out.println("//////////////////////////////////////////////////////");
                System.out.println(dse.getMessage());
            }
        }

        Map<String, String> args = new HashMap<String, String>();
        args.put("msg", "success");

        return new ResponseToProcess(HttpURLConnection.HTTP_OK, args);

    }
}
