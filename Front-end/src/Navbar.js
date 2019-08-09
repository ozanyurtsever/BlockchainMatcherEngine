import React, { Component } from 'react';
import { Navbar, Nav, Form, FormControl, Button } from 'react-bootstrap';

class NavbarElement extends Component{
    render(){
        return  <>
            <Navbar bg="dark" variant="dark">
              <Navbar.Brand href="#home">ATP Trader</Navbar.Brand>
              <Nav className="mr-auto">
                <Nav.Link href="#home">Borsa</Nav.Link>
                <Nav.Link href="#features">Alım-Satım</Nav.Link>
                <Nav.Link href="#pricing">Cüzdan</Nav.Link>
              </Nav>
              <Form inline>
                <FormControl type="text" placeholder="Döviz Ara" className="mr-sm-2" />
                <Button variant="outline-info">Ara</Button>
              </Form>
            </Navbar>
            <br />
          </>
    }
}

export default NavbarElement;