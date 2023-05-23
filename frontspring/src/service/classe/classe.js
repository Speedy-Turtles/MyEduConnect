import Axios from "axios";

import "@/plugins/axios"

export default{
    chercherclasse(page,search,per,id){
        return Axios.get(`getAllClasse?${search ? "search="+search : ""}&page=${page}&per_page=${per}&${id!="" ? "id="+id : 0}`);
    },
    UpdateClasse(id,nom){
        return Axios.post("UpdateClasse?id="+id+"&nom="+nom);
    },
    deleteClasse(id){
        return Axios.delete("deleteClasse?id="+id);
    },
    ChangerClasse(id,id_new){
        return Axios.post("ChangerClasse?id="+id+"&new_id="+id_new);
    }
}