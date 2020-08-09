package com.example.testdemohyperlink

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.testdemohyperlink.adapter.ImageViewAdapter
import com.example.testdemohyperlink.api.ApiClient
import com.example.testdemohyperlink.custom.GridItemDecoration
import com.example.testdemohyperlink.models.Image
import com.example.testdemohyperlink.models.ImagesListResponse
import kotlinx.android.synthetic.main.activity_images.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ImageViewAdapter
    var dataList = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        initView()
    }

    private fun initView() {
        recyclerview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //This will for default android divider
        recyclerview.addItemDecoration(GridItemDecoration(10, 2))

        adapter = ImageViewAdapter()
        recyclerview.adapter = adapter

        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getData()
    }

    private fun getData() {
        val call: Call<ImagesListResponse> = ApiClient.getClient.getImages()
        call.enqueue(object : Callback<ImagesListResponse> {

            override fun onFailure(call: Call<ImagesListResponse>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

            override fun onResponse(
                call: Call<ImagesListResponse>,
                response: Response<ImagesListResponse>
            ) {
                progerssProgressDialog.dismiss()

                response.body()?.images?.let { dataList.addAll(it) }
                adapter.setImageList(dataList)
            }

        })
    }
}