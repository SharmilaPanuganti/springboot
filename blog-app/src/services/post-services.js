import { getCategoryById } from "./category-services";
import { getCurrentUserDetail } from "../auth";
import { myAxios, privateAxios } from "./helper";

//create post function
export const createPost = (postData) => {
  
 const post={title:postData.title,content:postData.content,user:{},category:getCategoryById(postData.category),addedDate:"",imageName:"default.png",comments:[]};
  
   return privateAxios
    .post(
      `/posts/user/${postData.userId}/category/${postData.category}`,
      post
    )
    .then((response) => response.data);
};

//get all posts
export const loadAllPosts = (pageNumber, pageSize) => {
  return myAxios
    .get(
      `/posts/?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=addedDate&sortDir=desc`
    )
    .then((response) => response.data);
};

//load single post of given id
export const loadPost=(postId)=>{
    return privateAxios.get(`/posts/${postId}`)
    .then(response=>response.data)
}

export const createComment = (comment, postId) => {
  return privateAxios.post(`/posts/${postId}/comments`, comment);
};
//upload post banner image
export const uploadPostImage = (image, postId) => {
  let formData = new FormData();
  formData.append("image", image);
  return privateAxios
    .post(`/post/image/upload/${postId}`, formData, {
      Headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then((response) => response.data);
};

//get category wise posts
export const loadPostCategoryWise = (categoryId) => {
  return privateAxios
    .get(`/category/${categoryId}/posts`)
    .then((response) => response.data);
};
//
export const loadPostUserWise = (userId) => {
  return privateAxios
    .get(`/posts/user/${userId}`)
    .then((response) => response.data);
};

//delete post
export const deletePostService = (postId) => {
  return privateAxios
    .delete(`/posts/${postId}`)
    .then((response) => response.data);
};

//update post
export const doUpdatePost = (post, postId) => {
  console.log(post);
  return privateAxios
    .put(`/posts/${postId}`, post)
    .then((response) => response.data);
};
