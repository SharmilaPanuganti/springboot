import {
  Card,
  CardBody,
  CardHeader,
  Container,
  FormGroup,
  Row,
  Col,
  Button,
  Input,
} from "reactstrap";
import Base from "../components/base";
// import { Button } from "bootstrap";
import { useState } from "react";
import { loginUser } from "../services/user-services";
import { doLogin } from "../auth";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import CustomNavbar from "../components/CustomNavbar";

const Login = () => {
  const navigate = useNavigate();
  const [loginDetail, setLoginDetail] = useState({
    username: "",
    password: "",
  });
  const handleChange = (event, field) => {
    let actualValue = event.target.value;
    setLoginDetail({
      ...loginDetail,
      [field]: actualValue,
    });
  };
  const handleReset = () => {
    setLoginDetail({
      username: " ",
      password: " ",
    });
  };
  const handleFormSubmit = (event) => {
    event.preventDefault();
    console.log(loginDetail);
    if (loginDetail.username.trim() == "" || loginDetail.password == "") {
      toast.error("Username or password  is required!!");
      return;
    }
    //submit the data to server to generate token
    loginUser(loginDetail)
      .then((data) => {
        console.log(data);
        //save the data to localstorage
        doLogin(data, () => {
          console.log("login detail is saved to localstorage");
          //redirect to user dashboard
          navigate("/user/dashboard");
        });
        toast.success("Login Success");
      })
      .catch((error) => {
        console.log(error);
        if (error.response.status == 400 || error.response.status == 404) {
          toast.error(error.response.data.message);
        } else {
          toast.error("Something went wrong on server !!");
        }
      });
  };

  return (
    <Base>
    <CustomNavbar/>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader>
                <h3>Login Here!!</h3>
              </CardHeader>
              <CardBody>
                <form onSubmit={handleFormSubmit}>
                  {/*Email field*/}
                  <FormGroup>
                    <label htmlFor="email" className="fomr-label">Enter Email</label>
                    <Input
                      type="text"
                      id="email"
                      value={loginDetail.username}
                      onChange={(e) => handleChange(e, "username")}
                      className="form-control"
                    />
                  </FormGroup>
                  <FormGroup>
                    <label htmlFor="password" className="form-label">Enter Password</label>
                    <Input
                      type="password"
                      id="password"
                      onChange={(e) => handleChange(e, "password")}
                      className="form-control"
                    />
                  </FormGroup>
                  <Container className="text-center">
                    <Button color="light" outline type="submit">
                      Login
                    </Button>
                    <Button
                      onClick={handleReset}
                      className="ms-2"
                      outline-color="secondary"

                    >
                      Reset
                    </Button>
                  </Container>
                </form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};
export default Login;
