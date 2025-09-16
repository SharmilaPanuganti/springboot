import React from "react";
import { Link } from "react-router-dom";
import {
  Card,
  CardBody,
  CardTitle,
  CardText,
  Button,
  Row,
  Col,
} from "reactstrap";

const Post = ({
  post = {
    title: "This is default post title",
    content: "This is default content",
    id: 123,
  },
}) => {
  return (
    <Card className="shadow-sm border-0 rounded-3 mb-4">
      <CardBody>
        {/* Title */}
        <CardTitle tag="h4" className="fw-bold text-primary mb-2">
          {post.title}
          <span className="text-muted fs-6 ms-2">#{post.id}</span>
        </CardTitle>

        {/* Content preview */}
        <CardText
          className="text-muted mb-3"
          dangerouslySetInnerHTML={{
            __html: post.content.substring(0, 120) + "...",
          }}
        />

        {/* Actions */}
        <Row className="align-items-center">
          <Col xs="auto">
            <Button
              color="success"
              tag={Link}
              to={`/posts/${post.id}`}
              className="px-4 shadow-sm"
            >
              <i className="bi bi-book me-2"></i> Read More
            </Button>
          </Col>
          <Col className="text-end">
            <small className="text-secondary fst-italic">
              Updated recently
            </small>
          </Col>
        </Row>
      </CardBody>
    </Card>
  );
};

export default Post;
