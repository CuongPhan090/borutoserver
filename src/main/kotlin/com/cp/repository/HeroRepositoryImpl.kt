package com.cp.repository

import com.cp.models.ApiResponse
import com.cp.models.Hero

class HeroRepositoryImpl: HeroRepository {

    val heroes: Map<Int, List<Hero>> = emptyMap()

    val page1: List<Hero> = emptyList()
    val page2: List<Hero> = emptyList()
    val page3: List<Hero> = emptyList()
    val page4: List<Hero> = emptyList()
    val page5: List<Hero> = emptyList()

    override fun getAllHeroes(pageNumber: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override fun searchHeroes(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}
