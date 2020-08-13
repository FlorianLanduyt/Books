[app](../../index.md) / [com.example.books.domain](../index.md) / [BindingAdapters](./index.md)

# BindingAdapters

`class BindingAdapters`

Uses Glide library to load an image by URL into an [ImageView](https://developer.android.com/reference/android/widget/ImageView.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BindingAdapters()`<br>Uses Glide library to load an image by URL into an [ImageView](https://developer.android.com/reference/android/widget/ImageView.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [bindAuthors](bind-authors.md) | `fun bindAuthors(textView: `[`TextView`](https://developer.android.com/reference/android/widget/TextView.html)`, authors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binding the authors to a string |
| [bindImage](bind-image.md) | `fun bindImage(imgView: `[`ImageView`](https://developer.android.com/reference/android/widget/ImageView.html)`, thumbnail: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Downloads imageUri with [Glide](#) Glide will set the imageUri in the imageView |
| [bindStatus](bind-status.md) | `fun bindStatus(statusImageView: `[`ImageView`](https://developer.android.com/reference/android/widget/ImageView.html)`, status: `[`MyBooksApiStatus`](../../com.example.books.domain.book-search/-my-books-api-status/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binding the status of the book to image of the status |
| [bindStatusText](bind-status-text.md) | `fun bindStatusText(textView: `[`TextView`](https://developer.android.com/reference/android/widget/TextView.html)`, status: `[`MyBooksApiStatus`](../../com.example.books.domain.book-search/-my-books-api-status/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binding the status of the book to text of the status |
| [bindingRecycleView](binding-recycle-view.md) | `fun bindingRecycleView(recyclerView: RecyclerView, data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Book`](../../com.example.books.domain.models/-book/index.md)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Maniulating the data to |
| [bindingRecycleViewFavorites](binding-recycle-view-favorites.md) | `fun bindingRecycleViewFavorites(recyclerView: RecyclerView, data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookFavorite`](../../com.example.books.data.favorites/-book-favorite/index.md)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Maniulating the data to |
| [bindingRecycleViewFinishedBooks](binding-recycle-view-finished-books.md) | `fun bindingRecycleViewFinishedBooks(recyclerView: RecyclerView, data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`FinishedBook`](../../com.example.books.data.finished-books/-finished-book/index.md)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Maniulating the data to |
| [bindingRecycleViewToRead](binding-recycle-view-to-read.md) | `fun bindingRecycleViewToRead(recyclerView: RecyclerView, data: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`BookToRead`](../../com.example.books.data.toread/-book-to-read/index.md)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Maniulating the data to |
