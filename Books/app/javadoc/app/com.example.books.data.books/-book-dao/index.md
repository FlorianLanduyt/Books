[app](../../index.md) / [com.example.books.data.books](../index.md) / [BookDao](./index.md)

# BookDao

`interface BookDao`

### Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | `abstract fun clear(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clears the books of the database |
| [getBooks](get-books.md) | `abstract fun getBooks(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`DatabaseBook`](../-database-book/index.md)`>>` |
| [insertAll](insert-all.md) | `abstract fun insertAll(vararg book: `[`DatabaseBook`](../-database-book/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>insert an amount of books into database |
