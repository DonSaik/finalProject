# Final Project

This api gets your bmi and suggests activities by its value by given location (latidude and longtidude). Also, stores response values in database. 

Before using this API change google API key in GoogleAPIClient class.

Use Post method /FinalProject/api with body:
{
    "nick": "Name",
    "lat": 54.893482, 
    "lng": 23.944413,
    "mass": 100,
    "height": 1.8
}
to get response.
