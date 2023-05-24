import axios from "axios";
import  "@/plugins/axios";
export default {
   
  getAllSuggestion(search,per_page,page){
    if(search=='All'){
       search='';
    }  
    return axios.get(`suggestion/getAllSuggestion?${search   ? 'search='+search : ''}&${per_page!=3 ? 'per_page='+per_page : ''}&page=${page}`);
  },
  deleteSuggestion(id){
    return axios.delete("suggestion/deleteSugg/"+id);
  },
  addSuggestion(info){
    return axios.post("suggestion/StoreSugg",info);
  },
  delete_all_sugg(){
    return axios.delete("suggestion/delete_all_sugg");
  }
}