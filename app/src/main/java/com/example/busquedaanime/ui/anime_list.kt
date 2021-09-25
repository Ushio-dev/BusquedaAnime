package com.example.busquedaanime.ui

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.busquedaanime.databinding.FragmentAnimeListBinding
import com.example.busquedaanime.model.Repository
import com.example.busquedaanime.model.Result
import com.example.busquedaanime.ui.adapter.RecyclerViewAdapter
import com.example.busquedaanime.viewmodel.MainViewModel
import com.example.busquedaanime.viewmodel.MainViewModelFactory

class anime_list : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentAnimeListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecyclerViewAdapter

    private lateinit var viewModel: MainViewModel
    private val listadoDeAnimes = mutableListOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnimeListBinding.inflate(inflater, container, false)

        binding.svAnime.setOnQueryTextListener(this)
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewAdapter(listadoDeAnimes)
        binding.rvListaAnime.layoutManager = LinearLayoutManager(context)
        binding.rvListaAnime.adapter = adapter
    }

    private fun searchAnimeByName(query: String) {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // hago uso de la api
        viewModel.getAnimeList("anime?q=$query")

        viewModel.animeList.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val animes = viewModel.animeList.value?.body()?.results ?: emptyList()
                listadoDeAnimes.clear()
                listadoDeAnimes.addAll(animes)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(context,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
        })
        viewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()) {
            searchAnimeByName(query)
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}