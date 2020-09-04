package vladyslav.yarokh.bookssearcherv3.data

data class DataItems (
    val kind: String,
    val totalItems: Int,
    val items: List<Item>
)