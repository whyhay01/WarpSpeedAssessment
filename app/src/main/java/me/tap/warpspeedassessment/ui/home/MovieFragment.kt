package me.tap.warpspeedassessment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.tap.warpspeedassessment.R
import me.tap.warpspeedassessment.databinding.FragmentMovieBinding
import timber.log.Timber

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieAdapter.ItemClickedListener {

    private lateinit var binding: FragmentMovieBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMovieBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(this)
        binding.recyclerView.adapter = movieAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.searchView.isSubmitButtonEnabled = true

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query!!.isBlank()) {
                    Timber.d("Input movie title")
                    Toast.makeText(requireContext(), "Input movie title", Toast.LENGTH_SHORT).show()
                }else{
                    movieViewModel.getMovieList(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.d(newText)
                return true
            }

        })
        subscribe()
    }

    private fun subscribe(){
        movieViewModel.movieList.observe(viewLifecycleOwner){movies ->
            if (movies.isEmpty()){
                val showError = movies.isEmpty()
                binding.infoText.isVisible = showError
                binding.infoText.text = getString(R.string.no_display)
            }else {
                movieAdapter.submitList(movies)
            }
        }

        movieViewModel.error.observe(viewLifecycleOwner){(showError, message) ->
           binding.recyclerView.isVisible = !showError
            binding.infoText.isVisible = showError
            binding.infoText.text = message
            Timber.d("Error message: $message  Error Status: $showError")
        }

        movieViewModel.loading.observe(viewLifecycleOwner){
            Timber.d("Loading  status:$it")
            binding.progressCircular.isVisible = it
        }

    }

    override fun clickedItem(movieID: String) {
        findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(movieID))
    }

}