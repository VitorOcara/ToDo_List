package com.example.exeple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initializing the array lists and the adapter
        val itemlist = arrayListOf<String>()
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice, itemlist
        )
        add.setOnClickListener {

            itemlist.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Item selecionado: " + itemlist.get(i), Toast.LENGTH_SHORT)
                .show()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}