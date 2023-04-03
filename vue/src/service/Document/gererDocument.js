import axios from "axios";
import  "@/plugins/axios";

export default{
  getAllDocment(){
     return axios.get('/documents/');
  },

  addDocument(doc){
    return axios.post('/documents/addDemande',doc);
  }


}