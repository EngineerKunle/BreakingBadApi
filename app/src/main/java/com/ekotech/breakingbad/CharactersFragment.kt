package com.ekotech.breakingbad

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ekotech.breakingbad.adapter.CharactersAdapter
import com.ekotech.breakingbad.data.domain.BreakingBadCharacters
import com.ekotech.breakingbad.databinding.FragmentCharactersBinding
import com.ekotech.breakingbad.viewmodel.CharactersViewModel
import com.ekotech.breakingbad.viewstate.CharactersViewState
import com.ekotech.breakingbad.viewstate.SeasonFilter
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters), SearchView.OnQueryTextListener {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val model: CharactersViewModel by activityViewModels()
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.charactersData.observe(viewLifecycleOwner, { characterList ->
            characterList?.let { characters ->
                charactersAdapter = CharactersAdapter { state ->
                    activity?.let {
                        startDetailsFragment(it, state)
                        binding.fragmentCharactersSearch.setQuery("", false)
                    }
                }.apply {
                    this.characters = transform(characters)
                }
                binding.fragmentCharactersList.apply {
                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(this.context, 2)
                    adapter = charactersAdapter
                }

                binding.fragmentCharactersSearch.setOnQueryTextListener(this)
            }
        })
        setUpFilterUi()
    }

    private fun startDetailsFragment(activity: FragmentActivity, viewState: CharactersViewState) {
        activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, CharactersDetailsFragment.newInstance(viewState))
            .addToBackStack(null)
            .commit()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        model.filterCharacters(query!!).observe(viewLifecycleOwner) { characters ->
            charactersAdapter.updateCharacters(transform(characters))
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        model.filterCharacters(newText!!).observe(viewLifecycleOwner) { characters ->
            charactersAdapter.updateCharacters(transform(characters))
        }
        return false
    }

    private fun transform(characters: MutableList<BreakingBadCharacters>): MutableList<CharactersViewState> =
        characters.map {
            CharactersViewState(
                it.id,
                it.name,
                it.image,
                it.occupation,
                it.status,
                it.nickname,
                it.seasonAppearance
            )
        }.toMutableList()

    private fun setUpFilterUi() {
        binding.fragmentCharactersSeasonFilter.viewFilterSeasonOne.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_1).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(transform(characters))
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonTwo.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_2).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(transform(characters))
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonThree.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_3).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(transform(characters))
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonFour.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_4).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(transform(characters))
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonFive.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_5).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(transform(characters))
            }
        }
    }
}
