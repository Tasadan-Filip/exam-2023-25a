package com.example.exam25a

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam25a.domain.MyEntity
import com.example.exam25a.utils.toast
import com.example.exam25a.viewmodels.ApiViewModel
import com.example.exam25a.viewmodels.MyEntityViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class Section3: AppCompatActivity() {
    private lateinit var entityViewModel: MyEntityViewModel
    private val model: ApiViewModel by viewModels()
    private lateinit var adapter: EntitiesListAdapter
    private lateinit var view: View
    private lateinit var spinner: ProgressBar
    private lateinit var editGroupTextView: TextView

    inner class EntitiesListAdapter internal constructor(
        context: Context
    ): RecyclerView.Adapter<EntitiesListAdapter.EntityViewHolder>() {

        inner class EntityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val idTextView: TextView = itemView.findViewById(R.id.id)
            val nameTextView: TextView = itemView.findViewById(R.id.name)
            val groupTextView: TextView = itemView.findViewById(R.id.group)
            val typeTextView: TextView = itemView.findViewById(R.id.type)
            val viewButton: Button = itemView.findViewById(R.id.viewButton)
        }

        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var entities = mutableListOf<MyEntity>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
            val itemView = inflater.inflate(R.layout.entity_item, parent, false)
            return EntityViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
            val current = entities[position]
            holder.idTextView.text = current.id.toString()
            holder.nameTextView.text = current.name
            holder.groupTextView.text = current.group
            holder.typeTextView.text = current.type
            /* holder.confirmButton.setOnClickListener {
                 GlobalScope.launch(Dispatchers.Main) {
                     model.confirm(current.id)
                     entityViewModel.confirm(current.id)
                 }
                 entities.remove(entities[position])
                 notifyDataSetChanged()
             }*/
        }

        override fun getItemCount(): Int {
            return entities.size
        }

        internal fun setEntities(entities: List<MyEntity>) {
//            var sortedEntities = sortAndFilterEntities(entities)
            this.entities.clear()
            this.entities.addAll(entities)
            notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_3)

        view = View(this)
        entityViewModel = ViewModelProvider(this).get(MyEntityViewModel::class.java)
        adapter = EntitiesListAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStats)

        editGroupTextView = findViewById(R.id.editGroupStats)
        spinner = findViewById(R.id.progressBarStats)
        spinner.visibility = View.GONE

        val buttonGroups = findViewById<Button>(R.id.getGroupsButton)
        buttonGroups.setOnClickListener {
            fetchData()
        }

        setupRecyclerView(recyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        if(entityViewModel.allEntities != null) {
            adapter.setEntities(entityViewModel.allEntities!!)
        }
    }

    private fun fetchData() {
        spinner.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            val list = model.getGroup(editGroupTextView.text.toString())
            if(list != null) {
                toast("Downloading data from server!")
               // entityViewModel.insertAll(list)
                displayEntities(list)
                toast("Successfully downloaded data from server!")
            } else {
                toast("The application is offline and there are no reservations in the local DB")
            }
            spinner.visibility = View.GONE
        }
    }

    private fun displayEntities(entities: List<MyEntity>) {
        adapter.setEntities(entities)
    }
}