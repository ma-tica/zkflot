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

package org.matica.zkdata;

import java.util.List;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;

public class ChartData 
{

	private String _label;
	private List<MaPoint> _data;
	private int _barOrder;
	
	

	public ChartData(String label, List<MaPoint> data) {
		super();
		this._label = label;
		this._data = data;
		this._barOrder = -1;
	}
	
	public ChartData(String label, List<MaPoint> data, int barOrder) {
		super();
		this._label = label;
		this._data = data;
		this._barOrder = barOrder;
	}
	
	
	public String getLabel() {
		return _label;
	}
	public void setLabel(String label) {
		this._label = label;
	}
	public List<MaPoint> getData() {
		return _data;
	}
	public void setData(List<MaPoint> data) {
		this._data = data;
	}
	
	public int get_barOrder() {
		return _barOrder;
	}

	public void set_barOrder(int barOrder) {
		this._barOrder = _barOrder;
	}
	
	public JSONObject toJson()
	{
		JSONObject o = new JSONObject();
		o.put("label", this._label);
		JSONArray jdArr = new JSONArray();
		for (MaPoint p : this._data )
		{
			jdArr.add(p.toJson());
		}

		
		o.put("data", jdArr );
		
		if (this._barOrder >= 0)
		{
			
			JSONObject b = new JSONObject();
			b.put("order", this._barOrder);
			o.put("bars", b);
		}
		
		return o;
		
	}
	
	
}
