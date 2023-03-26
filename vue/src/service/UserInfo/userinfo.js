import axios from "axios";
import  "@/plugins/axios";

export default{

  TestExistEmail(email){
     return axios.get("auth/Existmail/"+email);
  } 



}