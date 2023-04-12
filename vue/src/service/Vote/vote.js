import axios from "axios";
import  "@/plugins/axios";

export default{
    AddUserNominated_Session(id){
        return axios.post("vote/AddUserNominated_Session",id)
    },
    checkUser(id){
        return axios.get("vote/CheckUserNomanated/"+id);
    },
    ListNominated(){
        return axios.get("vote/ListNominated");
    },
    addvote(data){
        return axios.post("vote/AddVote",data);
    },
    checkUserVoted(id){
        return axios.get("vote/checkUserVoted/"+id);
    },
    countVoted(id){
        return axios.get("vote/NombreVoted/"+id);
    },
    annuler(id){
        return axios.put("vote/Annuler/"+id);
    },
    suspende(id){
        return axios.put("vote/suspende/"+id);
    },
    deleteSession(id){
        return axios.delete("vote/deleteSession/"+id);
    },
    getSessionTerminer(){
        return axios.get("vote/GetSessionTerminer");
    },
    getUserNomanitedByIdSession(id){
        return axios.get("vote/getUserNomanitedByIdSession/"+id);
    },
    getUserVoted(){
        return axios.get("vote/getUserVoted");
    },
    getUserNominated(){
        return axios.get('vote/getUserNominated');
    }
}