import React, { useEffect, useState } from "react";
import Base from "../../components/base";
import { Container, Row, Col, Card, CardBody } from "reactstrap";
import AddPost from "../../components/AddPost";
import { getCurrentUserDetail } from "../../auth";
import { loadPostUserWise, deletePostService } from "../../services/post-services";
import Post from "../../components/Post";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import CustomNavbar from "../../components/CustomNavbar";
import AOS from "aos";
import "aos/dist/aos.css";

const Userdashboard = () => {
  const [user, setUser] = useState(null);
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    AOS.init({ duration: 800, easing: "ease-in-out", once: true });

    const currentUser = getCurrentUserDetail();
    setUser(currentUser);

    if (currentUser?.id) {
      loadPostData(currentUser.id);
    }
  }, []);

  function loadPostData(id) {
    loadPostUserWise(id)
      .then((data) => {
        setPosts(data);
        console.log(data);
      })
      .catch(() => toast.error("❌ Error in loading user posts"));
  }

  const deletePost = (post) => {
    deletePostService(post.postId)
      .then(() => {
        toast.success("🗑️ Post deleted successfully!");
        setPosts((prev) => prev.filter((p) => p.postId !== post.postId));
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
                    Welcome,{" "}
                    <span className="fw-bold text-primary">
                      {user?.name || "User"}
                    </span>
                  </h4>
                  <p className="text-muted mb-0">
                    Manage your posts and add new content here.
                  </p>
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
       <Row className="mt-5">
         <Col>
           <Card className="shadow-sm border-0 rounded-3" style={{ background: "rgba(255, 255, 255, 0.9)" }}>
             <CardBody>
               <h5 className="fw-bold text-dark mb-3">
                 📌 Your Posts{" "}
                 <span className="badge bg-primary">{posts.length}</span>
               </h5>

               {posts.length === 0 ? (
                 <div className="text-center text-muted py-5">
                   <h6>No posts yet! Start by creating one above.</h6>
                 </div>
               ) : (
                 <div className="d-grid gap-4">
                   {posts.map((post, index) => (
                     <div
                       key={post.postId || index}
                       data-aos="fade-up"
                       data-aos-delay={index * 100} // staggered effect
                     >
                       <Card className="shadow-sm border-0 rounded-3 hover-shadow">
                         <CardBody>
                           <Post post={post} deletePost={deletePost} />
                         </CardBody>
                       </Card>
                     </div>
                   ))}
                 </div>
               )}
             </CardBody>
           </Card>
         </Col>
       </Row>
      </Container>
    </Base>
  );
};

export default Userdashboard;
