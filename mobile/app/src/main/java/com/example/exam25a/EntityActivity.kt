package com.example.exam25a

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.exam25a.databinding.ActivityEntityBinding
import com.example.exam25a.domain.MyEntity
import com.example.exam25a.utils.logd
import com.example.exam25a.utils.toast
import com.example.exam25a.viewmodels.ApiViewModel
import com.example.exam25a.viewmodels.MyEntityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EntityActivity: AppCompatActivity() {
    companion object {
        const val ENTITY_ID = "id"
        const val ENTITY_NAME = "name"
        const val ENTITY_GROUP = "group"
        const val ENTITY_DETAILS = "details"
        const val ENTITY_STATUS = "status"
        const val ENTITY_STUDENTS = "students"
        const val ENTITY_TYPE = "type"
    }

    private lateinit var binding: ActivityEntityBinding
    private val model: ApiViewModel by viewModels()
    private lateinit var entityViewModel: MyEntityViewModel

    private var id = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        entityViewModel = ViewModelProvider(this).get(MyEntityViewModel::class.java)

        val bundle: Bundle? = intent.extras
        if(bundle != null) {
            logd("In bundle...")
            id = bundle.getInt(ENTITY_ID, 0)
            logd("id: $id")
            if(id != 0) {
                binding.editName.setText(bundle.getString(ENTITY_NAME))
                binding.editGroup.setText(bundle.getString(ENTITY_GROUP))
                binding.editDetails.setText(bundle.getString(ENTITY_DETAILS))
                binding.editStatus.setText(bundle.getString(ENTITY_STATUS))
                binding.editStudents.setText(bundle.getString(ENTITY_STUDENTS))
                binding.editType.setText(bundle.getString(ENTITY_TYPE))
            }
        }

        binding.btnSave.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val nume = binding.editName.text.toString()
                val group = binding.editGroup.text.toString()
                val details = binding.editDetails.text.toString()
                val status = binding.editStatus.text.toString()
                val students = binding.editStudents.text.toString()
                val type = binding.editType.text.toString()
//                val status = binding.editStatus.isChecked

//                val validationMessage = validate(nume, medie, etaj, orientare)
                val validationMessage = "";
                if(validationMessage.isNotEmpty()) {
                    toast(validationMessage)
                } else {
                    val myEntity = MyEntity(0, nume, group, details, status, students.toInt(), type);
                    val result = model.save(myEntity)
                    if(result >= 1) {
                        toast("Successfully saved the admission document!\nDocument id: $result")
                        logd("Saved entity id: $result to server")
                        finish()
                    } else if(result == 0) {
                        toast("Could not save to Server!")
                    } else if(result == -1) {
                        toast("Could not save to Server due to an error or connection problems!")
                    } else if(result == -2) {
                        toast("An error occurred when trying to save to Server!")
                    }
//                    if(result < 1) {
//
//                    }
                    entityViewModel.insert(myEntity)
                    finish()
                    toast("Successfully saved the admission document!\nDocument id: $result")
                    logd("Saved entity id: $result to local db")
                }
            }
        }
    }


//    fun setupClickListener(activity: AppCompatActivity) {
//        activity.setOnClickListener {
//            println("Activity clicked!")
//        }
//    }
}