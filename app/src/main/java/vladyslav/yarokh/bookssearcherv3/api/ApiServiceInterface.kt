package vladyslav.yarokh.bookssearcherv3.api

import retrofit2.http.GET
import retrofit2.http.Query
import vladyslav.yarokh.bookssearcherv3.data.DataItems


interface ApiServiceInterface {

    @GET("volumes")
    suspend fun getItemsByName(@Query("key") apiKey: String, @Query("q") queryString: String, @Query("maxResults") maxResults: Int): DataItems
}