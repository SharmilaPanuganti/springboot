import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  FormFeedback,
  FormGroup,
  Row,
  Input,
  Label,
} from "reactstrap";
import Base from "../components/base";
import { useEffect, useState } from "react";
import { signUp } from "../services/user-services";
import { toast } from "react-toastify";
import CustomNavbar from "../components/CustomNavbar";

const Signup = () => {
  const [data, setData] = useState({
    username: "",
    email: "",
    password: "",
    about: "",
  });

  useEffect(() => {
    console.log(data);
  }, [data]);

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };

  const resetData = () => {
    setData({
     username: "",
      email: "",
      password: "",
      about: "",
    });
  };

  const submitForm = (event) => {
    event.preventDefault();

    if (error.isError) {
      toast.error("Form data is invalid, correct all details before submitting!");
      setError({ ...error, isError: false });
      return;
    }

    signUp(data)
      .then((resp) => {
        toast.success("User registered successfully! User ID: " + resp.id);
        resetData();
      })
      .catch((error) => {
        console.log("Error:", error);
        setError({
          errors: error,
          isError: true,
        });
      });
  };

  return (
    <Base>
     <CustomNavbar />
      <Container
        className="d-flex justify-content-center align-items-center"
        style={{ minHeight: "85vh" }}
      >
        <Row className="w-100">
          <Col sm={{ size: 6, offset: 3 }} md={{ size: 6, offset: 3 }}>
            <Card
              className="shadow-lg border-0"
              style={{ borderRadius: "15px" }}
            >
              <CardHeader
                className="text-center text-white"
                style={{
                  background: "linear-gradient(135deg, #ff7eb3, #ff758c)",
                  borderTopLeftRadius: "15px",
                  borderTopRightRadius: "15px",
                }}
              >
                <h3 className="mb-0">Create Your Account</h3>
              </CardHeader>

              <CardBody className="p-4">
                <form onSubmit={submitForm}>
                  {/* Name */}
                  <FormGroup>
                    <Label for="username" className="fw-bold">
                      Full Name
                    </Label>
                    <Input
                      type="text"
                      id="username"
                      placeholder="Enter your full name"
                      value={data.name}
                      onChange={(e) => handleChange(e, "username")}
                      invalid={error.errors?.response?.data?.name ? true : false}
                      className="rounded-pill"
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.name}
                    </FormFeedback>
                  </FormGroup>

                  {/* Email */}
                  <FormGroup className="mt-3">
                    <Label for="email" className="fw-bold">
                      Email
                    </Label>
                    <Input
                      type="email"
                      id="email"
                      placeholder="Enter your email"
                      value={data.email}
                      onChange={(e) => handleChange(e, "email")}
                      invalid={
                        error.errors?.response?.data?.email ? true : false
                      }
                      className="rounded-pill"
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.email}
                    </FormFeedback>
                  </FormGroup>

                  {/* Password */}
                  <FormGroup className="mt-3">
                    <Label for="password" className="fw-bold">
                      Password
                    </Label>
                    <Input
                      type="password"
                      id="password"
                      placeholder="Enter your password"
                      value={data.password}
                      onChange={(e) => handleChange(e, "password")}
                      invalid={
                        error.errors?.response?.data?.password ? true : false
                      }
                      className="rounded-pill"
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.password}
                    </FormFeedback>
                  </FormGroup>

                  {/* About */}
                  <FormGroup className="mt-3">
                    <Label for="about" className="fw-bold">
                      About
                    </Label>
                    <Input
                      type="textarea"
                      id="about"
                      placeholder="Tell us about yourself"
                      value={data.about}
                      onChange={(e) => handleChange(e, "about")}
                      invalid={
                        error.errors?.response?.data?.about ? true : false
                      }
                      style={{ height: "120px" }}
                      className="rounded"
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.about}
                    </FormFeedback>
                  </FormGroup>

                  {/* Buttons */}
                  <Container className="text-center mt-4">
                    <Button
                      color="success"
                      type="submit"
                      className="px-4 rounded-pill"
                    >
                      Register
                    </Button>
                    <Button
                      onClick={resetData}
                      color="secondary"
                      type="reset"
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

export default Signup;
