package com.cit.placepicker;

public class Venue {
private String name;
private String lat;
private String lng;
private String address;
private String category;
private String imgurl;
private String distance ;
public void setDistance(String distance)
{
	this.distance = distance;
	
}
public String getDistance()
{
	
	return this.distance;
}
public void setCategory(String category)
{
	
	this.category = category;
}
public String getCategory()
{
	
	return this.category;
}

public String getImgurl(String imgurl)
{
	return this.imgurl;
	
}
public void setImgurl(String imgurl)
{
	this.imgurl = imgurl;
	
}
public String getName()
{
	
	return name;
}
public void setName(String name)
{
	
	this.name = name;
}
public String getLat()
{
	
	return lat;
}
public String getLng()
{
	
	return lng;
}
public void  setLat(String lat)
{
this.lat = lat;	
}
public void setLng(String lng)
{
	this.lng = lng;
	
}
public void setAddress(String address)
{
this.address = address;	
}
public String getAddress(String address)
{
	return this.address;
	
}

}
