package com.github.chufarnovevgeniy.testpeopleapp.ui.common

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.github.chufarnovevgeniy.testpeopleapp.R
import com.github.chufarnovevgeniy.testpeopleapp.databinding.ItemUserInfoHeaderBinding
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import com.github.chufarnovevgeniy.testpeopleapp.utils.getEyeColorId
import com.github.chufarnovevgeniy.testpeopleapp.utils.getFavoriteFruitDrawableId
import com.github.chufarnovevgeniy.testpeopleapp.utils.getFormattedRegisterDateOrNull

class UserInfoViewHolder(
    viewGroup: ViewGroup,
    itemId: Int = R.layout.item_user_info_header
) : UsersAdapter.BaseUserViewHolder(viewGroup, itemId) {

    private val binding = ItemUserInfoHeaderBinding.bind(itemView)

    fun bind(
        user: UserEntity,
        onPhoneClicked: (() -> Unit)? = null,
        onEmailClicked: (() -> Unit)? = null,
        onCoordinatesClicked: (() -> Unit)? = null
    ) {
        setUserInfo(user)
        binding.root.invalidate()

        binding.phoneTextView.setOnClickListener {
            onPhoneClicked?.invoke()
        }

        binding.emailTextView.setOnClickListener {
            onEmailClicked?.invoke()
        }

        binding.coordinatesTextView.setOnClickListener {
            onCoordinatesClicked?.invoke()
        }
    }

    private fun setUserInfo(user: UserEntity) {
        binding.nameTextView.setField(R.string.user_name, user.name)
        binding.ageTextView.setField(R.string.user_age, user.age.toString())
        binding.companyTextView.setField(R.string.user_company, user.company)
        binding.emailTextView.setField(R.string.user_email, user.email)
        binding.phoneTextView.setField(R.string.user_phone, user.phone)
        binding.addressTextView.setField(R.string.user_address, user.address)
        binding.aboutTextView.setField(R.string.user_about, user.about)
        binding.coordinatesTextView.setField(R.string.user_coordinates, user.coordinates)
        binding.registeredTextView.setField(
            R.string.user_date,
            user.getFormattedRegisterDateOrNull()
        )

        binding.eyeColorImageView.setColorFilter(
            itemView.context.resources.getColor(user.getEyeColorId(), null)
        )

        binding.favoriteFruitImageView.setImageResource(
            user.getFavoriteFruitDrawableId()
        )

        binding.friendsTextView.isVisible = !user.friends.isNullOrEmpty()
    }

    private fun TextView.setField(stringId: Int, inputText: String?) {
        if (inputText != null) {
            text = itemView.context.getString(stringId, inputText.trim())
        } else {
            isVisible = false
        }
    }
}
