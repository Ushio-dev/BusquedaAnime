package com.example.busquedaanime.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.busquedaanime.databinding.FragmentAnimeItemBinding
import com.squareup.picasso.Picasso

class anime_item : Fragment() {

    private var _binding: FragmentAnimeItemBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<anime_itemArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimeItemBinding.inflate(inflater, container, false)


        Picasso.get().load(args.currentAnime.image_url).into(binding.ivBanner)
        binding.tvName.text = args.currentAnime.title
        binding.tvCapitulos.text = args.currentAnime.episodes.toString()
        binding.tvSinpsis.text = args.currentAnime.synopsis


        // para pasar datos id("androidx.navigation.safeargs") esta cosa es importante

        return binding.root
    }
}