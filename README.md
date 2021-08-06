<h1 align="center">Dogs App</h1>

<p align="center">  
 Dog App using Java, Jetpack components, MVVM, Retrofit and RxJava</br>
</p>
</br>
<p align="center">
  <img src="/previews/DogsAppGif1.gif" width="21%"/>
  <img src="/previews/DogsAppNotificationGif.gif" width="21%"/>
</p>

## Features
- List of Dogs
- Details about each Dog
- Retrieve Dog Data from API
- Caches the data in Local Database
- Notify when retrieved from API
- Color Palettes matching Dog photos
- Settings to adjust Cache duration

<p align="center">
  <img src="/previews/DogsAppCacheGif.gif" width="21%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 16
- Java based
- MVVM Architecture
- Retrofit and RxJava is used to consume the API
- Room is used to store the retrieved data in local SQLite DB
- JetPack components: Navigation, LiveData, ViewModel
- Used ButterKnife to bind UI elements
- Implemented DataBinding
- Glide and Palette Library for loading images and colors
- Notifies the user when retrieved from Endpoint
- Used Settings Fragment to update the Cache Duration
