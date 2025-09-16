import React, { useEffect, useState } from "react";
import Base from "../../components/base";
import { Container, Row, Col, Card, CardBody, Button } from "reactstrap";
import AddPost from "../../components/AddPost";
import { getCurrentUserDetail } from "../../auth";
import { loadPostUserWise, deletePostService } from "../../services/post-services";
import Post from "../../components/Post";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import CustomNavbar from "../../components/CustomNavbar";

const Userdashboard = () => {
  const [user, setUser] = useState({});
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    setUser(getCurrentUserDetail());
    loadPostData();
  }, []);

  function loadPostData() {
    loadPostUserWise(getCurrentUserDetail().id)
      .then((data) => {
        setPosts([...data]);
      })
      .catch(() => toast.error("❌ Error in loading user posts"));
  }

  const deletePost = (post) => {
    deletePostService(post.postId)
      .then(() => {
        toast.success("🗑️ Post deleted successfully!");
        let newPosts = posts.filter((p) => p.postId !== post.postId);
        setPosts([...newPosts]);
      })
      .catch(() => toast.error("⚠️ Error in deleting post"));
  };

  return (
    <Base>
      <CustomNavbar />
      <Container className="mt-4">
        {/* User Welcome Section */}
        <Row className="mb-4">
          <Col>
            <Card className="shadow-sm border-0 bg-light">
              <CardBody className="d-flex justify-content-between align-items-center">
                <div>
                  <h4 className="mb-1 text-dark">
                    Welcome, <span className="fw-bold text-primary">{user?.name || "User"}</span>
                  </h4>
                  <p className="text-muted mb-0">Manage your posts and add new content here.</p>
                </div>

              </CardBody>
            </Card>
          </Col>
        </Row>

        {/* Add Post Form */}
        <Row>
          <Col>
            <AddPost />
          </Col>
        </Row>

        {/* Posts Section */}
        <Row className="mt-4">
          <Col>
            <h5 className="fw-bold text-dark mb-3">
              📌 Your Posts <span className="badge bg-primary">{posts.length}</span>
            </h5>
            {posts.length === 0 ? (
              <div className="text-center text-muted py-5">
                <h6>No posts yet! Start by creating one above.</h6>
              </div>
            ) : (
              posts.map((post, index) => (
                <Post
                  post={post}
                  key={index}
                  deletePost={deletePost}
                />
              ))
            )}
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default Userdashboard;
