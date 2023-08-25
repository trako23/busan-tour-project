package com.trako.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {
	
	@Value("${openApi.FestivalServiceKey}")
    private String FestivalServiceKey;

    @Value("${openApi.FestivalCallBackUrl}")
    private String FestivalCallBackUrl;
    
    @Value("${openApi.CourseServiceKey}")
    private String CourseServiceKey;

    @Value("${openApi.CourseCallBackUrl}")
    private String CourseCallBackUrl;
    
    @Value("${openApi.dataType}")
    private String dataType;
    
    //공공데이터 축제데이터JSON으로 return
    @GetMapping("/api/festival")
    public ResponseEntity<String> callFestivalApi(
            @RequestParam(value="pageNo" ,defaultValue = "1") String pageNo,
            @RequestParam(value="numOfRows", defaultValue = "4") String numOfRows
    ){
        HttpURLConnection urlConnection = null;
        StringBuilder sb=null;
        String urlStr = FestivalCallBackUrl +
                "serviceKey=" + FestivalServiceKey +
                "&dataType=" + dataType +
                "&pageNo=" + pageNo +
                "&numOfRows=" + numOfRows; 
        
        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-type",  "application/json");
            urlConnection.setRequestMethod("GET");
            System.out.println("Response code: " + urlConnection.getResponseCode());
            BufferedReader rd;
            if(urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            }
             sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return ResponseEntity.ok(sb.toString());
        
    }
    //코스데이터 JSON으로 return
    @GetMapping("/api/course")
    public ResponseEntity<String> callCourseApi(
            @RequestParam(value="pageNo" ,defaultValue = "1") String pageNo,
            @RequestParam(value="numOfRows", defaultValue = "4") String numOfRows
    ){
        HttpURLConnection urlConnection = null;
        StringBuilder sb=null;
        String urlStr = CourseCallBackUrl +
                "serviceKey=" + CourseServiceKey +
                "&dataType=" + dataType +
                "&pageNo=" + pageNo +
                "&numOfRows=" + numOfRows; 
        
        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-type",  "application/json");
            urlConnection.setRequestMethod("GET");
            System.out.println("Response code: " + urlConnection.getResponseCode());
            BufferedReader rd;
            if(urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            }
             sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return ResponseEntity.ok(sb.toString());
        
    }


}
