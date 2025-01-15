package com.sg.mydemoapp.ui.users.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sg.mydemoapp.databinding.FragmentUserProfileBinding
import com.sg.mydemoapp.ui.users.UserHomeActivity
import com.sg.mydemoapp.utils.Constants
import com.sg.mydemoapp.utils.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var userId: Int = 0
    companion object {
        val TAG = "UserProfileFragmentT"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userProfileViewModel =
            ViewModelProvider(this).get(UserProfileViewModel::class.java)

        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textProfile
        Logger.d(TAG, "onCreateView ")
        UserHomeActivity.userIdHome?.let { id ->
            Logger.d(TAG, "onCreateView args")
            userId = id
            userProfileViewModel.getUserData(userId).observe(viewLifecycleOwner) {
                Logger.d(TAG, "User Observe: $userId = ${it.name}")
                textView.text = it.name
            }
        }


        /*userProfileViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}