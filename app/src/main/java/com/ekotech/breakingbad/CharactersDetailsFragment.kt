package com.ekotech.breakingbad

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ekotech.breakingbad.databinding.FragmentCharactersDetailsBinding
import com.ekotech.breakingbad.viewstate.CharactersViewState
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class CharactersDetailsFragment : Fragment(R.layout.fragment_characters_details) {

    private val binding by viewBinding(FragmentCharactersDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val state = arguments?.getParcelable<CharactersViewState>(VIEW_STATE)

        state?.let {
            Glide
                .with(this)
                .load(state.imageURl)
                .into(binding.fragmentCharactersDetailsImage)

            textDetailsSetUp(it)
        }
    }

    private fun textDetailsSetUp(viewState: CharactersViewState) {
        with(binding) {
            fragmentCharactersDetailsName.text = getString(R.string.fragment_characters_details_name, viewState.name)

            val occupation = StringBuilder()
            occupation.append(getString(R.string.fragment_characters_details_occupation))

            viewState.occupation.forEach {
                occupation.append("$it, ")
            }
            fragmentCharactersDetailsOccupation.text = occupation

            fragmentCharactersDetailsStatus.text = getString(R.string.fragment_characters_details_status, viewState.status)
            fragmentCharactersDetailsNickname.text = getString(R.string.fragment_characters_details_nickname, viewState.nickName)

            val appearancesBuilder = StringBuilder()
            appearancesBuilder.append(getString(R.string.fragment_characters_details_appearance))

            viewState.seasonAppearance.forEach {
                appearancesBuilder.append("$it \n")
            }

            fragmentCharactersDetailsAppearance.text = appearancesBuilder
        }
    }

    companion object {
        fun newInstance(viewState: CharactersViewState) = CharactersDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(VIEW_STATE, viewState)
            }
        }

        private const val VIEW_STATE = "VIEW_STATE"
    }
}