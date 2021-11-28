package com.github.chufarnovevgeniy.testpeopleapp.ui.common

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.github.chufarnovevgeniy.testpeopleapp.R
import com.github.chufarnovevgeniy.testpeopleapp.databinding.ItemUserBinding
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity

class UserViewHolder(
    viewGroup: ViewGroup,
    itemId: Int = R.layout.item_user
) : UsersAdapter.BaseUserViewHolder(viewGroup, itemId) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(user: UserEntity, onClick: (UserEntity) -> Unit) {
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email
        binding.activeImageView.isVisible = user.isActive

        if (user.isActive) {
            binding.root.setOnClickListener {
                onClick(user)
            }
        } else {
            binding.root.setOnClickListener(null)
        }
    }
}