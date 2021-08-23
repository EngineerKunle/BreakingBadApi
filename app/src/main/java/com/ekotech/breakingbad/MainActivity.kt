package com.ekotech.breakingbad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState.let {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CharactersFragment>(R.id.fragment_container_view)
            }
        }
    }
}
