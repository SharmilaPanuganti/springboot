import React, { useState, useContext, useEffect } from "react";
import { NavLink as ReactLink, useNavigate } from "react-router-dom";
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";
import { doLogout, getCurrentUserDetail, isloggedIn } from "../auth";
import { userContext } from "../context/userContext";

const CustomNavbar = () => {
  const userContextData = useContext(userContext);
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const [login, setLogin] = useState(false);
  const [user, setUser] = useState(undefined);

  useEffect(() => {
    setLogin(isloggedIn());
    setUser(getCurrentUserDetail());
  }, [login]);

  const logout = () => {
    doLogout(() => {
      setLogin(false);
      setUser({
        login: false,
        datat: null,
      });
      navigate("/");
    });
  };

  const toggle = () => setIsOpen(!isOpen);

  return (
    <Navbar color="dark" dark expand="md" fixed="top" className="px-5 shadow-sm">
      <NavbarBrand tag={ReactLink} to="/" className="fw-bold text-success">
        MyBlogs
      </NavbarBrand>
      <NavbarToggler onClick={toggle} />
      <Collapse isOpen={isOpen} navbar>
        <Nav className="me-auto" navbar>
          <NavItem>
            <NavLink tag={ReactLink} to="/">
              New Feed
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink tag={ReactLink} to="/About/">
              About
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink tag={ReactLink} to="/services/">
              Services
            </NavLink>
          </NavItem>

          <UncontrolledDropdown nav inNavbar>
            <DropdownToggle nav caret>
              More
            </DropdownToggle>
            <DropdownMenu end>
              <DropdownItem tag={ReactLink} to="/contact">
                Contact Us
              </DropdownItem>
              <DropdownItem>Facebook</DropdownItem>
              <DropdownItem divider />
              <DropdownItem>Youtube</DropdownItem>
              <DropdownItem>Instagram</DropdownItem>
              <DropdownItem>LinkedIn</DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Nav>
        <Nav navbar>
          {login ? (
            <>
              <NavItem>
                <NavLink tag={ReactLink} to="/user/profile-info">
                  {user?.username}
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/user/dashboard">
                  Add Post
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink onClick={logout}>Logout</NavLink>
              </NavItem>
            </>
          ) : (
            <>
              <NavItem>
                <NavLink tag={ReactLink} to="/Login">
                  Login
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/Signup">
                  Signup
                </NavLink>
              </NavItem>
            </>
          )}
        </Nav>
      </Collapse>
    </Navbar>
  );
};

export default CustomNavbar;
