// import { Children } from "react";
import CustomNavbar from "./CustomNavbar";

const Base = ({ title = "Welcome to website ", children }) => {
  return (
    <div className="container-fluid p-0 m-0">
      {children}
      <footer>This is footer</footer>
    </div>
  );
};

export default Base;
