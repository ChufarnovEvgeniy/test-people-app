package com.github.chufarnovevgeniy.testpeopleapp.ui.user

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.chufarnovevgeniy.testpeopleapp.R
import com.github.chufarnovevgeniy.testpeopleapp.databinding.FragmentUserBinding
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import com.github.chufarnovevgeniy.testpeopleapp.ui.common.UsersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<UserViewModel> {
        parametersOf(arguments?.getParcelable(ARGUMENT_USER_KEY))
    }

    private lateinit var adapter: UsersAdapter

    companion object {
        private const val ARGUMENT_USER_KEY = "user key"

        fun getInstance(user: UserEntity) = UserFragment().apply {
            arguments = bundleOf(ARGUMENT_USER_KEY to user)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        observeValues()
    }

    private fun initRecyclerView() {
        adapter = UsersAdapter(
            viewModel::onUserClicked,
            viewModel::onPhoneClicked,
            viewModel::onEmailClicked,
            viewModel::onCoordinatesClicked
        )

        binding.friendsRecyclerView.adapter = adapter
        binding.friendsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeValues() {
        viewModel.friends.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.userClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.onUserClickedEventFinished()
                (requireActivity() as Contract).navigateToUser(it)
            }
        }

        viewModel.phoneClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                safeActivityCall(Intent.ACTION_DIAL, "tel:${it}")
                viewModel.onPhoneClickedEventFinished()
            }
        }

        viewModel.emailClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                safeActivityCall(Intent.ACTION_SENDTO, "mailto:${it}")
                viewModel.onEmailClickedEventFinished()
            }
        }

        viewModel.coordinatesClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                safeActivityCall(Intent.ACTION_VIEW, "geo:${it}")
                viewModel.onCoordinatesClickedEventFinished()
            }
        }
    }

    private fun safeActivityCall(action: String, dataQuery: String) {
        val intent = Intent(action).apply {
            data = Uri.parse(dataQuery)
        }

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                getString(R.string.error_message_activity_not_found),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (requireActivity() !is Contract) {
            throw RuntimeException("Activity must implement UserFragment.Contract")
        }
    }

    interface Contract {
        fun navigateToUser(user: UserEntity)
    }
}