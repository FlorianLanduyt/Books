[app](../../index.md) / [com.example.books.data](../index.md) / [BookDatabase](./index.md)

# BookDatabase

`abstract class BookDatabase : RoomDatabase`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BookDatabase()` |

### Properties

| Name | Summary |
|---|---|
| [bookDao](book-dao.md) | `abstract val bookDao: `[`BookDao`](../../com.example.books.data.books/-book-dao/index.md) |
| [favoritesDao](favorites-dao.md) | `abstract val favoritesDao: `[`FavoritesDao`](../../com.example.books.data.favorites/-favorites-dao/index.md) |
| [finishedBooksDao](finished-books-dao.md) | `abstract val finishedBooksDao: `[`FinishedBooksDao`](../../com.example.books.data.finished-books/-finished-books-dao/index.md) |
| [toReadDao](to-read-dao.md) | `abstract val toReadDao: `[`ToReadDao`](../../com.example.books.data.toread/-to-read-dao/index.md) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [getInstance](get-instance.md) | `fun getInstance(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`BookDatabase`](./index.md) |
