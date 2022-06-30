package com.example.ciromine.movieappv3.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ciromine.movieappv3.R
import com.example.ciromine.movieappv3.core.mvi.MviUi
import com.example.ciromine.movieappv3.core.mvi.MviUiEffect
import com.example.ciromine.movieappv3.databinding.FragmentMovieListBinding
import com.example.ciromine.movieappv3.domain.model.DomainMovie
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUIntent
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUIntent.*
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUiEffect
import com.example.ciromine.movieappv3.presentation.movielist.MovieListUiState
import com.example.ciromine.movieappv3.presentation.movielist.MovieListViewModel
import com.example.ciromine.movieappv3.ui.movielist.adapter.MovieListAdapter
import com.google.android.material.snackbar.Snackbar
import com.example.ciromine.movieappv3.ui.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MovieListFragment : Fragment(), MviUi<MovieListUIntent, MovieListUiState>,
    MviUiEffect<MovieListUiEffect> {

    var binding: FragmentMovieListBinding? = null

    private val viewModel by viewModels<MovieListViewModel>()

    private val userIntents: MutableSharedFlow<MovieListUIntent> = MutableSharedFlow()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupCollectors()
        statesProcessIntents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentMovieListBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun statesProcessIntents() {
        viewModel.run {
            viewModel.processUserIntents(userIntents())
        }
    }

    private fun initialUserIntent(): Flow<MovieListUIntent> = flow {
        emit(InitialUIntent)
    }

    private fun setupListener() {
        //binding?.btnRetry?.setOnClickListener { onRetryTapped() }
    }

    private fun setupCollectors() {
        with(viewModel) {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
            uiEffect().onEach { handleEffect(it) }.launchIn(lifecycleScope)
        }
    }

    override fun userIntents(): Flow<MovieListUIntent> = merge(
        initialUserIntent(),
        userIntents.asSharedFlow()
    )

    override fun renderUiStates(uiState: MovieListUiState) {
        when (uiState) {
            MovieListUiState.LoadingUiState -> showLoading()

            is MovieListUiState.SuccessUiState -> {
                hideLoading()
                showMovie(uiState.movies)
            }

            MovieListUiState.ErrorUiState -> {
                hideLoading()
                showError()
            }
        }
    }

    private fun showLoading() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.progressBar?.visibility = View.INVISIBLE
    }

    private fun showError() {
        binding?.root?.let {
            val snackbar = Snackbar
                .make(
                    it,
                    getString(R.string.error_get_movie_list),
                    Snackbar.LENGTH_LONG
                )
            snackbar.show()
        }
    }

    private fun showMovie(movies: List<DomainMovie>) {
        val adapter = MovieListAdapter(movies) {
            onItemCharacterTapped(it)
        }
        binding?.apply {
            mainRecycler.adapter = adapter
            mainRecycler.visibility = View.VISIBLE
        }
    }

    override fun handleEffect(uiEffect: MovieListUiEffect) {
        when (uiEffect) {
            is MovieListUiEffect.NavigateToCharacterDetailUiEffect -> goToCharacterEdit(uiEffect.id)
        }
    }

    private fun onItemCharacterTapped(id: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(SeeDetailUIntent(id))
        }
    }

    private fun goToCharacterEdit(id: Int) {
        binding?.let {
            navigator.goToMovieDetail(it.root, id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}