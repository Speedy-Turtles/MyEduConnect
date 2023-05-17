import Axios from "axios";

import "@/plugins/axios"

export default{
    async chercherSpec(page,search,per){
        const res= await Axios.get(`getSepecialite?${search ? "search="+search : ""}&page=${page}&per_page=${per}`);
        return res;
    },
    deleteSpec(id){
        return Axios.delete("deleteSpec?id="+id);
    },
    updateSpec(data){
        return Axios.post("updateSpecilte",data);
    },
    AddSpec(data){
        return Axios.post("AddSpec",data);
    },
    AddClasse(classe){
        return Axios.post("AddClasse",classe);
    }
}