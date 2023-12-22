**Sneaker Shopping App**

Welcome to the Sneaker Shopping App repository! This Android application is your go-to solution for a seamless and enjoyable sneaker shopping experience. Built with Kotlin and Jetpack Compose, and following clean architecture principles with MVVM, this app combines robust development practices with an intuitive user interface.

**Features** :

**Sneaker List Screen**

The landing screen, SneakerListScreen, offers a comprehensive view of the available sneakers. With a clean and visually appealing 2-column grid layout, users can easily explore a wide range of sneakers. The screen is equipped with the following features:

Sorting Functionality: Sort sneakers based on various criteria to find the perfect pair quickly.
Search Bar: Easily locate specific sneakers by using the search bar.
Bottom Bar Navigation: Seamlessly switch between the SneakerListScreen and the CartScreen for a fluid shopping experience.
Sneaker Details Screen

**SneakerDetailsScreen**

The SneakerDetailsScreen provides information about a selected sneaker. Users can explore details such as design, size availability, and pricing. The screen also allows users to add the selected sneaker to their cart.

**Cart Screen**

The CartScreen is a central hub for managing selected sneakers. Here, users can:
View Cart Contents: See a list of sneakers added to the cart.
Remove Items: Easily remove sneakers from the cart.
Total Price Display: Get a clear overview of the total price of all items in the cart.


**Technical Details**

**Data Source**: The application uses hardcoded JSON data for a smooth UI experience. The absence of actual API calls is compensated by a delay method that simulates loading times.

**Room Database**: Sneakers added to the cart and removed from the cart are stored using Room Database, providing a robust and persistent solution for managing cart data.

**Hilt for Dependency Injection**: The app leverages Hilt for dependency injection, streamlining the management of dependencies. One of the key advantages of using dependency injection is the enhanced testability and maintainability of the codebase.

**Cart Icon on Listing Screen**: The "Add to Cart" icon on the listing screen doesn't consider size. Currently, it adds the product to the cart with size 0. For a more accurate representation, a dialog to select the size should be added in future iterations.


**Tech Stack**

**Kotlin**: The modern and expressive programming language for Android app development.

**Jetpack Compose**: A modern UI toolkit that simplifies and accelerates UI development.

**Clean Architecture**: A modular and scalable architecture for building maintainable software.

**MVVM (Model-View-ViewModel)**: A design pattern that separates the application logic into three components, enhancing maintainability and testability.

**Room Database**: A local database solution for storing and managing cart data.

**Hilt for Dependency Injection**: A framework for simplifying dependency injection in Android applications.


Getting Started
To run the Sneaker Shopping App locally, follow these steps:

Clone the repository: git clone https://github.com/shivanshumishra/sneakersapplication.git
Open the project in Android Studio.
Build and run the app on an Android emulator or physical device.
