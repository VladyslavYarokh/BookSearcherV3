package vladyslav.yarokh.bookssearcherv3.ui.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import vladyslav.yarokh.bookssearcherv3.R

abstract class BaseFragment: DaggerFragment() {

    protected fun addFragment(fragment: Fragment, backStack: Boolean) {
        val transaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.container, fragment)
        if (backStack) transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }

    protected fun closeKeyboard() {
        if (requireActivity().currentFocus != null) {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
        }
    }

    private fun toast(textResId: Int) {
        Toast.makeText(requireActivity(), textResId, Toast.LENGTH_SHORT).show()
    }

    private fun toast(text: String) {
        Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
    }

    protected fun showInfoMsg(text: String){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) snackbar(text)
        else toast(text)
    }

    protected fun showInfoMsg(textResId: Int){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) snackbar(textResId)
        else toast(textResId)
    }

    private fun snackbar(text: String) {
        Snackbar.make(requireActivity().window.decorView.rootView, text, Snackbar.LENGTH_LONG)
            .apply { view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams)
                .apply { setMargins(10, 0, 10, 0) }
                .apply { gravity = Gravity.CENTER }
            }
            .setActionTextColor(Color.WHITE)
            .show()
    }

    private fun snackbar(textResId: Int) {
        Snackbar.make(requireActivity().window.decorView.rootView, textResId, Snackbar.LENGTH_LONG)
            .apply { view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams)
                .apply { setMargins(10, 0, 10, 0) }
                .apply { gravity = Gravity.CENTER }
            }
            .setActionTextColor(Color.WHITE)
            .show()
    }
}