import React, { Component } from 'react';
import TraderPanel from './TraderPanel';
import NavbarElement from './Navbar';
import './MainPage.css';
import StockChart from './StockChart';



class MainPage extends Component {

    render() {
      return <><NavbarElement />
              <StockChart />
              <br/><br/>
              <TraderPanel />
             <br /><br/><br/>
             <footer>
               <img id = "footerLogo" src = "https://www.atp.com.tr/wp-content/uploads/20plus.png" height = "50vh"/>
             </footer></>
    }
  }
  
  export default MainPage;
  