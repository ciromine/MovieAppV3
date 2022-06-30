package com.example.ciromine.movieappv3.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ciromine.movieappv3.databinding.FragmentDetailBinding
import com.example.ciromine.movieappv3.domain.model.DomainMovie
import com.example.ciromine.movieappv3.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class DetailFragment : Fragment() {

    var binding: FragmentDetailBinding? = null

    var movie: DomainMovie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.let { DetailFragmentArgs.fromBundle(it).domainMovie }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentDetailBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            tvDate.text = movie?.releaseDate
            tvDescription.text = movie?.overview
            Picasso.get().load(Constants.baseUrlImages + movie?.posterPath)
                .fit().centerCrop()
                .into(imageView)
        }
    }
}