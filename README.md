# -sd44cM4k2-abDa8-1eD4-a3Zc1-8bd44sac1Dvwa

The service updates itself with the latest info on building the application.
There are times however when the covid api does not return the countries(probably, because its being updated)
For such cases I have created the PUT / summary endpoint to update the data by force.

There are two other GET endpoints:
**/summary/countries** - returns all countries
**/country/{countryCode}** - returns country by a specific country code


For running the application I use the following run configuration in IntelliJ.
![image](https://user-images.githubusercontent.com/48070027/222758705-095b8955-1b15-4458-996f-166262a61b3b.png)
