package com.example.twitchtopgames.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitchtopgames.BackgroundExecutor
import com.example.twitchtopgames.Executors
import com.example.twitchtopgames.R
import com.example.twitchtopgames.data.GamesRepository
import com.example.twitchtopgames.data.GamesRepositoryImpl
import com.example.twitchtopgames.data.database.DatabaseHolder
import com.example.twitchtopgames.data.database.GamesDatabase
import com.example.twitchtopgames.data.domain.Games
import com.example.twitchtopgames.data.domain.GamesDb
import com.example.twitchtopgames.data.domain.mappers.GamesMapper
import com.example.twitchtopgames.data.domain.mappers.Mapper
import com.example.twitchtopgames.databinding.FragmentGamesBinding
import com.example.twitchtopgames.network.GamesApiService
import com.example.twitchtopgames.network.TopGamesApi
import com.example.twitchtopgames.presenter.GamesPresenter
import com.example.twitchtopgames.ui.mappers.GamesVoMapper
import com.example.twitchtopgames.ui.templates.GamesVO
import com.example.twitchtopgames.ui.templates.VisualObject
import com.google.android.material.snackbar.Snackbar

class GamesFragment : Fragment(), GamesView {

    private val executors: Executors = Executors(
        background = BackgroundExecutor()
    )

    private val topApiGames: TopGamesApi by lazy { GamesApiService.gamesApi }
    private val database: GamesDatabase by lazy { DatabaseHolder.gamesDatabase }
    private val gamesMapper: Mapper<Games, GamesDb> = GamesMapper()
    private val gamesRepository: GamesRepository by lazy {
        GamesRepositoryImpl(topApiGames, gamesMapper, database, executors)
    }
    private val gamesVoMapper: Mapper<GamesDb, GamesVO> = GamesVoMapper()
    private val presenter: GamesPresenter by lazy(LazyThreadSafetyMode.NONE) {
        GamesPresenter(gamesRepository, gamesVoMapper)
    }

    private val gamesAdapter: GamesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        GamesAdapter()
    }

    private lateinit var binding: FragmentGamesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesBinding.inflate(inflater)

        presenter.attach(this)
        initRecycler()
        initSwipeToRefresh()
        presenter.loadData(false)
        setHasOptionsMenu(true)
        onFloatClick()

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun showLoader(isShow: Boolean) {
        binding.progressBar.isVisible = isShow
    }

    override fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun updateGames(games: List<VisualObject>) {
        gamesAdapter.update(games)
    }

    private fun initRecycler() {
        with(binding.gamesRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = gamesAdapter
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.loadData(true)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onFloatClick() {
        binding.fab.setOnClickListener {
            var dialog = Dialog(requireContext())

            dialog.setContentView(R.layout.dialog_rating)
            dialog.show()
            val ratingBar = dialog.findViewById<RatingBar>(R.id.rating_bar)
            val btSubmit = dialog.findViewById<Button>(R.id.bt_submit)
            btSubmit.setOnClickListener {
                var sRating = ratingBar.rating
                Toast.makeText(requireContext(), sRating.toString(), Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
        }
    }


}