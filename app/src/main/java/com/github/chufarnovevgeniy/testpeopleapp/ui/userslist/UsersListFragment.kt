package com.github.chufarnovevgeniy.testpeopleapp.ui.userslist

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.chufarnovevgeniy.testpeopleapp.R
import com.github.chufarnovevgeniy.testpeopleapp.databinding.FragmentUsersListBinding
import com.github.chufarnovevgeniy.testpeopleapp.domain.ResultWrapper
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import com.github.chufarnovevgeniy.testpeopleapp.ui.common.UsersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersListFragment : Fragment() {
    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<UsersListViewModel>()

    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        initRecyclerView()
        observeValues()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_refresh, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.item_refresh) {
            viewModel.onRefreshClicked()
            true
        } else {
            false
        }
    }

    private fun initRecyclerView() {
        adapter = UsersAdapter(viewModel::onUserClicked)
        binding.usersRecyclerView.adapter = adapter
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeValues() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.root.children.forEach { it.isVisible = false }

            when (state) {
                is ResultWrapper.Success -> {
                    adapter.submitList(state.value)
                    binding.usersRecyclerView.isVisible = true
                }
                is ResultWrapper.Error -> {
                    binding.errorMessageTextView.isVisible = true
                }
                is ResultWrapper.Loading -> {
                    binding.loadingLayout.isVisible = true
                }
            }
        }

        viewModel.userClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.onUserEventFinished()
                (requireActivity() as Contract).navigateToUser(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (requireActivity() !is Contract) {
            throw RuntimeException("Activity must implement UsersListFragment.Contract")
        }
    }

    interface Contract {
        fun navigateToUser(user: UserEntity)
    }
}