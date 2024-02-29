package com.cp.repository

import com.cp.models.ApiResponse

interface HeroRepository {

    fun getAllHeroes(pageNumber: Int = 1): ApiResponse
    fun searchHeroes(heroNames: String?): ApiResponse
}