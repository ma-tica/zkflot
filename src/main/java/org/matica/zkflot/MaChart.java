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

package org.matica.zkflot;

import java.util.List;

import org.matica.zkdata.ChartAxis;
import org.matica.zkdata.ChartData;
import org.zkoss.json.JSONArray;
import org.zkoss.zul.impl.XulElement;

public class MaChart extends XulElement 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5976295377425974057L;

	private List<ChartData> _dataset;
	
	//private String _data;
	
	private ChartAxis _xaxis;
	
	private ChartAxis _yaxis;
	



	private String _lines;
	private String _bars;
	private String _legend;
	private String _title;



	private boolean _stacked;
	
	//private String _model;
	

	public List<ChartData> getDataset()
	{
		return _dataset;
	}

	public void setDataset(List<ChartData> data)
	{		
		_dataset = data;
		smartUpdate("dataset", buildJsonData());
	}
	
	public ChartAxis getXaxis()
	{
		return this._xaxis;
	}
	public void setXaxis(ChartAxis xaxis)
	{		
		_xaxis = xaxis;
		smartUpdate("xaxis", this._xaxis.toJson().toJSONString());
	}
	
	public ChartAxis getYaxis() {
		return _yaxis;
	}
	public void setYaxis(ChartAxis yaxis) {
		this._yaxis = yaxis;
		smartUpdate("yaxis", this._yaxis.toJson().toJSONString()); 
	}


	public String getLines() {
		return _lines;		
	}

	public void setLines(String lines) {
		this._lines = lines;
		smartUpdate("lines", _lines);
	}
	
	public String getBars() {
		return _bars;		
	}

	public void setBars(String bars) {
		this._bars = bars;
		smartUpdate("bars", _bars);
	}

	public String getLegend() {
		return _legend;
	}

	public void setLegend(String legend) {
		this._legend = legend;
		smartUpdate("legend", _legend);
	}
	
	public String getTitle() {
		return _title;
	}
	
	public void setTitle(String title) {
		this._title = title;
		smartUpdate("title", _title);		
	}
	
	public boolean getStacked() {
		return _stacked;
	}

	public void setStacked(boolean stacked) {
		this._stacked = stacked;
		smartUpdate("stack", _stacked);
	}

	
	private String buildJsonData()
	{
		if (_dataset != null)
		{
			JSONArray a = new JSONArray();
			for(ChartData d : this._dataset )
			{
				a.add(d.toJson());
			}
		
			return  a.toJSONString();
			
		}else
		{
			return null;
		}
		
		
	}
	
	
	
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)  throws java.io.IOException {
            super.renderProperties(renderer);
            
            String dataset = buildJsonData();
    
            if (dataset != null) {
                    render(renderer, "dataset", dataset);
                    
            }
            
            if (this._xaxis != null)
            {
            	render(renderer, "xaxis", this._xaxis.toJson().toJSONString());
            }
            
            if (this._yaxis != null)
            {
            	render(renderer, "yaxis", this._yaxis.toJson().toJSONString());
            }
            if (this._lines != null)
            {
            	render(renderer, "lines", this._lines);
            }
            if (this._bars != null)
            {
            	render(renderer, "bars", this._bars);
            }
            if (this._legend != null)
            {
            	render(renderer, "legend", this._legend);
            }
            if (this._stacked != false)
            {
            	render(renderer, "stack", this._stacked);
            }
            if (this._title != null)
            {
            	render(renderer, "title", this._title);
            }
            
    }
}
