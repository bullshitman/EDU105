package com.bullshitman.edu105

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bullshitman.edu105.model.Discipline
import com.bullshitman.edu105.model.ResponseData
import com.bullshitman.edu105.model.Semester

private const val TAG = "DataResponseFragment"

class DataResponseFragment : Fragment() {
    private lateinit var dataRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val responseLiveData: LiveData<ResponseData> = EduFetchr().fetchResponce()
        responseLiveData.observe(
            this,
            Observer {responseData ->
                Log.d(TAG, "Response received: $responseData")
                dataRecyclerView.adapter = ActivityAdapter(responseData.semesters)            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data_response, container, false)
        dataRecyclerView = view.findViewById(R.id.data_recycler_view)
        dataRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    private class ActivityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView = itemView as TextView
        fun bind(semester: Semester) {
            nameTextView.text = semester.toString()
        }
    }

    private class ActivityAdapter(val semesters: List<Semester>) : RecyclerView.Adapter<ActivityHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return ActivityHolder(view)
        }

        override fun getItemCount(): Int {
            return semesters.size
        }

        override fun onBindViewHolder(holder: ActivityHolder, position: Int) {
            val semester = semesters[position]
            holder.bind(semester)
        }

    }

    companion object {
        fun newInstance() = DataResponseFragment()
    }
}