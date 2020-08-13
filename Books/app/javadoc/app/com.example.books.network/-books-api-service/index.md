[app](../../index.md) / [com.example.books.network](../index.md) / [BooksApiService](./index.md)

# BooksApiService

`interface BooksApiService`

### Functions

| Name | Summary |
|---|---|
| [getBookById](get-book-by-id.md) | `abstract fun getBookById(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Deferred<`[`SearchBooksResponse`](../../com.example.books.domain.models/-search-books-response/index.md)`>`<br>Gets a book on id |
| [getBooksOnName](get-books-on-name.md) | `abstract fun getBooksOnName(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, filter: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Deferred<`[`SearchBooksResponse`](../../com.example.books.domain.models/-search-books-response/index.md)`>`<br>Gets all the books with a given title |
