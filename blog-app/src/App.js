import logo from "./logo.svg";
import "./App.css";
import Base from "./components/base";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Singup from "./pages/Signup";
import About from "./pages/About";
import Services from "./pages/sevices";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Userdashboard from "./pages/user-modules/Userdashboard";
import Privateroute from "./components/Privateroute";
import PostPage from "./pages/PostPage";
import ProfileInfo from "./pages/user-modules/Profileinfo";
function App() {
  return (
    <BrowserRouter>
      <ToastContainer position="bottom-center" />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        {/* <Route
          path="/"
          element={
            <Base title="Home">
              <Home />
            </Base>
          }
        /> */}
        <Route path="/login" element={<Login />}></Route>
        <Route path="/signup" element={<Singup />}></Route>
        <Route path="/about" element={<About />}></Route>
        <Route path="/sevices" element={<Services />} />
        <Route path="/posts/:postId" element={<PostPage />} />
        <Route path="/user" element={<Privateroute />}>
          <Route path="dashboard" element={<Userdashboard />} />
          <Route path="profile-info" element={<ProfileInfo />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
