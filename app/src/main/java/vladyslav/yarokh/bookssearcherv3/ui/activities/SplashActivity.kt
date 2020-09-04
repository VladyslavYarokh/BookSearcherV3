package vladyslav.yarokh.bookssearcherv3.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_splash.*
import vladyslav.yarokh.bookssearcherv3.R
import vladyslav.yarokh.bookssearcherv3.ui.base.BaseActivity
import vladyslav.yarokh.bookssearcherv3.utils.NetworkState
import java.util.*

class SplashActivity: BaseActivity() {

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()

        if (NetworkState.isOnline(this)) {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    val myIntent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(myIntent)
                    finish()
                }
            }, 3000)
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.network_error))
                .setMessage(getString(R.string.network_error_message))
                .setNegativeButton(getString(android.R.string.ok)) { dialog, _ ->
                    dialog.cancel()
                    onBackPressed()
                }
            val alert = builder.create()
            alert.show()
        }

    }

    private fun initViews(){
        position = (0..9).shuffled().first()
        tv_phrase.text = resources.getStringArray(R.array.phrases)[position]
        tv_author.text = resources.getStringArray(R.array.authors)[position]
    }
}