package ru.andrewkir.vktestcase.flows.main.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
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
import ru.andrewkir.vktestcase.flows.main.model.TodoModel
import java.util.*


class TodoAdapter(
    private var dataSet: List<TodoModel>,
    private val listener: (TodoModel) -> Unit
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

    @SuppressLint("SimpleDateFormat", "SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.run {
            text.text = dataSet[position].text
            checkbox.isChecked = dataSet[position].isCompleted ?: false
            val str = dataSet[position].text
            if (dataSet[position].isCompleted == true) {
                val textString = SpannableString(text.text)
                textString.setSpan(StrikethroughSpan(), 0, textString.length, 0)
                text.text = textString
            } else {
                text.text = str
            }

            checkbox.setOnCheckedChangeListener { _, _ ->
                if (checkbox.isChecked){
                    val textString = SpannableString(text.text)
                    textString.setSpan(StrikethroughSpan(), 0, textString.length, 0)
                    text.text = textString
                }
                else text.text = str
            }

            deleteButton.setOnClickListener {
                listener.invoke(dataSet[adapterPosition])
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun getItemCount() = dataSet.size

}