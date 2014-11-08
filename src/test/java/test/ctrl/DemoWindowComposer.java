/**
 *   - ZKFlot -
 *   
 *   Copyright (C) 2014  Matteo Carminati (mcarminati@ma-tica.it)
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package test.ctrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.matica.zkflot.MaChart;
import org.matica.zkflot.MaPieChart;
import org.matica.zkdata.ChartAxis;
import org.matica.zkdata.ChartData;
import org.matica.zkdata.MaPoint;
import org.matica.zkdata.PieChartData;
import org.matica.zkflot.MyComp;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class DemoWindowComposer extends GenericForwardComposer {
	
	//private MyComp myComp;
	private MaPieChart pchart;
	private MaChart lineChart;
	private MaChart barChart;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		//myComp.setText("Hello ZK Component!! Please click me.");
		this.test_build_piechart();
		this.test_build_linechart();
		this.test_build_barchart();
		//comp.appendChild(pchart);
		//comp.appendChild(lineChart);
	}
	
//	public void onFoo$myComp (ForwardEvent event) {
//		Event mouseEvent = (Event) event.getOrigin();
//		alert("You listen onFoo: " + mouseEvent.getTarget());
//	}
	
	private void test_build_piechart()
	{
		
		List<PieChartData> dataset = new ArrayList<PieChartData>();
	
		//pchart = new MaPieChart();
		
		dataset.add(new PieChartData(22.3, "EU", "#1111dd"));
		dataset.add(new PieChartData(58.3, "USA", "#99ff22"));
		dataset.add(new PieChartData(98.3, "ASIA", "#22aa22"));
		pchart.setDataset(dataset);
		pchart.setWidth("460px");
		pchart.setHeight("460px");
		pchart.setTitle("Pie Chart Exmple ");
		//pchart.setContainerHeight("500px");
		//pchart.setContainertWidth("500px");
	}

	
	private void test_build_linechart()
	{
		
		//lineChart = new MaChart();
		
		List<ChartData> dataset = new ArrayList<ChartData>();
		dataset.add(new ChartData("EU", Arrays.asList(new MaPoint(1, 30.2),
													  new MaPoint(2, 10.2),
													  new MaPoint(3, 14.2),
													  new MaPoint(4, 15.2),
													  new MaPoint(5, 21.2))));
		
		dataset.add(new ChartData("USA", Arrays.asList(new MaPoint(1, 25.2),
				  									   new MaPoint(2, 18.4),
				  									   new MaPoint(3, 21.2),
				  									   new MaPoint(4, 36.2),
				  									   new MaPoint(5, 31.2),
				  									   new MaPoint(6, 33.2))));
		
		ChartAxis xaxis = new ChartAxis();
		//xaxis.setAxisLabel("Ciaoooo");
		xaxis.setPosition(ChartAxis.axe_position.BOTTOM);
		xaxis.addTick("P 0", 0.0);
		xaxis.addTick("P 1", 1.0);
		xaxis.addTick("P 2", 2.0);
		xaxis.addTick("3", 3.0);
		xaxis.addTick("4", 4.0);
		xaxis.addTick("5", 5.0);
		xaxis.addTick("P_6", 6.0);
		
		lineChart.setDataset(dataset);
		lineChart.setLines("{show: true}");
		
		lineChart.setBars("{show: false, zero: true, barWidth: 0.5	, align: 'center'}");
		lineChart.setStacked(false);
		lineChart.setWidth("460px");
		lineChart.setHeight("460px");
		lineChart.setXaxis(xaxis);
		lineChart.setTitle("Line Chart example");
		
		
		
	}

	
	private void test_build_barchart()
	{
		
		//lineChart = new MaChart();
		
		List<ChartData> dataset = new ArrayList<ChartData>();
		dataset.add(new ChartData("ASIA", Arrays.asList(new MaPoint(1, 25.2),
				   new MaPoint(2, 18.4),
				   new MaPoint(3, 21.2),
				   new MaPoint(4, 36.2),
				   new MaPoint(5, 31.2),
				   new MaPoint(6, 3.2))));
		dataset.add(new ChartData("EU", Arrays.asList(new MaPoint(1, 30.2),
													  new MaPoint(2, 10.2),
													  new MaPoint(3, 14.2),
													  new MaPoint(4, 15.2),
													  new MaPoint(5, 3.2),
		  											  new MaPoint(6, 21.2)
		)));
		
		dataset.add(new ChartData("USA", Arrays.asList(new MaPoint(1, 25.2),
				  									   new MaPoint(2, 18.4),
				  									   new MaPoint(3, 21.2),
				  									   new MaPoint(4, 36.2),
				  									   new MaPoint(5, 31.2),
				  									   new MaPoint(6, 33.2))));

		ChartData d1 = new ChartData("USA 9", Arrays.asList(new MaPoint(1, 25.2),
				   new MaPoint(2, 18.4),
				   new MaPoint(3, 21.2),
				   new MaPoint(4, 36.2),
				   new MaPoint(5, 31.2),
				   new MaPoint(6, 33.2)));
		d1.setColor("#ff00ff");
		
		dataset.add(d1);
//		dataset.add(new ChartData("USA 119", Arrays.asList(new MaPoint(1, 25.2),
//				   new MaPoint(2, 18.4),
//				   new MaPoint(3, 21.2),
//				   new MaPoint(4, 36.2),
//				   new MaPoint(5, 31.2),
//				   new MaPoint(6, 33.2))));

		
		ChartAxis xaxis = new ChartAxis();
		//xaxis.setAxisLabel("X axis label");
		xaxis.setPosition(ChartAxis.axe_position.BOTTOM);
		xaxis.addTick("P 0", 0.0);
		xaxis.addTick("P 1", 1.0);
		xaxis.addTick("P 2", 2.0);
		xaxis.addTick("3", 3.0);
		xaxis.addTick("4", 4.0);
		xaxis.addTick("5", 5.0);
		xaxis.addTick("P_6", 6.0);
		
		barChart.setDataset(dataset);
		barChart.setLines("{show: true}");
		//barWidth: 0.10	,
		barChart.setBars("{show:false}");
		//barChart.setBars("{show: true, zero: true, barWidth: 0.15, align: 'center'}");
		barChart.setStacked(false);
		barChart.setWidth("760px");
		barChart.setHeight("460px");
		barChart.setXaxis(xaxis);
		barChart.setTitle("Bar Chart example");
		
		
		
	}
	
	
}