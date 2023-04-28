package com.hegunhee.feature.main

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.hegunhee.domain.Test.TestUseCase
import com.hegunhee.domain.usecase.GetStreamDataUseCase
import com.hegunhee.feature.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewDataBinding : ActivityMainBinding

    @Inject lateinit var testUseCase : TestUseCase

    @Inject lateinit var getStreamDataUseCase : GetStreamDataUseCase

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }
}