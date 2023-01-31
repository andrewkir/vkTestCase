package ru.andrewkir.vktestcase.flows.main.adapters

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.andrewkir.vktestcase.R
import ru.andrewkir.domain.models.TodoModel
import java.util.*


class TodoAdapter(
    private var dataSet: List<TodoModel>,
    private val listener: (TodoModel) -> Unit,
    private val onCheckListener: (TodoModel) -> Unit
) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    fun setData(data: List<TodoModel>) {
        dataSet = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.text)
        val checkbox: CheckBox = view.findViewById(R.id.checkbox)
        val deleteButton: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.todo_item_row, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.run {
            text.text = dataSet[position].text
            checkbox.isChecked = dataSet[position].isCompleted ?: false
            val str = dataSet[position].text
            val id = dataSet[position].id
            if (dataSet[position].isCompleted == true) {
                val textString = SpannableString(text.text)
                textString.setSpan(StrikethroughSpan(), 0, textString.length, 0)
                text.text = textString
            } else {
                text.text = str
            }

            checkbox.setOnCheckedChangeListener { _, _ ->
                onCheckListener.invoke(
                    TodoModel(
                        id = id,
                        text = str,
                        isCompleted = checkbox.isChecked
                    )
                )
                if (checkbox.isChecked) {
                    val textString = SpannableString(text.text)
                    textString.setSpan(StrikethroughSpan(), 0, textString.length, 0)
                    text.text = textString
                } else text.text = str
            }

            deleteButton.setOnClickListener {
                listener.invoke(dataSet[adapterPosition])
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun getItemCount() = dataSet.size

}