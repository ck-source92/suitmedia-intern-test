package com.dwicandra.suitmediatest.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): UserResponse
}