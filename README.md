# Magine TV (Android)

## System Requirements
----------------------
Android Studio 3.3.2

Gradle System 23.0.3

Android SDK (version 21 or later)

## DETAIL
----------------------
1. Tools Used : Minimum Android 5.0 Lollipop (API 21), Build Tool Version 28.0.3, Kotlin - 1.3.21
   
2. Library Used : Using these libraries for api call and image load.

      1.) volley (com.mcxiaoke.volley:library:1.0.19) -> Using volley for api call

      2.) Gson (com.google.code.gson:gson:2.8.2) -> for encoding and decoding

      3.) picasso (com.squareup.picasso:picasso:2.71828) -> loading image from server

## App Info
----------------------
### 1. List Api Call
<table>
  <tr><td><img src="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/device-2019-04-07-223328.png" data-canonical-src="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/device-2019-04-07-223328.png" width="300"/>
    </td>
    <td>
      <p> Api Used-> http://api.tvmaze.com/shows?page=1
      <p>You will find more info in <a href="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/app/src/main/java/com/magine/api/Api.kt">Api Class</a>
        <p>List will autoload next page content while you scroll to end of the list
      </td>
  </tr>
  <table>
    
 ### 2. Search Api Call
   <table>
  <tr><td><img src="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/device-2019-04-07-223430.png" data-canonical-src="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/device-2019-04-07-223430.png" width="300"/>
    </td>
    <td>
      <p> Api Used-> http://api.tvmaze.com/search/shows?q=girls
      <p>You will find more info in <a href="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/app/src/main/java/com/magine/api/Api.kt">Api Class</a>
      </td>
  </tr>
  <table>
    
### 3. Show info screen
  <table>
  <tr><td><img src="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/device-2019-04-07-223451.png" data-canonical-src="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/device-2019-04-07-223451.png" width="300"/>
    </td>
    <td>
      <p> Api Used-> http://api.tvmaze.com/shows/2
      <p>You will find more info in <a href="https://github.com/irahulyadav/Tvmaze-Android-Test/blob/master/app/src/main/java/com/magine/api/Api.kt">Api Class</a>
      </td>
  </tr>
  <table>
    
   **[Download Debug Flavor Apk](https://github.com/irahulyadav/Tvmaze-Android-Test/raw/master/app/debug/app-debug.apk)** - This is the test version
