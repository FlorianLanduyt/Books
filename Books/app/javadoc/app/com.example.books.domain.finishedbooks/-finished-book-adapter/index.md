[app](../../index.md) / [com.example.books.domain.finishedbooks](../index.md) / [FinishedBookAdapter](./index.md)

# FinishedBookAdapter

`class FinishedBookAdapter : ListAdapter<`[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`, `[`FinishedBookViewHolder`](-finished-book-view-holder/index.md)`>`

### Types

| Name | Summary |
|---|---|
| [FinishedBookDiffCallBack](-finished-book-diff-call-back/index.md) | `companion object FinishedBookDiffCallBack : ItemCallback<`[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`>` |
| [FinishedBookListener](-finished-book-listener/index.md) | `class FinishedBookListener` |
| [FinishedBookViewHolder](-finished-book-view-holder/index.md) | `class FinishedBookViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FinishedBookAdapter(onClickListener: `[`FinishedBookListener`](-finished-book-listener/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`FinishedBookViewHolder`](-finished-book-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Old view is recycled and reused by binding new data the view when scrolling |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`FinishedBookViewHolder`](-finished-book-view-holder/index.md)<br>Creates the viewholder for the recyclerView |

### Companion Object Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItem: `[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`, newItem: `[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the content of the Book items are the same |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItem: `[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`, newItem: `[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the items are the same |
