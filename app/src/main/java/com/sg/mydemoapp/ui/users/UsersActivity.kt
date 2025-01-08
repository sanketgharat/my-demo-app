package com.sg.mydemoapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sg.mydemoapp.BaseActivity
import com.sg.mydemoapp.databinding.ActivityUsersBinding
import com.sg.mydemoapp.utils.CommonUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : BaseActivity() {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var viewModel: UsersViewModel

    companion object {
        val TAG = UsersActivity::class.java.kotlin.simpleName
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UsersViewModel::class.java]

        if (CommonUtil.isOnline()) {
            viewModel.getUsers()
        }

        viewModel.users.observe(this) {
            //showToast("success: ${it.size}")

        }
        viewModel.error.observe(this) {
            showToast("error: $it")
        }
    }

    private fun initUI() {

    }
}