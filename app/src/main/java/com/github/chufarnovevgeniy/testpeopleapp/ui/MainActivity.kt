package com.github.chufarnovevgeniy.testpeopleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.chufarnovevgeniy.testpeopleapp.R
import com.github.chufarnovevgeniy.testpeopleapp.databinding.ActivityMainBinding
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import com.github.chufarnovevgeniy.testpeopleapp.ui.user.UserFragment
import com.github.chufarnovevgeniy.testpeopleapp.ui.userslist.UsersListFragment

class MainActivity : AppCompatActivity(), UsersListFragment.Contract, UserFragment.Contract {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.containerLayout.id, UsersListFragment())
                .commit()
        }
    }

    override fun navigateToUser(user: UserEntity) {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerLayout.id, UserFragment.getInstance(user))
            .addToBackStack(null)
            .commit()
    }
}