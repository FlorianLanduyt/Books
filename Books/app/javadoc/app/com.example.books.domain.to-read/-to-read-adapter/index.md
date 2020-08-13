[app](../../index.md) / [com.example.books.domain.toRead](../index.md) / [ToReadAdapter](./index.md)

# ToReadAdapter

`class ToReadAdapter : ListAdapter<`[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`, `[`ToReadBookViewHolder`](-to-read-book-view-holder/index.md)`>`

### Types

| Name | Summary |
|---|---|
| [DiffCallBack](-diff-call-back/index.md) | `companion object DiffCallBack : ItemCallback<`[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`>` |
| [ToReadBookViewHolder](-to-read-book-view-holder/index.md) | `class ToReadBookViewHolder : ViewHolder` |
| [ToReadListener](-to-read-listener/index.md) | `class ToReadListener` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ToReadAdapter(onClickListener: `[`ToReadListener`](-to-read-listener/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`ToReadBookViewHolder`](-to-read-book-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Old view is recycled and reused by binding new data the view when scrolling |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ToReadBookViewHolder`](-to-read-book-view-holder/index.md)<br>Creates the viewholder for the recyclerView |

### Companion Object Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItem: `[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`, newItem: `[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the content of the Book items are the same |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItem: `[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`, newItem: `[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the items are the same |
