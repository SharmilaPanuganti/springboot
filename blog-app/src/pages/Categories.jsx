import React, { useEffect, useState } from "react";
import { Container, Row, Col } from "reactstrap";
import CategorySideMenu from "../components/CategorySideMenu";
import {
  loadPostCategoryWise,
  deletePostService,
} from "../services/post-services";
import { clippingParents } from "@popperjs/core";
import { toast } from "react-toastify";
import { Post } from "../components/Post";
const Categories = () => {
  const { categoryId } = useParams();
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    console.log(categoryId);
    loadPostCategoryWise(categoryId)
      .then((data) => {
        setPosts([...data]);
      })
      .catch((error) => {
        console.log(error);
        toast.error("error in loading posts");
      });
  }, [categoryId]);
  const deletePost = (post) => {
    //going to delete post
    deletePostService(post.postId)
      .then((res) => {
        console.log(res);
        toast.success("Post is deleted...");
        //loadPostData()
        let newPosts = posts.filter((p) => p.postId != post.postId);
        setPosts([...newPosts]);
      })
      .catch((error) => {
        console.log(error);
        toast.error("error in deleting post");
      });
  };
  return (
    <Base>
      <Container className="mt-3">
        <Row>
          <Col md={2} className="pt-5">
            <CategorySideMenu />
          </Col>
          <Col md={3}>
            <h1>Blogs Count ({posts.length})</h1>
            {posts &&
              posts.map((post, index) => {
                return <Post deletePost={deletePost} key={index} post={post} />;
              })}
            {posts.length <= 0 ? <h1>No Posts in this category</h1> : ""}
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default Categories;
