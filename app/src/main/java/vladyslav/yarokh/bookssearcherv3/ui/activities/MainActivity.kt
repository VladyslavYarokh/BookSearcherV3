package vladyslav.yarokh.bookssearcherv3.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import vladyslav.yarokh.bookssearcherv3.R
import vladyslav.yarokh.bookssearcherv3.ui.base.BaseActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setupWithNavController(navController)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                if(navController.currentDestination!!.id != R.id.searchFragment)
                    navController.navigate(R.id.action_booksFragment_to_searchFragment)
                else showInfoMsg(R.string.on_current_destination)
            }
            R.id.action_my_results -> {
                if(navController.currentDestination!!.id != R.id.booksFragment)
                    navController.navigate(R.id.action_searchFragment_to_booksFragment)
                else showInfoMsg(R.string.on_current_destination)
            }
            R.id.action_logout -> showInfoMsg(R.string.added_later)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onBackPressed() {
        if(!drawer_layout.isDrawerOpen(nav_view))super.onBackPressed()
        drawer_layout.closeDrawer(GravityCompat.START)
    }
}
