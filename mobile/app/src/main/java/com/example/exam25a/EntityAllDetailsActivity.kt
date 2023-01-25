package com.example.exam25a

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.exam25a.databinding.EntityAllDetailsBinding
import com.example.exam25a.domain.MyEntity
import com.example.exam25a.utils.logd
import com.example.exam25a.utils.toast
import com.example.exam25a.viewmodels.ApiViewModel
import com.example.exam25a.viewmodels.MyEntityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

class EntityAllDetailsActivity : AppCompatActivity() {
    companion object {
        const val ENTITY_ID = "id"
        const val ENTITY_NAME = "name"
        const val ENTITY_GROUP = "group"
        const val ENTITY_DETAILS = "details"
        const val ENTITY_STATUS = "status"
        const val ENTITY_STUDENTS = "students"
        const val ENTITY_TYPE = "type"
    }

    private lateinit var binding: EntityAllDetailsBinding
    private val model: ApiViewModel by viewModels()
    private lateinit var entityViewModel: MyEntityViewModel
    private lateinit var spinner: ProgressBar
    private var entity: MyEntity? = null

    private var id = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntityAllDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        spinner = binding.progressBar
        setContentView(view)

        entityViewModel = ViewModelProvider(this).get(MyEntityViewModel::class.java)

        val bundle: Bundle? = intent.extras
        if(bundle != null) {
            logd("In bundle...")
            id = bundle.getInt(ENTITY_ID, 0)
            fetchData(id)
            logd("id: $id")
        }
    }

    private fun fetchData(id: Int) {
        spinner.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            toast("Downloading data from server!")
            val currentEntity = model.getOne(id)
            displayEntities(currentEntity)
            toast("Successfully downloaded data from server!")
            spinner.visibility = View.GONE
        }
    }

    private fun displayEntities(givenEntity: MyEntity) {
        entity = givenEntity
        binding.id.text = entity?.id.toString()
        binding.name.text = entity?.name
        binding.group.text = entity?.group
        binding.details.text = entity?.details
        binding.status.text = entity?.status
        binding.students.text = entity?.students.toString()
        binding.type.text = entity?.type
    }

//    fun setupClickListener(activity: AppCompatActivity) {
//        activity.setOnClickListener {
//            println("Activity clicked!")
//        }
//    }
}