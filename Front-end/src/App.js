import React, { Component } from 'react';
import './App.css';
import axios from 'axios';
import { Button, Jumbotron, Row, Col, Form   } from 'react-bootstrap';


class BuySell extends Component {

  constructor(props) {
    super(props);
    this.priceInput = React.createRef();
    this.amountInput = React.createRef(); 
    this.state = {
      price: 0.039,
      amount: 1,
      orderID: Math.random().toString(36).substr(2, 9),
      totalValue : 0,
      customerID: this.props.operation == "Alış" ? 'buy' : 'sell',
    }

    this.calculateTotalValue = this.calculateTotalValue.bind(this);
    this.submitHandler = this.submitHandler.bind(this);
    this.clearForm = this.clearForm.bind(this);
 }

 submitHandler(e){
  e.preventDefault();
  console.log(this.state);
  let obj = {
    price: this.state.price,
    amount: this.state.amount,
    total: this.state.totalValue,
  }
  
  this.props.getSubmittedDataList(obj, this.state.customerID);
  this.state.customerID == 'buy' ? axios.post(`http://localhost:8080/buyOrder`, this.state )
                                   :axios.post(`http://localhost:8080/saleOrder`, this.state)
     .then(res => {
       console.log(res);
       console.log(res.data);
       
     })
     alert(this.props.operation + " Gönderildi!");
     this.clearForm();
}
  toFixed(value, precision) {
    var power = Math.pow(10, precision || 0);
    return Math.round(value * power) / power;
  }
 
 clearForm(){
    this.setState({price: '0.039', amount: '1', totalValue: 0, orderID: Math.random().toString(36).substr(2, 9)});
 }
 
 calculateTotalValue(e){
  let value = this.toFixed(this.priceInput.current.value * this.amountInput.current.value, 4);
  this.setState({[e.target.name] : e.target.value, totalValue: value});
 }

  render() {
    const {price, amount, totalValue} = this.state
    return <div>
      <h5>{this.props.operation} ATPCoin(₳)</h5>
      <br/>
      <Form onSubmit = {this.submitHandler}>
      <Form.Group as={Row} controlId="formPrice">
        <Form.Label column sm="2">
          Fiyat(₿):
      </Form.Label>
        <Col sm="8" style = {{margin: "0 auto"}}>
            <Form.Control name="price" 
                          ref={this.priceInput} 
                          value={price} 
                          onChange={this.calculateTotalValue} 
                          type="number" 
                          step = {0.001}
                          min = {0.01}
                          placeholder="Fiyat Giriniz" />
        </Col>
      </Form.Group>

      <Form.Group as={Row} controlId="formAmount">
        <Form.Label column sm="2">
          Miktar:
      </Form.Label>
        <Col sm="8" style = {{margin: "0 auto"}}>
            <Form.Control name = "amount" 
                          ref = {this.amountInput} 
                          value = {amount} 
                          onChange = {this.calculateTotalValue}  
                          type="number" 
                          min = {1}
                          placeholder="Miktar Giriniz" />
        </Col>
      </Form.Group>

      <Form.Group as={Row} controlId="formTotal">
        <Form.Label column sm="2">
          Toplam:
      </Form.Label>
        <Col sm="8" style = {{margin: "0 auto"}}>
          <Form.Control value = {totalValue}  type="number"  readOnly/>
        </Col>
      </Form.Group>

      <Button style = {{float: "right"}} variant="primary" type="submit">
          Emri Koy
      </Button>
    </Form></div>
  }
}

class App extends Component {

  render() {
    return <Jumbotron className="exchangePanel">

      <Row id="panelNavBar">
        <h2 style = {{margin: "0 auto"}}>Alım - Satım</h2>
      </Row>
      <hr />
      <Row>
        <Col id="leftPanel"><BuySell getSubmittedDataList = {this.props.getSubmittedDataList} operation = "Alış" /></Col>
        <Col id="rightPanel"><BuySell getSubmittedDataList = {this.props.getSubmittedDataList} operation = "Satış" /></Col>
      </Row>

    </Jumbotron>

  }
}

export default App;
