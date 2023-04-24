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
        viewDataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        Log.d("TEST!!!",testUseCase().toString())
        lifecycleScope.launchWhenStarted {
            Log.d("TEST!!!",getStreamDataUseCase().getOrNull()!!.toString())
        }



//        button.setOnClickListener {
//            val intent = packageManager.getLaunchIntentForPackage("tv.twitch.android.app")
//            if(intent != null){
//                it
//            }else{
//                it
//            }
//            Intent(Intent.ACTION_VIEW).apply {
//                data = Uri.parse("twitch://stream/cotton__123")
//                startActivity(this)
//            }

//            val intent = packageManager.getLaunchIntentForPackage("tv.twitch.android.app")
//            intent?.data = Uri.parse("twitch://stream/cotton__123")
//            startActivity(intent)
//            runCatching {
//                packageManager.getPackageInfo("tv.twitch.android.app", PackageManager.PackageInfoFlags.of(0L))
//            }.onSuccess {
//                it
//                Intent().apply {
//                    data = Uri.parse("twitch://stream/cotton__123")
//                    startActivity(this)
//                }
//            }.onFailure {
//                it
//            }
//            if (intent != null) {
//                intent.data = Uri.parse("twitch://stream/cotton__123")
//                startActivity(intent)
//            } else {
//                // 트위치 앱이 설치되어 있지 않은 경우 구글 플레이 스토어 페이지로 이동합니다.
////                val elseIntent = Intent(Intent.ACTION_VIEW)
////                elseIntent.data = Uri.parse("market://details?id=tv.twitch.android.app")
////                startActivity(elseIntent)
//            }
    }
}