import axios from "axios";

import  "@/plugins/axios";

export default{

  TestExistEmail(email){
     return axios.get("auth/Existmail/"+email);
  },
  TestExistcode(code){
    return axios.get("auth/testExistToken/"+code);
  },
  TestExistCin(cin){
    return axios.get("auth/testExistCin/"+cin);
  },
  GetNiveau(){
   return axios.get("/getNiveau");
  },
  GetUser(id){
    return axios.get("GetUser/"+id);
  },
  getUserAuthentifie(){
    return axios.get("getUserAuthentifie");
  },
  ActiveUser(id, status) {
    return axios.post('/active/' + id, status);
  },
  ActiveUser(id, status) {
       return axios.post('/active/' + id, status);
  }, 
  updateWelcome(){
    return axios.post('/welcome');
  }
}
