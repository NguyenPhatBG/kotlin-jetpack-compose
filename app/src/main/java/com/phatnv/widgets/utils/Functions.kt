package com.phatnv.widgets.utils

import com.google.gson.JsonParser
import retrofit2.Response

class Functions {

    companion object {
        inline fun <reified T : Any> handleError(response: Response<T>):String {
            val errorResponseBody = response.errorBody()?.string()
            val jsonObject = JsonParser.parseString(errorResponseBody).asJsonObject
            var message = "Server error !"
            if(jsonObject["message"] != null){
                message = jsonObject["message"].toString()
            }
            return message
        }
    }

}
