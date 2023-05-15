import axios from "axios";
import  "@/plugins/axios";
export default {
    getForums(){
        return axios.get("/forum");
    },
    addPost(post){
        return axios.post("/addPost",post);
    },
    getAllUsers(){
        return axios.get("/allUsers");
    },
    getChatById(id){
        return axios.get("/getChat/"+id);
    },
    getUseById(id){
        return axios.get("/getUser/"+id)
    },
    addMessage(id,message){
        return axios.post("/addMessage/"+id,message);
    },
}