package com.bullshitman.edu105

import android.content.Context
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
import com.bullshitman.edu105.model.ResponseData
import com.bullshitman.edu105.model.Semester

private const val TAG = "DataResponseFragment"

class DataResponseFragment : Fragment() {

    interface Callbacks {
        fun onDisciplineSelected(semester: Semester)
    }

    private var callbacks: Callbacks? = null
    private lateinit var responseLiveData: LiveData<ResponseData>

    private lateinit var dataRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        responseLiveData = EduFetchr().fetchResponce()
        updateUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_data_response, container, false)
        dataRecyclerView = view.findViewById(R.id.data_recycler_view)
        dataRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    private inner class ActivityHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val nameTextView = itemView as TextView
        private lateinit var semester: Semester
        init {
            nameTextView.setOnClickListener(this)
        }

        fun bind(semester: Semester) {
            this.semester = semester
            nameTextView.text = semester.discipline.name
        }

        override fun onClick(v: View?) {
            Log.d(TAG, "was pressed ${semester.discipline.id}")
            callbacks?.onDisciplineSelected(semester)
        }
    }

    private inner class ActivityAdapter(val semesters: List<Semester>) : RecyclerView.Adapter<ActivityHolder>() {
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

    private fun updateUI() {
        responseLiveData.observe(
            this,
            Observer { responseData ->
                Log.d(TAG, "Response received: $responseData")
                dataRecyclerView.adapter = ActivityAdapter(responseData.semesters.toSet().toList())
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance() = DataResponseFragment()
    }
}