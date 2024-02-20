import { userContext } from "../context/userContext";
import Base from "../components/base";

const Services = () => {
  return (
    <userContext.Consumer>
      {(user) => (
        <Base>
          <h1>This is services</h1>

          <h1>Welcome {user?.user.login && user?.user.data.user.name}</h1>
        </Base>
      )}
    </userContext.Consumer>
  );
};
export default Services;
