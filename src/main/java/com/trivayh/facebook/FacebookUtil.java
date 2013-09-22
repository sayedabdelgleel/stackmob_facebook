package com.trivayh.facebook;


import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;
import com.stackmob.sdkapi.http.Header;
import com.stackmob.sdkapi.http.HttpService;
import com.stackmob.sdkapi.http.request.GetRequest;
import com.stackmob.sdkapi.http.response.HttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public FacebookUtil(String token, SDKServiceProvider sdkServiceProvider) {
        serviceProvider = sdkServiceProvider;
        accessToken = "?access_token=" + token;
    }


    public User getMe() {
        String url = FACEBOOk_GRAPH_URL + "/me" + accessToken;
        HttpResponse resp = makeGETRequest(url);
        return createUser(resp.getBody());
    }

    public List<FacebookUser> getFriends() {
        String url = FACEBOOk_GRAPH_URL + "/me/friends" + accessToken;
        HttpResponse resp = makeGETRequest(url);
        return createFriends(resp.getBody());
    }

    public User getUser(String id) {
        String url = FACEBOOk_GRAPH_URL + "/" + id + accessToken;
        HttpResponse resp = makeGETRequest(url);
        return createUser(resp.getBody());
    }

    public HttpResponse makeGETRequest(String url) {

        Header accept = new Header("Accept-Charset", "utf-8");
        Header content = new Header("Content-Type", "application/x-www-form-urlencoded");

        Set<Header> set = new HashSet();
        set.add(accept);
        set.add(content);

        try {
            HttpService http = serviceProvider.getHttpService();
            GetRequest req = new GetRequest(url, set);
            HttpResponse resp = http.get(req);
            System.out.println(resp.getBody());
            return resp;

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }

    }

    public User createUser(String body) {

        User user = new User();
        JSONObject userObject = parseJSONObject(body);

        user.setId((String) userObject.get("id"));
        user.setName((String) userObject.get("name"));
        user.setUserName((String) userObject.get("username"));
        user.setFirstName((String) userObject.get("first_name"));
        user.setLastName((String) userObject.get("last_name"));
        user.setBirthDay((String) userObject.get("birthday"));

        JSONObject hometownObject = (JSONObject) userObject.get("hometown");
        user.setHomeTown((String) hometownObject.get("name"));

        JSONObject currentCityObject = (JSONObject) userObject.get("location");
        user.setCurrentCity((String) currentCityObject.get("name"));

        return user;
    }

    public List <FacebookUser> createFriends(String body){
        List<FacebookUser> friends = new ArrayList<FacebookUser>();
        JSONObject friendsObject = parseJSONObject(body);
        JSONArray friendsList = (JSONArray) friendsObject.get("data");
        for( int i=0; i<friendsList.size(); i++){
            JSONObject friendObject = (JSONObject) friendsList.get(i);
            FacebookUser friend = new FacebookUser();
            friend.setId((String) friendObject.get("id"));
        }
        return  friends;
    }

    public JSONObject parseJSONObject(String body) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {

            obj = (JSONObject) parser.parse(body);

        } catch (ParseException pe) {

        }
        return obj;
    }
}
