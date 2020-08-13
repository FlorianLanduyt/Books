[app](../../index.md) / [com.example.books.domain.favorites](../index.md) / [FavoritesAdapter](./index.md)

# FavoritesAdapter

`class FavoritesAdapter : ListAdapter<`[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`, `[`FavoritesBookViewHolder`](-favorites-book-view-holder/index.md)`>`

### Types

| Name | Summary |
|---|---|
| [DiffCallBack](-diff-call-back/index.md) | `companion object DiffCallBack : ItemCallback<`[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`>` |
| [FavoriteListener](-favorite-listener/index.md) | `class FavoriteListener` |
| [FavoritesBookViewHolder](-favorites-book-view-holder/index.md) | `class FavoritesBookViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FavoritesAdapter(onClickListener: `[`FavoriteListener`](-favorite-listener/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`FavoritesBookViewHolder`](-favorites-book-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Old view is recycled and reused by binding new data the view when scrolling |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`FavoritesBookViewHolder`](-favorites-book-view-holder/index.md)<br>Creates the viewholder for the recyclerView |

### Companion Object Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItem: `[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`, newItem: `[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the content of the Book items are the same |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItem: `[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`, newItem: `[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the items are the same |
