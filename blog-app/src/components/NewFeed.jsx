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
  Spinner,
  Alert,
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
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    changePage(0);
  }, []);

  const changePage = (pageNumber = 0, pageSize = 5) => {
    if (pageNumber > postContent.pageNumber && postContent.lastPage) return;
    if (pageNumber < postContent.pageNumber && postContent.pageNumber === 0)
      return;

    setLoading(true);
    loadAllPosts(pageNumber, pageSize)
      .then((data) => {
        setPostContent(data);
        setLoading(false);
        window.scroll(0, 0);
      })
      .catch((error) => {
        setLoading(false);
        toast.error("⚠️ Error in loading posts");
      });
  };

  return (
    <div className="container-fluid py-4 bg-light min-vh-100">
      <Row>
        <Col md={{ size: 10, offset: 1 }}>
          <div className="d-flex justify-content-between align-items-center mb-4">
            <h2 className="fw-bold text-dark">
              📚 Blogs <small className="text-muted">({postContent?.totalElements})</small>
            </h2>
          </div>

          {/* Loading state */}
          {loading && (
            <div className="text-center py-5">
              <Spinner color="primary" size="lg" />
              <p className="mt-3 text-muted">Loading posts...</p>
            </div>
          )}

          {/* Empty state */}
          {!loading && postContent.content.length === 0 && (
            <Alert color="warning" className="shadow-sm">
              No posts available. Be the first to add a post! 🚀
            </Alert>
          )}

          {/* Posts List */}
          {!loading && (
            <InfiniteScroll
              dataLength={postContent.content.length}
              style={{ overflow: "visible" }}
            >
              {postContent.content.map((post) => (
                <Post post={post} key={post.postId} />
              ))}
            </InfiniteScroll>
          )}

          {/* Pagination */}
          {!loading && postContent.totalPages > 1 && (
            <Container className="mt-4">
              <Pagination
                size="lg"
                className="justify-content-center flex-wrap gap-2"
              >
                <PaginationItem
                  onClick={() => {
                    if (postContent.pageNumber > 0) {
                      changePage(postContent.pageNumber - 1);
                    }
                  }}
                  disabled={postContent.pageNumber === 0}
                >
                  <PaginationLink previous className="rounded-pill shadow-sm">
                    ⬅ Prev
                  </PaginationLink>
                </PaginationItem>

                {[...Array(postContent.totalPages)].map((_, index) => (
                  <PaginationItem
                    key={index}
                    active={index === postContent.pageNumber}
                    onClick={() => changePage(index)}
                  >
                    <PaginationLink className="rounded-pill shadow-sm">
                      {index + 1}
                    </PaginationLink>
                  </PaginationItem>
                ))}

                <PaginationItem
                  onClick={() => changePage(postContent.pageNumber + 1)}
                  disabled={postContent.lastPage}
                >
                  <PaginationLink next className="rounded-pill shadow-sm">
                    Next ➡
                  </PaginationLink>
                </PaginationItem>
              </Pagination>
            </Container>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default NewFeed;
