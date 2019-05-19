Instructions for the Gambit Challenge made by Mikael Fagerström.

App is created in android studios written in Kotlin.

App starts by reading in two json arrays. One containing registry values and the other manual values for registers.
After arrays are read they go into the parser class which loops through the manual value array and does parsing and combining with the registry array.
Once parsing is complete it returns a list of the ParsedItem class containing parsed values.
This list is passed on to a recyclerview adapter that binds the items to the recyclerview.
If an item in the recyclerview is clicked it will open a new activity containing more information about the selected value. Here you will find information on how I've converted the registry values.
If you are satisfied with the parsed values you can press on the post button and app will using volley do a post request with the parsed list containing just the values seen in the recyclerview.

In the ScreenShot folder of the project you can find both pictures and a video of the app running as well as pictures of some code.

The Parser class is actually pretty much the same as my last years contribution to this challenge. However that was written in c# in this is in Kotlin. I also made some changes to the code for certain manual values which were incorrect.
As i don't have the correct parsed values cheatsheet i can't be 100% sure all values are correct but at least the ones from the instructions are on point.

It was fun to tackle this challenge again this year with a little more tools in my aresenal.