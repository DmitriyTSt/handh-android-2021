package ru.handh.lesson_4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.handh.lesson_4.model.Product
import ru.handh.lesson_4.model.User

class ProductViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
) {
    private val textViewTitle by lazy { itemView.findViewById<TextView>(R.id.textViewTitle) }
    private val textViewPrice by lazy { itemView.findViewById<TextView>(R.id.textViewPrice) }
    private val textViewVendor by lazy { itemView.findViewById<TextView>(R.id.textViewVendor) }

    fun bind(product: Product) {
        textViewTitle.text = product.title
        textViewPrice.text = "${product.price / 100}.%02d".format(product.price % 100)
        textViewVendor.text = product.vendor
    }
}