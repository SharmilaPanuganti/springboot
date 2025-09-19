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
  Label,
} from "reactstrap";
import Base from "../components/base";
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
    setLoginDetail({
      ...loginDetail,
      [field]: event.target.value,
    });
  };

  const handleReset = () => {
    setLoginDetail({
      username: "",
      password: "",
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    if (loginDetail.username.trim() === "" || loginDetail.password === "") {
      toast.error("Username or password is required!!");
      return;
    }

    loginUser(loginDetail)
      .then((data) => {

      if(data!=null&& data!=''){
        doLogin(data, () => {
          navigate("/user/dashboard");
        });

        toast.success("Login Success");
        }else{
        toast.error("Invalid Username or Password");
        }
      })
      .catch((error) => {
        if (error.response?.status === 400 || error.response?.status === 404) {
          toast.error(error.response.data.message);
        } else {
          toast.error("Something went wrong on server !!");
        }
      });
  };

  return (
    <Base>
      <CustomNavbar />
      <Container
        className="d-flex justify-content-center align-items-center"
        style={{ minHeight: "80vh" }}
      >
        <Row className="w-100">
          <Col sm={{ size: 6, offset: 3 }} md={{ size: 4, offset: 4 }}>
            <Card
              className="shadow-lg border-0"
              style={{ borderRadius: "15px" }}
            >
              <CardHeader
                className="text-center text-white"
                style={{
                  background: "linear-gradient(135deg, #667eea, #764ba2)",
                  borderTopLeftRadius: "15px",
                  borderTopRightRadius: "15px",
                }}
              >
                <h3 className="mb-0">Login</h3>
              </CardHeader>
              <CardBody className="p-4">
                <form onSubmit={handleFormSubmit}>
                  {/* Email field */}
                  <FormGroup>
                    <Label htmlFor="email" className="fw-bold">
                      Username
                    </Label>
                    <Input
                      type="text"
                      id="email"
                      placeholder="Enter your email"
                      value={loginDetail.username}
                      onChange={(e) => handleChange(e, "username")}
                      className="rounded-pill"
                    />
                  </FormGroup>

                  {/* Password field */}
                  <FormGroup className="mt-3">
                    <Label htmlFor="password" className="fw-bold">
                      Password
                    </Label>
                    <Input
                      type="password"
                      id="password"
                      placeholder="Enter your password"
                      value={loginDetail.password}
                      onChange={(e) => handleChange(e, "password")}
                      className="rounded-pill"
                    />
                  </FormGroup>

                  {/* Buttons */}
                  <Container className="text-center mt-4">
                    <Button
                      color="primary"
                      type="submit"
                      className="px-4 rounded-pill"
                    >
                      Login
                    </Button>
                    <Button
                      onClick={handleReset}
                      color="secondary"
                      type="button"
                      className="ms-3 px-4 rounded-pill"
                      outline
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
