package com.cit.placepicker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Foursquare {
	
public ArrayList<Venue> getVenues(String location,String query)
{
	ArrayList<Venue> venueList = new ArrayList<Venue>();
System.out.println("hi");

//location = "11.0253488,77.02187249999997";
//query = "hotel";

StringBuilder data = new StringBuilder();
URL url = null;
try {
	url = new URL("https://api.foursquare.com/v2/venues/search?ll="+location+"&client_id=NHTULFRI01SSRNDZDGPRPRB0JYKGUDWTDJIXANEBQ51ME1QO&client_secret=GFAON3F032TY3OOFG33LNOAY4MWMXZWMIYX5LCBIUBHHXHOL&query="+query+"&v=20120213");
System.out.println("https://api.foursquare.com/v2/venues/search?ll="+location+"&client_id=NHTULFRI01SSRNDZDGPRPRB0JYKGUDWTDJIXANEBQ51ME1QO&client_secret=GFAON3F032TY3OOFG33LNOAY4MWMXZWMIYX5LCBIUBHHXHOL&query="+query+"&v=20120213");
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
URLConnection urlConnection = null;
try {
	urlConnection = url.openConnection();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

// Read the response

BufferedReader in = null;
try {
	in = new BufferedReader(
	   new InputStreamReader(urlConnection.getInputStream()));
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
String line = null;
try {
	while ((line = in.readLine()) != null)
	{
	  // System.out.println(line);
	   data.append(line);
	}
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

try {
	in.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
JSONParser parser=new JSONParser();
JSONObject obj = new  JSONObject();
JSONObject loc = new  JSONObject();
JSONArray array = new JSONArray();
JSONArray array1 = new JSONArray();
try {
	obj = (JSONObject)parser.parse(data.toString());
	obj = (JSONObject)obj.get("response");
	System.out.println("obj is "+obj);
	array1 = (JSONArray)obj.get("venues");
	array = (JSONArray)obj.get("venues");
	//System.out.println("size is "+array.size());
	int i = 0;
	for(i=0;i<array1.size();i++){
		Venue v = new Venue();
		array = array1;
	obj  = (JSONObject)array.get(i);
	loc = (JSONObject)obj.get("location");
	v.setName(obj.get("name").toString());
	//System.out.println(""+obj.get("name"));
	if(loc.get("address")==null)
	v.setAddress(" ");
	else
		v.setAddress(loc.get("address").toString()+" ");
	//System.out.println(loc.get("address"));
	v.setDistance(loc.get("distance").toString());
	v.setLat(loc.get("lat").toString());
	//System.out.println(loc.get("lat"));
	v.setLng(loc.get("lng").toString());
	//System.out.println(loc.get("lng"));
	array = (JSONArray)obj.get("categories");
	//System.out.println("cat is "+array);
	v.setCategory("");
	int j=0;
	for(j=0;j<array.size();j++){
	obj = (JSONObject)array.get(0);
	//System.out.println(""+obj.get("name"));
	v.setCategory(v.getCategory()+","+obj.get("name"));
	obj = (JSONObject)obj.get("icon");
	//System.out.println("Prefix is "+obj.get("prefix")+"32.png");
	v.setImgurl(obj.get("prefix")+"32.png");
	}
	venueList.add(v);
	
	
	}
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
int i;

for(i=0;i<venueList.size();i++)
{
	System.out.println(venueList.get(i).getName());
	
}
return venueList;
}
}
