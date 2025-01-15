package com.sg.mydemoapp.ui.users.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sg.mydemoapp.databinding.FragmentUserHomeBinding
import com.sg.mydemoapp.ui.users.profile.UserProfileFragment
import com.sg.mydemoapp.ui.users.profile.UserProfileFragment.Companion
import com.sg.mydemoapp.utils.Constants
import com.sg.mydemoapp.utils.Logger

class UserHomeFragment : Fragment() {

    private var _binding: FragmentUserHomeBinding? = null
    companion object {
        val TAG = "UserHomeFragmentT"
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userHomeViewModel =
            ViewModelProvider(this).get(UserHomeViewModel::class.java)

        _binding = FragmentUserHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Logger.d(TAG, "onCreateView ")
        arguments?.let { args ->
            Logger.d(TAG, "onCreateView args")

        }

        val textView: TextView = binding.textHome
        userHomeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}