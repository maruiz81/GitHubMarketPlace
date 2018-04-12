package maruiz.com.githubmarketplace.presentation.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import me.alexrs.recyclerviewrenderers.renderer.Renderer

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun Renderer.getView(viewGroup: ViewGroup): View =
        LayoutInflater.from(viewGroup.context).inflate(id, viewGroup, false)