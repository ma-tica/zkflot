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

import java.math.BigDecimal;






import org.zkoss.json.JSONArray;

public class MaPoint 
{
	private int x;
	private Double y;
	
	
	
	public MaPoint(int x, Double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	
	public JSONArray toJson()
	{
		JSONArray a = new JSONArray();
		a.add(x);
		a.add(y);
		
		return a;
		
	}

	public boolean equals(Object o){
		MaPoint other = (MaPoint)o;
		if (this.x == other.getX() &
			this.y.equals(other.getY()) == true)
		{
			return true;
		}else
		{
			return false;
		}
    }		
	
	public int hashCode() {  
		   
	    int hash = new BigDecimal((this.x + this.y)).hashCode();  
	    return hash;  
	}  		
	
}
