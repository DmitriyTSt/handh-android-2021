package ru.handh.lesson_4

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.handh.lesson_4.model.Product
import ru.handh.lesson_4.model.User

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val USER_TYPE = 0
        private const val PRODUCT_TYPE = 1
    }

    private val items = mutableListOf<AdapterElement>()

    lateinit var onItemClick: (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            USER_TYPE -> UserViewHolder(parent, onItemClick)
            PRODUCT_TYPE -> ProductViewHolder(parent)
            else -> throw Exception("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            USER_TYPE -> (holder as UserViewHolder).bind(items[position] as User)
            PRODUCT_TYPE -> (holder as ProductViewHolder).bind(items[position] as Product)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<AdapterElement>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is User) {
            USER_TYPE
        } else {
            PRODUCT_TYPE
        }
    }
}