package vladyslav.yarokh.bookssearcherv3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.books_view_holder.view.*
import vladyslav.yarokh.bookssearcherv3.Constants
import vladyslav.yarokh.bookssearcherv3.R
import vladyslav.yarokh.bookssearcherv3.data.DataItems
import vladyslav.yarokh.bookssearcherv3.data.Item

class BooksAdapter(
    private var items: DataItems,
    private val context: Context,
    private val callback: BookItemCallback
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BooksViewHolder =
        BooksViewHolder(
            LayoutInflater.from(context).inflate(R.layout.books_view_holder, parent, false)
        )

    override fun getItemCount(): Int = items.totalItems

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        if(position < Constants.MAX_RESULTS) holder.bind(items.items[position])
    }

    inner class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.apply {
                tv_book_title.text = item.volumeInfo?.title
                tv_book_author.text = item.volumeInfo?.authors?.first()
                tv_book_desc.text = item.volumeInfo?.description
                tv_book_pages.text = context.resources.getString(
                    R.string.pages,
                    item.volumeInfo?.pageCount.toString()
                )
                Glide.with(context).load(item.volumeInfo?.imageLinks?.thumbnail)
                    .placeholder(ResourcesCompat.getDrawable(resources, R.drawable.placeholder, null))
                    .into(iv_book_image)
                setOnClickListener { callback.onItemClick(item) }
            }
        }
    }

    interface BookItemCallback {
        fun onItemClick(item: Item)
    }
}