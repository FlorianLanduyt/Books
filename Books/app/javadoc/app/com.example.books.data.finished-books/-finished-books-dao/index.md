[app](../../index.md) / [com.example.books.data.finishedBooks](../index.md) / [FinishedBooksDao](./index.md)

# FinishedBooksDao

`interface FinishedBooksDao`

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Deletes a finished book out of the database |
| [get](get.md) | `abstract fun get(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`FinishedBook`](../-finished-book/index.md)<br>Gives a book with a given id from the finished books |
| [getAll](get-all.md) | `abstract fun getAll(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FinishedBook`](../-finished-book/index.md)`>>`<br>Gives all the books you have finished reading from the database |
| [insert](insert.md) | `abstract fun insert(finishedBook: `[`FinishedBook`](../-finished-book/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a finished book in the database |
