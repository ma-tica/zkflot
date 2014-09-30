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






import org.matica.zkdata.PieChartData;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.zul.impl.XulElement;

public class MaPieChart extends XulElement 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5976295377425974057L;

	private List<PieChartData> _dataset;
	private String _title;
	
	//private String _model;
	
	public List<PieChartData> getDataset()
	{
		return _dataset;
	}

	public void setDataset(List<PieChartData> dataset)
	{
		
		_dataset = dataset;
		smartUpdate("dataset", buildJsonData());
	}
	
	
	

	public String getTitle() {
		return _title;
	}
	


	public void setTitle(String title) {
		this._title = title;
		smartUpdate("title", _title);		
	}

	private String buildJsonData()
	{
		if (_dataset != null)
		{
			JSONArray jsArr = new JSONArray();
			
			for (PieChartData data : _dataset) {
			
				JSONObject jobj = new JSONObject();
				jobj.put("data", data.getData());
				jobj.put("label", data.getLabel());
				if (data.getColor() != null)
				{
					jobj.put("color", data.getColor());
				}
				jsArr.add(jobj);
			}
			
			
			
			return jsArr.toJSONString();
			
		}else
		{
			return null;
		}
		
		
	}
	
	
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)  throws java.io.IOException 
	{
	
            super.renderProperties(renderer);
            
            String blockToRender = buildJsonData();
    
            if (blockToRender != null) {
                    render(renderer, "dataset", blockToRender);
            }
            
            if (this._title != null)
            {
            	render(renderer, "title", _title);
            }
    }
	
	/**
	 * The default zclass is "z-MaPieChart"
	 */
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-mapiechart");
	}


}
