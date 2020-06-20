package com.bullshitman.edu105

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

private const val ARG_SEM_ID = "semester_id"

class DataFragment : Fragment() {

    private lateinit var professorChart: PieChart
    private lateinit var disciplineChart: PieChart
    private lateinit var disciplineText: TextView
    private lateinit var professorText: TextView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data_response, container, false)
        professorChart = view.findViewById(R.id.professorChart)
        disciplineChart = view.findViewById(R.id.disciplineChart)
        professorText = view.findViewById(R.id.professor)
        professorText.text = resources.getString(R.string.prof_rating)
        disciplineText = view.findViewById(R.id.discipline)
        disciplineText.text = resources.getString(R.string.disc_rating)
        setupCharts()
        return view
    }

    private fun setupCharts() {
        val dataSetProfessor = ArrayList<PieEntry>()
        val dataSetDiscipline = ArrayList<PieEntry>()


        dataSetProfessor.add(PieEntry(100f, resources.getString(R.string.requirements)))
        dataSetProfessor.add(PieEntry(50f, resources.getString(R.string.explanation)))
        dataSetProfessor.add(PieEntry(50f, resources.getString(R.string.feedback)))
        dataSetProfessor.add(PieEntry(67f, resources.getString(R.string.extracurricular)))

        dataSetDiscipline.add(PieEntry(50f, resources.getString(R.string.purview)))
        dataSetDiscipline.add(PieEntry(67f, resources.getString(R.string.career)))
        dataSetDiscipline.add(PieEntry(67f, resources.getString(R.string.newness)))
        dataSetDiscipline.add(PieEntry(0f, resources.getString(R.string.difficult)))

        val dataSet = PieDataSet(dataSetDiscipline, "")
        val dataSetProf = PieDataSet(dataSetProfessor, "")

        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        disciplineChart.data = data
        disciplineChart.highlightValues(null)
        disciplineChart.invalidate()
        disciplineChart.animateXY(1000, 1000)


        dataSetProf.setDrawIcons(false)
        dataSetProf.sliceSpace = 3f
        dataSetProf.iconsOffset = MPPointF(0F, 40F)
        dataSetProf.selectionShift = 5f
        dataSetProf.setColors(*ColorTemplate.COLORFUL_COLORS)

        val dataProf = PieData(dataSetProf)
        dataProf.setValueTextSize(11f)
        dataProf.setValueTextColor(Color.DKGRAY)
        professorChart.data = dataProf
        professorChart.highlightValues(null)
        professorChart.invalidate()
        professorChart.animateXY(1000, 1000)
    }

    companion object {
        fun newInstance(semesterId: Int): DataFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SEM_ID, semesterId)
            }
            return DataFragment().apply {
                arguments = args
            }
        }
    }
}