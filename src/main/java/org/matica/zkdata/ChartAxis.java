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



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;

public class ChartAxis 
{
	
	public enum axe_position
	{
		BOTTOM,
		TOP,
		LEFT,
		RIGHT
	}
	
	private class tick
	{
		private String label;
		private Double value;
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public Double getValue() {
			return value;
		}
		public void setValue(Double value) {
			this.value = value;
		}
		public JSONArray toJson()
		{
			JSONArray a = new JSONArray();
			a.add(this.value);
			a.add(this.label);
			
			return a;
			
		}		
		
		public boolean equals(Object o){
			tick other = (tick)o;
			return (this.value.equals(other.getValue()) );  			
	    }		
		
		public int hashCode() {  
			   
		    int hash = this.value.hashCode();  
		    return hash;  
		}  		
	}
	
	private boolean show = true;  //: null or true/false
	private axe_position position; //: "bottom" or "top" or "left" or "right"
    private String mode; //: null or "time" ("time" requires jquery.flot.time.js plugin)
    private String timezone; //: null, "browser" or timezone (only makes sense for mode: "time")

//    color: null or color spec
//    tickColor: null or color spec
//    font: null or font spec object

    private Double min; //: null or number
    private Double max; //: null or number
    private Double autoscaleMargin; //: null or number

    //transform: null or fn: number -> number
    //inverseTransform: null or fn: number -> number

    private HashSet<tick> ticks = new HashSet<tick>(); //: null or number or ticks array or (fn: axis -> ticks array)
    //tickSize: number or array
    //minTickSize: number or array
    //tickFormatter: (fn: number, object -> string) or string
    //tickDecimals: null or number

    private Double labelWidth; //: null or number
    private Double labelHeight; //: null or number
    private boolean reserveSpace; //: null or true

    private Double tickLength; //: null or number

    private Double alignTicksWithAxis; //: null or number

    private String axisLabel;
    
    
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public axe_position getPosition() {
		return position;
	}

	public void setPosition(axe_position position) {
		this.position = position;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getAutoscaleMargin() {
		return autoscaleMargin;
	}

	public void setAutoscaleMargin(Double autoscaleMargin) {
		this.autoscaleMargin = autoscaleMargin;
	}

	public HashSet<tick> getTicks() {
		return ticks;
	}

	public void addTick(String label, Double value) {
		tick t = new tick();
		t.setLabel(label);
		t.setValue(value);
		this.ticks.add(t);
	}

	public Double getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(Double labelWidth) {
		this.labelWidth = labelWidth;
	}

	public Double getLabelHeight() {
		return labelHeight;
	}

	public void setLabelHeight(Double labelHeight) {
		this.labelHeight = labelHeight;
	}

	public boolean isReserveSpace() {
		return reserveSpace;
	}

	public void setReserveSpace(boolean reserveSpace) {
		this.reserveSpace = reserveSpace;
	}

	public Double getTickLength() {
		return tickLength;
	}

	public void setTickLength(Double tickLength) {
		this.tickLength = tickLength;
	}

	public Double getAlignTicksWithAxis() {
		return alignTicksWithAxis;
	}

	public void setAlignTicksWithAxis(Double alignTicksWithAxis) {
		this.alignTicksWithAxis = alignTicksWithAxis;
	}
    
	public String getAxisLabel() {
		return axisLabel;
	}

	public void setAxisLabel(String axisLabel) {
		this.axisLabel = axisLabel;
	}

    
	public JSONObject toJson()
	{
		JSONObject o = new JSONObject();
		
		o.put("show", this.show);   							 									//show: null or true/false
		if (this.position != null)
		{
			o.put("position", this.position.toString().toLowerCase());  							//position: "bottom" or "top" or "left" or "right"
		}
//			else{
//			o.put("position", axe_position.BOTTOM.toString().toLowerCase());
//		}
		if (this.mode != null){ o.put("mode", this.mode);}
		
		if (this.timezone != null){o.put("timezone", this.timezone);}		
		if (this.min != null){o.put("min", this.min);}
		if (this.max != null){o.put("max", this.max);}  						
		if(this.autoscaleMargin != null){o.put("autoscaleMargin", this.autoscaleMargin);} 			//autoscaleMargin: null or number

		
		if (this.labelWidth!= null){o.put("labelWidth", this.labelWidth);}    						//labelWidth: null or number
		if (this.labelHeight!=null) {o.put("labelHeight", this.labelHeight); }  					//labelHeight: null or number
		if (this.reserveSpace == true)  {
			o.put("reserveSpace", true);  														//reserveSpace: null or true
		}

		if (this.tickLength!=null){o.put("tickLength", this.tickLength);}    						//tickLength: null or number

		if (this.alignTicksWithAxis!=null){o.put("alignTicksWithAxis", this.alignTicksWithAxis);}   //alignTicksWithAxis: null or number
		
		if (this.axisLabel != null){
			o.put("axisLabel", this.axisLabel);
			o.put("axisLabelUseCanvas", true);
			o.put("axisLabelFontSizePixels", 12);
			o.put("axisLabelFontFamily", "Verdana, Arial");
			o.put("axisLabelPadding", 10);

				
		}
		
		
		JSONArray jdArr = new JSONArray();
		
		if (this.ticks != null && this.ticks.size() > 0 ) 
		{
			for (tick t : this.ticks)
			{
				jdArr.add(t.toJson());
			}
			o.put("ticks", jdArr ); 
		}
		
		
		return o;
	}

}
