import axios from "axios";
import  "@/plugins/axios";

export default{

  TestExistEmail(email){
     return axios.get("auth/Existmail/"+email);
  },
  TestExistcode(code){
    return axios.get("auth/testExistToken/"+code);
  },
  GetNiveau(){
   return axios.get("/getNiveau");
  },
  GetUser(id){
    return axios.get("GetUser/"+id);
  }



}