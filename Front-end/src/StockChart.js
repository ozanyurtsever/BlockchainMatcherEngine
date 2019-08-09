import React, { Component } from 'react';
import { ChartCanvas, Chart } from "react-stockcharts";
import {
    CandlestickSeries,
} from "react-stockcharts/lib/series";
import { XAxis, YAxis } from "react-stockcharts/lib/axes";
import { discontinuousTimeScaleProvider } from "react-stockcharts/lib/scale";
import { last } from "react-stockcharts/lib/utils";


function GenerateData(dayCount){
    var newDate = new Date();
    newDate.setDate(newDate.getDate() + dayCount);
    let data = {
        "date": newDate,
        "price": Math.random() * 0.1800 + 0.1500 + (dayCount*10),
        "accuracy": 0.1489 ,
        "high":  Math.random() * 1373.5059 + 2500.0765 + (dayCount*10),
        "low": Math.random() * 974.1351 + 1150 + (dayCount*10),
        "open": Math.random() * 1412.5995 + 1800 + (dayCount*10),
        "close": Math.random() * 1447.5179 + 1900 + (dayCount*10)
      };

    return data;
}

class StockChart extends Component{
  constructor(props){
    super(props);
    this.state = {
      stock : this.GenerateJsonArray(200)
    }
}
  GenerateJsonArray(len){
    let array = [];
    for(var i = 0; i<len; i++){
        array.push(GenerateData(i+1));
    }
    return array;
    }
  
  render(){

    const initialData = this.state.stock;

		const xScaleProvider = discontinuousTimeScaleProvider
			.inputDateAccessor(d => d.date);
		const {
			data,
			xScale,
			xAccessor,
			displayXAccessor,
		} = xScaleProvider(initialData);
		const xExtents = [
			xAccessor(last(data)),
			xAccessor(data[data.length - 200])
		];
    return (
            <><h4 style = {{marginLeft: "40%"}}>ATPCoin-Bitcoin Tablosu</h4>
			<ChartCanvas height={300}
				width={1200}
				margin={{ left: 220, right: 0, top: 50, bottom: 30 }}
				type="svg"
				seriesName="MSFT"
				data={data}
				xScale={xScale}
				xAccessor={xAccessor}
				displayXAccessor={displayXAccessor}
				xExtents={xExtents}
			>

				<Chart id={1} yExtents={d => [d.high, d.low]}>
					<XAxis axisAt="bottom" orient="bottom" ticks={6}/>
					<YAxis axisAt="left" orient="left" ticks={5} />
					<CandlestickSeries  />
				</Chart>
			</ChartCanvas></>
		)
}
}

export default StockChart;