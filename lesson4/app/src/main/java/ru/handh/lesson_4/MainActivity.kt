package ru.handh.lesson_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.recyclerview.widget.RecyclerView
import ru.handh.lesson_4.model.Product
import ru.handh.lesson_4.model.User

class MainActivity : AppCompatActivity() {

    companion object {
        private const val VIEW_STATE_LOADING = 0
        private const val VIEW_STATE_DATA = 1
        private const val VIEW_STATE_ERROR = 2
    }

    private val viewFlipper by lazy { findViewById<ViewFlipper>(R.id.viewFlipper) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewFlipper.displayedChild = VIEW_STATE_DATA
        val adapter = MainAdapter()
        recyclerView.adapter = adapter
        adapter.onItemClick = { userName ->
            Toast.makeText(recyclerView.context, userName, Toast.LENGTH_SHORT).show()
        }

        adapter.setItems(
            listOf(
                *List(6) {
                    User(name = "Имя $it", surname = "Фамилия $it", birthday = "10.10.10.200$it")
                }.toTypedArray(),
                *List(6) {
                    Product(title = "Ноутбук $it", price = it * 1000L, vendor = "10.10.10.200$it")
                }.toTypedArray(),
            )
        )
    }


}

sealed class BaseProduct {
    class Product1() : BaseProduct()
    class Product2() : BaseProduct()
}