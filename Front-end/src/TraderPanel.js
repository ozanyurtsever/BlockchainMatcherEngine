import React, { Component } from 'react';
import App from './App';
import { Button, Jumbotron, Row, Col, Form, Card, Table   } from 'react-bootstrap';
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-balham.css';
import { privateEncrypt } from 'crypto';



class OrderTable extends Component{

  constructor(props){
    super(props);
    this.state = {
      columnDefs: [{
        headerName: "Fiyat", field: "price", width: 85, sortable: true
      }, {
        headerName: "Miktar", field: "amount", width: 85, sortable: true
      }, {
        headerName: "Toplam", field: "total", width: 85
      }],
      rowSelection: "single",
    }

  }


  render(){
    return <div 
        className="ag-theme-balham"
        style={{ 
          height : "70vh",
          width: "auto"
          
         }} 
      >
        <AgGridReact
          rowSelection = {this.state.rowSelection}
          columnDefs={this.state.columnDefs}
          rowData={this.props.tableData}>
        </AgGridReact>
      </div>
  
  }
}

class TraderPanel extends Component {

  constructor(props){
    super(props);
    this.state = {
      buyTableData : [],
      sellTableData: [],
    }
  this.getSubmittedDataList = this.getSubmittedDataList.bind(this);
  }

  getSubmittedDataList(formJsonData,activeTable){
    let buyTableData = this.state.buyTableData.slice();
    let sellTableData = this.state.sellTableData.slice();
    console.log("akjhfahjsejf" + activeTable);
    activeTable == 'buy' ? buyTableData.push(formJsonData) : sellTableData.push(formJsonData);
    activeTable == 'buy' ? this.setState({buyTableData : buyTableData}) : this.setState({sellTableData : sellTableData});
  }

    render() {
      return <Row style = {{width: "80%", margin: "0 auto"}}>
              <Col xs = "3"><OrderTable tableData = {this.state.buyTableData} operation = "Alış" /></Col>
              <Col xs = "6"><App getSubmittedDataList = {this.getSubmittedDataList}/></Col>
              <Col xs="3"><OrderTable  tableData = {this.state.sellTableData}  operation = "Satış" /></Col>
             </Row>
    }
  }
  
  export default TraderPanel;
  