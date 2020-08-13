[app](../../index.md) / [com.example.books.data.toread](../index.md) / [ToReadDao](./index.md)

# ToReadDao

`interface ToReadDao`

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a book you want to read from the database |
| [get](get.md) | `abstract fun get(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`BookToRead`](../-book-to-read/index.md)<br>Gives the book you want to read with given id |
| [getAll](get-all.md) | `abstract fun getAll(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookToRead`](../-book-to-read/index.md)`>>`<br>Refreshes the books you want to read by retrieving them from the database |
| [insert](insert.md) | `abstract fun insert(toRead: `[`BookToRead`](../-book-to-read/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts the book you want to read in the database |
