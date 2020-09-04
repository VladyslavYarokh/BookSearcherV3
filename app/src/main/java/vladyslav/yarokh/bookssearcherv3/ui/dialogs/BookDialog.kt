package vladyslav.yarokh.bookssearcherv3.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.dialog_layout.*
import vladyslav.yarokh.bookssearcherv3.R
import vladyslav.yarokh.bookssearcherv3.data.Item

class BookDialog(context: Context,
                 style: Int,
                 private val dialogCallback: OnDialogButtonClickListener,
                 private val item: Item
): Dialog(context, style){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_layout)
        window!!.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        initViews()
        initListeners()
    }

    private fun initListeners(){
        val btnOpenStore = findViewById<MaterialCardView>(R.id.btn_open_store)
        btnOpenStore.setOnClickListener {
            dismiss()
            dialogCallback.onDialogButtonClick(item)
        }

        val btnCancel = findViewById<TextView>(R.id.tv_cancel)
        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initViews() {
        with(item) {
            tv_dialog_book_title.text = volumeInfo?.title
            tv_dialog_book_author.text = volumeInfo?.authors?.first()
            tv_dialog_book_desc.text = volumeInfo?.description
            tv_dialog_book_pages.text =
                context.getString(R.string.pages, volumeInfo?.pageCount.toString())
            Glide.with(context).load(volumeInfo?.imageLinks?.thumbnail).into(iv_book_image)
        }
    }

    interface OnDialogButtonClickListener {
        fun onDialogButtonClick(item: Item)
    }
}
