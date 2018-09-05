/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spiralg.chatrestful.rowmapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khoand
 */
public class DisplayFriend {

//    private static DisplayFriend instance;
//
//    private DisplayFriend() {
//    }
//
//    public static DisplayFriend getInstance() {
//        if (instance == null) {
//            instance = new DisplayFriend();
//        }
//        return instance;
//    }
//
//    public List<Friend> getListFriendApi(String username) {
//        List<Friend> friends = new ArrayList<>();
//        try {
//            Gson gson = new Gson();
//            String json = "";
//            URL url = new URL(String.valueOf("http://localhost:8080//api/friends?username=" + username));
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");
//
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//
//            String output;
//            while ((output = br.readLine()) != null) {
//                json += output;
//            }
//            //JsonElement jelement = new JsonParser().parse(json);
//            friends = gson.fromJson(json,
//                    new TypeToken<ArrayList<Friend>>() {
//                    }.getType());
//            conn.disconnect();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return friends;
//    }

}
