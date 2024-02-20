import Base from "../components/base";
import { useEffect, useState } from "react";
import { loadAllPosts } from "../services/post-services";
import InfiniteScroll from "react-infinite-scroll-component";

import {
  Row,
  Col,
  Pagination,
  PaginationItem,
  PaginationLink,
  Container,
} from "reactstrap";
import Post from "./Post";
import { toast } from "react-toastify";

const NewFeed = () => {
  const [postContent, setPostContent] = useState({
    content: [],
    totalPages: "",
    totalElements: "",
    pageSize: "",
    lastPage: false,
    pageNumber: "",
  });
  
  useEffect(() => {
    //load all the posts from server
    // loadAllPosts(0,5).then(data=>{
    //     console.log(data)
    //     setPostContent(data)
    //   })
    // .catch(error=>{
    //   console.log(error)
    //   toast.error('Error in loading posts')
    // })
    changePage(0);
  }, []);

  const changePage = (pageNumber = 0, pageSize = 5) => {
    if (pageNumber > postContent.pageNumber && postContent.lastPage) {
      return;
    }
    if (pageNumber < postContent.pageNumber && postContent.pageNumber === 0) {
      return;
    }
    loadAllPosts(pageNumber, pageSize)
      .then((data) => {
        console.log(data);
        setPostContent(data);
        
        window.scroll(0, 0);
      })
      .catch((error) => toast.error("Error in loading posts"));
  };
  return (
    <div className="container-fluid">
      <Row>
        <Col
          md={{
            offset: 1,
          }}
        >
          <h1>Blogs Count ({postContent?.totalElements})</h1>

          <InfiniteScroll dataLength={postContent.content.length}>
            {postContent.content.map((post) => (
              <Post post={post} key={post.postId} />
            ))}
          </InfiniteScroll>

          <Container className="mt-3">
            <Pagination size="lg">
              <PaginationItem
                onClick={() => {
                  if (postContent.pageNumber > 0) {
                    changePage(postContent.pageNumber - 1);
                  }
                }}
                disabled={postContent.pageNumber === 0}
              >
                <PaginationLink previous>Previous</PaginationLink>
              </PaginationItem>

              {[
                ...Array(
                  postContent.totalPages
                ),
              ].map((item, index) => (
                <PaginationItem
                  onClick={() => changePage(index)}
                  active={index === postContent.pageNumber}
                  key={index}
                >
                  <PaginationLink>{index + 1}</PaginationLink>
                </PaginationItem>
              ))}
              <PaginationItem
                onClick={() => changePage(postContent.pageNumber + 1)}
                disabled={postContent.lastPage}
              >
                <PaginationLink next>Next</PaginationLink>
              </PaginationItem>
            </Pagination>
          </Container>
        </Col>
      </Row>
    </div>
  );
};
export default NewFeed;
