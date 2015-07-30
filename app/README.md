#About HQInterviewDhara

This application is part of the AndroidRound-1 test. It gets a list of webviews from the base url.
Some webview contents have to be preloaded while some others have to be loaded on user request.

#Dependencies
    * AppCompatLibrary
    * Android Support v4
    * Guava collections
    * GSON

#How to build the application
This application is developed using AndroidStudio and built using gradle. Dependencies are fetched from jcenter.
The apk generated after building this project will be located in the folder /app/build/outputs/apk

#Testing environment
This application is tested on Gingerbread and Lollipop versions
The app is supported from 2.2 api levels to 5.1

#Issues
The tag ordersSavings does not have the apiSecretKey param in the list of params, without which a "restricted access" error is thrown.
To prevent this, the param has been added to the object whenever it is tapped.