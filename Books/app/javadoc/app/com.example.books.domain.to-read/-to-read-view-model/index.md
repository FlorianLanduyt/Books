[app](../../index.md) / [com.example.books.domain.toRead](../index.md) / [ToReadViewModel](./index.md)

# ToReadViewModel

`class ToReadViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ToReadViewModel(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [bookToNavigateTo](book-to-navigate-to.md) | `val bookToNavigateTo: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [removeToReadBook](remove-to-read-book.md) | `val removeToReadBook: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [toReadAdded](to-read-added.md) | `val toReadAdded: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [toReadBooks](to-read-books.md) | `val toReadBooks: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`>>` |
| [toReadRemoved](to-read-removed.md) | `val toReadRemoved: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [getToReads](get-to-reads.md) | `fun getToReads(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Gets the books from the toread list |
| [insertBookToRead](insert-book-to-read.md) | `fun insertBookToRead(book: `[`Book`](../../com.example.books.domain.models/-book/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a book in the book to read list |
| [navigateToBook](navigate-to-book.md) | `fun navigateToBook(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value to bookToNavigateTo |
| [navigateToBookFinished](navigate-to-book-finished.md) | `fun navigateToBookFinished(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the value of bookToNavigateTo to null |
| [onBookToReadRemoved](on-book-to-read-removed.md) | `fun onBookToReadRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets removeToReadBook to null |
| [onBookToReadRemovedClicked](on-book-to-read-removed-clicked.md) | `fun onBookToReadRemovedClicked(toReadBookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assigns a value to removeToReadBook |
| [onToReadBookAddClicked](on-to-read-book-add-clicked.md) | `fun onToReadBookAddClicked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets toReadAdded to true |
| [onToReadBookAdded](on-to-read-book-added.md) | `fun onToReadBookAdded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets toReadAdded to false |
| [onToReadBookRemoveClicked](on-to-read-book-remove-clicked.md) | `fun onToReadBookRemoveClicked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets toReadRemoved to true |
| [onToReadRemoved](on-to-read-removed.md) | `fun onToReadRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets toReadRemoved to false |
| [removeBookToRead](remove-book-to-read.md) | `fun removeBookToRead(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a book out of the books to read list |
