package com.ekotech.breakingbad

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ekotech.breakingbad.adapter.CharactersAdapter
import com.ekotech.breakingbad.databinding.FragmentCharactersBinding
import com.ekotech.breakingbad.viewmodel.CharactersViewModel
import com.ekotech.breakingbad.viewstate.CharactersModel
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
        model.charactersViewState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is CharactersViewState.LoadingState -> {
                    binding.fragmentCharactersProgressBar.visibility = View.VISIBLE
                }

                is CharactersViewState.Success -> {
                    binding.fragmentCharactersProgressBar.visibility = View.GONE
                    charactersAdapter = CharactersAdapter {
                        activity?.let { activity ->
                            state.data.forEach {
                                startDetailsFragment(activity, it)
                            }
                            binding.fragmentCharactersSearch.setQuery("", false)
                        }
                    }.apply {
                        this.characters = state.data.toMutableList()
                    }

                    binding.fragmentCharactersList.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this.context, 2)
                        adapter = charactersAdapter
                    }
                }

                is CharactersViewState.Error -> {
                    binding.fragmentCharactersProgressBar.visibility = View.GONE
                    Toast.makeText(activity, "Error ${state.error}", Toast.LENGTH_SHORT).show()
                }
            }

            binding.fragmentCharactersSearch.setOnQueryTextListener(this)
        })
        setUpFilterUi()
    }

    private fun startDetailsFragment(activity: FragmentActivity, model: CharactersModel) {
        activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, CharactersDetailsFragment.newInstance(model))
            .addToBackStack(null)
            .commit()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        model.filterCharacters(query!!).observe(viewLifecycleOwner) { characters ->
            charactersAdapter.updateCharacters(characters)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        model.filterCharacters(newText!!).observe(viewLifecycleOwner) { characters ->
            charactersAdapter.updateCharacters(characters)
        }
        return false
    }

    private fun setUpFilterUi() {
        binding.fragmentCharactersSeasonFilter.viewFilterSeasonOne.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_1).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(characters)
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonTwo.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_2).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(characters)
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonThree.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_3).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(characters)
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonFour.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_4).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(characters)
            }
        }

        binding.fragmentCharactersSeasonFilter.viewFilterSeasonFive.setOnClickListener {
            model.filterBySeason(SeasonFilter.SEASON_5).observe(viewLifecycleOwner) { characters ->
                charactersAdapter.updateCharacters(characters)
            }
        }
    }
}
