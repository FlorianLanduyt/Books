[app](../../index.md) / [com.example.books.domain.finishedbooks](../index.md) / [FinishedBooksViewModel](./index.md)

# FinishedBooksViewModel

`class FinishedBooksViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FinishedBooksViewModel(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [bookToRemove](book-to-remove.md) | `val bookToRemove: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [finishedBookAdded](finished-book-added.md) | `val finishedBookAdded: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [finishedBookRemoved](finished-book-removed.md) | `val finishedBookRemoved: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [finishedBooks](finished-books.md) | `val finishedBooks: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`>>` |
| [navigateToBookDetail](navigate-to-book-detail.md) | `val navigateToBookDetail: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`MyBooksApiStatus`](../../com.example.books.domain.book-search/-my-books-api-status/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [getFinishedBooks](get-finished-books.md) | `fun getFinishedBooks(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Get the list of finished books |
| [insertFinishedBook](insert-finished-book.md) | `fun insertFinishedBook(book: `[`Book`](../../com.example.books.domain.models/-book/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a book in the finished books list |
| [onBookFinishedBookRemoved](on-book-finished-book-removed.md) | `fun onBookFinishedBookRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets value of removeFinishedBook to null |
| [onBookFinishedClicked](on-book-finished-clicked.md) | `fun onBookFinishedClicked(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assings value to navigateToBookDetail |
| [onBookFinishedNavigated](on-book-finished-navigated.md) | `fun onBookFinishedNavigated(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets value of navigateToBookDetail to null |
| [onFinishedBookAddClicked](on-finished-book-add-clicked.md) | `fun onFinishedBookAddClicked(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets finishedBookAdded to true |
| [onFinishedBookAdded](on-finished-book-added.md) | `fun onFinishedBookAdded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets finishedBookAdded to false |
| [onFinishedBookRemoved](on-finished-book-removed.md) | `fun onFinishedBookRemoved(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets finishedBooksRemoved to false |
| [onFinishedBookRemovedClicked](on-finished-book-removed-clicked.md) | `fun onFinishedBookRemovedClicked(finishedBook: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Assings value to removeFinishedBook |
| [removeFinishedBook](remove-finished-book.md) | `fun removeFinishedBook(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes the book out of the finished books list |
