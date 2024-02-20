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
} from "reactstrap";
import Base from "../components/base";
import { useEffect, useState } from "react";
import { signUp } from "../services/user-services";
import { toast } from "react-toastify";
const Singup = () => {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    about: "",
  });
  useEffect(() => {
    console.log(data);
  }, [data]);
  const [error, setError] = useState({
    error: {},
    isError: false,
  });
  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };
  const resetData = () => {
    setData({
      name: "",
      email: "",
      password: "",
      about: "",
    });
  };
  const submitForm = (event) => {
    event.preventDefault();
    if (error.isError) {
      toast.error("form data is invalid,correct all details then submit ");
      setError({ ...error, isError: false });
      return;
    }
    console.log(data);
    signUp(data)
      .then((resp) => {
        console.log(resp);
        console.log("success log");
        toast.success("user is registered successfully !! user id " + resp.id);
        setData({
          name: " ",
          email: " ",
          password: " ",
          about: " ",
        });
      })
      .catch((error) => {
        console.log(error);
        console.log("Error log");
        setError({
          errors: error,
          isError: true,
        });
      });
  };
  return (
    <Base>
      <Container>
        <Row className="mt-4">
          {JSON.stringify(data)}
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader>
                <h3>Fill the information to register !!</h3>
              </CardHeader>
              <CardBody>
                {/*creating form*/}
                <form onSubmit={submitForm}>
                  <FormGroup>
                    <label htmlFor="name">Enter Name</label>
                    <input
                      type="text"
                      placeholder="Enter here"
                      id="name"
                      onChange={(e) => handleChange(e, "name")}
                      value={data.name}
                      invalid={
                        error.errors?.response?.data?.name ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.name}
                    </FormFeedback>
                  </FormGroup>
                  <FormGroup>
                    <label htmlFor="email">Enter Email</label>
                    <input
                      type="email"
                      placeholder="Enter here"
                      id="email"
                      onChange={(e) => handleChange(e, "email")}
                      value={data.email}
                      invalid={
                        error.errors?.response?.data?.email ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.email}
                    </FormFeedback>
                  </FormGroup>
                  <FormGroup>
                    <label htmlFor="password">Enter Password</label>
                    <input
                      type="password"
                      placeholder="Enter here"
                      id="password"
                      onChange={(e) => handleChange(e, "password")}
                      value={data.password}
                      invalid={
                        error.errors?.response?.data?.password ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.password}
                    </FormFeedback>
                  </FormGroup>

                  <FormGroup>
                    <label htmlFor="about">About</label>
                    <input
                      type="textarea"
                      placeholder="Enter here"
                      id="about"
                      style={{ height: "250px" }}
                      onChange={(e) => handleChange(e, "about")}
                      value={data.about}
                      invalid={
                        error.errors?.response?.data?.about ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.about ? true : false}
                    </FormFeedback>
                  </FormGroup>
                  <Container className="text-center">
                    <Button outline color="light">
                      Register
                    </Button>
                    <Button
                      onClick={resetData}
                      color="secondary"
                      type="reset"
                      className="ms-2"
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
export default Singup;
