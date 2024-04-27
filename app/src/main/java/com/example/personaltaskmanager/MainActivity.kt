package com.example.personaltaskmanager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.personaltaskmanager.ui.MainViewModel
import com.example.personaltaskmanager.utils.PermissionManager
import com.example.personaltaskmanager.utils.ThemeUtil
import com.example.personaltaskmanager.utils.collectFlowAtLifecycle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    @Inject
    lateinit var themeUtil: ThemeUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionManager.requestNotificationPermissionIfNeeded(this){
            Toast.makeText(this, "Thank you", Toast.LENGTH_LONG).show()
        }
        setupObservables()
    }

    private fun setupObservables() {
        collectFlowAtLifecycle(viewModel.appTheme) {
            themeUtil.setAppTheme(it)
        }
    }
}
