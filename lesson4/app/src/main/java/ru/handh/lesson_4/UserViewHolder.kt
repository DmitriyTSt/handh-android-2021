package ru.handh.lesson_4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.handh.lesson_4.model.User

class UserViewHolder(
    parent: ViewGroup,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val textViewName by lazy { itemView.findViewById<TextView>(R.id.textViewName) }
    private val textViewSurname by lazy { itemView.findViewById<TextView>(R.id.textViewSurname) }
    private val textViewBirthday by lazy { itemView.findViewById<TextView>(R.id.textViewBirthday) }

    fun bind(user: User) {
        itemView.setOnClickListener {
            onItemClick(user.name)
        }
        textViewName.text = user.name
        textViewSurname.text = user.surname
        textViewBirthday.text = user.birthday
    }
}