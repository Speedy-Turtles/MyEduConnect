import axios from "axios";
import  "@/plugins/axios";

export default{
  getStat(){
    return axios.get("/admin/getstat");
  },
  startVote(data){
    return axios.post("/admin/StartVote",data);
  },
  test_Vote(){
    return axios.get("/admin/test_Vote");
  },
 
}