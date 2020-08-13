[app](../../index.md) / [com.example.books.data.favorites](../index.md) / [FavoritesDao](./index.md)

# FavoritesDao

`interface FavoritesDao`

FavoritesDAO is an interface that provides all the methods that are necessary to manipulate the favorites
in the database

[Dao](#) Data Access Objects are the main classes where you define your database interactions. $
They can include a variety of query methods.

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Deletes a favorite book in the database |
| [get](get.md) | `abstract fun get(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`BookFavorite`](../-book-favorite/index.md)<br>Gives a favorite book with given id |
| [getAll](get-all.md) | `abstract fun getAll(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookFavorite`](../-book-favorite/index.md)`>>` |
| [insert](insert.md) | `abstract fun insert(favorite: `[`BookFavorite`](../-book-favorite/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a favorite book in the database |
