package me.tap.warpspeedassessment.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import me.tap.warpspeedassessment.R
import me.tap.warpspeedassessment.databinding.FragmentDetailBinding
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val args:DetailFragmentArgs by navArgs()
    private val viewModel:MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentDetailBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }.apply {
            viewModel.getMovieDetail(args.movieID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribe()
    }

    private fun subscribe(){
        viewModel.error.observe(viewLifecycleOwner){(showError, message) ->
            binding.textResponse.isVisible = showError
            binding.textResponse.text = message
        }

        viewModel.movieDetail.observe(viewLifecycleOwner){movie ->
            binding.actor.text = getString(R.string.actors, movie.actors)
            binding.description.text = getString(R.string.plot, movie.description)
            binding.displayImage.load(movie.moviePoster){
                error(R.drawable.blur)
            }
            binding.movieGenre.text = getString(R.string.genre, movie.genre)
            binding.movieYear.text = getString(R.string.year, movie.year)
            binding.writer.text = getString(R.string.writers, movie.writer)
            binding.title.text = getString(R.string.title, movie.title)
        }

        viewModel.loading.observe(viewLifecycleOwner){
            binding.progress.isVisible = it
        }
    }
}