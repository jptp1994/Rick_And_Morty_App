package com.rickandmorty.myapplication.ui.characterlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rick_and_morty.app.R
import com.rick_and_morty.app.databinding.FragmentCharacterListBinding
import com.rickandmorty.myapplication.base.BaseFragment
import com.rickandmorty.myapplication.extension.observe
import com.rickandmorty.ui.viewmodel.BaseViewModel
import com.rickandmorty.ui.viewmodel.CharacterListViewModel
import com.rickandmorty.ui.viewmodel.CharacterUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentCharacterListBinding =
        FragmentCharacterListBinding.inflate(layoutInflater)

    override val viewModel: CharacterListViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isFavorite =
            (findNavController().currentDestination?.label == getString(R.string.menu_favorites))
        viewModel.getCharacters(isFavorite)
        observe(viewModel.getCharacters(), ::onViewStateChange)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewCharacters.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        characterAdapter.setItemClickListener { character ->
            findNavController().navigate(
                CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    character.id.toLong()
                )
            )
        }
    }

    private fun onViewStateChange(event: CharacterUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is CharacterUIModel.Error -> handleErrorMessage(event.error)
            is CharacterUIModel.Loading -> handleLoading(true)
            is CharacterUIModel.Success -> {
                handleLoading(false)
                event.data.let {
                    characterAdapter.list = it
                }
            }
        }
    }
}
