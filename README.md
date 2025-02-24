# CookpadClone

CookpadClone is an Android app that displays collections and recipes using the Cookpad API. It follows Single Activity Architecture with Clean Architecture + MVVM pattern.

---

## Features

- **Collections Screen:** Lists collections; click to view recipes for each collection.
- **Recipes Screen:** Displays top recipes; click for detailed recipe information.
- **Recipe Details Screen:** Shows author, ingredients, steps, and images.
- **Offline Handling:** Handles server down and no internet scenarios.
- **Image Loading:** Uses Coil library with placeholders.
- **Configuration Changes:** Retains state with ViewModel.

---

## Tech Stack

- **Language:** Kotlin (100%)
- **Architecture:** Clean Architecture + MVVM
- **UI:** XML (Single Activity Architecture)
- **Networking:** Retrofit
- **Dependency Injection:** Dagger Hilt
- **Image Loading:** Coil
- **Navigation:** Nav Graph with SafeArgs
- **Async Operations:** Coroutines
- **Testing:** JUnit4, MockK, Turbine

---

## Architecture Overview

The app follows Single Activity Architecture as shown below:

![Single Activity Architecture](https://github.com/hiteshchopra11/CookpadClone/blob/main/cookpad.png)

- **HomeActivity**: Hosts all fragments.
- **HomeFragment (Default)**: Uses ViewPager2 for navigation.
- **CollectionsFragment**: Displays a list of collections.
- **CollectionRecipesFragment**: Shows recipes for a selected collection.
- **RecipesFragment**: Displays top recipes.
- **RecipeDetailsFragment**: Shows detailed information about a recipe.

---

## Links

- [Source Code](https://github.com/hiteshchopra11/CookpadClone)
- [APK Download](https://github.com/hiteshchopra11/CookpadClone/blob/main/CookpadClone.apk)
- [Demo Video](https://drive.google.com/file/d/1FTEwHNHBjOVIRYlFweEJxavu5-_i6bhb/view?usp=sharing)

---

## Future Enhancements

- Add more unit tests and UI tests.
- Improve UI with a collapsing toolbar and app icon.
- Design separate layouts for landscape and smaller screens.
