package com.magine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.widget.ImageView
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.magine.api.Api
import com.magine.model.Show
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_info.*
import org.json.JSONObject

class ShowInfoActivity : AppCompatActivity(), Api.VolleyListener<JSONObject> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_info)
        setSupportActionBar(toolbar)
        val show = Show.get(intent)
        if (show == null) {
            finish()
            return
        }

        Api.ShowInfo(show, this).call(this)
    }

    fun displayInfo(show: Show) {
        tvInfo.text = Html.fromHtml(show.summary)
        title = show.name
        tvNetwork.text = show.network.name
        tvSchedule.text = show.schedule.info
        tvGenres.text = show.genresInfo
        tvStatus.text = show.status
        tvType.text = show.type
        Picasso.get()
            .load(show.image?.original)
            .placeholder(R.drawable.no_img)
            .into(ivIcon, object : Callback {
                override fun onSuccess() {
                    ivIcon.scaleType = ImageView.ScaleType.FIT_XY
                    val _bitmap = (ivIcon.drawable as BitmapDrawable).bitmap
                    if (_bitmap != null) {
                        ivBg.setImageBitmap(_bitmap.blur(this@ShowInfoActivity))
                        ivBg.scaleType = ImageView.ScaleType.FIT_XY
                    }
                }

                override fun onError(e: java.lang.Exception?) {

                }
            })
    }

    override fun onResponse(response: JSONObject) {
        val show: Show = Gson().fromJson(response.toString(), Show::class.java)
        displayInfo(show)
    }

    override fun onErrorResponse(error: VolleyError) {

    }
}

fun Bitmap.blur(context: Context): Bitmap {
    val image: Bitmap = copy(config, isMutable)

    val outputBitmap = Bitmap.createBitmap(image)
    val renderScript = RenderScript.create(context)
    val tmpIn = Allocation.createFromBitmap(renderScript, image)
    val tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap)

    //Intrinsic Gausian blur filter
    val theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
    theIntrinsic.setRadius(25f)
    theIntrinsic.setInput(tmpIn)
    theIntrinsic.forEach(tmpOut)
    tmpOut.copyTo(outputBitmap)
    return outputBitmap
}