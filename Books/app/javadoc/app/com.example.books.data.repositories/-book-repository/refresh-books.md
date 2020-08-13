[app](../../index.md) / [com.example.books.data.repositories](../index.md) / [BookRepository](index.md) / [refreshBooks](./refresh-books.md)

# refreshBooks

`suspend fun refreshBooks(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, filter: `[`BookApiFilter`](../../com.example.books.network/-book-api-filter/index.md)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Refreshes the books after a search query has entered by sending a request to the back-end or by retrieving them from the database

### Parameters

`title` - the title of the book

`filter` - the filter of the books (all, e-books or free e-books)