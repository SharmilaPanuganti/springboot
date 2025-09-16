import React, { useState, useEffect } from "react";
import {
  Button,
  Card,
  CardBody,
  CardFooter,
  Container,
  Table,
} from "reactstrap";
import { getCurrentUserDetail, isloggedIn } from "../auth";

const ViewUserprofile = ({ user }) => {
  const [currentUser, setCurrentUser] = useState(null);
  const [login, setLogin] = useState(null);

  useEffect(() => {
    setCurrentUser(getCurrentUserDetail());
    setLogin(isloggedIn());
  }, []);

  return (
    <Card className="mt-4 border-0 shadow rounded-3">
      <CardBody className="text-center">
        <h3 className="text-uppercase fw-bold text-primary mb-4">
          👤 User Information
        </h3>

        {/* Profile Picture */}
        <Container className="mb-4">
          <img
            style={{
              width: "160px",
              height: "160px",
              objectFit: "cover",
              borderRadius: "50%",
              border: "4px solid #0d6efd",
              boxShadow: "0px 4px 10px rgba(0,0,0,0.15)",
            }}
            src={
              user.image ||
              "https://cdn-icons-png.flaticon.com/512/149/149071.png"
            }
            alt="User profile"
          />
        </Container>

        {/* User Info Table */}
        <Table
          responsive
          bordered
          hover
          className="text-center align-middle shadow-sm"
        >
          <tbody>
            <tr>
              <td className="fw-bold">ID</td>
              <td>LCWD{user.id}</td>
            </tr>
            <tr>
              <td className="fw-bold">Username</td>
              <td>{user.username}</td>
            </tr>
            <tr>
              <td className="fw-bold">Email</td>
              <td>{user.email}</td>
            </tr>
            <tr>
              <td className="fw-bold">About</td>
              <td className="text-muted">{user.about || "—"}</td>
            </tr>
            <tr>
              <td className="fw-bold">Role</td>
              <td>
                <span className="badge bg-success">{user.role}</span>
              </td>
            </tr>
          </tbody>
        </Table>
      </CardBody>

      {/* Update Profile Button (Only if current user is viewing their own profile) */}
      {currentUser?.id === user.id && (
        <CardFooter className="text-center bg-light">
          <Button color="warning" className="px-4 shadow-sm">
            ✏️ Update Profile
          </Button>
        </CardFooter>
      )}
    </Card>
  );
};

export default ViewUserprofile;
