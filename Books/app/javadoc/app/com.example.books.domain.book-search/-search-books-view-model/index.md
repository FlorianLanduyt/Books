[app](../../index.md) / [com.example.books.domain.bookSearch](../index.md) / [SearchBooksViewModel](./index.md)

# SearchBooksViewModel

`class SearchBooksViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SearchBooksViewModel(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [authors](authors.md) | `val authors: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [books](books.md) | `val books: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Book`](../../com.example.books.domain.models/-book/index.md)`>>` |
| [navigateToSelectedBook](navigate-to-selected-book.md) | `val navigateToSelectedBook: LiveData<`[`Book`](../../com.example.books.domain.models/-book/index.md)`>` |
| [status](status.md) | `val status: LiveData<`[`MyBooksApiStatus`](../-my-books-api-status/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [displayBookDetails](display-book-details.md) | `fun displayBookDetails(book: `[`Book`](../../com.example.books.domain.models/-book/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value to navigateToSelectedBook |
| [displayBookDetailsComplete](display-book-details-complete.md) | `fun displayBookDetailsComplete(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clears the value of navigateToSelectedBook |
| [getBooks](get-books.md) | `fun getBooks(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, filter: `[`BookApiFilter`](../../com.example.books.network/-book-api-filter/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Gets the books |
| [onCleared](on-cleared.md) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Will be called when the viewmodel is no longer used and will be destroyed |
