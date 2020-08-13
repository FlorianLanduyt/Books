[app](../../index.md) / [com.example.books.network](../index.md) / [BooksApiService](index.md) / [getBooksOnName](./get-books-on-name.md)

# getBooksOnName

`@GET("volumes") abstract fun getBooksOnName(@Query("q") query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, @Query("filter") filter: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Deferred<`[`SearchBooksResponse`](../../com.example.books.domain.models/-search-books-response/index.md)`>`

Gets all the books with a given title

### Parameters

`query` - the title of the book

`filter` - the filter of the list (All books, e-books, free e-books)

**Return**
the books

