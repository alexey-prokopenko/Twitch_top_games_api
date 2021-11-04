package com.example.twitchtopgames.presenter

import com.example.twitchtopgames.data.GamesRepository
import com.example.twitchtopgames.data.domain.GamesDb
import com.example.twitchtopgames.data.domain.ResultListener
import com.example.twitchtopgames.data.domain.mappers.Mapper
import com.example.twitchtopgames.ui.GamesView
import com.example.twitchtopgames.ui.templates.GamesVO
import com.example.twitchtopgames.ui.templates.VisualObject

class GamesPresenter(
    private val gamesRepository: GamesRepository,
    private val gamesMapper: Mapper<GamesDb, GamesVO>
) {

    private var view: GamesView? = null
    private var books: List<VisualObject> = emptyList()

    fun attach(view: GamesView) {
        this.view = view
    }

    fun detach() {
        view = null
    }

    fun loadData(forceUpdate: Boolean) {
        view?.showLoader(true)
        gamesRepository.getGames(
            forceUpdate = forceUpdate,
            resultListener = handleResult()
        )
    }

    private fun handleResult() = object : ResultListener<List<GamesDb>> {
        override fun onSuccess(data: List<GamesDb>) {
            val gamesVO = data.map(gamesMapper::map)
            val dataList: MutableList<VisualObject> = mapW(gamesVO)

            books = dataList
            view?.showLoader(false)
            view?.updateGames(dataList)
        }

        override fun onError(error: Throwable) {
            view?.showLoader(false)
            view?.showMessage("Something is wrong")
        }
    }

    private fun mapW(gamesVO: List<GamesVO>): MutableList<VisualObject> {
        val dataList: MutableList<VisualObject> = mutableListOf()

        if (gamesVO.isNotEmpty()) {

            gamesVO.forEach { gamesVo ->
                dataList.add(gamesVo)
            }
        }
        return dataList
    }

}