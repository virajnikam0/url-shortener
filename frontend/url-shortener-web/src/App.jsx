import './App.css'
import Footer from './components/Footer';
import Header from './components/Header';
import LongToShort from './components/LongToShort';
import "bootstrap/dist/js/bootstrap.min.js";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {

  return (
    <>
      <Header/>
      <LongToShort />
      <Footer/>
    </>
  )
}

export default App
