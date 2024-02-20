import React, { useEffect } from "react";
import { useState } from "react";
import userContext from "./userContext";
import { getCurrentUserDetail, isloggedIn } from "../auth";

function UserProvider({ children }) {
  const [user, setUser] = useState({
    data: {},
    login: false,
  });
  useEffect(() => {
    setUser({
      data: getCurrentUserDetail(),
      login: isloggedIn(),
    });
  }, []);
  return (
    <userContext.Provider value={{ user, setUser }}>
      {children}
    </userContext.Provider>
  );
}
export default UserProvider;
