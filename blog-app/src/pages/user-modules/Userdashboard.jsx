import React, { useEffect, useState } from "react";
import Base from "../../components/base";
import { Container } from "reactstrap";
import AddPost from "../../components/AddPost";
import NewFeed from "../../components/NewFeed";
import { getCurrentUserDetail } from "../../auth";
import { loadPostUserWise } from "../../services/post-services";
import Post from "../../components/Post";
import { deletePostService } from "../../services/post-services";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import CustomNavbar from "../../components/CustomNavbar";

const Userdashboard = () => {
  const [user, setUser] = useState({});
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    console.log(getCurrentUserDetail());
    setUser(getCurrentUserDetail());
    loadPostData();
  }, []);

  function loadPostData() {
    loadPostUserWise(getCurrentUserDetail().id)
      .then((data) => {
        console.log(data);
        setPosts([...data]);
      })
      .catch((error) => {
        console.log(error);
        toast.error("error in loading user posts");
      });
  }
  //function to delete post
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
     <CustomNavbar/>
      <Container>
       
        <AddPost />
        <h3 className="my-3">Posts Count : ({posts.length})</h3>
        {posts.map((post, index) => {
          return <Post post={post} key={index} deletePost={deletePost} />;
        })}
      </Container>
    </Base>
  );
};
export default Userdashboard;
