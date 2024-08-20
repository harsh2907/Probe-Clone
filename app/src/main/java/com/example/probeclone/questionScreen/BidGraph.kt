package com.example.probeclone.questionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.combinedchart.CombinedChart
import co.yml.charts.ui.combinedchart.model.CombinedChartData
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import com.example.probeclone.ui.theme.UiColors

/**
 * Bar with line chart
 *
 */
@Composable
fun BarWithLineChart() {
    val maxRange = 50
    val groupBarData = DataUtils.getGroupBarChartData(20, 20, 2)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(10.dp)
        .bottomPadding(5.dp)
        .labelData { index -> index.toString() }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()

    val linePlotData = LinePlotData(
        lines = listOf(
            Line(
                DataUtils.getLineChartData(20, maxRange = 50),
                lineStyle = LineStyle(color = UiColors.Blue),
                intersectionPoint = null,
                selectionHighlightPoint = SelectionHighlightPoint(),
                selectionHighlightPopUp = SelectionHighlightPopUp()
            ),
            Line(
                DataUtils.getLineChartData(20, maxRange = 50),
                lineStyle = LineStyle(color = Color.Red),
                intersectionPoint = null,
                selectionHighlightPoint = SelectionHighlightPoint(),
                selectionHighlightPopUp = SelectionHighlightPopUp()
            )
        )
    )
    val colorPaletteList = listOf(UiColors.LightBlue, UiColors.LightRed)
    val legendsConfig = LegendsConfig(
        legendLabelList = listOf(
            LegendLabel(Color.Blue, "Yes"),
            LegendLabel(Color.Red, "No"),
        ),
        gridColumnCount = 2
    )
    val barPlotData = BarPlotData(
        groupBarList = groupBarData,
        barStyle = BarStyle(barWidth = 35.dp),
        barColorPaletteList = colorPaletteList
    )
    val combinedChartData = CombinedChartData(
        combinedPlotDataList = listOf(barPlotData, linePlotData),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )
    Column(
        Modifier.height(350.dp)
    ) {
        CombinedChart(
            modifier = Modifier
                .height(300.dp),
            combinedChartData = combinedChartData
        )
        Legends(
            legendsConfig = legendsConfig
        )
    }
}

