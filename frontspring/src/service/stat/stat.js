import axios from "axios";
import  "@/plugins/axios";
import Axios from "axios";

export default {
    getMan(){
        return Axios.get("getUserMan");
    },
    getWomen(){
        return Axios.get("getUserWomen");
    },
    getEtudiant(){
        return Axios.get("getUserEtudiant");
    },
    getEnseignant(){
        return Axios.get("getUserEnseignant");
    },
}