package vladyslav.yarokh.bookssearcherv3.ui.base

import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {

    private fun toast(textResId: Int) {
        Toast.makeText(this, textResId, Toast.LENGTH_SHORT).show()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun snackbar(text: String) {
        Snackbar.make(this.window.decorView.rootView, text, Snackbar.LENGTH_LONG)
            .apply { view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams)
                .apply { setMargins(10, 0, 10, 0) }
                .apply { gravity = Gravity.CENTER }
            }
            .setActionTextColor(Color.WHITE)
            .show()
    }

    private fun snackbar(textResId: Int) {
        Snackbar.make(this.window.decorView.rootView, textResId, Snackbar.LENGTH_LONG)
            .apply { view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams)
                .apply { setMargins(10, 0, 10, 0) }
                .apply { gravity = Gravity.CENTER }
            }
            .setActionTextColor(Color.WHITE)
            .show()
    }

    protected fun showInfoMsg(text: String){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) snackbar(text)
        else toast(text)
    }

    protected fun showInfoMsg(textResId: Int){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) snackbar(textResId)
        else toast(textResId)
    }
}