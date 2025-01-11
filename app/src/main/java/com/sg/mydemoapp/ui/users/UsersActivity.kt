package com.sg.mydemoapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sg.mydemoapp.BaseActivity
import com.sg.mydemoapp.data.local.entity.User
import com.sg.mydemoapp.databinding.ActivityUsersBinding
import com.sg.mydemoapp.utils.CommonUtil
import com.sg.mydemoapp.utils.Constants
import com.sg.mydemoapp.utils.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : BaseActivity(), UsersRecyclerAdapter.OnUserClickListener {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var viewModel: UsersViewModel

    companion object {
        val TAG = "UsersActivityT"
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
            Logger.d(TAG, "users observe: ${it.size}")
            if (it.isNotEmpty()) {
                adapter.updateList(it)
            }

        }
        viewModel.error.observe(this) {
            Logger.d(TAG, "error observe: $it")
            showToast(it)
        }

        viewModel.usersLocal.observe(this) {
            Logger.d(TAG, "local observe: ${it.size}")
            adapter.updateList(it)
        }

        viewModel.loader.observe(this) {
            Logger.d(TAG, "loader observe: $it")
            if (it) {
                //binding.rvUsers.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.rvUsers.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }

        }
    }

    private fun initUI() {
        adapter = UsersRecyclerAdapter(emptyList(), this)
        binding.rvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.adapter = adapter
    }

    override fun onclick(user: User) {
        val intent = Intent(this, UserHomeActivity::class.java)
        intent.putExtra(Constants.EXTRA_USER_ID, user.id)
        startActivity(intent)
    }
}