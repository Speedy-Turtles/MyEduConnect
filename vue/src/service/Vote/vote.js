import axios from "axios";
import  "@/plugins/axios";

export default{
    AddUserNominated_Session(id){
        return axios.post("vote/AddUserNominated_Session",id)
    },
    checkUser(){
        return axios.get("vote/CheckUserNomanated");
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
        return axios.delete("vote/Annuler/"+id);
    },
    suspende(id){
        return axios.post("vote/suspende/"+id);
    }
}