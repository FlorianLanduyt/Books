[app](../../index.md) / [com.example.books.data.repositories](../index.md) / [FavoritesRepository](./index.md)

# FavoritesRepository

`class FavoritesRepository`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FavoritesRepository(database: `[`BookDatabase`](../../com.example.books.data/-book-database/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [favorites](favorites.md) | `var favorites: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| [getBookFavorite](get-book-favorite.md) | `suspend fun getBookFavorite(bookId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)<br>Gets a favorite book with given id from the database |
| [insertBookFavorite](insert-book-favorite.md) | `suspend fun insertBookFavorite(bookFavorite: `[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Inserts a book into the favorites |
| [refreshFavoriteBooks](refresh-favorite-books.md) | `suspend fun refreshFavoriteBooks(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Refreshes the favorite books by retrieving them from the database |
| [removeBookFavorite](remove-book-favorite.md) | `suspend fun removeBookFavorite(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a favorite book from the database |
