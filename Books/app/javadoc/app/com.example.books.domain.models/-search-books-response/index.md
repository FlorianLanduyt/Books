[app](../../index.md) / [com.example.books.domain.models](../index.md) / [SearchBooksResponse](./index.md)

# SearchBooksResponse

`data class SearchBooksResponse`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SearchBooksResponse(totalItems: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, books: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Book`](../-book/index.md)`>)` |

### Properties

| Name | Summary |
|---|---|
| [books](books.md) | `val books: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Book`](../-book/index.md)`>` |
| [totalItems](total-items.md) | `val totalItems: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [asDatabaseModel](../as-database-model.md) | `fun `[`SearchBooksResponse`](./index.md)`.asDatabaseModel(): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`DatabaseBook`](../../com.example.books.data.books/-database-book/index.md)`>` |
