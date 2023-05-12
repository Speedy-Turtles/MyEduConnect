import Axios from "axios";

import "@/plugins/axios"

export default{
    ClassesBySpecialte(id){
        return Axios.get("getClasse?id="+id);
    },
    getClasseById(id){
        return Axios.get("getClasseById?id="+id); 
    }
}