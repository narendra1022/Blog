# Blog Viewer App

## Features

- **List of Blogs**: Displays a list of blogs fetched from an API.
- **Detailed Blog Content**: Opens each blog in a full-screen WebView with smooth loading indicators.
- **Error Handling**: Displays meaningful error messages when fetching or loading blogs fails.
- **Modern UI**: Designed with Material Design 3 components for a sleek and consistent user experience.
- **Efficient State Management**: Leverages Kotlin Coroutines and Jetpack Compose to manage UI state efficiently.
- **URL Encoding & Decoding**: Safely handles URLs for blog posts with proper encoding and decoding.

## Technologies Used

- **Kotlin**: A modern, concise, and powerful programming language for Android development.
- **Jetpack Compose**: A fully declarative UI toolkit for building Android UIs with less boilerplate code.
- **WebView**: For rendering HTML content directly in the app.
- **Hilt**: A modern dependency injection framework to manage dependencies.
- **Coroutines**: For managing asynchronous tasks and ensuring smooth background operations.
- **Material 3**: The latest Material Design guidelines for beautiful and accessible UIs.

## Architecture

The **Blog Viewer App** follows the **MVVM (Model-View-ViewModel)** architecture pattern to separate concerns and make the codebase maintainable and scalable. Here's a breakdown of the components:

### 1. **Model**

- **Blog Data Model**: Represents the data structure of a blog post (e.g., title, link, content).
- **Repository**: Handles data fetching, either from a remote API or a local database (if implemented). It abstracts the data layer and provides a clean API for the ViewModel to interact with.
  
### 2. **View**

- **UI Components**: These are the UI elements built using Jetpack Compose, such as `BlogListScreen`, `BlogContentScreen`, and `LoadingIndicator`. These composables display data and interact with the ViewModel to display state updates.
- **WebView**: Displays the content of the selected blog in a full-screen WebView.

### 3. **ViewModel**

- The **BlogViewModel** is responsible for managing UI-related data in a lifecycle-conscious way. It interacts with the repository to fetch blog posts and provides the data to the View via LiveData or StateFlow.
- The ViewModel also handles loading states, error states, and manages navigation between the blog list and blog content screens.

### Data Flow

1. **User Action**: The user interacts with the UI, such as selecting a blog post from the list.
2. **ViewModel**: The ViewModel reacts to the user action and requests data from the repository.
3. **Repository**: The repository fetches the required data, either from a remote API or local database, and returns it to the ViewModel.
4. **UI Updates**: The ViewModel updates the UI with the new data or loading/error states, which are observed by the Composables, triggering UI updates.

### Example Flow:

1. The user opens the **Blog List Screen**.
2. The **BlogViewModel** requests the blog data from the repository.
3. The data is fetched and provided to the **BlogListScreen**.
4. Upon selecting a blog post, the **BlogContentScreen** is displayed using a **WebView** to render the blog content.
