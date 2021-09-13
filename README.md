# WeatherApp

## App to get the weather according to your location or by city name 

My application is consisit of three screens 
- First screen is a landing screen that navigate to other screens.
- Second screen is Get Weather By name : in this screen you can input atleast 3 of city names or maximum 7 and app will get to your the weather of these cities.
- third screen is Get Weather By location : in this screen it shows the result of 5 days 3 hours according to your location so you need to give this app the permission to access your location.

## Permissions

- INTERNET
- LOCATION


## Development Notes

this app is written by java with architecture pattern MVVM also used Retorit to call api's, Dagger is used as a DI library and the communication between layers done through LiveData

app divided into three modules
- data   : that has retrofit service, data source models and repos
- Module : that has Dependency injection modules
- UI     : that has view and viewModel

## used libraries
- Dagger
- gson
- lifecycle
- retrofit2

## Screenshots

<p float="left" >

<img src="https://user-images.githubusercontent.com/37122820/133034920-9b77c8b1-d0ef-42d8-8b5e-2babc403c9f3.jpg" width="200" height="400" hspace="20">

<img src="https://user-images.githubusercontent.com/37122820/133035133-d40294c9-37ad-43cb-bedd-a749d98606f2.jpg" width="200" height="400" hspace="20">
      
<img src="https://user-images.githubusercontent.com/37122820/133038865-c219d0fe-d1af-4d23-82c7-9e26fad7b03b.jpg" width="200" height="400" hspace="20">
     
</p>


