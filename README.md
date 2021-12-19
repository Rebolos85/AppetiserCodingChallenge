# AppetiserCodingChallenge

Create a master-detail application that contains at least one dependency. This application should display a list of items obtained from a iTunes Search API and show a detailed view of each item. The URL you must obtain your data from is as follows:

https://itunes.apple.com/search?term=star&country=au&media=movie&;all

(iTunes Web Service Documentation: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching)

Getting Started Clone this repository by using the comamnd lines below

git clone https://github.com/Rebolos85/Appetiser-Coding-Challenge.git
find the directory folder
import it via Android Studio

Technologies Used

RxKotlin & Rx Android - to handle multi threading especially on calling network request and database operation to make the app have a good user experience
because multi threading helps the app to run smooth and efficient.

Dagger Android - to easily handle dependency injection to the consumer classes without using manual injection to make the code
testable and maintainable along in the long run

Retrofit - to easily connect with webservices

Glide- to display images and also to keep taking care of caching and keeping a low memory impact when doing image manipulations.

Swipe Refresher- in order to provide a good user experience when the images are loaded in the recycle view

AndroidX Room - SQLite ORM for Android, for a much lesser boilerplate code and to prevent runtime error on our queries statement which I believe that it will
be really hard to debug.

Project Architecture

This app uses the MVVM architecture as recommended by Google with repository pattern and usecase to handle the domain layer

 adapter- data source in order to populate the recycle view
 
 base - these are the common base classes to all child clases which I believe it will help us to reduce the boiler plate and to follow the SOLID Principle by   defining  what are the common methods and fields for the subclasses
 
common - responsible for defining what are the common extension function in our views of the app
 
 data - constains repository which it has data source coming from local and remote source
 
 di - defining all the depedencies that are needed for consumer classes to make the app testable and maintanable
 
 domain - constains a domain model which is the data source to update our UI 
 
 ext- constains an extension function 
 
 features- constains the view model, activities and sealed classes
 
 local- data source for local and it constains dao, db,and Music Local Entity Model
 
 network - data source for remote which it constains the api and request model and base responses
 
 usecases - for business logic of the app in order that the viewmodel will not be bloated 
 
 utils- constains base scheduler interface to easily call this interface to the consumer classes and also it constains
 mapper interface to map the dto to cache model and lastly to the domain model
 
 What are the benefits of this architecture? It has the following benefits below are:

 -separation of concerns- each layer has it's only specific job that it does not interfere with the other layers.

 -Repository Pattern- to abstract multiple data source, whether it maybe local cache or from a network fetch by using mapper interface
  to map the dto to cache model and also to help map the cache data to domain model
  -usecases - for handling business logic or domain layer
  
  To know more about this architecture you may visit this link below
  -https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576
  - https://medium.com/swlh/repository-pattern-in-android-c31d0268118c
  - https://www.tutorialspoint.com/mvvm/mvvm_advantages.htm
 
Authors and Acknowledgment This is for Appetiser Apps submission purposes only.

Roberto A Rebolos Jr

