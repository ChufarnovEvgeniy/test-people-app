package com.github.chufarnovevgeniy.testpeopleapp.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity

private const val USER_INFO_HEADER_TYPE = 0
private const val USER_TYPE = 1

class UsersAdapter(
    private val onClick: (UserEntity) -> Unit,
    private val onPhoneClicked: (() -> Unit)? = null,
    private val onEmailClicked: (() -> Unit)? = null,
    private val onCoordinatesClicked: (() -> Unit)? = null
) : ListAdapter<UserEntity, UsersAdapter.BaseUserViewHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseUserViewHolder {
        return when (viewType) {
            USER_INFO_HEADER_TYPE -> UserInfoViewHolder(parent)
            USER_TYPE -> UserViewHolder(parent)
            else -> throw IllegalArgumentException("Unknown ViewHolder type")
        }
    }

    override fun onBindViewHolder(holder: BaseUserViewHolder, position: Int) {
        if (getItem(position).isInfoHeader) {
            (holder as UserInfoViewHolder).bind(
                getItem(position),
                onPhoneClicked,
                onEmailClicked,
                onCoordinatesClicked
            )
        } else {
            (holder as UserViewHolder).bind(getItem(position), onClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isInfoHeader) {
            USER_INFO_HEADER_TYPE
        } else {
            USER_TYPE
        }
    }

    abstract class BaseUserViewHolder(viewGroup: ViewGroup, itemId: Int) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(itemId, viewGroup, false)
    )
}

class UsersDiffCallback : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.guid == newItem.guid
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }
}