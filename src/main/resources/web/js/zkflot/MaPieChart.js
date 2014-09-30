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


zkflot.MaPieChart = zk.$extends(zk.Widget, {
    _dataSet : null, 
    _containerWidth :null,
    _containerHeight : null,
    _title : null,
   
    
    getDataset : function() {
        return this._dataSet;
    },
    setDataset : function(dataSet) {
        if (this._dataSet != dataSet) {
            this._dataSet = dataSet;
            if (this.desktop)
            {
                
            	this.plot_();
            	this.poslegend_();
            }
        }
    },
    
    getTitle : function() {
    	return this._title;
    },
    setTitle : function(title) {
    	if (this._title != title){
    		this._title = title;
    	}
    },
    
    plot_ : function() {
    	var dt = jq.evalJSON(this._dataSet);
    	
    	
    	
    	var plot = $.plot("#" + this.uuid , 
    			dt,{
    	          	series: {
    	          		pie: {
    	          			show: true,
    	          			tilt: 1
    	          		}
    	          	},
    	          	grid: {
    	          		hoverable: true,
    	          		clickable: true
    	          	},
    	          	
    	          	tooltip: true,
    	          	
      	          	tooltipOpts: {
    					content: "%s: %p.1%",
    					shifts: {
    						x: -60,
    						y: 15
    					},
    					defaultTheme: true
    	          		
    				}  	          	
    	          	
    	          });
    
    } ,
    
    bind_: function (desktop, skipper, after) {
    	this.$supers(zkflot.MaPieChart, 'bind_', arguments);
    	
    	//move temporary the flot placeholder to body to plot correctly and avoid problem with hidden 
    	//(display: none) parents
    	$('#' +this.uuid).appendTo('body');
    	
    	this.plot_();
    	this.poslegend_();
    	this.resizecontainer_();    	
    	
    	//Replace the flot placeholder to the original position
    	$('#' +this.uuid).appendTo( $('#' + this.uuid + "_container"));
    	
    },
    
    poslegend_ : function() {
    	var id =  this.uuid; 
    	var w = $('#' + id + ' > .legend > div').width();
    	
    	$('#' + id + ' > .legend > table').css('right', w * -1);
    	$('#' + id + ' > .legend > div').css('right', w * -1);
    } ,

    resizecontainer_ : function() {
    	var id = this.uuid; 
    	var w = $('#' + id).outerWidth();
    	w += $('#' + id + ' > .legend > table').width();
    	w += 20;    	
    	$('#' + this.uuid + '_container' ).parent().css('width', w );
    } ,
    
    unbind_: function () {
		this.$supers(zkflot.MaPieChart,'unbind_', arguments);
	}
 
});