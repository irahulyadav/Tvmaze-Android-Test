package com.magine.api

import android.content.Context
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.magine.api.Api

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*

//import com.appszum.lib.config.Logger;


/**
 * Created by iprahul on 8/3/15.
 */
open class AuthJsonObjectRequest<T>(
        method: Int,
        url: String,
        jsonRequest: JSONObject? = null,
        listener: Api.VolleyListener<T>
) : JsonRequest<T>(method, url, jsonRequest?.toString(), listener, listener) {


    val headers = HashMap<String, String>()


    init {
        retryPolicy = DefaultRetryPolicy(
                600000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        //        headers.put("Content-Type", "application/json");
        //        headers.put("charset", "UTF-8");


        showInfo()
    }



    /* private static String getUrl(@NonNull String url, @NonNull LoginUser userDetails) {
        return url.replace("HOST", userDetails.getUrl())
                .replace("SESSION_ID", userDetails.getSessionId());
       *//* try {
            return URLDecoder.decode(URLEncoder.encode(
                    url.replace("HOST", userDetails.getUrl())
                            .replace("SESSION_ID", userDetails.getSessionId())
                    , "UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;*//*
    }*/


    protected fun showInfo() {
        Log.d("URL CALL[" + getMethod(method).name + "] ::: ", url)
        // Logger.getInstance().info(getClass(), "URL CALL[".concat(getMethod(getMethod()).name()).concat("] ::: "), getUrl());

        try {
            //Log.d()
            Log.d("heeaders", headers.toString())
            Log.d(" body", bodyContentType)
            Log.d("encoding", paramsEncoding)
            val bytes = body
            val body = if (bytes == null || bytes.size <= 0) "" else String(bytes)
            if (!body.contains("password")) {
                Log.d(" Request[Body]", body)
            }
        } catch (e: Exception) {
            Log.e("", "", e)
        }

    }

    private fun showInfo(response: NetworkResponse) {
        try {
            Log.d("heeaders", response.headers.toString())
            val bytes = response.data
            Log.d(
                    "Response[Body]",
                    if (bytes == null || bytes.size <= 0) "" else String(bytes, Charset.forName("UTF-8"))
            )
        } catch (e: Exception) {
            Log.e("", "", e)
        }

    }

    private fun getMethod(method: Int): METHOD {
        when (method) {
            Method.DELETE -> return METHOD.DELETE
            Method.PUT -> return METHOD.PUT
            Method.HEAD -> return METHOD.HEAD
            Method.GET -> return METHOD.GET
            Method.POST -> return METHOD.POST
        }
        return METHOD.UNKNOWN
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<T>? {
        if (!response.headers.containsKey("charset")) {
            response.headers["charset"] = "UTF-8"
        }
        showInfo(response)
        val jsonString: String
        try {
            jsonString = String(response.data, Charset.forName("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            return Response.error(ParseError(e))
        }

        val response: Response<Any> = try {
             Response.success(JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response))
        } catch (je: JSONException) {
            try {
                Response.success(JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response))
            } catch (e: JSONException) {
                Response.error(ParseError(e))
            }
        }
        return response as? Response<T>
    }

    fun call(context: Context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context)
        }
        queue?.add(this)
    }


    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        return headers
    }

    private enum class METHOD {
        DELETE, PUT, HEAD, GET, POST, UNKNOWN
    }

    companion object {
        var queue: RequestQueue? = null
    }
}
