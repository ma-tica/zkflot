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



function(out) {
	
	 var containerclass =this.getZclass() + '-container';
	 var titleclass = this.getZclass() + '-title';

/*var uuid = this.uuid + '-container';*/
/*	 out.push('<span>', this.getModel() , '</span>');	
     out.push('<span>', jq.evalJSON(this.getModel()) , '</span>');*/
 /*out.push('<span>', this.domAttrs_(), '</span>');
 out.push('<span> class:', this.getZclass() , '</span>');
*/
//	 var cntstyle = "overflow: auto; width: " + this.getContainerWidth() + "; height:" + this.getContainerHeight() + ";";
 
	 out.push('<div id="' ,this.uuid + "_container" ,  '" class="',containerclass,'"  >');
	 if (this.getTitle() != null){
		 out.push('<span class="', titleclass, '">' + this.getTitle() + '</span>');
	 }
	 out.push('<div', this.domAttrs_(), '>');
	 out.push('</div>');
	 out.push('</div>');
 

 
}


