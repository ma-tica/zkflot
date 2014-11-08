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



/**
 *
 * Base naming rule:
 * The stuff start with "_" means private , end with "_" means protect ,
 * others mean public.
 *
 * All the member field should be private.
 *
 * Life cycle: (It's very important to know when we bind the event)
 * A widget will do this by order :
 * 1. $init
 * 2. set attributes (setters)
 * 3. rendering mold (@see mold/zkflot.js )
 * 4. call bind_ to bind the event to dom .
 *
 * this.deskop will be assigned after super bind_ is called,
 * so we use it to determine whether we need to update view
 * manually in setter or not.
 * If this.desktop exist , means it's after mold rendering.
 *
 */
zkflot.MaChart = zk.$extends(zk.Widget, {
    _dataset : null, // default value
    _xaxis : null,
    _yaxis: null,
    _lines : null,
    _bars : null,
    _stack : false,
    _title : null,
    _legend: '{noColumns: 1,position: \'ne\'}',
    
    getDataset : function() {
        return this._dataset;
    },
    setDataset : function(data) {
        if (this._dataset != data) {
            this._dataset = data;
            if (this.desktop)
           	{                
            	this.plot_();
            	this.poslegend_();
           	}
        }
    },
        
    getXaxis : function()
    {
    	return this._xaxis; 
    },    
    setXaxis : function(xaxis)
    {    	
    	this._xaxis = xaxis;        
    },
    
    getYaxis : function()
    {
    	return this._yaxis; 
    },    
    setYaxis : function(yaxis)
    {    	
    	this._yaxis = yaxis;
    },

    getLines : function()
    {
    	return this._lines;
    },
    setLines : function(lines)
    {
    	this._lines = lines;
    },
    getBars : function()
    {
    	return this._bars;
    },
    setBars : function(bars)
    {
    	this._bars = bars;    	
    },
    
    getStack : function()
    {
    	return this._stack;
    },
    setStack : function(stack)
    {
    	this._stack = stack;
    },

    getLegend : function()
    {
    	return this._legend;
    },
    setLegend : function(legend)
    {
    	this._legend = legend;
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
    	var dt = jq.evalJSON(this._dataset);
    	
    	var xaxisprop = {};
    	if (this._xaxis != null)
    	{
    		xaxisprop = jq.evalJSON(this._xaxis);	
    	}
    	
    	var yaxisprop = {};
    	if (this._yaxis != null)
    	{
    		yaxisprop = jq.evalJSON(this._yaxis);	
    	}
    	var linesprop = {};
    	if (this._lines != null)
		{
    		linesprop = jq.evalJSON(this._lines);	
		}
    	
    	var barssprop = {};
    	if (this._bars != null)
		{
    		barsprop = jq.evalJSON(this._bars);	
		}

    	var legendprop = {};
    	if (this._legend != null)
		{
    		legendprop = jq.evalJSON(this._legend);
		}
    	var stackprop = this._stack;
    	
    	//reset multiseries bar position on x axis
    	if (this._bars != null && (barsprop.show == true & dt.length > 1 & this._stack != true))
    	{
    		if (barsprop.barWidth == undefined)
    		{
    			barsprop.barWidth = 0.5;
    		}
    		var bw = barsprop.barWidth;
    		var c = 0;
    		
    		//first half: move to left 
    		for(i=Math.floor(dt.length / 2);i>=0;i--)
    		{
				dt[i].data.forEach(function(point){					
					point[0] -= c * bw;
				})
    			c++;
    		}
    		
    		//second half: move to rigth
    		for(i=Math.floor(dt.length / 2)+1;i<dt.length;i++)
    		{
				dt[i].data.forEach(function(point){					
					point[0] += (i - Math.floor(dt.length / 2)) * bw;
				})
    			
    		}
    	}

    	var plot = $.plot("#" + this.uuid ,     			
  			  dt,
  			  {
  		 		
  	          	series: {
  	          		stack: stackprop,
  	          		lines: linesprop, 
  	          		bars: barsprop,    	          		
  					points: {show: false}
  	          	},
  	          	grid: {
  	          		hoverable: true, 
  	          		clickable: true
  	          	},
  	          	xaxis: xaxisprop,
  	          	yaxis: yaxisprop,
  	          	legend: legendprop,
  	          	
  	          	tooltip: true,
  	          	tooltipOpts: {
					content: "'%s':  %y",
					shifts: {
						x: -60,
						y: 25
					}
				}  	          	
  	          });
    	
    	
    } ,
    
    poslegend_ : function() {
    	var w = $('#' + this.uuid + ' > .legend > div').width();
    	
    	$('#' + this.uuid + ' > .legend > table').css('right', (w * -1) );
    	$('#' + this.uuid + ' > .legend > div').css('right', (w * -1) );
    } ,
    
    resizecontainer_ : function() {
    	var id = this.uuid;
    	var w = $('#' + id).outerWidth();    	
    	w += $('#' + id + ' > .legend > table').width();
    	w += 20;
    	
    	
    	$('#' + this.uuid + '_container' ).parent().css('width', w );
    	
    } ,
    
    bind_: function (desktop, skipper, after) {
    	this.$supers(zkflot.MaChart, 'bind_', arguments);

    	//move temporary the flot placeholder to body to plot correctly and avoid problem with hidden 
    	//(display: none) parents
    	$('#' +this.uuid).appendTo('body');

    	this.plot_();    	
    	this.poslegend_();
    	this.resizecontainer_();
    	
    	
    	//Replace the flot placeholder to the original position
    	$('#' +this.uuid).appendTo( $('#' + this.uuid + "_container"));


    },   
 
    
    
    
    unbind_: function () {
		this.$supers(zkflot.MaChart,'unbind_', arguments);
	}
    
});