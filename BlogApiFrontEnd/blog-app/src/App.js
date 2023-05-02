import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route  } from 'react-router-dom';
import Home from './components/Pages/Home';
import Login from './components/Pages/Login';
import SignUp from './components/Pages/SignUp';
import About from './components/Pages/About';

function App() {
  return (
    <BrowserRouter>
    <Routes>

      <Route path="/" element={<Home/>}></Route>
      <Route path="/login" element={<Login/>}></Route>
      <Route path="/signup" element={<SignUp/>}></Route>
      <Route path="/about" element={<About/>}></Route>

    </Routes>
    </BrowserRouter>

  );
}

export default App;
