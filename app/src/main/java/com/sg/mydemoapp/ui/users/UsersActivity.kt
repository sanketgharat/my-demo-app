package com.sg.mydemoapp.ui.users

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

    private lateinit var adapter: UsersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        initUI()
        if (CommonUtil.isOnline()) {
            viewModel.getUsers()
        }

        viewModel.users.observe(this) {
            if(it.isNotEmpty()) {
                adapter.updateList(it)
            }

        }
        viewModel.error.observe(this) {
            showToast(it)
        }
    }

    private fun initUI() {
        adapter = UsersRecyclerAdapter(emptyList())
        binding.rvUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.adapter = adapter
    }
}