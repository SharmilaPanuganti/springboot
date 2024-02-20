import { privateAxios } from "./helper";

export const loadAllCategories=async ()=>{
  const response = await privateAxios.get('/categories/')
  console.log(response)
  return response.data
}

export const getCategoryById=(id)=>{
    return privateAxios.get(`/categories/${id}`)
    .then(response=>response.data).catch((error)=>error)
}