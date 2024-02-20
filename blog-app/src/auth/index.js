//isloggedIn=>
export const isloggedIn = () => {
  let data = sessionStorage.getItem("data");
  if (data != null) return true;
  else return false;
};
//doLogin=>data=>set to local storage
export const doLogin = (data, next) => {
  sessionStorage.setItem("data", JSON.stringify(data));
  next();
};
//doLogout=>remove from local storage
export const doLogout = (next) => {
  sessionStorage.removeItem("data");
  next();
};
export const getCurrentUserDetail = () => {
  if (isloggedIn()) {
    
    return JSON.parse(sessionStorage.getItem("data")).user;
  } else {
    return false;
  }
};

export const getToken = () => {
  if (isloggedIn()) {
   
    return JSON.parse(sessionStorage.getItem("data"))?.token;
  } else {
    return null;
  }
};
