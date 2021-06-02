package com.example.onboardingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_user_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.prefs.AbstractPreferences
import javax.inject.Inject

@AndroidEntryPoint
class UserListActivity: AppCompatActivity() {

    lateinit var adapterrecyclerview: UserAdapter
    private var list = ArrayList<DataModel>()
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        viewModel.showListOfUser()

        viewModel.apiResponse.observe(this, Observer {
            val body = it.body()
            if (body != null) {
                for (i in body.data.indices) {
                    val id = body.data[i].id.toString()
                    val email = body.data[i].email
                    val img = body.data[i].avatar
                    val firstname = body.data[i].first_name
                    val item = DataModel(id, email, img, firstname)
                    list.add(item)
                }
                setAdapter()
            }
        })
    }

    private fun setAdapter() {
        adapterrecyclerview = UserAdapter(list, this@UserListActivity)
        recyclerView.adapter = adapterrecyclerview
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}