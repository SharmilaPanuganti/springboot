import CategorySideMenu from "../components/CategorySideMenu";
import CustomNavbar from "../components/CustomNavbar";
import NewFeed from "../components/NewFeed";
import Base from "../components/base";
import { Container, Row, Col } from "reactstrap";

const Home = () => {
  return (
    <Base>
    <CustomNavbar />
      <Container className="mt-3">
      
        <Row>
          <Col md={2} className="pt-5">
            <CategorySideMenu />
          </Col>
          <Col md={9}>
            <NewFeed />
          </Col>
        </Row>
      </Container>
      
    </Base>
  );
};
export default Home;
