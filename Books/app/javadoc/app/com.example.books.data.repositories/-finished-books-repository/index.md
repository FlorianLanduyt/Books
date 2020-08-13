[app](../../index.md) / [com.example.books.data.repositories](../index.md) / [FinishedBooksRepository](./index.md)

# FinishedBooksRepository

`class FinishedBooksRepository`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FinishedBooksRepository(database: `[`BookDatabase`](../../com.example.books.data/-book-database/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [finishedBooks](finished-books.md) | `var finishedBooks: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| [getAll](get-all.md) | `suspend fun getAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Refreshes all finished books by retrieving them from the database |
| [getFinishedBook](get-finished-book.md) | `suspend fun getFinishedBook(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)<br>Gets a finished book with given id |
| [insertFinishedBook](insert-finished-book.md) | `suspend fun insertFinishedBook(finishedBook: `[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a finished book in the database |
| [removeFinishedBook](remove-finished-book.md) | `suspend fun removeFinishedBook(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a finished book from the database |
