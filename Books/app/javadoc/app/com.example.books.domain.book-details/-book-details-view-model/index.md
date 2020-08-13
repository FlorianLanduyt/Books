[app](../../index.md) / [com.example.books.domain.bookDetails](../index.md) / [BookDetailsViewModel](./index.md)

# BookDetailsViewModel

`class BookDetailsViewModel : AndroidViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BookDetailsViewModel(app: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [authors](authors.md) | `val authors: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [moreText](more-text.md) | `val moreText: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [selectedBook](selected-book.md) | `val selectedBook: LiveData<`[`Book`](../../com.example.books.domain.models/-book/index.md)`>` |
| [status](status.md) | `val status: LiveData<`[`BookApiStatus`](../-book-api-status/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [getBookProperties](get-book-properties.md) | `fun getBookProperties(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Gets the bookdetails of a book with given id |
| [lessText](less-text.md) | `fun lessText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of more text to false |
| [moreText](more-text.md) | `fun moreText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of more text to true |
| [onCleared](on-cleared.md) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Will be called when the viewmodel is no longer used and will be destroyed |
