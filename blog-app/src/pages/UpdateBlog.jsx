import React, { useEffect, useState } from "react";
import Base from "../components/base";
import { useParams, useNavigate } from "react-router-dom";
import {
  loadPost,
  updatePost as doUpdatePost,
} from "../services/post-services";
import userContext from "../context/userContext";
import { toast } from "react-toastify";
import { useContext } from "react";
import { loadPost } from "../services/post-services";
import { loadAllCategories } from "../services/category-services";
import { Button, Card, CardBody, Container, Input } from "reactstrap";
import JoditEditor from "jodit-react";
import { useRef } from "react";
function UpdateBlog() {
  const [categories, setCategories] = useState([]);
  const { blogId } = useParams();
  const object = useContext(userContext);
  const navigate = useNavigate();
  const [post, setPost] = useState(null);
  const editor = useRef(null);
  useEffect(() => {
    loadAllCategories()
      .then((data) => {
        console.log(data);
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
    //load the blog from database
    loadPost(blogId)
      .then((data) => {
        setPost({ ...data, categoryId: data.category.categoryId });
      })
      .catch((error) => {
        console.log(error);
        toast.error("error in loading the blog");
      });
  }, []);
  useEffect(() => {
    if (!post) {
      if (post.user.id != object.user.data.id) {
        toast.error("This is not your post!!");
        navigate("/");
      }
    }
  }, [post]);
  const handleChange = (event, fieldName) => {
    setPost({
      ...post,
      [fieldName]: event.target.value,
    });
  };
  const updatePost = (event) => {
    event.preventDefault();
    console.log(post);
    doUpdatePost(
      { ...post, category: { categoryId: post.categoryId } },
      post.postId
    )
      .then((res) => {
        console.log(res);
        toast.success("Post Updated");
      })
      .catch((error) => {
        console.log(error);
        toast.error("Error in updating post");
      });
  };
  const updateHtml = () => {
    return (
      <div className="wrapper ">
        <Card className="shadow-sm border-0 mt-2">
          <CardBody>
            <h3>Update post from here</h3>
            <form onSubmit={updatePost}>
              <div className="my-3">
                <label for="content">Post title</label>
                <input
                  type="textarea"
                  id="content"
                  placeholder="Enter here"
                  className="rounded-0"
                  style={{ height: "300px" }}
                  name="title"
                  value={post.title}
                  onChnage={(event) => handleChange(event, "title")}
                />
              </div>
              <div className="my-3">
                <label for="content">Post Content</label>
                {/* <input type="select" id="content" placeholder="Enter here" className="rounded-0"onChange={fieldChanged} style={{ height: '300px' }}/>*/}
                <JoditEditor
                  ref={editor}
                  value={post.content}
                  onChange={(newContent) =>
                    setPost({ ...post, content: newContent })
                  }
                />
              </div>

              {/*file field*/}
              <div className="mt-3">
                <label htmlFor="image">Select Post banner</label>
                <Input id="image" type="file" onChange={""} />
              </div>
              <div className="my-3">
                <label for="category">Post Category</label>
                <input
                  type="select"
                  id="category"
                  placeholder="Enter here"
                  className="rounded-0"
                  name="categoryId"
                  onChange={(event) => handleChange(event, "title")}
                  value={post.categoryId}
                >
                  <option disabled value={0}>
                    --Select category--
                  </option>
                  {categories.map((category) => (
                    <option
                      value={category.categoryId}
                      key={category.categoryId}
                    >
                      {category.categoryTitle}
                    </option>
                  ))}
                </input>
              </div>
              <Container className="text-center">
                <Button type="submit" className="rounded-0" color="primary">
                  Update Post
                </Button>
                <Button className="rounded-0 ms-2" color="danger">
                  Rest Content
                </Button>
              </Container>
            </form>
          </CardBody>
        </Card>
      </div>
    );
  };
  return (
    <Base>
      <Container>{post && updateHtml()}</Container>
    </Base>
  );
}
export default UpdateBlog;
