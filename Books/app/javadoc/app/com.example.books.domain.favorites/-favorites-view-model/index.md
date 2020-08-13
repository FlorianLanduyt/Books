[app](../../index.md) / [com.example.books.domain.favorites](../index.md) / [FavoritesViewModel](./index.md)

# FavoritesViewModel

`class FavoritesViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FavoritesViewModel(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [bookToNavigateTo](book-to-navigate-to.md) | `val bookToNavigateTo: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [favoriteAdded](favorite-added.md) | `val favoriteAdded: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [favoriteBooks](favorite-books.md) | `val favoriteBooks: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`>>` |
| [favoriteRemoved](favorite-removed.md) | `val favoriteRemoved: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [removeFavoriteBook](remove-favorite-book.md) | `val removeFavoriteBook: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`MyBooksApiStatus`](../../com.example.books.domain.book-search/-my-books-api-status/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [bookInFavorites](book-in-favorites.md) | `fun bookInFavorites(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if the book is in favorites in the detailsfragment |
| [getFavorites](get-favorites.md) | `fun getFavorites(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Gets the list of favorite books |
| [insertBookFavorite](insert-book-favorite.md) | `fun insertBookFavorite(book: `[`Book`](../../com.example.books.domain.models/-book/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a book in the list of favorite books |
| [navigateToBook](navigate-to-book.md) | `fun navigateToBook(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns value to bookToNavigateTo |
| [navigateToBookFinished](navigate-to-book-finished.md) | `fun navigateToBookFinished(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of bookToNavigateTo to null |
| [onBookFavoriteRemoved](on-book-favorite-removed.md) | `fun onBookFavoriteRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of the book to be deleted to null |
| [onBookFavoriteRemovedClicked](on-book-favorite-removed-clicked.md) | `fun onBookFavoriteRemovedClicked(favoriteBookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value for the book to be removed out of the favorites list |
| [onFavoriteAddClicked](on-favorite-add-clicked.md) | `fun onFavoriteAddClicked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets favoriteAdded to true |
| [onFavoriteAdded](on-favorite-added.md) | `fun onFavoriteAdded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets favoriteAdded to false |
| [onFavoriteRemoveClicked](on-favorite-remove-clicked.md) | `fun onFavoriteRemoveClicked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets favoriteRemoved to true |
| [onFavoriteRemoved](on-favorite-removed.md) | `fun onFavoriteRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets favoriteRemoved to false |
| [removeBookFavorite](remove-book-favorite.md) | `fun removeBookFavorite(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes the book from the favorite list |
