[app](../../index.md) / [com.example.books.domain.bookSearch](../index.md) / [BooksAdapter](./index.md)

# BooksAdapter

`class BooksAdapter : ListAdapter<`[`Book`](../../com.example.books.domain.models/-book/index.md)`, `[`SearchBooksViewHolder`](-search-books-view-holder/index.md)`>`

### Types

| Name | Summary |
|---|---|
| [DiffCallBack](-diff-call-back/index.md) | `companion object DiffCallBack : ItemCallback<`[`Book`](../../com.example.books.domain.models/-book/index.md)`>` |
| [OnClickListener](-on-click-listener/index.md) | `class OnClickListener` |
| [SearchBooksViewHolder](-search-books-view-holder/index.md) | `class SearchBooksViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BooksAdapter(onClickListener: `[`OnClickListener`](-on-click-listener/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`SearchBooksViewHolder`](-search-books-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Old view is recycled and reused by binding new data the view when scrolling |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`SearchBooksViewHolder`](-search-books-view-holder/index.md)<br>Creates the viewholder for the recyclerView |

### Companion Object Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItem: `[`Book`](../../com.example.books.domain.models/-book/index.md)`, newItem: `[`Book`](../../com.example.books.domain.models/-book/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the content of the Book items are the same |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItem: `[`Book`](../../com.example.books.domain.models/-book/index.md)`, newItem: `[`Book`](../../com.example.books.domain.models/-book/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the items are the same |
