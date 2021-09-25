package com.example.busquedaanime.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.ListFragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.busquedaanime.R
import com.example.busquedaanime.databinding.AnimeItemBinding
import com.example.busquedaanime.model.ResponseAnime
import com.example.busquedaanime.model.Result
import com.example.busquedaanime.ui.anime_listDirections
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val lista: List<Result>) : RecyclerView.Adapter<RecyclerViewAdapter.AnimeHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AnimeHolder(layoutInflater.inflate(R.layout.anime_item, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeHolder, position: Int) {
        var item = lista[position]
        holder.render(item)

        // envio los datos hacia el fragment
        holder.binding.rowLayout.setOnClickListener {
            val action = anime_listDirections.actionAnimeListToAnimeItem(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class AnimeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = AnimeItemBinding.bind(view)

        fun render(anime: Result) {
            Picasso.get().load(anime.image_url).into(binding.ivAnimeItem)
            binding.tvAnimeItem.text = anime.title
        }
    }
}