package me.tap.warpspeedassessment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.tap.warpspeedassessment.R
import me.tap.warpspeedassessment.databinding.DisplayMovieBinding
import me.tap.warpspeedassessment.domain.model.Movie
import timber.log.Timber

class MovieAdapter(private val listener: ItemClickedListener) :
    androidx.recyclerview.widget.ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = DisplayMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemBind.cardView.setOnClickListener {
            listener.clickedItem(getItem(position).movieID)
        }
    }

    class MovieViewHolder(val itemBind: DisplayMovieBinding) :
        RecyclerView.ViewHolder(itemBind.root) {

        fun bind(movie: Movie) {
            itemBind.apply {
                movieTitle.text = movie.title
                movieYear.text = movie.release
                movieImage.load(movie.moviePoster){
                    error(R.drawable.blur)
                }
            }
        }



    }

    interface ItemClickedListener{
        fun clickedItem(movieID: String)
    }
}

private object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.movieID == newItem.movieID
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}