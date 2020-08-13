[app](../../index.md) / [com.example.books.data.repositories](../index.md) / [ToReadRepository](./index.md)

# ToReadRepository

`class ToReadRepository`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ToReadRepository(database: `[`BookDatabase`](../../com.example.books.data/-book-database/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [booksToRead](books-to-read.md) | `var booksToRead: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| [getBookToRead](get-book-to-read.md) | `suspend fun getBookToRead(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)<br>Gets a book you want to read from the database |
| [insertBookToRead](insert-book-to-read.md) | `suspend fun insertBookToRead(bookToRead: `[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a book you want to read into the database |
| [refreshBooksToRead](refresh-books-to-read.md) | `suspend fun refreshBooksToRead(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Refreshes the books you want to read by retrieving them from the database |
| [removeBookToRead](remove-book-to-read.md) | `suspend fun removeBookToRead(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a book you want to read with given id |
