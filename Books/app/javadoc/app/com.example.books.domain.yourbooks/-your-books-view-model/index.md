[app](../../index.md) / [com.example.books.domain.yourbooks](../index.md) / [YourBooksViewModel](./index.md)

# YourBooksViewModel

`class YourBooksViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `YourBooksViewModel(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [bookToNavigateTo](book-to-navigate-to.md) | `val bookToNavigateTo: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [favoriteBooks](favorite-books.md) | `val favoriteBooks: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`>>` |
| [finishedBooks](finished-books.md) | `val finishedBooks: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`>>` |
| [generalStatus](general-status.md) | `val generalStatus: LiveData<`[`YourBooksStatus`](../-your-books-status/index.md)`>` |
| [removeBookToRead](remove-book-to-read.md) | `val removeBookToRead: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [removeFavoriteBook](remove-favorite-book.md) | `val removeFavoriteBook: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [removeFinishedBook](remove-finished-book.md) | `val removeFinishedBook: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [statusBookToRead](status-book-to-read.md) | `val statusBookToRead: LiveData<`[`YourBooksStatus`](../-your-books-status/index.md)`>` |
| [statusFavorites](status-favorites.md) | `val statusFavorites: LiveData<`[`YourBooksStatus`](../-your-books-status/index.md)`>` |
| [statusFinishedBooks](status-finished-books.md) | `val statusFinishedBooks: LiveData<`[`YourBooksStatus`](../-your-books-status/index.md)`>` |
| [toReadBooks](to-read-books.md) | `val toReadBooks: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| [navigateToBook](navigate-to-book.md) | `fun navigateToBook(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value to bookToNavigateTo |
| [navigateToBookFinished](navigate-to-book-finished.md) | `fun navigateToBookFinished(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of bookToNavigateTo to null |
| [onBookFavoriteRemoved](on-book-favorite-removed.md) | `fun onBookFavoriteRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of the book to be deleted to null |
| [onBookFavoriteRemovedClicked](on-book-favorite-removed-clicked.md) | `fun onBookFavoriteRemovedClicked(favoriteBookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value for the book to be removed out of the favorites list |
| [onBookToReadRemoved](on-book-to-read-removed.md) | `fun onBookToReadRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of the book to be deleted to null |
| [onBookToReadRemovedClicked](on-book-to-read-removed-clicked.md) | `fun onBookToReadRemovedClicked(bookToReadId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value for the book to be removed out of the To read list |
| [onFinishedBookRemoved](on-finished-book-removed.md) | `fun onFinishedBookRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of the book to be deleted to null |
| [onFinishedBookRemovedClicked](on-finished-book-removed-clicked.md) | `fun onFinishedBookRemovedClicked(finishedBookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value for the book to be removed out of the finished books list |
| [refreshBooksToRead](refresh-books-to-read.md) | `fun refreshBooksToRead(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Refreshes the to read books |
| [refreshFavoriteBooks](refresh-favorite-books.md) | `fun refreshFavoriteBooks(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Refreshes the favorite books |
| [refreshFinishedBooks](refresh-finished-books.md) | `fun refreshFinishedBooks(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Refreshes the finished books |
| [removeFavorite](remove-favorite.md) | `fun removeFavorite(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a book out of the favorite books list |
| [removeFinished](remove-finished.md) | `fun removeFinished(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a book out of the finished books list |
| [removeToRead](remove-to-read.md) | `fun removeToRead(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a book out of the to read list |
