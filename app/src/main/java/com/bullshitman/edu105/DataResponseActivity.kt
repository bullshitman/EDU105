package com.bullshitman.edu105

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bullshitman.edu105.model.Semester
private const val TAG = "ResponseActivity"

class DataResponseActivity : AppCompatActivity() , DataResponseFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_responce)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, DataResponseFragment.newInstance())
                .commit()
        }
    }

    override fun onDisciplineSelected(semester: Semester) {
            Log.d(TAG, "was pressed: ${semester.discipline.id}")
            val fragment = DataFragment.newInstance(semester.id)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
    }

}