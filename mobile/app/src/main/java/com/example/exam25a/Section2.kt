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
import com.example.exam25a.utils.logd
import com.example.exam25a.utils.toast
import com.example.exam25a.viewmodels.ApiViewModel
import com.example.exam25a.viewmodels.MyEntityViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Section2 : AppCompatActivity() {
    private lateinit var entityViewModel: MyEntityViewModel
    private val model: ApiViewModel by viewModels()
    private lateinit var adapter: EntitiesListAdapter
    private lateinit var view: View
    private lateinit var spinner: ProgressBar

    inner class EntitiesListAdapter internal constructor(
        context: Context
    ): RecyclerView.Adapter<EntitiesListAdapter.EntityViewHolder>() {

        inner class EntityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val idTextView: TextView = itemView.findViewById(R.id.id2)
            val nameTextView: TextView = itemView.findViewById(R.id.name2)
            val joinExamButton: Button = itemView.findViewById(R.id.joinExamButton)
        }

        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var entities = mutableListOf<MyEntity>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
            val itemView = inflater.inflate(R.layout.entity_item_confirmation, parent, false)
            return EntityViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
            val current = entities[position]
            holder.idTextView.text = current.id.toString()
            holder.nameTextView.text = current.name
            val buttonJoin = holder.joinExamButton
            buttonJoin.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    model.join(current.id)
                    entities.remove(current)
                    notifyDataSetChanged()
                }
            }
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

//        internal fun sortAndFilterEntities(entities: List<MyEntity>): List<MyEntity> {
//            return entities
//                .filter { entity -> entity.status }
//                .sortedWith(compareByDescending<MyEntity> { computeMedie(it.medie1, it.medie2) }.thenBy { it.medie1 }.thenBy { it.medie2 })
//        }

//        internal fun computeMedie(medie1: Int, medie2: Int): Double {
//            return (medie1 * 0.75) + (medie2 * 0.25)
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_2)

        view = View(this)
        entityViewModel = ViewModelProvider(this).get(MyEntityViewModel::class.java)
        adapter = EntitiesListAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView2)


        spinner = findViewById(R.id.progressBar3)
        spinner.visibility = View.GONE

        fetchData()
        setupRecyclerView(recyclerView)
//        val buttonRefresh = findViewById<ExtendedFloatingActionButton>(R.id.refreshButton)
//        buttonRefresh.setOnClickListener {
//            logd("Works")
//            if(entityViewModel.allEntities == null) {
//                fetchData()
//            }
//        }
//
//        val buttonAdd = findViewById<ExtendedFloatingActionButton>(R.id.addButton)
//        buttonAdd.setOnClickListener {
//            logd("Add button works")
//            GlobalScope.launch(Dispatchers.Main) {
//                val intent = Intent(this@Section1, EntityActivity::class.java)
//                startActivity(intent)
//            }
//        }
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
            val list = model.getDraft()
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