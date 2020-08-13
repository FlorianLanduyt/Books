[app](../../index.md) / [com.example.books.data.repositories](../index.md) / [BookRepository](./index.md)

# BookRepository

`class BookRepository`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BookRepository(database: `[`BookDatabase`](../../com.example.books.data/-book-database/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [books](books.md) | `val books: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Book`](../../com.example.books.domain.models/-book/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| [clearBooks](clear-books.md) | `suspend fun clearBooks(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clears the book database |
| [getBook](get-book.md) | `suspend fun getBook(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Book`](../../com.example.books.domain.models/-book/index.md)<br>Gets the book with the given id from the backend |
| [refreshBooks](refresh-books.md) | `suspend fun refreshBooks(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, filter: `[`BookApiFilter`](../../com.example.books.network/-book-api-filter/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Refreshes the books after a search query has entered by sending a request to the back-end or by retrieving them from the database |
