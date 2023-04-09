import axios from "axios";
import  "@/plugins/axios";

export default{
  getStat(){
    return axios.get("/admin/getstat");
  }
}