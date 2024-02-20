import React, { useState, useEffect } from "react";
import {
  Button,
  Card,
  CardBody,
  CardFooter,
  Container,
  Table,
} from "reactstrap";
import { getCurrentUserDetail } from "../auth";
import { isloggedIn } from "../auth";
const ViewUserprofile = ({ user }) => {
  const [currentUser, setCurrentUser] = useState(null);
  const [login, setlogin] = useState(null);
  useEffect(() => {
    setCurrentUser(getCurrentUserDetail());
    setlogin(isloggedIn());
  }, []);
  return (
    <Card className="mt-2 border-0 rounded-0 shadow-sm">
      <CardBody>
        <h3 className="text-uppercase">user Information</h3>
      </CardBody>
      <Container>
        <img
          style={{ maxWidth: "200px", maxHeight: "200px" }}
          src={
            user.image
              ? user.image
              : "https://www.google.com/url?sa=i&url=https%3A%2F%2Fdribbble.com%2Fshots%2F5679189-Default-Profile-Image&psig=AOvVaw22PkJZpUxWZjuydTwa0ctt&ust=1694341401128000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjw89ynnYEDFQAAAAAdAAAAABAE"
          }
          alt="use profile picture"
          className="image-fluid"
        ></img>
      </Container>
      <Table
        responsive
        striped
        hover
        bordered={true}
        className="text-center mt-5"
      >
        <tbody>
          <tr>
            <td>LCWDB1LOGS ID</td>
            <td>LCWD{user.id}</td>
          </tr>
          <tr>
            <td>USER NAME</td>
            <td>{user.username}</td>
          </tr>
          <tr>
            <td>USER EMAIL</td>
            <td>{user.email}</td>
          </tr>
          <tr>
            <td>ABOUT</td>
            <td>{user.about}</td>
          </tr>
          <tr>
            <td>ROLE</td>
            <td>
              {user.role}
            </td>
          </tr>
        </tbody>
      </Table>
      {currentUser ? (
        currentUser.id == user.id ? (
          <CardFooter className="text-center">
            <Button color="warning">Update Profile</Button>
          </CardFooter>
        ) : (
          ""
        )
      ) : (
        ""
      )}
    </Card>
  );
};
export default ViewUserprofile;
